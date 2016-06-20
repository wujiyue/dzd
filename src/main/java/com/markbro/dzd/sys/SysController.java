package com.markbro.dzd.sys;

import com.markbro.asoiaf.core.model.Msg;
import com.markbro.dzd.base.tablekey.service.TableKeyService;
import com.markbro.dzd.base.util.dao.BasicUtilMapper;
import com.markbro.dzd.common.util.PatternUtil;
import com.markbro.dzd.common.util.RequestUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Area管理
 * Created by wujiyue on 2016-03-13 03:24:07.
 */
@Controller
@RequestMapping("/sys")
public class SysController extends com.markbro.asoiaf.core.web.BaseController{
    @Autowired
    TableKeyService bmKeyService;
    @Autowired
    TableKeyService gwKeyService;
    @RequestMapping("/json/testTableKey")
    @ResponseBody
    public String testTableKey(){
        String bm=   bmKeyService.getStringId()+"===="+bmKeyService.getIntegerId();
        String gw=   gwKeyService.getStringId()+"===="+gwKeyService.getIntegerId();
        return bm+"$$$"+gw;
    }
    /**
     * 跳转到敏感词页面
     */
    @RequestMapping("/sensitivewords")
    public String sensitivewords(){
        return "/sys/sensitivewords";
    }
    /**
     * 跳转到基础设置页面
     */
    @RequestMapping("/settings")
    public String settings(){
        return "/sys/settings";
    }
    /**
     * 跳转到系统日志页面
     */
    @RequestMapping("/syslog")
    public String syslog(){
        return "/sys/syslog";
    }

    /**
     * 发送sms短信接口
     * @return
     */
    @RequestMapping("/sendSms")
    @ResponseBody
    public Object sendSms(){
        Map map= RequestUtil.getMap(request);
        String phone= PatternUtil.isNull(String.valueOf(map.get("phone")));
        String content= PatternUtil.isNull(String.valueOf(map.get("content")));
        Msg msg=new Msg();
        int r= sendSms(phone,content);
        if(r==1){
            msg.setType(Msg.MsgType.success);
            msg.setContent("发送短信成功！");
        }else{
            msg.setType(Msg.MsgType.error);
            msg.setContent("发送短信失败！");
        }
        return msg;

    }

    @Autowired
    BasicUtilMapper basicUtilMapper;

    private  Integer sendSms(String phone, String content) {
        int timeout = 2 * 60;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();

        Integer returnCode = -99;
        try {
            try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {

                HttpGet getRequest = new HttpGet(
                        "http://utf8.sms.webchinese.cn/?Uid=yigaosu&Key=0575f30733f5480e6adb&smsMob=" + phone + "&smsText=" + content);

                try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
                    if (response.getStatusLine().getStatusCode() != 200) {
                        throw new RuntimeException("Failed : HTTP error code : "
                                + response.getStatusLine().getStatusCode());
                    }
                    StringWriter writer = new StringWriter();
                    IOUtils.copy(response.getEntity().getContent(), writer, "UTF-8");
                    returnCode = Integer.valueOf(writer.toString());

                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        } finally {
         /*   SmsHist smsHist = new SmsHist();
            smsHist.setMobilePhone(mobilePhone);
            smsHist.setContent(content);
            smsHist.setReturnCode(returnCode);
            smsHistService.add(smsHist);*/
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("phone",phone);
            map.put("content",content);
            map.put("returncode",returnCode);
            basicUtilMapper.addSmsHis(map);
            return returnCode;
        }
    }
}
