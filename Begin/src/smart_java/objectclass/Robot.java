package smart_java.objectclass;

public class Robot extends Object {

	int x;			
	int y;			
	int angle;		
	int job;		
	public Robot() {
	}
	public Robot(int x, int y, int angle, int job) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.job = job;
	}
	
	// toString() 오버라이딩
	public String toString() {
		String result ="[Robot]:";
		result += "[X="+this.x+"]";
		result += "[Y="+this.y+"]";
		result += "[ANGLE="+this.angle+"]";
		result += "[JOB="+this.job+"]";
		return result;
	}
	
	// equals() 오버라이딩
	public boolean equals(Object obj) {
		Robot r = (Robot)obj;	// 여기서 r은 b이다
		if( r.x == x && r.y == y )
		  	 return true;
		else return false;
	}
	
}


