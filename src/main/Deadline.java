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

import java.util.Calendar;

public class Deadline {
	protected Calendar deadline = null;

	// constructor
	Deadline(){
		
	}
	
	Deadline(Calendar deadline) {
		this.deadline = deadline;
	}

	public Calendar getDeadline(){
		return this.deadline;
	}
}