//@@author A0133992X
package Junit_Test;

import java.io.IOException;
import java.text.ParseException;

import GUI.TrayController;
import GUI.UIBuffer;
import GUI.UIHotKey;
import GUI.UITask;
import Logic.MainLogic;
import javafx.embed.swing.JFXPanel;
import junit.framework.TestCase;

public class SystemTest extends TestCase{
	private static String SPACE = " ";
	private UIBuffer myUIBuffer = null;
	private TrayController myTrayController = null;
	@Override  
    protected void setUp() throws Exception {  
		MainLogic.initialize();
		myUIBuffer = UIBuffer.getInstance();
		myTrayController = TrayController.getInstance();
    } 
	
	//@@author A0127142R
	public void testClear() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("clearall").substring(20);//MainLogic.implement("clearall").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "clearall" + "\n" + SPACE 
								+ "All contents cleared! Please Undo now if you made a mistake!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddMemo() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test memo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test memo" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddChineseMemo() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add 中文").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add 中文" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddEmpty() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add ").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add " + "\n" + SPACE 
								+ "Cannot add empty event!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddMemoWithFromTo() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test memo from a to b").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test memo from a to b" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddMemoWithBy() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test memo by c").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test memo by c" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddEventWithFullDate() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test Event from 10:00 to 11:00 04/11/2015").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test Event from 10:00 to 11:00 04/11/2015" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddDeadlineWithFullDate() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test deadline by 10:00 04/11/2015").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test deadline by 10:00 04/11/2015" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddEventWithWordDateToday() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test Event from 10:00 to 11:00 today").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test Event from 10:00 to 11:00 today" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddEventWithWordDateTmr() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test Event from 10:00 to 11:00 tomorrow").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test Event from 10:00 to 11:00 tomorrow" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddDeadlineWithWordDateToday() throws IOException, ParseException{
		MainLogic.implement("add test by 12:00 today");
		String returnedMessage = MainLogic.implement("add test by 12:00 today").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test by 12:00 today" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddDeadlineWithWordDateTmr() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test deadline by 10:00 tomorrow").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test deadline by 10:00 tomorrow" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	
	public void testAddEventWithShortDateTmr() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test Event from 10:00 to 11:00 tmr").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test Event from 10:00 to 11:00 tmr" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testAddDeadlineWithShortDateTmr() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("add test deadline by 10:00 tmr").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add test deadline by 10:00 tmr" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testOutline() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("outline").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "outline" + "\n" + SPACE 
								+ "Events of today and tomorrow displayed on the right!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadAll() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("readall").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "readall" + "\n" + SPACE 
								+ "All events are displayed!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadDeadline() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 08/11/2015");
		String returnedMessage = MainLogic.implement("read d1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read d1" + "\n" + SPACE 
								+ "Detail: test \n" + " Comment: \n" + " Time: 11:00 08/11/2015";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadDeadlineFailed() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("read d50").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read d50" + "\n" + SPACE 
								+ "There is no event at the input index!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadMemo() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("read m1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read m1" + "\n" + SPACE 
								+ "Detail: test memo by c\n" + " Comment: \n" + " Time: ";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadMemoFailed() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("read m10").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read m10" + "\n" + SPACE 
								+ "There is no event at the input index!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadEventFailed() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("read e1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read e1" + "\n" + SPACE 
								+ "There is no event at the input index!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadEvent() throws IOException, ParseException{
		MainLogic.implement("add test from 10:00 to 11:00 today");
		String returnedMessage = MainLogic.implement("read e1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read e1" + "\n" + SPACE 
								+ "Detail: test Event \n" + " Comment: \n" + " Time: 10:00-11:00 04/11/2015";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadToday() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("read today").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read today" + "\n" + SPACE 
								+ "Events of today displayed on the right!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadTomorrow() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("read tomorrow").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read tomorrow" + "\n" + SPACE 
								+ "Events of tomorrow displayed on the right!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadTomorrowShort() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("read tmr").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "read tmr" + "\n" + SPACE 
								+ "Events of tomorrow displayed on the right!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testDelete() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("delete d1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "delete d1" + "\n" + SPACE 
								+ "Delete successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUpdate() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("update d1 abc by 10:00 today").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "update d1 abc by 10:00 today" + "\n" + SPACE 
								+ "Event updated successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUndo() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("undo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "undo" + "\n" + SPACE 
								+ "Undo operation successful!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUndoError() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("undo abc").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "undo abc" + "\n" + SPACE 
								+ "Unrecognized parameter!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testCannotUndo() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		MainLogic.implement("undo");
		String returnedMessage = MainLogic.implement("undo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "undo" + "\n" + SPACE 
								+ "Cannot undo! Do some operations first!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testRedo() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		MainLogic.implement("undo");
		String returnedMessage = MainLogic.implement("redo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "redo" + "\n" + SPACE 
								+ "Redo operation successful!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testRedoError() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		MainLogic.implement("undo");
		String returnedMessage = MainLogic.implement("redo it").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "redo it" + "\n" + SPACE 
								+ "Unrecognized parameter!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testCannotRndo() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		MainLogic.implement("undo");
		MainLogic.implement("redo");
		String returnedMessage = MainLogic.implement("redo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "redo" + "\n" + SPACE 
								+ "Cannot redo if you did not undo!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testCommentDeadline() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("comment d1 test comment").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "comment d1 test comment" + "\n" + SPACE 
								+ "Comment added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testCommentMemo() throws IOException, ParseException{
		MainLogic.implement("add test comment");
		String returnedMessage = MainLogic.implement("comment m1 test comment").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "comment m1 test comment" + "\n" + SPACE 
								+ "Comment added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testCommentEvent() throws IOException, ParseException{
		MainLogic.implement("add test from 10:00 to 11:00 today");
		String returnedMessage = MainLogic.implement("comment e1 test comment").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "comment e1 test comment" + "\n" + SPACE 
								+ "Comment added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	//@@author A0133992X
	public void testMarkDeadline() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("mark d1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "mark d1" + "\n" + SPACE 
								+ "Event marked successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testMarkMemo() throws IOException, ParseException{
		MainLogic.implement("add test mark");
		String returnedMessage = MainLogic.implement("mark m1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "mark m1" + "\n" + SPACE 
								+ "Event marked successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testMarkEvent() throws IOException, ParseException{
		MainLogic.implement("add test from 10:00 to 11:00 today");
		String returnedMessage = MainLogic.implement("mark e1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "mark e1" + "\n" + SPACE 
								+ "Event marked successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testNUSMODS() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("nusmods").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "nusmods" + "\n" + SPACE 
								+ "Show nusmods successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testNUSMODSError() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("nusmods abc").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "nusmods abc" + "\n" + SPACE 
								+ "Unrecognized parameter!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testTodolist() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("todolist").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "todolist" + "\n" + SPACE 
								+ "Show TodoList successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testTodolistError() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("todolist abc").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "todolist abc" + "\n" + SPACE 
								+ "Unrecognized parameter!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testRecurDeadline() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("recur d1 1 1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "recur d1 1 1" + "\n" + SPACE 
								+ "Recur set successfully!";
		MainLogic.implement("delete d1");
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testRecurEvent() throws IOException, ParseException{
		MainLogic.implement("add test from 00:01 to 00:02 08/11/2015");
		String returnedMessage = MainLogic.implement("recur e1 1 50").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "recur e1 1 50" + "\n" + SPACE 
								+ "Recur set successfully!";
		MainLogic.implement("delete d1");
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadmark() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		String returnedMessage = MainLogic.implement("readmark").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "readmark" + "\n" + SPACE 
								+ "All marked events are displayed!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUnmarkDeadline() throws IOException, ParseException{
		MainLogic.implement("add test by 11:00 today");
		MainLogic.implement("mark d1");
		MainLogic.implement("readmark");
		String returnedMessage = MainLogic.implement("unmark d1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "unmark d1" + "\n" + SPACE 
								+ "Event unmarked successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUnmarkEvent() throws IOException, ParseException{
		MainLogic.implement("add test from 11:00 to 12:00 today");
		MainLogic.implement("mark e1");
		MainLogic.implement("readmark");
		String returnedMessage = MainLogic.implement("unmark e1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "unmark e1" + "\n" + SPACE 
								+ "Event unmarked successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUnmarkMemo() throws IOException, ParseException{
		MainLogic.implement("add test unmark");
		MainLogic.implement("mark m1");
		MainLogic.implement("readmark");
		String returnedMessage = MainLogic.implement("unmark m1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "unmark m1" + "\n" + SPACE 
								+ "Event unmarked successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelp() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help" + "\n" + SPACE 
								+ "add\n theme\n read\n outline\n delete\n search\n update\n undo\n redo\n comment\n recur\n mark\n readmark\n unmark\n setpath\n nusmods\n todolist\n help\n clearall\n exit";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpAdd() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help add").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help add" + "\n" + SPACE 
								+ "You can add 3 types of event to your list, namely deadline event, event-time event and memo event\n For events with deadline, type in \"add (event name) by HH:MM DD/MM/YYYY\"\n For events with event time, type in \"add (event name) from HH:MM to HH:MM DD/MM/YYYY\"\n For events without a time, type in \"add (event name)\"";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpTheme() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help theme").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help theme" + "\n" + SPACE 
								+ "You can change the picture you display on the right hand side panel. There are two pre-set pictures and you can change between them using \"theme 1\" (\"theme default\") and \"theme 2\"\n To use your own picture, manually add a picture of size 383*418 and file type png into the user.dir folder. Rename it to myTheme. Then you can change to this picture by typing in \"theme my theme\"";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpRead() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help read").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help read" + "\n" + SPACE 
								+ "As you can see your events listed under three categories on the right, you can read detailed information about each one by typing in \"read\", event type (d, e, m) and index.\n For example, \"read d1\" can give you information on first deadline event.\n You can also use \"read\" to check all the events on today or tomorrow, simply type \"read today\" or \"read tomorrow(tmr)\"";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpOutline() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help outline").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help outline" + "\n" + SPACE 
								+ "You can type \"outline\" to view all the events of today and tomorrow";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpDelete() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help delete").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help delete" + "\n" + SPACE 
								+ "With reference to the right hand side panel, you can delete an event by typing in \"delete\", event type (d, e, m) and index.\n For example, \"delete d1\" will remove the first deadline event";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpSearch() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help search").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help search" + "\n" + SPACE 
								+ "You can use the simple search to quickly locate an event. Type in \"search\" and your keyword. The events containing your keyword (ignore case) will be displayed on the right";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpUpdate() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help update").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help update" + "\n" + SPACE 
								+ "You can update the detail of an event by typing in \"update\", event type (d, e, m) and index, new event detail.\n For example, typing \"update m1 go to school\" will replace the event name of first event in memo to \"go to school\"";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpUndo() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help undo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help undo" + "\n" + SPACE 
								+ "Type in \"undo\" to revert your previous changes. Keep in mind you can only undo the latest operation";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpRedo() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help redo").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help redo" + "\n" + SPACE 
								+ "Type in \"redo\" to revert your undo operation";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpComment() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help comment").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help comment" + "\n" + SPACE 
								+ "You can set comment for events by typing in \"comment\", event type (d, e, m) and index, comment information.\n For example, typing \"comment e1 ASAP\" will set a comment for the first event in event-time event. You will see an icon highlighted for events with comments and you can read the comments by reading that event";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpRecur() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help recur").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help recur" + "\n" + SPACE 
								+ "You can set an event as recurring task by typing in \"recur\", event type (d, e, m) and index, the number of days for one iteration, the number of iterations desired.\n For example, typing \"recur d1 7 10\" will set the first deadline event as recurring event, which happens every 7 days (each week) and happens for 10 times (including the intial one)\n However keep in mind, you cannot recur a memo event as we do not know when you do it the first time!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpMark() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help mark").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help mark" + "\n" + SPACE 
								+ "You can mark an event as done by typing in \"mark\", event type (d, e, m) and index.\n An event will not be seen once you mark it as done. If you made a mistake, undo or type in \"readmark\" to find the list of marked event. You can then use \"unmark\" and the index to unmark an event";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpReadmark() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help readmark").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help readmark" + "\n" + SPACE 
								+ "Type in \"readmark\" and you can see the list of marked event";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpHelp() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help help").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help help" + "\n" + SPACE 
								+ "You can always refer to helplist for all the commands available\n Type in \"help\" you can see a list of all commands available\n Type in \"help\" and the command you want to know to learn more about it!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpUnmark() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help unmark").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help unmark" + "\n" + SPACE 
								+ "After you have get the list of marked event, you can \"unmark\" it to let it appear in your normal list again. Note that unmarking an event which is not marked previously will make no changes";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpSetpath() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help setpath").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help setpath" + "\n" + SPACE 
								+ "Type in \"setpath\" followed by the path you want to store your data file.\n Note that this will create a new empty file in the location you specified so please move your old data file to the new location if you still need the old list!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpNusmods() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help nusmods").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help nusmods" + "\n" + SPACE 
								+ "Type in \"nusmods\" and you can go to the NUSMODS website to allow you to check the timetable quickly!\n To go back to your list, just type \"todolist\"";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpTodolist() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help todolist").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help todolist" + "\n" + SPACE 
								+ "Type in \"todolist\" to go back to your list from NUSMODS website or other features!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpClearall() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help clearall").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help clearall" + "\n" + SPACE 
								+ "You can clear all the tasks in the list by typing in \"clearall\".\n Undo at once if you made a mistake! Otherwise you will permanently lose your list";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpExit() throws IOException, ParseException{;
		String returnedMessage = MainLogic.implement("help exit").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help exit" + "\n" + SPACE 
								+ "You can exit the app by typing in \"exit\"";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testHelpError() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("help abc").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "help abc" + "\n" + SPACE 
								+ "The command you entered is not found\n Please check to ensure you entered the correct command word!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testSearch() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("search test").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "search test" + "\n" + SPACE 
								+ "The list of found results are on the right!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testThemeOne() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("theme 1").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "theme 1" + "\n" + SPACE 
								+ "Background is changed to the default!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testThemeTwo() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("theme 2").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "theme 2" + "\n" + SPACE 
								+ "Background is changed successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}

	public void testReadallError() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("readall abc").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "readall abc" + "\n" + SPACE 
								+ "Unrecognized parameter!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUselessCommands() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("abc").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "abc" + "\n" + SPACE 
								+ "Error!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadMark() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("readmark").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "readmark" + "\n" + SPACE 
								+ "All marked events are displayed!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testReadMarkError() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("readmark abc").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "readmark abc" + "\n" + SPACE 
								+ "Unrecognized parameter!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testSetpath() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("setpath mytest/test").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "setpath mytest/test" + "\n" + SPACE 
								+ "New path set successfully!";
		MainLogic.implement("setpath user.dir/Yui");
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testExitError() throws IOException, ParseException{
		String returnedMessage = MainLogic.implement("exit abc").substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "exit abc" + "\n" + SPACE 
								+ "Unrecognized parameter!";
		MainLogic.implement("setpath user.dir/Yui");
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUIBufferInstance(){
		myUIBuffer = UIBuffer.getInstance();
		assertTrue(myUIBuffer != null);
	}
	
	public void testUIBufferGetList() throws IOException, ParseException{
		myUIBuffer.getList();
		assertTrue(myUIBuffer.DeadlineList() != null && myUIBuffer.EventList() != null && myUIBuffer.FloatingList() != null);
	}
	
	public void testUIBufferInitializeCommand() throws IOException, ParseException{
		myUIBuffer.initializeCommand("");
		String returnedMessage = myUIBuffer.returnedCommand().substring(20);
		String expectedMessage = " Hello, my master. Welcome back." + "\n" + " This is Yui!  <(^v^)/ "
				+ "\n" + " The events of today is shown on the right " + "\n" + " -What would you like to do?\n";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUIBufferGetFeeadBack() throws IOException, ParseException{
		myUIBuffer.getFeedback("add buffer test");
		String returnedMessage = myUIBuffer.returnedCommand().substring(20);
		String expectedMessage = SPACE + "Command Entered: " + "add buffer test" + "\n" + SPACE 
								+ "Event added successfully!";
		assertEquals(returnedMessage, expectedMessage);
	}
	
	public void testUIBufferGetTheme() throws IOException, ParseException{
		myUIBuffer.getTheme();
		assertEquals(myUIBuffer.theme(), "2");
	}
	
	public void testUIBufferGetIsShowMainGrid() throws IOException, ParseException{
		myUIBuffer.getIsShowMainGrid();
		assertEquals(myUIBuffer.isShowMainGrid(), true);
	}
	
	public void testTrayControllerInstance(){
		myTrayController = TrayController.getInstance();
		assertTrue(myTrayController != null);
	}
	
	public void testUIHotKey(){
		UIHotKey myUIHotKey = UIHotKey.getInstance();
		assertTrue(myUIHotKey != null);
	}
	
	public void testUITaskEvent() throws IOException, ParseException{
		@SuppressWarnings("unused")
		JFXPanel myPanel = new JFXPanel();
		myUIBuffer.getList();
		UITask myTaskEvent = null;
		UITask myTaskDeadline = null;
		UITask myTaskMemo = null;
		myTaskEvent = new UITask(myUIBuffer.EventList().get(0));
		myTaskDeadline = new UITask(myUIBuffer.DeadlineList().get(0));
		myTaskMemo = new UITask(myUIBuffer.FloatingList().get(0));
		assertTrue(myTaskEvent != null && myTaskDeadline != null && myTaskMemo != null);
	}
}

	
