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
public class PdfUtil { 
	/**纸张操作区域*/
	private Rectangle rect = null;
	/**纸张左边距*/
	private float leftmargin = 36;
	/**纸张右边距*/
	private float rightmargin = 36;
	public static float DPI = 72f;
	public static float RADIO = 25.4f;
	/**
	 * 打印类型
	 */
	private String printType;
	/**
	 * @param pageWidth 纸张宽度  单位毫米
	 * @param pageHeight 纸张高度  单位毫米
	 * */
	public PdfUtil(float pageWidth, float  pageHeight){  
		this.rect = new Rectangle(calSize(pageWidth), calSize(pageHeight));
	}
	/**
	 * @param rect 纸张区域，例如：PageSize.A4
	 * */
	public PdfUtil(Rectangle rect){  
		this.rect = rect;
	} 
	/**
	 * @param 纸张信息map
	 * */
	public PdfUtil(Map<String,Object> paperMap){
		float pageWidth = Float.parseFloat(String.valueOf(paperMap.get("width")));
		float pageHeight = Float.parseFloat(String.valueOf(paperMap.get("height")));
		this.rect = new Rectangle(calSize(pageWidth), calSize(pageHeight));
		this.leftmargin = calSize(Float.parseFloat(String.valueOf(paperMap.get("leftmargin"))));
		this.rightmargin = calSize(Float.parseFloat(String.valueOf(paperMap.get("rightmargin"))));
		this.printType=String.valueOf(paperMap.get("guid"));//纸张配置信息的guid
	} 
	
	
	/**
	 * <p>执行导出pdf到本地并返回pdf路径</p>
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
	public String exportPdfWithPdfpath(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		if("2015120212083837824376".equals(printType)){//A4竖向(3份)
			String filepath = this.exportPdfFor3WithPdfpath(dataMap, request, response);
			return filepath;
		}else{
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
			
			//订单合计参数
			//String[] orderHjArr  = (String[])dataMap.get("orderHjArr");
			
			//页脚数据
			Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
			//主表格列相关参数
			String[] columParamArr  = (String[])dataMap.get("columParamArr");
			//合计列数组
			String[] sumColumnArr = (String[])dataMap.get("sumColumnArr");
			Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
			//列英文名称   
			String[] columnArr = (String[])map.get("columnArr");
			//列中文名称     
			String[] titleArr  =  (String[])map.get("titleArr");
			//列宽度
			float[] widthArr = (float[])map.get("widthArr");
			//列对齐方式
			String[] columnAlignArr = (String[])map.get("columnAlignArr");
			//生成表格的数据
			List<Map<String,Object>> dataList = (List<Map<String,Object>>)dataMap.get("dataList");
			fileName = new String(fileName.getBytes(),"iso-8859-1");
			// 1.创建 Document 对象
			Document document = new Document(this.rect,leftmargin,rightmargin,36,36);
			// 2.创建书写器，通过书写器将文档写入流中
			File pdfpathFile=new File(pdfpath);
			if(!pdfpathFile.exists()){
				pdfpathFile.createNewFile();
			}
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfpathFile));//response.getOutputStream());
			//this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
			writer.setFullCompression();  
			writer.setPdfVersion(PdfWriter.VERSION_1_4);  
			document.open();  
			
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
			//粗体   
			Font titleChinese = new Font(bfChinese, 14, Font.BOLD);
			//Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
			
			//抬头
			if(mainTitle!=null && !"".equals(mainTitle)){
				Paragraph mainTitleGraph = new Paragraph(mainTitle, titleChinese);  
				mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
				mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
				document.add(mainTitleGraph); 
			}
			
			//副标题
			this.addTable(document,subTitleArr,subTitleMap);
			//生成表格
	        PdfPTable table = generateTable(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, dataList);
	        if(table != null){
	        	document.add(table);
	        }
	        
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
	
	
	public void exportPdf(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		if("2015120212083837824376".equals(printType)){//A4竖向(3份)
			this.exportPdfFor3(dataMap, request, response);
		}else{
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
			
			//订单合计参数
			//String[] orderHjArr  = (String[])dataMap.get("orderHjArr");
			
			//页脚数据
			Map<String,Object> pageFooterMap  = (Map<String,Object>)dataMap.get("pageFooterMap");
			//主表格列相关参数
			String[] columParamArr  = (String[])dataMap.get("columParamArr");
			//合计列数组
			String[] sumColumnArr = (String[])dataMap.get("sumColumnArr");
			Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
			//列英文名称   
			String[] columnArr = (String[])map.get("columnArr");
			//列中文名称     
			String[] titleArr  =  (String[])map.get("titleArr");
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
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			//this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
			writer.setFullCompression();  
			writer.setPdfVersion(PdfWriter.VERSION_1_4);  
			document.open();  
			
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
			//粗体   
			Font titleChinese = new Font(bfChinese, 14, Font.BOLD);
			//Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
			
			//抬头
			if(mainTitle!=null && !"".equals(mainTitle)){
				Paragraph mainTitleGraph = new Paragraph(mainTitle, titleChinese);  
				mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
				mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
				document.add(mainTitleGraph); 
			}
			
			//副标题
			this.addTable(document,subTitleArr,subTitleMap);
			//生成表格
	        PdfPTable table = generateTable(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, dataList);
	        if(table != null){
	        	document.add(table);
	        }
	        
	        if(pageFooterArr!=null&&pageFooterArr.length>0){
	        	  //原来打印页脚信息是在纸张的最底端，现在是紧挨着正文内容打印页脚信息
	 	       this.addFooterInfo(document,pageFooterArr,pageFooterMap);
	        }
	        
			document.close();  
		}
	}
	private void addTable(Document document,String[] subTitleArr,Map<String,Object> subTitleMap){
		try {
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
		//粗体   
		//Font titleChinese = new Font(bfChinese, 14, Font.BOLD);
		Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
		PdfPCell cell = new PdfPCell();
		//副标题
		if(subTitleArr!=null && subTitleArr.length>0){
			PdfPTable subTable = new PdfPTable(6);
			subTable.setSpacingBefore(4f);// 设置表格上面空白宽度
			float[] widths = { 4f,9f,4f,8f,4f,6f};// 设置表格的列宽和列数  
			
				subTable.setWidths(widths);
		
			subTable.setTotalWidth(500);
			subTable.setWidthPercentage(100);//设置表格宽度为%100
			//table.setLockedWidth(true);
			
			int border = 0;
			for(int i=0;i<subTitleArr.length;i++){
				String subTitle = subTitleArr[i];
				if(subTitle!=null){
					String[] tmp = subTitle.split(":");
					cell = getCell(tmp[1]+"：", defaultFont,"right",border,0);
					subTable.addCell(cell);
					if(subTitleMap != null){
						cell = getCell(changeObjToStr(subTitleMap.get(tmp[0])),defaultFont,"left",border,0);
					}else{
						cell = getCell("",defaultFont,"left",border,0);
					}
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
			document.add(subTable);	
		}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加页脚
	 * */
	private void setFooter(PdfWriter writer,Rectangle rect,String[] pageFooterArr, Map<String,Object> pageFooterMap) throws DocumentException, IOException {  
		PageEvent event = new PageEvent(pageFooterArr,pageFooterMap);
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
	private PdfPTable generateTable(String[] columnArr,String[] titleArr,float[] widthArr,String[] columnAlignArr,
			String[] sumColumnArr, List<Map<String,Object>> dataList){
		if(titleArr==null || titleArr.length==0){
			return null;
		}
		//设置表格的列宽和列数 
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
			for(int i=0;i<titleArr.length;i++){
				String aTitle = titleArr[i];
				cell2 = getCell(aTitle, subBoldFontChinese, "center", 1,1);
				table.addCell(cell2);  

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
	 * 解析列参数，返回列英文名称数组、中文名称数组、列宽度数组、列对齐方式数组
	 * */
	private Map<String,Object> analyseColumnParamArr(String[] columParamArr){
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
	private int[] getSumColumnIndexArr(String[] columnArr ,String[] sumColumnArr){
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
	 * */
	private PdfPCell getCell(String str,Font font,String hAlign,int border,int wrap){
		PdfPCell cell = new PdfPCell(new Paragraph(str,font));//描述  
		if(wrap==0){
			cell.setFixedHeight(15);  
		}else{
			cell.setMinimumHeight(15);  
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
	private float calSize(float mm){
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
	public void exportComplicatedPdf(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
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
	public String exportComplicatedPdfWithPdfpath(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		
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
	private Map<String,Object> analyseFirstColumnArr(String[] firstColumArr){
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
	private PdfPTable generateTable_change(String[] columnArr,String[] titleArr,float[] widthArr,String[] columnAlignArr,
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
	public void exportPdfWithNewPage(Map<String,Object> allDataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
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
			this.setFooter(writer, rect, null, null);
			writer.setFullCompression();  
			writer.setPdfVersion(PdfWriter.VERSION_1_4);  
			
			document.open();  
			if(dataMapList!=null){
				for(int i=0;i<dataMapList.size();i++){
					Map<String,Object> dataMap = dataMapList.get(i);
					if(i!=0){
						//如果不是第一页，则开始新的一页
						document.newPage();
					}
					document = this.writePdf(dataMap, document);
				}
			}
		
			document.close();  
			
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
	private Document writePdf(Map<String,Object> dataMap,Document document){
		try {
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
			//合计列数组
			String[] sumColumnArr = (String[])dataMap.get("sumColumnArr");
			Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
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
			
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
			//粗体   
			Font titleChinese = new Font(bfChinese, 14, Font.BOLD);
			Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
			//抬头
			if(mainTitle!=null && !"".equals(mainTitle)){
				Paragraph mainTitleGraph = new Paragraph(mainTitle, titleChinese);  
				mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
				mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
				document.add(mainTitleGraph); 
			}
			
			PdfPCell cell = new PdfPCell();
			//副标题
			if(subTitleArr!=null && subTitleArr.length>0){
				PdfPTable subTable = new PdfPTable(6);
				subTable.setSpacingBefore(4f);// 设置表格上面空白宽度
				float[] widths = { 4f,9f,4f,8f,4f,6f};// 设置表格的列宽和列数  
				subTable.setWidths(widths);
				subTable.setTotalWidth(500);
				subTable.setWidthPercentage(100);//设置表格宽度为%100
				//table.setLockedWidth(true);
				
				int border = 0;
				for(int i=0;i<subTitleArr.length;i++){
					String subTitle = subTitleArr[i];
					if(subTitle!=null){
						String[] tmp = subTitle.split(":");
						cell = getCell(tmp[1]+"：", defaultFont,"right",border,0);
						subTable.addCell(cell);
						if(subTitleMap != null){
							cell = getCell(changeObjToStr(subTitleMap.get(tmp[0])),defaultFont,"left",border,0);
						}else{
							cell = getCell("",defaultFont,"left",border,0);
						}
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
	private PdfPTable generateTableNew(String[] columnArr,String[] titleArr,float[] widthArr,String[] columnAlignArr,
			String[] sumColumnArr, List<Map<String,Object>> dataList,
			String[] pageFooterArr,Map<String,Object> pageFooterMap, Document document){
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
			for(int i=0;i<titleArr.length;i++){
				String aTitle = titleArr[i];
				cell2 = getCell(aTitle, subBoldFontChinese, "center", 1,1);
				table.addCell(cell2);  

			}
			//表尾
			cell2 = getCell("合计", defaultFont, "center", 1,1);
			table.addCell(cell2);
			//添加空单元格
			for(int i=0;i<columnArr.length-1;i++){
				cell2 = getCell("", defaultFont, "left", 1,1);
				table.addCell(cell2);
			}
			table.setHeaderRows(2);
		    table.setFooterRows(1);
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
			//通过表格分页事件，计算合计值，在纸张底部绘制不同表格的页脚
			int[] sumColumnIndexArr = null;
			if(sumColumnArr!=null && sumColumnArr.length>0){
				//获取合计列在列数组中的索引位置
				sumColumnIndexArr = getSumColumnIndexArr(columnArr, sumColumnArr);
			}
			PageTableSplitEvent event = new PageTableSplitEvent(dataList, sumColumnArr, sumColumnIndexArr, pageFooterArr, pageFooterMap, document);
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
	private void addDottedLineSeparator(Document document){
		Paragraph p = new Paragraph(new Chunk(new DottedLineSeparator()));  
		try {
			p.setLeading(25f);
			document.add(p);
		} catch (DocumentException e) {
			e.printStackTrace();
		}  

	}
	public String exportPdfFor3WithPdfpath(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
		String dir=request.getSession().getServletContext().getRealPath("/")+"/exportfile/pdf/";
		File dirFile=new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdir();
		}
		//文件名称
		//String fileName = changeObjToStr(dataMap.get("fileName"));
		String fileName = Guid.get();
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
		//合计列数组
		String[] sumColumnArr = (String[])dataMap.get("sumColumnArr");
		Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
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

		// 1.创建 Document 对象
		Document document = new Document(this.rect,leftmargin,rightmargin,36,36);  
		File pdfpathFile=new File(pdfpath);
		if(!pdfpathFile.exists()){
			pdfpathFile.createNewFile();
		}
		// 2.创建书写器，通过书写器将文档写入流中
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(pdfpathFile));//response.getOutputStream());
		//this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
		writer.setFullCompression();  
		writer.setPdfVersion(PdfWriter.VERSION_1_4);  
		document.open();  
		
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
		//粗体   
		Font titleChinese = new Font(bfChinese, 14, Font.BOLD);
		//Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
		
		for(int count=0;count<3;count++){
			//抬头
			if(mainTitle!=null && !"".equals(mainTitle)){
				Paragraph mainTitleGraph = new Paragraph(mainTitle, titleChinese);  
				mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
				if(count>0){
					mainTitleGraph.setLeading(35f);//设置行间距//设置上面空白宽度  
				}else{
					
					mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
				}
				document.add(mainTitleGraph); 
			}
			
			//副标题
			this.addTable(document, subTitleArr, subTitleMap);
			//生成表格
	        PdfPTable table = generateTable(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, dataList);
	        if(table != null){
	        	document.add(table);
	        }
	        this.addFooterInfo(document, pageFooterArr, pageFooterMap);
	        if(count<2){
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
	public void exportPdfFor3(Map<String,Object> dataMap ,HttpServletRequest request, HttpServletResponse response) throws Exception{
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
		//合计列数组
		String[] sumColumnArr = (String[])dataMap.get("sumColumnArr");
		Map<String,Object> map = this.analyseColumnParamArr(columParamArr);
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
		//this.setFooter(writer, rect, pageFooterArr, pageFooterMap);
		writer.setFullCompression();  
		writer.setPdfVersion(PdfWriter.VERSION_1_4);  
		document.open();  
		
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED); // 中文处理  
		//粗体   
		Font titleChinese = new Font(bfChinese, 14, Font.BOLD);
		//Font defaultFont = new Font(bfChinese, 8, Font.NORMAL); 
		
		for(int count=0;count<3;count++){
			//抬头
			if(mainTitle!=null && !"".equals(mainTitle)){
				Paragraph mainTitleGraph = new Paragraph(mainTitle, titleChinese);  
				mainTitleGraph.setAlignment(Element.ALIGN_CENTER); // 居中设置  
				if(count>0){
					mainTitleGraph.setLeading(35f);//设置行间距//设置上面空白宽度  
				}else{
					
					mainTitleGraph.setLeading(1f);//设置行间距//设置上面空白宽度  
				}
				document.add(mainTitleGraph); 
			}
			
			//副标题
			this.addTable(document, subTitleArr, subTitleMap);
			//生成表格
	        PdfPTable table = generateTable(columnArr, titleArr, widthArr, columnAlignArr, sumColumnArr, dataList);
	        if(table != null){
	        	document.add(table);
	        }
	        this.addFooterInfo(document, pageFooterArr, pageFooterMap);
	        if(count<2){
	        	this.addDottedLineSeparator(document);
	        }
		}
		document.close();  
	}
	
	//该方法是备注单独占据一个段落，其它页脚信息以表格的形式一行3列均分
	private void addFooterInfo(Document document,String[] pageFooterArr,Map<String,Object> pageFooterMap) throws DocumentException{
		try {
			List<String> tempList=new ArrayList<String>();
			//Paragraph footerParagraph = new Paragraph();  
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
				
				if(key.equals("bz")||key.equals("memo")){//如果是备注信息或者退货原因，单独占用一个段落
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
/*	//该方法用来打印一行段落
	private void addFooterLine(Document document,String[] arr,Map<String,Object> dataMap) throws DocumentException{
		try {
		Paragraph footerParagraph = new Paragraph();  
		BaseFont bfChinese;
		Font footerTextFont;
		    float footertextsize = 8f;
			bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			footerTextFont = new Font(bfChinese, footertextsize, Font.NORMAL);
		for(int i=0;i<arr.length;i++){
			String item = arr[i];
			String key = item.split(":")[0];
			String name = item.split(":")[1];
			Chunk chunk = new Chunk(name+" :  "+String.valueOf(dataMap.get(key)),footerTextFont);
			footerParagraph.add(chunk);
			if(i<arr.length-1){
				footerParagraph.add(new Chunk("       "));
			}
		}
		footerParagraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(footerParagraph);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	//打印订单合计信息行
		private void addOrderHjLine(Document document,String[] arr,Map<String,Object> dataMap) throws DocumentException{
			try {
			Paragraph footerParagraph = new Paragraph(new Chunk("合计：       "));  
			BaseFont bfChinese;
			Font footerTextFont;
			    float footertextsize = 8f;
				bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
				footerTextFont = new Font(bfChinese, footertextsize, Font.NORMAL);
			for(int i=0;i<arr.length;i++){
				String item = arr[i];
				String key = item.split(":")[0];
				String name = item.split(":")[1];
				Chunk chunk = new Chunk(name+" :  "+String.valueOf(dataMap.get(key)),footerTextFont);
				footerParagraph.add(chunk);
				if(i<arr.length-1){
					footerParagraph.add(new Chunk("       "));
				}
			}
			footerParagraph.setAlignment(Element.ALIGN_RIGHT);
			document.add(footerParagraph);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}*/
}
