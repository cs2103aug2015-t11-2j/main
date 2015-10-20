package Yui;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.NumberedEvent;
import main.ToDoList;

public class GUILogic {
	private static int X = 0;
	private static int Y = 0;
	
	public static void showEvents(GridPane eventGrid, ImageView deadline, ImageView eventIcon, ImageView floatingIcon) throws IOException, ParseException{
		//deadline icon
	    eventGrid.add(deadline, 0, 0);
	    //Y = Y + 1;
	    
	    //add deadlines
	    /*ArrayList<NumberedEvent> deadlines = ToDoList.getDealine();
	    int ddlLength = deadlines.size();
	    Y = ddlLength;
	    if(ddlLength > 0){
	    for(int i = 0; i < Y; i++){
	    	NumberedEvent thisEvent = deadlines.get(i);
	    	UIdeadline thisDdlBox = new UIdeadline(thisEvent);
	    	eventGrid.add(thisDdlBox.getDdlBox(), 0, i + 1);
	    }
	    }*/
	    Y = Y + 1;
	    
	    //event icon
	    eventGrid.add(eventIcon, 0, Y);
	    Y = Y + 1;
	    /*
	    //add events
	    ArrayList<NumberedEvent> events = ToDoList.getEventTime();
	    int entLength = events.size();
	    for(int i = 0; i < entLength; i++){
	    	NumberedEvent thisEvent = events.get(i);
	    	UIevent thisEntBox = new UIevent(thisEvent);
	    	eventGrid.add(thisEntBox.getEntBox(), 0, Y + i);
	    }
	    Y = Y + entLength + 1;
	    */  
	    //floating tasks icon
	    eventGrid.add(floatingIcon, 0, Y);
	    Y = Y + 1;
	    
	    //add floating
	    ArrayList<NumberedEvent> floating = ToDoList.getFloating();
	    int fltLength = floating.size();
	    GridPane floatingBox = new GridPane();
	    floatingBox.setHgap(1);
	    for(int i = 0; i < fltLength; i++){
	    	NumberedEvent thisEvent = floating.get(i);
	    	UIfloating thisEntBox = new UIfloating(thisEvent);
	    	if( i%2 == 0){
	    		X = 0;
	    	} else {
	    		X = 1;
	    	}
	    	int y = i/2;
	    	floatingBox.add(thisEntBox.getFltBox(), X, y);
	    }
	    eventGrid.add(floatingBox, 0, Y);
	    
	}
}
