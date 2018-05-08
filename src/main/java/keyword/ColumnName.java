package keyword;


import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import Report.ReportRow;
import Utilities.Driverclass;
import excelRead.Playlist;
import excelRead.PropertyFileReader;
//Set 1 Commit
//Trial
public class ColumnName extends Driverclass {
	
	public ColumnName(WebDriver driver, ExtentReports Report, ExtentTest Test) {
		super(driver, Report, Test);
		
		// TODO Auto-generated constructor stub
	}
	
	
	int c=0,d,e=0,j=0,c1=0,d1=0,j1=0,e1=0;
	List<ReportRow>listOfDataPlist = new ArrayList<ReportRow>();
	List<ReportRow> listOfDataFromReport = new ArrayList<ReportRow>();
	List<ReportRow> utilitylist = new ArrayList<ReportRow>();
	
	public  void excelcall( String sheetname, String fName)
	//excelcall( sheetname=Plist, fname=./data\\Playlist.xlsx)--for first call
	//excelcall( sheetname=General, fname=./data\\TestScript-CDDUK.xlsx)--for second call
	{
		try
		{				
			Workbook workbook = WorkbookFactory.create(new FileInputStream(fName));
			Sheet sheet = workbook.getSheet(sheetname);
			int totalRows = sheet.getPhysicalNumberOfRows();
			Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
			Row row =  sheet.getRow(0); //Get first row
			//following is boilerplate from the java doc
			short minColIx = row.getFirstCellNum(); //get the first column index for a row
			short maxColIx = row.getLastCellNum(); //get the last column index for a row	
			for(short colIx=minColIx; colIx<maxColIx; colIx++) 
			{ //loop from first to last index
				Cell cell = row.getCell(colIx); //get the cell				
				map.put(cell.getStringCellValue(),cell.getColumnIndex());//add the cell contents (name of column) and cell index to the map								
			}
    
			for(int x = 1; x<totalRows; x++)
			{
				if(maxColIx<=3)
				{
					ReportRow rr = new ReportRow(); //Data structure to hold the data from the xls file.
					XSSFRow dataRow = (XSSFRow) sheet.getRow(x);			 
					int idxForColumn1 = map.get("Sheet"); //get the column index for the column with header name = "Column1"			
					int idxForColumn2 = map.get("Method");
					int idxForColumn3 = map.get("Status");			 			 
					DataFormatter formatter = new DataFormatter();
					XSSFCell cell1 = dataRow.getCell(idxForColumn1); //Get the cells for each of the indexes
					XSSFCell cell2 = dataRow.getCell(idxForColumn2);
					XSSFCell cell3 = dataRow.getCell(idxForColumn3);			 
					rr.setColumn1(formatter.formatCellValue(cell1)); //Get the values out of those cells and put them into the report row object
					rr.setColumn2(formatter.formatCellValue(cell2));
					rr.setColumn3(formatter.formatCellValue(cell3));			 
					listOfDataPlist.add(rr);		 			 
				}
				else if(maxColIx==8)
				{
			
					ReportRow rr = new ReportRow(); //Data structure to hold the data from the xls file.
					XSSFRow dataRow = (XSSFRow) sheet.getRow(x); //get row 1 to row n (rows containing data)
					int idxForColumn1 = map.get("locator type"); //get the column index for the column with header name = "Column1"
					int idxForColumn2 = map.get("value");
					int idxForColumn3 = map.get("keyword");
					int idxForColumn4 = map.get("parameter");
					int idxForColumn5 = map.get("Title");
					int idxForColumn6 = map.get("Status");		
					DataFormatter formatter = new DataFormatter();
					XSSFCell cell1 = dataRow.getCell(idxForColumn1); //Get the cells for each of the indexes
					XSSFCell cell2 = dataRow.getCell(idxForColumn2);
					XSSFCell cell3 = dataRow.getCell(idxForColumn3); 
					XSSFCell cell4 = dataRow.getCell(idxForColumn4); //Get the cells for each of the indexes
					XSSFCell cell5 = dataRow.getCell(idxForColumn5);
					XSSFCell cell6 = dataRow.getCell(idxForColumn6);			 
					rr.setColumn1(formatter.formatCellValue(cell1)); //Get the values out of those cells and put them into the report row object
					rr.setColumn2(formatter.formatCellValue(cell2));
					rr.setColumn3(formatter.formatCellValue(cell3));
					rr.setColumn4(formatter.formatCellValue(cell4));
					rr.setColumn5(formatter.formatCellValue(cell5));
					rr.setColumn6(formatter.formatCellValue(cell6));
					utilitylist.add(rr);
				}
		
				else
				{
		
					ReportRow rr = new ReportRow(); //Data structure to hold the data from the xls file.
					XSSFRow dataRow = (XSSFRow) sheet.getRow(x); //get row 1 to row n (rows containing data) 
					int idxForColumn1 = map.get("locator type"); //get the column index for the column with header name = "Column1"		
					int idxForColumn2 = map.get("value");		
					int idxForColumn3 = map.get("keyword");
					int idxForColumn4 = map.get("parameter");
					int idxForColumn5 = map.get("Test method");
					int idxForColumn6 = map.get("Status");		
					DataFormatter formatter = new DataFormatter();
					XSSFCell cell1 = dataRow.getCell(idxForColumn1); //Get the cells for each of the indexes
					XSSFCell cell2 = dataRow.getCell(idxForColumn2);
					XSSFCell cell3 = dataRow.getCell(idxForColumn3); 
					XSSFCell cell4 = dataRow.getCell(idxForColumn4); //Get the cells for each of the indexes
					XSSFCell cell5 = dataRow.getCell(idxForColumn5);
					XSSFCell cell6 = dataRow.getCell(idxForColumn6);	 
					rr.setColumn1(formatter.formatCellValue(cell1)); //Get the values out of those cells and put them into the report row object
					rr.setColumn2(formatter.formatCellValue(cell2));
					rr.setColumn3(formatter.formatCellValue(cell3));
					rr.setColumn4(formatter.formatCellValue(cell4));
					rr.setColumn5(formatter.formatCellValue(cell5));
					rr.setColumn6(formatter.formatCellValue(cell6));
					listOfDataFromReport.add(rr);	 	
				}

			}

			for (int k=0;k<listOfDataPlist.size();k++)
			{
				Playlist pl= new Playlist(driver,Report,Test);
				pl.Plistcall1(listOfDataPlist.get(k).getColumn1(),listOfDataPlist.get(k).getColumn2(),listOfDataPlist.get(k).getColumn3());		 		
			}		  
		}	
	catch (Exception e)
		{
			System.out.println(e.getMessage());
	    }
		
	}
	// Method to call test methods
	public void calltestmethod(String Sheet1,String Method1) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException, InterruptedException
		{
			excelcall(Sheet1,PropertyFileReader.TestMethod());//TestMethod() returns--./data\\TestScript-CDDUK.xlsx
			for(j=0; j< listOfDataFromReport.size();j++)
			{	   
				if((listOfDataFromReport.get(j).getColumn5().equals(Method1)))
				{
					c=j;
					break;
				}
				else if(j==listOfDataFromReport.size())
				{
					c=0;
				}
			}
			for(d=c+1; d<=listOfDataFromReport.size();d++)
			{
				int h=listOfDataFromReport.size();
		  
				if(d==h)
				{
					e=d;
				}
				else if(!(listOfDataFromReport.get(d).getColumn5().isEmpty()))
				{
					e=d;
					break;
				}		  
			}
	
			for(int f=c;f<e;f++)
			{			
				if((listOfDataFromReport.get(f).getColumn1().equals("Common method")))
				{			 
					callutility(listOfDataFromReport.get(f).getColumn2());
				}
				else if(listOfDataFromReport.get(f).getColumn1().equalsIgnoreCase("Testmethod") || listOfDataFromReport.get(f).getColumn1().equalsIgnoreCase("Actionmethod"))
				{			  
					ActionSwitch actionSwitch= new ActionSwitch(driver,Report,Test);
					actionSwitch.pommethods(listOfDataFromReport.get(f).getColumn1(),listOfDataFromReport.get(f).getColumn2(),listOfDataFromReport.get(f).getColumn3(),listOfDataFromReport.get(f).getColumn4(),listOfDataFromReport.get(f).getColumn5(),listOfDataFromReport.get(f).getColumn6());
				}
				else
				{
					Keywordswitch ks=new Keywordswitch( driver,Report,Test);
					ks.locate(listOfDataFromReport.get(f).getColumn1(),listOfDataFromReport.get(f).getColumn2(),listOfDataFromReport.get(f).getColumn3(),listOfDataFromReport.get(f).getColumn4(),listOfDataFromReport.get(f).getColumn5(),listOfDataFromReport.get(f).getColumn6());
				}		  
			}
		
		}
	//Methods to call utilities
	public void callutility( String Method) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException, InterruptedException
	{
		 excelcall("Common method",PropertyFileReader.Commonmethods());//./data\\TestScript-CDDUK.xlsx
		 for(j1=0; j1< utilitylist.size();j1++)
			{		  
			  if((utilitylist.get(j1).getColumn5().equals(Method)))
			   {
		         c1=j1;
		         break;
			   }
	          else if(j1==utilitylist.size())
	           {
		       c1=0;
	            }
	           }
	     for(d1=c1+1; d1<=utilitylist.size();d1++)
	       {
		     int h1=utilitylist.size();
		  
		     if(d1==h1)
			 {
				e1=d1;
			 }
		     else if(!(utilitylist.get(d1).getColumn5().isEmpty()))
		   {
			e1=d1;
			break;
		   }
		
		   }
	    
		for(int f1=c1;f1<e1;f1++)
		{
			 Keywordswitch ks=new Keywordswitch( driver,Report,Test);
		      ks.locate(utilitylist.get(f1).getColumn1(),utilitylist.get(f1).getColumn2(),utilitylist.get(f1).getColumn3(),utilitylist.get(f1).getColumn4(),utilitylist.get(f1).getColumn5(),utilitylist.get(f1).getColumn6());			 
		}		  
	}
}

