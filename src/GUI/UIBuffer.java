package GUI;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import Logic.ToDoList;
import Tasks.NumberedEvent;

public class UIBuffer {
	private ArrayList<NumberedEvent> DeadLineList;
	private ArrayList<NumberedEvent> EventList;
	private ArrayList<NumberedEvent> FloatingList;
	private String returnedCommand;
	private boolean isShowMainGrid;
	private String theme;
	private static UIBuffer theUIBuffer;
	
	private UIBuffer(){
	}
	
	protected static UIBuffer getInstance(){
		if(theUIBuffer == null){
			theUIBuffer = new UIBuffer();
		}
		return theUIBuffer;
	}
	
	private void getDealineList() throws IOException, ParseException{
		DeadLineList = ToDoList.getDealine();
	}
	
	private void getEventList() throws IOException, ParseException{
		EventList = ToDoList.getEventTime();
	}
	
	private void getFloatingList() throws IOException, ParseException{
		FloatingList = ToDoList.getFloating();
	}
	
	protected void getTheme(){
		theme = ToDoList.getTheme();
	}
	
	public void getList() throws IOException, ParseException{
		getDealineList();
		getEventList();
		getFloatingList();
	}
	
	public ArrayList<NumberedEvent> DeadlineList(){
		return DeadLineList;
	}
	
	public ArrayList<NumberedEvent> EventList(){
		return EventList;
	}
	
	public ArrayList<NumberedEvent> FloatingList(){
		return FloatingList;
	}
	
	public void initializeCommand(String userCommand) throws IOException, ParseException{
		returnedCommand = ToDoList.initialize();
	}
	
	public void getFeedback(String userCommand) throws IOException, ParseException{
		returnedCommand = ToDoList.implement(userCommand);
	}
	
	public String returnedCommand(){
		return returnedCommand;
	}
	
	protected void getIsShowMainGrid(){
		isShowMainGrid = !ToDoList.getIsShow();
	}
	
	protected boolean isShowMainGrid(){
		return isShowMainGrid;
	}
	
	public String theme(){
		return theme;
	}
}
