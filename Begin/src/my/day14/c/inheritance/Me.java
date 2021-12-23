package my.day14.c.inheritance;

//public class Me extends Father {
public class Me extends Mother{
//public class Me extends Father, Mother{
	// 자바에서는 클래스 생성시 다중 상속이 불가하다!!!
	// 참고 : C++ 에서는 다중상속이 가능하다
	// 자바에서 다중 상속을 하려면 인터페이스를 사용하면 된다.
	void info() {
		//System.out.println("1.아이디 : " + super.userid);
		System.out.println("2.성명 : " + super.name);
	}

	
}
