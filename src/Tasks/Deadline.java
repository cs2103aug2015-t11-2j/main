//@@author A0127142R

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