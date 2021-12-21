package my.day11.a.string;

import java.text.DecimalFormat;

import my.util.MyUtilBok;

public class StringMainBok {

	public static void main(String[] args) {
		
		char ch = "안녕하세요".charAt(2);
		System.out.println("ch => " + ch);
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		String str = "안녕하세요";
		String result = "";
		for (int i = str.length()-1; i >= 0; i--) {
			result += str.charAt(i);
		}
		System.out.println(result);

//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		char[] chArr = "안녕하세요".toCharArray();
		result = "";
		for (int i = chArr.length-1; i >= 0; i--) {
			result += chArr[i];
		}
		System.out.println(result);
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		str = "안녕하세요".substring(2);
		System.out.println(str);
		
		int len = "안녕하세요".length();
		str = "안녕하세요".substring(2, len);
		System.out.println(str);
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		int index = "시작하라 안녕하세요 건강하세요".indexOf("하");
		System.out.println(index);
		
		index = "시작하라 안녕하세요 건강하세요".indexOf("하세요");
		System.out.println(index);
		
		index = "시작하라 안녕하세요 건강하세요".indexOf("A");
		System.out.println(index);
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		String[] pathFileNameArr = {"C:/mydocument/resume/나의이력서.hwp",
									"D:/mymusic.mp3",
									"C:/photo/내얼굴.jpg"};
		
		for (int i = 0; i < pathFileNameArr.length; i++) {
		
			System.out.println(pathFileNameArr[i]);
		}
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		for (int i = 0; i < pathFileNameArr.length; i++) {	
		
			char[] chrArr = pathFileNameArr[i].toCharArray();
			
			String strReverse = "";
			for (int j = chrArr.length-1; j >= 0 ; j--) {	
			
				strReverse += chrArr[j];
			}
			
			System.out.println(strReverse);
			
			int idxSlash = strReverse.indexOf("/");
			strReverse = strReverse.substring(0, idxSlash);
			
			System.out.println(strReverse);
			
			chrArr = strReverse.toCharArray();
			
			String fileName = "";
			for (int j = chrArr.length-1; j >= 0; j--) {
			
				fileName += chrArr[j];
				
			}
			System.out.println(fileName);	
			System.out.println();
		}
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		index = "시작하라 안녕하세요 건강하세요".lastIndexOf("하");
		System.out.println(index);
		
		index = "시작하라 안녕하세요 건강하세요".lastIndexOf("하세요");
		System.out.println(index);
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		for (int i = 0; i < pathFileNameArr.length; i++) {
			
			int idx = pathFileNameArr[i].lastIndexOf("/");
			
			System.out.println(pathFileNameArr[i].substring(idx+1));
		}
		System.out.println();
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		String money = "2,000,000";
		
		do {
			int commaIndex = money.indexOf(",");
			if(commaIndex == -1)
				break;
			
			money = money.substring(0, commaIndex) + money.substring(commaIndex+1);
		} while (true);
		
		System.out.println(money);

//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		int sum = MyUtilBok.delComma("2,000,000") + MyUtilBok.delComma("5,000") + MyUtilBok.delComma("100");
		
		DecimalFormat df = new DecimalFormat("#,###");
		System.out.println(df.format(sum));
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		String food = "파스타,국밥,볶음밥,고구마,계란말이";
		String[] foodArr = food.split(",");
		
		for (int i = 0; i < foodArr.length; i++) {
			System.out.println(foodArr[i]);
		}
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		food = "파스타	국밥	볶음밥	고구마	계란말이";
		foodArr = food.split("\t");
		
		for (int i = 0; i < foodArr.length; i++) {
			System.out.println(foodArr[i]);
		}
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		food = "파스타.국밥.볶음밥.고구마.계란말이";
		foodArr = food.split(".");
		System.out.println("foodArr.length => " + foodArr.length);
		
		foodArr = food.split("\\.");
		System.out.println("foodArr.length => " + foodArr.length);
		
		foodArr = food.split("[.]");
		System.out.println("foddArr.length => " + foodArr.length);
		
		for (int i = 0; i < foodArr.length; i++) {
			System.out.println(foodArr[i]);
		}
		System.out.println();

//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		food = "파스타|국밥|볶음밥|고구마|계란말이";
		foodArr = food.split("\\|");
		
		for (int i = 0; i < foodArr.length; i++) {
			System.out.println(foodArr[i]);
		}
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		food = "파스타,국밥,볶음밥,고구마,계란말이";
		foodArr = food.split("\\,");
		
		for (int i = 0; i < foodArr.length; i++) {
			System.out.println(foodArr[i]);
		}
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		food = "파스타,국밥.볶음밥	고구마,계란말이";
		foodArr = food.split("[,.\t]");
		
		for (int i = 0; i < foodArr.length; i++) {
			System.out.println(foodArr[i]);
		}
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		food = "파스타1국밥2볶음밥3고구마4계란말이";
		foodArr = food.split("\\d");
		
		for (int i = 0; i < foodArr.length; i++) {
			System.out.println(foodArr[i]);
		}
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		food = "파스타1국밥2볶음밥3고구마4계란말이";
		foodArr = food.split("\\D");
		
		for (int i = 0; i < foodArr.length; i++) {
			System.out.println(foodArr[i]);
		}
		System.out.println();

//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
		
		System.out.println("나의 이름은 \"이순신\" 입니다.");
		System.out.println("C:\\movie\\koreamovie\\경이로운소문.mp4");
		System.out.println("나의 이름은 '엄정화' 입니다.");
		
		String[] nameArr = {"한석규","두석규","세석규","네석규","오석규"};
		String names = String.join("-", nameArr);
		System.out.println(names);
		
		names = names.replaceAll("석규", "SK");
		System.out.println(names);
		
		names = names.replaceFirst("SK", "석규");
		System.out.println(names);
		
		names = "한SK-두SK-세SK-네SK-오SK";
		for (int i = 0; i < 3; i++) {
			names = names.replaceFirst("SK", "석규");
		}
		System.out.println(names);
		System.out.println();
		
//■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■

		String[] contents = {"호호안녕하세요","건강하세요","행복하세요 또봐요","즐겁고 건강한 하루되세요"};
		
		for (int i = 0; i < contents.length; i++) {
			int idx = contents[i].indexOf("건강");
			if(idx != -1)
				System.out.println(contents[i]);
		}
		
	}
}









































