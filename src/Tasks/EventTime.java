//@@author A0127142R
package Tasks;

import java.util.Date;

public class EventTime {
	private Date start = null;
	private Date end = null;

	// constructor
	public EventTime() {

	}

	public EventTime(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	public Date getStart() {
		return this.start;
	}

	public Date getEnd() {
		return this.end;
	}

	public void setStart(Date s) {
		this.start = s;
	}

	public void setEnd(Date e) {
		this.end = e;
	}
}
