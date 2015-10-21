package main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Converter {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	static ArrayList<String> eventToString(ArrayList<Event> eventList) {
		ArrayList<String> arr = new ArrayList<String>();
		for (Event e : eventList) {
			arr.add(e.detail);
			arr.add(e.comment);
			arr.add(e.status);
			arr.add(e.priority);
			if (e.deadline != null) {
				arr.add(dateFormat.format(e.deadline.deadline));
			} else {
				arr.add("------");
			}
			if (e.eventTime != null) {
				arr.add(dateFormat.format(e.eventTime.start));
				arr.add(dateFormat.format(e.eventTime.end));
			} else {
				arr.add("------");
				arr.add("------");
			}
			
		}
		
		return arr;

	}

	// ParseException: str not in correct format to parse, throw saying corrupt save file
	static ArrayList<Event> stringToEvent(ArrayList<String> arr) throws ParseException, IOException {
		ArrayList<Event> eventList = new ArrayList<Event>();
		//SimpleDateFormat dt = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		
		if (arr.size() % 7 != 0) {
			throw new IOException("invalid array size");
		}

		while(arr.size() != 0){
			Event e = new Event();
			e.detail = arr.get(0);
			e.comment = arr.get(1);
			e.status = arr.get(2);
			e.priority = arr.get(3);
			if (!arr.get(4).equals("------")) {
				e.deadline = new Deadline();
				e.deadline.deadline = dateFormat.parse(arr.get(4));
			}
			if (!arr.get(5).equals("------") && !arr.get(6).equals("------")) {
				e.eventTime = new EventTime();
				e.eventTime.start = dateFormat.parse(arr.get(5));
				e.eventTime.end = dateFormat.parse(arr.get(6));
			}
			eventList.add(e);
			for(int i = 0; i < 7; i++){
				arr.remove(0);
			}
		}
		return eventList;

	}

}
