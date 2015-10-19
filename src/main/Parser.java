package main;

import java.util.*;

public class Parser {
	
	public static String getAction(String userCommand){
		String action = Splitter.getFirstWord(userCommand).toLowerCase();
		return action;
	}
	
	/*public static String getParameter(String userCommand){
		String parameter = Splitter.removeFirstWord(userCommand);
		return parameter;
	}*/
	
	public static ArrayList<String> getParameter(String userCommand){
		String action = getAction(userCommand);
		ArrayList<String> parameter = new ArrayList<String>();
		if (action.equals("add")){
			String unsplitParameter = Splitter.removeFirstWord(userCommand);
			if (unsplitParameter.contains("from")){ //event with specific time interval
				parameter = Splitter.splitEvent(parameter, unsplitParameter);
			}
			
			else if (unsplitParameter.contains("by")){ //deadline
				parameter = Splitter.splitDeadline(parameter, unsplitParameter);
			}
			else{//no time specified
				parameter.add(Splitter.removeFirstWord(userCommand));
			}
		}
		else{
			parameter.add(Splitter.removeFirstWord(userCommand)); //not an "add" event
		}
		
		return parameter;
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