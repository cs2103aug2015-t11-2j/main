package Yui;

import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import main.ToDoList;

public class MotionCatcher {
	private static Logger logger = Logger.getLogger("MotionCatcher");
	public static void keyboardCatcher(final TextField userCommandBox, final TextArea showBox){
		userCommandBox.setOnKeyPressed(new EventHandler<KeyEvent>(){
	    	   @Override
	    	   public void handle(KeyEvent event) {
	    		   if(event.getCode().equals(KeyCode.ENTER)){
	    			   Yui_GUI.userCommand = userCommandBox.getText();
	    			   //link with logic
	    			   System.out.print(Yui_GUI.userCommand);
	    			   userCommandBox.clear();
	    			   //link with logic
	    			   if(!Yui_GUI.userCommand.equals("")){
	    				   try {
	    					   logger.log(Level.INFO, "get the output");
	    					   Yui_GUI.returnCommand = ToDoList.implement(Yui_GUI.userCommand);
	    				   } catch (IOException e) {
	    					   logger.log(Level.WARNING, "output error", e);
	    					   e.printStackTrace();
	    				   } catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    				   showBox.appendText(Yui_GUI.returnCommand + "\n" + "\n");
	    				   logger.log(Level.INFO, "end of processing");
	    			   }
	    		   }
	    	   }
	       });
	}
	
	public static void mouseCatcher(ImageView enterKey, final TextField userCommandBox, final TextArea showBox){
		enterKey.setOnMouseClicked(new EventHandler<MouseEvent>(){
	    	   @Override
	    	   public void handle(MouseEvent event) {
	    		   Yui_GUI.userCommand = userCommandBox.getText();
	    		   //link with logic
	    		   System.out.print(Yui_GUI.userCommand);
	    		   userCommandBox.clear();
	    		   //link with logic
	    		   if(!Yui_GUI.userCommand.equals("")){
					   try {
						   Yui_GUI.returnCommand = ToDoList.implement(Yui_GUI.userCommand);
					   } catch (IOException e) {
						   e.printStackTrace();
					   } catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					   showBox.appendText(Yui_GUI.returnCommand + "\n" + "\n");
				   	}
	    	   }
	       });
	}
}
