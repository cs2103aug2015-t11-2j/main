package Yui;


import java.text.SimpleDateFormat;
import java.util.Date;

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
	private static SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	private static SimpleDateFormat formatCompare = new SimpleDateFormat("yyyyMMdd");

	public UIevent(NumberedEvent numberedEvent){
		events = numberedEvent.getEvent();
		Date theDate = events.getEventTime().getStart();
		String startString = dateFormat1.format(events.getEventTime().getStart()) + " -" + " ";
		String endString = dateFormat2.format(events.getEventTime().getEnd());
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

	    Image deadlineBkCom = new Image(getClass().getResourceAsStream("commonEvent.png"));
	    Image deadlineBkNear = new Image(getClass().getResourceAsStream("redEvent.png"));
	    ImageView ddlBk = new ImageView();
	    if(isToday(theDate)){
	    	ddlBk.setImage(deadlineBkNear);
	    } else {
	    	ddlBk.setImage(deadlineBkCom);
	    }

	    ddlBackg.getChildren().addAll(ddlBk,seEvent);
	}

	private boolean isToday(Date theDate){
		Date today = new Date();
		String theDateString = formatCompare.format(theDate);
		String todayString = formatCompare.format(today);
		if(theDateString.equals(todayString)){
			return true;
		}
		return false;
	}

	public Group getEntBox(){
		return ddlBackg;
	}
}
