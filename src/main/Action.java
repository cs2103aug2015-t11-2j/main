package main;

import java.io.IOException;
import java.util.ArrayList;

public class Action {

	private static final String SEARCH_NOT_FOUND_MSG = "Cannot find the key words!";
	private static final String DELETE_OUT_OF_BOUND_MSG = "Cannot delete. Index entered is larger than current event amount!";
	private static final String DELETE_SUCCESSFUL_MSG = "Delete successful!";
	private static final String ADD_SUCCESS_MSG = "Event added successful!";
	private static final String UPDATE_SUCCESS_MSG = "Event updated successfully!";
	private static final String NO_EVENT_MSG = "Your event list is empty!";
	private static final String UNDO_MSG = "Undo operation successful!";

	static String addToList(Storage s, ArrayList<String> list, String parameter) throws IOException {
		list.add(parameter);
		s.save(list);
		return ADD_SUCCESS_MSG;
	}

	static String show(Storage s, String parameter) throws IOException {
		StringBuilder output = new StringBuilder();
		ArrayList<String> list = s.load(s.mainDir);
		if (parameter.length() == 0) {
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					output.append((i + 1) + ". " + list.get(i) + "\n");
				} else {
					output.append(" " + (i + 1) + ". " + list.get(i) + "\n");
				}
			}
			if (output.length() == 0) {
				return NO_EVENT_MSG;
			} else {
				output.deleteCharAt(output.length() - 1);// remove the last new
															// line
				return output.toString();
			}
		} else {
			return parameter;
		}
	}

	static void exit() {
		ToDoList.shouldExit = true;
		System.exit(0);
	}

	static String searchKey(ArrayList<String> list, String parameter) {
		return searchResult(list, parameter);
	}

	private static String searchResult(ArrayList<String> list, String parameter) {
		if (list.size() == 0 || parameter == null) {
			return SEARCH_NOT_FOUND_MSG;
		} else {
			StringBuilder resultList = new StringBuilder();
			boolean isFound = false;
			int index = 1;
			for (int i = 0; i < list.size(); i++) {
				String task = list.get(i);
				// if key word found ignore case
				if (task.toLowerCase().contains(parameter.toLowerCase())) {
					isFound = true; // set status found
					resultList.append(index++);
					resultList.append(". ");
					resultList.append(task);
					resultList.append("\n");
				}
			}
			if (isFound) {
				return resultList.toString();
			} else {
				return SEARCH_NOT_FOUND_MSG; // no result found
			}
		}

	}

	static String deleteEvent(Storage s, ArrayList<String> list, String parameter) throws IOException {
		if (Integer.valueOf(parameter) > Integer.valueOf(list.size())) {
			return DELETE_OUT_OF_BOUND_MSG;
		} else {
			list.remove(Integer.valueOf(parameter) - 1);
			s.save(list);
			return DELETE_SUCCESSFUL_MSG;
		}
	}

	static String update(Storage s, ArrayList<String> list, String parameter) throws IOException {
		list.set(Parser.getUpdateIndex(parameter), Parser.getUpdateParameter(parameter));
		s.save(list);
		return UPDATE_SUCCESS_MSG;
	}

	public static String undo(Storage s) throws IOException {
		s.save(s.load(s.tempDir));
		return UNDO_MSG;

	}

}
