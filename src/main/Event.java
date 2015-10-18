package main;

import java.util.Calendar;

public class Event {
<<<<<<< HEAD
	private String number;
	private String detail;
	private String comment;
	private String status; // done, in-progress, etc.
	private String priority;
	private Deadline deadline;
	private EventTime eventTime;
=======
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
>>>>>>> refs/remotes/origin/master
	
	
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
<<<<<<< HEAD

	void setNumber(int numberInt){
		this.number = String.valueOf(numberInt);
	}
	
	void addComment(String comment){
		this.comment = comment;
	}
	void setStatus(String status){
		this.status = status;
	}
	void setPriority(String priority){
		this.priority = priority;
	}
	
	public String getNumber(){
		return this.number;
	}
	
	public String getDetail(){
		return this.detail;
	}
	public String getComment(){
		return this.comment;
	}
	public String getStatus(){
		return this.status;
	}
	public String getPriority(){
		return this.priority;
	}
	public Deadline getDeadline(){
		return this.deadline;
	}
	public EventTime getEventTime(){
		return this.eventTime;
	}
=======
*/
>>>>>>> refs/remotes/origin/master
}
