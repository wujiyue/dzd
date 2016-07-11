package com.markbro.dzd.base.util;

import com.markbro.dzd.common.util.Des;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Scanner;

public class SerialGenerator {
    private static String _key="wujiyue";//密钥
    private  String starttime;//开始日期
    private  String endtime;//结束日期
    private  String userid;//注册人唯一序列号
    private  String cpdm;//产品代码
    private  String machinecode;//机器码
    private  String sqdm;//授权代码
    private Boolean userMachinecode;//使用机器码
    //SerialGenerator
    public static void main(String args[]) {
        System.out.println(getMachineCode());
      //Des des=new Des(_key);
       // System.out.println(des.getDesString(des.getDesString("57FAFB1BC1020AA7553916F20CC169302FDCEE9988777BB4614D941C9D92EEF2766345BE5581D13C")));
        SerialBean bean=new SerialBean();
        bean.setStarttime("2016-06-21");
        bean.setEndtime("2017-06-21");
        bean.setUserMachinecode(false);
        bean.setMachinecode("BFEBFBFF000306A9#714920#D8-50-E6-21-B2-8D");
        bean.setCpdm("7000201");
        bean.setSqdm("001");
        bean.setUserid("wujiyue");
        SerialGenerator generator=new SerialGenerator(bean);
        String code= generator.zhuce();
        System.out.println("注册码："+code);
        SerialBean newbean=generator.jiexi(code);
        System.out.println(newbean);
    }
    public SerialGenerator(SerialBean serialBean){
        this.starttime=serialBean.getStarttime();
        this.endtime=serialBean.getEndtime();
        this.userid=serialBean.getUserid();
        this.cpdm=serialBean.getCpdm();
        this.machinecode=serialBean.getMachinecode();
        this.sqdm=serialBean.getSqdm();
        this.userMachinecode=serialBean.getUserMachinecode();
    }
    //注册获得机器码
    public String zhuce(){
        String str="";
        if(this.userMachinecode){
            str=this.starttime+"_"+this.endtime+"_"+this.userid+"_"+this.cpdm+"_"+this.machinecode+"_"+this.sqdm;
        }else{
            str=this.starttime+"_"+this.endtime+"_"+this.userid+"_"+this.cpdm+"_"+this.sqdm;
        }
        return jiami(str);
    }
    //解析注册码
    public SerialBean jiexi(String code){
        String str=jiemi(code);
        String[] arr=str.split("_");
        SerialBean bean=new SerialBean();
        if(this.userMachinecode){
            bean.setStarttime(arr[0]);
            bean.setEndtime(arr[1]);
            bean.setUserid(arr[2]);
            bean.setCpdm(arr[3]);
            bean.setMachinecode(arr[4]);
            bean.setSqdm(arr[5]);
        }else{
            bean.setStarttime(arr[0]);
            bean.setEndtime(arr[1]);
            bean.setUserid(arr[2]);
            bean.setCpdm(arr[3]);
            bean.setSqdm(arr[4]);
            bean.setUserMachinecode(false);
        }

        return bean;
    }
    //加密序列号
    public   String jiami(String mingwen){
        Des des=new Des(_key);
        String res=des.getEncString(mingwen);
        return res;
    }
    //解密序列号
    public   String jiemi(String miwen){
        Des des=new Des(_key);
        String res=des.getDesString(miwen);
        return res;
    }
    //获得机器码
    public  static String getMachineCode(){
        String cpu="";
        String disk="";
        String mac="";//网卡编号
        try {
             cpu=getCpu();
            System.out.println("CPU序列号："+cpu);
             disk=getDisk("C");
            System.out.println("硬盘序列号："+disk);
            mac=getMac();
            System.out.println("网卡序列号："+mac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  cpu+"#"+disk+"#"+mac;
    }
    //获得cpu序列号
    public static String getCpu()throws Exception {

        Process process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
        process.getOutputStream().close();
        Scanner sc = new Scanner(process.getInputStream());
        String property = sc.next();
        String serial = sc.next();
        return serial;
    }
    //获取硬盘序列号
    public static String getDisk(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto",".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    +"Set colDrives = objFSO.Drives\n"
                    +"Set objDrive = colDrives.item(\"" + drive + "\")\n"
                    +"Wscript.Echo objDrive.SerialNumber";  // see note
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result.trim();
    }
    //获取主板序列号
    public static String getMisc() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p
                    .getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }
    //获得网卡编号
    public static String getMac() {
        try {
            Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
            while (el.hasMoreElements()) {
                byte[] mac = el.nextElement().getHardwareAddress();
                if (mac == null)
                    continue;

                StringBuilder builder = new StringBuilder();
                for (byte b : mac) {
                    builder.append(byte2hex(b));
                    builder.append("-");
                }
                builder.deleteCharAt(builder.length() - 1);
                return builder.toString();

            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
    public static String byte2hex(byte b) {
               String hex = Integer.toHexString(b & 0xFF);
               if (hex.length() == 1) {
                   hex = '0' + hex;
                   }
                hex= hex.toUpperCase();

          return hex;
        }

}

