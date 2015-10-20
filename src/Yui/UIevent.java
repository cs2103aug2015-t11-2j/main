package Yui;


import java.text.SimpleDateFormat;
import java.util.Calendar;

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

public class UIevent {
	
	private static Event events;
	public Group ddlBackg = new Group();
	private static SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	
	public UIevent(NumberedEvent numberedEvent){
		events = numberedEvent.getEvent();
		Calendar startCal = events.getEventTime().getStart();
		Calendar endCal = events.getEventTime().getEnd();
		String startString = date_format.format(startCal.getTime()) + " -" + " ";
		String endString = date_format.format(endCal.getTime());
		String eventName = events.getDetail();
		int num = numberedEvent.getIndex();
		
		GridPane seEvent = new GridPane();
		seEvent.setAccessibleText(num+eventName);
	    Text tN = new Text(" " + num + " ");
	    tN.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tN.setFill(Color.WHITE);
	    seEvent.add(tN, 0, 0);
	    Text tNm = new Text(eventName);
	    tNm.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tNm.setFill(Color.WHITE);
	    seEvent.add(tNm, 1, 0);
	    Text tS = new Text(" " + startString + " ");
	    tS.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tS.setFill(Color.WHITE);
	    seEvent.add(tS, 2, 0);
	    Text tE = new Text(endString);
	    tE.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tE.setFill(Color.WHITE);
	    seEvent.add(tE, 3, 0);
	    seEvent.setPrefSize(273, 20); 
	       
	    Image deadlineBk = new Image(getClass().getResourceAsStream("commonEvent.png"));
	    ImageView ddlBk = new ImageView(deadlineBk);
	    ddlBackg.getChildren().addAll(ddlBk,seEvent);
	}
	
	public Group getEntBox(){
		return ddlBackg;
	}
}
