package Lib;

import java.io.InputStream;

public class LoadLib {
	private final String SYSTEM_TYPE = System.getProperty("os.name"); 
	private static LoadLib theLoadLib;
	
	private LoadLib(){
	}
	
	public static LoadLib getInstance(){
		if(theLoadLib == null){
			theLoadLib = new LoadLib();
		}
		return theLoadLib;
	}
	
	public InputStream getLib32(){ 
	    String libExtension = (SYSTEM_TYPE.toLowerCase().indexOf("win")!=-1) ? ".dll" : ".so";     
	    String libFullName = "JIntellitype" + libExtension;
	    return LoadLib.class.getResourceAsStream(libFullName);
	}
	
	public InputStream getLib64(){
	    String libExtension = (SYSTEM_TYPE.toLowerCase().indexOf("win")!=-1) ? ".dll" : ".so";   
	    String libFullName = "JIntellitype64" + libExtension;
	    return LoadLib.class.getResourceAsStream(libFullName);
	}
}
