package Yui;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import javafx.application.Platform;
import javafx.stage.Stage;

public class UI_HotKey {
	private static final int SHOW_WINDOWS = 1;
	private static final int HIDE_WINDOWS = 2;
	private static final String LIB_PATH_32 = "lib/JIntellitype.dll";
	private static final String LIB_PATH_64 = "lib/JIntellitype64.dll";
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
	public static void listenHotKey(final Stage myStage){
		JudgeSystem();
		JIntellitype.setLibraryLocation(libPath); 
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
	
	
}
