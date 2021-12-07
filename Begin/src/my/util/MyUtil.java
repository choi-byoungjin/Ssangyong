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
}
