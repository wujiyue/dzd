package com.markbro.dzd.common.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class PageEventConfig extends PdfPageEventHelper {
	
	private String[] title;
	private Map<String,Object> pagedata;
	private PdfTemplate total;
	protected BaseFont helv;
	private Font footerTextFont = null;
	private float footertextsize = 9;
	
	public PageEventConfig(String[] title,Map<String,Object> pagedata, float footertextsize, Font footerTextFont){
		this.title = title;
		this.pagedata = pagedata;
		this.footertextsize = footertextsize;
		this.footerTextFont = footerTextFont; 
		try {
			helv = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", true);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void onOpenDocument(PdfWriter writer,Document document){
//        total = writer.getDirectContent().createTemplate(30,16);
		total = writer.getDirectContent().createTemplate(100, 100);
		total.setBoundingBox(new Rectangle(-20, -20, 100, 100));
		
    }
	
	public void onStartPage(PdfWriter writer, Document document){
		
	}
	
	public void onEndPage (PdfWriter writer, Document document) {
		try {
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
			
			
			
			String text = String.format("第"+writer.getPageNumber()+"页，共");
			cell = new PdfPCell();
			Paragraph para = new Paragraph(text, footerTextFont);
			cell.addElement(para);
//		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setBorder(0);
			_table.addCell(cell);
			
			_table.writeSelectedRows(0, -1,
					document.leftMargin(), document.bottom() - 10 + 5, cb);
			
			//计算total页的位置
			float cellwidth = (document.right()-document.left())/100*headerwidths[headerwidths.length-1];
			float textwidth = helv.getWidthPoint(text,footertextsize);
			float x = document.right()-cellwidth+textwidth+2;
			float y = document.bottom() - 10 + 5 - footertextsize - 6;
			cb.addTemplate(total, x,y);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public void onCloseDocument(PdfWriter writer,Document document){
//       ColumnText.showTextAligned(total,Element.ALIGN_LEFT,new Phrase(String.valueOf(writer.getPageNumber()-1)),2,2,0);
		total.beginText();
		total.setFontAndSize(helv, footertextsize);
		total.setTextMatrix(0, 0);
		total.showText(String.valueOf(writer.getPageNumber() - 1)+"页");
		total.endText();
    }
	
	public float getSumPageOffset(){
		float offset = 0;
		BigDecimal b = new BigDecimal(footertextsize);
		if(b.compareTo(new BigDecimal(8))==0){
			offset = 0;
		}else if(b.compareTo(new BigDecimal(9))==0){
			offset = 0;
		}else if(b.compareTo(new BigDecimal(10))==0){
			offset = 1;
		}
		return offset;
	}
}
