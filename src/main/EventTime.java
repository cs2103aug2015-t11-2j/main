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

import java.util.Date;

public class EventTime {
	protected Date start = null;
	protected Date end = null;

	// constructor
	EventTime(){

	}

	public EventTime(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	public Date getStart(){
		return this.start;
	}
	public Date getEnd(){
		return this.end;
	}
}


