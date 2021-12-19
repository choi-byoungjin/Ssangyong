package my.day02.a;

public class Main01Bok {

	String id;
	String pwd;
	String name;
	String email;
	int age;
	double height;
	double weight = 58.6;
	
	void showInfo1() {
		int hopeMoney = 5000;
		System.out.println("=== 회원정보 ===\n"
				+  "1. 아이디 : " + id + "\n"
				+  "2. 암호 : " + id + "\n"
				+  "3. 성명 : " + name + "\n"
				+  "4. 이메일 : " + email + "\n"
				+  "5. 나이 : " + age + "\n"
				+  "6. 신장 : " + height + "\n"
				+  "7. 몸무게 : " + weight + "\n"
				+  "8. 희망급여 : " + hopeMoney + "만원");
	}

	void showInfo2() {
		int hopeMoney = 7000;
		System.out.println("=== 회원정보 ===\n"
				+  "1. 아이디 : " + id + "\n"
				+  "2. 암호 : " + id + "\n"
				+  "3. 성명 : " + name + "\n"
				+  "4. 이메일 : " + email + "\n"
				+  "5. 나이 : " + age + "\n"
				+  "6. 신장 : " + height + "\n"
				+  "7. 몸무게 : " + weight + "\n"
				+  "8. 희망급여 : " + hopeMoney + "만원");
	}
	
	public static void main(String[] args) {
		
		Main01 ma1 = new Main01();
		ma1.id = "leess";
		ma1.pwd = "qwer1234";
		ma1.name = "이순신";
		ma1.email = "leess@naver.com";
		ma1.age = 25;
		ma1.showInfo1();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Main01 ma2 = new Main01();
		ma2.showInfo1();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		ma2.showInfo2();
		
	}

}
