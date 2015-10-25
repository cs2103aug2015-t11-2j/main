package main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Yui.Yui_GUI;

public class Action {

	private static final String SEARCH_NOT_FOUND_MSG = "Cannot find the key words!";
	private static final String DELETE_OUT_OF_BOUND_MSG = "Cannot delete. Index entered is larger than current event amount!";
	private static final String DELETE_SUCCESSFUL_MSG = "Delete successful!";
	private static final String ADD_SUCCESS_MSG = "Event added successful!";
	private static final String UPDATE_SUCCESS_MSG = "Event updated successfully!";
	// private static final String NO_EVENT_MSG = "Your event list is empty!";
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
	private static SimpleDateFormat deadline_format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	private static SimpleDateFormat eventStart_format = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat eventEnd_format = new SimpleDateFormat("HH:mm dd/MM/yyyy");
	private static boolean canUndo = false;

	static String addToList(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		ArrayList<Event> list = s.loadE();
		Event newEvent = Parser.parseForEvent(parameter);
		if (newEvent == null) {
			return INVALID_ADD_PARAMETER_MSG;
		} else {
			list.add(newEvent);
			s.saveE(list);
			canUndo = true;
			return ADD_SUCCESS_MSG;
		}
	}

	static void exit() {
		ToDoList.shouldExit = true;
		System.exit(0);
	}

	public static String bground(ArrayList<String> parameter){
		if(!(parameter.get(0).equals("default")||parameter.get(0).equals("1"))){
			String theme = parameter.get(0);
			if(theme.equals("2")){
				Yui_GUI.listBkImage = Yui_GUI.listBkImage2;
				return CHANGR_BK_SUCCESSFUL;
			} else {
				return INVALID_THEME;
			}
			
		} else {
			//Yui_GUI.listBackgroundPath = "listBK2.png";
			Yui_GUI.listBkImage = Yui_GUI.listBkImage1;
			return CHANGR_BK_DEFAULT;
		}
	}
	
	static String searchKey(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		return searchResult(s.loadE(), parameter.get(0));
	}

	private static String searchResult(ArrayList<Event> list, String parameter) {
		if (list.size() == 0 || parameter == null) {
			return SEARCH_NOT_FOUND_MSG;
		} else {
			StringBuilder resultList = new StringBuilder();
			boolean isFound = false;
			int index = 0;
			for (int i = 0; i < list.size(); i++) {
				String task = list.get(i).getDetail();
				// if key word found ignore case
				if (task.toLowerCase().contains(parameter.toLowerCase())) {
					isFound = true; // set status found
					index++;
					if(index > 1){
						resultList.append(" ");
					}
					resultList.append(index);
					resultList.append(". ");
					resultList.append(task);
					resultList.append("\n");
				}
			}
			if (isFound) {
				return resultList.deleteCharAt(resultList.length()-1).toString();
			} else {
				return SEARCH_NOT_FOUND_MSG; // no result found
			}
		}

	}

	public static String undo(Storage s) throws IOException, ParseException {
		if (canUndo) {
			s.saveE(s.loadE(s.tempDir));
			canUndo = false;
			return UNDO_MSG;
		} else {
			return UNABLE_UNDO_MSG;
		}
	}

	public static String redo(Storage s) throws IOException, ParseException {
		if (!canUndo) {
			s.saveE(s.loadE(s.tempDir));
			canUndo = true;
			return REDO_MSG;
		} else {
			return UNABLE_REDO_MSG;
		}
	}

	public static ArrayList<NumberedEvent> getDeadlineList(Storage s) throws IOException, ParseException {
		ArrayList<Event> fullList = s.loadE();
		ArrayList<NumberedEvent> deadlineList = new ArrayList<NumberedEvent>();
		int indexCount = 0;
		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).getDeadline() != null && !fullList.get(i).status.equalsIgnoreCase("done")) {
				deadlineList.add(new NumberedEvent(++indexCount, fullList.get(i)));

			}
		}
		return deadlineList;
	}

	public static ArrayList<NumberedEvent> getEventTimeList(Storage s) throws IOException, ParseException {
		ArrayList<Event> fullList = s.loadE();
		ArrayList<NumberedEvent> eventTimeList = new ArrayList<NumberedEvent>();
		int indexCount = 0;
		for (int i = 0; i < fullList.size(); i++) {
			if (fullList.get(i).getEventTime() != null && !fullList.get(i).status.equalsIgnoreCase("done")) {
				eventTimeList.add(new NumberedEvent(++indexCount, fullList.get(i)));
			}
		}
		return eventTimeList;
	}

	public static ArrayList<NumberedEvent> getFloatingList(Storage s) throws IOException, ParseException {
		ArrayList<Event> fullList = s.loadE();
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

	/*
	 * static String show(Storage s, ArrayList<String> parameter) throws
	 * IOException { StringBuilder output = new StringBuilder();
	 * ArrayList<String> list = s.load(s.mainDir); if (parameter.size() == 0) {
	 * for (int i = 0; i < list.size(); i++) { if (i == 0) { output.append((i +
	 * 1) + ". " + list.get(i) + "\n"); } else { output.append(" " + (i + 1) +
	 * ". " + list.get(i) + "\n"); } } if (output.length() == 0) { return
	 * NO_EVENT_MSG; } else { output.deleteCharAt(output.length() - 1);// remove
	 * the last new // line return output.toString(); } } else { return null; }
	 * }
	 */

	public static String read(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		StringBuilder output = new StringBuilder();
		if (parameter.size() == 0) {
			return NO_INDEX_TO_READ_MSG;
		} else {
			if (parameter.get(0).toLowerCase().contains("d")) {
				ArrayList<NumberedEvent> list = getDeadlineList(s);
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
				ArrayList<NumberedEvent> list = getEventTimeList(s);
				int index = Integer.valueOf(parameter.get(0).substring(1));
				if (index > list.size()) {
					return READING_INDEX_OUTOFBOUND_MSG;
				} else {
					Event reading = list.get(index - 1).getEvent();
					output.append("Detail: " + reading.getDetail() + "\n");
					output.append(" Comment: " + reading.getComment() + "\n");
					output.append(" Time: " + eventStart_format.format(reading.getEventTime().getStart())
							+ eventEnd_format.format(reading.getEventTime().getEnd()));
				}
			} else if (parameter.get(0).toLowerCase().contains("m")) {
				ArrayList<NumberedEvent> list = getFloatingList(s);
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

	static String update(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		ArrayList<Event> list = s.loadE();
		list.set(Parser.getUpdateIndex(s, parameter), Parser.getUpdateEvent(s, parameter));
		s.saveE(list);
		canUndo = true;
		return UPDATE_SUCCESS_MSG;
	}

	static String deleteEvent(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		ArrayList<Event> list = s.loadE();
		int indexInFullList = Parser.indexInFullList(s, parameter.get(0));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return DELETE_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			list.remove(indexInFullList);
			s.saveE(list);
			canUndo = true;
			return DELETE_SUCCESSFUL_MSG;
		} else {
			return null;
		}

		/*
		 * if (parameter.get(0).toLowerCase().contains("d")) { int index =
		 * Integer.valueOf(parameter.get(0).substring(1)); int count = 0; for
		 * (int i = 0; i < list.size(); i++) { if (list.get(i).getDeadline() !=
		 * null) { count++; if (count == index) { list.remove(i); s.saveE(list);
		 * canUndo = true; return DELETE_SUCCESSFUL_MSG; } } } if (count <
		 * index) { return DELETE_OUT_OF_BOUND_MSG; } } else if
		 * (parameter.get(0).toLowerCase().contains("e")) { int index =
		 * Integer.valueOf(parameter.get(0).substring(1)); int count = 0; for
		 * (int i = 0; i < list.size(); i++) { if (list.get(i).getEventTime() !=
		 * null) { count++; if (count == index) { list.remove(i); s.saveE(list);
		 * canUndo = true; return DELETE_SUCCESSFUL_MSG; } } } if (count <
		 * index) { return DELETE_OUT_OF_BOUND_MSG; } } else if
		 * (parameter.get(0).toLowerCase().contains("f")) { int index =
		 * Integer.valueOf(parameter.get(0).substring(1)); int count = 0; for
		 * (int i = 0; i < list.size(); i++) { if (list.get(i).getEventTime() ==
		 * null && list.get(i).getDeadline() == null) { count++; if (count ==
		 * index) { list.remove(i); s.saveE(list); canUndo = true; return
		 * DELETE_SUCCESSFUL_MSG; } } } if (count < index) { return
		 * DELETE_OUT_OF_BOUND_MSG; } } else { return INVALID_LIST_TYPE_MSG; }
		 * return null;
		 */
	}

	static String comment(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		ArrayList<Event> list = s.loadE();
		String comment = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		int indexInFullList = Parser.indexInFullList(s, parameter.get(0).substring(0, parameter.get(0).indexOf(" ")));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return COMMENT_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			list.get(indexInFullList).comment = comment;
			s.saveE(list);
			canUndo = true;
			return COMMENT_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	static String priority(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		ArrayList<Event> list = s.loadE();
		String priority = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		int indexInFullList = Parser.indexInFullList(s, parameter.get(0).substring(0, parameter.get(0).indexOf(" ")));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return PRIORITY_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			list.get(indexInFullList).priority = priority;
			s.saveE(list);
			canUndo = true;
			return PRIORITY_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

	static String mark(Storage s, ArrayList<String> parameter) throws IOException, ParseException {
		ArrayList<Event> list = s.loadE();
		String status = parameter.get(0).substring(parameter.get(0).indexOf(" ") + 1);
		int indexInFullList = Parser.indexInFullList(s, parameter.get(0).substring(0, parameter.get(0).indexOf(" ")));
		if (indexInFullList == -2) {
			return INVALID_LIST_TYPE_MSG;
		} else if (indexInFullList == -1) {
			return MARK_OUT_OF_BOUND_MSG;
		} else if (indexInFullList >= 0) {
			list.get(indexInFullList).status = status;
			s.saveE(list);
			canUndo = true;
			return MARK_SUCCESSFUL_MSG;
		} else {
			return null;
		}
	}

}
