package main;

import java.text.SimpleDateFormat;
import java.util.*;



public class test {


	public static void main(String[] args) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_WEEK, 2);
		cal.set(Calendar.MONTH, 0);
		String str = cal.getTime().toString();
		System.out.println(str);
		ArrayList<String> arr = new ArrayList<String>();
		
		arr.add("detail");
		arr.add("comment");
		arr.add("status");
		arr.add("prior");
		arr.add(str);
		arr.add(str);
		arr.add(str);		
		
		arr.add("detail 2");
		arr.add("comment 2");
		arr.add("status 2");
		arr.add("prior 2");
		arr.add(str);
		arr.add(str);
		arr.add(str);
	
		
		ArrayList<Event> e = Converter.stringToEvent(arr);		
		System.out.println(e);		
		arr = Converter.eventToString(e);
		System.out.println(arr);
	
		
		SimpleDateFormat timeF = new SimpleDateFormat("HH:mm:ss a");
		str = timeF.format(cal.getTime());
		System.out.println(str);
		SimpleDateFormat dateF = new SimpleDateFormat("'hello' dd/MMM//yyyy '!@#$%^& week year 'www 'week of month 'W");
		str = dateF.format(cal.getTime());
		System.out.println(str);
		System.out.println(dateF.parse(str));// will give 0 to hour,min,sec since str don't have it
		
		
		
		
		
		
		
		
		
/*			
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime());
		
		String str = "Fri Oct 02 07:08:00 SGT 2015";
		
		
		str = "Fri Oct 02 2015 07:08 AM";
		
		SimpleDateFormat dt = new SimpleDateFormat("EEE MMM dd yyyy HH:mm a");
		java.util.Date date = dt.parse(str);
		cal.setTime(date);
		System.out.println(cal.getTime());
*/
/*		
		Storage s = new Storage("test.txt");
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(cal.getTime().toString());
		s.save(arr);
		System.out.println(arr);
		System.out.println("");
		
		arr = s.load();
		System.out.println(arr);
		
		String str = arr.get(0);
		System.out.println(str);
		
		SimpleDateFormat dt = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		java.util.Date date = dt.parse(str);
		cal.setTime(date);
		System.out.println(cal.getTime());
*/		
		
/*		
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(stamp.getDate());
		System.out.println(stamp);
		System.out.println(date);
		System.out.println("");
		String timeStr = "8:00:00 AM";
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a");
		java.util.Date dt = df.parse(timeStr);
		Time t = new Time(1, 0, 0);

		System.out.println(dt);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");

		System.out.println(sdf.format(dt));
		System.out.println(sdf.format(t));
		System.out.println(cal.getTime());
		System.out.println(sdf.format(cal.getTime()));
		
		Storage s = new Storage("test.txt");
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(stamp.toString());
		s.save(arr);
*/
	}
}
