package main;

public class Parser {
	
	public String getAction(String userCommand){
		String action = getFirstWord(userCommand).toLowerCase();
		return action;
	}
	
	public String getParameter(String userCommand){
		String parameter = removeFirstWord(userCommand);
		return parameter;
	}
	
	private static String removeFirstWord(String userCommand) {
		return userCommand.replace(getFirstWord(userCommand), "").trim();
	}
	
	private static String getFirstWord(String userCommand) {
		String commandTypeString = userCommand.trim().split("\\s+")[0];
		return commandTypeString;
	}
	
}
