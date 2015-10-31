package Yui;


import java.text.SimpleDateFormat;
import java.util.Date;

import Fonts.ChineseJudge;
import Image.ImageJudge;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Event;
import main.NumberedEvent;

public class UIevent {
	private static Event events;
	private static SimpleDateFormat dateFormatDate = new SimpleDateFormat("dd/MM/yyyy");
	private static SimpleDateFormat dateFormatTime = new SimpleDateFormat("HH : mm");
	public GridPane eventPane = new GridPane();
	private ImageView commentBk = new ImageView();
	private ImageView nameBk = new ImageView();
	private static int NAME_MAX_LENGTH = 14;
	private static int SINGLE_BIT_NUMBER = 9;

	public UIevent(NumberedEvent numberedEvent){
		events = numberedEvent.getEvent();
		Date theDate = events.getEventTime().getStart();
		String timeString = dateFormatTime.format(events.getEventTime().getStart()) + " -"
		 					+ dateFormatTime.format(events.getEventTime().getEnd());
		String dateString = dateFormatDate.format(events.getEventTime().getEnd());
		String eventName = events.getDetail();
		String commentString = events.getComment();
		int num = numberedEvent.getIndex();

		GridPane number = new GridPane();
		GridPane time = new GridPane();
		GridPane name = new GridPane();		
		GridPane date = new GridPane();
		GridPane comment = new GridPane();
		
		Image numberImage = new Image(getClass().getResourceAsStream("/Image/number.png"));
		Image nameDateTimeImage = new Image(getClass().getResourceAsStream("/Image/NTD.png"));
		Image dangerImage = new Image(getClass().getResourceAsStream("/Image/danger.png"));
		Image uncommentImage = new Image(getClass().getResourceAsStream("/Image/uncomment.png"));
		Image commentImage = new Image(getClass().getResourceAsStream("/Image/comment.png"));
		
		ImageView numberBk = new ImageView(numberImage);
		if(ImageJudge.isToday(theDate)){
			nameBk = new ImageView(dangerImage);
		} else {
			nameBk = new ImageView(nameDateTimeImage);
		}
		ImageView timeBk = new ImageView(nameDateTimeImage);
		ImageView dateBk = new ImageView(nameDateTimeImage);
		if(ImageJudge.isCommented(commentString)){
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
		
		eventPane.setHgap(1);
	    Text tN = new Text(" " + num);
	    tN.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/UI.ttf"), 18));
	    tN.setFill(Color.WHITE);
	    number.setPadding(new Insets(4, 1, 1, 5));
	    if(num > SINGLE_BIT_NUMBER){
	    	number.setPadding(new Insets(4, 1, 1, 1));
	    }
	    number.add(tN, 0, 0);
	    
	    Text tT = new Text(timeString);
	    tT.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/UI.ttf"), 18));
	    tT.setFill(Color.WHITE);
	    time.setPadding(new Insets(4, 1, 1, 5));
	    time.add(tT, 0, 0);
	    
	    if(eventName.length() > NAME_MAX_LENGTH){
	    	eventName = eventName.substring(0, NAME_MAX_LENGTH);
	    }
	    Text tNm = new Text(" " + " " + eventName);
	    if(ChineseJudge.isContainsChinese(eventName)){
	    	tNm.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/CN.ttf"), 16));
	    } else {
	    	tNm.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/UI.ttf"), 18));
	    }
	    tNm.setFill(Color.WHITE);
	    name.setPadding(new Insets(4, 1, 1, 1));
	    name.add(tNm, 0, 0);
	    
	    Text tD = new Text(dateString);
	    tD.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/UI.ttf"), 18));
	    tD.setFill(Color.WHITE);
	    date.setPadding(new Insets(4, 1, 1, 17));
	    date.add(tD, 0, 0);

	    eventPane.setPrefSize(380, 25);
	    eventPane.add(numberBackg, 0, 0);
	    eventPane.add(timeBackg, 1, 0);
	    eventPane.add(nameBackg, 2, 0);
	    eventPane.add(dateBackg, 3, 0);
	    eventPane.add(commentBackg, 4, 0);
	}

	public GridPane getEntBox(){
		return eventPane;
	}
}
