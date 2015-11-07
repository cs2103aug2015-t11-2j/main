package GUI;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import Logic.ToDoList;
import Tasks.NumberedEvent;

public class UIBuffer {
	private static ArrayList<NumberedEvent> DeadLineList;
	private static ArrayList<NumberedEvent> EventList;
	private static ArrayList<NumberedEvent> FloatingList;
	private static String returnedCommand;
	private static boolean isShowMainGrid;
	private static String theme;
	
	private static void getDealineList() throws IOException, ParseException{
		DeadLineList = ToDoList.getDealine();
	}
	
	private static void getEventList() throws IOException, ParseException{
		EventList = ToDoList.getEventTime();
	}
	
	private static void getFloatingList() throws IOException, ParseException{
		FloatingList = ToDoList.getFloating();
	}
	
	protected static void getTheme(){
		theme = ToDoList.getTheme();
	}
	
	public static void getList() throws IOException, ParseException{
		getDealineList();
		getEventList();
		getFloatingList();
	}
	
	public static ArrayList<NumberedEvent> DeadlineList(){
		return DeadLineList;
	}
	
	public static ArrayList<NumberedEvent> EventList(){
		return EventList;
	}
	
	public static ArrayList<NumberedEvent> FloatingList(){
		return FloatingList;
	}
	
	public static void initializeCommand(String userCommand) throws IOException, ParseException{
		returnedCommand = ToDoList.initialize();
	}
	
	public static void getFeedback(String userCommand) throws IOException, ParseException{
		returnedCommand = ToDoList.implement(userCommand);
	}
	
	public static String returnedCommand(){
		return returnedCommand;
	}
	
	protected static void getIsShowMainGrid(){
		isShowMainGrid = !ToDoList.getIsShow();
	}
	
	protected static boolean isShowMainGrid(){
		return isShowMainGrid;
	}
	
	public static String theme(){
		return theme;
	}
}
