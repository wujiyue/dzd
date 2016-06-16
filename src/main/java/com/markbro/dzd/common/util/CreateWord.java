package com.markbro.dzd.common.util;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.Map;

public class CreateWord   {

	private Configuration configuration = null;
	public CreateWord(){
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");//utf-8
	}


	/**
	 * 根据模版生成文档
	 *
	 * @Title: createWord 
	 * @param @param packName		模板所在包名
	 * @param @param templateName	模板名称
	 * @param @param fname			生成的文件放到那个目录
	 * @param @param dataMap		数据
	 * @param @return
	 * @param @throws TemplateException
	 * @param @throws IOException    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public String createWord(String packName, String templateName, String fpath, Map<String, Object> dataMap) 
			throws TemplateException, IOException{
		//3中加载模版的方式 ，此选择用类的路径+包名在包名下查找模版文件  如 /com/jcxt/jccgyy/action
		configuration.setClassForTemplateLoading(this.getClass(), packName);  //FTL文件所存在的位置
		Template t = configuration.getTemplate(templateName); //文件名  外部部门反馈
		//判断文件夹是否存在，不存在创建文件夹
		File fpfile =new File(fpath);    
		//如果文件夹不存在则创建     
		if(!fpfile.exists() && !fpfile.isDirectory()) {       
			fpfile.mkdir();    
		}
		//long creatime = System.currentTimeMillis();//时间戳
		String filePath= fpath+"\\"+System.currentTimeMillis()+".doc";
		File outFile = new File(filePath);
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8")); // 解决xml 非法字符问题
		t.process(dataMap, out);
		out.flush();     
		out.close();
		return filePath;
	}
	public String createWordByDirectoryTemplate(String dir, String templateName, String fpath, Map<String, Object> dataMap) 
			throws TemplateException, IOException{
		//3中加载模版的方式 ，此选择用类的路径+包名在包名下查找模版文件  如 /com/jcxt/jccgyy/action
		//configuration.setClassForTemplateLoading(this.getClass(), packName);  //FTL文件所存在的位置
		configuration.setDirectoryForTemplateLoading(new File(dir));
		Template t = configuration.getTemplate(templateName); //文件名  外部部门反馈
		File fpfile =new File(fpath);    
		if(!fpfile.exists() && !fpfile.isDirectory()) {       
			fpfile.mkdir();    
		}
		//long creatime = System.currentTimeMillis();//时间戳
		String filePath= fpath+"\\"+System.currentTimeMillis()+".doc";
		File outFile = new File(filePath);
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8")); // 解决xml 非法字符问题
		t.process(dataMap, out);
		out.flush();     
		out.close();
		return filePath;
	}

}