package my.day12.a.encapsulation;

public class Sungjuk {
	
	String name;
	int kor;
	int eng;
	int math;
	
	boolean isCheckJumsu(int jumsu) {
		if( 0 <= jumsu && jumsu <= 100)
			return true;
		else
			return false;
	}
	
	void showSungjuk() {
		System.out.println("1.성명 : " + name + "\n"
						+  "2.국어 : "+ kor +"\n"
						+  "3.영어 : "+ eng +"\n"
						+  "4.수학 : "+ math +"\n");
	}
	
}
