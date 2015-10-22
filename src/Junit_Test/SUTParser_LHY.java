package Junit_Test;
import main.Event;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import main.Deadline;
import main.EventTime;
import main.Parser;


public class SUTParser_LHY extends TestCase{
	private static String EXPECTED_ACTION = "add";

	public void test_getAction(){
		String testResult = Parser.getAction("add");
		assertEquals(testResult, EXPECTED_ACTION);
	}

	//partition case test
	public void test_getParameter_1(){
		String test1 = "add try";
		ArrayList<String> expectedResult1 = new ArrayList<String>();
		expectedResult1.add("try");
		ArrayList<String> testResult1 = Parser.getParameter(test1);
		assertTrue(isSameList(testResult1, expectedResult1));
	}

	public void test_getParameter_2(){
		String test2 = "add try2 by 22:00 22/10/2015";
		ArrayList<String> expectedResult2 = new ArrayList<String>();
		expectedResult2.add("try2 ");
		expectedResult2.add("22:00");
		expectedResult2.add("22/10/2015");
		ArrayList<String> testResult2 = Parser.getParameter(test2);
		assertTrue(isSameList(testResult2, expectedResult2));
	}

	public void test_getParameter_3(){
		String test3 = "add try3 from 10:00 to 11:00 22/10/2015";
		ArrayList<String> expectedResult3 = new ArrayList<String>();
		expectedResult3.add("try3 ");
		expectedResult3.add("10:00 ");
		expectedResult3.add("11:00");
		expectedResult3.add("22/10/2015");
		ArrayList<String> testResult3 = Parser.getParameter(test3);
		assertTrue(isSameList(testResult3, expectedResult3));
	}

	private boolean isSameList(ArrayList<String> A, ArrayList<String> B){
		if(A.size() != B.size()){
			return false;
		} else {
			for(int i = 0; i < A.size(); i++){
				if(!A.get(i).equals(B.get(i))){
					return false;
				}
			}
		}
		return true;

	}

	public void test_parseForEvent1() throws ParseException{
		Event expected1 = new Event("try");
		Event result1 = Parser.parseForEvent(Parser.getParameter("add try"));
		assertTrue(isEqualEvent1(expected1,result1));
	}

	public void test_parseForEvent2() throws ParseException{
		String timeAndDate = "22:00 22/10/2015";
		SimpleDateFormat dateF = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		Date thisDate = dateF.parse(timeAndDate);
		Event expected2 = new Event("try2 ", new Deadline(thisDate));
		Event result2 = Parser.parseForEvent(Parser.getParameter("add try2 by 22:00 22/10/2015"));
		assertTrue(isEqualEvent2(expected2,result2));
	}

	public void test_parseForEvent3() throws ParseException{
		String timeAndDate1 = "10:00 22/10/2015";
		String timeAndDate2 = "11:00 22/10/2015";
		SimpleDateFormat dateF = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		Date sDate = dateF.parse(timeAndDate1);
		Date eDate = dateF.parse(timeAndDate2);
		Event expected3 = new Event("try3 ", new EventTime(sDate, eDate));
		Event result3 = Parser.parseForEvent(Parser.getParameter("add try3 from 10:00 to 11:00 22/10/2015"));
		assertTrue(isEqualEvent3(expected3,result3));
	}

	private boolean isEqualEvent1(Event A, Event B){
		if(!A.getComment().equals(B.getComment())){
			return false;
		}
		if(!A.getDetail().equals(B.getDetail())){
			return false;
		}
		if(!A.getStatus().equals(B.getStatus())){
			return false;
		}
		/*
		if(!A.getDeadline().getDeadline().equals(B.getDeadline().getDeadline())){
			return false;
		}
		if(!A.getEventTime().getStart().equals(B.getEventTime().getStart())){
			return false;
		}
		if(!A.getEventTime().getEnd().equals(B.getEventTime().getEnd())){
			return false;
		}*/
		return true;
	}

	private boolean isEqualEvent2(Event A, Event B){
		if(!A.getComment().equals(B.getComment())){
			return false;
		}
		if(!A.getDetail().equals(B.getDetail())){
			return false;
		}
		if(!A.getStatus().equals(B.getStatus())){
			return false;
		}

		if(!A.getDeadline().getDeadline().equals(B.getDeadline().getDeadline())){
			return false;
		}
		/*
		if(!A.getEventTime().getStart().equals(B.getEventTime().getStart())){
			return false;
		}
		if(!A.getEventTime().getEnd().equals(B.getEventTime().getEnd())){
			return false;
		}*/
		return true;
	}

	private boolean isEqualEvent3(Event A, Event B){
		if(!A.getComment().equals(B.getComment())){
			return false;
		}
		if(!A.getDetail().equals(B.getDetail())){
			return false;
		}
		if(!A.getStatus().equals(B.getStatus())){
			return false;
		}
		/*
		if(!A.getDeadline().getDeadline().equals(B.getDeadline().getDeadline())){
			return false;
		}
		*/
		if(!A.getEventTime().getStart().equals(B.getEventTime().getStart())){
			return false;
		}
		if(!A.getEventTime().getEnd().equals(B.getEventTime().getEnd())){
			return false;
		}
		return true;
	}
}