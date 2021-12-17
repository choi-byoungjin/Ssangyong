package smart.overriding; // 패키지 지정

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
	void gotoxy(int x, int y) { 
		this.x = this.x + x;
		this.y = this.y + y;
	}
	void moveAngle(int angle) {	
		this.angle = this.angle + angle;
	}
	void pickup() {
		System.out.println("xy["+x+","+y+"], angle["+angle+"] Pick Up !!");
	}
	void putdown() {
		System.out.println("xy["+x+","+y+"], angle["+angle+"] Put Down !!");
		this.job++;  
	}
	void printOut() {
		System.out.println("[Robot Class]");
		System.out.println("[X="+this.x+"]");
		System.out.println("[Y="+this.y+"]");
		System.out.println("[ANGLE="+this.angle+"]");
		System.out.println("[JOB="+this.job+"]");
	}

}

