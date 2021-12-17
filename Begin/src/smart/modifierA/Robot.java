package smart.modifierA; 

public class Robot {

	private int x;
	private int y;		
    
	int job;	// friendly 접근제한
	public Robot() { 
	}
	void setX(int x) {		// setter method
		this.x = x;
	}
	int getX() {			// getter method
		return x;
	}
	void setY(int y) {		// setter method
		this.y = y;
	}
	int getY() {			// getter method
		return y;
	}// private는 getter, setter를 사용해서 값을 불러와야 함
	
	/*
	protected int job;			
	public Robot() {  // 
	}
	protected void setX(int x) {		// setter method
		this.x = x;
	}
	protected int getX() {			// getter method
		return x;
	}
	protected void setY(int y) {		// setter method
		this.y = y;
	}
	protected int getY() {			// getter method
		return y;
	}
    */
	/*
	public int job;
	public Robot() {  // �⺻ ������ �޼ҵ�
	}
	public void setX(int x) {		// setter method
		this.x = x;
	}
	public int getX() {			// getter method
		return x;
	}
	public void setY(int y) {		// setter method
		this.y = y;
	}
	public int getY() {			// getter method
		return y;
	}
    */
}

