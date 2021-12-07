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

	// behavior, 행위, 기능, 함수, method
	void showInfo() {
		
		System.out.println("==="+name+"님의 회원정보 ===\n"
						+ "1. 아이디 : " + userid + "\n"
						+ "2. 암호 : " + passwd + "\n"
						+ "3. 성명 : " + name + "\n"
						+ "4. 나이 : " + age + "\n"
						+ "5. 포인트 : " + point + "\n");
		
	}
	
}
