package my.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

	// === 현재시각을 출력해주는 static 메소드생성하기 ====
	public static void currentTime() {	// 인스턴스화 할 필요가 없다.
		
		Date now = new Date(); // 현재시각
		
		SimpleDateFormat sdfrmt =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = sdfrmt.format(now);
		
		System.out.println(">> 현재시각 : " + today);
		// 2021-12-07 10:43:50
	}
	
	
	// === 비밀번호가 규칙에 맞는지 틀리는지 알려주는 static 메소드 생성하기 === // 
	// 비밀번호 규칙은 비밀번호의 길이는 8글자 이상 15글자 이하이어야 하고,
	// 또한 비밀번호는 영문대문자, 영문소문자, 숫자, 특수기호가 혼합되어야만 한다.
	// 우리는 규칙에 맞으면 true , 규칙에 틀리면  false 를 리턴해주도록 만든다.
	public static boolean isCheckPasswd(String passwd) {
		
		// passwd => "qwEr1234$" 이라면 
		// 문자열 "qwEr1234$" 의 문자열 길이는 "qwEr1234$".length(); 이다.
		// "qwEr1234$".length(); 은 9 가 나온다.
		
		int length = passwd.length();
		
		if(length < 8 || length > 15) {
			return false;
		}
		else { // 암호의 길이가 8글자 이상 15글자 이하인 경우
			
			boolean flagUpper = false;   // 대문자임을 표식하는 것
			boolean flagLower = false;   // 소문자임을 표식하는 것
			boolean flagDigit = false;   // 숫자임을 표식하는 것
			boolean flagSpecial = false; // 특수문자임을 표식하는 것
			
			for(int i=0; i<length; i++) {
				// 암호가 "qwEr1234$" 이라면
				// index 012345678
				
				char ch = passwd.charAt(i);
				
				if(Character.isUpperCase(ch))
					flagUpper = true;
				
				else if(Character.isLowerCase(ch))
					flagLower = true;
				
				else if(Character.isDigit(ch))
					flagDigit = true;
				
				else
					flagSpecial = true;
				
			} // end of for -----------------------------------------------------
			
			return(flagUpper && flagLower && flagDigit && flagSpecial);
			
		}

	}// end of public static boolean isCheckPasswd(String passwd) ----------------------
	
	// === ,가 들어있는 숫자로 되어진 문자열을 ,를 제거해서 정수로 리턴시켜주는 메소드 생성하기 === //
	public static int delComma(String str) {
		
		do {
			
			int commaIndex = str.indexOf(",");
			
			if(commaIndex == -1) 
				break;
			
			str = str.substring(0, commaIndex) + str.substring(commaIndex+1);
			
		} while (true);
		
		return Integer.parseInt(str);
		
	}// end of delComma(String str)-----------------------------------------------------

}
