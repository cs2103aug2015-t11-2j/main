package Yui;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.Event;
import main.NumberedEvent;

public class UIfloating {
	private static Event floating;
	public GridPane memoPane = new GridPane();
	private ImageView commentBk = new ImageView();
	private ImageView nameBk = new ImageView();
	private static String REGEX = "[\u4e00-\u9fa5]";
	private static Pattern PAT = Pattern.compile(REGEX);
	
	public UIfloating(NumberedEvent numberedEvent){
		floating = numberedEvent.getEvent();
		String eventName = floating.getDetail();
		String commentString = floating.getComment();
		int num = numberedEvent.getIndex();
		
		GridPane number = new GridPane();
		GridPane name = new GridPane();		
		GridPane comment = new GridPane();
		
		Image numberImage = new Image(getClass().getResourceAsStream("number.png"));
		Image nameImage = new Image(getClass().getResourceAsStream("memoPad.png"));
		Image uncommentImage = new Image(getClass().getResourceAsStream("uncomment.png"));
		Image commentImage = new Image(getClass().getResourceAsStream("comment.png"));
		
		ImageView numberBk = new ImageView(numberImage);
		nameBk = new ImageView(nameImage);
		if(!commentString.equals("")){
			commentBk = new ImageView(commentImage);
	    } else {
	    	commentBk = new ImageView(uncommentImage);
	    }
		
		
		Group numberBackg = new Group();
		Group nameBackg = new Group();
		Group commentBackg = new Group();
		
		numberBackg.getChildren().addAll(numberBk,number);
		nameBackg.getChildren().addAll(nameBk,name);
		commentBackg.getChildren().addAll(commentBk,comment);
		
		memoPane.setHgap(1);
	    Text tN = new Text(" " + num);
	    tN.setFont(Font.loadFont(getClass().getResourceAsStream("UI.ttf"), 18));
	    tN.setFill(Color.WHITE);
	    number.setPadding(new Insets(4, 1, 1, 5));
	    number.add(tN, 0, 0);
	    
	    Text tNm = new Text(" " + " " + eventName);
	    if(isContainsChinese(eventName)){
	    	tNm.setFont(Font.loadFont(getClass().getResourceAsStream("Fonts/CN.ttf"), 18));
	    } else {
	    	tNm.setFont(Font.loadFont(getClass().getResourceAsStream("UI.ttf"), 18));
	    }
	    tNm.setFill(Color.WHITE);
	    name.setPadding(new Insets(4, 1, 1, 1));
	    name.add(tNm, 0, 0);

	    memoPane.setPrefSize(190, 25);
	    memoPane.add(numberBackg, 0, 0);
	    memoPane.add(nameBackg, 1, 0);
	    memoPane.add(commentBackg, 2, 0);
	}
	
	public static boolean isContainsChinese(String str){
		Matcher matcher = PAT.matcher(str);
		boolean flg = false;
		if(matcher.find()){
			flg = true;
		}
		return flg;
	}
	
	public GridPane getFltBox(){
		return memoPane;
	}
}
