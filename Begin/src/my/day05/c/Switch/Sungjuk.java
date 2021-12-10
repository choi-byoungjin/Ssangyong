package my.day05.c.Switch;

public class Sungjuk {
	
	String hakbun;	// 학번	"091234"
	String name;	// 성명
	byte kor;		// 국어	byte -128 ~ 127			0 ~ 100 로 제한
	byte eng;		// 영어
	byte math;		// 수학
	short age;		// 나이	short -32768 ~ 32767	20 ~ 50 로 제한

	// public Sungjuk()	{ } // 기본생성자
	
	
	// 입력받은 점수가 0 ~ 100 사이의 점수인지 아닌지를 판별해주는 메소드 생성하기
	boolean checkJumsu(byte jumsu) {
		if(0 <= jumsu && jumsu <= 100) {
			return true;
		}
		
		else {
			System.out.println("### 점수 입력은 0 이상 100 까지만 가능합니다. ###");
			return false;
		}
	}// end of boolean chekJumsu(byte jumsu)------------------------------------

	
	boolean checkAge(short age) {
		if(20 <= age && age <= 50) {
			return true;
		}
		
		else {
			System.out.println("### 나이 입력은 20 이상 50 까지만 가능합니다. ###");
			return false;
		}
	}// end of boolean checkAge(byte age)------------------------------------

	
	// 입력되어진 필드값을 화면에 출력해주는 메소드 생성하기
	void showInfo() {
		
		short total = (short)(kor+eng+math);
		
		float avg = Math.round((float)total/3*10)/10.0F;
	 // float avg = total/3.0F;
	 //	my.day05.a.math.MathMain 클래스 참조할 것!
		
		String hakjum = "";
		
		if(avg >= 90) {
			hakjum = "A";
		}
		else if(avg >= 80) {
			hakjum = "B";
		}
		else if(avg >= 70) {
			hakjum = "C";
		}
		else if(avg >= 60) {
			hakjum = "D";
		}
		else {
			hakjum = "F";
		}
		
		// 선물은 
		// 학점이 "A" 이라면 "놀이공원이용권,치킨,피자,아이스크림"
		// 학점이 "B" 이라면 "치킨,피자,아이스크림"
		// 학점이 "C" 이라면 "피자,아이스크림"
		// 학점이 "D" 이라면 "아이스크림"
		// 학점이 "F" 이라면 "꿀밤3대"
		
		String gift = "";
		
		switch (hakjum) {
			case "A":
				gift += "놀이공원이용권,"; // gift = gift + "놀이공원이용권";
			
			case "B":
				gift += "치킨,";
				
			case "C":
				gift += "피자,";
				
			case "D":
				gift += "아이스크림";
				break;	// break; 를 만나야만 switch case문을 벗어난다.
				
			case "F":
				gift += "꿀밤3대";

			default:
				break;
		}// end of switch-------------------------------------------------------
		
		System.out.println("\n === \""+name+"\"님의 성적결과 === \n"
						+ "1. 학번 : " + hakbun + "\n"
						+ "2. 성명 : " + name + "\n"
						+ "3. 국어 : " + kor + "\n"
						+ "4. 영어 : " + eng + "\n"
						+ "5. 수학 : " + math + "\n"
						+ "6. 총점 : " + total + "\n"
						+ "7. 평균 : " + avg + "\n"
						+ "8. 학점 : " + hakjum + "\n"
						+ "9. 나이 : " + age + "세\n"
						+ "10. 선물 : " + gift + "\n"
						);
		
	}// end of void showInfo()-------------------------------------------
	
}
