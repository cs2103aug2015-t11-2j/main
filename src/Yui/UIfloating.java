package Yui;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.Event;
import main.NumberedEvent;

public class UIfloating {
	private static Event floating;
	public Group ddlBackg = new Group();
	public UIfloating(NumberedEvent numberedEvent){
		floating = numberedEvent.getEvent();
		String eventName = floating.getDetail();
		int num = numberedEvent.getIndex();
		
		GridPane fltEvent = new GridPane();
	    fltEvent.setAccessibleText(num+eventName);
	    Text tN = new Text(" " + num + " ");
	    tN.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tN.setFill(Color.WHITE);
	    fltEvent.add(tN, 0, 0);
	    Text tNm = new Text(eventName);
	    tNm.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tNm.setFill(Color.WHITE);
	    fltEvent.add(tNm, 1, 0);
	    fltEvent.setPrefSize(135, 20); 
	       
	    Image deadlineBk = new Image(getClass().getResourceAsStream("greenEvent-half.png"));
	    ImageView ddlBk = new ImageView(deadlineBk);
	    ddlBackg.getChildren().addAll(ddlBk,fltEvent);
	}
	
	public Group getFltBox(){
		return ddlBackg;
	}
}
