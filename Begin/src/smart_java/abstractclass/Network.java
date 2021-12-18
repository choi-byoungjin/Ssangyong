package smart_java.abstractclass;

public abstract class Network extends Hardware{
	public boolean connect;
	abstract public void connectOn();
	public void connectOff() {
		connect = false;
	}
}
