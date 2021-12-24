package my.day16.b.Interface;

public class Circle implements Figure {

	// Circle 이라는 클래스는 Figure 라는 인터페이스를 implements(구현, 재정의) 한다는 말이다.
	
	@Override
	public double area(double x, double y) {
		return 0;
	}

	@Override
	public double circleArea(double r) {
		return r*r*PI;
	}

}
