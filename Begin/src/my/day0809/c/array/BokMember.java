package my.day0809.c.array;

public class BokMember {

	String id;
	String passwd;
	String name;
	int point;
	static int count = 2;
	
	public String showMyInfo() {
		return "1. 아이디 : "+id+"\n"
			+  "2. 비밀번호 : " +passwd+ "\n"
			+  "3. 성명 : " +name+ "\n"
			+  "4. 포인트 : " +point;
	}
	
	public String showInfo() {
		return id + "\t" + name + "\t" + point;
	}
	
}
