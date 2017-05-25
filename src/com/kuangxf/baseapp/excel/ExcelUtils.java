package com.kuangxf.baseapp.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import android.content.Context;
import android.widget.Toast;

import com.kuangxf.baseapp.AppConfig;
import com.kuangxf.baseapp.utils.LogUtil;
import com.kuangxf.baseapp.utils.ToastUtils;

public class ExcelUtils {
	public static WritableFont arial14font = null;

	public static WritableCellFormat arial14format = null;
	public static WritableFont arial10font = null;
	public static WritableCellFormat arial10format = null;
	public static WritableFont arial12font = null;
	public static WritableCellFormat arial12format = null;

	public final static String UTF8_ENCODING = "UTF-8";
	public final static String GBK_ENCODING = "GBK";
	
	public final static int high = 500;
	public final static int weigh = 10;

	/**
	 * 单元格的格式设置 字体大小 颜色 对齐方式、背景颜色等...
	 */
	public static void format() {
		try {
			arial14font = new WritableFont(WritableFont.ARIAL, 14,
					WritableFont.BOLD);
			arial14font.setColour(jxl.format.Colour.LIGHT_BLUE);
			arial14format = new WritableCellFormat(arial14font);
			arial14format.setAlignment(jxl.format.Alignment.CENTRE);
			arial14format.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			arial14format.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);

			arial10font = new WritableFont(WritableFont.ARIAL, 10,
					WritableFont.BOLD);
			arial10format = new WritableCellFormat(arial10font);
			arial10format.setAlignment(jxl.format.Alignment.CENTRE);
			arial10format.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN);
			arial10format.setBackground(Colour.GRAY_25);

			arial12font = new WritableFont(WritableFont.ARIAL, 10);
			arial12format = new WritableCellFormat(arial12font);
			arial10format.setAlignment(jxl.format.Alignment.CENTRE);// 对齐格式
			arial12format.setBorder(jxl.format.Border.ALL,
					jxl.format.BorderLineStyle.THIN); // 设置边框

		} catch (WriteException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化Excel
	 * 
	 * @param fileName
	 * @param colName
	 */
	public static void initExcel(String fileName, String sheetName,
			String title, String... colName) {
		LogUtil.e("fileName=" + fileName);
		format();
		WritableWorkbook workbook = null;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			workbook = Workbook.createWorkbook(file);
			WritableSheet sheet = workbook.createSheet(sheetName, 0);
			// 创建标题栏
			sheet.addCell((WritableCell) new Label(0, 0, title, arial14format));
			for (int col = 0; col < colName.length; col++) {
				sheet.addCell(new Label(col, 0, colName[col], arial10format));
			}
			sheet.setRowView(0, high); // 设置行高
			sheet.mergeCells(0, 0, 4, 0); // 合并单元格

			workbook.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void writeReportExcel(ReportExcel re) {
		String fileName = AppConfig.getSDPath() + re.getFileName();
		LogUtil.e("fileName=" + fileName);
		initExcel(fileName, re.getSheetName(), re.getTitle());

		WritableWorkbook writebook = null;
		InputStream in = null;
		try {
			WorkbookSettings setEncode = new WorkbookSettings();
			setEncode.setEncoding(UTF8_ENCODING);
			in = new FileInputStream(new File(fileName));
			Workbook workbook = Workbook.getWorkbook(in);
			writebook = Workbook.createWorkbook(new File(fileName), workbook);
			WritableSheet sheet = writebook.getSheet(0);

			sheet.setColumnView(0, weigh);//设置列宽 第0列为10
			sheet.setColumnView(4, weigh * 4);//设置列宽 第4列为40
			
			sheet.setRowView(1, high); // 设置行高
			sheet.setRowView(2, high); // 设置行高
			sheet.setRowView(3, high); // 设置行高
			sheet.setRowView(4, high); // 设置行高
			sheet.setRowView(5, high); // 设置行高
			sheet.setRowView(6, high); // 设置行高
			sheet.setRowView(7, high); // 设置行高
			sheet.setRowView(8, high); // 设置行高
			sheet.setRowView(9, high); // 设置行高
			
			sheet.addCell(new Label(0, 1, "企业", arial12format));
			sheet.mergeCells(1, 1, 4, 1); // 合并单元格
			sheet.addCell(new Label(1, 1, "shanadaded", arial12format));
			
			sheet.addCell(new Label(0, 2, "样品名称", arial12format));
			sheet.mergeCells(1, 2, 4, 2); // 合并单元格 从第1列2行  合并到 第4列2行
			sheet.addCell(new Label(1, 2, "1234放得多", arial12format));
			
			sheet.addCell(new Label(0, 3, "操作人", arial12format));
			sheet.addCell(new Label(1, 3, "tom", arial12format));
			sheet.addCell(new Label(2, 3, "测试时间", arial12format));
			sheet.addCell(new Label(3, 3, "2016", arial12format));
			
			//初熔1
			sheet.addCell(new Label(0, 4, "初熔1", arial12format));
			sheet.addCell(new Label(1, 4, "201℃", arial12format));
			sheet.addCell(new Label(2, 4, "终熔1", arial12format));
			sheet.addCell(new Label(3, 4, "501℃", arial12format));
			
			//初熔2
			sheet.addCell(new Label(0, 5, "初熔2", arial12format));
			sheet.addCell(new Label(1, 5, "202℃", arial12format));
			sheet.addCell(new Label(2, 5, "终熔2", arial12format));
			sheet.addCell(new Label(3, 5, "502℃", arial12format));
			
			//初熔3
			sheet.addCell(new Label(0, 6, "初熔3", arial12format));
			sheet.addCell(new Label(1, 6, "203℃", arial12format));
			sheet.addCell(new Label(2, 6, "终熔3", arial12format));
			sheet.addCell(new Label(3, 6, "503℃", arial12format));
			
			//初熔4
			sheet.addCell(new Label(0, 7, "初熔4", arial12format));
			sheet.addCell(new Label(1, 7, "204℃", arial12format));
			sheet.addCell(new Label(2, 7, "终熔4", arial12format));
			sheet.addCell(new Label(3, 7, "504℃", arial12format));
			
			sheet.mergeCells(0, 8, 4, 8); // 合并单元格
			sheet.addCell(new Label(0, 8, "备注", arial12format));
			
			sheet.mergeCells(0, 9, 4, 14); // 合并单元格
			sheet.addCell(new Label(0, 9, " ", arial12format));

			// for (int j = 0; j < objList.size(); j++) {
			// ArrayList<String> list = (ArrayList<String>) objList.get(j);
			// for (int i = 0; i < list.size(); i++) {
			// sheet.addCell(new Label(i, j + 1, list.get(i),
			// arial12format));
			// if (list.get(i).length() <= 5) {
			// sheet.setColumnView(i, list.get(i).length() + 8); // 设置列宽
			// } else {
			// sheet.setColumnView(i, list.get(i).length() + 5); // 设置列宽
			// }
			// }
			// sheet.setRowView(j + 1, 350); // 设置行高
			// }

			// String f = AppConfig.getSDPath() + "/" + "jiuwei.png";
			// Log.e("getSDPath", "f="+f);
			// WritableImage wi = new WritableImage(25,1,10,10, new
			// File(f));
			// sheet.addImage(wi);

			writebook.write();
			ToastUtils.showToast("导出到手机存储中文件夹Record成功");
			// Toast.makeText(, "导出到手机存储中文件夹Record成功", Toast.LENGTH_SHORT)
			// .show();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writebook != null) {
				try {
					writebook.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> void writeObjListToExcel(List<T> objList,
			String fileName, Context c) {
		if (objList != null && objList.size() > 0) {
			WritableWorkbook writebook = null;
			InputStream in = null;
			try {
				WorkbookSettings setEncode = new WorkbookSettings();
				setEncode.setEncoding(UTF8_ENCODING);
				in = new FileInputStream(new File(fileName));
				Workbook workbook = Workbook.getWorkbook(in);
				writebook = Workbook.createWorkbook(new File(fileName),
						workbook);
				WritableSheet sheet = writebook.getSheet(0);

				// sheet.mergeCells(0,1,0,objList.size()); //合并单元格
				// sheet.mergeCells()

				for (int j = 0; j < objList.size(); j++) {
					ArrayList<String> list = (ArrayList<String>) objList.get(j);
					for (int i = 0; i < list.size(); i++) {
						sheet.addCell(new Label(i, j + 1, list.get(i),
								arial12format));
						if (list.get(i).length() <= 5) {
							sheet.setColumnView(i, list.get(i).length() + 8); // 设置列宽
						} else {
							sheet.setColumnView(i, list.get(i).length() + 5); // 设置列宽
						}
					}
					sheet.setRowView(j + 1, 350); // 设置行高
				}

				// String f = AppConfig.getSDPath() + "/" + "jiuwei.png";
				// Log.e("getSDPath", "f="+f);
				// WritableImage wi = new WritableImage(25,1,10,10, new
				// File(f));
				// sheet.addImage(wi);

				writebook.write();
				Toast.makeText(c, "导出到手机存储中文件夹Record成功", Toast.LENGTH_SHORT)
						.show();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (writebook != null) {
					try {
						writebook.close();
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
}
