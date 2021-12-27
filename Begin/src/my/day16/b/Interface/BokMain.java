package my.day16.b.Interface;

public class BokMain {
	public static void main(String[] args) {
		BokRectangle rect = new BokRectangle();
		BokTriangle tri = new BokTriangle();
		BokCircle cir = new BokCircle();
		
		BokFigure fg1 = new BokRectangle();
		BokFigure fg2 = new BokTriangle();
		BokFigure fg3 = new BokCircle();
		
		System.out.println("가로 4, 세로 5인 사각형의 넓이 => " + fg1.area(4, 5));
		System.out.println("밑변 4, 높이 5인 삼각형의 넓이 => " + fg2.area(4, 5));
		System.out.println("반지름이 4인 원의 넓이 => " + fg3.circleArea(4));
		
		BokFigure[] fgArr = new BokFigure[3];
		fgArr[0] = new BokRectangle();
		fgArr[1] = new BokTriangle();
		fgArr[2] = new BokCircle();
		
		for( BokFigure fg : fgArr ) {
			if( fg instanceof BokCircle)
				System.out.println(fg.circleArea(4));
			else
				System.out.println(fg.area(4, 5));
		}
		
		
	}
}
