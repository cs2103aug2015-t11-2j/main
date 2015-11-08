////@@author A0127821J


package Storage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.EventTime;

public class Converter {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	static ArrayList<String> eventToString(ArrayList<Event> eventList) {
		ArrayList<String> arr = new ArrayList<String>();
		for (Event e : eventList) {
			arr.add(e.getDetail());
			arr.add(e.getComment());
			arr.add(e.getStatus());
			arr.add(e.getPriority());
			if (e.getDeadline() != null) {
				arr.add(dateFormat.format(e.getDeadline().getDeadline()));
			} else {
				arr.add("------");
			}
			if (e.getEventTime() != null) {
				arr.add(dateFormat.format(e.getEventTime().getStart()));
				arr.add(dateFormat.format(e.getEventTime().getEnd()));
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
			e.setDetail(arr.get(0));
			e.setComment(arr.get(1));
			e.setStatus(arr.get(2));
			e.setPriority(arr.get(3));
			if (!arr.get(4).equals("------")) {
				e.setDeadline(new Deadline());
				e.getDeadline().setDeadline(dateFormat.parse(arr.get(4)));
			}
			if (!arr.get(5).equals("------") && !arr.get(6).equals("------")) {
				e.setEventTime(new EventTime());
				e.getEventTime().setStart(dateFormat.parse(arr.get(5)));
				e.getEventTime().setEnd(dateFormat.parse(arr.get(6)));
			}
			eventList.add(e);
			for(int i = 0; i < 7; i++){
				arr.remove(0);
			}
		}
		return eventList;

	}

}
