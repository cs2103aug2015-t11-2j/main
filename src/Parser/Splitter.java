//@@author A0125622N

package Parser;

import java.util.*;

public class Splitter {
	
	public static String removeFirstWord(String userCommand) {
		return userCommand.replaceFirst(getFirstWord(userCommand), "").trim();
	}
	
	public static String getFirstWord(String userCommand) {
		String commandTypeString = userCommand.trim().split("\\s+")[0];
		return commandTypeString;
	}
	
	public static ArrayList<String> splitEvent(ArrayList<String> parameter, String unsplitParameter) {
		try {
			String[] parameters = unsplitParameter.split("from |to ");
			parameter.add(parameters[0]); // event
			parameter.add(parameters[1]); // start time
			String[] timeAndDate = parameters[2].split("\\s+");
			parameter.add(timeAndDate[0]); // end time
			parameter.add(timeAndDate[1]); // date
		} catch (Exception e) {
			return null; // act as boolean
		}
		return parameter;
	}
	
	public static ArrayList<String> splitDeadline(ArrayList<String> parameter, String unsplitParameter) {
		try {
			String[] parameters = unsplitParameter.split("by ");
			parameter.add(parameters[0]); // event
			String[] timeAndDate = parameters[1].split("\\s+");
			parameter.add(timeAndDate[0]); // time
			parameter.add(timeAndDate[1]); // date
		} catch (Exception e) {
			return null;
		}
		return parameter;
	}
}
