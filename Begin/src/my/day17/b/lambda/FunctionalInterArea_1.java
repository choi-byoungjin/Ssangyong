package my.day17.b.lambda;

@FunctionalInterface // 어노테이션으로 막아두면 미완성 메소드 하나만 만들게 할 수 있다.
public interface FunctionalInterArea_1 {

	void area(double x, double y, int type);

	// void test(); 
} // 미완성 메소드가 한개만 있어야 람다식으로 사용할 수 있다.
