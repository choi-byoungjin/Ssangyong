package smart_java.abstractclass;

public abstract class Hardware {
	public boolean power;
	abstract public void powerOn();  // 추상 메소드
	public void powerOff() {
		power = false;
	}
}
