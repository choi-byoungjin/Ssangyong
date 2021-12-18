package smart.interfacesrc;
import smart.interfaces.*;

public class Phone implements Switch {

	public void on_off() { // Toggle 인터페이스
		System.out.println("["+Toggle.name+"]");
	}
	public void switchOn() {  // Switch 인터페이스
		System.out.println("[Switch.RED] = "+Switch.ON);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[Phone Class]");

		Phone p = new Phone();
		p.on_off();
		p.switchOn();

	}

}
