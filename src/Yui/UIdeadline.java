package Yui;


import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.Insets;
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
	private static SimpleDateFormat dateFormatDate = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat dateFormatTime = new SimpleDateFormat("HH : mm");
	private static SimpleDateFormat formatCompare = new SimpleDateFormat("yyyyMMdd");
	public GridPane deadlinePane = new GridPane();
	private ImageView commentBk = new ImageView();
	private ImageView nameBk = new ImageView();
	

	public UIdeadline(NumberedEvent numberedEvent) throws MalformedURLException{
		deadline = numberedEvent.getEvent();
		Deadline deadlineCal = deadline.getDeadline();
		Date theDate = deadlineCal.getDeadline();
		String timeString = dateFormatTime.format(theDate);
		String dateString = dateFormatDate.format(theDate);
		String eventName = deadline.getDetail();
		String commentString = deadline.getComment();
		int num = numberedEvent.getIndex();

		GridPane number = new GridPane();
		GridPane time = new GridPane();
		GridPane name = new GridPane();		
		GridPane date = new GridPane();
		GridPane comment = new GridPane();
		
		Image numberImage = new Image(getClass().getResourceAsStream("number.png"));
		Image nameDateTimeImage = new Image(getClass().getResourceAsStream("NTD.png"));
		Image dangerImage = new Image(getClass().getResourceAsStream("danger.png"));
		Image uncommentImage = new Image(getClass().getResourceAsStream("uncomment.png"));
		Image commentImage = new Image(getClass().getResourceAsStream("comment.png"));
		
		ImageView numberBk = new ImageView(numberImage);
		if(isToday(theDate)){
			nameBk = new ImageView(dangerImage);
		} else {
			nameBk = new ImageView(nameDateTimeImage);
		}
		ImageView timeBk = new ImageView(nameDateTimeImage);
		ImageView dateBk = new ImageView(nameDateTimeImage);
		if(!commentString.equals("")){
			commentBk = new ImageView(commentImage);
	    } else {
	    	commentBk = new ImageView(uncommentImage);
	    }
		
		
		Group numberBackg = new Group();
		Group timeBackg = new Group();
		Group nameBackg = new Group();
		Group dateBackg = new Group();
		Group commentBackg = new Group();
		
		numberBackg.getChildren().addAll(numberBk,number);
		timeBackg.getChildren().addAll(timeBk,time);
		nameBackg.getChildren().addAll(nameBk,name);
		dateBackg.getChildren().addAll(dateBk,date);
		commentBackg.getChildren().addAll(commentBk,comment);
		
		deadlinePane.setHgap(1);
	    Text tN = new Text(" " + num);
	    tN.setFont(Font.loadFont(getClass().getResourceAsStream("UI.ttf"), 18));
	    //tN.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tN.setFill(Color.WHITE);
	    number.setPadding(new Insets(4, 1, 1, 5));
	    number.add(tN, 0, 0);
	    
	    Text tT = new Text(timeString);
	    tT.setFont(Font.loadFont(getClass().getResourceAsStream("UI.ttf"), 18));
	    tT.setFill(Color.WHITE);
	    time.setPadding(new Insets(4, 1, 1, 5));
	    time.add(tT, 0, 0);
	    
	    Text tNm = new Text(" " + " " + eventName);
	    //t1.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tNm.setFont(Font.loadFont(getClass().getResourceAsStream("UI.ttf"), 18));
	    tNm.setFill(Color.WHITE);
	    name.setPadding(new Insets(4, 1, 1, 2));
	    name.add(tNm, 0, 0);
	    
	    Text tD = new Text(dateString);
	    //tD.setFont(Font.font ("Agency FB", FontWeight.BOLD, 16));
	    tD.setFont(Font.loadFont(getClass().getResourceAsStream("UI.ttf"), 18));
	    tD.setFill(Color.WHITE);
	    date.setPadding(new Insets(4, 1, 1, 17));
	    date.add(tD, 0, 0);

	    deadlinePane.setPrefSize(380, 25);
	    deadlinePane.add(numberBackg, 0, 0);
	    deadlinePane.add(timeBackg, 1, 0);
	    deadlinePane.add(nameBackg, 2, 0);
	    deadlinePane.add(dateBackg, 3, 0);
	    deadlinePane.add(commentBackg, 4, 0);
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

	public GridPane getDdlBox(){
		return deadlinePane;
	}
}
