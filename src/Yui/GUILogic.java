package Yui;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import main.Event;
import main.ToDoList;

public class GUILogic {
	private static int X = 0;
	private static int Y = 0;
	public void showEvents(GridPane eventGrid){
		//deadline icon
	    ImageView deadline = new ImageView(new Image(getClass().getResourceAsStream("deadline.png")));
	    eventGrid.add(deadline, 0, 0);
	       
	    //add deadlines
	    ArrayList<Event> deadlines = ToDoList.getDealine();
	    int ddlLength = deadlines.size();
	    Y = ddlLength;
	    for(int i = 0; i < Y; i++){
	    	Event thisEvent = deadlines.get(i);
	    	UIdeadline thisDdlBox = new UIdeadline(thisEvent);
	    	eventGrid.add(thisDdlBox.getDdlBox(), 0, i + 1);
	    }
	    Y = Y + 1;
	    
	    //event icon
	    ImageView eventIcon = new ImageView(new Image(getClass().getResourceAsStream("event.png")));
	    eventGrid.add(eventIcon, 0, Y);
	    Y = Y + 1;
	    
	    //add events
	    ArrayList<Event> events = ToDoList.getEvents();
	    int entLength = events.size();
	    for(int i = 0; i < entLength; i++){
	    	Event thisEvent = deadlines.get(i);
	    	UIdeadline thisEntBox = new UIdeadline(thisEvent);
	    	eventGrid.add(thisEntBox.getDdlBox(), 0, Y + i);
	    }
	    Y = Y + entLength + 1;
	       
	    //floating tasks icon
	    ImageView floatingIcon = new ImageView(new Image(getClass().getResourceAsStream("floating.png")));
	    eventGrid.add(floatingIcon, 0, Y);
	    Y = Y + 1;
	}
}
