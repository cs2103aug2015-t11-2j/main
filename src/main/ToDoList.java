package main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ToDoList {
	private static Logger logger = Logger.getLogger("ToDoList");
	private static final String EXIT_MSG = "Thanks for using Yui!";
	private static final String SPACE = " ";
	private static final String WELCOME_MSG = " Hello, my master. Welcome back." + "\n" + " This is Yui!  <(£þv£þ)/ " + "\n"
			+" -What would you like to do?\n";
	private static final String ERROR_MSG = "Error!";
	private static Storage s;
	private static final SimpleDateFormat DATAFORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	private static String nowTime;
	protected static boolean shouldExit = false;

	public static String implement(String userCommand) throws IOException, ParseException {
		nowTime = DATAFORMAT.format(new Date()) + "\n";
		assert !userCommand.equals("");
		String command = Parser.getAction(userCommand);
		ArrayList<String> parameter = Parser.getParameter(userCommand);
		return nowTime + SPACE + modify(s, command, parameter);
	}

	private static String modify(Storage s, String command, ArrayList<String> parameter)
			throws IOException, ParseException {
		try{
			logger.log(Level.INFO, "handle a command once");
			switch (command) {
			case "add": {
				return Action.addToList(s, parameter);
			}
			case "theme": {
				return Action.bground(parameter);
			}
			case "read": {
				return Action.read(s, parameter);
			}
			case "outline": {
				return Action.outline(s, parameter);
			}
			case "delete": {
				return Action.deleteEvent(s, parameter);
			}
			case "search": {
				return Action.searchKey(s, parameter);
			}
			case "update": {
				return Action.update(s, parameter);
			}
			case "undo": {
				return Action.undo(s);
			}
			case "redo": {
				return Action.redo(s);
			}
			case "comment": {
				return Action.comment(s, parameter);
			}
			case "priority": {
				return Action.priority(s, parameter);
			}
			case "mark": {
				return Action.mark(s, parameter);
			}
			case "help": {
				return Action.help(parameter);
			}
			case "clearall": {
				return Action.clearAll(s, parameter);
			}
			case "exit": {
				Action.exit();
				return EXIT_MSG;
			}
			default: {
				return ERROR_MSG;
			}
			}
		} catch(IOException e) {
			logger.log(Level.WARNING, "IO error", e);
			e.printStackTrace();
			return null;
		}

	}

	//need to be updated
	public static ArrayList<NumberedEvent> getDealine() throws IOException, ParseException{
		return Action.getDeadlineList(s);
	}
	
	public static ArrayList<NumberedEvent> getEventTime() throws IOException, ParseException{
		return Action.getEventTimeList(s);
	}
	
	public static ArrayList<NumberedEvent> getFloating() throws IOException, ParseException{
		return Action.getFloatingList(s);
	}
	
	
	
	public static String initialize() throws IOException {
		logger.log(Level.INFO, "initialize the ToDoList");
		s = new Storage("Yui");
		shouldExit = false;
		nowTime = DATAFORMAT.format(new Date()) + "\n";
		return nowTime + WELCOME_MSG;
	}

}
