package Yui;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.NumberedEvent;
import main.ToDoList;

public class GUILogic {
	private static int X = 0;
	private static int Y = 0;
	private GridPane floatingBox;
	public void showEvents(GridPane eventGrid) throws IOException, ParseException{
		//deadline icon
	    ImageView deadline = new ImageView(new Image(getClass().getResourceAsStream("deadline.png")));
	    eventGrid.add(deadline, 0, 0);
	       
	    //add deadlines
	    ArrayList<NumberedEvent> deadlines = ToDoList.getDealine();
	    int ddlLength = deadlines.size();
	    Y = ddlLength;
	    for(int i = 0; i < Y; i++){
	    	NumberedEvent thisEvent = deadlines.get(i);
	    	UIdeadline thisDdlBox = new UIdeadline(thisEvent);
	    	eventGrid.add(thisDdlBox.getDdlBox(), 0, i + 1);
	    }
	    Y = Y + 1;
	    
	    //event icon
	    ImageView eventIcon = new ImageView(new Image(getClass().getResourceAsStream("event.png")));
	    eventGrid.add(eventIcon, 0, Y);
	    Y = Y + 1;
	    
	    //add events
	    ArrayList<NumberedEvent> events = ToDoList.getEventTime();
	    int entLength = events.size();
	    for(int i = 0; i < entLength; i++){
	    	NumberedEvent thisEvent = events.get(i);
	    	UIevent thisEntBox = new UIevent(thisEvent);
	    	eventGrid.add(thisEntBox.getEntBox(), 0, Y + i);
	    }
	    Y = Y + entLength + 1;
	       
	    //floating tasks icon
	    ImageView floatingIcon = new ImageView(new Image(getClass().getResourceAsStream("floating.png")));
	    eventGrid.add(floatingIcon, 0, Y);
	    Y = Y + 1;
	    
	    //add floating
	    ArrayList<NumberedEvent> floating = ToDoList.getFloating();
	    int fltLength = floating.size();
	    floatingBox = null;
	    for(int i = 0; i < fltLength; i++){
	    	NumberedEvent thisEvent = floating.get(i);
	    	UIfloating thisEntBox = new UIfloating(thisEvent);
	    	if( i%2 == 0){
	    		X = 0;
	    	} else {
	    		X = 1;
	    	}
	    	floatingBox.add(thisEntBox.getFltBox(), X, i/2);
	    }
	    eventGrid.add(floatingBox, 0, Y);
	}
}
