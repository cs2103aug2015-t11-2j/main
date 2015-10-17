package main;

import java.sql.Date;
import java.sql.Time;

public class Deadline {
	private Time time;
	private Date date;

	// constructor
	Deadline(Time time, Date date) {
		this.time = time;
		this.date = date;
	}

	public Time getTime() {
		return this.time;
	}

	public Date getDate() {
		return this.date;
	}
}
