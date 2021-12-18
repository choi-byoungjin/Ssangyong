package smart_java;

public class ThisLion {

	int height;
	int weight;
	int age;
	boolean sex;   // true:남, false:여
	static String name;   // 정적 멤버 변수 선언
	static int version;
	
	public ThisLion() {  // 디폴트 생성자 메소드
	}
	/*
	public ThisLion(int g, boolean s) {  // 사용자 정의 메소드
		height = weight = 0;
		age = g;
		sex = s;
	}
	public ThisLion(int h, int w, int g, boolean s) {  // 사용자 정의 메소드
		age = g;
		sex = s;
		height = h;
		weight = w;
	}
	*/
	public ThisLion(int age, boolean sex) {  // ����� ���� ������ �޼ҵ�
		this.age = age;
		this.sex = sex;
	}
	public ThisLion(int height, int weight, int age, boolean sex) {  // ����� ���� ������ �޼ҵ�
		this(age, sex);
		this.height = height;
		this.weight = weight;
	}
	
	int running(int x, int y) {		// �޸���
		int result = (age*x)-y;
		return result;
	}
	int running(int x) {	    	// �޸��� (�޼ҵ� �����ε�)
		int result = (age*x);
		return result;
	}
	int running(double x) {	    	// �޸��� (�޼ҵ� �����ε�)
		double result = (age*x);
		return (int)result;
	}
	boolean fight(ThisLion x) {			// �ο��
		boolean result = true;
		if( height > x.height && weight > x.weight ) 
			 result = true;
		else result = false;
		return result;
	}
	boolean hunting() {				// ����ϱ�
		if( age > 20 ) return false;
		else return true;
	}
	boolean hunting(int ... x) {		// ���� �μ�	
		int sum = 0;
		for(int i=0; i<x.length; i++) sum += x[i];
		if( sum < 50 ) return false;
		else return true;
	}
	boolean hunting(String name, int ... x) {			
		System.out.print("[Hunting.."+name+"] ");
		int sum = 0;
		for(int i=0; i<x.length; i++) sum += x[i];
		if( sum < 50 ) return false;
		else return true;
	}
	String sleeping() {				// ���ڱ�
		return "Going to Sleep!!!";
	}
	void printOut() {
		System.out.println("height="+height);
		System.out.println("weight="+weight);
		System.out.println("age="+age);
		System.out.println("sex="+( sex ? "MAIL" : "FEMAIL"));
	}
	
	static String whoami() {		// 정적 멤버 메소드 정의
		return "[This is "+name+" Class]";
	}
	
	public static void main(String[] args) {

		ThisLion a = new ThisLion(190,100,10,true);  // ������ �޼ҵ� ����
		System.out.println("[A Lion]");
		a.printOut();
	
		ThisLion b = new ThisLion(180,70,5,false);
		System.out.println("[B Lion]");
		b.printOut();
		
		// 정적 멤버 메소드
		ThisLion.name = "Lion";
		System.out.println("ThisLion.whoami() : "+ThisLion.whoami());
		System.out.println("a.whoami() : " + a.whoami());
		System.out.println("b.whoami() : " + b.whoami());

		a.name = "Lion's King";
		System.out.println("ThisLion.whoami() : "+ThisLion.whoami());
		System.out.println("a.whoami() : " + a.whoami());
		System.out.println("b.whoami() : " + b.whoami());

		// 정적 멤버 변수
		ThisLion.version = 10;
		System.out.println("version = "+(ThisLion.version+a.version+b.version));
		a.version = 20;
		System.out.println("version = "+(ThisLion.version+a.version+b.version));
		b.version = 30;
		System.out.println("version = "+(ThisLion.version+a.version+b.version));

		a = b = null;
		System.gc();
		
	}

}
