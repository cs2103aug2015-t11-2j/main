//@@author A0127142R
package Junit_Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Logic.Action;
import Storage.Storage;

public class ActionTest {

	private static final String INVALID_ADD_PARAMETER_MSG = "Cannot add empty event!";
	private static final String ADD_SUCCESS_MSG = "Event added successful!";
	private static final String SEARCH_NOT_FOUND_MSG = "Cannot find the key words!";
	private static final String UNDO_MSG = "Undo operation successful!";
	private static final String UNABLE_UNDO_MSG = "Cannot undo! Have some operations first!";
	private static final String REDO_MSG = "Redo operation successful!";
	private static final String UNABLE_REDO_MSG = "Cannot redo if you did not undo!";
	private static final String DELETE_OUT_OF_BOUND_MSG = "Cannot delete. Index entered is larger than current event amount!";
	private static final String DELETE_SUCCESSFUL_MSG = "Delete successful!";
	private static final String INVALID_LIST_TYPE_MSG = "Please enter the correct event type (d, e or m)!";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddToList() throws IOException, ParseException {
		Storage s = new Storage("test");
		ArrayList<String> parameter = new ArrayList<String>();
		assertEquals(Action.addToList(s, parameter, parameter), INVALID_ADD_PARAMETER_MSG);
		parameter.add("abc");
		assertEquals(Action.addToList(s, parameter, parameter), ADD_SUCCESS_MSG);
		s.reset();
	}

	@Test
	public void testSearchKey() throws IOException, ParseException {
		Storage s = new Storage("test");
		ArrayList<String> parameter = new ArrayList<String>();
		parameter.add("event1");
		assertEquals(Action.searchKey(s, parameter), SEARCH_NOT_FOUND_MSG);
		Action.addToList(s, parameter, parameter);
		assertEquals(Action.searchKey(s, parameter), "1. event1");
		s.reset();
	}

	@Test
	public void testUndo() throws IOException, ParseException {
		Storage s = new Storage("test");
		ArrayList<String> parameter = new ArrayList<String>();
		parameter.add("event1");
		Action.addToList(s, parameter, parameter);
		assertEquals(Action.undo(s), UNDO_MSG);
		assertEquals(Action.undo(s), UNABLE_UNDO_MSG);
		s.reset();
	}

	@Test
	public void testRedo() throws IOException, ParseException {
		Storage s = new Storage("test");
		ArrayList<String> parameter = new ArrayList<String>();
		parameter.add("event1");
		Action.addToList(s, parameter, parameter);
		Action.undo(s);
		assertEquals(Action.redo(s), REDO_MSG);
		assertEquals(Action.redo(s), UNABLE_REDO_MSG);
		s.reset();
	}

	@Test
	public void testDeleteEvent() throws IOException, ParseException {
		Storage s = new Storage("test");
		ArrayList<String> parameter = new ArrayList<String>();
		parameter.add("event1");
		Action.addToList(s, parameter, parameter);
		ArrayList<String> deleteParameter = new ArrayList<String>();
		deleteParameter.add("a1");
		assertEquals(Action.deleteEvent(s, deleteParameter), INVALID_LIST_TYPE_MSG);
		deleteParameter.set(0, "m2");
		assertEquals(Action.deleteEvent(s, deleteParameter), DELETE_OUT_OF_BOUND_MSG);
		deleteParameter.set(0, "m1");
		assertEquals(Action.deleteEvent(s, deleteParameter), DELETE_SUCCESSFUL_MSG);
		s.reset();
	}

}
