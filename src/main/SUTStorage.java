package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.junit.Test;

import junit.framework.TestCase;

public class SUTStorage extends TestCase {

	// note to converter: string to event delete original string arr
	// wont affect main program, it does not use string arr
	@Test
	public void test() throws Exception {

		ArrayList<Event> arrE1 = new ArrayList<Event>();
		ArrayList<Event> arrE2 = new ArrayList<Event>();
		ArrayList<String> arr1 = new ArrayList<String>();
		ArrayList<String> arr2 = new ArrayList<String>();
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
		e1.comment = "e1 comment";
		e1.detail = "e1 detail";
		e1.status = "e1 status";
		e1.priority = "e1 priority";
		e1.deadline = new Deadline();
		e1.deadline.deadline = dateFormat.parse(dateS);

		// event with event time
		Event e2 = new Event();
		e2.comment = "e2 comment";
		e2.detail = "e2 detail";
		e2.status = "e2 status";
		e2.priority = "e2 priority";
		e2.eventTime = new EventTime();
		e2.eventTime.start = dateFormat.parse(dateS);
		e2.eventTime.end = dateFormat.parse(dateS);
		
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
		assertEquals(Converter.eventToString(arrE1), arr1);
		//test for save load
		assertEquals(Converter.eventToString(arrE1), Converter.eventToString(arrE2));
	}

}
