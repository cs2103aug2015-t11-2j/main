import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

	private static final int MAX_NUMBER_OF_EVENTS = 1000;

	private static Scanner sc = new Scanner(System.in);

	static boolean shouldExit = false;

	private static void implement(ArrayList<String> list, Scanner sc2) throws IOException {
		while (!shouldExit) {
			String input = sc.nextLine();
			String command = Parser.getAction(input);
			String parameter = Parser.getParameter(input);
			modify(list, command, parameter);
		}
	}

	private static void modify(ArrayList<String> list, String command, String parameter) throws IOException {
		switch (command) {
		case "add": {
			Action.addToList(list, parameter);
			Storage.save(list);
		}
		case "show": {
			Action.showAll(list);
		}
		case "choose": {
			Action.chooseFile(list, parameter);
		}
		case "delete": { // need to decide when to backup and which actions need
							// backup
			Action.deleteEvent(list, parameter);
			Storage.save(list);
		}
		case "search": {
			Action.searchKey(list, parameter);
		}
		case "exit": {
			Action.exit();
		}
		}

	}

	public static void main(String args[]) throws IOException {
		ArrayList<String> list = new ArrayList<String>(MAX_NUMBER_OF_EVENTS);
		implement(list, sc);
	}

}
