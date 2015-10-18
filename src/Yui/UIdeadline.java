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

public class UIdeadline {
	private static Event deadline;
	public Group ddlBackg = new Group();
	public UIdeadline(Event event){
		deadline = event;
		Date thisDate = deadline.getDeadline().getDate();
		Time thisTime = deadline.getDeadline().getTime();
		String dataString = thisDate.toString();
		String timeString = thisTime.toString();
		String eventName = deadline.getDetail();
		String num = deadline.getNumber();
		
		GridPane deadlineEvent = new GridPane();
	    deadlineEvent.setAccessibleText(num+eventName);
	    Text tN = new Text(" " + num + " ");
	    tN.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tN.setFill(Color.WHITE);
	    deadlineEvent.add(tN, 0, 0);
	    Text t1 = new Text(eventName);
	    t1.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    t1.setFill(Color.WHITE);
	    deadlineEvent.add(t1, 1, 0);
	    Text tD = new Text(" " + dataString + " ");
	    tD.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tD.setFill(Color.WHITE);
	    deadlineEvent.add(tD, 2, 0);
	    Text tT = new Text(timeString);
	    tT.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tT.setFill(Color.WHITE);
	    deadlineEvent.add(tT, 3, 0);
	    deadlineEvent.setPrefSize(273, 20); 
	       
	    Image deadlineBk = new Image(getClass().getResourceAsStream("commonEvent.png"));
	    ImageView ddlBk = new ImageView(deadlineBk);
	    ddlBackg.getChildren().addAll(ddlBk,deadlineEvent);
	}
	
	public Group getDdlBox(){
		return ddlBackg;
	}
}
