package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities
{
	Workbook wb;
	 public ExcelUtilities() throws Exception{
		 							
		 FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\TestInout\\InputSheet.xlsx");
		 wb=new XSSFWorkbook(fi);
	 }
	 public int rowCount(String sheetname)
	 {
		 return wb.getSheet(sheetname).getLastRowNum();
	 }
	 
	 public int colCount(String sheetname){
		 return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	 }
	 public String getData(String sheetname, int row, int col){
		 String data;
		 if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType()==Cell.CELL_TYPE_NUMERIC){
			 int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
			 data=String.valueOf(celldata);
		 }else{
			 data=wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		 }
		 return data;
	 }
	 
	 public void setData(String sheetname, int row, int col, String status) throws Exception{
		 Sheet sh=wb.getSheet(sheetname);
		 Row rownum=sh.getRow(row);
		 Cell cellnum=rownum.createCell(col);
		 cellnum.setCellValue(status);
		 if(status.equalsIgnoreCase("Pass"))
		 {
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			
			rownum.getCell(col).setCellStyle(style);
			
		 }if(status.equalsIgnoreCase("Fail"))
		 {
			 CellStyle style=wb.createCellStyle();
			 Font font=wb.createFont();
			 font.setColor(IndexedColors.RED.getIndex());
			 font.setBold(true);
			 style.setFont(font); 
			 
			 rownum.getCell(col).setCellStyle(style);	
			 
		 }if(status.equalsIgnoreCase("Not Executed"))
		 {
			 CellStyle style=wb.createCellStyle();
			 Font font=wb.createFont();
			 font.setColor(IndexedColors.BLUE.getIndex());
			 font.setBold(true);
			 style.setFont(font);
			 rownum.getCell(col).setCellStyle(style);	 
		 }											
		 
		 FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"\\TestOutput\\InputSheet.xlsx");
		 wb.write(fos);
		 fos.close();
	 }
}
