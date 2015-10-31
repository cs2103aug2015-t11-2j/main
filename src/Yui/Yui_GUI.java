package Yui;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import main.ToDoList;

public class Yui_GUI extends Application{
	protected static String userCommand;
	protected static String returnCommand;
	protected static double xOffset = 0;
	protected static double yOffset = 0;
	private static Logger logger = Logger.getLogger("MotionCatcher");
	private static String listBackgroundPath1 = "/Image/listBKN.png";
	private static String listBackgroundPath2 = "/Image/newBK.png";
	public static String listBackgroundPath3 = "user.dir/myTheme.png";
	public static Image listBkImage1;
	public static Image listBkImage2;
	public static Image listBkImage3;
	public static Image listBkImage;

   public static void main(String[] args) {
       launch(args);
    }

   @Override
   public void start(final Stage primaryStage) throws IOException, ParseException {
       primaryStage.setTitle("Yui");
       primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Image/icon.png")));
       primaryStage.show();

       returnCommand = ToDoList.initialize();

       //Set main grid
       GridPane grid = new GridPane();
       grid.setHgap(5);
       grid.setVgap(5);
       grid.setPadding(new Insets(15, 7.5, 7.5, 37.5));

       //set main grid of showing
       final GridPane mainGrid = new GridPane();
       mainGrid.setHgap(10);
       mainGrid.setPrefSize(735, 420);
       grid.add(mainGrid, 0, 2);

       //set eventPane
       final ScrollPane eventPane = new ScrollPane();
       eventPane.setPrefSize(382.5, 420);
       eventPane.setHbarPolicy(ScrollBarPolicy.NEVER);
       eventPane.setVbarPolicy(ScrollBarPolicy.NEVER);
       mainGrid.add(eventPane, 1, 0);

       //set eventGrid
       final GridPane eventGrid = new GridPane();
       eventGrid.setHgap(1);
       eventGrid.setVgap(2);

       //event list background
       listBkImage1 = new Image(getClass().getResourceAsStream(listBackgroundPath1));
       listBkImage2 = new Image(getClass().getResourceAsStream(listBackgroundPath2));
       if(new File(listBackgroundPath3).exists()){
    	   listBkImage3 = new Image(new File(listBackgroundPath3).toURI().toURL().toString());
       }
       listBkImage = listBkImage1;
       final ImageView listBk = new ImageView(listBkImage);
       Group listAllBack = new Group();
       listAllBack.getChildren().addAll(listBk, eventGrid);
       eventPane.setContent(listAllBack);

       final ImageView deadlineIcon = new ImageView(new Image(getClass().getResourceAsStream("/Image/deadlineN.png")));
       final ImageView eventIcon = new ImageView(new Image(getClass().getResourceAsStream("/Image/eventN.png")));
   	   final ImageView floatingIcon = new ImageView(new Image(getClass().getResourceAsStream("/Image/floatingN.png")));


       GUILogic.showEvents(eventGrid,deadlineIcon, eventIcon, floatingIcon);

       //set background
       Image background = new Image(getClass().getResourceAsStream("/Image/uigroundN.png"));
       ImageView bk = new ImageView(background);
       Group backg = new Group();
       backg.getChildren().addAll(bk,grid);

       //set icon
       Image iconYui = new Image(getClass().getResourceAsStream("/Image/logo.png"));
       grid.add(new ImageView(iconYui), 0, 0);

       //set exit button
       Image imageExit = new Image(getClass().getResourceAsStream("/Image/exit.png"));
       ImageView exit = new ImageView(imageExit);
       exit.setOnMouseClicked(new EventHandler<MouseEvent>(){
    	   @Override
    	   public void handle(MouseEvent event) {
    		   Event.fireEvent(primaryStage, new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST ));
    		   }
    	   });
       grid.add(exit, 4, 0);

       //set menu button
       Image menuTodolist = new Image(getClass().getResourceAsStream("/Image/menu.png"));
       Image menuCalendar = new Image(getClass().getResourceAsStream("/Image/menu2.png"));
       final ImageView btnTodolist = new ImageView(menuTodolist);
       final ImageView btnCalendar = new ImageView(menuCalendar);
       btnCalendar.setVisible(false);
       grid.add(btnTodolist, 4, 2);
       grid.add(btnCalendar, 4, 2);

       //set enter button
       Image enter = new Image(getClass().getResourceAsStream("/Image/ok.png"));
       ImageView enterKey = new ImageView(enter);
       grid.add(enterKey, 4, 3);

       //Control the dragging of stage
       DragController.dragStage(grid, primaryStage);

       //set the scene
       Scene scene = new Scene(backg, 861, 556);
       primaryStage.setScene(scene);

       //add text box to show message
       final TextArea showBox = new TextArea();
       showBox.setPrefSize(337.5, 420);
       showBox.setStyle("overflow-x:hidden;");
       mainGrid.add(showBox, 0, 0);
       showBox.setEditable(false);
       
       //add command box
       final TextField userCommandBox = new TextField();
       grid.add(userCommandBox, 0, 3);
       userCommandBox.requestFocus();

       //initialize GUI and Logic
       showBox.appendText(returnCommand + "\n");
       
       //add web grid
       final WebView webBox = new WebView();
       final WebEngine myEngin = webBox.getEngine();
       myEngin.load("https://nusmods.com");
       webBox.setPrefSize(735,420);
       webBox.setVisible(false);
       grid.add(webBox, 0, 2);
       
       //catch the motion of users
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
    					   eventGrid.getChildren().clear();
    					   GUILogic.showEvents(eventGrid,deadlineIcon, eventIcon, floatingIcon);
    				   } catch (IOException e) {
    					   logger.log(Level.WARNING, "output error", e);
    					   e.printStackTrace();
    				   } catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
    				   }
    				   mainGrid.setVisible(GUILogic.isShowMainGrid());
    				   btnTodolist.setVisible(GUILogic.isShowMainGrid());
    				   webBox.setVisible(!GUILogic.isShowMainGrid());
    				   btnCalendar.setVisible(!GUILogic.isShowMainGrid());
    				   showBox.appendText(returnCommand + "\n" + "\n");
    				   listBk.setImage(listBkImage);
    				   logger.log(Level.INFO, "end of processing");
    			   }
    		   }

    		   if(event.isAltDown()){
    			   if(event.getCode().equals(KeyCode.UP)){
    				   showBox.setScrollTop(showBox.getScrollTop() - 1.2f);
    			   }
    			   if(event.getCode().equals(KeyCode.DOWN)){
    				   showBox.setScrollTop(showBox.getScrollTop() + 1.2f);
    			   }
    			   
    		   } else {
	    		   if(event.getCode().equals(KeyCode.UP)){
	    			   eventPane.setVvalue(eventPane.getVvalue() - 0.3f);
	    		   }
	    		   if(event.getCode().equals(KeyCode.DOWN)){
	    			   eventPane.setVvalue(eventPane.getVvalue() + 0.3f);
	    		   }
    		   }
    	   }
       });
       MotionCatcher.mouseCatcher(enterKey, userCommandBox, showBox);
   }
}
