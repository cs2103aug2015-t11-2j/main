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
	    					   Yui_GUI.eventGrid.getChildren().clear();
	    					   GUILogic.showEvents(Yui_GUI.eventGrid,Yui_GUI.deadlineIcon, Yui_GUI.eventIcon, Yui_GUI.floatingIcon);
	    				   } catch (IOException e) {
	    					   logger.log(Level.WARNING, "output error", e);
	    					   e.printStackTrace();
	    				   } catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
	    				   }
	    				   Yui_GUI.mainGrid.setVisible(GUILogic.isShowMainGrid());
	    				   Yui_GUI.btnTodolist.setVisible(GUILogic.isShowMainGrid());
	    				   Yui_GUI.webBox.setVisible(!GUILogic.isShowMainGrid());
	    				   Yui_GUI.btnCalendar.setVisible(!GUILogic.isShowMainGrid());
	    				   showBox.appendText(Yui_GUI.returnCommand + "\n" + "\n");
	    				   Yui_GUI.listBk.setImage(Yui_GUI.listBkImage);
	    				   logger.log(Level.INFO, "end of processing");
	    			   }
	    		   }

	    		   if(event.isAltDown()){
	    			   if(event.getCode().equals(KeyCode.UP)){
	    				   showBox.setScrollTop(showBox.getScrollTop() - 5f);
	    			   }
	    			   if(event.getCode().equals(KeyCode.DOWN)){
	    				   showBox.setScrollTop(showBox.getScrollTop() + 5f);
	    			   }
	    			   if(event.getCode().equals(KeyCode.LEFT)){
	    				   showBox.setScrollLeft(showBox.getScrollLeft() - 5f);
	    			   }
	    			   if(event.getCode().equals(KeyCode.RIGHT)){
	    				   showBox.setScrollLeft(showBox.getScrollLeft() + 5f);
	    			   }
	    		   } else {
		    		   if(event.getCode().equals(KeyCode.UP)){
		    			   Yui_GUI.eventPane.setVvalue(Yui_GUI.eventPane.getVvalue() - 0.3f);
		    		   }
		    		   if(event.getCode().equals(KeyCode.DOWN)){
		    			   Yui_GUI.eventPane.setVvalue(Yui_GUI.eventPane.getVvalue() + 0.3f);
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
						   logger.log(Level.INFO, "get the output");
    					   Yui_GUI.returnCommand = ToDoList.implement(Yui_GUI.userCommand);
    					   Yui_GUI.eventGrid.getChildren().clear();
    					   GUILogic.showEvents(Yui_GUI.eventGrid,Yui_GUI.deadlineIcon, Yui_GUI.eventIcon, Yui_GUI.floatingIcon);
					   } catch (IOException e) {
						   e.printStackTrace();
					   } catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					   Yui_GUI.mainGrid.setVisible(GUILogic.isShowMainGrid());
    				   Yui_GUI.btnTodolist.setVisible(GUILogic.isShowMainGrid());
    				   Yui_GUI.webBox.setVisible(!GUILogic.isShowMainGrid());
    				   Yui_GUI.btnCalendar.setVisible(!GUILogic.isShowMainGrid());
    				   showBox.appendText(Yui_GUI.returnCommand + "\n" + "\n");
    				   Yui_GUI.listBk.setImage(Yui_GUI.listBkImage);
    				   logger.log(Level.INFO, "end of processing");
				   	}
	    	   }
	       });
	}
}
