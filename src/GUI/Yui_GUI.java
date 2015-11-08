//@@author A0133992X
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Yui_GUI extends Application{
	private final String OK_PATH = "/Image/ok.png";
	private final String DEADLINE_ICON_PATH = "/Image/deadlineN.png";
	private final String EVENT_ICON_PATH = "/Image/eventN.png";
	private final String FLOAT_ICON_PATH = "/Image/floatingN.png";
	private final String BACKGROUND_PATH = "/Image/uigroundN.png";
	private final String LOGO_PATH = "/Image/logo.png";
	public static final String ICON_PATH = "/Image/icon.png";
	private final String EXIT_PATH = "/Image/exit.png";
	private final String MENU_STATE1_PATH = "/Image/menu.png";
	private final String MENU_STATE2_PATH = "/Image/menu2.png";
	private final String LIST_BACK_PATH1 = "/Image/theme1.png";
	private final String LIST_BACK_PATH2 = "/Image/theme2.png";
	private Stage myStage;
	private GridPane backgroundGrid = new GridPane();
	private ImageView enterKey = new ImageView();
	private Group backg = new Group();
	private TextField userCommandBox = new TextField();
	protected static String userCommand;
	protected static String returnCommand;
	protected static double xOffset = 0;
	protected static double yOffset = 0;
	protected static GridPane mainGrid = new GridPane();
	protected static ScrollPane eventPane = new ScrollPane();
	protected static GridPane eventGrid = new GridPane();
	protected static ImageView listBk = new ImageView();
	protected static ImageView deadlineIcon = new ImageView();
	protected static ImageView eventIcon = new ImageView();
	protected static ImageView floatingIcon = new ImageView();
	protected TextArea showBox = new TextArea();
	protected static WebView webBox = new WebView();
	protected static ImageView btnTodolist = new ImageView();
	protected static ImageView btnCalendar = new ImageView();
	public static String listBackgroundPath3 = "user.dir/myTheme.png";
	public static Image listBkImage1;
	public static Image listBkImage2;
	public static Image listBkImage3;
	public static Image listBkImage;
	protected static GUIController myGUIController;
	protected static TrayController myTrayController;
	//protected static UIHotKey myUIHotKey;
	protected static UIBuffer myUIBuffer;
	
	public static void main(String[] args) {
       launch();
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
       
       myGUIController.showEvents(eventGrid,deadlineIcon, eventIcon, floatingIcon);

       setBackground(BACKGROUND_PATH);
       setLogo(LOGO_PATH, backgroundGrid);
       setExitButton(EXIT_PATH, backgroundGrid);
       setMenuIcon(MENU_STATE1_PATH, MENU_STATE2_PATH, backgroundGrid);
       setOKButton(OK_PATH, backgroundGrid);

       //Control the dragging of stage
       myGUIController.dragStage(backgroundGrid, myStage);
       
       setScene();
       setShowBox(mainGrid);
       setCommandBox(backgroundGrid);
       setWebGrid(backgroundGrid);
       
       HotKey(myStage);
       showBox.appendText(returnCommand + "\n");
       MotionCatch();
   }
   
   private void initializeMsg() throws IOException, ParseException{
	   myUIBuffer = UIBuffer.getInstance();
	   myUIBuffer.initializeCommand(userCommand);
	   returnCommand = myUIBuffer.returnedCommand();
	   myGUIController  = GUIController.getInstance();
	   myTrayController = TrayController.getInstance();
	   //myUIHotKey = UIHotKey.getInstance();
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
	   listBkImage1 = new Image(getClass().getResourceAsStream(LIST_BACK_PATH1));
       listBkImage2 = new Image(getClass().getResourceAsStream(LIST_BACK_PATH2));
       if(new File(listBackgroundPath3).exists()){
    	   listBkImage3 = new Image(new File(listBackgroundPath3).toURI().toURL().toString());
       }
       myGUIController.refreshTheme();
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
    		   //Event.fireEvent(myStage, new WindowEvent(myStage, WindowEvent.WINDOW_CLOSE_REQUEST ));
    		   }
    	   });
       backgroundGrid.add(exit, 4, 0);
   }
   
   private void setMenuIcon(String memuState1, String memuState2, GridPane backgroundGrid){
	   Image menuTodolist = new Image(getClass().getResourceAsStream(memuState1));
       Image menuCalendar = new Image(getClass().getResourceAsStream(memuState2));
       btnTodolist = new ImageView(menuTodolist);
       btnCalendar = new ImageView(menuCalendar);
       btnCalendar.setVisible(false);
       backgroundGrid.add(btnTodolist, 4, 2);
       backgroundGrid.add(btnCalendar, 4, 2);
   }
   
   private void setOKButton(String okPath, GridPane backgroundGrid){
	   Image enter = new Image(getClass().getResourceAsStream(okPath));
       enterKey = new ImageView(enter);
       backgroundGrid.add(enterKey, 4, 3);
   }
   
   private void setScene(){
	   Scene scene = new Scene(backg, 861, 556);
       myStage.setScene(scene);
   }
   
   private void setShowBox(GridPane mainGrid){
       showBox.setPrefSize(337.5, 420);
       showBox.setStyle("overflow-x:hidden;");
       mainGrid.add(showBox, 0, 0);
       showBox.setEditable(false);
       showBox.setWrapText(true);
   }
   
   private void setCommandBox(GridPane backgroundGrid){
	   backgroundGrid.add(userCommandBox, 0, 3);
       userCommandBox.requestFocus();
   }
   
   private void setWebGrid(GridPane backgroundGrid){
	   final WebEngine myEngin = webBox.getEngine();
       myEngin.load("https://nusmods.com");
       webBox.setPrefSize(735,420);
       webBox.setVisible(false);
       backgroundGrid.add(webBox, 0, 2);
   }
   
   private void HotKey(Stage myStage) throws IOException{
	   //myUIHotKey.listenHotKey(myStage);
	   myTrayController.createTrayIcon(myStage);
   }
   
   private void MotionCatch(){
	   myGUIController.keyboardCatcher(userCommandBox, showBox);
       myGUIController.mouseCatcher(enterKey, userCommandBox, showBox);
   }
}



