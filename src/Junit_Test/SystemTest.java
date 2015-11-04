package Junit_Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;
import main.ToDoList;

public class SystemTest extends TestCase{
	private static final SimpleDateFormat DATAFORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	private static String SPACE = " ";
	private String nowTime = DATAFORMAT.format(new Date()) + "\n";
	
	public void testAddMemo() throws IOException, ParseException{
		ToDoList.initialize();
		String returnedMessage = ToDoList.implement("add test memo");
		String expectedMessage = nowTime + SPACE + "Command Entered: " + "add test memo" + "\n" + SPACE 
								+ "Event added successful!";
		assertEquals(returnedMessage, expectedMessage);
	}
}
