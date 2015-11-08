package Logic;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import Parser.Parser;
import Storage.Storage;
import Tasks.NumberedEvent;

public class MainLogic {
	private static Logger logger = Logger.getLogger("ToDoList");
	private static final String EXIT_MSG = "Thanks for using Yui!";
	private static final String SPACE = " ";
	private static final String WELCOME_MSG = " Hello, my master. Welcome back." + "\n" + " This is Yui!  <(^v^)/ "
			+ "\n" + " The events of today is shown on the right " + "\n" + " -What would you like to do?\n";
	private static final String ERROR_MSG = "Error!";
	protected static Storage s;
	// private static ArrayList<Event> fullList;
	private static final SimpleDateFormat DATAFORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	private static final String READ_MSG = "All events are displayed!";
	private static final String READMARK_MSG = "All marked events are displayed!";
	private static final String PARA_EXCEPTION_MSG = "Unrecognized parameter!";
	private static String nowTime;
	protected static boolean shouldExit = false;

	public static String implement(String userCommand) throws IOException, ParseException {
		nowTime = DATAFORMAT.format(new Date()) + "\n";
		assert!userCommand.equals("");
		String command = Parser.getAction(userCommand);
		ArrayList<String> parameter = Parser.getParameter(userCommand);
		ArrayList<String> sentence = Parser.getSentence(userCommand);
		System.out.println(sentence);
		String returnCommand = nowTime + SPACE + "Command Entered: " + userCommand + "\n" + SPACE
				+ modify(s, command, parameter, sentence);
		Action.setRecur(s);
		return returnCommand;
	}

	private static String modify(Storage s, String command, ArrayList<String> parameter, ArrayList<String> sentence)
			throws IOException, ParseException {
		try {
			logger.log(Level.INFO, "handle a command once");
			switch (command) {
			case "readall": {
				if (!parameter.get(0).equals("")) {
					return PARA_EXCEPTION_MSG;
				}
				Action.readAll(s);
				return READ_MSG;
			}
			case "add": {
				return Action.addToList(s, parameter, sentence);
			}
			case "theme": {
				return Action.bground(s,parameter);
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
				if (!parameter.get(0).equals("")) {
					return PARA_EXCEPTION_MSG;
				}
				return Action.undo(s);
			}
			case "redo": {
				if (!parameter.get(0).equals("")) {
					return PARA_EXCEPTION_MSG;
				}
				return Action.redo(s);
			}
			case "comment": {
				return Action.comment(s, parameter);
			}
			case "mark": {
				return Action.mark(s, parameter);
			}
			case "nusmods": {
				if (!parameter.get(0).equals("")) {
					return PARA_EXCEPTION_MSG;
				}
				return Action.nusmods();
			}
			case "todolist": {
				if (!parameter.get(0).equals("")) {
					return PARA_EXCEPTION_MSG;
				}
				return Action.todolist();
			}
			case "recur": {
				return Action.recur(s, parameter);
			}
			case "readmark": {
				if (!parameter.get(0).equals("")) {
					return PARA_EXCEPTION_MSG;
				}
				Action.readMark(s);
				return READMARK_MSG;
			}
			case "unmark": {
				return Action.unmark(s, parameter);
			}
			case "help": {
				return Action.help(parameter);
			}
			case "clearall": {
				return Action.clearAll(s, parameter);
			}
			case "setpath": {
				return Action.setpath(s, parameter);
			}
			case "exit": {
				if (!parameter.get(0).equals("")) {
					return PARA_EXCEPTION_MSG;
				}
				Action.exit();
				return EXIT_MSG;
			}
			default: {
				return ERROR_MSG;
			}
			}
		} catch (IOException e) {
			logger.log(Level.WARNING, "IO error", e);
			e.printStackTrace();
			return null;
		}

	}

	// need to be updated
	public static ArrayList<NumberedEvent> getDealine() throws IOException, ParseException {
		return Action.getDeadlineList();
	}

	public static ArrayList<NumberedEvent> getEventTime() throws IOException, ParseException {
		return Action.getEventTimeList();
	}

	public static ArrayList<NumberedEvent> getFloating() throws IOException, ParseException {
		return Action.getFloatingList();
	}

	public static void getFullList() throws IOException, ParseException {
		Action.readAll(s);
	}
	//@@author A0133992X
	public static boolean getIsShow(){
		return Action.getIsShow();
	}
	//@@author A0133992X
	public static String getTheme(){
		return Action.configedTheme;
	}

	public static String initialize() throws IOException, ParseException {
		logger.log(Level.INFO, "initialize the ToDoList");
		loadConfigInLogic();
		// fullList = s.loadE();
		shouldExit = false;
		nowTime = DATAFORMAT.format(new Date()) + "\n";
		getFullList();
		ArrayList<String> todayParameter = new ArrayList<String>();
		todayParameter.add("today");
		Action.read(s, todayParameter);
		return nowTime + WELCOME_MSG;
	}
	//@@author A0133992X
	private static void loadConfigInLogic() throws IOException{
		s = new Storage("Yui");
		ArrayList<String> config = s.loadConfig();
		Path mySavePath = Paths.get(config.get(0));
		//Path myTempPath = Paths.get(config.get(0) + "\\temp\\");
		s = new Storage("Yui",mySavePath);
		//s.mainDir = mySavePath;
		//s.tempDir = myTempPath;
		Action.configedTheme = config.get(1);
	}
	
}
