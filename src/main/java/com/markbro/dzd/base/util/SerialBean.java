package com.markbro.dzd.base.util;

/**
 * Created by Administrator on 2016-06-21.
 */
public class SerialBean {
    private  String starttime;//开始日期
    private  String endtime;//结束日期
    private  String userid;//注册人唯一序列号
    private  String cpdm;//产品代码
    private  String machinecode;//机器码
    private  String sqdm;//授权代码

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCpdm() {
        return cpdm;
    }

    public void setCpdm(String cpdm) {
        this.cpdm = cpdm;
    }

    public String getMachinecode() {
        return machinecode;
    }

    public void setMachinecode(String machinecode) {
        this.machinecode = machinecode;
    }

    public String getSqdm() {
        return sqdm;
    }

    public void setSqdm(String sqdm) {
        this.sqdm = sqdm;
    }

    @Override
    public String toString() {
        return "SerialBean{" +
                "starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", userid='" + userid + '\'' +
                ", cpdm='" + cpdm + '\'' +
                ", machinecode='" + machinecode + '\'' +
                ", sqdm='" + sqdm + '\'' +
                '}';
    }
}
