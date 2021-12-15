package my.day08.c.array;

public class Member {
	
	// field
	String id;
	String passwd;
	String name;
	
	
	// method
	public String showInfo() {
		return "1.아이디 : " + id + "\n"
			+  "2.비밀번호 : " + passwd + "\n"
			+  "3.성명 : " + name + "\n";
	}
}
