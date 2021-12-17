package smart.overriding;  

public class IndustrialRobot extends Robot {  
	int x, y, angle;   // 상위클래스와 동일한 멤버변수 존재
	String name;
	
	public IndustrialRobot() {
	}
	public IndustrialRobot(int x, int y, int angle) {
		super(x,y,angle);
		//this.x = x;
		//this.y = y;
		//this.angle = angle;
	}
	public IndustrialRobot(int x, int y, int angle, int job) {
		this(x, y, angle);
		super.job = job; 
		// this.job = job;
	}
	void gotoxy(int x, int y) {  // 오버라이딩, 접근제어 private 안됨
		this.x = this.x + x + 10;
		this.y = this.y + y + 10;
		//this.x = this.x + x;
		//this.y = this.y + y;
	}
	void gotoxy(int x, int y, int angle) {  // 오버라이딩된 메소드의 오버로딩
		super.x = this.x + x;
		super.y = this.y + y;
		super.angle = angle;
	}
	void printOut() {		// 오버라이딩
		super.printOut();
		System.out.println("[IndustrialRobot Class]"+this.name);
		System.out.println("[X="+this.x+"]");
		System.out.println("[Y="+this.y+"]");
	}

	public static void main(String[] args) {
		
		IndustrialRobot a = new IndustrialRobot(10,10,10);
		a.name = "computer";
		a.gotoxy(15,15);  	   	// 하위 클래스 x,y 좌표 이동
		a.gotoxy(20,20,100); 	// 상위 클래스 x,y 좌표 이동
		a.printOut();

	}
}
