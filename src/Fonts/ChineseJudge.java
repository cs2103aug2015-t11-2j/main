//@@author A0133992X
package Fonts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChineseJudge {
	private String REGEX = "[\u4e00-\u9fa5]";
	private Pattern PAT = Pattern.compile(REGEX);
	private static ChineseJudge theChineseJudge;
	
	private ChineseJudge(){
	}
	
	public static ChineseJudge getInstance(){
		if(theChineseJudge == null){
			theChineseJudge = new ChineseJudge();
		}
		return theChineseJudge;
	}
	
	public boolean isContainsChinese(String str){
		Matcher matcher = PAT.matcher(str);
		boolean flg = false;
		if(matcher.find()){
			flg = true;
		}
		return flg;
	}
}
