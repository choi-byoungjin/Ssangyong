package my.day18.a.generic;

public class Bok_Employee {

	String userid;
	String passwd;
	String name;
	String jik;
	
	
	public Bok_Employee(String userid, String passwd, String name, String jik) {
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.jik = jik;
	}


	@Override
	public String toString() {
		return "1.아이디 : "+ userid +"\n"+
			   "2.비밀번호 : "+ passwd +"\n"+
			   "3.성명 : "+ name +"\n"+
			   "4.직급 : "+ jik +"\n";
	}
	
}
