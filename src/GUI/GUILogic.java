package GUI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;

import Tasks.NumberedEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GUILogic {
	private static int X = 0;
	private static int Y = 0;

	public static void showEvents(GridPane eventGrid, ImageView deadline, ImageView eventIcon, ImageView floatingIcon)
			throws IOException, ParseException {
		initialize();
		// deadline icon
		addIcon(eventGrid, deadline);
		addDeadlineBox(eventGrid);
		// event icon
		addIcon(eventGrid, eventIcon);
		addEventBox(eventGrid);
		// floating tasks icon
		addIcon(eventGrid, floatingIcon);
		addFloatingBox(eventGrid);
	}
	
	public static boolean isShowMainGrid(){
		UIBuffer.getIsShowMainGrid();
		return UIBuffer.isShowMainGrid();
	}
	
	private static void initialize() throws IOException, ParseException{
		UIBuffer.getList();
		Y = 0;
	}
	
	private static void addIcon(GridPane taskGrid, ImageView Icon){
		taskGrid.add(Icon, 0, Y);
		Y = Y + 1;
	}
	
	private static void addDeadlineBox(GridPane taskGrid) throws MalformedURLException{
		ArrayList<NumberedEvent> deadlines = UIBuffer.DeadlineList();
		int ddlLength = deadlines.size();
		GridPane deadlineBox = new GridPane();
		deadlineBox.setVgap(1);
		// Y = ddlLength;
		for (int i = 0; i < ddlLength; i++) {
			NumberedEvent thisEvent = deadlines.get(i);
			UIdeadline thisDdlBox = new UIdeadline(thisEvent);
			deadlineBox.add(thisDdlBox.getDdlBox(), X, i);
		}
		taskGrid.add(deadlineBox, 0, Y);
		Y = Y + 1;
	}
	
	private static void addEventBox(GridPane taskGrid) throws MalformedURLException{
		ArrayList<NumberedEvent> events = UIBuffer.EventList();
		int entLength = events.size();
		GridPane eventBox = new GridPane();
		eventBox.setVgap(1);
		for (int i = 0; i < entLength; i++) {
			NumberedEvent thisEvent = events.get(i);
			UIevent thisEntBox = new UIevent(thisEvent);
			eventBox.add(thisEntBox.getEntBox(), X, i);
		}
		taskGrid.add(eventBox, 0, Y);
		Y = Y + 1;
	}
	
	private static void addFloatingBox(GridPane taskGrid) throws MalformedURLException{
		ArrayList<NumberedEvent> floating = UIBuffer.FloatingList();
		int fltLength = floating.size();
		GridPane floatingBox = new GridPane();
		floatingBox.setVgap(1);
		floatingBox.setHgap(1);
		for (int i = 0; i < fltLength; i++) {
			NumberedEvent thisEvent = floating.get(i);
			UIfloating thisEntBox = new UIfloating(thisEvent);
			if (i % 2 == 0) {
				X = 0;
			} else {
				X = 1;
			}
			int y = i / 2;
			floatingBox.add(thisEntBox.getFltBox(), X, y);
		}
		taskGrid.add(floatingBox, 0, Y);
	}
}
