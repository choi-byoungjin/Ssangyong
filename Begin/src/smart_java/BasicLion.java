package smart_java;

public class BasicLion {

	int height;
	int weight;
	int age;
	boolean sex;   // true:남, false:여
	
	int running(int x, int y) {		// 달리기
		int result = (age*x)-y;
		return result;
	}
	boolean fight(BasicLion x) {	// 싸우기
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

		BasicLion a = new BasicLion();
		BasicLion b = new BasicLion();
		
		a.height = 160;
		a.weight = 100;
		a.age = 10;
		a.sex = true;	// a객체에 대한 멤버변수 지정
		
		b.height = 130;
		b.weight = 90;
		b.age = 9;
		b.sex = false;	// b객체에 대한 멤버변수 지정
		
		System.out.println("[A Lion]");
		a.printOut();
		System.out.println("running = "+a.running(5, 20));
		System.out.println("hunting = "+a.hunting());
	
		System.out.println("[B Lion]");
		b.printOut();
		System.out.println("running = "+b.running(6, 30));
		System.out.println("hunting = "+b.hunting());
		
		boolean result = b.fight(a);  // a와 b가 싸우기
		String win = result ? "B Lion WINNER!!" : "A Lion WINNER!!";
		System.out.println("A FIGHT B : "+win);
		
		System.out.println("A Lion : "+a.sleeping());
		System.out.println("B Lion : "+b.sleeping());
		
		a = b = null;
		System.gc();
		
	}

}
