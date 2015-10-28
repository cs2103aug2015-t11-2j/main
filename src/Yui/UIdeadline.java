package Yui;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Deadline;
import main.Event;
import main.NumberedEvent;

public class UIdeadline {
	private static Event deadline;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	private static SimpleDateFormat formatCompare = new SimpleDateFormat("yyyyMMdd");
	public Group ddlBackg = new Group();

	public UIdeadline(NumberedEvent numberedEvent) throws MalformedURLException{
		deadline = numberedEvent.getEvent();
		Deadline deadlineCal = deadline.getDeadline();
		Date theDate = deadlineCal.getDeadline();
		String ddlString = dateFormat.format(theDate);
		String eventName = deadline.getDetail();
		int num = numberedEvent.getIndex();

		GridPane deadlineEvent = new GridPane();
	    deadlineEvent.setAccessibleText(num+eventName);
	    Text tN = new Text(" " + num + " ");
	    tN.setFont(Font.loadFont(new File("UI.otf").toURI().toURL().toString(), 16));
	    //tN.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tN.setFill(Color.GRAY);
	    deadlineEvent.add(tN, 0, 0);
	    Text t1 = new Text(eventName);
	    //t1.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    t1.setFont(Font.loadFont(new File("UI.otf").toURI().toURL().toString(), 16));
	    t1.setFill(Color.GRAY);
	    deadlineEvent.add(t1, 1, 0);
	    Text tD = new Text(" " + ddlString + " ");
	    //tD.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tD.setFont(Font.loadFont(new File("UI.otf").toURI().toURL().toString(), 16));
	    tD.setFill(Color.GRAY);
	    deadlineEvent.add(tD, 1, 1);
	    /*
	    Text tT = new Text(timeString);
	    tT.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tT.setFill(Color.WHITE);
	    deadlineEvent.add(tT, 3, 0);
	    */
	    deadlineEvent.setPrefSize(125, 39);

	    Image deadlineBkCom = new Image(getClass().getResourceAsStream("cmEvent2.png"));
	    Image deadlineBkNear = new Image(getClass().getResourceAsStream("redEvent.png"));
	    ImageView ddlBk = new ImageView();
	    if(isToday(theDate)){
	    	tN.setFill(Color.WHITE);
	    	t1.setFill(Color.WHITE);
	    	tD.setFill(Color.WHITE);
	    	ddlBk.setImage(deadlineBkNear);
	    } else {
	    	ddlBk.setImage(deadlineBkCom);
	    }

	    ddlBackg.getChildren().addAll(ddlBk,deadlineEvent);
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

	public Group getDdlBox(){
		return ddlBackg;
	}
}
