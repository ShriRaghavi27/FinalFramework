package genericutilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	public String readDataFromExcelFile(String sheetName,int rowNum,int cellNum) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/Ninza_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
		}
	public int getRowCount(String sheetName) throws IOException
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/Ninza_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;
		
	}

}
