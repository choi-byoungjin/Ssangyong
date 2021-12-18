package smart.genericclass;
import java.util.*;

public class GenericClass {
	Box<Toy> boxt;
	Box<Robot> boxr;
	Box<Car> boxc;
	
	public GenericClass() {
		boxt = new Box<Toy>();
		boxt.add(new Toy());
		boxt.add(new Robot());
		boxt.add(new Car());
		//boxt.add(new String("AAA"));
		
		boxr = new Box<Robot>();
		//boxr.add(new Toy());
		boxr.add(new Robot());
		//boxr.add(new Car());  // 로봇 객체 전용
		
		boxc = new Box<Car>();
		//boxc.add(new Toy());
		//boxc.add(new Robot());
		boxc.add(new Car());  // 자동차 객체 전용
	}
	public void print() {
		System.out.println("[제네릭 제한 <T extends Toy>]");
		System.out.println("[Box<Toy>]");
		System.out.println(boxt.toString());
		System.out.println("[Box<Robot>]");
		System.out.println(boxr.toString());
		System.out.println("[Box<Car>]");
		System.out.println(boxc.toString());

		System.out.println("[제네릭 제한 <? extends Toy>]");
		System.out.println(call(boxt));
		System.out.println(call(boxr));
		System.out.println(call(boxc));
		
		// 제네릭 제한 (참조)
		Box<? extends Toy>[] b = new Box[3];
		b[0] = boxt;
		b[1] = boxr;
		b[2] = boxc;
		for(int i=0; i<b.length; i++)
			System.out.println(call(b[i]));
	}
	public String call(Box<? extends Toy> box) {
		return box.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("[제네릭 : String 전용 ArrayList]");
		ArrayList<String> als = new ArrayList<String>();
		als.add(new String("AAA"));
		als.add(new String("BBB"));
		Iterator<String> its = als.iterator();
		while(its.hasNext())
			System.out.println(its.next());
		
		System.out.println("[제네릭 : Integer 전용 ArrayList]");
		ArrayList<Integer> ali = new ArrayList<Integer>();
		ali.add(100);
		ali.add(200);
		Iterator<Integer> iti = ali.iterator();
		while(iti.hasNext())
			System.out.println(iti.next());
		
		System.out.println("[제네릭 클래스 정의]");
		Member<String> ms = new Member<String>();
		ms.add(new String("CCC"));
		System.out.println("Member<String> : "+ms.get());
		
		Member<Integer> mi = new Member<Integer>();
		mi.add(500);
		System.out.println("Member<Integer> : "+mi.get());

		GenericClass gc = new GenericClass();
		gc.print();
		
	}
	class Toy { 
		public String toString() { 
			return "Hello! Toy"; 
		} 
	}
	class Robot extends Toy { 
		public String toString() { 
			return "Hello! Robot"; 
		} 
	}
	class Car extends Toy { 
		public String toString() { 
			return "Hello! Car"; 
		} 
	}
	class Box<T extends Toy> {
//	class Box<T> {
		ArrayList<T> list = new ArrayList<T>();
		void add(T item) {
			list.add(item);
		}
		public String toString() {
			String result = "";
			Iterator<T> it = list.iterator();
			while(it.hasNext()) {
				result += it.next().toString()+" : "; 
			}
			return result;
		}
	}
}

