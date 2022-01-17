package my.day17.a.annonymouseClass;

public class Bok_Annonymous_main {

	public static void main(String[] args) {
		
		System.out.println("\n=== 1. 일반 클래스 사용시 ===");
		
		Bok_InterArea area1 = new Bok_Area();
		double areaSize1 = area1.bok_area(10.5, 5.5);
		System.out.println("가로 10.5, 세로 5.5 인 면적은 : " + areaSize1);
		
		Bok_InterArea area2 = new Bok_Area();
		double areaSize2 = area2.bok_area(20.5, 5.5);
		System.out.println("가로 20.5, 세로 5.5 인 면적은 : " + areaSize2);
		
		
		System.out.println("\n=== 2. 익명(무명, annonymous) 클래스 사용시 ===");
		
		Bok_InterArea areaObj = new Bok_InterArea() {

			@Override
			public double bok_area(double x, double y) {
				return x*y;
			}	
		};
		
		double areaSize3 = areaObj.bok_area(10.5, 5.5);
		System.out.println("가로 10.5, 세로 5.5 인 면적은 : " + areaSize3);

	}

}
