package main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Converter {

	static ArrayList<String> eventToString(ArrayList<Event> eventList) {
		ArrayList<String> arr = new ArrayList<String>();
		for (Event e : eventList) {
			arr.add(e.detail);
			arr.add(e.comment);
			arr.add(e.status);
			arr.add(e.priority);
			if (e.deadline != null) {
				arr.add(e.deadline.getTime().toString());
			} else {
				arr.add("X");
			}
			if (e.start != null) {
				arr.add(e.start.getTime().toString());
			} else {
				arr.add("X");
			}
			if (e.end != null) {
				arr.add(e.end.getTime().toString());
			} else {
				arr.add("X");
			}
		}

		return arr;

	}

	// ParseException: str not in correct format to parse
	static ArrayList<Event> stringToEvent(ArrayList<String> arr) throws ParseException, IOException {
		ArrayList<Event> eventList = new ArrayList<Event>();
		SimpleDateFormat dt = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		// cannot use null, since input data lacks all information
		Calendar cal = Calendar.getInstance();
		
		if (arr.size() % 7 != 0) {
			throw new IOException("invalid array size");
		}

		while (arr.size() != 0) {
			Event e = new Event();
			e.detail = arr.remove(0);
			e.comment = arr.remove(0);
			e.status = arr.remove(0);
			e.priority = arr.remove(0);
			if (arr.get(0) != "X") {
				cal.setTime(dt.parse(arr.remove(0)));
				e.deadline = cal;
			}
			if (arr.get(0) != "X") {
				cal.setTime(dt.parse(arr.remove(0)));
				e.start = cal;
			}
			if (arr.get(0) != "X") {
				cal.setTime(dt.parse(arr.remove(0)));
				e.end = cal;
			}
			eventList.add(e);
		}
		return eventList;

	}

}
