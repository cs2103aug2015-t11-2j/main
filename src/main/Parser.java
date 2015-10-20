package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parser {

	public static String getAction(String userCommand) {
		String action = Splitter.getFirstWord(userCommand).toLowerCase();
		return action;
	}

	/*
	 * public static String getParameter(String userCommand){ String parameter =
	 * Splitter.removeFirstWord(userCommand); return parameter; }
	 */

	public static ArrayList<String> getParameter(String userCommand) {
		String action = getAction(userCommand);
		ArrayList<String> parameter = new ArrayList<String>();
		if (action.equals("add")) {
			String unsplitParameter = Splitter.removeFirstWord(userCommand);
			if (unsplitParameter.contains("from")) { // event with specific time
														// interval
				parameter = Splitter.splitEvent(parameter, unsplitParameter);
			}

			else if (unsplitParameter.contains("by")) { // deadline
				parameter = Splitter.splitDeadline(parameter, unsplitParameter);
			} else {// no time specified
				parameter.add(Splitter.removeFirstWord(userCommand));
			}
		} else {
			parameter.add(Splitter.removeFirstWord(userCommand)); // not an
																	// "add"
																	// event
		}

		return parameter;
	}

	public static int getUpdateIndex(ArrayList<String> parameter) {
		return Integer.valueOf(parameter.get(0).substring(0, parameter.indexOf(" "))) - 1;
	}

	public static Event getUpdateEvent(ArrayList<String> parameter) {
		String updateParameter = parameter.get(0).substring(parameter.indexOf(" ") + 1);

		return null;
	}

	public static Event parseForEvent(ArrayList<String> parameter) throws ParseException {
		if (parameter.size() == 1) {
			return new Event(parameter.get(0));
		} else if (parameter.size() == 2) {
			return new Event(parameter.get(0), parseForDeadline(parameter.get(1), parameter.get(2)));
		} else if (parameter.size() == 3) {
			return new Event(parameter.get(0), parseForEventTime(parameter.get(1), parameter.get(2),parameter.get(3)));
		}
		return null;
	}
	//parse exception if string stored not in tommorrow, today and not of dd/mm/yyyy format
	private static EventTime parseForEventTime(String start, String end, String date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		if (date.equalsIgnoreCase("today")){
			//do nothing, to break the else case
		}
		if (date.equalsIgnoreCase("tomorrow")){
			cal.add(Calendar.DATE, 1);
		}
		else{
			SimpleDateFormat dateF = new SimpleDateFormat("dd/mm/yyyy");
			cal.setTime(dateF.parse(date));
		}
		
		return new EventTime(parseForCalendarTime(start, cal),parseForCalendarTime(end, cal));
	}

	private static Deadline parseForDeadline(String deadline, String date) throws ParseException {
		Calendar cal = Calendar.getInstance();
		if (date.equalsIgnoreCase("today")){
			//do nothing, to break the else case
		}
		if (date.equalsIgnoreCase("tomorrow")){
			cal.add(Calendar.DATE, 1);
		}
		else{
			SimpleDateFormat dateF = new SimpleDateFormat("dd/mm/yyyy");
			cal.setTime(dateF.parse(date));
		}
		return new Deadline(parseForCalendarTime(deadline,cal));
	}

	private static Calendar parseForCalendarTime(String time, Calendar cal) throws ParseException {
		SimpleDateFormat timeF = new SimpleDateFormat("HH:mm");
		cal.setTime(timeF.parse(time));
		return cal;
	}
}