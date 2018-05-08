package Utilities;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TableHandling extends Driverclass{
	
	 public TableHandling(WebDriver driver, ExtentReports Report, ExtentTest Test) {
		super(driver, Report, Test);
		// TODO Auto-generated constructor stub
	}
	static int subCount=0;
	 public static int getTableRowUsingColumnHavingSubTable(WebElement table,String columnname,String parameter)
	 {
	    	int row=0;	    	
	    	List<WebElement> rows =  table.findElements(By.tagName("tr"));  
	    	rows=table.findElements(By.xpath("//tbody/tr"));
	    	System.out.println(" rows.size()"+rows.size());	    	
	    	boolean datafound=false;
	    	int column=getColumn(table,columnname);
	    	for(int i=0;i<rows.size();i++)
	    	{	    		 
	    		List<WebElement> data=rows.get(i).findElements(By.tagName("td"));	    		
	    		if(!datafound)
				{   
	    			handleSubTable(rows.get(i));
	    			for(int k=0;k<data.size();k++)
	    				{
							if(k==column)
							{								
								if(data.get(k).getText().toString().equals(parameter))
								{														
									row=i-subCount;
									datafound=true;
								}
							}
	    				}	    				
				}
				else
					{
						break;
					}   	
	    	}
			return row;
	    }
	 
	 public static int getTableRowUsingColumn(WebElement table,String columnname,String parameter)
	   {
		   int row=0;
		   List<WebElement> rows;
		   if(table.findElements(By.tagName("tbody")).size()>1)
		   {
			   rows=table.findElements(By.xpath("//tbody/tr"));
		   }
		   else
		   {
			   WebElement tbody=table.findElement(By.tagName("tbody"));
			   rows =  tbody.findElements(By.tagName("tr"));
		   }
		   
	    	System.out.println(" rows.size()"+rows.size());
	   		boolean datafound=false;
	   		int column=getColumn(table,columnname);
	    	
	   		for(int i=0;i<rows.size();i++)
	   		{
	   			List<WebElement> data=rows.get(i).findElements(By.tagName("td"));
	   			if(!datafound)
	   			{   			
	   				for(int k=0;k<data.size();k++)
	   					{
							if(k==column)
							{
								System.out.println(data.get(k).getText().toString());
								if(data.get(k).getText().toString().equals(parameter))
								{
									System.out.println(i);
									row=i;
									datafound=true;
								}
							}														 
	   					}
	   				
				}
				else
					{
						break;
					}   	
	   	}	   	   
	   	    System.out.println("row"+row);	    	
			return row;
	   }
	    
	    public static int getColumn(WebElement table,String columnname)
	    {
	    	
	        int column =0;
	        WebElement thead=table.findElement(By.tagName("thead"));	        
	        List<WebElement> header=thead.findElements(By.tagName("th"));
	        boolean found=false;
	        for(int i=0;i<header.size();i++)
	        {
	        	if(!found)
				{
	        		System.out.println(header.get(i).getText().toString());
	        		if(header.get(i).getText().toString().equals(columnname))
	        		{
	        			column=i;
	        			found=true;
	        		}
				}
	        	else
				{
					break;
				}
			
	        	
	        }
	    	System.out.println("column"+column);
	    	return column;
	    }
	    
	   
	    public static int handleSubTable(WebElement rows)
	    {
	    	
	    	List<WebElement> subtable=rows.findElements(By.tagName("table"));
	    	for(WebElement e:subtable )
	    	{
	    		if(e.getAttribute("id").toString().startsWith("subtable"))
	    		{
	    			subCount=subCount+2;
	    		}
	    		else
	    		{
	    			subCount=subCount+subtable.size();
	    		}
	    	}
	    	
	    	return subCount;
	    }
  
	   
}
