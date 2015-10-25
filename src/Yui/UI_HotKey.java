package Yui;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import javafx.stage.Stage;

public class UI_HotKey {
	private static final int SHOW_WINDOWS = 2;
	private static final int HIDE_WINDOWS = 1;
	private static final String libPath = "lib/JIntellitype.dll";
	private static int show = 1;
	
	public static void main(){
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
	    
		if(show == 1){
	    	myStage.show();
	    } else {
	    	myStage.hide();
	    }
		
	    JIntellitype.getInstance().addHotKeyListener(new HotkeyListener() {
		       
		      @Override
		      public void onHotKey(int markCode) {
		        switch (markCode) { 
		        case SHOW_WINDOWS: 
		        	show = 1;
		          break; 
		        case HIDE_WINDOWS: 
		        	System.out.print("try");
		          break;  
		        }         
		      }
		    }); 
	    
	    
	}
	
	
}
