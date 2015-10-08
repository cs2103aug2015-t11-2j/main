package main;

import java.util.*;

public class Parser {
	
	public static String getAction(String userCommand){
		String action = getFirstWord(userCommand).toLowerCase();
		return action;
	}
	
	/*public static String getParameter(String userCommand){
		String parameter = removeFirstWord(userCommand);
		return parameter;
	}*/
	
	public static ArrayList<String> getParameter(String userCommand){
		String action = getAction(userCommand);
		ArrayList<String> parameter = new ArrayList<String>();
		if (action.equals("add")){
			String unsplitParameter = removeFirstWord(userCommand);
			if (unsplitParameter.contains("from")){ //event with specific time interval
				String[] parameters = unsplitParameter.split("from |to ");
				parameter.add(parameters[0]); //event
				parameter.add(parameters[1]); //start time
				parameter.add(parameters[2]); //end time
				parameter.add(parameters[3]); //date
			}
			
			else if (unsplitParameter.contains("by")){ //deadline
				String[] parameters = unsplitParameter.split("by ");
				parameter.add(parameters[0]); //event
				parameter.add(parameters[1]); //time
				parameter.add(parameters[2]); //date
			}
		}
		else{
			parameter.add(removeFirstWord(userCommand)); //no time specified
		}
		
		return parameter;
	}
	
	
	private static String removeFirstWord(String userCommand) {
		return userCommand.replaceFirst(getFirstWord(userCommand), "").trim();
	}
	
	private static String getFirstWord(String userCommand) {
		String commandTypeString = userCommand.trim().split("\\s+")[0];
		return commandTypeString;
	}
	
	public static int getUpdateIndex(String parameter) {
		return Integer.valueOf(parameter.substring(0, parameter.indexOf(" ")))-1;
	}

	public static String getUpdateParameter(String parameter) {
		return parameter.substring(parameter.indexOf(" ") + 1);
	}
}
