package Yui;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Fonts.ChineseJudge;
import main.Event;
import main.NumberedEvent;

public class UIfloating {
	private static Event floating;
	public GridPane memoPane = new GridPane();
	//Event data
	private String eventName;
	private String commentString;
	private int num;
	//Panes
	private GridPane number;
	private GridPane name;		
	private GridPane comment;
	//ImageView
	private ImageView commentBk = new ImageView();
	private ImageView nameBk = new ImageView();
	private ImageView numberBk = new ImageView();
	//Background
	private Group numberBackg = new Group();
	private Group nameBackg = new Group();
	private Group commentBackg = new Group();
	private static int NAME_MAX_LENGTH = 17;
	private static int SINGLE_BIT_NUMBER = 9;
	
	public UIfloating(NumberedEvent numberedEvent){
		readEvent(numberedEvent);
		buildGrids();
		buildImageView();
		setBackground();
		memoPane.setHgap(1);
		setNum();
		setName();
	    
	    addAllElements();
	}
	
	private void readEvent(NumberedEvent numberedEvent){
		floating = numberedEvent.getEvent();
		eventName = floating.getDetail();
		commentString = floating.getComment();
		num = numberedEvent.getIndex();
	}
	
	private void buildGrids(){
		number = new GridPane();
		name = new GridPane();		
		comment = new GridPane();
	}
	
	private void buildImageView(){
		Image numberImage = new Image(getClass().getResourceAsStream("/Image/number.png"));
		Image nameImage = new Image(getClass().getResourceAsStream("/Image/memoPad.png"));
		Image uncommentImage = new Image(getClass().getResourceAsStream("/Image/uncomment.png"));
		Image commentImage = new Image(getClass().getResourceAsStream("/Image/comment.png"));
		
		numberBk = new ImageView(numberImage);
		nameBk = new ImageView(nameImage);
		if(!commentString.equals("")){
			commentBk = new ImageView(commentImage);
	    } else {
	    	commentBk = new ImageView(uncommentImage);
	    }
	}
	
	private void setBackground(){
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
	
	private void setName(){
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
	}
	
	private void addAllElements(){
		memoPane.setPrefSize(190, 25);
	    memoPane.add(numberBackg, 0, 0);
	    memoPane.add(nameBackg, 1, 0);
	    memoPane.add(commentBackg, 2, 0);
	}
	
	public GridPane getFltBox(){
		return memoPane;
	}
}
