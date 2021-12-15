package my.day08.c.array;

public class Member {
	
	// field
	String id;
	String passwd;
	String name;
	int point;	// 해당 인스턴스만 사용한다.
	static int count;	// 다 같이 사용한다.
	
	// method
	public String showInfo() {
		return "1.아이디 : " + id + "\n"
			+  "2.비밀번호 : " + passwd + "\n"
			+  "3.성명 : " + name + "\n";
	}
}
