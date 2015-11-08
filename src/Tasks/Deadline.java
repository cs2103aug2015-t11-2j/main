//@@author A0127142R
/*
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

	public Time getTime() {
		return this.time;
	}

	public Date getDate() {
		return this.date;
	}
}
*/

package Tasks;

import java.util.Date;

public class Deadline {
	private Date deadline = null;

	// constructor
	public Deadline() {

	}

	public Deadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Date d) {
		this.deadline = d;
	}
}