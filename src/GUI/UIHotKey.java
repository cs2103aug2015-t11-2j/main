package GUI;

import java.io.IOException;
import javax.swing.JFrame;
import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import javafx.application.Platform;
import javafx.stage.Stage;

public class UIHotKey extends JFrame implements HotkeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int SHOW_WINDOWS = 1;
	private final int HIDE_WINDOWS = 2;
	private String libPath;
	private static UIHotKey theUIHotKey;
	
	private UIHotKey(){
	}
	
	protected static UIHotKey getInstance(){
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
	public void listenHotKey(final Stage myStage) throws IOException{
		//JudgeSystem();
		//loadLib(libPath);
		//JIntellitype.setLibraryLocation(try1); 
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
							Yui_GUI.myTrayController.showTrayMsg();
						}
					});
				}
			}
		});
	}
	
	@Override
	public void onHotKey(int arg0) {
		// TODO Auto-generated method stub
	}
	/*
	private synchronized void loadLib(String libName) throws IOException {     
	       
	    //String nativeTempDir = System.getProperty("java.io.tmpdir");   
	       
	    //InputStream in = null;   
	    BufferedInputStream reader = null;   
	    FileOutputStream writer = null;   
	       
	    File extractedLibFile = new File("user.dir/" + libName);   
	    if(!extractedLibFile.exists()){   
	        try {     
	           // if(in==null)   
	             //   in =  Yui_GUI.class.getResourceAsStream(libFullName);   
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
	}*/	
}
