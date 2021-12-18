package smart.interfacesrc;
import smart.interfaces.*;

public class Robot implements Toggle, PushButton {

	public void on_off() { // Toggle 인터페이스
		System.out.println("["+Toggle.name+"]");
	}
	public void press() {  // PushButton 인터페이스
		System.out.println("["+PushButton.name+"]");
	}
	public static void main(String[] args) {

		System.out.println("[Robot Class]");
		Robot r = new Robot();
		r.on_off();
		r.press();
		
		Toggle[] t = new Toggle[2];
		t[0] = r;
		t[1] = new Phone();
		
		for(int i=0; i< t.length; i++ ) {
			System.out.print("Toggle["+i+"] : ");
			t[i].on_off();
		}

	}
}
