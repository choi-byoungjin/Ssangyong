package smart.casting;  

public class PetRobot extends Robot {  
	String master;
	
	public PetRobot() {
	}
	public PetRobot(int x, int y, int angle, String master) {
		super(x,y,angle);
		this.master = master;
	}
	void gotoxy(int x, int y) {    // 오버라이딩, 접근제어 private 안됨
		this.x = this.x + x + 20;
		this.y = this.y + y + 20;
	}
	void printOut() {		// 오버라이딩
		super.printOut();
		System.out.println("[PetRobot]");
		System.out.println(this.master);
	}

	public static void main(String[] args) {
		PetRobot a = new PetRobot(10,10,10, "KING");
		a.gotoxy(15,15);  	// 하위 클래스 x, y 좌표 이동
		a.printOut();
	}
}
