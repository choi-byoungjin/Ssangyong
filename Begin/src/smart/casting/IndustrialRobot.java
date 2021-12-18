package smart.casting;  

public class IndustrialRobot extends Robot {  
	String name;
	
	public IndustrialRobot() {
	}
	public IndustrialRobot(int x, int y, int angle, String name) {
		super(x,y,angle);
		this.name = name;
	}
	void gotoxy(int x, int y) {    // 오버라이딩, 접근제어 private 안됨
		this.x = this.x + x + 10;
		this.y = this.y + y + 10;
	}
	void printOut() {		// 오버라이딩
		super.printOut();
		System.out.println("[IndustrialRobot]");
		System.out.println(this.name);
	}

	public static void main(String[] args) {
		IndustrialRobot a = new IndustrialRobot(10,10,10, "computer");
		a.gotoxy(15,15);  	// 하위 클래스 x, y 좌표 이동
		a.printOut();
	}
}
