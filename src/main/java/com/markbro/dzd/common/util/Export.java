package com.markbro.dzd.common.util;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public class Export {
	public static void exportExcel(List<Map<String, Object>> list, String title,
			String filename, HttpServletResponse response) {
		OutputStream outputstream = null;
		WritableWorkbook wwb = null;
		String[] titleary = (String[]) null;
		String[] titlename = (String[]) null;
		String[] columnid = (String[]) null;
		try {
			if (title.equals("")) {
				throw new Exception("filename不能为空！");
			}

			titleary = title.split(",");
			columnid = new String[titleary.length];
			titlename = new String[titleary.length];
			for (int j = 0; j < titleary.length; j++) {
				if ((titleary[j] != null) && (!titleary[j].equals(""))) {
					String curtitle = titleary[j];
					if (curtitle.indexOf(":") < 0) {
						throw new Exception("title格式不正确");
					}
					columnid[j] = curtitle.split(":")[0];
					titlename[j] = curtitle.split(":")[1];
				}

			}

			String filenameconvert = new String(filename.getBytes(),
					"iso-8859-1");
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ filenameconvert + ".xls");
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
					lable = new Label(i, index, String.valueOf(map.get(columnid[i])).replace("null", ""));
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

			if (wwb != null) {
				try {
					wwb.close();
					wwb = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (outputstream != null)
				try {
					outputstream.close();
					outputstream = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
					wwb = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (outputstream != null)
				try {
					outputstream.close();
					outputstream = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
}