package com.aa.cme.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jListener extends FileAppender {

	private static boolean root=false;
	
	public static Logger getLogger(Class cls){
		if(root){
			return Logger.getLogger(cls);
		}
		PropertyConfigurator.configure("./Test_Configuration/log4j.properties");
		root = true;
		return Logger.getLogger(cls);
	}
	  @Override
	    public void setFile(String fileName)
	    {
	        if (fileName.indexOf("%timestamp") >= 0) {
	            Date d = new Date();
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
	            fileName = fileName.replaceAll("%timestamp", format.format(d));
	        }
	        super.setFile(fileName);
	   }
	
}