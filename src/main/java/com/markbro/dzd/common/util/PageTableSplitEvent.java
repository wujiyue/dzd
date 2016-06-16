package com.markbro.dzd.common.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class PageTableSplitEvent implements PdfPTableEventSplit {
	/**已显示数据行数*/
	public int splitedRows = 0;
	/**数据*/
	public List datalist;
	public Font defaultFont; 
	/**合计列名称数组*/
	public String[] sumColumnArr;
	/**合计列对应的列索引*/
	public int[] sumColumnIndexArr;
	/**页脚数组*/
	public String[] pageFooterArr;
	/**页脚数据*/
	public Map<String,Object> pageFooterMap;
	public Document document;
	
	
	public PageTableSplitEvent(List list,String[] sumColumnArr,int[] sumColumnIndexArr, String[] pageFooterArr, 
			Map<String,Object> pageFooterMap, Document document){
		this.datalist=list;
		this.sumColumnArr = sumColumnArr;
		this.sumColumnIndexArr = sumColumnIndexArr;
		
		this.pageFooterArr = pageFooterArr;
		this.pageFooterMap = pageFooterMap;
		this.document = document;
		try {
			BaseFont helv = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", true);
			defaultFont = new Font(helv,8);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionConverter(e);
		}
	}

	public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
		try {
			PdfContentByte cb = canvases[canvases.length-1];
			//添加合计值
			if(sumColumnArr!=null && sumColumnArr.length>0){
				int rows_len = table.getLastCompletedRowIndex()-table.getFooterRows();
				//计算合计值的位置
				int hei = widths.length;
				//合计单元格的纵坐标
				float y = heights[hei-1];
				if(datalist!=null){
					List subList = datalist.subList(splitedRows, splitedRows+rows_len);
					for(int i=0;i<sumColumnArr.length;i++){
						//float width = widths[0][len-1]-widths[0][len-2];
						//float x = widths[0][len-2];
						float width = widths[0][sumColumnIndexArr[i]+1]-widths[0][sumColumnIndexArr[i]];
						//合计单元格的横坐标
						float x = widths[0][sumColumnIndexArr[i]];
						String columnName = sumColumnArr[i];
						createSumTable(cb, width, x, y, subList, columnName);
					}
				}
				splitedRows+=rows_len;
			}
			//添加页脚
			if(pageFooterArr!=null && pageFooterArr.length>0){
				addFooter(cb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**填写合计单元格的值*/
	public void createSumTable(PdfContentByte cb, float width, float x , float y, List subList,String columnName){
		//合计值
		BigDecimal sumTotal = new BigDecimal(0);
		for(int i=0;i<subList.size();i++){
			Map row = (Map)subList.get(i);
			sumTotal = sumTotal.add(new BigDecimal(changeStr(row.get(columnName))));
		}
		PdfPTable temp_table = new PdfPTable(1);
		temp_table.setTotalWidth(width);
		PdfPCell cell = new PdfPCell(new Paragraph(sumTotal.toString(), defaultFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		temp_table.addCell(cell);
		temp_table.writeSelectedRows(0, -1, x, y, cb);
	}
	
	
	/**添加页脚*/
	public void addFooter(PdfContentByte cb){
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
		if(pageFooterArr!=null&&pageFooterArr.length>0){
			//页脚
			PdfPTable _table_ctx = new PdfPTable(pageFooterArr.length*2);
			
			_table_ctx.setWidthPercentage(100);
			_table_ctx.setTotalWidth((document.right()-document.left())/100*headerwidths[headerwidths.length-1]);
			
			float[] _table_ctx_widths = new float[pageFooterArr.length*2];
			for(int i=0;i<pageFooterArr.length;i++){
				String item = pageFooterArr[i];
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
			
			for(int i=0;i<pageFooterArr.length;i++){
				String item = pageFooterArr[i];
				String key = item.split(":")[0];
				String name = item.split(":")[1];
				
				cell = new PdfPCell(new Paragraph(name+"：", defaultFont));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setBorder(0);
				_table_ctx.addCell(cell);
				//获取值
				Object value = pageFooterMap.get(key);
				if(value==null||"null".equals(value)){
					value = "";
				}
				cell = new PdfPCell(new Paragraph(String.valueOf(value), defaultFont));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(0);
				_table_ctx.addCell(cell);
			}
			cell.addElement(_table_ctx);
		}
		cell.setBorder(0);
		_table.addCell(cell);
		
		String text = String.format("");
		cell = new PdfPCell();
		Paragraph para = new Paragraph(text, defaultFont);
		cell.addElement(para);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(0);
		_table.addCell(cell);
		float offsetHeight = 5;
		
		_table.writeSelectedRows(0, -1,
				document.leftMargin(), document.bottom() - 10-1+offsetHeight, cb);
	}

	public void splitTable(PdfPTable table) {
		
	}
	public static String changeStr(Object obj){
		return obj==null||"".equals(String.valueOf(obj).trim())||"null".equals(obj)?"0":String.valueOf(obj).trim();
	}

}
