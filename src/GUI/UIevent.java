package GUI;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import Fonts.ChineseJudge;
import Image.ImageJudge;
import Tasks.Event;
import Tasks.NumberedEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UIevent {
	private Event events;
	public GridPane eventPane = new GridPane();
	//Event data
	private Date theDate;
	private String timeString;
	private String dateString;
	private String eventName;
	private String commentString;
	private int num;
	//Panes
	private GridPane number;
	private GridPane time;
	private GridPane name;		
	private GridPane date;
	private GridPane comment;
	//ImageView
	private ImageView commentBk = new ImageView();
	private ImageView nameBk = new ImageView();
	private ImageView numberBk = new ImageView();
	private ImageView timeBk = new ImageView();
	private ImageView dateBk = new ImageView();
	//Background
	private Group numberBackg = new Group();
	private Group timeBackg = new Group();
	private Group nameBackg = new Group();
	private Group dateBackg = new Group();
	private Group commentBackg = new Group();
	private final int NAME_MAX_LENGTH = 14;
	private final int SINGLE_BIT_NUMBER = 9;
	private final SimpleDateFormat DATE_FORMATE_DATE = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.ENGLISH);
	private final SimpleDateFormat DATE_FORMATE_TIME = new SimpleDateFormat("HH : mm");
	private static ChineseJudge myChineseJudge;
	private static ImageJudge myImageJudge;
	
	public UIevent(NumberedEvent numberedEvent){
		readEvent(numberedEvent);
		buildGrids();
		buildImageView();
		setBackground();
		eventPane.setHgap(1);
		setNum();
		setTime();
		setName();
		setDate();	
	    
	    addAllElements();
	}

	private void readEvent(NumberedEvent numberedEvent){
		events = numberedEvent.getEvent();
		theDate = events.getEventTime().getStart();
		timeString = DATE_FORMATE_TIME.format(events.getEventTime().getStart()) + " -"
					+ DATE_FORMATE_TIME.format(events.getEventTime().getEnd());
		dateString = DATE_FORMATE_DATE.format(theDate);
		eventName = events.getDetail();
		commentString = events.getComment();
		num = numberedEvent.getIndex();
		myChineseJudge = ChineseJudge.getInstance();
		myImageJudge = ImageJudge.getInstance();
	}
	
	private void buildGrids(){
		number = new GridPane();
		time = new GridPane();
		name = new GridPane();		
		date = new GridPane();
		comment = new GridPane();
	}
	
	private void buildImageView(){
		Image numberImage = new Image(getClass().getResourceAsStream("/Image/number.png"));
		Image nameDateTimeImage = new Image(getClass().getResourceAsStream("/Image/NTD.png"));
		Image dangerImage = new Image(getClass().getResourceAsStream("/Image/danger.png"));
		Image uncommentImage = new Image(getClass().getResourceAsStream("/Image/uncomment.png"));
		Image commentImage = new Image(getClass().getResourceAsStream("/Image/comment.png"));
		
		numberBk = new ImageView(numberImage);
		if(myImageJudge.isToday(theDate)){
			nameBk = new ImageView(dangerImage);
		} else {
			nameBk = new ImageView(nameDateTimeImage);
		}
		timeBk = new ImageView(nameDateTimeImage);
		dateBk = new ImageView(nameDateTimeImage);
		if(myImageJudge.isCommented(commentString)){
			commentBk = new ImageView(commentImage);
	    } else {
	    	commentBk = new ImageView(uncommentImage);
	    }
	}
	
	private void setBackground(){
		numberBackg.getChildren().addAll(numberBk,number);
		timeBackg.getChildren().addAll(timeBk,time);
		nameBackg.getChildren().addAll(nameBk,name);
		dateBackg.getChildren().addAll(dateBk,date);
		commentBackg.getChildren().addAll(commentBk,comment);
	}
	
	private void setNum(){
	    Text tN = new Text(" " + num);
	    tN.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/UI.ttf"), 18));
	    tN.setFill(Color.WHITE);
	    number.setPadding(new Insets(4, 1, 1, 5));
	    if(num > SINGLE_BIT_NUMBER){
	    	number.setPadding(new Insets(4, 1, 1, 1));
	    }
	    number.add(tN, 0, 0);
	}
	
	private void setTime(){
		Text tT = new Text(timeString);
	    tT.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/UI.ttf"), 18));
	    tT.setFill(Color.WHITE);
	    time.setPadding(new Insets(4, 1, 1, 5));
	    time.add(tT, 0, 0);
	}
	
	private void setName(){
		if(eventName.length() > NAME_MAX_LENGTH){
	    	eventName = eventName.substring(0, NAME_MAX_LENGTH);
	    }
	    Text tNm = new Text(" " + " " + eventName);
	    if(myChineseJudge.isContainsChinese(eventName)){
	    	tNm.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/CN.ttf"), 16));
	    } else {
	    	tNm.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/UI.ttf"), 18));
	    }
	    tNm.setFill(Color.WHITE);
	    name.setPadding(new Insets(4, 1, 1, 1));
	    name.add(tNm, 0, 0);
	}
	
	private void setDate(){
		Text tD = new Text(dateString);
	    tD.setFont(Font.loadFont(getClass().getResourceAsStream("/Fonts/UI.ttf"), 18));
	    tD.setFill(Color.WHITE);
	    date.setPadding(new Insets(4, 1, 1, 5));
	    date.add(tD, 0, 0);
	}
	
	private void addAllElements(){
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
