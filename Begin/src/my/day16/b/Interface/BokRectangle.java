package my.day16.b.Interface;

public class BokRectangle implements BokFigure{

	@Override
	public double area(double x, double y) {
		return x*y;
	}

	@Override
	public double circleArea(double r) {
		return 0;
	}

}
