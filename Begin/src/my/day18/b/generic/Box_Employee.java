package my.day18.b.generic;

import java.util.*;

// === 타입에 제한이 있는 제네릭 클래스 === //

public class Box_Employee<T extends Employee> { // 클래스 이름 다음에 제네릭 타입 T extends Employee 를 선언한다. 
											    // 제네릭 타입 T extends Employee의 뜻은 Box_Anything 클래스에서 선언되어지는 필드의 타입과 메소드의 파라미터 및 리턴타입에서
											    // 사용되어지는 제네릭 타입은 Employee 클래그 포함한 자식클래스 까지만 타입으로 들어올 수 있다는 것이다.
	
	private List<T> list = new ArrayList<T>();

	public List<T> getList() {
		return list;
	}

	public void register(T item) {
		list.add(item);
	}
	
	
	
	
}
