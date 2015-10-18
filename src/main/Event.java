package main;

import java.util.Calendar;

public class Event {
	String detail = "";
	String comment = "";
	String status = "";
	String priority = "";
	
	Calendar deadline = null;
	Calendar start = null;//eventTime start
	Calendar end = null;
	
/*	may need to parse it through Calendar.getInstance(), since it cannot use any Calendar command from null directly
	
	Calendar.getInstance() generate current time and date and GMT and stuffs
	
	read api for calendar and SimpleDateFormat 
	or my test.java for some way to extract date and time in correct format
	
	null value for boolean check in logic part(!?) and converter
	
	
*/
	// constructor
	
	Event() {
	}
	
	//unused, edit directly from the above command
/*	
	Event(String detail) {
		this.detail = detail;
	}

	Event(String detail, Deadline deadline) {
		this.detail = detail;
		this.deadline = deadline;
	}

	Event(String detail, EventTime eventTime) {
		this.detail = detail;
		this.eventTime = eventTime;
	}
*/
}
