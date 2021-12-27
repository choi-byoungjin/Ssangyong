package my.day16.a.Final;

public class Bok_ChildClass_3 extends Bok_NoFinalClass_2{

	void test() {
		String name = super.name;
		double radius = 5;
		double circle_area = super.PI*radius*radius;
	}

	@Override
	protected void test1() {
		System.out.println("~~~ 안녕하세요");
	}
/*
	@Override
	protected void test2() {
		System.out.println("~~~ Hello ~~~");
	}
*/
}
