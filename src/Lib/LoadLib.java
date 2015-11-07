package Lib;

import java.io.InputStream;


public class LoadLib {
	private static final String SYSTEM_TYPE = System.getProperty("os.name"); 
	
	public static InputStream getLib32(){ 
	    String libExtension = (SYSTEM_TYPE.toLowerCase().indexOf("win")!=-1) ? ".dll" : ".so";     
	    String libFullName = "JIntellitype" + libExtension;
	    return LoadLib.class.getResourceAsStream(libFullName);
	}
	
	public static InputStream getLib64(){
	    String libExtension = (SYSTEM_TYPE.toLowerCase().indexOf("win")!=-1) ? ".dll" : ".so";   
	    String libFullName = "JIntellitype64" + libExtension;
	    return LoadLib.class.getResourceAsStream(libFullName);
	}
}
