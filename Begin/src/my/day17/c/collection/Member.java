package my.day17.c.collection;

public class Member {

	private String id;
	private String passwd;
	private String name;
	
	public Member() {
		
	}
	
	public Member(String id, String passwd, String name) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void infoPrint() {
		System.out.println("== "+ name + "님의 회원정보 == \n"
						+  "1.아이디 : "+id+"\n"
						+  "2.비밀번호 : "+passwd+"\n"
						+  "3.성명: "+name+"\n");
	}

	///////////////////////////////////////////////////////////////////
	@Override
	public String toString() {
		
		return "1.아이디 : "+id+"\n"
			+  "2.비밀번호 : "+passwd+"\n"
			+  "3.성명: "+name+"\n";
	}
}