//@@author A0127821J


package Junit_Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.junit.Test;

import Storage.Storage;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.EventTime;
import junit.framework.TestCase;

public class SUTStorage extends TestCase {

	// note to converter: string to event delete original string arr
	// wont affect main program, it does not use string arr
	@Test
	public void test() throws Exception {

		ArrayList<Event> arrE1 = new ArrayList<Event>();
		ArrayList<Event> arrE2 = new ArrayList<Event>();
		ArrayList<String> arr1 = new ArrayList<String>();
		//ArrayList<String> arr2 = new ArrayList<String>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dateS = "01/02/2013 04:55:56";
		Storage s = new Storage("SUTStorage.txt");

		//template arraylist string version
		arr1.add("e1 detail");
		arr1.add("e1 comment");
		arr1.add("e1 status");
		arr1.add("e1 priority");
		arr1.add(dateS);
		arr1.add("------");
		arr1.add("------");

		arr1.add("e2 detail");
		arr1.add("e2 comment");
		arr1.add("e2 status");
		arr1.add("e2 priority");
		arr1.add("------");
		arr1.add(dateS);
		arr1.add(dateS);

		// event with deadline
		Event e1 = new Event();
		e1.setComment("e1 comment");
		e1.setDetail("e1 detail");
		e1.setStatus("e1 status");
		e1.setPriority("e1 priority");
		e1.setDeadline(new Deadline());
		e1.getDeadline().setDeadline(dateFormat.parse(dateS));

		// event with event time
		Event e2 = new Event();
		e2.setComment("e2 comment");
		e2.setDetail("e2 detail");
		e2.setStatus("e2 status");
		e2.setPriority("e2 priority");
		e2.setEventTime(new EventTime());
		e2.getEventTime().setStart(dateFormat.parse(dateS));
		e2.getEventTime().setEnd(dateFormat.parse(dateS));

		arrE1.add(e1);
		arrE1.add(e2);

		s.saveE(arrE1);
		arrE2 = s.loadE();

		// testing
		/*
		 * combining multiple input: if it works for null state of eventTime
		 * and null state of Deadline, it will work for null state of both,
		 * which is floating event so no need to test
		 */
		//test for converter
		//assertEquals(Converter.eventToString(arrE1), arr1);
		//test for save load
		assertEquals(arrE1, arrE2);
	}

}
