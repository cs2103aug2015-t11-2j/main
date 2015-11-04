package Yui;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import javafx.application.Platform;
import javafx.stage.Stage;

public class UI_HotKey {
	private static final int SHOW_WINDOWS = 1;
	private static final int HIDE_WINDOWS = 2;
	private static final String libPath = "lib/JIntellitype.dll";
	
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
	public static void listenHotKey(Stage myStage){
		JIntellitype.setLibraryLocation(libPath); 
		JIntellitype.getInstance().registerHotKey(SHOW_WINDOWS, JIntellitype.MOD_CONTROL, 'S'); 
		JIntellitype.getInstance().registerHotKey(HIDE_WINDOWS, JIntellitype.MOD_CONTROL, 'H');
		
	    JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
	    	@Override
			public void onHotKey(int aIdentifier) {
				if (aIdentifier == SHOW_WINDOWS) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							myStage.show();
							myStage.toFront();
						}
					});

				} else if (aIdentifier == HIDE_WINDOWS) {
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
