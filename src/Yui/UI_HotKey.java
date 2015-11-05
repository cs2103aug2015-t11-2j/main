package Yui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import javafx.application.Platform;
import javafx.stage.Stage;

public class UI_HotKey {
	private static final int SHOW_WINDOWS = 1;
	private static final int HIDE_WINDOWS = 2;
	private static final String LIB_PATH_32 = "JIntellitype";
	//static File try1 = new File(Yui_GUI.class.getResourceAsStream("/Lib/JIntellitype.dll")); 
	private static final String LIB_PATH_64 = "JIntellitype64.dll";
	private static String libPath;
	
	private static void JudgeSystem(){
		String arch = System.getProperty("os.arch");
			if(arch.contains("x86")){
				libPath = LIB_PATH_32;
			} else {
				libPath = LIB_PATH_64;
			}
	}
	
	public static void initialize(){
		JIntellitype.setLibraryLocation(libPath);
		if (JIntellitype.checkInstanceAlreadyRunning("Yui")) {  
		    System.exit(1);  
		}  
		if (!JIntellitype.isJIntellitypeSupported()) {  
		    System.exit(1);  
		}
	}
	
	//set Hot Key
	public static void listenHotKey(final Stage myStage) throws IOException{
		JudgeSystem();
		loadLib(libPath);
		//JIntellitype.setLibraryLocation(libPath); 
		JIntellitype.getInstance().registerHotKey(SHOW_WINDOWS, JIntellitype.MOD_CONTROL, 'Y'); 
		JIntellitype.getInstance().registerHotKey(HIDE_WINDOWS, JIntellitype.MOD_CONTROL, 'H');
		
	    JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
	    	@Override
			public void onHotKey(int hotKeyID) {
				if (hotKeyID == SHOW_WINDOWS) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							myStage.show();
							myStage.toFront();
						}
					});

				} else if (hotKeyID == HIDE_WINDOWS) {
					Platform.setImplicitExit(false);
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							myStage.hide();
							myStage.toBack();
							TrayController.showTrayMsg();
						}
					});
				}
			}
		});
	    
	}
	
	private synchronized static void loadLib(String libName) throws IOException {   
	    String systemType = System.getProperty("os.name");   
	    String libExtension = (systemType.toLowerCase().indexOf("win")!=-1) ? ".dll" : ".so";   
	       
	    String libFullName = libName + libExtension;   
	       
	    //String nativeTempDir = System.getProperty("java.io.tmpdir");   
	       
	    InputStream in = null;   
	    BufferedInputStream reader = null;   
	    FileOutputStream writer = null;   
	       
	    File extractedLibFile = new File("user.dir"+File.separator+libFullName);   
	    if(!extractedLibFile.exists()){   
	        try {   
	            in = Yui_GUI.class.getResourceAsStream("/Lib/" + libFullName);   
	            if(in==null)   
	                in =  Yui_GUI.class.getResourceAsStream(libFullName);   
	            //Yui_GUI.class.getResource(libFullName);   
	            reader = new BufferedInputStream(in);   
	            writer = new FileOutputStream(extractedLibFile);   
	               
	            byte[] buffer = new byte[1024];   
	               
	            while (reader.read(buffer) > 0){   
	                writer.write(buffer);   
	                buffer = new byte[1024];   
	            }   
	        } catch (IOException e){   
	            e.printStackTrace();   
	        } finally {   
	            if(in!=null)   
	                in.close();   
	            if(writer!=null)   
	                writer.close();   
	        }   
	    }   
	    JIntellitype.setLibraryLocation(extractedLibFile);   
	}
	
}
