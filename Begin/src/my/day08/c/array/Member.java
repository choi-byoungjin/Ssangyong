package my.day08.c.array;

public class Member {
	
	// field
	String id;
	String passwd;
	String name;
	int point;	// 해당 인스턴스만 사용한다.
	static int count;	// 다 같이 사용한다.
	
	// method
	// 특정사용자의 개인정보를 보여주도록 하는 메소드
	public String showInfo(String id) {
		return "1.아이디 : " + id + "\n"
			+  "2.비밀번호 : " + passwd + "\n"
			+  "3.성명 : " + name + "\n"
			+  "4.포인트 : " + point;
	}
	
	// 모든사용자의 비밀번호를 제외한 개인정보를 보여주도록 하는 메소드
	public String showInfo() {
		return id + "\t" + name + "\t" + point;
	}
}
