package smart_java.genericclass;

public class Member<T> {
	T name;
	public Member() {  // 생성자 메소드는 제네릭 지정안해도 됨.
	}
	void add(T name) {
		this.name = name;
	}
	T get() {
		return name;
	}
}
