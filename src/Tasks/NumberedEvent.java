//@@author A0127142R
package Tasks;

public class NumberedEvent {
	protected int index;
	protected Event event;

	NumberedEvent() {

	}

	public NumberedEvent(int index, Event event) {
		this.index = index;
		this.event = event;
	}

	public int getIndex() {
		return this.index;
	}

	public Event getEvent() {
		return this.event;
	}
}
