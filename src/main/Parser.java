package main;

import java.io.IOException;
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

	public static int getUpdateIndex(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		return indexInFullList(s, (parameter.get(0).substring(0, parameter.get(0).indexOf(" "))));
	}

	public static Event getUpdateEvent(Storage s, ArrayList<String> parameter) throws ParseException, IOException {
		String updateParameter = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		ArrayList<String> eventParameter = new ArrayList<String>();
		if (updateParameter.contains("from")) { // event with specific time
												// interval
			eventParameter = Splitter.splitEvent(eventParameter, updateParameter);
		}

		else if (updateParameter.contains("by")) { // deadline
			eventParameter = Splitter.splitDeadline(eventParameter, updateParameter);
		} else {// no time specified
			eventParameter.add(updateParameter);
		}
		Event updatedEvent = parseForEvent(eventParameter);
		updatedEvent.comment = s.loadE().get(getUpdateIndex(s, parameter)).getComment();
		return updatedEvent;
	}

	public static Event parseForEvent(ArrayList<String> parameter) throws ParseException {
		if (parameter.size() == 1) {
			return new Event(parameter.get(0));
		} else if (parameter.size() == 3) {
			return new Event(parameter.get(0), parseForDeadline(parameter.get(1), parameter.get(2)));
		} else if (parameter.size() == 4) {
			return new Event(parameter.get(0), parseForEventTime(parameter.get(1), parameter.get(2), parameter.get(3)));
		}
		return null;
	}

	// parse exception if string stored not in tommorrow, today and not of
	// dd/mm/yyyy format
	private static EventTime parseForEventTime(String start, String end, String date) throws ParseException {
		Date thisDate = new Date();
		Date startDate = new Date();
		Date endDate = new Date();
		// need to be updated
		if (date.equalsIgnoreCase("today")) {
			// do nothing, to break the else case
		}
		if (date.equalsIgnoreCase("tomorrow")) {
			long time = (thisDate.getTime() / 1000) + 60 * 60 * 24;// √Î
			thisDate.setTime(time * 1000);
		} else {
			String startS = start + " " + date;
			String endS = end + " " + date;
			SimpleDateFormat dateF = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			startDate = dateF.parse(startS);
			endDate = dateF.parse(endS);
		}

		return new EventTime(startDate, endDate);
	}

	private static Deadline parseForDeadline(String deadline, String date) throws ParseException {
		Date thisDate = new Date();
		// need to be updated
		if (date.equalsIgnoreCase("today")) {
			// do nothing, to break the else case
		}
		if (date.equalsIgnoreCase("tomorrow")) {
			long time = (thisDate.getTime() / 1000) + 60 * 60 * 24;// √Î
			thisDate.setTime(time * 1000);
		} else {
			String timeAndDate = deadline + " " + date;
			SimpleDateFormat dateF = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			thisDate = dateF.parse(timeAndDate);
		}
		return new Deadline(thisDate);// parseForCalendarTime(deadline,cal));
	}

	protected static int indexInFullList(Storage s, String typeAndIndex) throws IOException, ParseException {
		ArrayList<Event> list = s.loadE();
		if (typeAndIndex.toLowerCase().contains("d")) {
			int index = Integer.valueOf(typeAndIndex.substring(1));
			int count = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getDeadline() != null && !list.get(i).status.equalsIgnoreCase("done")) {
					count++;
					if (count == index) {
						return i;
					}
				}
			}
			if (count < index) {
				return -1;
			}
		} else if (typeAndIndex.toLowerCase().contains("e")) {
			int index = Integer.valueOf(typeAndIndex.substring(1));
			int count = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getEventTime() != null && !list.get(i).status.equalsIgnoreCase("done")) {
					count++;
					if (count == index) {
						return i;
					}
				}
			}
			if (count < index) {
				return -1;
			}
		} else if (typeAndIndex.toLowerCase().contains("m")) {
			int index = Integer.valueOf(typeAndIndex.substring(1));
			int count = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getEventTime() == null && list.get(i).getDeadline() == null
						&& !list.get(i).status.equalsIgnoreCase("done")) {
					count++;
					if (count == index) {
						return i;
					}
				}
			}
			if (count < index) {
				return -1;
			}
		} else {
			return -2;
		}
		return -3;
	}
	/*
	 * private static Calendar parseForCalendarTime(String time, Calendar cal)
	 * throws ParseException { SimpleDateFormat timeF = new
	 * SimpleDateFormat("HH:mm"); cal.setTime(timeF.parse(time)); return cal; }
	 */
}