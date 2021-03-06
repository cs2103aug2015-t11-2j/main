//@@author A0127142R
package Logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import GUI.Yui_GUI;
import Parser.Parser;
import Storage.Storage;
import Tasks.Event;
import Tasks.NumberedEvent;

public class Action {

	private static ArrayList<Event> fullList;
	// successful operation msgs
	private static final String ADD_SUCCESSFUL_MSG = "Event added successfully!";
	private static final String DELETE_SUCCESSFUL_MSG = "Delete successfully!";
	private static final String UPDATE_SUCCESSFUL_MSG = "Event updated successfully!";
	private static final String UNDO_SUCCESSFUL_MSG = "Undo operation successful!";
	private static final String REDO_SUCCESSFUL_MSG = "Redo operation successful!";
	private static final String COMMENT_SUCCESSFUL_MSG = "Comment added successfully!";
	private static final String PRIORITY_SUCCESSFUL_MSG = "Recur set successfully!";
	private static final String MARK_SUCCESSFUL_MSG = "Event marked successfully!";
	private static final String UNMARK_SUCCESSFUL_MSG = "Event unmarked successfully!";
	private static final String CHANGE_BG_SUCCESSFUL_MSG = "Background is changed successfully!";
	private static final String SETPATH_SUCCESSFUL_MSG = "New path set successfully!";
	private static final String SEARCH_FOUND_SUCCESSFUL_MSG = "The list of found results are on the right!";
	private static final String CLEARALL_SUCCESSFUL_MSG = "All contents cleared! Please Undo now if you made a mistake!";
	private static final String NUS_MOD_SUCCESSFUL_MSG = "Show nusmods successfully!";
	private static final String TODOLIST_SUCCESSFUL_MSG = "Show TodoList successfully!";
	private static final String EVENT_TMR_DISPLAYED_SUCCESSFUL_MSG = "Events of tomorrow displayed on the right!";
	private static final String EVENT_TODAY_DISPLAYED_SUCCESSFUL_MSG = "Events of today displayed on the right!";
	private static final String EVENT_TODAY_AND_TMR_DISPLAYED_SUCCESSFUL_MSG = "Events of today and tomorrow displayed on the right!";
	private static final String CHANGE_BG_DEFAULT_SUCCESSFUL_MSG = "Background is changed to the default!";
	// fail operation msgs
	private static final String SEARCH_NOT_FOUND_MSG = "Cannot find the key words!";
	private static final String DELETE_OUT_OF_BOUND_MSG = "Cannot delete. Index entered is larger than current event amount!";
	private static final String UNABLE_UNDO_MSG = "Cannot undo! Do some operations first!";
	private static final String UNABLE_REDO_MSG = "Cannot redo if you did not undo!";
	private static final String INVALID_EVENT_PARAMETER_MSG = "Invalid Event!";
	private static final String EMPTY_ADD_PARAMETER_MSG = "Cannot add empty event!";
	private static final String NO_INDEX_TO_READ_MSG = "Please specify the event index to read!";
	private static final String INVALID_LIST_TYPE_MSG = "Please enter the correct event type (d, e or m) followed by the index!";
	private static final String READING_INDEX_OUTOFBOUND_MSG = "There is no event at the input index!";
	private static final String COMMENT_OUT_OF_BOUND_MSG = "Cannot add comment. Index entered is larger than current event amount!";
	private static final String PRIORITY_OUT_OF_BOUND_MSG = "Cannot set recur. Index entered is larger than current event amount!";
	private static final String MARK_OUT_OF_BOUND_MSG = "Cannot mark. Index entered is larger than current event amount!";
	private static final String INVALID_THEME_MSG = "Invalid theme selected!";
	private static final String NO_MY_THEME_MSG = "There is no custom user theme! \n Please add the picture in user.dir and name the file as myTheme.png";
	private static final String UNRECOGNIZABLE_CLEARALL_MSG = "You cannot enter a value after clearall command!";
	private static final String NO_EVENT_TODAY_MSG = "There is nothing to do today!";
	private static final String NO_EVENT_TMR_MSG = "There is nothing to do tomorrow!";
	private static final String UNMARK_OUT_OF_BOUND_MSG = "Cannot unmark. Index entered is larger than current event amount!";
	private static final String UPDATE_OUT_OF_BOUND_MSG = "Cannot undate. Index entered is larger than current event amount!";
	private static final String COMMAND_NOT_RECOGNIZED_IN_HELPLIST_MSG = "The command you entered is not found\n Please check to ensure you entered the correct command word!";
	private static final String IMPROPER_SEARCH_KEY_MSG = "Please enter a proper keyword!";
	private static final String NO_EVENT_TODAY_OR_TMR_MSG = "Have a rest! There is nothing to do today or tomorrow!";
	private static final String INVALID_RECUR_MSG = "Please enter the correct values for recur!";
	// help list
	private static final String HELPLIST = "add\n theme\n read\n outline\n delete\n search\n update\n undo\n redo\n comment\n recur\n mark\n readmark\n unmark\n setpath\n nusmods\n todolist\n hotkey\n help\n clearall\n exit";
	private static final String ADD_HELP_MSG = "You can add 3 types of event to your list, namely deadline event, event-time event and memo event\n For events with deadline, type in \"add (event name) by HH:MM DD/MM/YYYY\"\n For events with event time, type in \"add (event name) from HH:MM to HH:MM DD/MM/YYYY\"\n For events without a time, type in \"add (event name)\"";
	private static final String THEME_HELP_MSG = "You can change the picture you display on the right hand side panel. There are two pre-set pictures and you can change between them using \"theme 1\" (\"theme default\") and \"theme 2\"\n To use your own picture, manually add a picture of size 383*418 and file type png into the user.dir folder. Rename it to myTheme. Then you can change to this picture by typing in \"theme my theme\"";
	private static final String READ_HELP_MSG = "As you can see your events listed under three categories on the right, you can read detailed information about each one by typing in \"read\", event type (d, e, m) and index.\n For example, \"read d1\" can give you information on first deadline event.\n You can also use \"read\" to check all the events on today or tomorrow, simply type \"read today\" or \"read tomorrow(tmr)\"";
	private static final String OUTLINE_HELP_MSG = "You can type \"outline\" to view all the events of today and tomorrow";
	private static final String DELETE_HELP_MSG = "With reference to the right hand side panel, you can delete an event by typing in \"delete\", event type (d, e, m) and index.\n For example, \"delete d1\" will remove the first deadline event";
	private static final String SEARCH_HELP_MSG = "You can use the simple search to quickly locate an event. Type in \"search\" and your keyword. The events containing your keyword (ignore case) will be displayed on the right";
	private static final String UPDATE_HELP_MSG = "You can update the detail of an event by typing in \"update\", event type (d, e, m) and index, new event detail.\n For example, typing \"update m1 go to school\" will replace the event name of first event in memo to \"go to school\"";
	private static final String UNDO_HELP_MSG = "Type in \"undo\" to revert your previous changes. Keep in mind you can only undo the latest operation";
	private static final String REDO_HELP_MSG = "Type in \"redo\" to revert your undo operation";
	private static final String COMMENT_HELP_MSG = "You can set comment for events by typing in \"comment\", event type (d, e, m) and index, comment information.\n For example, typing \"comment e1 ASAP\" will set a comment for the first event in event-time event. You will see an icon highlighted for events with comments and you can read the comments by reading that event";
	private static final String PRIORITY_HELP_MSG = "You can set an event as recurring task by typing in \"recur\", event type (d, e, m) and index, the number of days for one iteration, the number of iterations desired.\n For example, typing \"recur d1 7 10\" will set the first deadline event as recurring event, which happens every 7 days (each week) and happens for 10 times (including the intial one)\n However keep in mind, you cannot recur a memo event as we do not know when you do it the first time!";
	private static final String MARK_HELP_MSG = "You can mark an event as done by typing in \"mark\", event type (d, e, m) and index.\n An event will not be seen once you mark it as done. If you made a mistake, undo or type in \"readmark\" to find the list of marked event. You can then use \"unmark\" and the index to unmark an event";
	private static final String HELP_HELP_MSG = "You can always refer to helplist for all the commands available\n Type in \"help\" you can see a list of all commands available\n Type in \"help\" and the command you want to know to learn more about it!";
	private static final String CLEARALL_HELP_MSG = "You can clear all the tasks in the list by typing in \"clearall\".\n Undo at once if you made a mistake! Otherwise you will permanently lose your list";
	private static final String EXIT_HELP_MSG = "You can exit the app by typing in \"exit\"";
	private static final String READMARK_HELP_MSG = "Type in \"readmark\" and you can see the list of marked event";
	private static final String UNMARK_HELP_MSG = "After you have get the list of marked event, you can \"unmark\" it to let it appear in your normal list again. Note that unmarking an event which is not marked previously will make no changes";
	private static final String SETPATH_HELP_MSG = "Type in \"setpath\" followed by the path you want to store your data file.\n Note that this will create a new empty file in the location you specified so please move your old data file to the new location if you still need the old list!";
	private static final String NUSMODS_HELP_MSG = "Type in \"nusmods\" and you can go to the NUSMODS website to allow you to check the timetable quickly!\n To go back to your list, just type \"todolist\"";
	private static final String TODOLIST_HELP_MSG = "Type in \"todolist\" to go back to your list from NUSMODS website or other features!";
	private static final String HOTKEY_HELP_MSG = "We provide the following hotkeys to allow easier control:\n \"��\" or \"��\" arrows to move the list on the right up or down\n Press \"Alt\" and \"��\" or \"��\" arrows to move the message on the left up or down\n Press \"Ctrl\" and \"H\" to hide Yui\n Press \"Ctrl\" and \"Y\" to show Yui";

	private static SimpleDateFormat deadline_format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	private static SimpleDateFormat eventStart_format = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat eventEnd_format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	private static boolean canUndo = false;
	private static boolean isShowNusMods = false;
	private static SimpleDateFormat formatCompare = new SimpleDateFormat("yyyyMMdd");
	protected static String configedTheme;

	public static String addToList(Storage s, ArrayList<String> parameter, ArrayList<String> sentence)
			throws IOException, ParseException {
		fullList = s.loadE();

		Event newEvent = new Event();
		try {
			newEvent = Parser.parseForEvent(parameter);
		} catch (ParseException e) {
			newEvent = Parser.parseForEvent(sentence);
		}
		if (newEvent.getDetail().equals("")) {
			readAll(s);
			return EMPTY_ADD_PARAMETER_MSG;
		} else {
			fullList.add(newEvent);
			s.saveE(fullList);
			canUndo = true;
			readAll(s);
			return ADD_SUCCESSFUL_MSG;
		}
	}

	protected static void exit() {
		MainLogic.shouldExit = true;
		System.exit(0);
	}

	public static void readAll(Storage s) throws IOException, ParseException {
		fullList = s.loadE();
		ArrayList<Event> temp = new ArrayList<Event>();
		for (int i = 0; i < fullList.size(); i++) {
			if (!fullList.get(i).getStatus().equalsIgnoreCase("done")) {
				temp.add(fullList.get(i));
			}
		}
		fullList = temp;
		Collections.sort(fullList);
	}

	// @@author A0133992X
	public static String bground(Storage s, ArrayList<String> parameter) throws IOException {
		if (!(parameter.get(0).toLowerCase().equals("default") || parameter.get(0).equals("1"))) {
			String theme = parameter.get(0);
			if (theme.equals("2")) {
				configedTheme = theme;
				saveConfigedTheme(s);
				return CHANGE_BG_SUCCESSFUL_MSG;
			} else if (theme.equals("my theme")) {
				configedTheme = theme;
				File myTheme = new File(Yui_GUI.listBackgroundPath3);
				if (myTheme.exists()) {
					saveConfigedTheme(s);
					return CHANGE_BG_SUCCESSFUL_MSG;
				} else {
					return NO_MY_THEME_MSG;
				}
			} else {
				return INVALID_THEME_MSG;
			}

		} else {
			configedTheme = "1";
			saveConfigedTheme(s);
			return CHANGE_BG_DEFAULT_SUCCESSFUL_MSG;
		}
	}

	// @@author A0133992X
	private static void saveConfigedTheme(Storage s) throws IOException {
		ArrayList<String> config = s.loadConfig();
		config.set(1, configedTheme);
		s.saveConfig(config);
	}

	// @@author A0127142R
	public static String searchKey(Storage s, ArrayList<String> parameterArrayList) throws IOException, ParseException {
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
				return SEARCH_FOUND_SUCCESSFUL_MSG;
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
			return UNDO_SUCCESSFUL_MSG;
		} else {
			return UNABLE_UNDO_MSG;
		}
	}

	public static String redo(Storage s) throws IOException, ParseException {
		if (!canUndo) {
			s.saveE(s.loadE(s.tempDir));
			canUndo = true;
			readAll(s);
			return REDO_SUCCESSFUL_MSG;
		} else {
			return UNABLE_REDO_MSG;
		}
	}

	public static ArrayList<NumberedEvent> getDeadlineList() throws IOException, ParseException {
		ArrayList<NumberedEvent> deadlineList = new ArrayList<NumberedEvent>();
		int indexCount = 0;
		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).getDeadline() != null) {
				deadlineList.add(new NumberedEvent(++indexCount, fullList.get(i)));
			}
		}
		return deadlineList;
	}

	public static ArrayList<NumberedEvent> getEventTimeList() throws IOException, ParseException {
		ArrayList<NumberedEvent> eventTimeList = new ArrayList<NumberedEvent>();
		int indexCount = 0;
		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).getEventTime() != null) {
				eventTimeList.add(new NumberedEvent(++indexCount, fullList.get(i)));
			}
		}
		return eventTimeList;
	}

	public static ArrayList<NumberedEvent> getFloatingList() throws IOException, ParseException {
		ArrayList<NumberedEvent> floatingList = new ArrayList<NumberedEvent>();
		int indexCount = 0;
		for (int i = 0; i < fullList.size(); i++) {
			if ((fullList.get(i).getDeadline() == null) && (fullList.get(i).getEventTime() == null)) {
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
			// ArrayList<NumberedEvent> memoEvent = getFloatingList();
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
			// for (NumberedEvent mEvent : memoEvent) {
			// fullList.remove(mEvent.getEvent());
			// }

			if (!hasEvent) {
				return NO_EVENT_TODAY_MSG;
			} else {
				return EVENT_TODAY_DISPLAYED_SUCCESSFUL_MSG;
			}

		} else if (parameter.get(0).equalsIgnoreCase("tomorrow") || parameter.get(0).equalsIgnoreCase("tmr"))

		{
			readAll(s);
			ArrayList<NumberedEvent> deadlineEvent = getDeadlineList();
			ArrayList<NumberedEvent> eventTimeEvent = getEventTimeList();
			// ArrayList<NumberedEvent> memoEvent = getFloatingList();
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
			// for (NumberedEvent mEvent : memoEvent) {
			// fullList.remove(mEvent.getEvent());
			// }
			if (!hasEvent) {
				return NO_EVENT_TMR_MSG;
			} else {
				return EVENT_TMR_DISPLAYED_SUCCESSFUL_MSG;
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

		ArrayList<String> readToday = new ArrayList<String>();
		ArrayList<String> readTmr = new ArrayList<String>();
		readToday.add("today");
		readTmr.add("tmr");
		readAll(s);

		readAll(s);
		ArrayList<NumberedEvent> deadlineEvent = getDeadlineList();
		ArrayList<NumberedEvent> eventTimeEvent = getEventTimeList();
		// ArrayList<NumberedEvent> memoEvent = getFloatingList();
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
		// for (NumberedEvent mEvent : memoEvent) {
		// fullList.remove(mEvent.getEvent());
		// }

		if (!hasEvent) {
			return NO_EVENT_TODAY_OR_TMR_MSG;
		} else {
			return EVENT_TODAY_AND_TMR_DISPLAYED_SUCCESSFUL_MSG;
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

	public static String update(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		if (parameter == null) {
			return INVALID_EVENT_PARAMETER_MSG;
		}
		int indexInFullList = Parser.indexInFullList(fullList,
				parameter.get(0).substring(0, parameter.get(0).indexOf(" ")));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return UPDATE_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			if (Parser.getUpdateEvent(fullList, parameter) == null) {
				return INVALID_EVENT_PARAMETER_MSG;
			}
			Event updatedEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(updatedEvent)) {
					temp.set(i, Parser.getUpdateEvent(fullList, parameter));
					break;
				}
			}
			s.saveE(temp);
			readAll(s);
			canUndo = true;
			return UPDATE_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	public static String deleteEvent(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		int indexInFullList = Parser.indexInFullList(fullList, parameter.get(0));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return DELETE_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			Event deletedEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(deletedEvent)) {
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

	public static String comment(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		String comment = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		int indexInFullList = Parser.indexInFullList(fullList,
				parameter.get(0).substring(0, parameter.get(0).indexOf(" ")));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return COMMENT_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			Event commentEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(commentEvent)) {
					temp.get(i).setComment(comment);
					break;
				}
			}
			s.saveE(temp);
			readAll(s);
			canUndo = true;
			return COMMENT_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	public static String recur(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		if (parameter.get(0).equals("") || !parameter.get(0).contains(" ")) {
			return INVALID_RECUR_MSG;
		}
		String priority = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		if (priority.equals("")) {
			return INVALID_RECUR_MSG;
		}
		int indexInFullList = Parser.indexInFullList(fullList,
				parameter.get(0).substring(0, parameter.get(0).indexOf(" ")));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return PRIORITY_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			String validity1 = priority.substring(0, priority.indexOf(" "));
			String validity2 = priority.substring(priority.indexOf(" ") + 1);
			if (!validity1.matches("[0-9]+") || !validity2.matches("[0-9]+")) {
				return INVALID_RECUR_MSG;
			}
			Event priorityEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(priorityEvent)) {
					temp.get(i).setPriority(priority);
					break;
				}
			}
			s.saveE(temp);
			readAll(s);
			canUndo = true;
			return PRIORITY_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	protected static void setRecur(Storage s) throws IOException, ParseException {
		// fullList = s.loadE();
		Date thisTime = new Date();
		ArrayList<NumberedEvent> deadlineEvent = getDeadlineList();
		ArrayList<NumberedEvent> eventTimeEvent = getEventTimeList();
		for (NumberedEvent temp : deadlineEvent) {
			if (!temp.getEvent().getPriority().equals("")) {
				if (thisTime.after(temp.getEvent().getDeadline().getDeadline())) {
					String paraFull = temp.getEvent().getPriority();
					int para1 = Integer.valueOf(paraFull.substring(0, paraFull.indexOf(" ")));
					int para2 = Integer.valueOf(paraFull.substring(paraFull.indexOf(" ") + 1));
					int noOfIteration = (int) ((thisTime.getTime()
							- temp.getEvent().getDeadline().getDeadline().getTime()) / (86400000 * para2) + 1);

					Date newDate = new Date(
							temp.getEvent().getDeadline().getDeadline().getTime() + noOfIteration * para1 * 86400000);
					int indexInFullList = Parser.indexInFullList(fullList, "d" + temp.getIndex());
					if (para2 <= 1) {
						fullList.remove(indexInFullList);
						return;
					} else {
						para2 -= noOfIteration;

						Event newEvent = temp.getEvent();
						newEvent.getDeadline().setDeadline(newDate);
						newEvent.setPriority(para1 + " " + para2);
						fullList.remove(indexInFullList);
						fullList.add(newEvent);
						s.saveE(fullList);
						readAll(s);
					}
				}
			}
		}
		for (NumberedEvent temp2 : eventTimeEvent) {
			if (!temp2.getEvent().getPriority().equals("")) {
				if (thisTime.after(temp2.getEvent().getEventTime().getEnd())) {
					String paraFull = temp2.getEvent().getPriority();
					int para1 = Integer.valueOf(paraFull.substring(0, paraFull.indexOf(" ")));
					int para2 = Integer.valueOf(paraFull.substring(paraFull.indexOf(" ") + 1));
					int noOfIteration = (int) ((thisTime.getTime() - temp2.getEvent().getEventTime().getEnd().getTime())
							/ (86400000 * para2) + 1);
					Date newDateStart = new Date(
							temp2.getEvent().getEventTime().getStart().getTime() + noOfIteration * para1 * 86400000);
					Date newDateEnd = new Date(
							temp2.getEvent().getEventTime().getEnd().getTime() + noOfIteration * para1 * 86400000);
					int indexInFullList = Parser.indexInFullList(fullList, "e" + temp2.getIndex());
					if (para2 <= 1) {
						fullList.remove(indexInFullList);
						return;
					} else {
						para2 -= noOfIteration;

						Event newEvent = temp2.getEvent();
						newEvent.getEventTime().setStart(newDateStart);
						newEvent.getEventTime().setEnd(newDateEnd);
						newEvent.setPriority(para1 + " " + para2);
						fullList.remove(indexInFullList);
						fullList.add(newEvent);
						s.saveE(fullList);
						readAll(s);
					}
				}
			}
		}

	}

	public static String mark(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		int indexInFullList = Parser.indexInFullList(fullList, parameter.get(0));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return MARK_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			Event markedEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(markedEvent)) {
					temp.get(i).setStatus("done");
					break;
				}
			}
			s.saveE(temp);
			readAll(s);
			canUndo = true;
			return MARK_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	// @@author A0133992X
	public static String nusmods() {
		isShowNusMods = true;
		return NUS_MOD_SUCCESSFUL_MSG;
	}

	// @@author A0133992X
	public static String todolist() {
		isShowNusMods = false;
		return TODOLIST_SUCCESSFUL_MSG;
	}

	// @@author A0133992X
	public static boolean getIsShow() {
		return isShowNusMods;
	}

	// @@author A0127142R
	public static String clearAll(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		if (!parameter.get(0).equals("")) {
			return UNRECOGNIZABLE_CLEARALL_MSG;
		} else {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add("temp");
			addToList(s, temp, temp);
			s.reset();
			s = new Storage("Yui");
			canUndo = true;
			readAll(s);
			return CLEARALL_SUCCESSFUL_MSG;
		}
	}

	public static void readMark(Storage s) throws IOException, ParseException {
		fullList = s.loadE();
		ArrayList<Event> temp = new ArrayList<Event>();
		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).getStatus().equalsIgnoreCase("done")) {
				temp.add(fullList.get(i));
			}
		}
		fullList = temp;
		Collections.sort(fullList);
	}

	public static String unmark(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		int indexInFullList = Parser.indexInDoneList(fullList, parameter.get(0));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return UNMARK_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			Event markedEvent = fullList.get(indexInFullList);
			ArrayList<Event> temp = s.loadE();
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i).equals(markedEvent)) {
					temp.get(i).setStatus("");
					break;
				}
			}
			s.saveE(temp);
			readAll(s);
			canUndo = true;
			return UNMARK_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	// @@author A0133992X
	public static String setpath(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		MainLogic.s = new Storage("Yui", Paths.get(parameter.get(0)));
		ArrayList<String> config = MainLogic.s.loadConfig();
		config.set(0, Paths.get(parameter.get(0)).toString());
		MainLogic.s.saveConfig(config);
		readAll(MainLogic.s);
		return SETPATH_SUCCESSFUL_MSG;
	}

	// @@author A0127142R
	public static String help(ArrayList<String> parameterArrayList) {
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
		} else if (parameter.equals("recur")) {
			return PRIORITY_HELP_MSG; // used for recur
		} else if (parameter.equals("mark")) {
			return MARK_HELP_MSG;
		} else if (parameter.equals("readmark")) {
			return READMARK_HELP_MSG;
		} else if (parameter.equals("unmark")) {
			return UNMARK_HELP_MSG;
		} else if (parameter.equals("setpath")) {
			return SETPATH_HELP_MSG;
		} else if (parameter.equals("nusmods")) {
			return NUSMODS_HELP_MSG;
		} else if (parameter.equals("todolist")) {
			return TODOLIST_HELP_MSG;
		} else if (parameter.equals("hotkey")) {
			return HOTKEY_HELP_MSG;
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
