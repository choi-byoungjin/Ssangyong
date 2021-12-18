package smart_java.objectclass;

public class ObjectClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//System.out.println("[toString() 메소드 오버라이딩 안함]");
		System.out.println("[toString() 메소드 오버라이딩 함]");

		Robot a = new Robot(10,20,30,40);
		Robot b = new Robot(10,20,30,40);
		System.out.println(a.toString());
		System.out.println(b.toString());

		//System.out.println("[equals() 메소드 오버라이딩 안함]");
		//a = b;
		System.out.println("[equals() 메소드 오버라이딩 함]");

		if( a.equals(b)) {
			System.out.println("[a.equals(b)] : true");
		} else { 
			System.out.println("[a.equals(b)] : false");
		}
		
		System.out.println("[클래스 및 객체 비교]");
		a = new Robot();
		b = new Robot();
		System.out.println("[a == b] : "+(a==b));
		System.out.println("[a != b] : "+(a!=b));
		System.out.println("[a instanceof Robot] : "+(a instanceof Robot));
		System.out.println("[b instanceof Robot] : "+(b instanceof Robot));
		System.out.println("[a instanceof Object] : "+(a instanceof Object));
		System.out.println("[b instanceof Object] : "+(b instanceof Object));
		
	}

}
