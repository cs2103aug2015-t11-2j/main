package main;

public class Parser {
	
	public static String getAction(String userCommand){
		String action = getFirstWord(userCommand).toLowerCase();
		return action;
	}
	
	public static String getParameter(String userCommand){
		String parameter = removeFirstWord(userCommand);
		return parameter;
	}
	
	/*public static ArrayList<String> getParameter(String userCommand){
		String action = getFirstWord(userCommand).toLowerCase();
		ArrayList<String> parameter = new ArrayList<String>;
		if (action.equals("add")){
			String unsplitParameter = removeFirstWord(userCommand);
			String event = getFirstWord()
		}
		else{
			parameter.add(removeFirstWord(userCommand));
		}
		
		return parameter;
	}*/
	
	
	private static String removeFirstWord(String userCommand) {
		return userCommand.replace(getFirstWord(userCommand), "").trim();
	}
	
	private static String getFirstWord(String userCommand) {
		String commandTypeString = userCommand.trim().split("\\s+")[0];
		return commandTypeString;
	}
	
	public static int getUpdateIndex(String parameter) {
		return Integer.valueOf(parameter.substring(0, parameter.indexOf(" ")));
	}

	public static String getUpdateParameter(String parameter) {
		return parameter.substring(parameter.indexOf(" ") + 1);
	}
}
