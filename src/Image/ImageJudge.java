package Image;

import java.text.SimpleDateFormat;
import java.util.Date;
	
public class ImageJudge {
	private static SimpleDateFormat FORMATE_COMPARE = new SimpleDateFormat("yyyyMMdd");
		
	public static boolean isToday(Date theDate){
		Date today = new Date();
		String theDateString = FORMATE_COMPARE.format(theDate);
		String todayString = FORMATE_COMPARE.format(today);
		if(theDateString.equals(todayString)){
			return true;
		}
		return false;
	}
	
	public static boolean isCommented(String comment){
		return !comment.equals("");
	}
}
