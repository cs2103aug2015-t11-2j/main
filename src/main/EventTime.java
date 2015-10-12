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