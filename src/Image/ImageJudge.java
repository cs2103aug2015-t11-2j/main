package Image;

import java.text.SimpleDateFormat;
import java.util.Date;
	
public class ImageJudge {
	private SimpleDateFormat FORMATE_COMPARE = new SimpleDateFormat("yyyyMMdd");
	private static ImageJudge theImageJudge;
	
	private ImageJudge(){
	}
	
	public static ImageJudge getInstance(){
		if(theImageJudge == null){
			theImageJudge = new ImageJudge();
		}
		return theImageJudge;
	}
	
	public boolean isToday(Date theDate){
		Date today = new Date();
		String theDateString = FORMATE_COMPARE.format(theDate);
		String todayString = FORMATE_COMPARE.format(today);
		if(theDateString.equals(todayString)){
			return true;
		}
		return false;
	}
	
	public boolean isCommented(String comment){
		return !comment.equals("");
	}
}
