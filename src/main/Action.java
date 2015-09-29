package main;


import java.io.IOException;
import java.util.ArrayList;

public class Action {

	private static final String SEARCH_NOT_FOUND_MSG = "Cannot find the key words!";
	private static final String DELETE_OUT_OF_BOUND_MSG = "Cannot delete. Index entered is larger than current event amount!";
	private static final String DELETE_SUCCESSFUL_MSG = "Delete successful!";
	private static final String ADD_SUCCESS_MSG = "Event added successful!";

	static String addToList(ArrayList<String> list, String parameter) throws IOException {
		list.add(parameter);
		Storage.save(list);
		return ADD_SUCCESS_MSG;
	}

	static String showAll(ArrayList<String> list) {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			output.append((i+1)+". "+list.get(i));
		}
		return output.toString();
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
					resultList.append(task);
				}
			}
			if (isFound) {
				return resultList.toString();
			} else {
				return SEARCH_NOT_FOUND_MSG; // no result found
			}
		}

	}

	static String deleteEvent(ArrayList<String> list, String parameter) throws IOException {
		if (Integer.valueOf(parameter)>Integer.valueOf(list.size())){
			return DELETE_OUT_OF_BOUND_MSG;
		} else {
			list.remove(Integer.valueOf(parameter)-1);
			Storage.save(list);
			return DELETE_SUCCESSFUL_MSG;
		}
	}

	static String chooseEvent(ArrayList<String> list, String parameter) {
		// TODO Auto-generated method stub
		return parameter;
	}
}
