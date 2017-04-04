package com.markbro.dzd.sys.httpgetinfo.web;

import com.alibaba.fastjson.JSONObject;
import com.markbro.dzd.common.util.Guid;
import com.markbro.dzd.sys.httpgetinfo.service.HttpgetinfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-28.
 */
@Controller
@RequestMapping("/servlet/httpgetinfo")
public class HttpgetinfoController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    HttpgetinfoService httpgetinfoService;


    //进销项查询产品信息
    @RequestMapping(value = "/",params="ywlx=queryCpxx")
    public void queryCpxx() {
        try
        {
            String msg="";
            Map mapIn = new HashMap();
            String qysh = String.valueOf(request.getParameter("nsrsbh"));
            String cpmc=httpgetinfoService.queryCpdm(String.valueOf(request.getParameter("proname")));
            if(!cpmc.equals("")){
                Map map=httpgetinfoService.queryQyZcxx(qysh, cpmc);
                JSONArray ja  = JSONArray.fromObject(map);
                msg=ja.toString();
            }
            PrintWriter out = response.getWriter();
            out.print(msg);
            out.flush();
            out.close();
        }catch (Exception ex){
            logger.error(ex.toString());
        }
    }
    //产品注册文件下载
    @RequestMapping(value = "/",params="ywlx=fileDown")
    public void fileDown() {
        try
        {
            String msg="";
            String qysh = String.valueOf(request.getParameter("nsrsbh"));
            String fjh = String.valueOf(request.getParameter("machineno"));
            String cpmc=httpgetinfoService.queryCpdm(String.valueOf(request.getParameter("proname")));
            String endtime=String.valueOf(request.getParameter("jsrq"));//注册文件的结束日期
            if(endtime.endsWith("?"))
            {
                endtime=endtime.substring(0,endtime.length()-1);
            }
            if(!cpmc.equals("")){
                msg=httpgetinfoService.queryQyxxUrl(qysh, fjh, cpmc,endtime);
            }
            PrintWriter out = response.getWriter();
            out.print(msg);
            out.flush();
            out.close();
        }catch (Exception ex){
            logger.error(ex.toString());
        }
    }

    //产品客户端登录日志记录
    @RequestMapping(value = "/",params="ywlx=cplogin")
    public void cplogin() {
        try
        {
            httpgetinfoService.cplogin(request);
        }catch (Exception ex){
            logger.error(ex.toString());
        }
    }
    public static Date addDate(Date date,long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
        time+=day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }
    //产品客户端（试用）注册
    //返回空字符串表示注册成功。否则会返回失败错误信息。
    @RequestMapping(value = "/",params="ywlx=cpzc")
    public void cpzc() {
        try
        {
            String msg="";
            Map arrMap=new HashMap();
            String banben = String.valueOf(request.getParameter("banben"));
            String qysh = String.valueOf(request.getParameter("qysh"));
            String fjh = String.valueOf(request.getParameter("kpjh"));
            String cpmc = String.valueOf(request.getParameter("cpmc"));
            String syflag = String.valueOf(request.getParameter("syflag"));
            String endtime=String.valueOf(request.getParameter("zcjssj"));
            arrMap.put("qysh", qysh);
            arrMap.put("qymc", String.valueOf(request.getParameter("qymc")));
            arrMap.put("dzdh", String.valueOf(request.getParameter("dzdh")));
            arrMap.put("yhzh", String.valueOf(request.getParameter("yhzh")));
            arrMap.put("fjh", String.valueOf(request.getParameter("kpjh")));
            arrMap.put("lxr", String.valueOf(request.getParameter("lxr")));
            arrMap.put("lxfs", String.valueOf(request.getParameter("lxfs")));
            arrMap.put("starttime", String.valueOf(request.getParameter("zckssj")));
            arrMap.put("endtime", endtime);
            arrMap.put("zcnx", String.valueOf(request.getParameter("zcnx")));
            arrMap.put("syflag", syflag);
            arrMap.put("banben", banben);
            arrMap.put("bz", String.valueOf(request.getParameter("bz")));
            arrMap.put("agentcode", String.valueOf(request.getParameter("agentcode")));
            boolean bflag=true;
            if(httpgetinfoService.queryexistsQYxx(qysh)<=0){
                //新增企业信息
                arrMap.put("guid", Guid.get());
                boolean flag=httpgetinfoService.saveQyxx(arrMap);
                if(!flag){
                    msg="注册失败！";
                    bflag=false;
                }
            }else{
                //更新企业信息
                boolean flag=httpgetinfoService.updateQyxx(arrMap);
                if(!flag){
                    msg="更新企业信息失败！";
                    bflag=false;
                }
            }
            if(bflag){
                //查询产品代码
                cpmc=httpgetinfoService.queryCpdm(cpmc);
                if(!cpmc.equals("")){
                    //判断是否已经正式注册，如果正式注册过，则不能试用注册啦
                    int zscount=httpgetinfoService.queryZsCount(qysh, cpmc, fjh);
                    if(zscount>0){
                        bflag=false;
                        msg="您已经正式注册过该产品,不能试用!";
                    }
                    arrMap.put("cpmc", cpmc);
                    arrMap.put("guid", Guid.get());
                    //如果为试用注册，则判断试用次数，默认2次
                    if(syflag.equals("1")){
                       boolean sycsFlag=httpgetinfoService.queryeQySycs(qysh, cpmc);
                        if(!sycsFlag){
                            bflag=false;
                            msg="您的试用次数已超出！";
                        }
                    }
                    if(bflag){
                        //新增企业注册信息
                        int days=httpgetinfoService.queryQyZcxxDays(qysh, cpmc);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = format.parse(endtime);
                        Date newDate = addDate(date, days);
                        endtime = format.format(newDate);
                        arrMap.put("endtime",endtime);
                        boolean flag=httpgetinfoService.saveCpzc(arrMap);
                        if(!flag){
                            msg="注册失败！";
                        }
                    }
                }
                else{
                    msg="您注册的产品不存在！";
                }
            }
            response.getWriter().print(msg);
            response.getWriter().flush();
            response.getWriter().close();
        }catch (Exception ex){
            logger.error(ex.toString());
        }
    }

    //清空错误的发票明细验证码信息
    @RequestMapping(value = "/",params="ywlx=fpcxyzmClear")
    public void fpcxyzmClear() {
        try{
            httpgetinfoService.fpcxyzmClear(request);
        }catch (Exception ex){
            logger.error(ex.toString());
        }
    }

    //保存发票明细验证码信息
    @RequestMapping(value = "/",params="ywlx=fpcxyzm")
    public void fpcxyzm() {
        try{
            httpgetinfoService.fpcxyzm(request);
        }catch (Exception ex){
            logger.error(ex.toString());
        }
    }
    //上传发票明细信息
    @RequestMapping(value = "/",params="ywlx=fpcxinfoUpload")
    public void fpcxinfoUpload() {
        try{
            httpgetinfoService.fpcxinfoUpload(request);
        }catch (Exception ex){
            logger.error(ex.toString());
        }
    }

    @RequestMapping(value = "/",params="savenr=1")
    public void getOne() throws IOException {//手机端上传内容
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String nr = request.getParameter("nr");
        nr = java.net.URLDecoder.decode(nr, "UTF-8");
        // 每个标签之间用^^分隔，每行内容之间用$$分隔
        String[] nrs = nr.split("~~");
        String sql = "insert into bqnr(lxid,nrwz ,nrzt,nrzx,nrztsize ,nrjx,nrxgscx,nrxgxhx,bqnr ,isprint,dyfs,bs,username,sbbs) values(";
        for (int i = 0; i < nrs.length; i++) {
            // System.out.println(nrs[i]);
            sql += "'" + nrs[i] + "',";
        }
        sql = sql.substring(0, (sql.length() - 1));
        sql = sql + ")";

        boolean flag = httpgetinfoService.savebq(sql);
        String res = "1";
        if (!flag) {
            res = "0";
        }
        Map m = new HashMap();
        m.put("flag",res);

        Object ja  = JSONObject.toJSON(m);
        out.print(ja.toString());
        out.flush();
        out.close();
    }
}
