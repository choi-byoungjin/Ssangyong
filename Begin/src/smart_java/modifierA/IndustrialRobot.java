package smart_java.modifierA;  // 패키지 지정
//import modifierB.*;

public class IndustrialRobot extends Robot {  // Robot 클래스 상속
	
	public static void main(String[] args) {
		
		IndustrialRobot a = new IndustrialRobot();
		
		//a.x = 10;   	// private 접근제한
		//a.y = 20;		// private 접근제한
		a.setX(10);
		a.setY(20);
		
		System.out.println("[IndustrialRobot Class]");
		//System.out.println("X="+a.x);	// private 접근제한
		//System.out.println("Y="+a.y);	// private 접근제한
		System.out.println("getX()="+a.getX());
		System.out.println("getY()="+a.getY());

		a.job = 100;				
		System.out.println("JOB="+a.job);	// friendly 접근제한
	}


}
