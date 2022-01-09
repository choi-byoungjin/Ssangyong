package my.day19.b.ENUM;

public class Bok_Enum_main_2 {

	public static void main(String[] args) {
		Bok_SeasonType_1[] bokSeasonArr = Bok_SeasonType_1.values();
		
		System.out.println("1.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for (Bok_SeasonType_1 bokSeason : bokSeasonArr) {
			String str_seasonName = bokSeason.name();
			System.out.println(str_seasonName);
			
		}
		
		System.out.println("2.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for (Bok_SeasonType_1 bokSeason : bokSeasonArr) {
			int order = bokSeason.ordinal();
			String str_seasonName = bokSeason.name();
			System.out.println(order + "." + str_seasonName);
		}
		
		System.out.println("3.~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		String str = "";
		try {
			str = "여름";
			Bok_SeasonType_1 bokSeason = Bok_SeasonType_1.valueOf("여름");
			System.out.println(bokSeason);
			
			str = "한겨울";
			bokSeason = Bok_SeasonType_1.valueOf("한겨울");
		} catch (IllegalArgumentException e){
			System.out.println(">> 열거형 상수에 "+ str +" 이 없습니다. <<");
		}

	}

}
