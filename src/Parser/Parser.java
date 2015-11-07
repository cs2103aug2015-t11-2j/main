package Parser;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.EventTime;

public class Parser {

	// Two command types - Add/Other
	enum COMMAND_TYPE {
		ADD, OTHER
	};

	public static String getAction(String userCommand) {
		String action = Splitter.getFirstWord(userCommand).toLowerCase();
		return action;
	}

	public static ArrayList<String> getParameter(String userCommand) {
		String action = getAction(userCommand);
		ArrayList<String> parameter = new ArrayList<String>();

		COMMAND_TYPE commandType = determineCommandType(action);

		switch (commandType) {
		case ADD:
			String unsplitParameter = Splitter.removeFirstWord(userCommand);
			if (unsplitParameter.contains("from") && unsplitParameter.contains("by")) { // event with specific time
														// interval
				parameter = Splitter.splitEvent(parameter, unsplitParameter);
			}

			else if (unsplitParameter.contains("by")) { // deadline
				parameter = Splitter.splitDeadline(parameter, unsplitParameter);
			} else {// no time specified
				parameter.add(Splitter.removeFirstWord(userCommand));
			}
			return parameter;

		case OTHER:
			parameter.add(Splitter.removeFirstWord(userCommand)); // not"add"event
			return parameter;
		default:
			return null;
		}
	}

	public static int getUpdateIndex(ArrayList<Event> fullList, ArrayList<String> parameter)
			throws IOException, ParseException {
		return indexInFullList(fullList, (parameter.get(0).substring(0, parameter.get(0).indexOf(" "))));
	}

	public static Event getUpdateEvent(ArrayList<Event> fullList, ArrayList<String> parameter)
			throws ParseException, IOException {
		String updateParameter = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		ArrayList<String> eventParameter = new ArrayList<String>();
		if (updateParameter.contains("from") && updateParameter.contains("by")) { // event with specific time
												// interval
			eventParameter = Splitter.splitEvent(eventParameter, updateParameter);
		}

		else if (updateParameter.contains("by")) { // deadline
			eventParameter = Splitter.splitDeadline(eventParameter, updateParameter);
		} else {// no time specified
			eventParameter.add(updateParameter);
		}
		Event updatedEvent = parseForEvent(eventParameter);
		if (updatedEvent == null){
			return null;
		}
		updatedEvent.setComment(fullList.get(getUpdateIndex(fullList, parameter)).getComment());
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
		return null;// run when parameter size different from above, cannot happen
	}

	// Determines command type (add or other)
	private static COMMAND_TYPE determineCommandType(String commandTypeString) {
		if (commandTypeString == null)
			throw new Error("command type string cannot be null!");

		if (commandTypeString.equalsIgnoreCase("add") || commandTypeString.equalsIgnoreCase("create")) {
			return COMMAND_TYPE.ADD;
		} else {
			return COMMAND_TYPE.OTHER;
		}
	}

	// parse exception if string stored not in tommorrow, today and not of
	// dd/mm/yyyy format
	private static EventTime parseForEventTime(String start, String end, String date) throws ParseException {
		Date thisDate = new Date();
		Date startDate = new Date();
		Date endDate = new Date();
		// need to be updated
		if (date.equalsIgnoreCase("today")) {
			SimpleDateFormat dateF1 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			SimpleDateFormat dateF2 = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = dateF2.format(thisDate);
			String startDateAndTime = start + " " + dateString;
			String endDateAndTime = end + " " + dateString;
			startDate = dateF1.parse(startDateAndTime);
			endDate = dateF1.parse(endDateAndTime);

		} else if (date.equalsIgnoreCase("tomorrow") || date.equalsIgnoreCase("tmr")) {
			long time = (thisDate.getTime() / 1000) + 60 * 60 * 24;// ﾃ�
			thisDate.setTime(time * 1000);
			SimpleDateFormat dateF1 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			SimpleDateFormat dateF2 = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = dateF2.format(thisDate);
			String startDateAndTime = start + " " + dateString;
			String endDateAndTime = end + " " + dateString;
			startDate = dateF1.parse(startDateAndTime);
			endDate = dateF1.parse(endDateAndTime);
		} else if (start.indexOf(':') != -1) {
			String startS = start + " " + date;
			String endS = end + " " + date;
			SimpleDateFormat dateF = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			startDate = dateF.parse(startS);
			endDate = dateF.parse(endS);
		} else {
			return null;
		}

		return new EventTime(startDate, endDate);
	}

	private static Deadline parseForDeadline(String deadline, String date) throws ParseException {
		Date thisDate = new Date();
		// need to be updated
		if (date.equalsIgnoreCase("today")) {
			SimpleDateFormat dateF1 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			SimpleDateFormat dateF2 = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = dateF2.format(thisDate);
			String dateAndTime = deadline + " " + dateString;
			thisDate = dateF1.parse(dateAndTime);
		} else if (date.equalsIgnoreCase("tomorrow") || date.equalsIgnoreCase("tmr")) {
			long time = (thisDate.getTime() / 1000) + 60 * 60 * 24;// seconds
			thisDate.setTime(time * 1000);
			SimpleDateFormat dateF1 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			SimpleDateFormat dateF2 = new SimpleDateFormat("dd/MM/yyyy");
			String dateString = dateF2.format(thisDate);
			String dateAndTime = deadline + " " + dateString;
			thisDate = dateF1.parse(dateAndTime);
		} else if (deadline.indexOf(':') != -1) {
			String timeAndDate = deadline + " " + date;
			SimpleDateFormat dateF = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			thisDate = dateF.parse(timeAndDate);
		} else {
			return null;
		}
		return new Deadline(thisDate);// parseForCalendarTime(deadline,cal));
	}

	public static int indexInFullList(ArrayList<Event> fullList, String typeAndIndex)
			throws IOException, ParseException {
		if (typeAndIndex.contains(" ")) {
			return -2;
		}
		String indexString = typeAndIndex.substring(1);
		int index = 0;
		try {
			index = Integer.parseInt(indexString);
		} catch (NumberFormatException e) {
			return -2;
		}
		if (typeAndIndex.toLowerCase().charAt(0) == 'd') {
			int count = 0;
			for (int i = 0; i < fullList.size(); i++) {
				if (fullList.get(i).getDeadline() != null && !fullList.get(i).getStatus().equalsIgnoreCase("done")) {
					count++;
					if (count == index) {
						return i;
					}
				}
			}
			if (count < index) {
				return -1;
			}
		} else if (typeAndIndex.toLowerCase().charAt(0) == 'e') {
			int count = 0;
			for (int i = 0; i < fullList.size(); i++) {
				if (fullList.get(i).getEventTime() != null && !fullList.get(i).getStatus().equalsIgnoreCase("done")) {
					count++;
					if (count == index) {
						return i;
					}
				}
			}
			if (count < index) {
				return -1;
			}
		} else if (typeAndIndex.toLowerCase().charAt(0) == 'm') {
			int count = 0;
			for (int i = 0; i < fullList.size(); i++) {
				if (fullList.get(i).getEventTime() == null && fullList.get(i).getDeadline() == null
						&& !fullList.get(i).getStatus().equalsIgnoreCase("done")) {
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

	public static int indexInDoneList(ArrayList<Event> fullList, String typeAndIndex)
			throws IOException, ParseException {
		if (typeAndIndex.contains(" ")) {
			return -2;
		}
		String indexString = typeAndIndex.substring(1);
		int index = 0;
		try {
			index = Integer.parseInt(indexString);
		} catch (NumberFormatException e) {
			return -2;
		}
		if (typeAndIndex.toLowerCase().charAt(0) == 'd') {
			int count = 0;
			for (int i = 0; i < fullList.size(); i++) {
				if (fullList.get(i).getDeadline() != null && fullList.get(i).getStatus().equalsIgnoreCase("done")) {
					count++;
					if (count == index) {
						return i;
					}
				}
			}
			if (count < index) {
				return -1;
			}
		} else if (typeAndIndex.toLowerCase().charAt(0) == 'e') {
			int count = 0;
			for (int i = 0; i < fullList.size(); i++) {
				if (fullList.get(i).getEventTime() != null && fullList.get(i).getStatus().equalsIgnoreCase("done")) {
					count++;
					if (count == index) {
						return i;
					}
				}
			}
			if (count < index) {
				return -1;
			}
		} else if (typeAndIndex.toLowerCase().charAt(0) == 'm') {
			int count = 0;
			for (int i = 0; i < fullList.size(); i++) {
				if (fullList.get(i).getEventTime() == null && fullList.get(i).getDeadline() == null
						&& fullList.get(i).getStatus().equalsIgnoreCase("done")) {
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
}