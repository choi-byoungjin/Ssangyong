package my.day16.c.multiInterface;

public interface InterChild extends InterFather, InterMother {
	// 자바는 인터페이스가 여러 인터페이스를 다중상속 하는 것이 가능하다
	
	void play();
}
