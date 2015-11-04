package Yui;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import main.ToDoList;

public class Yui_GUI extends Application{
	protected static String userCommand;
	protected static String returnCommand;
	protected static double xOffset = 0;
	protected static double yOffset = 0;
	private static Logger logger = Logger.getLogger("MotionCatcher");
	private static String listBackgroundPath1 = "/Image/theme1.png";
	private static String listBackgroundPath2 = "/Image/theme2.png";
	public static String listBackgroundPath3 = "user.dir/myTheme.png";
	public static Image listBkImage1;
	public static Image listBkImage2;
	public static Image listBkImage3;
	public static Image listBkImage;
	private static Stage myStage;
	private static GridPane backgroundGrid = new GridPane();
	private static GridPane mainGrid = new GridPane();
	private static ScrollPane eventPane = new ScrollPane();
	private static GridPane eventGrid = new GridPane();
	private static ImageView listBk = new ImageView();
	private static ImageView deadlineIcon = new ImageView();
	private static ImageView eventIcon = new ImageView();
	private static ImageView floatingIcon = new ImageView();
	private static final String DEADLINE_ICON_PATH = "/Image/deadlineN.png";
	private static final String EVENT_ICON_PATH = "/Image/eventN.png";
	private static final String FLOAT_ICON_PATH = "/Image/floatingN.png";
	private static Group backg = new Group();
	private static final String BACKGROUND_PATH = "/Image/uigroundN.png";
	private static final String LOGO_PATH = "/Image/logo.png";
	public static final String ICON_PATH = "/Image/icon.png";
	private static final String EXIT_PATH = "/Image/exit.png";
	
	

   public static void main(String[] args) {
       launch(args);
    }

   @Override
   public void start(final Stage primaryStage) throws IOException, ParseException {
	   setMyStage(primaryStage, ICON_PATH);
       
	   initializeMsg();
    
       setBackgrounfGrid();
       setMainGrid(backgroundGrid);
       setEventScrollPane(mainGrid);
       setEventGrid(eventPane);
       setTaskIcon(DEADLINE_ICON_PATH, EVENT_ICON_PATH, FLOAT_ICON_PATH);
       
       GUILogic.showEvents(eventGrid,deadlineIcon, eventIcon, floatingIcon);

       setBackground(BACKGROUND_PATH);
       setLogo(LOGO_PATH, backgroundGrid);
       setExitButton(EXIT_PATH, backgroundGrid);


       //set menu button
       Image menuTodolist = new Image(getClass().getResourceAsStream("/Image/menu.png"));
       Image menuCalendar = new Image(getClass().getResourceAsStream("/Image/menu2.png"));
       final ImageView btnTodolist = new ImageView(menuTodolist);
       final ImageView btnCalendar = new ImageView(menuCalendar);
       btnCalendar.setVisible(false);
       backgroundGrid.add(btnTodolist, 4, 2);
       backgroundGrid.add(btnCalendar, 4, 2);

       //set enter button
       Image enter = new Image(getClass().getResourceAsStream("/Image/ok.png"));
       ImageView enterKey = new ImageView(enter);
       backgroundGrid.add(enterKey, 4, 3);

       //Control the dragging of stage
       DragController.dragStage(backgroundGrid, myStage);

       //set the scene
       Scene scene = new Scene(backg, 861, 556);
       myStage.setScene(scene);

       //add text box to show message
       final TextArea showBox = new TextArea();
       showBox.setPrefSize(337.5, 420);
       showBox.setStyle("overflow-x:hidden;");
       mainGrid.add(showBox, 0, 0);
       showBox.setEditable(false);
       showBox.setWrapText(true);
       
       //add command box
       final TextField userCommandBox = new TextField();
       backgroundGrid.add(userCommandBox, 0, 3);
       userCommandBox.requestFocus();

       //initialize GUI and Logic
       showBox.appendText(returnCommand + "\n");
       
       //add web grid
       final WebView webBox = new WebView();
       final WebEngine myEngin = webBox.getEngine();
       myEngin.load("https://nusmods.com");
       webBox.setPrefSize(735,420);
       webBox.setVisible(false);
       backgroundGrid.add(webBox, 0, 2);
       
       UI_HotKey.listenHotKey(myStage);
       TrayController.createTrayIcon(myStage);
       
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
   
   private void initializeMsg() throws IOException, ParseException{
	   returnCommand = ToDoList.initialize();
   }
   
   private void setMyStage(Stage primaryStage, String iconString){
	   myStage = primaryStage;
	   myStage.setTitle("Yui");
	   myStage.initStyle(StageStyle.UNDECORATED);
	   myStage.getIcons().add(new Image(getClass().getResourceAsStream("/Image/icon.png")));
	   myStage.show();
	   myStage.setX(450);
	   myStage.setY(250);
   }
   
   private void setBackgrounfGrid(){
	   backgroundGrid.setHgap(5);
	   backgroundGrid.setVgap(5);
	   backgroundGrid.setPadding(new Insets(15, 7.5, 7.5, 37.5));
   }
   
   private void setMainGrid(GridPane bkGrid){
       mainGrid.setHgap(10);
       mainGrid.setPrefSize(735, 420);
       bkGrid.add(mainGrid, 0, 2);
   }
   
   private void setEventScrollPane(GridPane mainGrid){
       eventPane.setPrefSize(382.5, 420);
       eventPane.setHbarPolicy(ScrollBarPolicy.NEVER);
       eventPane.setVbarPolicy(ScrollBarPolicy.NEVER);
       mainGrid.add(eventPane, 1, 0);
   }
   
   private void setEventGrid(ScrollPane eventPane) throws MalformedURLException{
       eventGrid.setHgap(1);
       eventGrid.setVgap(2);
       setEventGridBackground(eventPane);
   }
   
   private void setEventGridBackground(ScrollPane eventPane) throws MalformedURLException{
	   listBkImage1 = new Image(getClass().getResourceAsStream(listBackgroundPath1));
       listBkImage2 = new Image(getClass().getResourceAsStream(listBackgroundPath2));
       if(new File(listBackgroundPath3).exists()){
    	   listBkImage3 = new Image(new File(listBackgroundPath3).toURI().toURL().toString());
       }
       listBkImage = listBkImage1;
       listBk = new ImageView(listBkImage);
       Group listAllBack = new Group();
       listAllBack.getChildren().addAll(listBk, eventGrid);
       eventPane.setContent(listAllBack);
   }
   
   private void setTaskIcon(String dP, String eP, String fP){
	   deadlineIcon = new ImageView(new Image(getClass().getResourceAsStream(dP)));
       eventIcon = new ImageView(new Image(getClass().getResourceAsStream(eP)));
   	   floatingIcon = new ImageView(new Image(getClass().getResourceAsStream(fP)));
   }
   
   private void setBackground(String bkPath){
	   Image background = new Image(getClass().getResourceAsStream(bkPath));
       ImageView bk = new ImageView(background);
       backg.getChildren().addAll(bk,backgroundGrid);
   }
   
   private void setLogo(String logoPath, GridPane backgroundGrid){
	   Image logoYui = new Image(getClass().getResourceAsStream(logoPath));
       backgroundGrid.add(new ImageView(logoYui), 0, 0);
   }
   
   private void setExitButton(String exitPath, GridPane backgroundGrid){
	   Image imageExit = new Image(getClass().getResourceAsStream(exitPath));
       ImageView exit = new ImageView(imageExit);
       exit.setOnMouseClicked(new EventHandler<MouseEvent>(){
    	   @Override
    	   public void handle(MouseEvent event) {
    		   System.exit(0);
    		   }
    	   });
       backgroundGrid.add(exit, 4, 0);
   }
}



