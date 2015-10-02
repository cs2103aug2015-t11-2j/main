package main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ToDoList {

	private static final int MAX_NUMBER_OF_EVENTS = 1000;

	private static final String EXIT_MSG = "Thanks for using Yui!";
	private static final String WELCOME_MSG = " Hello, my master. Welcome back. This is Yui!" + "\n" + " What would you like to do? \n";
	private static final String ERROR_MSG = "Error!";
	private static final String SPACE = " ";
	private static ArrayList<String> list;
	private static final SimpleDateFormat DATAFORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    	private static String nowTime;
	protected static boolean shouldExit = false;

	public static String implement(String userCommand) throws IOException {
			nowTime = DATAFORMAT.format(new Date()) + "\n";
			String command = Parser.getAction(userCommand);
			String parameter = Parser.getParameter(userCommand);
			return nowTime + SPACE + modify(list, command, parameter);
	}

	private static String modify(ArrayList<String> list, String command, String parameter) throws IOException {
		switch (command) {
		case "add": {
			return Action.addToList(list, parameter);

		}
		case "show": {
			return Action.showAll(list);
		}
		case "choose": {
			return Action.chooseEvent(list, parameter);
		}
		case "delete": { // need to decide when to backup and which actions need
							// backup
			return Action.deleteEvent(list, parameter);

		}
		case "search": {
			return Action.searchKey(list, parameter);
		}
		case "update": {
			return Action.update(list, parameter);
		}
		case "exit": {
			Action.exit();
			return EXIT_MSG;
		}
		default: {
			return ERROR_MSG;
		}
		}


	}

	public static String initialize() throws IOException {
		list = new ArrayList<String>(MAX_NUMBER_OF_EVENTS);
		shouldExit = false;
		nowTime = DATAFORMAT.format(new Date()) + "\n";
		return nowTime + WELCOME_MSG;
	}

}
