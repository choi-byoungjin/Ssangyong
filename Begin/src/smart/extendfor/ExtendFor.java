package smart.extendfor;
import java.util.*;

public class ExtendFor {

	public enum Car { Taxi, Truck, Bus, Trailer };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[배열 : for문]");
		int[] arr = new int[] { 10,20,30,40,50 };
		for(int i=0; i<arr.length; i++) {
			System.out.println(arr[i]);
		}
		System.out.println("[배열 : 확장 for문]");
		for(int e : arr) {
			System.out.println(e);
		}
		System.out.println("[컬렉션 : while문]");
		ArrayList<String> al = new ArrayList<String>();
		
		Iterator<String> it = al.iterator();
		al.add(new String("AAA"));
		al.add(new String("BBB"));
		al.add(new String("CCC"));
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("[컬렉션 : 확장 for문]");
		for(String el : al) {
			System.out.println(el);
		}

		System.out.println("[열거형 실습]");
		System.out.println(Car.Taxi);
		System.out.println(Car.Truck);
		System.out.println(Car.Bus);
		System.out.println(Car.Trailer);
		
		Car my = Car.Taxi;
		
		if( my == Car.Taxi ) {
			System.out.println("My = "+ my);
		} 		
	}

}
