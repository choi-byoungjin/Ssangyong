package smart_java.exim;

public abstract class Hardware {
	public boolean power = false;
	abstract public void powerOn();  // 추상 메소드
	public void powerOff() {
		power = false;
	}
}
