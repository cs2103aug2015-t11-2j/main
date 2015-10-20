package Yui;

import java.text.SimpleDateFormat;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.Deadline;
import main.Event;
import main.NumberedEvent;

public class UIdeadline {
	private static Event deadline;
	private static SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	public Group ddlBackg = new Group();
	
	public UIdeadline(NumberedEvent numberedEvent){
		deadline = numberedEvent.getEvent();
		Deadline deadlineCal = deadline.getDeadline();
		String ddlString = date_format.format(deadlineCal.getDeadline().getTime());
		String eventName = deadline.getDetail();
		int num = numberedEvent.getIndex();
		
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
	    Text tD = new Text(" " + ddlString + " ");
	    tD.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tD.setFill(Color.WHITE);
	    deadlineEvent.add(tD, 2, 0);
	    /*
	    Text tT = new Text(timeString);
	    tT.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tT.setFill(Color.WHITE);
	    deadlineEvent.add(tT, 3, 0);
	    */
	    deadlineEvent.setPrefSize(273, 20); 
	       
	    Image deadlineBk = new Image(getClass().getResourceAsStream("commonEvent.png"));
	    ImageView ddlBk = new ImageView(deadlineBk);
	    ddlBackg.getChildren().addAll(ddlBk,deadlineEvent);
	}
	
	public Group getDdlBox(){
		return ddlBackg;
	}
}
