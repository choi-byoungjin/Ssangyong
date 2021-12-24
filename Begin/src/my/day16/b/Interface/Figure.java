package my.day16.b.Interface;

public interface Figure {

	// === 인터페이스(interface)란? === //
	// 오로지 미완성메소드(추상메소드)와 field는 final변수로만 이루어진 것을 말한다.
	// 그리고 미완성메소드(추상메소드)와 field의 접근제한자는 public 으로만 되어진다.
	// 미완성메소드(추상메소드) 생성시 public abstract 이 생략되어져 있다.
	// field 생성시 public final 이 생략되어져 있다.
	
//  >> field 생성하기 << ==> public final 이 생략되어져 있다. 
//	public final double PI = 3.141592;
	double PI = 3.141592;
// ==> public final double PI = 3.141592; 와 같다.
	
//  >> method 생성하기 <<
	double area(double x, double y);
// ==> public abstract double area(double x, double y); 와 같다.
	
	double circleArea(double r);
}
