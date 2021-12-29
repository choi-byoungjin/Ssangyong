package my.day18.b.generic;

public class Employee {

	   String userid;  // 아이디
	   String passwd;  // 암호
	   String name;    // 사원명
	   String jik;     // 직급 
	   
	   public Employee() {}
	   
	   public Employee(String userid, String passwd, String name, String jik) {
	      this.userid = userid;
	      this.passwd = passwd;
	      this.name = name;
	      this.jik = jik;
	   }
	   
	   @Override
	   public String toString() {
	      
	      return "1.아이디: "+userid+"\n"+
	             "2.비밀번호: "+passwd+"\n"+
	             "3.성명: "+name+"\n"+
	             "4.직급: "+jik+"\n";
	      
	   }
	
}
