package main;

public class Event {
	private String detail;
	private String comment;
	private String status; // done, in-progress, etc.
	private String priority;
	private Deadline deadline;
	private EventTime eventTime;
	
	// constructor
	Event(String detail){
		this.detail = detail;
	}
	Event(String detail, Deadline deadline){
		this.detail = detail;
		this.deadline = deadline;
	}
	Event(String detail, EventTime eventTime){
		this.detail = detail;
		this.eventTime = eventTime;
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
	
	String getDetail(){
		return this.detail;
	}
	String getComment(){
		return this.comment;
	}
	String getStatus(){
		return this.status;
	}
	String getPriority(){
		return this.priority;
	}
	Deadline getDeadline(){
		return this.deadline;
	}
	EventTime getEventTime(){
		return this.eventTime;
	}
}
