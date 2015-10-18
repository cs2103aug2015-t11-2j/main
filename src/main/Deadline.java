package main;

import java.sql.Date;
import java.sql.Time;

//unused


public class Deadline {
	private Time time;
	private Date date;

	// constructor
	Deadline(Time time, Date date) {
		this.time = time;
		this.date = date;
	}

	Time getTime() {
		return this.time;
	}

	Date getDate() {
		return this.date;
	}
}
