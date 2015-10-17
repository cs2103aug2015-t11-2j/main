package Yui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import main.ToDoList;

public class Yui_GUI extends Application{
	protected static String userCommand;
	protected static String returnCommand;
	private double xOffset = 0;
    private double yOffset = 0;


   public static void main(String[] args) {
       launch(args);
    }

   @Override
   public void start(final Stage primaryStage) throws IOException {
       primaryStage.setTitle("Yui");
       primaryStage.initStyle(StageStyle.UNDECORATED);
       primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
       primaryStage.show();

       GridPane grid = new GridPane();
       grid.setHgap(5);
       grid.setVgap(5);
       grid.setPadding(new Insets(10, 5, 5, 25));

       Image background = new Image(getClass().getResourceAsStream("uiground.png"));
       ImageView bk = new ImageView(background);


       Image imageExit = new Image(getClass().getResourceAsStream("exit.png"));
       ImageView exit = new ImageView(imageExit);
       exit.setOnMouseClicked(new EventHandler<MouseEvent>(){
    	   @Override
    	   public void handle(MouseEvent event) {
    		   Event.fireEvent(primaryStage, new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST ));
    		   }
    	   });
       grid.add(exit, 4, 0);

       Image iconYui = new Image(getClass().getResourceAsStream("logo.png"));
       grid.add(new ImageView(iconYui), 0, 0);

       Image menu1 = new Image(getClass().getResourceAsStream("menu.png"));
       grid.add(new ImageView(menu1), 4, 2);

       Image enter = new Image(getClass().getResourceAsStream("ok.png"));
       ImageView enterKey = new ImageView(enter);
       grid.add(enterKey, 4, 3);

       grid.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               xOffset = event.getSceneX();
               yOffset = event.getSceneY();
           }
       });

       grid.setOnMouseDragged(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               primaryStage.setX(event.getScreenX() - xOffset);
               primaryStage.setY(event.getScreenY() - yOffset);
           }
       });

       Group backg = new Group();
       backg.getChildren().addAll(bk,grid);

       Scene scene = new Scene(backg, 600, 400);
       primaryStage.setScene(scene);

       final TextArea showBox = new TextArea();
       showBox.setPrefSize(490, 275);
       grid.add(showBox, 0, 2);
       showBox.setEditable(false);

       final TextField userCommandBox = new TextField();
       grid.add(userCommandBox, 0, 3);
       userCommandBox.requestFocus();

       returnCommand = ToDoList.initialize();
       showBox.appendText(returnCommand + "\n");

       MotionCatcher.keyboardCatcher(userCommandBox, showBox);
       MotionCatcher.mouseCatcher(enterKey, userCommandBox, showBox);
   }
}
