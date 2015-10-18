package Yui;

import java.sql.Date;
import java.sql.Time;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.Event;

public class UIevent {
	
	private static Event events;
	public Group ddlBackg = new Group();
	public UIevent(Event event){
		events = event;
		Date startDate = events.getEventTime().getStartDate();
		Time startTime = events.getEventTime().getStartTime();
		Date endDate = events.getEventTime().getEndDate();
		Time endTime = events.getEventTime().getEndTime();
		String startString = startDate.toString() + " " + startTime.toString() + " -" + " ";
		String endString = endDate.toString() + " " + endTime.toString();
		String eventName = events.getDetail();
		String num = events.getNumber();
		
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
