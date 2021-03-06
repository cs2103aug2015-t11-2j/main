//@@author A0133992X
package GUI;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

public class UITask {
	private Event thisTask;
	public GridPane TaskPane = new GridPane();
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
	private final int NAME_MAX_LENGTH_S = 15;
	private final int NAME_MAX_LENGTH_L = 20;
	private final int SINGLE_BIT_NUMBER = 9;
	private final String REGEX = "[\u4e00-\u9fa5]";
	private final Pattern PAT = Pattern.compile(REGEX);
	private final SimpleDateFormat DATE_FORMATE_DATE = new SimpleDateFormat("EEE, dd/MM/yyyy", Locale.ENGLISH);
	private final SimpleDateFormat DATE_FORMATE_TIME = new SimpleDateFormat("HH : mm");
	private final SimpleDateFormat FORMATE_COMPARE = new SimpleDateFormat("yyyyMMdd");
	private final int TYPE_DEADLINE_OR_EVERNT = 0;
	private final int TYPE_FLOATING = 1;
	private int type;
	
	public UITask(NumberedEvent numberedEvent) throws MalformedURLException{
		readEvent(numberedEvent);
		buildGrid(type);
		buildImageView(type);
		setBackground(type);
		TaskPane.setHgap(1);
		setNum();	
		setName(type);
		if(type == TYPE_DEADLINE_OR_EVERNT){
			setTime();
			setDate();
		}	
	    addAllElements(type);
	}
	
	private void readEvent(NumberedEvent numberedEvent){
		thisTask = numberedEvent.getEvent();
		typeJudge(thisTask);
		eventName = thisTask.getDetail();
		commentString = thisTask.getComment();
		num = numberedEvent.getIndex();
	}
	
	private void typeJudge(Event thisTask){
		if(!(thisTask.getDeadline() == null) ||!(thisTask.getEventTime() == null)){
			if(!(thisTask.getDeadline() == null)){
				readDeadline();
			}
			if(!(thisTask.getEventTime() == null)){
				readEvent();
			}
			dateString = DATE_FORMATE_DATE.format(theDate);
			type = TYPE_DEADLINE_OR_EVERNT;
		} else {
			type = TYPE_FLOATING;
		}
	}
	
	private void readDeadline(){
		theDate = thisTask.getDeadline().getDeadline();
		timeString = DATE_FORMATE_TIME.format(theDate);
	}
	
	private void readEvent(){
		theDate = thisTask.getEventTime().getStart();
		timeString = DATE_FORMATE_TIME.format(thisTask.getEventTime().getStart()) + " -"
					+ DATE_FORMATE_TIME.format(thisTask.getEventTime().getEnd());
	}
	
	private void buildGrid(int type){
		if(type == TYPE_DEADLINE_OR_EVERNT){
			time = new GridPane();
			date = new GridPane();
		}
		number = new GridPane();
		name = new GridPane();		
		comment = new GridPane();
	}
	
	private void buildImageView(int type){
		if(type == TYPE_DEADLINE_OR_EVERNT){
			Image nameDateTimeImage = new Image(getClass().getResourceAsStream("/Image/NTD.png"));
			Image dangerImage = new Image(getClass().getResourceAsStream("/Image/danger.png"));
			if(isToday(theDate)){
				nameBk = new ImageView(dangerImage);
			} else {
				nameBk = new ImageView(nameDateTimeImage);
			}
			timeBk = new ImageView(nameDateTimeImage);
			dateBk = new ImageView(nameDateTimeImage);
		}
		if(type == TYPE_FLOATING){
			Image nameImage = new Image(getClass().getResourceAsStream("/Image/memoPad.png"));
			nameBk = new ImageView(nameImage);
		}
		Image numberImage = new Image(getClass().getResourceAsStream("/Image/number.png"));
		Image uncommentImage = new Image(getClass().getResourceAsStream("/Image/uncomment.png"));
		Image commentImage = new Image(getClass().getResourceAsStream("/Image/comment.png"));
		
		numberBk = new ImageView(numberImage);
		if(isCommented(commentString)){
			commentBk = new ImageView(commentImage);
	    } else {
	    	commentBk = new ImageView(uncommentImage);
	    }
	}
	
	private void setBackground(int type){
		if(type == TYPE_DEADLINE_OR_EVERNT){
			timeBackg.getChildren().addAll(timeBk,time);
			dateBackg.getChildren().addAll(dateBk,date);
		}
		numberBackg.getChildren().addAll(numberBk,number);
		nameBackg.getChildren().addAll(nameBk,name);
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
	
	private void setName(int type){
		int nameMaxLength;
		if(type == TYPE_DEADLINE_OR_EVERNT){
			nameMaxLength = NAME_MAX_LENGTH_S;
		} else {
			nameMaxLength = NAME_MAX_LENGTH_L;
		}
		if(eventName.length() > nameMaxLength){
	    	eventName = eventName.substring(0, nameMaxLength) + "..";
	    }
	    Text tNm = new Text(" " + " " + eventName);
	    if(isContainsChinese(eventName)){
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
	
	private void addAllElements(int type){
		if(type == TYPE_DEADLINE_OR_EVERNT){
			TaskPane.setPrefSize(380, 25);
			TaskPane.add(numberBackg, 0, 0);
			TaskPane.add(timeBackg, 1, 0);
			TaskPane.add(nameBackg, 2, 0);
			TaskPane.add(dateBackg, 3, 0);
			TaskPane.add(commentBackg, 4, 0);
		} else {
			TaskPane.setPrefSize(190, 25);
		    TaskPane.add(numberBackg, 0, 0);
		    TaskPane.add(nameBackg, 1, 0);
		    TaskPane.add(commentBackg, 2, 0);
		}
	}
	
	private boolean isToday(Date theDate){
		Date today = new Date();
		String theDateString = FORMATE_COMPARE.format(theDate);
		String todayString = FORMATE_COMPARE.format(today);
		if(theDateString.equals(todayString)){
			return true;
		}
		return false;
	}
	
	private boolean isCommented(String comment){
		return !comment.equals("");
	}
	
	private boolean isContainsChinese(String str){
		Matcher matcher = PAT.matcher(str);
		boolean flg = false;
		if(matcher.find()){
			flg = true;
		}
		return flg;
	}
	
	public GridPane getTaskBox(){
		return TaskPane;
	}
}
