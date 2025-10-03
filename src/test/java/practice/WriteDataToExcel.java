package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDataToExcel {

	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\Manish\\OneDrive\\Documents\\Ninza_Data.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Campaign");
		Row r = sh.getRow(1);
		
		Cell c = r.createCell(4);
		c.setCellType(CellType.STRING);
		c.setCellValue("Shri");
		
		FileOutputStream fos=new FileOutputStream("C:\\Users\\Manish\\OneDrive\\Documents\\Ninza_Data.xlsx");
		wb.write(fos);
		
		wb.close();
	}

}
