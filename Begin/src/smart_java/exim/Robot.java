package smart_java.exim;

public class Robot extends Hardware implements Switch {

	public void powerOn() {  	// Hardware 추상 클래스로 부터 정의(필수)
		if(power == false) {
			power = true;
			System.out.println("[Power ON]");
		} else {
			System.out.println("Already Power ON!!");
		}
	}
	public void powerOff() {	// Hardware 추상 클새스로 부터 오버라이딩
		if(power == true) {
			power = false;
			System.out.println("[Power OFF]");
		} else {
			System.out.println("Already Power OFF!!");
		}
	}
	public void switchOn() {	// Switch 인터페이스로 부터 정의(필수)
		System.out.println(Switch.NAME+" Trying.. Power ON!!");
		powerOn();
	}
	public void switchOff() {	// Switch 인터페이스로 부터 정의(필수)
		System.out.println(Switch.NAME+" Trying.. Power OFF!!");
		powerOff();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Robot r = new Robot();
		r.switchOn();
		r.switchOn();
		r.switchOff();
		r.switchOff();
		r.switchOn();
	}
}
