package utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilitiesClass {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public ExcelUtilitiesClass(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		int rowcount = sheet.getLastRowNum();

		workbook.close();
		fi.close();

		return rowcount;

	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();

		workbook.close();
		fi.close();

		return cellcount;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;

		try {
			data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String regardless of
													// the cell type
		} catch (Exception e) {
			data = "";
		}

		workbook.close();
		fi.close();

		return data;
	}

	
	
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException {
		//File xlfile = new File(path);
		XSSFWorkbook workbook;
		FileOutputStream fo;
		FileInputStream fi;
   
		File xlfile = new File(path);
		// 1. If file doesn't exist, create it
		if (!xlfile.exists()) {
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
			fo.close(); // Close stream after creating
		}

		// 2. Open the file
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);

		// 3. Create sheet if it doesn't exist
		if (workbook.getSheetIndex(sheetName) == -1) {
			workbook.createSheet(sheetName);
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);

		// 4. Create row if it doesn't exist
		if (sheet.getRow(rownum) == null) {
			sheet.createRow(rownum);
		}
		XSSFRow row = sheet.getRow(rownum);

		// 5. Create cell and set value
		XSSFCell cell = row.createCell(colnum);
		cell.setCellValue(data);

		// 6. Save and close everything
		fo = new FileOutputStream(path);
		workbook.write(fo);

		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);

		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);

		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

}
