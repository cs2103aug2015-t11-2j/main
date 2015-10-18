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

public class UIfloating {
	private static Event floating;
	public Group ddlBackg = new Group();
	public UIfloating(Event event){
		floating = event;
		String eventName = floating.getDetail();
		String num = floating.getNumber();
		
		GridPane fltEvent = new GridPane();
	    fltEvent.setAccessibleText(num+eventName);
	    Text tN = new Text(" " + num + " ");
	    tN.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tN.setFill(Color.WHITE);
	    fltEvent.add(tN, 0, 0);
	    Text tNm = new Text(eventName);
	    tNm.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tNm.setFill(Color.WHITE);
	    fltEvent.setPrefSize(135, 20); 
	       
	    Image deadlineBk = new Image(getClass().getResourceAsStream("greenEvent.png"));
	    ImageView ddlBk = new ImageView(deadlineBk);
	    ddlBackg.getChildren().addAll(ddlBk,fltEvent);
	}
	
	public Group getEntBox(){
		return ddlBackg;
	}
}
