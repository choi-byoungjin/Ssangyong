package my.day02.c.constructor;

public class BokMember {
	
	String userid;
	String passwd;
	String name;
	int age;
	int point;
	
	public BokMember() {
		System.out.println(">>> 기본생성자 Member() 가 호출됨 <<<");
	}
	
	public BokMember(String userid, String passwd, String name) {
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
	}
	
	public BokMember(String userid, String passwd, String name, int age, int point) {
		this(userid, passwd, name);
		this.age = age;
		this.point = point;
		System.out.println(">>> 파라미터가 있는 생성자가 호출됨 <<<");
	}
	
	void showInfo() {
		System.out.println("==="+ name +"님의 회원정보 ===\n"
						 + "1. 아이디 : "+ userid +"\n"
						 + "2. 암호 : "+ passwd+"\n"
						 + "3. 성명 : "+ name +"\n"
						 + "4. 나이 : "+ age +"\n"
						 + "5. 포인트 : "+ point +"\n");
	}
	
	void updatePasswd(String currentPasswd, String newPasswd) {
		if(passwd != currentPasswd) {
			System.out.println(">>\""+ name +"\"님, 현재 사용중인 암호와 틀린 암호를 입력하셔서 암호 변경이 불가합니다.!! << \n");
		}
		else {
			passwd = newPasswd;
			System.out.println(">>\""+ name +"\"님, 암호가 성공적으로 변경되었습니다!! << \n");
			showInfo();
		}
	}
	
	int pointPayment(int point) {
		int jangoPoint = this.point;
		if(this.point == 0) {
			System.out.println(">> \""+ name +"\"님 현재 포인트가 0 이므로 포인트 결제가 불가합니다. <<");
		}
		else if(this.point < point) {
			System.out.println(">> \""+ name +"\"님 현재 포인트가 잔고가 부족하므로 결제가 불가합니다. <<");
		}
		else {
			System.out.println(">> \""+ name +"\"님 "+ point +"포인트 결제가 완료되었습니다. <<");
			jangoPoint = jangoPoint - point;
		}
		return jangoPoint;
	}
	
	String memberInfo(int n) {
		String title = "";
		if(n==0) {
			title = "=== "+ name +"님의 회원정보 수정하기전 ===\n";
		}
		else if(n==1) {
			title = "=== "+ name +"님의 회원정보 수정후 ===\n";
		}
		return title
				+ " 1. 아이디 : "+ userid +"\n"
				+ " 2. 암호 : "+ passwd +"\n"
				+ " 3. 성명 : "+ name +"\n"
				+ " 4. 나이 : "+ age +"\n"
				+ " 5. 포인트 : "+ point;
	}
	
	String changeInfo(String passwd, int age, int point) {
		String before_info = memberInfo(0);
		this.passwd = passwd;
		this.age = age;
		this.point = point;
		String after_info = memberInfo(1);
		return before_info = "\n\n" + after_info;
	}
	
	String changeInfo(String passwd, String name, int age, int point) {
		String before_info = memberInfo(0);
		this.passwd = passwd;
		this.name = name;
		this.age = age;
		this.point = point;
		String after_info = memberInfo(1);
		return before_info + "\n\n" + after_info;
	}
}