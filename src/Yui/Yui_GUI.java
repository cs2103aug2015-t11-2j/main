package Yui;

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


   public static void main(String[] args) {
       launch(args);
    }

   @Override
   public void start(final Stage primaryStage) throws IOException, ParseException {
       primaryStage.setTitle("Yui");
       primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
       primaryStage.show();
       
       returnCommand = ToDoList.initialize();
       
       //Set main grid
       GridPane grid = new GridPane();
       grid.setHgap(5);
       grid.setVgap(5);
       grid.setPadding(new Insets(10, 5, 5, 25));
       
       //set main grid of showing
       GridPane mainGrid = new GridPane();
       mainGrid.setHgap(10);
       mainGrid.setPrefSize(490, 275);
       grid.add(mainGrid, 0, 2);
       
       //set eventPane
       ScrollPane eventPane = new ScrollPane();
       eventPane.setPrefSize(255, 275);
       eventPane.setHbarPolicy(ScrollBarPolicy.NEVER);
       mainGrid.add(eventPane, 1, 0);
       
       //set eventGrid
       GridPane eventGrid = new GridPane();
       eventGrid.setHgap(3);
       //eventGrid.setVgap(1);
 
       //event list background
       ImageView listBk = new ImageView(new Image(getClass().getResourceAsStream("listBK2.png")));
       Group listAllBack = new Group();
       listAllBack.getChildren().addAll(listBk, eventGrid);
       eventPane.setContent(listAllBack);
       
       ImageView deadlineIcon = new ImageView(new Image(getClass().getResourceAsStream("deadline.png")));
       ImageView eventIcon = new ImageView(new Image(getClass().getResourceAsStream("event.png")));
   	   ImageView floatingIcon = new ImageView(new Image(getClass().getResourceAsStream("floating.png")));
   	   
       
       GUILogic.showEvents(eventGrid,deadlineIcon, eventIcon, floatingIcon);
       /*
       //deadline icon
       ImageView deadline = new ImageView(new Image(getClass().getResourceAsStream("deadline.png")));
       eventGrid.add(deadline, 0, 0);
       
       //event icon
       ImageView eventIcon = new ImageView(new Image(getClass().getResourceAsStream("event.png")));
       eventGrid.add(eventIcon, 0, 3);
       
       //floating tasks icon
       ImageView floatingIcon = new ImageView(new Image(getClass().getResourceAsStream("floating.png")));
       eventGrid.add(floatingIcon, 0, 5);
       
       //test events
       SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
       Calendar theDay = Calendar.getInstance();
       theDay.set(2011, 06, 24, 9, 59, 50);
       String getDate = date_format.format(theDay.getTime());
       
       GridPane deadlineEvent = new GridPane();
       deadlineEvent.setAccessibleText(1+"CS2101");
       Text tN = new Text(" 1" + " ");
       tN.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
       tN.setFill(Color.WHITE);
       deadlineEvent.add(tN, 0, 0);
       Text t1 = new Text("CS2101");
       t1.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
       t1.setFill(Color.WHITE);
       deadlineEvent.add(t1, 1, 0);
       Text tD = new Text(" " + getDate);
       tD.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
       tD.setFill(Color.WHITE);
       deadlineEvent.add(tD, 2, 0);
       Text tT = new Text("3:46");
       tT.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
       tT.setFill(Color.WHITE);
       deadlineEvent.add(tT, 3, 0);
       deadlineEvent.setPrefSize(273, 20); 
       
       Image deadlineBk = new Image(getClass().getResourceAsStream("commonEvent.png"));
       ImageView ddlBk = new ImageView(deadlineBk);
       Group ddlBackg = new Group();
       ddlBackg.getChildren().addAll(ddlBk,deadlineEvent);
       
       eventGrid.add(ddlBackg, 0, 1);
       
       //test event 2
       GridPane deadlineEvent2 = new GridPane();
       deadlineEvent2.setAccessibleText(1+"CS2103");
       Text tN2 = new Text(" 2" + " ");
       tN2.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
       tN2.setFill(Color.WHITE);
       deadlineEvent2.add(tN2, 0, 0);
       Text tNm2 = new Text("CS2103");
       tNm2.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
       tNm2.setFill(Color.WHITE);
       deadlineEvent2.add(tNm2, 1, 0);
       Text tD2 = new Text(" " + "15/10/19" + " ");
       tD2.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
       tD2.setFill(Color.WHITE);
       deadlineEvent2.add(tD2, 2, 0);
       Text tT2 = new Text("4:22");
       tT2.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
       tT2.setFill(Color.WHITE);
       deadlineEvent2.add(tT2, 3, 0);
       deadlineEvent2.setPrefSize(273, 20); 
       
       Image deadlineBk2 = new Image(getClass().getResourceAsStream("commonEvent.png"));
       ImageView ddlBk2 = new ImageView(deadlineBk2);
       Group ddlBackg2 = new Group();
       ddlBackg2.getChildren().addAll(ddlBk2,deadlineEvent2);
       
       eventGrid.add(ddlBackg2, 0, 2);
       /*
       GridPane events = new GridPane();
       events.add(new Text("new event"), 0, 0);
       events.setPrefSize(90, 25);
       eventGrid.add(events, 0, 1);
       

       GridPane events3 = new GridPane();
       events3.add(new Text("new event3"), 0, 0);
       events3.setPrefSize(90, 25);
       eventGrid.add(events3, 0, 4);
       */
       
       //set background
       Image background = new Image(getClass().getResourceAsStream("uiground.png"));
       ImageView bk = new ImageView(background);
       Group backg = new Group();
       backg.getChildren().addAll(bk,grid);
       
       //set icon
       Image iconYui = new Image(getClass().getResourceAsStream("logo.png"));
       grid.add(new ImageView(iconYui), 0, 0);
       
       //set exit button
       Image imageExit = new Image(getClass().getResourceAsStream("exit.png"));
       ImageView exit = new ImageView(imageExit);
       exit.setOnMouseClicked(new EventHandler<MouseEvent>(){
    	   @Override
    	   public void handle(MouseEvent event) {
    		   Event.fireEvent(primaryStage, new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST ));
    		   }
    	   });
       grid.add(exit, 4, 0);
       
       //set menu button
       Image menu1 = new Image(getClass().getResourceAsStream("menu.png"));
       grid.add(new ImageView(menu1), 4, 2);

       //set enter button
       Image enter = new Image(getClass().getResourceAsStream("ok.png"));
       ImageView enterKey = new ImageView(enter);
       grid.add(enterKey, 4, 3);
       
       //Control the dragging of stage
       DragController.dragStage(grid, primaryStage);

       //set the scene
       Scene scene = new Scene(backg, 600, 400);
       primaryStage.setScene(scene);

       //add text box to show message
       final TextArea showBox = new TextArea();
       showBox.setPrefSize(225, 275);
       showBox.setStyle("overflow-x:hidden;");
       mainGrid.add(showBox, 0, 0);
       showBox.setEditable(false);

       //add command box
       final TextField userCommandBox = new TextField();
       grid.add(userCommandBox, 0, 3);
       userCommandBox.requestFocus();
       
       //initialize GUI and Logic
       
       showBox.appendText(returnCommand + "\n");
       
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
    				   showBox.appendText(Yui_GUI.returnCommand + "\n" + "\n");
    				   logger.log(Level.INFO, "end of processing");
    			   }
    		   }
    	   }
       });
       MotionCatcher.mouseCatcher(enterKey, userCommandBox, showBox);
   }
}
