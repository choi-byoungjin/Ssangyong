package smart.inner;

public class Robot {
//public final class Robot {  // 상속 금지
	
	Arm left;			// 내부 멤버 클래스 선언
	int armLength;
	
	public Robot() {}
	public Robot(int armLength) {
		this.armLength = armLength;
		left = new Arm();	// 내부 멤버 클래스의 객체 생성
		left.armPower = 100;	// 내부 멤버 클래스의 armPower 멤버 변수에 값 지정
	}
	class Arm {		// 내부 멤버 클래스
		int armPower;
		int getPower() {
			int result = armLength / armPower; // 외부 클래스의 armLength 사용
			return result;
		}
	}
	
    void gotoxy(int x, int y) {
    // final void gotoxy(int x, int y) {	// 오버라이딩 금지

		class MoveXY {		// 메소드안에 내부 클래스 정의
			int x, y;
			public MoveXY(int x, int y) {
				this.x = x+10;
				this.y = y+10;
			}
			public void printOut() {
				System.out.println("[MOVE][X] = "+x);
				System.out.println("[MOVE][Y] = "+y);
			}
		}
		
		MoveXY m = new MoveXY(x, y);	// 내부 클래스 객체 생성
		m.printOut();
		
	}
	void call(Object o) {
		System.out.println(o.toString());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Robot r = new Robot(500);
		
		System.out.println("[내부 멤버 클래스]");
		int power = r.left.getPower();	// 내부 멤버 클래스 호출
		System.out.println("[Robot.Arm.getPower()] = "+power);
		
		System.out.println("[메소드안에 정의된 내부 클래스]");
		r.gotoxy(10, 10);

		System.out.println("[내부 무명 클래스]");

		r.call(new Object() { 
			public String toString() {
				return "{내부 무명 클래스}";
			}
		});
		
	}

}
