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
	
	public static int getUpdateIndex(ArrayList<String> parameter) {
		return Integer.valueOf(parameter.get(0).substring(0, parameter.indexOf(" ")))-1;
	}

	public static Event getUpdateEvent(ArrayList<String> parameter) {
		String updateParameter = parameter.get(0).substring(parameter.indexOf(" ") + 1);
		
		return null;		
	}

	public static Event parseForEvent(ArrayList<String> parameter) {
		if (parameter.size() == 1){
			return new Event(parameter.get(0));
		} else if (parameter.size() == 2){
			return new Event(parameter.get(0), parseForDeadline(parameter.get(1)));
		} else if (parameter.size() == 3){
			return new Event(parameter.get(0), parseForEventTime(parameter.get(1), parameter.get(2)));
		}
		return null;
	}

	private static EventTime parseForEventTime(String string, String string2) {
		return new EventTime(parseForCalendar(string),parseForCalendar(string2));
	}

	private static Deadline parseForDeadline(String string) {
		return new Deadline(parseForCalendar(string));
	}

	private static Calendar parseForCalendar(String string) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, value);
		return null;
	}
}