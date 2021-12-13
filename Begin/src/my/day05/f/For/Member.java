package my.day05.f.For;

public class Member {

	String userid;
	String passwd;
	String name;
	
	void showInfo() {
		System.out.println("\n=== 회원정보 ===\n"
						+ "1. 아이디 : " + userid + "\n"
						+ "2. 암호 : " + passwd + "\n"
						+ "3. 성명 : " + name + "\n");
	}
	
}
