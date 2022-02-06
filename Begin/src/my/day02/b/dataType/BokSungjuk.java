package my.day02.b.dataType;

public class BokSungjuk {
	
	String hakbun;
	String name;
	byte korPoint;
	byte eng_point;
	byte mathpoint;
	char hakjum;
	
	void showSungjuk() {
		
		int total = (short)(korPoint + eng_point + mathpoint);
		double avg_double = (double)total/3;
		float avg_float = (float)total/3;
		float avg_float2 = total/3.0F;
		
		if(avg_double >= 90) {
			hakjum = 'A';
		}
		else if(avg_double >= 80) {
			hakjum = 'B';
		}
		else if(avg_double >= 70) {
			hakjum = 'C';
		}
		else if(avg_double >= 60) {
			hakjum = 'D';
		}
		else {
			hakjum = 'F';
		}
		
		String star = "";
		
		switch (hakjum) {
			case 'A':
				star = "☆☆☆☆☆";
				break;
	
			case 'B':
				star = "☆☆☆☆";
				break;
			
			case 'C':
				star = "☆☆☆";
				break;
				
			case 'D':
				star = "☆☆";
				break;
				
			case 'F':
				star = "☆";
				break;	
				
			default:
				break;
		}
		
		String diamond = "";
		
		switch (total/3/10) {
			case 10:
			case 9:
				diamond = "◆◆◆◆◆";
				break;
				
			case 8:
				diamond = "◆◆◆◆";
				break;
				
			case 7:
				diamond = "◆◆◆";
				break;
				
			case 6:
				diamond = "◆◆";
				break;
	
			default:
				diamond = "◆";
				break;
		}
	
		char ch1 = 'k';
		char ch2 = 107;
		char ch3 = 'k' + 2;
		char ch4 = '\u23F3';
		boolean bool_1 = false;
		boolean bool_2 = (5-3 == 4);
		boolean bool_3 = (5-3 != 4);
		
		System.out.println("=== "+ name +"성적결과 ===\n"
						+  "1. 학번 : "+ hakbun +"\n"
						+  "2. 성명 : "+ name +"\n"
						+  "3. 국어 : "+ korPoint +"\n"
						+  "4. 영어 : "+ eng_point +"\n"
						+  "5. 수학 : "+ mathpoint +"\n"
						+  "6. 총점 : "+ total +"\n"
						+  "7. 평균1 : "+ avg_double +"\n"
						+  "8. 평균2 : "+ avg_float +"\n"
						+  "9. 평균3 : "+ avg_float2 +"\n"
						+  "10. 학점1 : "+ hakjum +"\n"
						+  "11. 학점2(☆) : "+ star +"\n"
						+  "12. 학점3(◆) : "+ diamond +"\n"
						+  "소문자 k : "+ ch1 +"\n"
						+  "소문자 k : "+ ch2+"\n"
						+  "소문자 m : "+ ch3+"\n"
						+  "모래시계 : "+ ch4+"\n"
						+  "boolean 타입인 bool_1 : "+ bool_1 +"\n"
						+  "boolean 타입인 bool_2 : "+ bool_2 +"\n"
						+  "boolean 타입인 bool_3 : "+ bool_3 +"\n");
	}
	
}
