package smart.inheritance; // 패키지 지정

public class Robot {
	// field
	int x;				// 로봇의 현재 x좌표
	int y;				// 로봇의 현재 y좌표
	int angle;			// 로봇 각도
	static int job;		// 로봇 작업 카운터
	
	// method
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
		this.job++;  // 물건을 내려놓으면 한번 작업(로봇이 여러대 있으면 모든 로봇의 작업량 카운트)
	}

}

