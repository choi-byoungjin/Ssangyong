package my.day01;

public class MemberBok {

	String id;
	String pwd;
	String name;
	String email;
	static String address;
	
	void showInfo() {
		System.out.println("==== 회원정보 ====\n"
							+ "1. 아이디 : " + id + "\n"
							+ "2. 비밀번호 : " + pwd + "\n"
							+ "3. 성명 : " + name + "\n"
							+ "4. 이메일 : " + email + "\n"
							+ "5. 주소 : " + address + "\n");
	}
}
