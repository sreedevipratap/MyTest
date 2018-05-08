package excelRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
//Set 1 Commit
import java.util.Set;

public class PropertyFileReader {
	static Properties prop; 
	static File file=new File("./data\\Config.properties");	
	static FileInputStream fileinput;
	static OutputStream os;
	public static void property() throws IOException
	{			
		prop = new Properties();
		FileInputStream fileinput=new FileInputStream(file);
		prop.load(fileinput);					
	}
	
	public static void updatePropertyFile(String key,String value) throws IOException
	{
		fileinput = new FileInputStream(file);
		prop.load(fileinput);
		fileinput.close();
		os = new FileOutputStream(file);	       
		prop.put(key, value);
		prop.store(os, "Dynamic Property File");
		os.close();		
	}
	
	public static void deleteProperty() throws IOException
	{
		fileinput = new FileInputStream(file);
		prop.load(fileinput);
		fileinput.close();
		Set<Object> keys= prop.keySet();
		for(Object k: new ArrayList<>(keys))
		{
			if(!(k.equals("P_fName") || k.equals("ChromePath") || k.equals("CM_fName")|| k.equals("TM_fName")|| k.equals("chrome")||k.equals("URL")||k.equals("PrerequisiteID")))
			{
				keys.remove(k);
			}
		}	
		os = new FileOutputStream(file);	       
		prop.store(os, "Dynamic Property File");
		os.close();		
	}
	
	public static String getPropertyValue(String Key)
	{
		return prop.getProperty(Key);
	}
	public static String getPath()
	{		
		return prop.getProperty("path");		
	}
   	 
	 public static String getURL()
	 {
		 return prop.getProperty("URL");
	 }
   
	 public static String getChrome()
	 {
		 return prop.getProperty("chrome");
	 }
	 
	 public static String getChromePath(){
		 return prop.getProperty("ChromePath");
	 }
	 public static String Playlist()
	 {
		 return prop.getProperty("P_fName");
	 }
	 public static String TestMethod()
	 {
		 return prop.getProperty("TM_fName");
	 }
	 public static String Commonmethods()
	 {
		 return prop.getProperty("CM_fName");
	 }
	 
}
