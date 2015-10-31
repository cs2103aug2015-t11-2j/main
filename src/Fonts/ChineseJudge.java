package Fonts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChineseJudge {
	private static String REGEX = "[\u4e00-\u9fa5]";
	private static Pattern PAT = Pattern.compile(REGEX);
	
	public static boolean isContainsChinese(String str){
		Matcher matcher = PAT.matcher(str);
		boolean flg = false;
		if(matcher.find()){
			flg = true;
		}
		return flg;
	}
}
