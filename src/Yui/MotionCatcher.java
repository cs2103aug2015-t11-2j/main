package Yui;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import main.ToDoList;

public class MotionCatcher {
	public static void keyboardCatcher(TextField userCommandBox, TextArea showBox){
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
	    					   Yui_GUI.returnCommand = ToDoList.implement(Yui_GUI.userCommand);
	    				   } catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
	    				   }
	    				   showBox.appendText(Yui_GUI.returnCommand + "\n" + "\n");
	    			   }
	    		   }
	    	   }
	       });
	}
	
	public static void mouseCatcher(ImageView enterKey, TextField userCommandBox, TextArea showBox){
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					   }
					   showBox.appendText(Yui_GUI.returnCommand + "\n" + "\n");
				   	}
	    	   }
	       });
	}
}
