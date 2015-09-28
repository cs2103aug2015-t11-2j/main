import java.io.IOException;
import java.util.ArrayList;

public class ToDoList {

	private static final int MAX_NUMBER_OF_EVENTS = 1000;

	private static final String EXIT_MSG = "Thanks for using Yui!";

	private static final String ERROR_MSG = "Error!";

	static boolean shouldExit = false;

	private static void implement(ArrayList<String> list, String userCommand) throws IOException {
		while (!shouldExit) {
			String command = Parser.getAction(userCommand);
			String parameter = Parser.getParameter(userCommand);
			modify(list, command, parameter);
		}
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
		case "exit": {
			Action.exit();
			return EXIT_MSG;
		}
		}
		return ERROR_MSG;

	}

	public static void initialize() {
		ArrayList<String> list = new ArrayList<String>(MAX_NUMBER_OF_EVENTS);
		implement(list, userCommand);
	}

}
