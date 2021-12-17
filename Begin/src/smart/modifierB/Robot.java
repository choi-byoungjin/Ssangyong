package smart.modifierB;
import smart.modifierA.*;

public class Robot {

	private int x;
	private int y;		
    
	int job;	// friendly ���� ����
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
	}
	
	/*
	protected int job;			
	public Robot() {  // �⺻ ������ �޼ҵ�
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

