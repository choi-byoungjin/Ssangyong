package smart_java.inheritance;  // 패키지 지정

public class IndustrialRobot extends Robot {  // 같은 패키지에 잇는 Robot 클래스 상속받음
	String name; 
	
	public IndustrialRobot() {
	}
	public IndustrialRobot(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	public IndustrialRobot(int x, int y, int angle, int job) {
		this(x, y, angle);
		this.job = job;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static void main(String[] args) {
		
		IndustrialRobot a = new IndustrialRobot(10,10,10);
		IndustrialRobot b = new IndustrialRobot(20,20,10,100);
		
		a.setName("Computer");
		System.out.println(a.name);
		a.gotoxy(15,15);  	// 좌표이동
		a.moveAngle(150);	// 각도 조정
		a.pickup();			// 물건 집기
		a.putdown();		// 물건 내려놓기

		b.setName("SmartPhone");
		System.out.println(b.name);
		b.gotoxy(95,80);	// 좌표이동
		b.moveAngle(355);
		b.pickup();
		b.putdown();
		
		System.out.println("Job Count = "+Robot.job);
		

	}


}
