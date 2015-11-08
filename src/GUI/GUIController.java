//@@author A0133992X
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Tasks.NumberedEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUIController {
	private int X = 0;
	private int Y = 0;
	private Logger logger = Logger.getLogger("GUILogic");
	private static GUIController theGUIController;
	
	private GUIController(){
	}
	
	protected static GUIController getInstance(){
		if(theGUIController == null){
			theGUIController = new GUIController();
		}
		return theGUIController;
	}
	
	public void showEvents(GridPane eventGrid, ImageView deadline, ImageView eventIcon, ImageView floatingIcon)
			throws IOException, ParseException {
		initialize();
		// deadline icon
		addIcon(eventGrid, deadline);
		addTaskBox(eventGrid, Yui_GUI.myUIBuffer.DeadlineList());
		// event icon
		addIcon(eventGrid, eventIcon);
		addTaskBox(eventGrid, Yui_GUI.myUIBuffer.EventList());
		// floating tasks icon
		addIcon(eventGrid, floatingIcon);
		addFloatingBox(eventGrid);
	}
	
	private boolean isShowMainGrid(){
		Yui_GUI.myUIBuffer.getIsShowMainGrid();
		return Yui_GUI.myUIBuffer.isShowMainGrid();
	}
	
	public void refreshTheme(){
		Yui_GUI.myUIBuffer.getTheme();
		String thisTheme = Yui_GUI.myUIBuffer.theme();
		if(thisTheme.equals("1")){
			Yui_GUI.listBkImage = Yui_GUI.listBkImage1;
		}
		if(thisTheme.equals("2")){
			Yui_GUI.listBkImage = Yui_GUI.listBkImage2;
		}
		if(thisTheme.equals("my theme")){
			File myTheme = new File(Yui_GUI.listBackgroundPath3);
			if (myTheme.exists()) {
				Yui_GUI.listBkImage = Yui_GUI.listBkImage3;
			}
		}
		Yui_GUI.listBk.setImage(Yui_GUI.listBkImage);
	}
	
	private void initialize() throws IOException, ParseException{
		Yui_GUI.myUIBuffer.getList();
		Y = 0;
	}
	
	private void addIcon(GridPane taskGrid, ImageView Icon){
		taskGrid.add(Icon, 0, Y);
		Y = Y + 1;
	}
	
	private void addTaskBox(GridPane taskGrid, ArrayList<NumberedEvent> tasksList) throws MalformedURLException{
		ArrayList<NumberedEvent> tasks = tasksList;
		int ddlLength = tasks.size();
		GridPane deadlineBox = new GridPane();
		deadlineBox.setVgap(1);
		// Y = ddlLength;
		for (int i = 0; i < ddlLength; i++) {
			NumberedEvent thisTask = tasks.get(i);
			UITask thisTasksBox = new UITask(thisTask);
			deadlineBox.add(thisTasksBox.getTaskBox(), X, i);
		}
		taskGrid.add(deadlineBox, 0, Y);
		Y = Y + 1;
	}
	
	private void addFloatingBox(GridPane taskGrid) throws MalformedURLException{
		ArrayList<NumberedEvent> floating = Yui_GUI.myUIBuffer.FloatingList();
		int fltLength = floating.size();
		GridPane floatingBox = new GridPane();
		floatingBox.setVgap(1);
		floatingBox.setHgap(1);
		for (int i = 0; i < fltLength; i++) {
			NumberedEvent thisFloating = floating.get(i);
			UITask thisFltBox = new UITask(thisFloating);
			if (i % 2 == 0) {
				X = 0;
			} else {
				X = 1;
			}
			int y = i / 2;
			floatingBox.add(thisFltBox.getTaskBox(), X, y);
		}
		taskGrid.add(floatingBox, 0, Y);
	}
	
	protected void keyboardCatcher(final TextField userCommandBox, final TextArea showBox){
		userCommandBox.setOnKeyPressed(new EventHandler<KeyEvent>(){
	    	   @Override
	    	   public void handle(KeyEvent event) {
	    		   if(event.getCode().equals(KeyCode.ENTER)){
	    			   handleEvent(userCommandBox, showBox);
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
	
	protected void mouseCatcher(ImageView enterKey, final TextField userCommandBox, final TextArea showBox){
		enterKey.setOnMouseClicked(new EventHandler<MouseEvent>(){
	    	   @Override
	    	   public void handle(MouseEvent event) {
	    		   handleEvent(userCommandBox, showBox);
	    	   }
	       });
	}
	
	private void handleEvent(final TextField userCommandBox, final TextArea showBox){
		passCommands(userCommandBox);
		   //link with logic
		if(!Yui_GUI.userCommand.equals("")){
		   refresh(showBox);
		}
	}
	
	private void passCommands(TextField userCommandBox){
		Yui_GUI.userCommand = userCommandBox.getText();
		System.out.print(Yui_GUI.userCommand);
		userCommandBox.clear();
	}
	
	private void refresh(TextArea showBox){
		refreshWithTry(showBox);
		refreshEveryTime(showBox);
	}
	
	private void refreshWithTry(TextArea showBox){
		try {
			   logger.log(Level.INFO, "get the output");
			   Yui_GUI.myUIBuffer.getFeedback(Yui_GUI.userCommand);
			   Yui_GUI.returnCommand =  Yui_GUI.myUIBuffer.returnedCommand();
			   Yui_GUI.eventGrid.getChildren().clear();
			   Yui_GUI.myGUIController.showEvents(Yui_GUI.eventGrid,Yui_GUI.deadlineIcon, Yui_GUI.eventIcon, Yui_GUI.floatingIcon);
		   } catch (IOException e) {
			   logger.log(Level.WARNING, "output error", e);
			   e.printStackTrace();
		   } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	}
	
	private void refreshEveryTime(TextArea showBox){
		Yui_GUI.mainGrid.setVisible(Yui_GUI.myGUIController.isShowMainGrid());
		Yui_GUI.btnTodolist.setVisible(Yui_GUI.myGUIController.isShowMainGrid());
		Yui_GUI.webBox.setVisible(!Yui_GUI.myGUIController.isShowMainGrid());
		Yui_GUI.btnCalendar.setVisible(!Yui_GUI.myGUIController.isShowMainGrid());
		showBox.appendText(Yui_GUI.returnCommand + "\n" + "\n");
		Yui_GUI.myGUIController.refreshTheme();
		logger.log(Level.INFO, "end of processing");
	}
	
	public void dragStage(GridPane grid, final Stage primaryStage){
		grid.setOnMousePressed(new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent event) {
	               Yui_GUI.xOffset = event.getSceneX();
	               Yui_GUI.yOffset = event.getSceneY();
	           }
	       });

	       grid.setOnMouseDragged(new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent event) {
	               primaryStage.setX(event.getScreenX() - Yui_GUI.xOffset);
	               primaryStage.setY(event.getScreenY() - Yui_GUI.yOffset);
	           }
	       });
	}
}
