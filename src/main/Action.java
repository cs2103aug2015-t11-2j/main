package main;

import java.util.ArrayList;

public class Action {

	private static final String SEARCH_NOT_FOUND_MSG = "Cannot find the key words!";
	private static final String DELETE_OUT_OF_BOUND_MSG = "Cannot delete. Index entered is larger than current event amount!";
	private static final String DELETE_SUCCESSFUL_MSG = "Delete successful!";

	static void addToList(ArrayList<String> list, String parameter) {
		list.add(parameter);
	}

	static void showAll(ArrayList<String> list) {
		printEvent(list);
	}

	private static void printEvent(ArrayList<String> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i+1)+". "+list.get(i));
		}
	}

	static void exit() {
		ToDoList.shouldExit = true;
	}

	static void searchKey(ArrayList<String> list, String parameter) {
		String result = searchResult(list, parameter);
		System.out.print(result);
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

	static void deleteEvent(ArrayList<String> list, String parameter) {
		if (Integer.valueOf(parameter)>Integer.valueOf(list.size())){
			System.out.println(DELETE_OUT_OF_BOUND_MSG);
		} else {
			list.remove(Integer.valueOf(parameter)-1);
			System.out.println(DELETE_SUCCESSFUL_MSG);
		}
	}

	static void chooseEvent(ArrayList<String> list, String parameter) {
		// TODO Auto-generated method stub

	}
}
