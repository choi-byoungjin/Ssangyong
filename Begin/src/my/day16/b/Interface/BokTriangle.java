package my.day16.b.Interface;

public class BokTriangle implements BokFigure{

	@Override
	public double area(double x, double y) {
		return x*y*0.5;
	}

	@Override
	public double circleArea(double r) {
		return 0;
	}

}
