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

package main;

import java.util.Date;

public class Deadline {
	protected Date deadline = null;

	// constructor
	Deadline(){

	}

	public Deadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getDeadline(){
		return this.deadline;
	}
}