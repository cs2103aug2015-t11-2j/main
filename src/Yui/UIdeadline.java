package Yui;

import java.sql.Date;
import java.sql.Time;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import main.Event;

public class UIdeadline {
	private static Event deadlineEvent;
	public UIdeadline(Event event){
		deadlineEvent = event;
		Date thisDate = deadlineEvent.getDeadline().getDate();
		Time thisTime = deadlineEvent.getDeadline().getTime();
		String dataString = thisDate.toString();
		String timeString = thisTime.toString();
		String eventName = deadlineEvent.getDetail();
		String num = deadlineEvent.getNumber();
		
		GridPane deadlineEvent = new GridPane();
		deadlineEvent.setAccessibleText(num+eventName);
		deadlineEvent.add(new Text(num), 0, 0);
		deadlineEvent.add(new Text(eventName), 1, 0);
		deadlineEvent.add(new Text(dataString), 2, 0);
		deadlineEvent.add(new Text(timeString), 3, 0);
		deadlineEvent.setPrefSize(90, 25);   
	}
}
