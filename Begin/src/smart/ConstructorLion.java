package smart;

public class ConstructorLion {

	int height;
	int weight;
	int age;
	boolean sex;   // true:남, false:여
	
	public ConstructorLion() {  // 디폴트 생성자 메소드
	}
	public ConstructorLion(int g, boolean s) {  // 사용자 정의 메소드
		height = weight = 0;
		age = g;
		sex = s;
	}
	public ConstructorLion(int h, int w, int g, boolean s) {  // 사용자 정의 메소드
		height = h;
		weight = w;
		age = g;
		sex = s;
	}
	
    int running(int x, int y) {		// 달리기
		int result = (age*x)-y;
		return result;
	}
	boolean fight(ConstructorLion x) {			// 싸우기
		boolean result = true;
		if( height > x.height && weight > x.weight ) 
			 result = true;
		else result = false;
		return result;
	}
	boolean hunting() {				// 사냥하기
		if( age > 20 ) return false;
		else return true;
	}
	String sleeping() {				// 잠자기
		return "Going to Sleep!!!";
	}
	void printOut() {
		System.out.println("height="+height);
		System.out.println("weight="+weight);
		System.out.println("age="+age);
		System.out.println("sex="+( sex ? "MAIL" : "FEMAIL"));
	}
	
	public static void main(String[] args) {

		ConstructorLion a = new ConstructorLion();	// 전체 값 초기화
		ConstructorLion b = new ConstructorLion(10,true);	// 나이, 성별 지정
		ConstructorLion c = new ConstructorLion(190, 200, 7, false);	// 키, 몸무게, 나이, 성별 모두 지정
		
		/*
		a.height = 160;
		a.weight = 100;
		a.age = 10;
		a.sex = true;
		
		b.height = 130;
		b.weight = 90;
		b.age = 9;
		b.sex = false;
		*/
		System.out.println("[A Lion]");
		a.printOut();
	
		System.out.println("[B Lion]");
		b.printOut();

		System.out.println("[C Lion]");
		c.printOut();
		
		a = b = c = null;
		System.gc();
		
	}

}
