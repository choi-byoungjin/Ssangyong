package my.day16.a.Final;

public class NoFinalClass_2 {
	
	String name = "이순신";
	final double PI = 3.141592;
	// 필드에 final 을 붙이면 해당 변수에는 값을 부여한 다음에 새로운 값으로 변경이 불가하다.
	// 그래서 final 변수를 상수변수 라고도 부른다.
	
	protected void test1() {
		System.out.println("~~~ 테스트1 입니다. ~~~");
	}
	
	protected final void test2() {
		// 메소드 final 을 붙이면 해당 메소드는 자식클래스에서 오버라이딩(재정의)이 불가하다.
		System.out.println("~~~ 테스트1 입니다. ~~~");
	}
}