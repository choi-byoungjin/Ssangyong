package my.day16.a.Final;

public class ChildClass_3 extends NoFinalClass_2 {

	void test() {
		String name = super.name;
	//	super.pi = 0.141592; // super.pi는 final 이므로 값 변경이 불가하다.
		
		double radius = 5;
		double circle_area = super.PI*radius*radius; 
	}

	@Override
	protected void test1() {
		System.out.println("~~~ 안녕하세요? 하하하");
	}
/*
	@Override
	protected void test2() {
		System.out.println("~~~ Hello ~~~");
	}
*/
}
