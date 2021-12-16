package smart;

public class Robot {

	static String name = "Robot Arm";	// 로봇 이름
	static int limitX = 100;	// 로봇 x 좌표 한계
	static int limitY = 200;	// 로봇 y 좌표 한계
	int x;				// 로봇의 현재 x 좌표
	int y;				// 로봇의 현재 y 좌표
	int angle;			// 로봇팔의 각도
	static int job;		// 로봇팔로 물건 옮긴 횟수

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

	boolean gotoxy(int x, int y) {  // 로봇 좌표 이동
		int tx = this.x + x;
		int ty = this.y + y;
		if(tx > limitX || ty > limitY) {   // 좌표 이동 한계를 넘으면 에러 발생
			System.out.println("ERROR XY !!");
			return false;
		} else {   // 좌표 이동 한계 내이면 로봇 이동
			System.out.println("MOVE XY ["+tx+","+ty+"]");
			this.x = tx;
			this.y = ty;
			return true;
		}
	}
	void gotoxy(int ... xy ) {  // 로봇 좌표 이동(가변 인자) x1, y1, x2, y2 ...
		for(int i=0; i< xy.length; i+=2) {
			boolean result = gotoxy(xy[i], xy[i+1]);
			if(result == false) break;  // 좌표이동시 에러 발생하면 이동 종료
		}
	}
	void armAngle(int angle) {	// 로봇팔 각도 조정
		int ta = this.angle + angle;
		if( ta > 360 ) this.angle = ta - 360;
		else if( ta < 0 ) this.angle =360 + ta;
		else {
			this.angle = ta;
			System.out.println("Robot Angle : "+this.angle);
		}
	}
	void pickup() {
		System.out.println("xy["+x+","+y+"], angle["+angle+"] Pick Up !!");
	}
	void putdown() {
		System.out.println("xy["+x+","+y+"], angle["+angle+"] Put Down !!");
		Robot.job++;  // 물건을 내려놓으면 한번 작업(로봇이 여러대 있으면 모든 로봇의 작업량 카운터)
	}
	static String whoami() {
		String result = "Robot name : "+Robot.name + " Limit XY["+limitX+","+limitY+"]";
		return result;
	}
	public static void main(String[] args) {

		System.out.println(Robot.whoami());
		Robot a = new Robot(10,10,10);
		Robot b = new Robot(20,20,10,100);

		boolean ok = a.gotoxy(15,15);  // 좌표이동
		if(ok) {
			a.armAngle(150);	// 각도 조정
			a.pickup();			// 물건 집기
			a.putdown();		// 물건 내려놓기
		}
		ok = b.gotoxy(95,80);		// 좌표이동
		if(ok) {
			b.armAngle(355);
			b.pickup();
			b.putdown();
		}
		System.out.println("Job Count = "+Robot.job);
	}

}
