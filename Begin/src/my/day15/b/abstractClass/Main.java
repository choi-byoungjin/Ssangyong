package my.day15.b.abstractClass;

import java.util.Iterator;

public class Main {

	public static void main(String[] args) {
		
		// >>> 다형성(Polymorphism) <<< 
		// ==> 상속을 이용하여 여러 클래스 타입을 하나의 클래스 타입으로 다루는 기술.
		// 자식클래스로 생성되어진 객체를 부모 클래스 타입으로 받을 수 있다는 것이 다형성(Polymorphism)이다.
		
		Animal[] aniArr = new Animal[5];
		
		Dog dog = new Dog();
	//	Animal ani1 = new Dog(); // 가능
	//	aniArr[0] = new Dog();	 // 가능
		dog.setName("뽀삐");
		dog.setBirthYear(2010);
		dog.setWeight(5);
		
		aniArr[0] = dog;
		
		Cat cat = new Cat();
	//	Animal ani2 = new Cat(); // 가능
	//	aniArr[1] = new Cat();	 // 가능
		cat.setName("톰");
		cat.setBirthYear(2017);
		cat.setColor("검정");
		
		aniArr[1] = cat;

		Duck duck = new Duck();
	//	Animal ani3 = new Duck(); // 가능
	//	aniArr[2] = new Duck();	 // 가능
		duck.setName("도날드");
		duck.setBirthYear(2018);
		duck.setPrice(5000);

		aniArr[2] = duck;
		
	//	Animal ani = new Animal();  
		// Animal 은 미완성 클래스(추상 클래스, abstract class)이므로 객체생성(인스턴스화)이 불가하다.
		
		Animal ani2 = new Dog();
		// 그러나 자식클래스가 일반클래스라면 자식클래스로 생성되어진 객체는 부모클래스가 미완성 클래스이더라도 
		// 자식클래스 객체를 받을 수 있다.
		// 그래서 일반적으로 추상클래스의 용도는 자식클래스의 저장소 역할을 한다.
		
		for (int i = 0; i < aniArr.length; i++) {
			if(aniArr[i] != null)
				aniArr[i].showInfo();
		}// end of for -------------------------------------

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		for (int i = 0; i < aniArr.length; i++) {
			if(aniArr[i] != null)
				aniArr[i].cry();
		}// end of for -------------------------------------

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for (int i = 0; i < aniArr.length; i++) {
			if(aniArr[i] != null)
				aniArr[i].move();
		}
		
	}// end of main--------------------------------------------

}
