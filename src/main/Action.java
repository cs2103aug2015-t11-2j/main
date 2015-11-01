package main;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import Yui.Yui_GUI;

public class Action {

	private static ArrayList<Event> fullList;
	private static final String SEARCH_NOT_FOUND_MSG = "Cannot find the key words!";
	private static final String DELETE_OUT_OF_BOUND_MSG = "Cannot delete. Index entered is larger than current event amount!";
	private static final String DELETE_SUCCESSFUL_MSG = "Delete successful!";
	private static final String ADD_SUCCESS_MSG = "Event added successful!";
	private static final String UPDATE_SUCCESS_MSG = "Event updated successfully!";
	private static final String UNDO_MSG = "Undo operation successful!";
	private static final String UNABLE_UNDO_MSG = "Cannot undo! Have some operations first!";
	private static final String REDO_MSG = "Redo operation successful!";
	private static final String UNABLE_REDO_MSG = "Cannot redo if you did not undo!";
	private static final String INVALID_ADD_PARAMETER_MSG = "Cannot add empty event!";
	private static final String NO_INDEX_TO_READ_MSG = "Please specify the event index to read!";
	private static final String INVALID_LIST_TYPE_MSG = "Please enter the correct event type (d, e or m)!";
	private static final String READING_INDEX_OUTOFBOUND_MSG = "There is no event of the index entered!";
	private static final String COMMENT_SUCCESSFUL_MSG = "Comment added successfully!";
	private static final String COMMENT_OUT_OF_BOUND_MSG = "Cannot comment. Index entered is larger than current event amount!";
	private static final String PRIORITY_OUT_OF_BOUND_MSG = "Cannot set priority. Index entered is larger than current event amount!";
	private static final String PRIORITY_SUCCESSFUL_MSG = "Priority set successfully!";
	private static final String MARK_OUT_OF_BOUND_MSG = "Cannot mark. Index entered is larger than current event amount!";
	private static final String MARK_SUCCESSFUL_MSG = "Event marked successfully!";
	private static final String CHANGR_BK_SUCCESSFUL = "Background is changed successfully!";
	private static final String CHANGR_BK_DEFAULT = "Background is changed as default!";
	private static final String INVALID_THEME = "It is an invalid theme!";
	private static final String NO_MY_THEME = "There is no user's theme! \n Please add in uer.dir. \n And name it as myTheme.png";
	private static final String UNRECOGNIZED_OUTLINE_MSG = "You cannot enter a value after outline command!";
	private static final String UNRECOGNIZABLE_CLEARALL_MSG = "You cannot enter a value after clearall command!";
	private static final String CLEARALL_MSG = "All contents cleared! Please Undo now if you made a mistake!";
	private static final String NO_EVENT_TODAY_MSG = "There is nothing to do today!";
	private static final String NO_EVENT_TMR_MSG = "There is nothing to do tomorrow!";
	private static final String HELPLIST = "add\n theme\n read\n outline\n delete\n search\n update\n undo\n redo\n comment\n priority\n mark\n help\n clearall\n exit";
	private static final String ADD_HELP_MSG = "You can add 3 types of event to your\n list, namely deadline event,\n event-time event and memo event\n For events with deadline, type in\n \"add (event name) by HH:MM\n DD/MM/YYYY\"\n For events with event time, type in\n \"add (event name) from HH:MM to\n HH:MM DD/MM/YYYY\"\n For events without a time, type in\n \"add (event name)\"";
	private static final String THEME_HELP_MSG = "You can change the picture you display on the right hand side panel. There are two pre-set pictures and you can change between them using \"theme 1\" and \"theme 2\"\n To use your own picture, manually add a picture of size 253*273 and file type png into the user.dir folder. Rename it to myTheme. Then you can change to this picture by typing in \"theme my theme\"";
	private static final String READ_HELP_MSG = "As you can see your events listed under three categories on the right, you can read detailed information about ";
	private static final String OUTLINE_HELP_MSG = null;
	private static final String DELETE_HELP_MSG = null;
	private static final String SEARCH_HELP_MSG = null;
	private static final String UPDATE_HELP_MSG = null;
	private static final String UNDO_HELP_MSG = null;
	private static final String REDO_HELP_MSG = null;
	private static final String COMMENT_HELP_MSG = null;
	private static final String PRIORITY_HELP_MSG = null;
	private static final String MARK_HELP_MSG = null;
	private static final String HELP_HELP_MSG = "You can always refer to helplist for all\n the commands available\n Type in \"help\" you can see a list of all\n commands available\n Type in \"help\" and the command you\n want to know to learn more about it!";
	private static final String CLEARALL_HELP_MSG = null;
	private static final String EXIT_HELP_MSG = null;
	private static final String COMMAND_NOT_RECOGNIZED_IN_HELPLIST_MSG = "The command you entered is not found\n Please check to ensure you entered the correct command word!";
	private static final String SEARCH_FOUND_MSG = "The list of found results are on the right!";
	private static final String IMPROPER_SEARCH_KEY_MSG = "Please enter a proper keyword!";
	private static final String EVENT_TMR_DISPLAYED_MSG = "Events of tomorrow displayed on the right!";
	private static final String EVENT_TODAY_DISPLAYED_MSG = "Events of today displayed on the right!";
	private static final String NO_EVENT_TODAY_OR_TMR_MSG = "Have a rest! There is nothing to do today or tomorrow!";
	private static final String EVENT_TODAY_AND_TMR_DISPLAYED_MSG = "Events of today and tomorrow displayed on the right!";
	private static final String NUS_MOD_SUCESSFUL = "Show nusmods sucessfully!";
	private static final String TODOLIST_SUCESSFUL = "Show TodoList sucessfully!";
	private static SimpleDateFormat deadline_format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	private static SimpleDateFormat eventStart_format = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat eventEnd_format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	private static boolean canUndo = false;
	private static boolean isShowNusMods = false;
	private static SimpleDateFormat formatCompare = new SimpleDateFormat("yyyyMMdd");

	static String addToList(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		fullList = s.loadE();
		Event newEvent = Parser.parseForEvent(parameter);
		if (newEvent.getDetail().equals("")) {
			return INVALID_ADD_PARAMETER_MSG;
		} else {
			fullList.add(newEvent);
			s.saveE(fullList);
			canUndo = true;
			readAll(s);
			return ADD_SUCCESS_MSG;
		}
	}

	static void exit() {
		ToDoList.shouldExit = true;
		System.exit(0);
	}

	public static void readAll(Storage s) throws IOException, ParseException {
		fullList = s.loadE();
		Collections.sort(fullList);
	}

	public static String bground(ArrayList<String> parameter) {
		if (!(parameter.get(0).toLowerCase().equals("default") || parameter.get(0).equals("1"))) {
			String theme = parameter.get(0);
			if (theme.equals("2")) {
				Yui_GUI.listBkImage = Yui_GUI.listBkImage2;
				return CHANGR_BK_SUCCESSFUL;
			} else if (theme.equals("my theme")) {
				File myTheme = new File(Yui_GUI.listBackgroundPath3);
				if (myTheme.exists()) {
					Yui_GUI.listBkImage = Yui_GUI.listBkImage3;
					return CHANGR_BK_SUCCESSFUL;
				} else {
					return NO_MY_THEME;
				}
			} else {
				return INVALID_THEME;
			}

		} else {
			// Yui_GUI.listBackgroundPath = "listBK2.png";
			Yui_GUI.listBkImage = Yui_GUI.listBkImage1;
			return CHANGR_BK_DEFAULT;
		}
	}

	protected static String searchKey(Storage s, ArrayList<String> parameterArrayList)
			throws IOException, ParseException {
		String parameter = parameterArrayList.get(0);
		if (fullList.size() == 0) {
			return SEARCH_NOT_FOUND_MSG;
		} else if (parameter.equals("")) {
			return IMPROPER_SEARCH_KEY_MSG;
		} else {
			ArrayList<NumberedEvent> deadlineEvent = getDeadlineList();
			ArrayList<NumberedEvent> eventTimeEvent = getEventTimeList();
			ArrayList<NumberedEvent> memoEvent = getFloatingList();
			Boolean isFound = false;
			for (NumberedEvent dEvent : deadlineEvent) {
				if (dEvent.getEvent().getDetail().toLowerCase().contains(parameter.toLowerCase())) {
					isFound = true;
				} else {
					fullList.remove(dEvent.getEvent());
				}
			}
			for (NumberedEvent eEvent : eventTimeEvent) {
				if (eEvent.getEvent().getDetail().toLowerCase().contains(parameter.toLowerCase())) {
					isFound = true;
				} else {
					fullList.remove(eEvent.getEvent());
				}

			}
			for (NumberedEvent mEvent : memoEvent) {
				if (mEvent.getEvent().getDetail().toLowerCase().contains(parameter.toLowerCase())) {
					isFound = true;
				} else {
					fullList.remove(mEvent.getEvent());
				}
			}
			if (isFound) {
				return SEARCH_FOUND_MSG;
			} else {
				return SEARCH_NOT_FOUND_MSG; // no result found
			}
		}

	}

	public static String undo(Storage s) throws IOException, ParseException {
		if (canUndo) {
			s.saveE(s.loadE(s.tempDir));
			canUndo = false;
			readAll(s);
			return UNDO_MSG;
		} else {
			return UNABLE_UNDO_MSG;
		}
	}

	public static String redo(Storage s) throws IOException, ParseException {
		if (!canUndo) {
			s.saveE(s.loadE(s.tempDir));
			canUndo = true;
			readAll(s);
			return REDO_MSG;
		} else {
			return UNABLE_REDO_MSG;
		}
	}

	public static ArrayList<NumberedEvent> getDeadlineList() throws IOException, ParseException {
		ArrayList<NumberedEvent> deadlineList = new ArrayList<NumberedEvent>();
		int indexCount = 0;
		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).getDeadline() != null && !fullList.get(i).status.equalsIgnoreCase("done")) {
				deadlineList.add(new NumberedEvent(++indexCount, fullList.get(i)));

			}
		}
		return deadlineList;
	}

	public static ArrayList<NumberedEvent> getEventTimeList() throws IOException, ParseException {
		ArrayList<NumberedEvent> eventTimeList = new ArrayList<NumberedEvent>();
		int indexCount = 0;
		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).getEventTime() != null && !fullList.get(i).status.equalsIgnoreCase("done")) {
				eventTimeList.add(new NumberedEvent(++indexCount, fullList.get(i)));
			}
		}
		return eventTimeList;
	}

	public static ArrayList<NumberedEvent> getFloatingList() throws IOException, ParseException {
		ArrayList<NumberedEvent> floatingList = new ArrayList<NumberedEvent>();
		int indexCount = 0;
		for (int i = 0; i < fullList.size(); i++) {
			if ((fullList.get(i).getDeadline() == null) && (fullList.get(i).getEventTime() == null)
					&& !fullList.get(i).status.equalsIgnoreCase("done")) {
				floatingList.add(new NumberedEvent(++indexCount, fullList.get(i)));
			}
		}
		return floatingList;
	}

	public static String read(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		StringBuilder output = new StringBuilder();
		if (parameter.size() == 0) {
			return NO_INDEX_TO_READ_MSG;
		} else if (parameter.get(0).equalsIgnoreCase("today")) {
			readAll(s);
			ArrayList<NumberedEvent> deadlineEvent = getDeadlineList();
			ArrayList<NumberedEvent> eventTimeEvent = getEventTimeList();
			ArrayList<NumberedEvent> memoEvent = getFloatingList();
			Boolean hasEvent = false;
			for (NumberedEvent dEvent : deadlineEvent) {
				Date eventDeadline = dEvent.getEvent().getDeadline().getDeadline();
				if (isToday(eventDeadline)) {
					hasEvent = true;
				} else {
					fullList.remove(dEvent.getEvent());
				}
			}
			for (NumberedEvent eEvent : eventTimeEvent) {
				Date eventEventTime = eEvent.getEvent().getEventTime().getStart();
				if (isToday(eventEventTime)) {
					hasEvent = true;
				} else {
					fullList.remove(eEvent.getEvent());
				}

			}
			for (NumberedEvent mEvent : memoEvent) {
				fullList.remove(mEvent.getEvent());
			}

			if (!hasEvent) {
				return NO_EVENT_TODAY_MSG;
			} else {
				return EVENT_TODAY_DISPLAYED_MSG;
			}

		} else if (parameter.get(0).equalsIgnoreCase("tomorrow") || parameter.get(0).equalsIgnoreCase("tmr"))

		{
			readAll(s);
			ArrayList<NumberedEvent> deadlineEvent = getDeadlineList();
			ArrayList<NumberedEvent> eventTimeEvent = getEventTimeList();
			ArrayList<NumberedEvent> memoEvent = getFloatingList();
			Boolean hasEvent = false;
			for (NumberedEvent dEvent : deadlineEvent) {
				Date eventDeadline = dEvent.getEvent().getDeadline().getDeadline();
				if (isTmr(eventDeadline)) {
					hasEvent = true;
				} else {
					fullList.remove(dEvent.getEvent());
				}
			}
			for (NumberedEvent eEvent : eventTimeEvent) {
				Date eventEventTime = eEvent.getEvent().getEventTime().getStart();
				if (isTmr(eventEventTime)) {
					hasEvent = true;
				} else {
					fullList.remove(eEvent.getEvent());
				}

			}
			for (NumberedEvent mEvent : memoEvent) {
				fullList.remove(mEvent.getEvent());
			}
			if (!hasEvent) {
				return NO_EVENT_TMR_MSG;
			} else {
				return EVENT_TMR_DISPLAYED_MSG;
			}

		} else

		{
			if (parameter.get(0).toLowerCase().contains("d")) {
				ArrayList<NumberedEvent> list = getDeadlineList();
				int index = Integer.valueOf(parameter.get(0).substring(1));
				if (index > list.size()) {
					return READING_INDEX_OUTOFBOUND_MSG;
				} else {
					Event reading = list.get(index - 1).getEvent();
					output.append("Detail: " + reading.getDetail() + "\n");
					output.append(" Comment: " + reading.getComment() + "\n");
					output.append(" Time: " + deadline_format.format(reading.getDeadline().getDeadline()));
				}
			} else if (parameter.get(0).toLowerCase().contains("e")) {
				ArrayList<NumberedEvent> list = getEventTimeList();
				int index = Integer.valueOf(parameter.get(0).substring(1));
				if (index > list.size()) {
					return READING_INDEX_OUTOFBOUND_MSG;
				} else {
					Event reading = list.get(index - 1).getEvent();
					output.append("Detail: " + reading.getDetail() + "\n");
					output.append(" Comment: " + reading.getComment() + "\n");
					output.append(" Time: " + eventStart_format.format(reading.getEventTime().getStart()) + "-"
							+ eventEnd_format.format(reading.getEventTime().getEnd()));
				}
			} else if (parameter.get(0).toLowerCase().contains("m")) {
				ArrayList<NumberedEvent> list = getFloatingList();
				int index = Integer.valueOf(parameter.get(0).substring(1));
				if (index > list.size()) {
					return READING_INDEX_OUTOFBOUND_MSG;
				} else {
					Event reading = list.get(index - 1).getEvent();
					output.append("Detail: " + reading.getDetail() + "\n");
					output.append(" Comment: " + reading.getComment() + "\n");
					output.append(" Time: ");
				}
			} else {
				return INVALID_LIST_TYPE_MSG;
			}

		}
		return output.toString();

	}

	public static String outline(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		if (!parameter.get(0).equals("")) {
			return UNRECOGNIZED_OUTLINE_MSG;
		} else {
			ArrayList<String> readToday = new ArrayList<String>();
			ArrayList<String> readTmr = new ArrayList<String>();
			readToday.add("today");
			readTmr.add("tmr");
			readAll(s);

			readAll(s);
			ArrayList<NumberedEvent> deadlineEvent = getDeadlineList();
			ArrayList<NumberedEvent> eventTimeEvent = getEventTimeList();
			ArrayList<NumberedEvent> memoEvent = getFloatingList();
			Boolean hasEvent = false;
			for (NumberedEvent dEvent : deadlineEvent) {
				Date eventDeadline = dEvent.getEvent().getDeadline().getDeadline();
				if (isToday(eventDeadline) || isTmr(eventDeadline)) {
					hasEvent = true;
				} else {
					fullList.remove(dEvent.getEvent());
				}
			}
			for (NumberedEvent eEvent : eventTimeEvent) {
				Date eventEventTime = eEvent.getEvent().getEventTime().getStart();
				if (isToday(eventEventTime) || isTmr(eventEventTime)) {
					hasEvent = true;
				} else {
					fullList.remove(eEvent.getEvent());
				}

			}
			for (NumberedEvent mEvent : memoEvent) {
				fullList.remove(mEvent.getEvent());
			}

			if (!hasEvent) {
				return NO_EVENT_TODAY_OR_TMR_MSG;
			} else {
				return EVENT_TODAY_AND_TMR_DISPLAYED_MSG;
			}

		}

	}

	private static boolean isToday(Date theDate) {
		Date today = new Date();
		String theDateString = formatCompare.format(theDate);
		String todayString = formatCompare.format(today);
		if (theDateString.equals(todayString)) {
			return true;
		}
		return false;
	}

	private static boolean isTmr(Date theDate) {
		Date today = new Date();
		Date tmr = new Date(today.getTime() + 86400000);
		String theDateString = formatCompare.format(theDate);
		String tmrString = formatCompare.format(tmr);
		if (theDateString.equals(tmrString)) {
			return true;
		}
		return false;
	}

	protected static String update(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		ArrayList<Event> list = s.loadE();
		list.set(Parser.getUpdateIndex(fullList, parameter), Parser.getUpdateEvent(fullList, parameter));
		s.saveE(list);
		canUndo = true;
		readAll(s);
		return UPDATE_SUCCESS_MSG;
	}

	protected static String deleteEvent(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		int indexInFullList = Parser.indexInFullList(fullList, parameter.get(0));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return DELETE_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			Event deletedEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(deletedEvent)){
					temp.remove(i);
					break;
				}
			}
			s.saveE(temp);
			readAll(s);
			canUndo = true;
			return DELETE_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	protected static String comment(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		String comment = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		int indexInFullList = Parser.indexInFullList(fullList, parameter.get(0).substring(0, parameter.get(0).indexOf(" ")));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return COMMENT_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			Event commentEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(commentEvent)){
					temp.remove(i);
					break;
				}
			}
			commentEvent.comment = comment;
			temp.add(commentEvent);
			s.saveE(temp);
			readAll(s);
			canUndo = true;
			return COMMENT_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	protected static String priority(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		String priority = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		int indexInFullList = Parser.indexInFullList(fullList, parameter.get(0).substring(0, parameter.get(0).indexOf(" ")));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return PRIORITY_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			Event priorityEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(priorityEvent)){
					temp.remove(i);
					break;
				}
			}
			priorityEvent.priority = priority;
			temp.add(priorityEvent);
			s.saveE(temp);
			readAll(s);
			canUndo = true;
			return PRIORITY_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	protected static String mark(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		String status = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		int indexInFullList = Parser.indexInFullList(fullList, parameter.get(0).substring(0, parameter.get(0).indexOf(" ")));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return MARK_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			Event markedEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(markedEvent)){
					temp.remove(i);
					break;
				}
			}
//			temp.remove(markedEvent);
			markedEvent.status = status;
			temp.add(markedEvent);
			s.saveE(temp);
			readAll(s);
//			s.saveE(fullList);
			canUndo = true;
			return MARK_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	public static String nusmods(){
		isShowNusMods = true;
		return NUS_MOD_SUCESSFUL;
	}
	
	public static String todolist(){
		isShowNusMods = false;
		return TODOLIST_SUCESSFUL;
	}
	
	public static boolean getIsShow(){
		return isShowNusMods;
	}
	
	protected static String clearAll(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		if (!parameter.get(0).equals("")) {
			return UNRECOGNIZABLE_CLEARALL_MSG;
		} else {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add("temp");
			addToList(s, temp);
			s.reset();
			s = new Storage("Yui");
			canUndo = true;
			readAll(s);
			return CLEARALL_MSG;
		}
	}

	protected static String help(ArrayList<String> parameterArrayList) {
		String parameter = parameterArrayList.get(0).toLowerCase();
		if (parameter.equals("")) {
			return HELPLIST;
		} else if (parameter.equals("add")) {
			return ADD_HELP_MSG;
		} else if (parameter.equals("theme")) {
			return THEME_HELP_MSG;
		} else if (parameter.equals("read")) {
			return READ_HELP_MSG;
		} else if (parameter.equals("outline")) {
			return OUTLINE_HELP_MSG;
		} else if (parameter.equals("delete")) {
			return DELETE_HELP_MSG;
		} else if (parameter.equals("search")) {
			return SEARCH_HELP_MSG;
		} else if (parameter.equals("update")) {
			return UPDATE_HELP_MSG;
		} else if (parameter.equals("undo")) {
			return UNDO_HELP_MSG;
		} else if (parameter.equals("redo")) {
			return REDO_HELP_MSG;
		} else if (parameter.equals("comment")) {
			return COMMENT_HELP_MSG;
		} else if (parameter.equals("priority")) {
			return PRIORITY_HELP_MSG;
		} else if (parameter.equals("mark")) {
			return MARK_HELP_MSG;
		} else if (parameter.equals("help")) {
			return HELP_HELP_MSG;
		} else if (parameter.equals("clearall")) {
			return CLEARALL_HELP_MSG;
		} else if (parameter.equals("exit")) {
			return EXIT_HELP_MSG;
		} else {
			return COMMAND_NOT_RECOGNIZED_IN_HELPLIST_MSG;
		}
	}

}
