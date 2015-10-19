package main;

public class Parser {
	
	public static String getAction(String userCommand){
		String action = Splitter.getFirstWord(userCommand).toLowerCase();
		return action;
	}
	
	/*public static String getParameter(String userCommand){
		String parameter = Splitter.removeFirstWord(userCommand);
		return parameter;
	}*/
	
	public static ArrayList<String> getParameter(String userCommand){
		String action = getAction(userCommand);
		ArrayList<String> parameter = new ArrayList<String>();
		if (action.equals("add")){
			String unsplitParameter = Splitter.removeFirstWord(userCommand);
			if (unsplitParameter.contains("from")){ //event with specific time interval
				parameter = Splitter.splitEvent(parameter, unsplitParameter);
			}
			
			else if (unsplitParameter.contains("by")){ //deadline
				parameter = Splitter.splitDeadline(parameter, unsplitParameter);
			}
		}
		else{
			parameter.add(Splitter.removeFirstWord(userCommand)); //no time specified
		}
		
		return parameter;
	}
	
	public static int getUpdateIndex(String parameter) { 
		return Integer.valueOf(parameter.substring(0, parameter.indexOf(" ")))-1;
	}

	public static String getUpdateParameter(String parameter) {
		return parameter.substring(parameter.indexOf(" ") + 1);
	}
}
