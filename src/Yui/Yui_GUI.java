package Yui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import main.ToDoList;

public class Yui_GUI extends Application{
	protected static String userCommand;
	protected static String returnCommand;
	protected static double xOffset = 0;
	protected static double yOffset = 0;


   public static void main(String[] args) {
       launch(args);
    }

   @Override
   public void start(final Stage primaryStage) throws IOException {
       primaryStage.setTitle("Yui");
       primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
       primaryStage.show();
       
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
       eventPane.setStyle("overflow-x:hidden;");
       mainGrid.add(eventPane, 1, 0);
       
       //set eventGrid
       GridPane eventGrid = new GridPane();
       eventGrid.setHgap(3);
       eventGrid.setVgap(3);
       
       
       //deadline icon
       ImageView deadline = new ImageView(new Image(getClass().getResourceAsStream("deadline.png")));
       eventGrid.add(deadline, 0, 0);
       
       //event icon
       ImageView eventIcon = new ImageView(new Image(getClass().getResourceAsStream("event.png")));
       eventGrid.add(eventIcon, 0, 3);
       
       //floating tasks icon
       ImageView floatingIcon = new ImageView(new Image(getClass().getResourceAsStream("floating.png")));
       eventGrid.add(floatingIcon, 0, 5);
 
       //event list background
       ImageView listBk = new ImageView(new Image(getClass().getResourceAsStream("listBK2.png")));
       Group listAllBack = new Group();
       listAllBack.getChildren().addAll(listBk, eventGrid);
       eventPane.setContent(listAllBack);
       
       
       
       //test events
       GridPane events = new GridPane();
       events.add(new Text("new event"), 0, 0);
       events.setPrefSize(90, 25);
       eventGrid.add(events, 0, 1);
       
       GridPane events2 = new GridPane();
       events2.add(new Text("new event2"), 0, 0);
       events2.setPrefSize(90, 25);
       eventGrid.add(events2, 0, 2);
       
       GridPane events3 = new GridPane();
       events3.add(new Text("new event3"), 0, 0);
       events3.setPrefSize(90, 25);
       eventGrid.add(events3, 0, 4);
       
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
       returnCommand = ToDoList.initialize();
       showBox.appendText(returnCommand + "\n");
       
       //catch the motion of users
       MotionCatcher.keyboardCatcher(userCommandBox, showBox);
       MotionCatcher.mouseCatcher(enterKey, userCommandBox, showBox);
   }
}
