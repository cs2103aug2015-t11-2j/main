package main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Converter {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	static ArrayList<String> eventToString(ArrayList<Event> eventList) {
		ArrayList<String> arr = new ArrayList<String>();
		for (Event e : eventList) {
			arr.add(e.detail);
			arr.add(e.comment);
			arr.add(e.status);
			arr.add(e.priority);
			if (e.deadline != null) {
				arr.add(dateFormat.format(e.deadline.deadline.getTime()));
			} else {
				arr.add("X");
			}
			if (e.eventTime != null) {
				arr.add(dateFormat.format(e.eventTime.start.getTime()));
				arr.add(dateFormat.format(e.eventTime.end.getTime()));
			} else {
				arr.add("X");
				arr.add("X");
			}
			
		}
		
		return arr;

	}

	// ParseException: str not in correct format to parse, throw saying corrupt save file
	static ArrayList<Event> stringToEvent(ArrayList<String> arr) throws ParseException, IOException {
		ArrayList<Event> eventList = new ArrayList<Event>();
		//SimpleDateFormat dt = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
		Calendar cal = Calendar.getInstance();
		
		if (arr.size() % 7 != 0) {
			throw new IOException("invalid array size");
		}

		while(arr.size() != 0){
			Event e = new Event();
			e.detail = arr.get(0);
			e.comment = arr.get(1);
			e.status = arr.get(2);
			e.priority = arr.get(3);
			if (!arr.get(4).equals("X")) {
				e.deadline = new Deadline();
				cal.setTime(dateFormat.parse(arr.get(4)));
				e.deadline.deadline = cal;
			}
			if (!arr.get(5).equals("X") && !arr.get(6).equals("X")) {
				e.eventTime = new EventTime();
				cal.setTime(dateFormat.parse(arr.get(5)));
				e.eventTime.start = cal;
				cal.setTime(dateFormat.parse(arr.get(6)));
				e.eventTime.end = cal;
			}
			eventList.add(e);
			for(int i = 0; i < 7; i++){
				arr.remove(0);
			}
		}
		return eventList;

	}

}
