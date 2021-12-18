package smart_java;

public class OverloadingLion { // 타입을 다르게 해서 다른 동작을 할 수 있게 하는 것이 메소드 오버로딩이다.

	int height;
	int weight;
	int age;
	boolean sex;   // true:남, false:여
	int speed;  
	
	public OverloadingLion() {  // 디폴트 생성자 메소드
	}
	public OverloadingLion(int g, boolean s) {  // 사용자 정의 메소드
		height = weight = 0;
		age = g;
		sex = s;
	}
	public OverloadingLion(int h, int w, int g, boolean s) {  // 사용자 정의 메소드
		height = h;
		weight = w;
		age = g;
		sex = s;
	}
	
	int running(int x, int y) {		// 달리기
		int result = (age*x)-y;
		return result;
	}
	int running(int x) {	    	// 달리기 (메소드 오버로딩)
		int result = (age*x);
		return result;
	}
	/*
	String running(int x) {	    	// 달리기(메소드 오버로딩ε� ��Ģ ����)
		int result = (age*10);
		return "OK";
	}
	*/
	int running(double x) {	    	// 달리기 (메소드 오버로딩)
		double result = (age*x);
		return (int)result;
	}
	
	boolean fight(OverloadingLion x) {			// 싸우기
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
	boolean hunting(int ... x) {		// 사냥하기
		int sum = 0;
		for(int i=0; i<x.length; i++) sum += x[i];
		if( sum < 50 ) return false;
		else return true;
	}
	boolean hunting(String name, int ... x) {	// 가변인자는 마지막에 지정		
		System.out.print("[Hunting.."+name+"] ");
		int sum = 0;
		for(int i=0; i<x.length; i++) sum += x[i];
		if( sum < 50 ) return false;
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

		OverloadingLion a = new OverloadingLion(190,100,10,true);

		System.out.println("[A Lion]");
		System.out.println("int running(int, int) : "+a.running(5, 10));
		System.out.println("int running(int) : "+a.running(5));
		System.out.println("int running(double) : "+a.running(5.5));
	
		OverloadingLion b = new OverloadingLion(180,70,5,false);
		System.out.println("[B Lion]");
		System.out.println("int hunting(int ... x) : "+b.hunting(10,8,6));
		System.out.println("int hunting(String name, int ... x) : "+b.hunting("rabbit",10,20,30,40,50));
		

		a = b = null;
		System.gc();
		
	}

}
