package com.markbro.dzd.common.util;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PdfUtilConfig { 
	/**纸张操作区域*/
	private Rectangle rect = null;
	/**纸张操作区域*/
	//private Rectangle rectFor3 = null;
	/**纸张左边距*/
	private float leftmargin = 36;
	/**纸张右边距*/
	private float rightmargin = 36;
	public static float DPI = 72f;
	public static float RADIO = 25.4f;
	
	
	/**主标题文字大小，像素，默认14*/
	private float maintitlesize = 14;
	/**'副标题文本字体大小，默认9*/
	private float subtitlesize = 9;
	/**副标题单元格高度，像素，默认15*/
	private float subtitleheight = 15;
	/**副标题宽度比例，默认4:9:4:8:4:6*/
	private String subtitlewidth = "4:9:4:8:4:6";
	/**副标题内容是否可折行，默认不折行，0:不折行，1:折行*/
	private int subtitlewrap = 0;
	/**副标题表格的boder是否显示，0：不显示，1显示，默认不显示*/
	private int subtitlebordershow = 0;
	/**表格标题是否可以折行，默认不折行，0:不折行，1:折行*/
	private int headwrap = 0;
	/**表头单元格高度，整数，像素，默认15*/
	private float headheight = 15f;
	/**表头字体大小，整数，像素，默认为9*/
	private float headtextsize = 9;
	/**表格表体内容是否可以折行，默认不折行，0:不折行，1:折行*/
	private int bodywrap = 0;
	/**表体字体大小，像素 ，整数，默认为9*/
	private float bodytextsize = 0;
	/**表体单元格高度，整数，像素，默认15*/
	private float bodyheight = 0;
	/**数据表格border是否显示，0：不显示，1：不显示，默认显示*/
	private int bordershow = 1;
	/**页脚字体大小，整数，像素，默认为9*/
	private float footertextsize = 9;
	
	/**主标题文字*/
	private Font mainTitleFont = null;
	/**主标题文字*/
	private Font subTitleFont = null;
	/**主标题文字*/
	private Font headTextFont = null;
	/**主标题文字*/
	private Font bodyTextFont = null;
	/**主标题文字*/
	private Font footerTextFont = null;
	
	
	/**列信息*/
	private Map<String,Object> configMap = null;
	/**pdf基本配置信息*/
	private Map<String,Object> mainMap = null;
	/**
	 * 打印类型
	 */
	private String printType;
	/**
	 * @param pageWidth 纸张宽度  单位毫米
	 * @param pageHeight 纸张高度  单位毫米
	 * */
	public PdfUtilConfig(float pageWidth, float  pageHeight){  
		this.rect = new Rectangle(calSize(pageWidth), calSize(pageHeight));
	}
	/**
	 * @param rect 纸张区域，例如：PageSize.A4
	 * */
	public PdfUtilConfig(Rectangle rect){  
		this.rect = rect;
	} 
	/**
	 * @param 纸张信息map
	 * */
	public PdfUtilConfig(Map<String,Object> paperMap){
		try {
			float pageWidth = Float.parseFloat(String.valueOf(paperMap.get("width")));
			float pageHeight = Float.parseFloat(String.valueOf(paperMap.get("height")));
			this.rect = new Rectangle(calSize(pageWidth), calSize(pageHeight));
			this.leftmargin = calSize(Float.parseFloat(String.valueOf(paperMap.get("leftmargin"))));
			this.rightmargin = calSize(Float.parseFloat(String.valueOf(paperMap.get("rightmargin"))));
			
			//this.rectFor3 = new Rectangle(calSize(pageWidth), calSize(pageHeight/3));
			this.printType=String.valueOf(paperMap.get("guid"));//纸张配置信息的guid
		}  catch (Exception e) {
			e.printStackTrace();
		} 
		
	} 
	
	/**设置基本配置*/
	public void setConfig(){
		try {
			//中文处理  
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			//主标题文字大小
			maintitlesize = Float.parseFloat(String.valueOf(configMap.get("maintitlesize")));
			mainTitleFont = new Font(bfChinese, maintitlesize, Font.BOLD);
			//副标题文字大小
			subtitlesize = Float.parseFloat(String.valueOf(configMap.get("subtitlesize")));
			subTitleFont = new Font(bfChinese, subtitlesize, Font.NORMAL);
			//表头文字大小
			headtextsize = Float.parseFloat(String.valueOf(configMap.get("headtextsize")));
			headTextFont = new Font(bfChinese, headtextsize, Font.BOLD);
			//表体文字大小
			bodytextsize = Float.parseFloat(String.valueOf(configMap.get("bodytextsize")));
			bodyTextFont = new Font(bfChinese, bodytextsize, Font.NORMAL);
			//页脚文字大小
			footertextsize = Float.parseFloat(String.valueOf(configMap.get("footertextsize")));
			footerTextFont = new Font(bfChinese, footertextsize, Font.NORMAL);
			//副标题内容是否可折行
			subtitlewrap = Integer.valueOf(changeObjToStr(configMap.get("subtitlewrap")));
			//副标题表格的boder是否显示
			subtitlebordershow = Integer.valueOf(changeObjToStr(configMap.get("subtitlebordershow")));
			//数据表格border是否显示
			bordershow = Integer.valueOf(changeObjToStr(configMap.get("bordershow")));
			//表格标题是否可以折行
			headwrap = Integer.valueOf(changeObjToStr(configMap.get("headwrap")));
			//表格表体内容是否可以折行
			bodywrap = Integer.valueOf(changeObjToStr(configMap.get("bodywrap")));
			//副标题宽度比例
			subtitlewidth =  changeObjToStr(configMap.get("subtitlewidth"));
			//副标题单元格高度
			subtitleheight = Float.parseFloat(String.valueOf(configMap.get("subtitleheight")));
			//表头单元格高度
			headheight = Float.parseFloat(String.valueOf(configMap.get("headheight")));
			//表体单元格高度
			bodyheight = Float.parseFloat(String.valueOf(configMap.get("bodyheight")));
		}  catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * <p>下载pdf</p>
	 * dataMap键值有： <br>
	 *   fileName：导出文件名称，mainTitle：主标题 <br>
	 *   subTitleArr：副标题相关参数，subTitleMap：副标题数据 <br>
	 *   pageFooterArr：页脚参数，pageFooterMap：页脚数据 <br>
	 *   columParamArr：表格列的相关参数，dataList：生成表格数据 <br>
	 *   sumColumnArr：合计列名称数组<br>
	 * <br>
	 * subTitleArr  数据格式为:{"列英文名:列中文名","列英文名:列中文名"} <br>
	 * 例如 {"custname:客户名称","bh:销售单号","salesman:业务员"}<br>
	 * columParamArr 数据格式为:{"列英文名:列中文名:列宽度:列对齐方式","列英文名:列中文名:列宽度:列对齐方式"} <br>
	 * 例如 {"wldm:货物编号:10:center","wlmc:货物名称:14:center","spec:规格:10:center"}<br>
	 * */
	public  void exportPdf(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		if("2015120212083837824376".equals(printType)){//A4竖向(3份)
			this.exportPdfFor3(dataMap, request, response);
		}else{
		//文件名称
		String fileName = changeObjToStr(dataMap.get("fileName"));
		//页脚数据
		Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
		//副标题数据
		Map<String,Object> subTitleMap  = (Map<String,Object>)dataMap.get("subTitleMap");
		//生成表格的数据
		List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
		
		this.configMap = (Map<String,Object>)dataMap.get("configMap");
		this.mainMap = (Map<String,Object>)dataMap.get("mainMap");
		setConfig();
		
		//主标题
		String mainTitle = changeObjToStr(configMap.get("maintitle"));
		//副标题参数 
		String[] subTitleArr  = (String[])mainMap.get("subTitleArr");
		//页脚参数 
		String[] pageFooterArr  = (String[])mainMap.get("pageFooterArr");
		//主表格列相关参数
		String[] columParamArr  = (String[])mainMap.get("columParamArr");
		//合计列数组
		String[] sumColumnArr = (String[])mainMap.get("sumColumnArr");
		Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
		//列英文名称   
		String[] columnArr = (String[])map.get("columnArr");
		//列中文名称     
		String []titleArr  =  (String[])map.get("titleArr");
		//列宽度
		float[] widthArr = (float[])map.get("widthArr");
		//列对齐方式
		String[] columnAlignArr = (String[])map.get("columnAlignArr");
		
		
		
		fileName = new String(fileName.getBytes(),"iso-8859-1");
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/pdf");//application/pdf//application/x-google-chrome-pdf
		response.setHeader("Content-Disposition","attachment; filename="+fileName+".pdf");
		// 1.创建 Document 对象
		Document document = new Document(this.rect,leftmargin,rightmargin,36,36);  
		// 2.创建书写器，通过书写器将文档写入流中
		PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
		//this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
		writer.setFullCompression();  
		writer.setPdfVersion(PdfWriter.VERSION_1_4);  
		document.open();  
		
		//抬头
		if(mainTitle!=null && !"".equals(mainTitle)){
			Paragraph mainTitleGraph = new Paragraph(mainTitle, mainTitleFont);  
			mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
			mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
			document.add(mainTitleGraph); 
		}
		//副标题表格
		this.addTable(document, subTitleArr, subTitleMap);
		
		//生成表格
        PdfPTable table = generateTable(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, dataList);
        if(table != null){
        	document.add(table);
        }
        
        //添加页脚表格
        
        if(pageFooterArr!=null&&pageFooterArr.length>0){
      	  //原来打印页脚信息是在纸张的最底端，现在是紧挨着正文内容打印页脚信息
	       this.addFooterInfo(document,pageFooterArr,pageFooterMap);
      }
		document.close(); 
	}
}
	
	//该方法导出pdf到本地并返回pdf在本地的路径
	public  String exportPdfWithPdfpath(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		if("2015120212083837824376".equals(printType)){//A4竖向(3份)
			return this.exportPdfFor3WithPdfpath(dataMap, request, response);
		}else{
			String dir=request.getSession().getServletContext().getRealPath("/")+"/exportfile/pdf/";
			File dirFile=new File(dir);
			if(!dirFile.exists()){
				dirFile.mkdir();
			}
			//文件名称
			//String fileName = changeObjToStr(dataMap.get("fileName"));
			String fileName = Guid.get();
			String pdfpath = dir + "\\"+fileName+".pdf";
		//页脚数据
		Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
		//副标题数据
		Map<String,Object> subTitleMap  = (Map<String,Object>)dataMap.get("subTitleMap");
		//生成表格的数据
		List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
		
		this.configMap = (Map<String,Object>)dataMap.get("configMap");
		this.mainMap = (Map<String,Object>)dataMap.get("mainMap");
		setConfig();
		
		//主标题
		String mainTitle = changeObjToStr(configMap.get("maintitle"));
		//副标题参数 
		String[] subTitleArr  = (String[])mainMap.get("subTitleArr");
		//页脚参数 
		String[] pageFooterArr  = (String[])mainMap.get("pageFooterArr");
		//主表格列相关参数
		String[] columParamArr  = (String[])mainMap.get("columParamArr");
		//合计列数组
		String[] sumColumnArr = (String[])mainMap.get("sumColumnArr");
		Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
		//列英文名称   
		String[] columnArr = (String[])map.get("columnArr");
		//列中文名称     
		String []titleArr  =  (String[])map.get("titleArr");
		//列宽度
		float[] widthArr = (float[])map.get("widthArr");
		//列对齐方式
		String[] columnAlignArr = (String[])map.get("columnAlignArr");
		
		
		
		fileName = new String(fileName.getBytes(),"iso-8859-1");
		// 1.创建 Document 对象
		Document document = new Document(this.rect,leftmargin,rightmargin,36,36);  
		
		File pdfpathFile=new File(pdfpath);
		if(!pdfpathFile.exists()){
			pdfpathFile.createNewFile();
		}
		
		// 2.创建书写器，通过书写器将文档写入流中
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfpathFile));
		//this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
		writer.setFullCompression();  
		writer.setPdfVersion(PdfWriter.VERSION_1_4);  
		document.open();  
		
		//抬头
		if(mainTitle!=null && !"".equals(mainTitle)){
			Paragraph mainTitleGraph = new Paragraph(mainTitle, mainTitleFont);  
			mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
			mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
			document.add(mainTitleGraph); 
		}
		//副标题表格
		this.addTable(document, subTitleArr, subTitleMap);
		
		//生成表格
        PdfPTable table = generateTable(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, dataList);
        if(table != null){
        	document.add(table);
        }
        
        //添加页脚表格
        
        if(pageFooterArr!=null&&pageFooterArr.length>0){
      	  //原来打印页脚信息是在纸张的最底端，现在是紧挨着正文内容打印页脚信息
	       this.addFooterInfo(document,pageFooterArr,pageFooterMap);
      }
		document.close(); 
		//返回相对路径
				String basePath=request.getSession().getServletContext().getRealPath("/");
				String relativePath=pdfpathFile.getCanonicalPath();
				relativePath=relativePath.replace(basePath, "");
				relativePath="\\"+relativePath;
				relativePath=relativePath.replace("\\", "/");
				return relativePath;
	}
}

	
	/*public  void exportPdfFor3(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//文件名称
				String fileName = changeObjToStr(dataMap.get("fileName"));
				//页脚数据
				Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
				//副标题数据
				Map<String,Object> subTitleMap  = (Map<String,Object>)dataMap.get("subTitleMap");
				//生成表格的数据
				List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
				
				this.configMap = (Map<String,Object>)dataMap.get("configMap");
				this.mainMap = (Map<String,Object>)dataMap.get("mainMap");
				setConfig();
				
				//主标题
				String mainTitle = changeObjToStr(configMap.get("maintitle"));
				//副标题参数 
				String[] subTitleArr  = (String[])mainMap.get("subTitleArr");
				//页脚参数 
				String[] pageFooterArr  = (String[])mainMap.get("pageFooterArr");
				//主表格列相关参数
				String[] columParamArr  = (String[])mainMap.get("columParamArr");
				//合计列数组
				String[] sumColumnArr = (String[])mainMap.get("sumColumnArr");
				Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
				//列英文名称   
				String[] columnArr = (String[])map.get("columnArr");
				//列中文名称     
				String []titleArr  =  (String[])map.get("titleArr");
				//列宽度
				float[] widthArr = (float[])map.get("widthArr");
				//列对齐方式
				String[] columnAlignArr = (String[])map.get("columnAlignArr");
				
				
				
				fileName = new String(fileName.getBytes(),"iso-8859-1");
				response.reset();
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition","attachment; filename="+fileName+".pdf");
				// 1.创建 Document 对象
				Document document = new Document(rectFor3,leftmargin,rightmargin,36,36);  
				// 2.创建书写器，通过书写器将文档写入流中
				PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
				//this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
				writer.setFullCompression();  
				writer.setPdfVersion(PdfWriter.VERSION_1_4);  
				document.open();	
				
				for(int count=0;count<3;count++){
					//抬头
					if(mainTitle!=null && !"".equals(mainTitle)){
						Paragraph mainTitleGraph = new Paragraph(mainTitle, mainTitleFont);  
						mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
						mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
						document.add(mainTitleGraph); 
					}
					
					PdfPCell cell = new PdfPCell();
					//副标题
					if(subTitleArr!=null && subTitleArr.length>0){
						PdfPTable subTable = new PdfPTable(6);
						subTable.setSpacingBefore(4f);// 设置表格上面空白宽度
						
						String[] sArr = subtitlewidth.split(":");
						float[] widths = { Float.valueOf(sArr[0]),Float.valueOf(sArr[1]),
								Float.valueOf(sArr[2]),Float.valueOf(sArr[3]),
								Float.valueOf(sArr[4]),Float.valueOf(sArr[5])};// 设置表格的列宽和列数  
						subTable.setWidths(widths);
						subTable.setTotalWidth(500);
						subTable.setWidthPercentage(100);//设置表格宽度为%100
						//table.setLockedWidth(true);
						
						//副标题表格的boder是否显示，0：不显示，1显示，默认不显示
						int border = subtitlebordershow;
						for(int i=0;i<subTitleArr.length;i++){
							String subTitle = subTitleArr[i];
							if(subTitle!=null){
								String[] tmp = subTitle.split(":");
								cell = getCell(tmp[1]+"：", subTitleFont, "right", border, subtitlewrap, subtitleheight);
								subTable.addCell(cell);
								if(subTitleMap != null){
									cell = getCell(changeObjToStr(subTitleMap.get(tmp[0])), subTitleFont, "left", border, subtitlewrap, subtitleheight);
								}else{
									cell = getCell("", subTitleFont, "left", border, subtitlewrap, subtitleheight);
								}
								subTable.addCell(cell);
							}
						}
						//计算单元格数量是否能够充满一行，如果不能，则需要补齐剩下单元格
						int remainder = subTitleArr.length%3;
						if(remainder!=0){
							for(int i=0;i<(3-remainder)*2;i++){
								cell = getCell("", subTitleFont, "left", border, subtitlewrap, subtitleheight);
								subTable.addCell(cell);
							}
						}
						document.add(subTable);	
					}
					
					//生成表格
			        PdfPTable table = generateTable(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, dataList);
			        if(table != null){
			        	document.add(table);
			        }
			        
			       //生成页脚表格
			        //this.generateFooterTable(document,pageFooterArr,pageFooterMap);
					//生成页脚信息行
			        this.addFooterLine(document,pageFooterArr,pageFooterMap);
			        if(count<2){
			        	document.newPage();
			        }
			       
				}
				document.close();  
	
}*/
	
	public  void exportPdfFor3(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//文件名称
		String fileName = changeObjToStr(dataMap.get("fileName"));
		//页脚数据
		Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
		//副标题数据
		Map<String,Object> subTitleMap  = (Map<String,Object>)dataMap.get("subTitleMap");
		//生成表格的数据
		List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
		
		this.configMap = (Map<String,Object>)dataMap.get("configMap");
		this.mainMap = (Map<String,Object>)dataMap.get("mainMap");
		setConfig();
		
		//主标题
		String mainTitle = changeObjToStr(configMap.get("maintitle"));
		//副标题参数 
		String[] subTitleArr  = (String[])mainMap.get("subTitleArr");
		//页脚参数 
		String[] pageFooterArr  = (String[])mainMap.get("pageFooterArr");
		//主表格列相关参数
		String[] columParamArr  = (String[])mainMap.get("columParamArr");
		//合计列数组
		String[] sumColumnArr = (String[])mainMap.get("sumColumnArr");
		Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
		//列英文名称   
		String[] columnArr = (String[])map.get("columnArr");
		//列中文名称     
		String []titleArr  =  (String[])map.get("titleArr");
		//列宽度
		float[] widthArr = (float[])map.get("widthArr");
		//列对齐方式
		String[] columnAlignArr = (String[])map.get("columnAlignArr");
		
		
		
		fileName = new String(fileName.getBytes(),"iso-8859-1");
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/pdf");
		//response.setContentType("multipart/form-data"); 
		response.setHeader("Content-Disposition","attachment; filename="+fileName+".pdf");
		// 1.创建 Document 对象
		Document document = new Document(rect,leftmargin,rightmargin,36,36);  
		// 2.创建书写器，通过书写器将文档写入流中
		PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
		//this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
		writer.setFullCompression();  
		writer.setPdfVersion(PdfWriter.VERSION_1_4);  
		document.open();	
		
		for(int count=0;count<3;count++){
			//抬头
			if(mainTitle!=null && !"".equals(mainTitle)){
				Paragraph mainTitleGraph = new Paragraph(mainTitle, mainTitleFont);  
				mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
				if(count>0){
					mainTitleGraph.setLeading(35f);//设置行间距//设置上面空白宽度  
				}else{
					
					mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
				}
				document.add(mainTitleGraph); 
			}
			//副标题表格
			this.addTable(document, subTitleArr, subTitleMap);
			//生成表格
	        PdfPTable table = generateTable(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, dataList);
	        if(table != null){
	        	document.add(table);
	        }
	        
			//生成页脚信息
	        if(pageFooterArr!=null&&pageFooterArr.length>0){
	        //原来打印页脚信息是在纸张的最底端，现在是紧挨着正文内容打印页脚信息
	  	       this.addFooterInfo(document,pageFooterArr,pageFooterMap);
	        }
	        
	        if(count<2){
	        	//圆点分割线
	        	this.addDottedLineSeparator(document);
	        }
	       
		}
		document.close();  
	}
	
	public  String exportPdfFor3WithPdfpath(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String dir=request.getSession().getServletContext().getRealPath("/")+"/exportfile/pdf/";
		File dirFile=new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		//文件名称
		//String fileName = changeObjToStr(dataMap.get("fileName"));
		String fileName = Guid.get();
		String pdfpath = dir + "\\"+fileName+".pdf";
		//页脚数据
		Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
		//副标题数据
		Map<String,Object> subTitleMap  = (Map<String,Object>)dataMap.get("subTitleMap");
		//生成表格的数据
		List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
		
		this.configMap = (Map<String,Object>)dataMap.get("configMap");
		this.mainMap = (Map<String,Object>)dataMap.get("mainMap");
		setConfig();
		
		//主标题
		String mainTitle = changeObjToStr(configMap.get("maintitle"));
		//副标题参数 
		String[] subTitleArr  = (String[])mainMap.get("subTitleArr");
		//页脚参数 
		String[] pageFooterArr  = (String[])mainMap.get("pageFooterArr");
		//主表格列相关参数
		String[] columParamArr  = (String[])mainMap.get("columParamArr");
		//合计列数组
		String[] sumColumnArr = (String[])mainMap.get("sumColumnArr");
		Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
		//列英文名称   
		String[] columnArr = (String[])map.get("columnArr");
		//列中文名称     
		String []titleArr  =  (String[])map.get("titleArr");
		//列宽度
		float[] widthArr = (float[])map.get("widthArr");
		//列对齐方式
		String[] columnAlignArr = (String[])map.get("columnAlignArr");
		
		
		
		fileName = new String(fileName.getBytes(),"iso-8859-1");
		// 1.创建 Document 对象
		Document document = new Document(rect,leftmargin,rightmargin,36,36);  
		
		File pdfpathFile=new File(pdfpath);
		if(!pdfpathFile.exists()){
			pdfpathFile.createNewFile();
		}
		
		// 2.创建书写器，通过书写器将文档写入流中
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(pdfpathFile));
		//this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
		writer.setFullCompression();  
		writer.setPdfVersion(PdfWriter.VERSION_1_4);  
		document.open();	
		
		for(int count=0;count<3;count++){
			//抬头
			if(mainTitle!=null && !"".equals(mainTitle)){
				Paragraph mainTitleGraph = new Paragraph(mainTitle, mainTitleFont);  
				mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
				if(count>0){
					mainTitleGraph.setLeading(35f);//设置行间距//设置上面空白宽度  
				}else{
					
					mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
				}
				document.add(mainTitleGraph); 
			}
			//副标题表格
			this.addTable(document, subTitleArr, subTitleMap);
			//生成表格
	        PdfPTable table = generateTable(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, dataList);
	        if(table != null){
	        	document.add(table);
	        }
	        
			//生成页脚信息
	        if(pageFooterArr!=null&&pageFooterArr.length>0){
	        //原来打印页脚信息是在纸张的最底端，现在是紧挨着正文内容打印页脚信息
	  	       this.addFooterInfo(document,pageFooterArr,pageFooterMap);
	        }
	        if(count<2){
	        	//圆点分割线
	        	this.addDottedLineSeparator(document);
	        }
	       
		}
		document.close();
		//返回相对路径
		String basePath=request.getSession().getServletContext().getRealPath("/");
		String relativePath=pdfpathFile.getCanonicalPath();
		relativePath=relativePath.replace(basePath, "");
		relativePath="\\"+relativePath;
		relativePath=relativePath.replace("\\", "/");
		return relativePath;
	}
	@SuppressWarnings("unused")
	private void addFooterLine(Document document,String[] pageFooterArr,Map<String,Object> pageFooterMap) throws DocumentException{
		Paragraph footer = new Paragraph();  
		for(int i=0;i<pageFooterArr.length;i++){
			String item = pageFooterArr[i];
			String key = item.split(":")[0];
			String name = item.split(":")[1];
			//String name_width = item.split(":")[2];
			//String value_width = item.split(":")[3];
			Chunk chunk = new Chunk(name+" :  "+String.valueOf(pageFooterMap.get(key)),footerTextFont);
			footer.add(chunk);
			if(i<pageFooterArr.length-1){
				footer.add(new Chunk("       "));
			}
		}
		footer.setAlignment(Element.ALIGN_RIGHT);
		document.add(footer);
	}
	/**
	 * 生成脚信息table
	 * @param document
	 * @param title
	 * @param pagedata
	 * @return
	 */
	@SuppressWarnings("unused")
	private void generateFooterTable(Document document,String[] title,Map<String,Object> pagedata){
		try {
		 //增加页脚信息
			//增加页脚信息
			PdfPTable _table = new PdfPTable(2);
			_table.getDefaultCell().setBorder(0);
			float[] headerwidths = {90,10};
			_table.setWidthPercentage(100);
			_table.setTotalWidth(document.right()-document.left());
			try {
				_table.setWidths(headerwidths);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			PdfPCell cell = new PdfPCell();
			if(title!=null&&title.length>0){
				//页脚
				PdfPTable _table_ctx = new PdfPTable(title.length*2);
				
				_table_ctx.setWidthPercentage(100);
				_table_ctx.setTotalWidth((document.right()-document.left())/100*headerwidths[headerwidths.length-1]);
				
				float[] _table_ctx_widths = new float[title.length*2];
				for(int i=0;i<title.length;i++){
					String item = title[i];
					String name_width = item.split(":")[2];
					String value_width = item.split(":")[3];
					_table_ctx_widths[i*2]=Float.parseFloat(name_width);
					_table_ctx_widths[i*2+1]=Float.parseFloat(value_width);
				}
				
				try {
					_table_ctx.setWidths(_table_ctx_widths);
				} catch (DocumentException e) {
					e.printStackTrace();
				}
				
				for(int i=0;i<title.length;i++){
					String item = title[i];
					String key = item.split(":")[0];
					String name = item.split(":")[1];
					
					cell = new PdfPCell(new Paragraph(name+"：", footerTextFont));
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setBorder(0);
					_table_ctx.addCell(cell);
					cell = new PdfPCell(new Paragraph(String.valueOf(pagedata.get(key)), footerTextFont));
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setBorder(0);
					_table_ctx.addCell(cell);
				}
				cell.addElement(_table_ctx);
			}
			cell.setBorder(0);
			_table.addCell(cell);
			document.add(_table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 添加页脚
	 * */
	private void setFooter(PdfWriter writer,Rectangle rect,String[] pageFooterArr, Map<String,Object> pageFooterMap) throws DocumentException, IOException {  
		PageEventConfig event = new PageEventConfig(pageFooterArr,pageFooterMap,footertextsize,footerTextFont);
		writer.setBoxSize("art",rect);  
		writer.setPageEvent(event);  
	}  
	
	 /**
	  * @return 生成表格
	  * @param columnArr 列英文名称数组
	  * @param titleArr 列中文名称数组
	  * @param widthArr 列宽度数组
	  * @param columnAlignArr 列对齐方式数组
	  * @param sumColumnArr 合计列数组
	  * @param dataList 数据
	  * */
	public PdfPTable generateTable(String[] columnArr,String[] titleArr,float[] widthArr,String[] columnAlignArr,
			String[] sumColumnArr, List<Map<String,Object>> dataList){
		if(titleArr==null || titleArr.length==0){
			return null;
		}
		//设置表格的列宽和列数 
		PdfPTable table = new PdfPTable(widthArr);
		try {
			table.setSpacingBefore(4f);// 设置表格上面空白宽度  
			table.setTotalWidth(500f);// 设置表格的宽度  
			table.setWidthPercentage(100);//设置表格宽度为%100 
			PdfPCell cell2 = null;
			
			
			//表头  
			for(int i=0;i<titleArr.length;i++){
				String aTitle = titleArr[i];
				cell2 = getCell(aTitle, headTextFont, "center", bordershow,headwrap,headheight);
				table.addCell(cell2);  

			}
			//如果合计列数组有数据，则添加合计行
			if(sumColumnArr!=null && sumColumnArr.length>0){
				//表尾
				cell2 = getCell("合计", bodyTextFont, "center", bordershow,bodywrap,bodyheight);
				table.addCell(cell2);
				//添加空单元格
				for(int i=0;i<columnArr.length-1;i++){
					cell2 = getCell("", bodyTextFont, "left", bordershow,bodywrap,bodyheight);
					table.addCell(cell2);
				}
				table.setHeaderRows(2);
				table.setFooterRows(1);
			}else{
				table.setHeaderRows(1);
			}
			//数据行
			if(dataList!=null){
				for(int i=0;i<dataList.size();i++){
					Map<String,Object> map = (Map<String,Object>)dataList.get(i);
					for(int j=0;j<columnArr.length;j++){
						String value = changeObjToStr(map.get(columnArr[j]));
						cell2 = getCell(value, bodyTextFont, columnAlignArr[j], bordershow,bodywrap,bodyheight);
						table.addCell(cell2); 
					}
				}
			}
			//如果合计列数组有数据，则添加表格分页事件
			if(sumColumnArr!=null && sumColumnArr.length>0){
				//获取合计列在列数组中的索引位置
				int[] sumColumnIndexArr = getSumColumnIndexArr(columnArr, sumColumnArr);
				TableSplitEventConfig event = new TableSplitEventConfig(dataList, sumColumnArr, sumColumnIndexArr, bodyTextFont);
				table.setTableEvent(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}
	/**
	 * 解析列参数，返回列英文名称数组、中文名称数组、列宽度数组、列对齐方式数组
	 * */
	public Map<String,Object> analyseColumnParamArr(String[] columParamArr){
		if(columParamArr==null || columParamArr.length==0){
			return new HashMap<String,Object>();
		}
		int len = columParamArr.length;
		//列英文名称   
		String[] columnArr = new String[len];
		//列中文名称     
		String []titleArr  =  new String[len];
		//列宽度
		float[] widthArr = new float[len];
		//列对齐方式
		String[] columnAlignArr = new String[len];
		
		for(int i=0;i<len;i++){
			String column = columParamArr[i];
			String[] colArr = column.split(":");
			columnArr[i] = colArr[0];
			titleArr[i] = colArr[1];
			widthArr[i] = Float.parseFloat(colArr[2]);
			columnAlignArr[i] = colArr[3];
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("columnArr", columnArr);
		map.put("titleArr", titleArr);
		map.put("widthArr", widthArr);
		map.put("columnAlignArr", columnAlignArr);
		return map;
	}
	/**获取合计列在列数组中的索引位置*/
	int[] getSumColumnIndexArr(String[] columnArr ,String[] sumColumnArr){
		int[] sumColumnIndexArr = new int[sumColumnArr.length];
		for(int i=0;i<sumColumnArr.length;i++){
			String sumColumn = sumColumnArr[i];
			for(int j=0;j<columnArr.length;j++){
				String column = columnArr[j];
				if(sumColumn.equals(column)){
					sumColumnIndexArr[i] = j;
					break;
				}
			}
		}
		return sumColumnIndexArr;
	}
	/**
	 *  @param str 单元格内容
	 *  @param font  字体
	 *  @param hAlign 水平显示位置  值有：left,right,center
	 *  @param border 0:没有边框,1:有边框
	 *  @param wrap 0:单元格内容不折行，1：可以折行
	 *  @param height 单元格高度
	 * */
	public PdfPCell getCell(String str,Font font,String hAlign,int border,int wrap, float height){
		PdfPCell cell = new PdfPCell(new Paragraph(str,font));//描述  
		if(wrap==0){
			cell.setFixedHeight(height);  
		}else{
			cell.setMinimumHeight(height);  
		}
        int h_align = 0;
        if("left".equals(hAlign)){
        	h_align = Element.ALIGN_LEFT;
        }else if("right".equals(hAlign)){
        	h_align = Element.ALIGN_RIGHT;
        }else{
        	h_align = Element.ALIGN_CENTER;
        }
        cell.setHorizontalAlignment(h_align);// 设置内容水平显示  
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中  
        if(border == 0){
        	cell.setBorder(Rectangle.NO_BORDER);
        }
        return cell;
	}
	
	/**
	 *  @param str 单元格内容
	 *  @param font  字体
	 *  @param hAlign 水平显示位置  值有：left,right,center
	 *  @param border 0:没有边框,1:有边框
	 *  @param wrap 0:单元格内容不折行，1：可以折行
	 * */
	public PdfPCell getCell(String str,Font font,String hAlign,int border,int wrap){
		PdfPCell cell = new PdfPCell(new Paragraph(str,font));//描述  
		if(wrap==0){
			cell.setFixedHeight(15f);  
		}else{
			cell.setMinimumHeight(15f);  
		}
        int h_align = 0;
        if("left".equals(hAlign)){
        	h_align = Element.ALIGN_LEFT;
        }else if("right".equals(hAlign)){
        	h_align = Element.ALIGN_RIGHT;
        }else{
        	h_align = Element.ALIGN_CENTER;
        }
        cell.setHorizontalAlignment(h_align);// 设置内容水平显示  
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);  // 设置垂直居中  
        if(border == 0){
        	cell.setBorder(Rectangle.NO_BORDER);
        }
        return cell;
	}
	/**
	 * 把object对象转换成字符串，如果为null则转换成""
	 * */
	public static String changeObjToStr(Object obj){
		return obj==null?"":String.valueOf(obj);
	}
	/**
	 * 把毫米转换成像素点
	 * */
	public float calSize(float mm){
		return Math.round(mm*DPI/RADIO);
	}
	
	/**
	 * <p>执行导出复杂表头的pdf功能 </p>
	 * dataMap键值有： <br>
	 *   fileName：导出文件名称，mainTitle：主标题 <br>
	 *   subTitleArr：副标题相关参数，subTitleMap：副标题数据 <br>
	 *   pageFooterArr：页脚参数，pageFooterMap：页脚数据 <br>
	 *   columParamArr：表格列的相关参数，dataList：生成表格数据 <br>
	 *   sumColumnArr：合计列名称数组<br>
	 * <br>
	 * subTitleArr  数据格式为:{"列英文名:列中文名","列英文名:列中文名"} <br>
	 * 例如 {"custname:客户名称","bh:销售单号","salesman:业务员"}<br>
	 * columParamArr 数据格式为:{"列英文名:列中文名:列宽度:列对齐方式","列英文名:列中文名:列宽度:列对齐方式"} <br>
	 * 例如 {"wldm:货物编号:10:center","wlmc:货物名称:14:center","spec:规格:10:center"}<br>
	 * */
	public  void exportComplicatedPdf(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		//文件名称
		String fileName = changeObjToStr(dataMap.get("fileName"));
		//主标题
		String mainTitle = changeObjToStr(dataMap.get("mainTitle"));
		//副标题参数 
		String[] subTitleArr  = (String[])dataMap.get("subTitleArr");
		//副标题数据
		Map<String,Object> subTitleMap  = (Map<String,Object>)dataMap.get("subTitleMap");
		//页脚参数 
		String[] pageFooterArr  = (String[])dataMap.get("pageFooterArr");
		//页脚数据
		Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
		//主表格列相关参数
		String[] columParamArr  = (String[])dataMap.get("columParamArr");
		//主表格复杂表头列相关参数
		String[] firstColumnArr  = (String[])dataMap.get("firstColumnArr");
		Map<String,Object> map = this.analyseFirstColumnArr(firstColumnArr);
		//列中文名称
		String[] columntitle = (String[])map.get("columntitle");
		//列宽数量
		int[] columnnum = (int[])map.get("columnnum");
		//合计列数组
		String[] sumColumnArr = (String[])dataMap.get("sumColumnArr");
		map = this.analyseColumnParamArr(columParamArr);
		//列英文名称   
		String[] columnArr = (String[])map.get("columnArr");
		//列中文名称     
		String []titleArr  =  (String[])map.get("titleArr");
		//列宽度
		float[] widthArr = (float[])map.get("widthArr");
		//列对齐方式
		String[] columnAlignArr = (String[])map.get("columnAlignArr");
		
		
		
		//生成表格的数据
		List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
		fileName = new String(fileName.getBytes(),"iso-8859-1");
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment; filename="+fileName+".pdf");
		// 1.创建 Document 对象
		Document document = new Document(this.rect,leftmargin,rightmargin,36,36);  
		// 2.创建书写器，通过书写器将文档写入流中
		PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
		this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
		writer.setFullCompression();  
		writer.setPdfVersion(PdfWriter.VERSION_1_4);  
		document.open();  
		
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
		//粗体   
		Font titleChinese = new Font(bfChinese, 14, Font.BOLD);
		Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
		 
		//抬头
		Paragraph mainTitleGraph = new Paragraph(mainTitle, titleChinese);  
		mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
		mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
		document.add(mainTitleGraph); 
		
		//副标题
		PdfPTable subTable = new PdfPTable(6);
		subTable.setSpacingBefore(4f);// 设置表格上面空白宽度
		float[] widths = { 4f,9f,4f,8f,4f,6f};// 设置表格的列宽和列数  
		subTable.setWidths(widths);
		subTable.setTotalWidth(500);
		subTable.setWidthPercentage(100);//设置表格宽度为%100
		//table.setLockedWidth(true);
		
		int border = 0;
		PdfPCell cell = new PdfPCell();
		if(subTitleArr!=null && subTitleArr.length>0){
			for(int i=0;i<subTitleArr.length;i++){
				String subTitle = subTitleArr[i];
				if(subTitle!=null){
					String[] tmp = subTitle.split(":");
					cell = getCell(tmp[1]+"：", defaultFont,"right",border,0);
					subTable.addCell(cell);
					cell = getCell(changeObjToStr(subTitleMap.get(tmp[0])),defaultFont,"left",border,0);
					subTable.addCell(cell);
				}
			}
			//计算单元格数量是否能够充满一行，如果不能，则需要补齐剩下单元格
			int remainder = subTitleArr.length%3;
			if(remainder!=0){
				for(int i=0;i<(3-remainder)*2;i++){
					cell = getCell("",defaultFont,"left",border,0);
					subTable.addCell(cell);
				}
			}
		}
        document.add(subTable);	
		//生成表格
        PdfPTable table = generateTable_change(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, columntitle, columnnum, dataList);
        document.add(table);
		document.close();  
	}
	
	public  String exportComplicatedPdfWithPdfpath(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
		String dir=request.getSession().getServletContext().getRealPath("/")+"/exportfile/pdf/";
		File dirFile=new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		//文件名称
		//String fileName = changeObjToStr(dataMap.get("fileName"));
		String fileName = Guid.get();//用guid作为文件名可以避免一些问题，不推荐用传来的filename做为文件名
		String pdfpath = dir + "\\"+fileName+".pdf";
		//主标题
		String mainTitle = changeObjToStr(dataMap.get("mainTitle"));
		//副标题参数 
		String[] subTitleArr  = (String[])dataMap.get("subTitleArr");
		//副标题数据
		Map<String,Object> subTitleMap  = (Map<String,Object>)dataMap.get("subTitleMap");
		//页脚参数 
		String[] pageFooterArr  = (String[])dataMap.get("pageFooterArr");
		//页脚数据
		Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
		//主表格列相关参数
		String[] columParamArr  = (String[])dataMap.get("columParamArr");
		//主表格复杂表头列相关参数
		String[] firstColumnArr  = (String[])dataMap.get("firstColumnArr");
		Map<String,Object> map = this.analyseFirstColumnArr(firstColumnArr);
		//列中文名称
		String[] columntitle = (String[])map.get("columntitle");
		//列宽数量
		int[] columnnum = (int[])map.get("columnnum");
		//合计列数组
		String[] sumColumnArr = (String[])dataMap.get("sumColumnArr");
		map = this.analyseColumnParamArr(columParamArr);
		//列英文名称   
		String[] columnArr = (String[])map.get("columnArr");
		//列中文名称     
		String []titleArr  =  (String[])map.get("titleArr");
		//列宽度
		float[] widthArr = (float[])map.get("widthArr");
		//列对齐方式
		String[] columnAlignArr = (String[])map.get("columnAlignArr");
		
		
		
		//生成表格的数据
		List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
		fileName = new String(fileName.getBytes(),"iso-8859-1");
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition","attachment; filename="+fileName+".pdf");
		// 1.创建 Document 对象
		Document document = new Document(this.rect,leftmargin,rightmargin,36,36);  
		// 2.创建书写器，通过书写器将文档写入流中
		//PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
		
		File pdfpathFile=new File(pdfpath);
		if(!pdfpathFile.exists()){
			pdfpathFile.createNewFile();
		}
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfpathFile));//response.getOutputStream());
		
		
		this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
		writer.setFullCompression();  
		writer.setPdfVersion(PdfWriter.VERSION_1_4);  
		document.open();  
		
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
		//粗体   
		Font titleChinese = new Font(bfChinese, 14, Font.BOLD);
		Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
		 
		//抬头
		Paragraph mainTitleGraph = new Paragraph(mainTitle, titleChinese);  
		mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
		mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
		document.add(mainTitleGraph); 
		
		//副标题
		PdfPTable subTable = new PdfPTable(6);
		subTable.setSpacingBefore(4f);// 设置表格上面空白宽度
		float[] widths = { 4f,9f,4f,8f,4f,6f};// 设置表格的列宽和列数  
		subTable.setWidths(widths);
		subTable.setTotalWidth(500);
		subTable.setWidthPercentage(100);//设置表格宽度为%100
		//table.setLockedWidth(true);
		
		int border = 0;
		PdfPCell cell = new PdfPCell();
		if(subTitleArr!=null && subTitleArr.length>0){
			for(int i=0;i<subTitleArr.length;i++){
				String subTitle = subTitleArr[i];
				if(subTitle!=null){
					String[] tmp = subTitle.split(":");
					cell = getCell(tmp[1]+"：", defaultFont,"right",border,0);
					subTable.addCell(cell);
					cell = getCell(changeObjToStr(subTitleMap.get(tmp[0])),defaultFont,"left",border,0);
					subTable.addCell(cell);
				}
			}
			//计算单元格数量是否能够充满一行，如果不能，则需要补齐剩下单元格
			int remainder = subTitleArr.length%3;
			if(remainder!=0){
				for(int i=0;i<(3-remainder)*2;i++){
					cell = getCell("",defaultFont,"left",border,0);
					subTable.addCell(cell);
				}
			}
		}
        document.add(subTable);	
		//生成表格
        PdfPTable table = generateTable_change(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, columntitle, columnnum, dataList);
        document.add(table);
		document.close();  
		
		//返回相对路径
		String basePath=request.getSession().getServletContext().getRealPath("/");
		String relativePath=pdfpathFile.getCanonicalPath();
		relativePath=relativePath.replace(basePath, "");
		relativePath="\\"+relativePath;
		relativePath=relativePath.replace("\\", "/");
		return relativePath;
	}
	/**
	 * 解析列参数，返回列中文名称数组、列宽数量数组
	 * */
	public Map<String,Object> analyseFirstColumnArr(String[] firstColumArr){
		if(firstColumArr==null){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("columntitle", null);
			map.put("columnnum", null);
			return map;
		}
		int len = firstColumArr.length;
		//列中文名称     
		String [] columntitle  =  new String[len];
		//列宽度
		int[] columnnum = new int[len];
		
		for(int i=0;i<len;i++){
			String column = firstColumArr[i];
			if(!column.equals("")){
				String[] colArr = column.split(":");
				columntitle[i] = colArr[0];
				columnnum[i] = Integer.parseInt(colArr[1]);
			} else {
				columntitle[i] = "";
				columnnum[i] = 1;
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("columntitle", columntitle);
		map.put("columnnum", columnnum);
		return map;
	}
	
	/**
	  * 支持两行表头
	  * */
	public PdfPTable generateTable_change(String[] columnArr,String[] titleArr,float[] widthArr,String[] columnAlignArr,
			String[] sumColumnArr, String[] columntitle, int[] columnnum, List<Map<String,Object>> dataList){
		// 设置表格的列宽和列数 
		PdfPTable table = new PdfPTable(widthArr);
		try {
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
			Font subBoldFontChinese = new Font(bfChinese, 8, Font.BOLD); 
			Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
			table.setSpacingBefore(4f);// 设置表格上面空白宽度  
			table.setTotalWidth(500f);// 设置表格的宽度  
			table.setWidthPercentage(100);//设置表格宽度为%100 
			PdfPCell cell2 = null;
			//表头  
			if(columntitle==null){
				for(int i=0;i<titleArr.length;i++){
					String aTitle = titleArr[i];
					cell2 = getCell(aTitle, subBoldFontChinese, "center", 1,1);
					table.addCell(cell2);  

				}
			} else {
				List<PdfPCell> cells = new ArrayList<PdfPCell>();
				int index=0;
				for(int i=0;i<columntitle.length;i++){
					if(columntitle[i].equals("")){
						String aTitle = titleArr[i];
						cell2 = getCell(aTitle, subBoldFontChinese, "center", 1,1);
						cell2.setRowspan(2);
						table.addCell(cell2);
						index++;
					} else {
						String aTitle = columntitle[i];
						cell2 = getCell(aTitle, subBoldFontChinese, "center", 1,1);
						cell2.setColspan(columnnum[i]);
						table.addCell(cell2);
						
						for(int m=0;m<columnnum[i];m++){
							String aTitle_temp = titleArr[m+index];
							cell2 = getCell(aTitle_temp, subBoldFontChinese, "center", 1,1);
							cells.add(cell2);
						}
						index+=columnnum[i];
					}
				}
				for(int i=0;i<cells.size();i++){
					table.addCell(cells.get(i));
				}
			}
			//如果合计列数组有数据，则添加合计行
			if(sumColumnArr!=null && sumColumnArr.length>0){
				//表尾
				cell2 = getCell("合计", defaultFont, "center", 1,1);
				table.addCell(cell2);
				//添加空单元格
				for(int i=0;i<columnArr.length-1;i++){
					cell2 = getCell("", defaultFont, "left", 1,1);
					table.addCell(cell2);
				}
				
				int rowsnum = 2;
				if(columntitle!=null){
					rowsnum=3;
				}
				table.setHeaderRows(rowsnum);
			    table.setFooterRows(1);
			}else{
				int rowsnum = 1;
				if(columntitle!=null){
					rowsnum=2;
				}
				table.setHeaderRows(rowsnum);
			}
			
			
			//数据行
			if(dataList!=null){
				for(int i=0;i<dataList.size();i++){
					Map<String,Object> map = (Map<String,Object>)dataList.get(i);
					for(int j=0;j<columnArr.length;j++){
						String value = changeObjToStr(map.get(columnArr[j]));
						cell2 = getCell(value, defaultFont, columnAlignArr[j], 1,1);
						table.addCell(cell2); 
					}
				}
			}
			//如果合计列数组有数据，则添加表格分页事件
			if(sumColumnArr!=null && sumColumnArr.length>0){
				//获取合计列在列数组中的索引位置
				int[] sumColumnIndexArr = getSumColumnIndexArr(columnArr, sumColumnArr);
				TableSplitEvent event = new TableSplitEvent(dataList, sumColumnArr, sumColumnIndexArr);
				table.setTableEvent(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}
	/**
	 * <p> <b>执行导出pdf功能 </b> </p>
	 * <b> dataMap键值有： </b> <br>
	 *   fileName：导出文件名称，mainTitle：主标题,dataMapList:存放dataMap <br>
	 *   <b>dataMap 键值有：</b><br>
	 *      subTitleArr：副标题相关参数，subTitleMap：副标题数据 <br>
	 *      pageFooterArr：页脚参数，pageFooterMap：页脚数据 <br>
	 *      columParamArr：表格列的相关参数，dataList：生成表格数据 <br>
	 *      sumColumnArr：合计列名称数组<br>
	 * <br>
	 * subTitleArr  数据格式为:{"列英文名:列中文名","列英文名:列中文名"} <br>
	 * 例如 {"custname:客户名称","bh:销售单号","salesman:业务员"}<br>
	 * columParamArr 数据格式为:{"列英文名:列中文名:列宽度:列对齐方式","列英文名:列中文名:列宽度:列对齐方式"} <br>
	 * 例如 {"wldm:货物编号:10:center","wlmc:货物名称:14:center","spec:规格:10:center"}<br>
	 * */
	public  void exportPdfWithNewPage(Map<String,Object> allDataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//文件名称
			String fileName = changeObjToStr(allDataMap.get("fileName"));
			//生成不同文档的数据
			List<Map<String,Object>> dataMapList = (List<Map<String,Object>>)allDataMap.get("dataMapList");
			fileName = new String(fileName.getBytes(),"iso-8859-1");
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition","attachment; filename="+fileName+".pdf");
			// 1.创建 Document 对象
			Document document = new Document(this.rect,leftmargin,rightmargin,36,36);  
			// 2.创建书写器，通过书写器将文档写入流中
			PdfWriter writer = PdfWriter.getInstance(document,response.getOutputStream());
			writer.setFullCompression();  
			writer.setPdfVersion(PdfWriter.VERSION_1_4);  
			
			if(dataMapList!=null&&dataMapList.size()>0){
				Map<String,Object> dataMap = dataMapList.get(0);
				if(dataMap != null&&dataMap.size()>0){
					this.configMap = (Map<String,Object>)dataMap.get("configMap");
					//页脚文字大小
					footertextsize = Float.parseFloat(String.valueOf(configMap.get("footertextsize")));
				}
				//中文处理  
				BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				footerTextFont = new Font(bfChinese, footertextsize, Font.NORMAL);
				this.setFooter(writer, rect, null, null);
				document.open();  
				
				for(int i=0;i<dataMapList.size();i++){
					dataMap = dataMapList.get(i);
					if(i!=0){
						//如果不是第一页，则开始新的一页
						document.newPage();
					}
					document = this.writePdf(dataMap, document);
				}
				document.close();  
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * <p><b>执行导出pdf功能</b></p>
	 * dataMap键值有： <br>
	 *   mainTitle：主标题 <br>
	 *   subTitleArr：副标题相关参数，subTitleMap：副标题数据 <br>
	 *   pageFooterArr：页脚参数，pageFooterMap：页脚数据 <br>
	 *   columParamArr：表格列的相关参数，dataList：生成表格数据 <br>
	 *   sumColumnArr：合计列名称数组<br>
	 * <br>
	 * subTitleArr  数据格式为:{"列英文名:列中文名","列英文名:列中文名"} <br>
	 * 例如 {"custname:客户名称","bh:销售单号","salesman:业务员"}<br>
	 * columParamArr 数据格式为:{"列英文名:列中文名:列宽度:列对齐方式","列英文名:列中文名:列宽度:列对齐方式"} <br>
	 * 例如 {"wldm:货物编号:10:center","wlmc:货物名称:14:center","spec:规格:10:center"}<br>
	 * */
	public  Document writePdf(Map<String,Object> dataMap,Document document){
		try {
			
			//副标题数据
			Map<String,Object> subTitleMap  = (Map<String,Object>)dataMap.get("subTitleMap");
			//生成表格的数据
			List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
			//页脚数据
			Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
			
			this.configMap = (Map<String,Object>)dataMap.get("configMap");
			this.mainMap = (Map<String,Object>)dataMap.get("mainMap");
			
			setConfig();
			
			//主标题
			String mainTitle = changeObjToStr(configMap.get("maintitle"));
			//副标题参数 
			String[] subTitleArr  = (String[])mainMap.get("subTitleArr");
			//页脚参数 
			String[] pageFooterArr  = (String[])mainMap.get("pageFooterArr");
			//主表格列相关参数
			String[] columParamArr  = (String[])mainMap.get("columParamArr");
			//合计列数组
			String[] sumColumnArr = (String[])mainMap.get("sumColumnArr");
			Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
			//列英文名称   
			String[] columnArr = (String[])map.get("columnArr");
			//列中文名称     
			String []titleArr  =  (String[])map.get("titleArr");
			//列宽度
			float[] widthArr = (float[])map.get("widthArr");
			//列对齐方式
			String[] columnAlignArr = (String[])map.get("columnAlignArr");
			//抬头
			if(mainTitle!=null && !"".equals(mainTitle)){
				Paragraph mainTitleGraph = new Paragraph(mainTitle, mainTitleFont);  
				mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
				mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
				document.add(mainTitleGraph); 
			}
			
			PdfPCell cell = new PdfPCell();
			//副标题
			if(subTitleArr!=null && subTitleArr.length>0){
				PdfPTable subTable = new PdfPTable(6);
				subTable.setSpacingBefore(4f);// 设置表格上面空白宽度
				
				String[] sArr = subtitlewidth.split(":");
				float[] widths = { Float.valueOf(sArr[0]),Float.valueOf(sArr[1]),
						Float.valueOf(sArr[2]),Float.valueOf(sArr[3]),
						Float.valueOf(sArr[4]),Float.valueOf(sArr[5])};// 设置表格的列宽和列数  
				subTable.setWidths(widths);
				subTable.setTotalWidth(500);
				subTable.setWidthPercentage(100);//设置表格宽度为%100
				//table.setLockedWidth(true);
				
				//副标题表格的boder是否显示，0：不显示，1显示，默认不显示
				int border = subtitlebordershow;
				for(int i=0;i<subTitleArr.length;i++){
					String subTitle = subTitleArr[i];
					if(subTitle!=null){
						String[] tmp = subTitle.split(":");
						cell = getCell(tmp[1]+"：", subTitleFont, "right", border, subtitlewrap, subtitleheight);
						subTable.addCell(cell);
						if(subTitleMap != null){
							cell = getCell(changeObjToStr(subTitleMap.get(tmp[0])), subTitleFont, "left", border, subtitlewrap, subtitleheight);
						}else{
							cell = getCell("", subTitleFont, "left", border, subtitlewrap, subtitleheight);
						}
						subTable.addCell(cell);
					}
				}
				//计算单元格数量是否能够充满一行，如果不能，则需要补齐剩下单元格
				int remainder = subTitleArr.length%3;
				if(remainder!=0){
					for(int i=0;i<(3-remainder)*2;i++){
						cell = getCell("", subTitleFont, "left", border, subtitlewrap, subtitleheight);
						subTable.addCell(cell);
					}
				}
				document.add(subTable);	
			}
			//生成表格
			PdfPTable table = generateTableNew(columnArr, titleArr, widthArr, columnAlignArr, 
						sumColumnArr, dataList, pageFooterArr, pageFooterMap, document);
			if(table != null){
				document.add(table);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
	/**
	  * 生成表格
	  * */
	public PdfPTable generateTableNew(String[] columnArr,String[] titleArr,float[] widthArr,String[] columnAlignArr,
			String[] sumColumnArr, List<Map<String,Object>> dataList,
			String[] pageFooterArr,Map<String,Object> pageFooterMap, Document document){
		if(titleArr==null || titleArr.length==0){
			return null;
		}
		//设置表格的列宽和列数 
		PdfPTable table = new PdfPTable(widthArr);
		try {
			table.setSpacingBefore(4f);// 设置表格上面空白宽度  
			table.setTotalWidth(500f);// 设置表格的宽度  
			table.setWidthPercentage(100);//设置表格宽度为%100 
			PdfPCell cell2 = null;
			
			
			//表头  
			for(int i=0;i<titleArr.length;i++){
				String aTitle = titleArr[i];
				cell2 = getCell(aTitle, headTextFont, "center", bordershow,headwrap,headheight);
				table.addCell(cell2);  

			}
			//如果合计列数组有数据，则添加合计行
			if(sumColumnArr!=null && sumColumnArr.length>0){
				//表尾
				cell2 = getCell("合计", bodyTextFont, "center", bordershow,bodywrap,bodyheight);
				table.addCell(cell2);
				//添加空单元格
				for(int i=0;i<columnArr.length-1;i++){
					cell2 = getCell("", bodyTextFont, "left", bordershow,bodywrap,bodyheight);
					table.addCell(cell2);
				}
				table.setHeaderRows(2);
				table.setFooterRows(1);
			}else{
				table.setHeaderRows(1);
			}
			//数据行
			if(dataList!=null){
				for(int i=0;i<dataList.size();i++){
					Map<String,Object> map = (Map<String,Object>)dataList.get(i);
					for(int j=0;j<columnArr.length;j++){
						String value = changeObjToStr(map.get(columnArr[j]));
						cell2 = getCell(value, bodyTextFont, columnAlignArr[j], bordershow,bodywrap,bodyheight);
						table.addCell(cell2); 
					}
				}
			}
			//通过表格分页事件，计算合计值，在纸张底部绘制不同表格的页脚
			int[] sumColumnIndexArr = null;
			if(sumColumnArr!=null && sumColumnArr.length>0){
				//获取合计列在列数组中的索引位置
				sumColumnIndexArr = getSumColumnIndexArr(columnArr, sumColumnArr);
			}
			PageTableSplitEventConfig event = new PageTableSplitEventConfig(dataList, sumColumnArr, 
					sumColumnIndexArr, pageFooterArr, pageFooterMap, document, bodyTextFont, footerTextFont);
			table.setTableEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return table;
	}
	/**
	 * 输出一行点分割线
	 * @param document
	 */
	public void addDottedLineSeparator(Document document){
		Paragraph p = new Paragraph(new Chunk(new DottedLineSeparator()));  
		try {
			p.setLeading(25f);
			document.add(p);
		} catch (DocumentException e) {
			e.printStackTrace();
		}  

	}
	
	private void addTable(Document document,String[] subTitleArr,Map<String,Object> subTitleMap){
		try {
	//		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
			//粗体   
	//		Font titleChinese = new Font(bfChinese, 14, Font.BOLD);
	//		Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
			PdfPCell cell = new PdfPCell();
			//副标题
			if(subTitleArr!=null && subTitleArr.length>0){
				PdfPTable subTable = new PdfPTable(6);
				subTable.setSpacingBefore(4f);// 设置表格上面空白宽度
				
				String[] sArr = subtitlewidth.split(":");
				float[] widths = { Float.valueOf(sArr[0]),Float.valueOf(sArr[1]),
						Float.valueOf(sArr[2]),Float.valueOf(sArr[3]),
						Float.valueOf(sArr[4]),Float.valueOf(sArr[5])};// 设置表格的列宽和列数  
				subTable.setWidths(widths);
				subTable.setTotalWidth(500);
				subTable.setWidthPercentage(100);//设置表格宽度为%100
				//table.setLockedWidth(true);
				
				//副标题表格的boder是否显示，0：不显示，1显示，默认不显示
				int border = subtitlebordershow;
				for(int i=0;i<subTitleArr.length;i++){
					String subTitle = subTitleArr[i];
					if(subTitle!=null){
						String[] tmp = subTitle.split(":");
						cell = getCell(tmp[1]+"：", subTitleFont, "right", border, subtitlewrap, subtitleheight);
						subTable.addCell(cell);
						if(subTitleMap != null){
							cell = getCell(changeObjToStr(subTitleMap.get(tmp[0])), subTitleFont, "left", border, subtitlewrap, subtitleheight);
						}else{
							cell = getCell("", subTitleFont, "left", border, subtitlewrap, subtitleheight);
						}
						subTable.addCell(cell);
					}
				}
				//计算单元格数量是否能够充满一行，如果不能，则需要补齐剩下单元格
				int remainder = subTitleArr.length%3;
				if(remainder!=0){
					for(int i=0;i<(3-remainder)*2;i++){
						cell = getCell("", subTitleFont, "left", border, subtitlewrap, subtitleheight);
						subTable.addCell(cell);
					}
				}
				document.add(subTable);	
			}
		
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	
	//该方法是备注单独占据一个段落，其它页脚信息以表格的形式一行3列均分
		private void addFooterInfo(Document document,String[] pageFooterArr,Map<String,Object> pageFooterMap) throws DocumentException{
			try {
				List<String> tempList=new ArrayList<String>();
				Paragraph bzParagraph =null;
				BaseFont bfChinese;
				Font footerTextFont;
				    float footertextsize = 8f;
					bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
					footerTextFont = new Font(bfChinese, footertextsize, Font.NORMAL);
				for(int i=0;i<pageFooterArr.length;i++){
					String item = pageFooterArr[i];
					String key = item.split(":")[0];
					String name = item.split(":")[1];
					
					if(key.equals("bz")){//如果是备注信息或者退货原因，单独占用一个段落
						bzParagraph = new Paragraph();  
						Chunk bzchunk = new Chunk(name+" :  "+String.valueOf(pageFooterMap.get(key)),footerTextFont);
						bzParagraph.add(bzchunk);
						bzParagraph.setAlignment(Element.ALIGN_LEFT);
					}else{
						//收集除了备注和退货原因等需要占据一行的页脚信息
						tempList.add(pageFooterArr[i]);
					}
				}
				
				if(bzParagraph!=null){
					document.add(bzParagraph);
				}
				
				//生成新的页脚数组
				int size=tempList.size();
				if(size>0){
					String[] newpageFooterArr=new String[size];
					for(int i=0;i<size;i++){
						newpageFooterArr[i]=tempList.get(i);
					}
					//生成页脚表格
					this.addTable(document, newpageFooterArr, pageFooterMap);
				}
				
				
				} catch (IOException e) {
					e.printStackTrace();
				} 
					
			}
	
	
}
