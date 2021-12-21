package my.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtilBok {

	public static void currentTime() {
		
		Date now = new Date();
		
		SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = sdfrmt.format(now);
		
		System.out.println(">> 현재시각 : " + today);
	}
	
	public static boolean isCheckPasswd(String passwd) {
	
		int length = passwd.length();
	
		if(length < 8 || length > 15) {
			return false;
		}
		else {
			boolean flagUpper = false;
			boolean flagLower = false;
			boolean flagDigit = false;
			boolean flagSpecial = false;
			for (int i = 0; i < length; i++) {
				char ch = passwd.charAt(i);
				if(Character.isUpperCase(ch))
					flagUpper = true;
				else if(Character.isLowerCase(ch))
					flagLower = true;
				else if(Character.isDigit(ch))
					flagDigit = true;
				else
					flagSpecial = true;
			}
			return(flagUpper && flagLower && flagDigit && flagSpecial);
		}
	}
	
	public static int delComma(String str) {
		
		do {
			
			int commaIndex = str.indexOf(",");
			if(commaIndex == -1)
				break;
			
			str = str.substring(0, commaIndex) + str.substring(commaIndex+1);
		} while (true);
		
		return Integer.parseInt(str);
	}

	public static boolean isCheckJubun(String jubun) {
		
		if(jubun == null)
			return false;
				
		Pattern p = Pattern.compile("^\\d{2}[01]\\d[0-3]\\d[1-4]$");
		Matcher m = p.matcher(jubun);
		boolean bool = m.matches();
		
		return bool;
	}
}
