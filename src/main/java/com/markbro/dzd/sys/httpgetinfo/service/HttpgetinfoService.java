package com.markbro.dzd.sys.httpgetinfo.service;

import com.alibaba.fastjson.JSONObject;
import com.markbro.asoiaf.core.utils.DbUtil;
import com.markbro.asoiaf.utils.ip.IpUtil;
import com.markbro.dzd.common.util.Guid;
import com.markbro.dzd.sys.httpgetinfo.DesUtil;
import com.markbro.dzd.sys.httpgetinfo.FpcxBean;
import com.markbro.dzd.sys.httpgetinfo.FpcxMxBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-28.
 */
@Service
public class HttpgetinfoService {

    public boolean savebq(String sql) {
        boolean flag = true;
        try {
            DbUtil.getJtN().execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 查询产品代码
     * @param cpmc
     * @return
     */
    public String queryCpdm(String cpmc) {
        try {
           String dm="";
            if(cpmc.startsWith("70002"))
            {
                return cpmc;
            }
           String sql=" select cpdm from t_cpxx where 1=1 and zt_dm=1 and cpmc='"+cpmc+"' ";
            List list = DbUtil.getJtN().queryForList(sql);
            if(list.size()>0){
                Map map=(Map)list.get(0);
                dm=String.valueOf(map.get("cpdm"));
                return dm;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 查询企业注册信息
     */
    public Map queryQyZcxx(String qysh,String cpmc) {
        try {

            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(" select c.qysh nsrsbh,c.qymc nsrmc,c.fjh machineno,c.zcnx,c.starttime ksrq,c.endtime jzrq,c.syflag,q.agentcode,q.lxr,q.lxdh,q.qydz dzdh,q.qydh yhzh,c.bz   ");
            sqlBuffer.append(" ,(select count(*) from t_zcpt_cpzcall where zt_dm=1 and syflag=1 and cpmc='"+cpmc+"' and qysh='"+qysh+"') sycs   ");
            sqlBuffer.append(" from t_zcpt_cpzcall c left join t_zcpt_qyxx q on q.qysh=c.qysh and q.zt_dm=1 ");
            sqlBuffer.append(" where 1=1 and c.zt_dm=1 and c.endtime>NOW() and c.cpmc='"+cpmc+"' and c.qysh='"+qysh+"' order by c.endtime desc LIMIT 0,1 ");
            List list = DbUtil.getJtN().queryForList(sqlBuffer.toString());
            if(list.size()>0){
                Map map=(Map)list.get(0);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 查询企业注册文件  返回字符串说明： 注册文件url|注销标志（1被注销0正常）
     */
    public String queryQyxxUrl(String qysh,String fjh,String cpmc,String endtime) {
        try {
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(" select endtime,url from t_zcpt_cpzcall where 1=1 ");
            sqlBuffer.append(" and cpmc='"+cpmc+"' and qysh='"+qysh+"' and fjh='"+fjh+"' order by guid desc LIMIT 0,1 ");
            List list = DbUtil.getJtN().queryForList(sqlBuffer.toString());
            if(list!=null&&list.size()>0){
                Map tmap=(Map)list.get(0);
                if(endtime!=null&&!"".equals(endtime)){
                    String tempEndtime=String.valueOf(tmap.get("endtime"));
                    if("1900-01-01".equals(tempEndtime)){
                        //这是新增的注销记录
                        return String.valueOf(tmap.get("url"))+"|1";
                    }else{
                        //说明没有注销记录
                        //再次查询当前能用的注册文件
                        //为什么要再次查询？因为有可能有超前续费的记录这种注册文件当前还不能用，要查询到当前能用的
                        String sql="select * from t_zcpt_cpzcall where 1=1  and endtime>=NOW() and starttime<=NOW() and cpmc='"+cpmc+"' and qysh='"+qysh+"' and fjh='"+fjh+"' order by endtime desc LIMIT 1  ";
                        List list2 = DbUtil.getJtN().queryForList(sql);
                        if(list2!=null&&list2.size()>0){
                            Map tmap2=(Map)list2.get(0);
                            String zt_dm=String.valueOf(tmap2.get("zt_dm"));
                            if("0".equals(zt_dm))
                            {
                                return "1|1";//注册记录zt_dm=0，这个注销状态与新增的注销记录区分开来
                            }else
                            {
                                if(endtime.equals(tempEndtime)){
                                    //说明客户端的注册文件结束日期没变，不用重新下载
                                    return "OK|OK";
                                }else{
                                    return String.valueOf(tmap2.get("url"))+"|DEL";
                                }
                            }
                        }else{
                            //没有查询到当前可用的记录
                            return "0|0";
                        }
                    }
                }else{
                    //没有传递结束时间，比如没有登录过，或者删除了注册文件
                    String tempEndtime=String.valueOf(tmap.get("endtime"));
                    if("1900-01-01".equals(tempEndtime)){
                        //这是注销的
                        return String.valueOf(tmap.get("url"))+"|1";
                    }else{
                        //return String.valueOf(tmap.get("url"))+"|0";
                        String sql="select * from t_zcpt_cpzcall where 1=1  and endtime>=NOW() and starttime<=NOW() and cpmc='"+cpmc+"' and qysh='"+qysh+"' and fjh='"+fjh+"' order by endtime desc LIMIT 1  ";
                        List list2 = DbUtil.getJtN().queryForList(sql);
                        if(list2!=null&&list2.size()>0){
                            Map tmap2=(Map)list2.get(0);
                            String zt_dm=String.valueOf(tmap2.get("zt_dm"));
                            if("0".equals(zt_dm))
                            {
                                return "1|1";//注册记录zt_dm=0，这个注销状态与新增的注销记录区分开来
                            }else
                            {
                                if(endtime.equals(tempEndtime)){
                                    //说明客户端的注册文件结束日期没变，不用重新下载
                                    return "OK|OK";
                                }else{
                                    return String.valueOf(tmap2.get("url"))+"|DEL";
                                }
                            }
                        }else{
                            //没有查询到当前可用的记录
                            return "0|0";
                        }
                    }
                }
            }else
            {
                //没有查询到当前可用的记录
                return "0|0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void cplogin(HttpServletRequest request){
        try{
            String qysh=request.getParameter("qysh");
            String syflag="1";
            String qymc="";
            String qymcsql="select qymc from t_zcpt_qyxx where 1=1 and zt_dm=1 and qysh='"+qysh+"' limit 1 ";
            List list2 = DbUtil.getJtN().queryForList(qymcsql);
            if(list2.size()>0){
                Map map=(Map)list2.get(0);
                qymc= String.valueOf(map.get("qymc"));
            }
            String cpdm=request.getParameter("cpdm");
            String cpmc="";
            String cpsql="select cpmc from t_cpxx where 1=1 and zt_dm=1 and cpdm='"+cpdm+"' ";
            List list = DbUtil.getJtN().queryForList(cpsql);
            if(list.size()>0){
                Map map=(Map)list.get(0);
                cpmc= String.valueOf(map.get("cpmc"));
            }
            String fjh=request.getParameter("fjh");
            String sf=request.getParameter("sf");
				/*String sfsql="select dq from t_fpgl_dq where  1=1 and ztdm=1 and url like '"+httpurl+"%' ";
				List list3 = this.getJtN().queryForList(sfsql);
				if(list3.size()>0){
					Map map=(Map)list3.get(0);
					sf= String.valueOf(map.get("dq"));
				}*/
            String version=request.getParameter("version");
            //String ip=getIpByHttpRequest(request);
            String ip= IpUtil.getIpByHttpRequest(request);
            StringBuilder sb=new StringBuilder();

            String sysql ="select syflag from t_zcpt_cpzcall where qysh='"+qysh+"' and cpmc='"+cpdm+"' and zt_dm=1 ";
            List<Map<String,Object>> sylist=DbUtil.getJtN().queryForList(sysql);
            for(Map<String,Object> sytemp:sylist){
                if(sytemp.get("syflag").equals("0"))
                {
                    syflag="0";
                    break;
                }
            }
            sb.append("insert into t_cplogin_log(guid,qysh,qymc,fjh,sf,cpdm,cpmc,version,logintime,ip,syflag) values('").append(Guid.get()).append("','")
                    .append(qysh).append("','").append(qymc).append("','").append(fjh).append("','").append(sf).append("','").append(cpdm).append("','").append(cpmc)
                    .append("','").append(version).append("',now(),'").append(ip).append("','"+syflag+"') ");
            System.out.println("产品用户登录：cpdm:"+cpdm+",cpmc:"+cpmc+",qysh:"+qysh+",qymc:"+qymc+",version:"+version+",ip:"+ip+"。");
            DbUtil.getJtN().execute(sb.toString());
            String qybanben="";
            if(!sf.equals("")){
                qybanben=sf;
            }else{
                qybanben="";
            }
            //插入登录累积次数表t_cplogin
            String cksql="select count(1) from t_cplogin where qysh='"+qysh+"' and cpdm='"+cpdm+"'";
            int n=DbUtil.getJtN().queryForInt(cksql);
            String sql="";
            if(n>0){
                sql="update t_cplogin set dlcs=dlcs+1,logintime=now(),version='"+version+"',qybanben='"+qybanben+"',syflag='"+syflag+"' where qysh='"+qysh+"' and cpdm='"+cpdm+"'";
                DbUtil.getJtN().execute(sql);
            }else{
                StringBuilder sb2=new StringBuilder();
                sb2.append("insert into t_cplogin(guid,qysh,qymc,qybanben,cpdm,cpmc,version,dlcs,logintime,syflag) values('").append(Guid.get()).append("','")
                        .append(qysh).append("','").append(qymc).append("','").append(qybanben).append("','").append(cpdm).append("','").append(cpmc)
                        .append("','").append(version).append("',1,now(),'"+syflag+"') ");
                DbUtil.getJtN().execute(sb2.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 查询是否存在企业信息
     */
    public int queryexistsQYxx(String qysh) {
        int i=0;
        try {
            StringBuffer sqlBuffer=new StringBuffer();
            sqlBuffer.append("select count(*) from t_zcpt_qyxx where zt_dm=1 and qysh='"+qysh+"' ");
            i= DbUtil.getJtN().queryForInt(sqlBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 新增企业信息
     */
    public boolean saveQyxx(Map map)
    {
        try{
            String banben=(String)map.get("banben");
            String lrr="804";
            if("3".equals(banben)){
                lrr="664";
            }
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(" insert into t_zcpt_qyxx(guid,qysh,qymc,qydz,qydh,lxr,lxdh,lrsj,zt_dm,lrr,agentcode) ");
            sqlBuffer.append(" values('"+map.get("guid")+"','"+map.get("qysh")+"','"+map.get("qymc")+"','"+map.get("dzdh")+"','"+map.get("yhzh")+"','"+map.get("lxr")+"','"+map.get("lxfs")+"',NOW(),1,'"+lrr+"','"+map.get("agentcode")+"')  ");
            DbUtil.getJtN().execute(sqlBuffer.toString());
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 更新企业信息
     */
    public boolean updateQyxx(Map map)
    {
        try{
            String banben=(String)map.get("banben");
            String lrr="804";
            if("3".equals(banben)){//新疆版
                lrr="664";
            }
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(" update t_zcpt_qyxx set qymc='").append(map.get("qymc")).append("', qydz='")
                    .append(map.get("yhzh")).append("', qydh='").append(map.get("yhzh")).append("', lxr='").append(map.get("lxr")).append("', lxdh='").append(map.get("lxfs")).append("' where qysh='")
                    .append(map.get("qysh")).append("'");
            DbUtil.getJtN().execute(sqlBuffer.toString());
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 查询注册产品试用次数
     * @return false 表示试用次数超出
     */
    public boolean queryeQySycs(String qysh,String cpmc) {
        boolean flag=true;
        int i=0;
        try {
            StringBuffer sqlBuffer=new StringBuffer();
            sqlBuffer.append(" select count(*) from t_zcpt_cpzcall where 1=1 ");
            sqlBuffer.append(" and zt_dm=1 and syflag=1 and cpmc='"+cpmc+"' and qysh='"+qysh+"' ");
            i= DbUtil.getJtN().queryForInt(sqlBuffer.toString());
            if(i>=2){
                flag=false;
            }
        } catch (Exception e) {
            flag=false;
            e.printStackTrace();
        }
        return flag;
    }
    //查询是否正式注册过
    public int queryZsCount(String qysh,String cpmc,String fjh){
        int i=0;
        try {
            String zssql="select count(1) from t_zcpt_cpzcall where syflag=0 and cpmc='"+cpmc+"' and qysh='"+qysh+"' and fjh='"+fjh+"' and zt_dm=1 ";
            i=DbUtil.getJtN().queryForInt(zssql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    /**
     * 查询企业注册剩余天数
     * @return
     */
    public int queryQyZcxxDays(String qysh,String cpmc) {
        try {
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(" select TIMESTAMPDIFF(DAY,NOW(),endtime) days from t_zcpt_cpzcall where 1=1 ");
            sqlBuffer.append(" and zt_dm=1 and endtime>NOW() and syflag=0 and cpmc='"+cpmc+"' and qysh='"+qysh+"' order by endtime desc LIMIT 0,1 ");
            List list = DbUtil.getJtN().queryForList(sqlBuffer.toString());
            if(list.size()>0){
                Map map=(Map)list.get(0);
                int days=Integer.parseInt(String.valueOf(map.get("days")));
                return days;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 产品注册新增
     * @return
     */
    public boolean saveCpzc(Map map)
    {
        try{
            String banben=(String)map.get("banben");
            String lrr="804";
            if("3".equals(banben)){//进销项新疆版
                lrr="664";
            }
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append(" insert into t_zcpt_cpzcall(guid,qysh,qymc,fjh,cpmc,zcnx,starttime,endtime,bz,syflag,lrsj,zt_dm,lrr,yhid,gwid) ");
            sqlBuffer.append(" values('"+map.get("guid")+"','"+map.get("qysh")+"','"+map.get("qymc")+"','"+map.get("fjh")+"','"+map.get("cpmc")+"','"+map.get("zcnx")+"','"+map.get("starttime")+"','"+map.get("endtime")+"','"+map.get("bz")+"','"+map.get("syflag")+"',NOW(),1,'"+lrr+"','"+lrr+"',791)  ");
            DbUtil.getJtN().execute(sqlBuffer.toString());
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //清空错误的发票明细验证码信息
    public String fpcxyzmClear(HttpServletRequest request){
        String res="";
        try{
            String yzm=request.getParameter("yzm");
            String tsinfo=request.getParameter("tsinfo");
            if(yzm!=null&&!"".equals(yzm))
            {
                yzm= DesUtil.decrypt(yzm, "fpcx0210");
                yzm= URLDecoder.decode(yzm, "UTF-8");
            }
            if(tsinfo!=null&&!"".equals(tsinfo))
            {
                tsinfo=DesUtil.decrypt(tsinfo, "fpcx0210");
                tsinfo=URLDecoder.decode(tsinfo,"UTF-8");
            }
            String sql="update t_fpcx_yzm set realinfo='' where yzm='"+yzm+"' and tsinfo='"+tsinfo+"' ";
            System.out.println(sql);
            DbUtil.getJtN().execute(sql);

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return res;

    }
    //保存发票明细验证码信息
    public String fpcxyzm(HttpServletRequest request){
        String res="";
        try{
            String realinfo=request.getParameter("realinfo");
            String yzm=request.getParameter("yzm");
            String tsinfo=request.getParameter("tsinfo");
            if(realinfo!=null&&!"".equals(realinfo))
            {
                realinfo=DesUtil.decrypt(realinfo, "fpcx0210");
                realinfo=URLDecoder.decode(realinfo,"UTF-8");
            }
            if(yzm!=null&&!"".equals(yzm))
            {
                yzm=DesUtil.decrypt(yzm, "fpcx0210");
                yzm=URLDecoder.decode(yzm,"UTF-8");
            }
            if(tsinfo!=null&&!"".equals(tsinfo))
            {
                tsinfo=DesUtil.decrypt(tsinfo, "fpcx0210");
                tsinfo=URLDecoder.decode(tsinfo,"UTF-8");
            }
            String sql="";
            String checkSql="select realinfo from t_fpcx_yzm where yzm='"+yzm+"' and tsinfo='"+tsinfo+"' ";
            List<Map<String,Object>> ls=DbUtil.getJtN().queryForList(checkSql);
            if(ls!=null&&ls.size()>0)
            {
                String dbrealinfo=(String)ls.get(0).get("realinfo");
                if(res!=null&&!"null".equals(dbrealinfo)&&!"".equals(dbrealinfo))
                {
                    res=dbrealinfo;
                    System.out.println(res);
                }
                if(realinfo!=null&&!realinfo.equals(""))
                {
                    sql="update t_fpcx_yzm set realinfo='"+realinfo+"' where yzm='"+yzm+"' and tsinfo='"+tsinfo+"' ";
                    System.out.println(sql);
                    DbUtil.getJtN().execute(sql);
                }
            }else
            {
                sql="insert into t_fpcx_yzm (yzm,tsinfo) values('"+yzm+"','"+tsinfo+"') ";
                System.out.println(sql);
                DbUtil.getJtN().execute(sql);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return res;
    }

    //上传发票明细信息
    public String fpcxinfoUpload(HttpServletRequest request){
        String res="";
        try{
            String fpcxinfo=request.getParameter("aaa");
            String nsrsbh=request.getParameter("bbb");
            String nsrmc=request.getParameter("ccc");
            if(fpcxinfo!=null&&!"".equals(fpcxinfo))
            {
                fpcxinfo=DesUtil.decrypt(fpcxinfo, "fpcx0210");
                fpcxinfo=URLDecoder.decode(fpcxinfo,"UTF-8");
            }
            if(nsrsbh!=null&&!"".equals(nsrsbh))
            {
                nsrsbh=DesUtil.decrypt(nsrsbh, "fpcx0210");
                //fpcxinfo=URLDecoder.decode(nsrsbh,"UTF-8");
            }
            if(nsrmc!=null&&!"".equals(nsrmc))
            {
                nsrmc=DesUtil.decrypt(nsrmc, "fpcx0210");
                nsrmc=URLDecoder.decode(nsrmc,"UTF-8");
            }
            JSONObject jsonobject = JSONObject.parseObject(fpcxinfo);
            FpcxBean b=jsonobject.toJavaObject(jsonobject, FpcxBean.class);

            String delZb="delete from t_jxfpb where fpdm='"+b.getFpdm()+"' and fphm='"+b.getFphm()+"'";
            System.out.println(delZb);
            DbUtil.getJtN().execute(delZb);
            String zbsql="insert into t_jxfpb(fpdm,fphm,kprq,xfsh,xfmc,hjje,hjse,fpzl,qysh,qymc,lrsj,gfsh,gfdzdh,gfyhzh,xfdzdh,xfyhzh,bz) ";
            zbsql+="values('"+b.getFpdm()+"','"+b.getFphm()+"','"+b.getKprq()+"','"+b.getXfsh()+"','"+b.getXfmc()+"','"+b.getHjje()+"','"+b.getHjse()+"','"+b.getFpzl()+"','"+nsrsbh+"','"+nsrmc+"',now(),'"+b.getGfsh()+"','"+b.getGfdzdh()+"','"+b.getGfyhzh()+"','"+b.getXfdzdh()+"','"+b.getXfyhzh()+"','"+b.getBz()+"')";
            System.out.println(zbsql);
            DbUtil.getJtN().execute(zbsql);

            List<FpcxMxBean> ls=b.getMxList();
            if(ls!=null&&ls.size()>0)
            {
                String del2="delete from t_jxfpb_mx where  fpdm='"+b.getFpdm()+"' and fphm='"+b.getFphm()+"'";
                DbUtil.getJtN().execute(del2);
                for(FpcxMxBean mx:ls)
                {
                    String s="insert into t_jxfpb_mx(fpzl,fpdm,fphm,je,se,slv,spmc,jldw,sl,dj) values('"+b.getFpzl()+"','"+b.getFpdm()+"','"+b.getFphm()+"','"+mx.getJe()+"','"+mx.getSe()+"','"+mx.getSlv()+"','"+mx.getSpmc()+"','"+mx.getJldw()+"','"+mx.getSl()+"','"+mx.getDj()+"')";
                    DbUtil.getJtN().execute(s);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return res;

    }
}
