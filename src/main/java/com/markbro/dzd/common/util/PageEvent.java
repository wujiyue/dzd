package com.markbro.dzd.common.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.util.Map;

public class PageEvent extends PdfPageEventHelper {
	
	String[] title;
	Map<String,Object> pagedata;
	protected PdfTemplate total;
	protected BaseFont helv;
	Font font;
	int fontsize=8;
	
	public PageEvent(String[] title,Map<String,Object> pagedata){
		this.title = title;
		this.pagedata = pagedata;
	}
	
	public void onOpenDocument(PdfWriter writer,Document document){
//        total = writer.getDirectContent().createTemplate(30,16);
		total = writer.getDirectContent().createTemplate(100, 100);
		total.setBoundingBox(new Rectangle(-20, -20, 100, 100));
		try {
			helv = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", true);
			font = new Font(helv,fontsize);
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
    }
	
	public void onStartPage(PdfWriter writer, Document document){
		
	}
	
	public void onEndPage (PdfWriter writer, Document document) {
		PdfContentByte cb = writer.getDirectContent();
		
		
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
				
				cell = new PdfPCell(new Paragraph(name+"：", font));
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setBorder(0);
				_table_ctx.addCell(cell);
				cell = new PdfPCell(new Paragraph(String.valueOf(pagedata.get(key)), font));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setBorder(0);
				_table_ctx.addCell(cell);
			}
			cell.addElement(_table_ctx);
		}
		cell.setBorder(0);
		_table.addCell(cell);
		
		
		
		String text = String.format("第"+writer.getPageNumber()+"页，共");
		cell = new PdfPCell();
		Paragraph para = new Paragraph(text, font);
		cell.addElement(para);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setBorder(0);
		_table.addCell(cell);
		
		float offsetHeight = 5;
		
		_table.writeSelectedRows(0, -1,
				document.leftMargin(), document.bottom() - 10 +offsetHeight, cb);
		
		//计算total页的位置 根据footer不同  写自己的逻辑
		//footer是table 计算公式如下 
		//ps 底边距+修订值
		float cellwidth = (document.right()-document.left())/100*headerwidths[headerwidths.length-1];
		float textwidth = helv.getWidthPoint(text,fontsize);
//		float x = document.left()+document.right()-document.left()-cellwidth/2+textwidth/2;
		float x = document.right()-cellwidth+textwidth+2;
		float y = document.bottom() - 10-fontsize-2-4+offsetHeight;
		cb.addTemplate(total, x,y);
			
			
		
		
    }
	
	public void onCloseDocument(PdfWriter writer,Document document){
//       ColumnText.showTextAligned(total,Element.ALIGN_LEFT,new Phrase(String.valueOf(writer.getPageNumber()-1)),2,2,0);
		total.beginText();
		total.setFontAndSize(helv, fontsize);
		total.setTextMatrix(0, 0);
		total.showText(String.valueOf(writer.getPageNumber() - 1)+"页");
		total.endText();
    }
}
