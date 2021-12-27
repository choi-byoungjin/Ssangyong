package my.day16.b.Interface;

public class BokCircle implements BokFigure{

	@Override
	public double area(double x, double y) {
		return 0;
	}

	@Override
	public double circleArea(double r) {
		return r*r*PI;
	}

}
