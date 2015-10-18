package main;

public class Event {
	private String number;
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
}
