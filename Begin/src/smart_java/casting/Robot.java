package smart_java.casting; 

public class Robot {

	int x;			
	int y;			
	int angle;		
	int job;		
	
	public Robot() { 
	}
	public Robot(int x, int y, int angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	public Robot(int x, int y, int angle, int job) {
		this(x, y, angle);
		this.job = job;
	}
	void gotoxy(int x, int y) {  // 로봇 좌표 이동
		this.x = this.x + x;
		this.y = this.y + y;
	}
	void moveAngle(int angle) {	// 로봇팔 각도 조정
		this.angle = this.angle + angle;
	}
	void pickup() {
		System.out.println("xy["+x+","+y+"], angle["+angle+"] Pick Up !!");
	}
	void putdown() {
		System.out.println("xy["+x+","+y+"], angle["+angle+"] Put Down !!");
		this.job++;  // 물건을 내려놓으면 한번 작업(로봇이 여러대 있으면 모든 로봇의 작업량 카운터)
	}
	void printOut() {
		System.out.println("[Robot]");
		System.out.println("[X="+this.x+"]");
		System.out.println("[Y="+this.y+"]");
		System.out.println("[ANGLE="+this.angle+"]");
		System.out.println("[JOB="+this.job+"]");
	}
	public static void main(String[] args) {
		IndustrialRobot ir = new IndustrialRobot(10,10,10,"computer");
		PetRobot pr = new PetRobot(10,10,10, "KING");

		Robot ru = ir;  // 업케스팅
		ru.gotoxy(10, 10);
		ru.printOut();
		
		ru = pr;   // 업케스팅
		ru.gotoxy(10, 10);
		ru.printOut();

		// 다운 캐스팅 : 실행시 오류가 발생됨
		// IndustrialRobot ird = (IndustrialRobot)ru; 
		// ird.gotoxy(5, 5);
		// ird.printOut();
		
		
		PetRobot prd = (PetRobot)ru; // 다운 캐스팅
		prd.gotoxy(5, 5);
		prd.printOut();
	}

}

