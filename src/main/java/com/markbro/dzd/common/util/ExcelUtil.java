package com.markbro.dzd.common.util;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.*;
import jxl.format.VerticalAlignment;
import jxl.write.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;

/**
 * Excel导出
 * @author mywhile
 *
 */
public class ExcelUtil {
	
	/**
	 * 单表导出
	 * 
	 * 创建时间  2014-4-15 上午9:28:22 
	 * 创建人 yangsl
	 * @param list :数据对象
	 * @param title ：表格中要显示的内容{"title:标题","content:内容","time:日期","bz:备注"}
	 * @param filename  Excel的标题
	 * @param response 
	 *
	 */
	public static void exportExcel(List<Map<String, Object>> list, String[] title, String filename, HttpServletResponse response) {
		OutputStream outputstream = null;
		WritableWorkbook wwb = null;
		try {
			if (title.equals("")) {
				throw new Exception("filename不能为空！");
			}
			String[] columnid = new String[title.length];
			String[] titlename = new String[title.length];
			for (int j = 0; j < title.length; j++) {
				title[j] = PatternUtil.isNull(title[j]);
				if (!title[j].equals("")) {
					String curtitle = title[j];
					if (curtitle.indexOf(":") < 0) {
						throw new Exception("title格式不正确");
					}
					columnid[j] = curtitle.split(":")[0];
					titlename[j] = curtitle.split(":")[1];
				}

			}

			String filenameconvert = new String(filename.getBytes(), "iso-8859-1");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + filenameconvert + ".xls");
			outputstream = response.getOutputStream();
			wwb = Workbook.createWorkbook(outputstream);
			WritableSheet sheet = wwb.createSheet(filename, 0);
			
			int index = 0;
			Label lable;
			for (int j = 0; j < titlename.length; j++) {
				lable = new Label(j, index, titlename[j]);
				sheet.addCell(lable);
			}
			index++;
			for (Map<String, Object> map : list) {
				for (int i = 0; i < columnid.length; i++) {
					String value = String.valueOf(map.get(columnid[i]));
					value = PatternUtil.isNull(value);
					lable = new Label(i, index, value);
					sheet.addCell(lable);
				}
				index++;
			}

			wwb.write();
			wwb.close();
			outputstream.close();
			wwb = null;
			outputstream = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
					wwb = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (outputstream != null){
				try {
					outputstream.close();
					outputstream = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 单表导出
	 * 
	 * 创建时间  2014-4-15 上午9:28:22 
	 * 创建人 yangsl
	 * @param list :数据对象
	 * @param title ：表格中要显示的内容{"title:标题","content:内容","time:日期","bz:备注"}
	 * @param filename  Excel的标题
	 * @param response 
	 *
	 */
	public static void exportExcelWithSimpleFormat(List<Map<String, Object>> list, String[] title, String filename, HttpServletResponse response) {
		OutputStream outputstream = null;
		WritableWorkbook wwb = null;
		try {
			if (title.equals("")) {
				throw new Exception("filename不能为空！");
			}
			String[] columnid = new String[title.length];
			String[] titlename = new String[title.length];
			for (int j = 0; j < title.length; j++) {
				title[j] = PatternUtil.isNull(title[j]);
				if (!title[j].equals("")) {
					String curtitle = title[j];
					if (curtitle.indexOf(":") < 0) {
						throw new Exception("title格式不正确");
					}
					columnid[j] = curtitle.split(":")[0];
					titlename[j] = curtitle.split(":")[1];
				}

			}

			String filenameconvert = new String(filename.getBytes(), "iso-8859-1");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + filenameconvert + ".xls");
			outputstream = response.getOutputStream();
			wwb = Workbook.createWorkbook(outputstream);
			WritableSheet sheet = wwb.createSheet(filename, 0);
			
			int index = 0;
			Label lable;
			//表头
			WritableFont wfc = new WritableFont(WritableFont.createFont("宋体"),
					12, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wcfHeader = new WritableCellFormat(wfc);
			wcfHeader.setBorder(Border.ALL , BorderLineStyle.THIN);
			wcfHeader.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfHeader.setAlignment(Alignment.CENTRE);
			wcfHeader.setBackground(Colour.ICE_BLUE);
			
			
			//红色
			WritableFont wfc_Red = new WritableFont(WritableFont.createFont("宋体"),
					12, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat wcfCont_Red = new WritableCellFormat(wfc_Red);
			wcfCont_Red.setBorder(Border.ALL , BorderLineStyle.THIN);
			wcfCont_Red.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcfCont_Red.setAlignment(Alignment.CENTRE);
			int columnBestWidth[]=new  int[titlename.length];    
			for (int j = 0; j < titlename.length; j++) {
				//sheet.setColumnView(j, 30);
				jxl.write.Label lTitle = new jxl.write.Label(j, index, titlename[j], wcfHeader);

				int width=titlename[j].length()+getChineseNum(titlename[j]);    ///汉字占2个单位长度
				if(columnBestWidth[j]<width) {   ///求取到目前为止的最佳列宽
                     columnBestWidth[j]=width;
				}
				sheet.addCell(lTitle);
			}
			index++;
			for (Map<String, Object> map : list) {
				for (int i = 0; i < columnid.length; i++) {
					String value = String.valueOf(map.get(columnid[i]));
					value = PatternUtil.isNull(value);
					lable = new Label(i, index, value,wcfCont_Red);
					sheet.addCell(lable);
					int width=value.length()+getChineseNum(value);    ///汉字占2个单位长度
					if(columnBestWidth[i]<width) {   ///求取到目前为止的最佳列宽
	                     columnBestWidth[i]=width;
					}


				}
				index++;
			}

			for (int j = 0; j < titlename.length; j++) {
				sheet.setColumnView(j, columnBestWidth[j]);

			}

			wwb.write();
			wwb.close();
			outputstream.close();
			wwb = null;
			outputstream = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
					wwb = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (outputstream != null){
				try {
					outputstream.close();
					outputstream = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static int getChineseNum(String context){    ///统计context中是汉字的个数
        int lenOfChinese=0;
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");    //汉字的Unicode编码范围
        Matcher m = p.matcher(context);
        while(m.find()){
            lenOfChinese++;
        }
        return lenOfChinese+10;
    }


	/**
	 * 主
	 * 创建时间  2014-4-15 上午9:38:18
	 * 创建人 yangsl
	 * @param list	源数据
	 * @param sonKey	子表数据的Key值
	 * @param title		主表的内容{"title:标题","content:内容","time:日期","bz:备注"}
	 * @param sonTitle	子表的内容{"jsr:接收人","jszt接收状态","ydzt阅读状态","bz:备注"}
	 * @param filename	文件名
	 * @param response
	 *
	 */
	@SuppressWarnings("unchecked")
	public static void exportExcelForZmx(List<Map<String, Object>> list, String sonKey, String[] title,
			String[] sonTitle, String filename, HttpServletResponse response) {
		OutputStream outputstream = null;
		jxl.write.WritableWorkbook wwb = null;
		String[] pTitleNam = null, sTitleNam = null;
		String[] pColumnId = null, sColumnId = null;
		try {
			if (title.equals("") || sonTitle.equals("")) {
				throw new Exception("filename不能为空！");
			}
			pColumnId = new String[title.length];
			pTitleNam = new String[title.length];
			for (int j = 0; j < title.length; j++) {
				title[j] = PatternUtil.isNull(title[j]);
				if (!title[j].equals("")){
					String curtitle = title[j];
					if (curtitle.indexOf(":") < 0) {
						throw new Exception("title格式不正确");
					}
					pColumnId[j] = curtitle.split(":")[0];
					pTitleNam[j] = curtitle.split(":")[1];
				}
			}
			sColumnId = new String[sonTitle.length];
			sTitleNam = new String[sonTitle.length];
			for (int j = 0; j < sonTitle.length; j++) {
				sonTitle[j] = PatternUtil.isNull(sonTitle[j]);
				if (!sonTitle[j].equals("")) {
					String curtitle = sonTitle[j];
					if (curtitle.indexOf(":") < 0) {
						throw new Exception("明细表title格式不正确");
					}
					sColumnId[j] = curtitle.split(":")[0];
					sTitleNam[j] = curtitle.split(":")[1];
				}
			}
			String filenameconvert = new String(filename.getBytes(), "iso-8859-1");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + filenameconvert + ".xls");
			outputstream = response.getOutputStream();
			wwb = jxl.Workbook.createWorkbook(outputstream);
			jxl.write.WritableSheet sheet = wwb.createSheet(filename, 0);

			WritableCellFormat wc = new WritableCellFormat();
			wc.setAlignment(Alignment.CENTRE); // 设置居中
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
			wc.setBackground(jxl.format.Colour.BLACK); //

			int index = 0;
			for (int j = 0; j < pTitleNam.length; j++) {
				jxl.write.Label lable = new jxl.write.Label(j, index, pTitleNam[j], wc);
				sheet.addCell(lable);
			}
			index = index + 1;
			for (int j = 0;j<list.size();j++) {
				Map<String, Object> map = list.get(j);
				for (int i = 0; i < pColumnId.length; i++) {
					String tmp = map.get(pColumnId[i]) != null ? map.get(pColumnId[i]).toString() : "";
					jxl.write.Label lable = new jxl.write.Label(i, index, tmp, wc);
					sheet.addCell(lable);
				}
				index++;
				for (int m = 0; m < sTitleNam.length; m++) {
					jxl.write.Label lable = new jxl.write.Label(m+1, index, sTitleNam[m], wc);
					sheet.addCell(lable);
				}
				index++;
				if(map.get(sonKey) != null){
					List<Map<String, Object>> son_list = (List<Map<String, Object>>) map.get(sonKey);
					for(int k = 0;k<son_list.size();k++){
						Map<String, Object> map_son = son_list.get(k);
						for (int i = 0; i < sColumnId.length; i++) {
							String tmp = map_son.get(sColumnId[i]) != null ? map_son.get(sColumnId[i]).toString() : "";
							jxl.write.Label lable = new jxl.write.Label(i+1, index, tmp, wc);
							sheet.addCell(lable);
						}
						index++;
					}
					//sheet.mergeCells(0, index-(son_list.size() + 1), 0, index-1);
				}
			}
			// 写入数据
			wwb.write();
			wwb.close();
			outputstream.close();
			wwb = null;
			outputstream = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
					wwb = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (outputstream != null) {
				try {
					outputstream.close();
					outputstream = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 单据式打印：无样式
	 *
	 * 创建时间  2014-4-21 下午2:38:28
	 * 创建人 yangsl
	 * @param data
	 * @param sonKey
	 * @param title
	 * @param sonTitle
	 * @param filename
	 * @param response
	 *
	 */
	@SuppressWarnings("unchecked")
	public static void exportExcelForBill(Map<String, Object> data, String sonKey, String[][] title,
			String[] sonTitle, String filename, HttpServletResponse response) {
		OutputStream outputstream = null;
		jxl.write.WritableWorkbook wwb = null;

		try {
			String filenameconvert = new String(filename.getBytes(), "iso-8859-1");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=" + filenameconvert + ".xls");
			outputstream = response.getOutputStream();
			wwb = jxl.Workbook.createWorkbook(outputstream);
			jxl.write.WritableSheet sheet = wwb.createSheet(filename, 0);

			//大标题样式
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);		//// 定义格式 字体 下划线 斜体 粗体 颜色
			WritableCellFormat wc = new WritableCellFormat(wf);
			wc.setAlignment(Alignment.CENTRE); // 设置居中
			wc.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
			wc.setBackground(jxl.format.Colour.BLACK); //

			//主单标题样式
			WritableFont wf_main = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
			WritableCellFormat wc_main = new WritableCellFormat(wf_main);
			wc_main.setAlignment(Alignment.RIGHT); // 设置居中
			wc_main.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
			wc_main.setBackground(jxl.format.Colour.BLACK); //

			//主单数据样式
			WritableCellFormat wc_normal_main = new WritableCellFormat();
			wc_normal_main.setAlignment(Alignment.LEFT); // 设置居中
			wc_normal_main.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
			wc_normal_main.setBackground(jxl.format.Colour.BLACK); //


			//子单标题样式
			WritableFont wf_mx = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
			WritableCellFormat wc_mx = new WritableCellFormat(wf_mx);
			wc_mx.setAlignment(Alignment.CENTRE); // 设置居中
			wc_mx.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
			wc_mx.setBackground(jxl.format.Colour.GREY_25_PERCENT); //背景灰色


			//子单数据样式
			WritableCellFormat wc_normal_mx = new WritableCellFormat();
			wc_normal_mx.setAlignment(Alignment.CENTRE); // 设置居中
			wc_normal_mx.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
			wc_normal_mx.setBackground(jxl.format.Colour.BLACK); //

			//隔行换色样式
			WritableCellFormat wc_normal_exch = new WritableCellFormat();
			wc_normal_exch.setAlignment(Alignment.CENTRE); // 设置居中
			wc_normal_exch.setBorder(Border.ALL, BorderLineStyle.THIN); // 设置边框线
			wc_normal_exch.setBackground(jxl.format.Colour.ICE_BLUE); //

			//写标题
			int index = 0;
			//sheet.setRowView(0, 20,false);  //设置行高度
			sheet.mergeCells(0,  0, sonTitle.length-1, 0);//合并单元格
			jxl.write.Label lable = new jxl.write.Label(0, index, filename, wc);
			sheet.addCell(lable);

			for(int i=0;i<title.length;i++){
				index++;
				for(int j=0;j<title[i].length;j++){
					String curtitle = title[i][j];
					if (curtitle.indexOf(":") < 0) {
						throw new Exception("明细表title格式不正确");
					}
					String pColumnId = title[i][j].split(":")[0];
					String pTitleNam = title[i][j].split(":")[1];
					if(j%2==0){
						lable = new jxl.write.Label(0, index, pTitleNam, wc_main);
						sheet.addCell(lable);
						String val = String.valueOf(data.get(pColumnId));
						val = PatternUtil.isNull(val);
						sheet.mergeCells(1,  index, (sonTitle.length-1)/2, index);//合并单元格 参数格式（开始列，开始行，结束列，结束行）
						lable = new jxl.write.Label(1, index, val, wc_normal_main);
						sheet.addCell(lable);
					}else{
						lable = new jxl.write.Label((sonTitle.length-1)/2+1, index, pTitleNam, wc_main);
						sheet.addCell(lable);
						String val = String.valueOf(data.get(pColumnId));
						val = PatternUtil.isNull(val);
						sheet.mergeCells((sonTitle.length-1)/2+2,  index, sonTitle.length-1, index);//合并单元格 参数格式（开始列，开始行，结束列，结束行）
						lable = new jxl.write.Label((sonTitle.length-1)/2+2, index, val, wc_normal_main);
						sheet.addCell(lable);
					}


				}
			}

			String[] sColumnId = new String[sonTitle.length];
			String[] sTitleNam = new String[sonTitle.length];
			for (int j = 0; j < sonTitle.length; j++) {
				sonTitle[j] = PatternUtil.isNull(sonTitle[j]);
				if (!sonTitle[j].equals("")) {
					String curtitle = sonTitle[j];
					if (curtitle.indexOf(":") < 0) {
						throw new Exception("明细表title格式不正确");
					}
					sColumnId[j] = curtitle.split(":")[0];
					sTitleNam[j] = curtitle.split(":")[1];
				}
			}

			index = index + 1;
			for (int m = 0; m < sTitleNam.length; m++) {
				if(sTitleNam[m].equals("物料名称") || sTitleNam[m].equals("备注") || sTitleNam[m].equals("订单号")|| sTitleNam[m].equals("退货原因") ){
					sheet.setColumnView(m, 23); //设置列宽
				}else{
					sheet.setColumnView(m, 13); //设置列宽
				}

				lable = new jxl.write.Label(m, index, sTitleNam[m], wc_mx);
				sheet.addCell(lable);
			}
			index++;
			if(data.get(sonKey) != null){
				List<Map<String, Object>> son_list = (List<Map<String, Object>>) data.get(sonKey);
				for(int k = 0;k<son_list.size();k++){
					Map<String, Object> map_son = son_list.get(k);
					for (int i = 0; i < sColumnId.length; i++) {
						String tmp = String.valueOf(map_son.get(sColumnId[i]));
						tmp = PatternUtil.isNull(tmp);
						if (index % 2 == 0){
							lable = new jxl.write.Label(i, index, tmp, wc_normal_mx);
						}else{
							lable = new jxl.write.Label(i, index, tmp, wc_normal_exch);
						}
						
						sheet.addCell(lable);
					}
					index++;
				}
			}
			// 写入数据
			wwb.write();
			wwb.close();
			outputstream.close();
			wwb = null;
			outputstream = null;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
					wwb = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (outputstream != null) {
				try {
					outputstream.close();
					outputstream = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
