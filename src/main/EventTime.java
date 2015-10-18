
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

	Time getStartTime() {
		return this.startTime;
	}

	Date getStartDate() {
		return this.startDate;
	}

	Time getEndTime() {
		return this.endTime;
	}

	Date getEndDate() {
		return this.endDate;
	}
}


/*
package main;

import java.util.Calendar;

public class EventTime {
	Calendar start = null;
	Calendar end = null;

	// constructor
	EventTime(Calendar start, Calendar end) {
		this.start = start;
		this.end = end;
	}

}

*/