package excelRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


//Set 1 Commit

public class ExcelReader {

	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static  File file;
	static FileInputStream fs;
	static DataFormatter df;
	public static String[][] arrayExcelData=null;
	public static ArrayList<String> array = new ArrayList<String>();
	public static String[][] getarrayExcelData(String path, String sheetname,String field,String data) throws IOException
	{
		
		
		 filehandling(path,sheetname);
	     XSSFRow row=sheet.getRow(0);      
	     int totalNoOfCols =row.getLastCellNum();        	   
	     int arrayrow=getrow(field);
	     arrayExcelData = new String[arrayrow][2];
	     
	     for(int i=1;i<=arrayrow;i++)
	     {
	         int l=0; 
	    	 for (int j=0;j<totalNoOfCols;j++)
	    	 {	   	    		 	    		 
	    			if(sheet.getRow(0).getCell(j).getRichStringCellValue().toString().equals(field) || sheet.getRow(0).getCell(j).getRichStringCellValue().toString().equals(data) )
	    			{
	    		    	if((df.formatCellValue(sheet.getRow(i).getCell(j)).toString().trim())!=null)
	    		    	{
	    		    		if(j<arrayrow-1)
	    		    			{	    		    			
	    		    				arrayExcelData[i-1][l]=df.formatCellValue(sheet.getRow(i).getCell(j)).toString().trim();
	    		    				l=l+1;
	    		    			}
	    		    		else
	    		    			{  			    		    
	    		    				arrayExcelData[i-1][l]=df.formatCellValue(sheet.getRow(i).getCell(j)).toString().trim();
	    		    				l=l+1;
	    		    			}
	    		    	}
	    			}
	    			
	    		 }
	    		     		
	    	 }	    	
	    	return (arrayExcelData); 
	  }
	
	
	
	public static int getrow(String field)
	  	{  
		  
			int r=0;
			for(int i=1;i<=sheet.getLastRowNum();i++)
				{
					for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
						{
							if(sheet.getRow(0).getCell(j).toString().equals(field))
								{	   			
									try
										{
											sheet.getRow(i).getCell(j).toString();										
											r++;
										}
	   			 
									catch(Exception e)
									{
										break;
									}	   			
								}	   		 	   		 
						}	   	
				}
			return r;    
	  	} 
	
	public static ArrayList<String> getExcelData(String path, String sheetname,String field) throws IOException
	{
		filehandling(path,sheetname);
		  
			     for(int i=1;i<=sheet.getLastRowNum();i++)
			     {
			    	 for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			    	 {
			    		 if(sheet.getRow(0).getCell(j).toString().equals(field))			    		 {
			    			
			    			try
			    			{
			    			array.add(sheet.getRow(i).getCell(j).toString());
			    				 
			    			 }
			    			 
			    			catch(Exception e)
			    			{
			    				break;
			    			}
			    			
			    		 }
			    		 
			    		 
			    	 }
			    	
			     }
			
		     
		
		
		return array;
		
	}
	
	public static void filehandling(String path, String sheetname) throws IOException
	{
		 file=new File(path);
		 fs =new FileInputStream(file);
		 wb = new XSSFWorkbook(fs);		
		 df = new DataFormatter();
		 sheet=wb.getSheet(sheetname);        	   	     
	}

}
