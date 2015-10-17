package Yui;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DragController {
	public static void dragStage(GridPane grid, Stage primaryStage){
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
