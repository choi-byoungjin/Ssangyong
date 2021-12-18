package smart_java.abstractsrc;

import smart_java.abstractclass.*;

public class Phone extends Network {

	public void powerOn() {	// Hardware 추상 클래스에서 오버라이딩 하지 않으면 오류 발생
		power = true;
	}

	public void connectOn() {   // Network 추상 클래스에서 오버라이딩 하지 않으면 오류 발생
		connect = true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Network n = new Phone();
		n.powerOn();
		n.connectOn();
		
		System.out.println("[Phone Class]");
		System.out.println("power = "+ n.power);
		System.out.println("connect = "+ n.connect);
	}

}
