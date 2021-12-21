package my.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtilBok {

	public static void currentTime() {
		
		Date now = new Date();
		
		SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = sdfrmt.format(now);
		
		System.out.println(">> 현재시각 : " + today);
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

}
