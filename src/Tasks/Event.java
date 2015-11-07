package Tasks;

import java.util.Date;

public class Event implements Comparable<Event> {
	private String detail = "";
	private String comment = "";
	private String status = ""; // done, in-progress, etc.
	private String priority = "";
	private Deadline deadline = null;
	private EventTime eventTime = null;

	/*
	 * may need to parse it through Calendar.getInstance(), since it cannot use
	 * any Calendar command from null directly
	 * 
	 * Calendar.getInstance() generate current time and date and GMT and stuffs
	 * 
	 * read api for calendar and SimpleDateFormat or my test.java for some way
	 * to extract date and time in correct format
	 * 
	 * null value for boolean check in logic part(!?) and converter
	 * 
	 * 
	 */
	// constructor

	// unused, edit directly from the above command
	public Event() {

	}

	public Event(String detail) {
		this.detail = detail;
	}

	public Event(String detail, Deadline deadline) {
		this.detail = detail;
		this.deadline = deadline;
	}

	public Event(String detail, EventTime eventTime) {
		this.detail = detail;
		this.eventTime = eventTime;
	}

	public String getDetail() {
		return this.detail;
	}

	public String getComment() {
		return this.comment;
	}

	public String getStatus() {
		return this.status;
	}

	public String getPriority() {
		return this.priority;
	}

	public Deadline getDeadline() {
		return this.deadline;
	}

	public EventTime getEventTime() {
		return this.eventTime;
	}

	public void setPriority(String p){
		this.priority = p;
	}
	
	public void setComment(String c){
		this.comment = c;
	}
	
	public void setDetail(String d){
		this.detail = d;
	}
	
	public void setStatus(String s){
		this.status = s;
	}
	
	public void setDeadline(Deadline d){
		this.deadline = d;
	}
	
	public void setEventTime(EventTime e){
		this.eventTime = e;
	}
	
	@Override
	public int compareTo(Event compareEvent) {
		Date compareEventDate = new Date(0);
		Date currentEventDate = new Date(0);
		if (compareEvent.getDeadline() != null) {
			compareEventDate = compareEvent.getDeadline().getDeadline();
		} else if (compareEvent.getEventTime() != null) {
			compareEventDate = compareEvent.getEventTime().getStart();
		}
		if (this.getDeadline() != null) {
			currentEventDate = this.getDeadline().getDeadline();
		} else if (this.getEventTime() != null) {
			currentEventDate = this.getEventTime().getStart();
		}
		return currentEventDate.compareTo(compareEventDate);
	}

	public boolean equals(Event event2) {
		if (this.getDeadline() != null){
			if (event2.getDeadline()==null){
				return false;
			} else {
				return (this.getDetail().equals(event2.getDetail()) && this.getComment().equals(event2.getComment())
						&& this.getPriority().equals(event2.getPriority()) && this.getStatus().equals(event2.getStatus())
						&& this.getDeadline().getDeadline().compareTo(event2.getDeadline().getDeadline())==0 );
			}
		} else if (this.getEventTime() != null){
			if (event2.getEventTime()==null){
				return false;
			} else {
				return (this.getDetail().equals(event2.getDetail()) && this.getComment().equals(event2.getComment())
						&& this.getPriority().equals(event2.getPriority()) && this.getStatus().equals(event2.getStatus())
						&& this.getEventTime().getStart().compareTo(event2.getEventTime().getStart())==0
						&& this.getEventTime().getEnd().compareTo(event2.getEventTime().getEnd())==0);
			}
		} else {
		return (this.getDetail().equals(event2.getDetail()) && this.getComment().equals(event2.getComment())
				&& this.getPriority().equals(event2.getPriority()) && this.getStatus().equals(event2.getStatus()));
				//&& this.compareTo(event2) == 0);
		}
	}

}
