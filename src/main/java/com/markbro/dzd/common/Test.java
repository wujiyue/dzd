package com.markbro.dzd.common;

import com.markbro.asoiaf.utils.file.FileUtil;
import com.markbro.asoiaf.utils.string.StringUtil;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016-09-19.
 */
public class Test {
    public  static void main(String[] args){

   /* String s="ss<a href=\"#ddds\"> dd";
        Pattern pattern = Pattern.compile("<a href=\"#\\w+\">");
        Matcher matcher = pattern.matcher(s);
        boolean b= matcher.matches();
        System.out.println(matcher.replaceAll("<a>"));*/
        String path="E:\\IdeaProjects\\markbro\\dzd\\webapp\\front\\page\\fontawesome.html";
        String content=readAnReplaceFile(path);
        FileUtil.writeFileByLine(content,path);
    }
    public  static String readAnReplaceFile(String path){
        InputStreamReader isr = null;
        StringBuffer sb = new StringBuffer();
       // Pattern pattern = Pattern.compile("<a href=\"\\.\\.(\\/\\S+)+\">");
        Pattern pattern = Pattern.compile("<a href=\"#\\S+\">");
        Matcher matcher = null;
        try {
            isr = new InputStreamReader(new FileInputStream(new File(path)));
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                // if (!"".equals(line))// 这种写法不全面,换下面的
                if (!StringUtil.isMatch(line, new String[] { "", "\\s+" }))// 默认地把空行剔除
                {
                    matcher = pattern.matcher(line);
                    line=matcher.replaceAll("<a>");
                    sb.append(line).append("\r\n");
                }

            }
            br.close();
            isr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
