package my.day16.b.Interface;

public class Main {

	public static void main(String[] args) {
		
		Rectangle rect = new Rectangle();
		Triangle tri = new Triangle();
		Circle cir = new Circle();
		
		// == 다형성(Polymorphism) == //
	    // ==> 자식클래스로 생성되어진 객체를 부모클래스의 타입으로 받을 수 가 있다.
	    // ==> 특정한 인터페이스로 구현된 클래스의 객체를 특정한 인터페이스의 타입으로 받을 수 가 있다. 
		
	//	Figure fg = new Figure(); // 오류!!!
		
		Figure fg1 = new Rectangle();
		Figure fg2 = new Triangle();
		Figure fg3 = new Circle();
		
		System.out.println("가로 4, 세로 5인 사각형의 넓이 => " + fg1.area(4, 5));
		// 가로 4, 세로 5인 사각형의 넓이 => 20.0
		
		System.out.println("밑변 4, 높이 5인 사각형의 넓이 => " + fg2.area(4, 5));
		// 밑변 4, 높이 5인 삼각형의 넓이 => 10.0
		
		System.out.println("반지름이 4인 원의 넓이 => " + fg3.circleArea(4));
		// 반지름이 4인 원의 넓이 => 50.265472
		
		Figure[] fgArr = new Figure[3];
		fgArr[0] = new Rectangle();
		fgArr[1] = new Triangle();
		fgArr[2] = new Circle();
		
		for( Figure fg : fgArr) {
			if( fg instanceof Circle )
				System.out.println(fg.circleArea(4));
			else
				System.out.println(fg.area(4, 5));
		}
	}// end of main(String[] args)-------------------------------------------------
}
