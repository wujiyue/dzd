package com.markbro.dzd.common.util;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@SuppressWarnings("rawtypes")
public class TableSplitEventConfig implements PdfPTableEventSplit {
	/**已显示数据行数*/
	int splitedRows = 0;
	
	/**数据*/
	List datalist;
	BaseFont helv;
	//Font defaultFont; 
	String columnname;
	/**合计列名称*/
	String[] sumColumnArr;
	/**合计列对应的列索引*/
	int[] sumColumnIndexArr;
	/**合计值字体样式*/
	private Font bodyTextFont = null;
	
	public TableSplitEventConfig(List list,String[] sumColumnArr,int[] sumColumnIndexArr, Font bodyTextFont){
		this.datalist=list;
		this.sumColumnArr = sumColumnArr;
		this.sumColumnIndexArr = sumColumnIndexArr;
		this.bodyTextFont = bodyTextFont;
	}

	public void tableLayout(PdfPTable table, float[][] widths, float[] heights, int headerRows, int rowStart, PdfContentByte[] canvases) {
		try {
			int rows_len = table.getLastCompletedRowIndex()-table.getFooterRows();
			PdfContentByte cb = canvases[canvases.length-1];
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
		PdfPCell cell = new PdfPCell(new Paragraph(sumTotal.toString(), bodyTextFont));
		cell.setBorder(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		temp_table.addCell(cell);
		temp_table.writeSelectedRows(0, -1, x, y, cb);
	}

	public void splitTable(PdfPTable table) {
		
	}
	public static String changeStr(Object obj){
		return obj==null||"".equals(String.valueOf(obj).trim())||"null".equals(obj)?"0":String.valueOf(obj).trim();
	}

}
