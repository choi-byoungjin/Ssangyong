package smart_java.abstractsrc;

import smart_java.abstractclass.*;

public class Robot extends Hardware {
	
	public void powerOn() {	// Hardware 추상클래스에서 오버라이딩 하지 않으면 오류 발생
		power = true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Hardware[] h = new Hardware[2];
		h[0] = new Robot();
		h[1] = new Phone();
		System.out.println("[Robot Class]");
		for(int i=0; i<h.length; i++ ) {
			h[i].powerOn();
			System.out.println("Hardware["+i+"].power = "+h[i].power);
		}
		
	}

}
