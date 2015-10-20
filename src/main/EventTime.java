/*
package main;

import java.sql.Date;
import java.sql.Time;

public class EventTime {
	private Time startTime;
	private Date startDate;
	private Time endTime;
	private Date endDate;

	// constructor
	EventTime(Time startTime, Date startDate, Time endTime, Date endDate) {
		this.startTime = startTime;
		this.startDate = startDate;
		this.endTime = endTime;
		this.endDate = endDate;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Time getEndTime() {
		return this.endTime;
	}

	public Date getEndDate() {
		return this.endDate;
	}
}
*/


package main;

import java.util.Calendar;

public class EventTime {
	protected Calendar start = null;
	protected Calendar end = null;

	// constructor
	EventTime(){
		
	}
	
	EventTime(Calendar start, Calendar end) {
		this.start = start;
		this.end = end;
	}
	
	public Calendar getStart(){
		return this.start;
	}
	public Calendar getEnd(){
		return this.end;
	}
}


