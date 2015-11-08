//@@author A0133992X
package Junit_Test;

import java.io.IOException;
import java.text.ParseException;

import Logic.MainLogic;
import junit.framework.TestCase;

public class SystemTest extends TestCase{
	private static String SPACE = " ";
	
	public void testClear() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("clearall").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "clearall" + "\n" + SPACE 
								+ "All contents cleared! Please Undo now if you made a mistake!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddMemo() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("add test memo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test memo" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddEventWithFullDate() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("add test Event from 10:00 to 11:00 04/11/2015").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test Event from 10:00 to 11:00 04/11/2015" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddDeadlineWithFullDate() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("add test deadline by 10:00 04/11/2015").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test deadline by 10:00 04/11/2015" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddEventWithWordDate() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("add test Event from 10:00 to 11:00 today").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test Event from 10:00 to 11:00 today" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddDeadlineWithWordDate() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("add test deadline by 10:00 tomorrow").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test deadline by 10:00 tomorrow" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddEventWithShortDate() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("add test Event from 10:00 to 11:00 tmr").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test Event from 10:00 to 11:00 tmr" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddDeadlineWithShortDate() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("add test deadline by 10:00 tmr").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test deadline by 10:00 tmr" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testOuline() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("outline").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "outline" + "\n" + SPACE 
								+ "Events of today and tomorrow displayed on the right!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadAll() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("readall").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "readall" + "\n" + SPACE 
								+ "All events are displayed!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testRead() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("read d1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read d1" + "\n" + SPACE 
								+ "Detail: test \n" + " Comment: \n" + " Time: 11:00 08/11/2015";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadToday() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("read today").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read today" + "\n" + SPACE 
								+ "Events of today displayed on the right!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testDelete() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("delete d1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "delete d1" + "\n" + SPACE 
								+ "Delete successful!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUpdate() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("update d1 abc by 10:00 today").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "update d1 abc by 10:00 today" + "\n" + SPACE 
								+ "Event updated successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUndo() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("undo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "undo" + "\n" + SPACE 
								+ "Undo operation successful!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testRedo() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		MainLogic.implement("undo");
		String returnedMessage = MainLogic.implement("redo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "redo" + "\n" + SPACE 
								+ "Redo operation successful!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testComment() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("comment d1 test comment").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "comment d1 test comment" + "\n" + SPACE 
								+ "Comment added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testMark() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("mark d1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "mark d1" + "\n" + SPACE 
								+ "Event marked successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testNUSMODS() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("nusmods").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "nusmods" + "\n" + SPACE 
								+ "Show nusmods sucessfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testMainLogic() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 12:00 today");
		String returnedMessage = MainLogic.implement("add test by 12:00 today").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test by 12:00 today" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testRecur() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("recur d1 1 1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "recur d1 1 1" + "\n" + SPACE 
								+ "Recur set successfully!";
		MainLogic.implement("delete d1");
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadmark() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("readmark").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "readmark" + "\n" + SPACE 
								+ "All marked events are displayed!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUnmark() throws IOException, ParseException{
		MainLogic.initialize();
		MainLogic.implement("add test by 11:00 today");
		MainLogic.implement("mark d1");
		MainLogic.implement("readmark");
		String returnedMessage = MainLogic.implement("unmark d1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "unmark d1" + "\n" + SPACE 
								+ "Event unmarked successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelp() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("help").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help" + "\n" + SPACE 
								+ "add\n theme\n read\n outline\n delete\n search\n update\n undo\n redo\n comment\n recur\n mark\n readmark\n unmark\n setpath\n nusmods\n todolist\n help\n clearall\n exit";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpAdd() throws IOException, ParseException{
		MainLogic.initialize();
		String returnedMessage = MainLogic.implement("help add").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help add" + "\n" + SPACE 
								+ "You can add 3 types of event to your list, namely deadline event, event-time event and memo event\n For events with deadline, type in \"add (event name) by HH:MM DD/MM/YYYY\"\n For events with event time, type in \"add (event name) from HH:MM to HH:MM DD/MM/YYYY\"\n For events without a time, type in \"add (event name)\"";
		assertEquals(returnedMessage, expectedMessage);
	}
}
