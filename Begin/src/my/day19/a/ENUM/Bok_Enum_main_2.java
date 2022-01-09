package my.day19.a.ENUM;

import java.util.Calendar;

public class Bok_Enum_main_2 {

	public static void main(String[] args) {
		Calendar currentDate = Calendar.getInstance();
		int currentMonth = currentDate.get(Calendar.MONTH) + 1;
		
		System.out.println(currentMonth);
		
		String season = null;
		if( 1 <= currentMonth && currentMonth <= 2 || currentMonth == 12)
			season = Bok_Season_1.WINTER;
		else if( 3 <= currentMonth && currentMonth <= 5 )
			season = Bok_Season_1.SPRING;
		else if( 6 <= currentMonth && currentMonth <= 8 )
			season = Bok_Season_1.SUMMER;
		else 
			season = Bok_Season_1.AUTUMN;
		System.out.println(season);
		
		System.out.println("====================================================");
		
		if( 1 <= currentMonth && currentMonth <= 2 || currentMonth == 12)
			season = "하하하";
		else if( 3 <= currentMonth && currentMonth <= 5 )
			season = "호호호";
		else if( 6 <= currentMonth && currentMonth <= 8 )
			season = "헤헤헤";
		else 
			season = "히히히";
		System.out.println(season);
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		Bok_SeasonType_3 stype = null;
		
		if( 1 <= currentMonth && currentMonth <= 2 || currentMonth == 12)
			stype = Bok_SeasonType_3.겨울;
		else if( 3 <= currentMonth && currentMonth <= 5 )
			stype = Bok_SeasonType_3.봄;
		else if( 6 <= currentMonth && currentMonth <= 8 )
			stype = Bok_SeasonType_3.여름;
		else 
			stype = Bok_SeasonType_3.가을;
		System.out.println(stype);
		
		System.out.println("=====================================================");
		
		switch (stype) {
			case 봄:
				System.out.println("따스한 봄 입니다.");
				break;
			case 여름:
				System.out.println("무더운 여름 입니다.");
				break;
			case 가을:
				System.out.println("시원한 가을 입니다.");
				break;
			case 겨울:
				System.out.println("추운 겨울 입니다.");
				break;	
			default:
				break;
		}
	}

}
