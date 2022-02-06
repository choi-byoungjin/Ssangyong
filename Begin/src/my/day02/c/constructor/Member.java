package my.day02.c.constructor;

public class Member {

	// field, attribute, property, 속성
	String userid;
	String passwd;
	String name;
	int age;
	int point;

	// >> 생성자(constructor) ==> 인스턴스(객체)화 할 때 사용되어지는 일종의 메소드라고 보면 된다.
	
	// 파라미터가 없는 생성자를 기본생성자(default constructor)라고 부른다.
	// 모든 클래스는 아래와 같이 기본생성자(default constructor)를 명기하지 않으면
	// 기본생성자가 없는 것이 아니라 생략되어져 안 보일 뿐이다.
	public Member() {
		System.out.println(">>> 기본생성자 Member() 가 호출됨 <<<");
	}
	
	// >>> 파라미터가 있는 생성자의 주목적은 필드를 초기화시켜 주는 것이다.
	// !!! 파라미터가 있는 생성자를 만들면 자동적으로 생략되어져 있던 기본생성자가 삭제가 되어져 버린다.!!!
	public Member(String userid, String passwd, String name) {
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
	}
	
	// >>> 파라미터가 있는 생성자
	// !!! 파라미터가 있는 생성자를 만들면 자동적으로 생략되어져 있던 기본생성자가 삭제가 되어져 버린다.!!!
	public Member(String userid, String passwd, String name, int age, int point) {
	// String userid, String passwd, String name, int age, int point 을 파라미터(parameter) 또는 매개변수 또는 인자라고 부른다.
		
	/*	
		this.userid = userid;
		// 지역변수명과 필드명이 동일할 경우 지역변수명이 더 우선하므로 모두 지역변수명으로 되어져 버린다.
		// this는 자기자신 클래스인 Member 클래스를 뜻하는 것이다.(대명사)
		
		this.passwd = passwd;
		this.name = name;
	*/
	// 또는
		this(userid, passwd, name); // 생성자 내에서 또 다른 생성자를 호출 한 것임
		
		this.age = age;
		this.point = point;
		
		System.out.println(">>> 파라미터가 있는 생성자가 호출됨 <<<");
	}

	// behavior, 행위, operation, 기능, 함수, method
	void showInfo() {
		
		System.out.println("==="+name+"님의 회원정보 ===\n"
						+ "1. 아이디 : " + userid + "\n"
						+ "2. 암호 : " + passwd + "\n"
						+ "3. 성명 : " + name + "\n"
						+ "4. 나이 : " + age + "\n"
						+ "5. 포인트 : " + point + "\n");
		
	}
	
	// == return 타입이 void 인 메소드 생성하기 == //
	// 암호를 변경하려는 기능(메소드)
	void updatePasswd(String currentPasswd, String newPasswd) {	// 메소드 생성시 void 는 리턴타입이 없는 타입이다.
		
		if (passwd != currentPasswd) {	// 현재 인스턴스의 암호인 passwd 와 입력받은 currentPasswd 값이 서로 일치하지 않으면
			System.out.println(">>\"" + name + "\"님, 현재 사용중인 암호와 틀린 암호를 입력하셔서 암호 변경이 불가합니다.!! << \n");	// 역슬레시는 이스케이프
		}
		else {	// 현재 인스턴스의 암호인 passwd 와 입력받은 currentPasswd 값이 서로 일치하면 
			passwd = newPasswd;
			
			System.out.println(">>\"" + name + "\"님, 암호가 성공적으로 변경되었습니다!! <<");
			showInfo();
		}
	}
	
	
	// == return 타입이 있는 메소드 생성하기 == //
	// 포인트로 결제후 포인트를 차감해주는 기능(메소드)
	// 리턴값은 결제하고 남은 포인트 잔고이다.
	int pointPayment(int point) {	// 메소드는 반드시 타입이 있어야 한다.
		
		int jangoPoint = this.point;
		
		if(this.point == 0) {
			System.out.println(">> \"" + name + "\"님 현재 포인트가 0 이므로 포인트 결제가 불가합니다. <<");
			
		}
		
		else if(this.point < point) {
			System.out.println(">> \"" + name + "\"님 현재 포인트가 잔고가 부족하므로 결제가 불가합니다. <<");
		
		}
		
		else {
			System.out.println(">> \"" + name + "\" 님 " + point + "포인트 결제가 완료되었습니다. <<");
			jangoPoint = jangoPoint - point;
		}
		
		return jangoPoint;	// int값으로 돌려줘야 함
	
	}// end of int pointPayment(int point)------------------------------------
	
	
	String memberInfo(int n){
		
		String title = "";
		
		if(n==0) {
			title = "=== "+ name +"님의 회원정보 수정하기전 ===\n";
		}
		
		else if(n==1) {
			title = "=== "+ name +"님의 회원정보 수정후 ===\n";
		}
		
		return title
			 + " 1. 아이디 : "+ userid +" \n"
			 + " 2. 암호 : "+ passwd +" \n" 
			 + " 3. 성명 : "+ name +" \n"
			 + " 4. 나이 : "+ age +" \n"
			 + " 5. 포인트 : "+ point;
		
	}// end of String memberInfo()--------------------------------------------
	
	
	String changeInfo(String passwd, int age, int point) {
		
		String before_info = memberInfo(0); // String 변경전 정보
		
		this.passwd = passwd;
		this.age = age;
		this.point = point;
		
		String after_info = memberInfo(1);	// String 변경후 정보
		
		return before_info + "\n\n" + after_info ;
		
	}// end of String changeInfo(String passwd, int age, int point)-----------------

	
	// !!!! 메소드의 오버로딩(overloading) !!!!
	// 1. 메소드의 이름은 같더라도 파라미터의 개수가 다르면 다른 메소드로 취급하므로 메소드 중복이 아니다.
	// 2. 메소드의 이름이 같고, 파라미터의 개수도 같더라도 파라미터의 데이터타입의 순서가 다르면 다른 메소드로 취급하므로 메소드 중복이 아니다.
	// 3. 메소드의 이름이 같고, 파라미터의 개수도 같고, 파라미터의 데이터타입의 순서도 같고, 단 return 타입은 다를 경우라도 메소드 중복이라고 본다.
	// 그러므로 메소드의 이름이 같지만 메소드가 중복이 안되려면 리턴타입은 필요가 없고 오로지 파라미터가 달라야 한다.
	
	String changeInfo(String passwd, String name, int age, int point) {
		
		String before_info = memberInfo(0); // String 변경전 정보
		
		this.passwd = passwd;
		this.name = name;
		this.age = age;
		this.point = point;
		
		String after_info = memberInfo(1);	// String 변경후 정보
		
		return before_info + "\n\n" + after_info ;
		
	}// end of String changeInfo(String passwd, String name, int age, int point)-----------------

}

