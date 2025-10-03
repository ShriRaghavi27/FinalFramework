package practice;

import java.io.IOException;

import genericutilities.ExcelFileUtility;

public class ReadMultipleDataFromExcel {
	public static void main(String[] args) throws IOException {
		ExcelFileUtility eLip=new ExcelFileUtility();
		int rowcount = eLip.getRowCount("Practice");
		for(int i=1;i<=rowcount;i++)
		{
			System.out.println("BrandName=> "+eLip.readDataFromExcelFile("Practice", i, 0));
			System.out.println("ProductName=> "+eLip.readDataFromExcelFile("Practice", i, 1));
			//System.out.println("BrandName=> "+sheet.getRow(i).getCell(0).getStringCellValue());
			//System.out.println("ProductName=> "+sheet.getRow(i).getCell(1).getStringCellValue());
		}
	}

}
