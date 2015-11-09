//@@author A0133992X
package GUI;

import java.io.IOException;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import javafx.application.Platform;
import javafx.stage.Stage;

public class UIHotKey{
	private final int SHOW_WINDOWS = 1;
	private final int HIDE_WINDOWS = 2;
	private String libPath;
	private static UIHotKey theUIHotKey;
	
	private UIHotKey(){
	}
	
	public static UIHotKey getInstance(){
		if(theUIHotKey == null){
			theUIHotKey = new UIHotKey();
		}
		return theUIHotKey;
	}
	
	public void initialize(){
		JIntellitype.setLibraryLocation(libPath);
		if (JIntellitype.checkInstanceAlreadyRunning("Yui")) {  
		    System.exit(1);  
		}  
		if (!JIntellitype.isJIntellitypeSupported()) {  
		    System.exit(1);  
		}
	}
	
	//set Hot Key
	public void listenHotKey(final Stage myStage, final TrayController myTrayController) throws IOException{ 
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
							myTrayController.showTrayMsg();
						}
					});
				}
			}
		});
	}
}
