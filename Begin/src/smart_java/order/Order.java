package smart_java.order;
import java.util.*;

public class Order {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("[주문처리 프로그램]");
		OrderList<String> list = new OrderList<String>();
		Runnable r1 = new Adding(list);
		Runnable r2 = new Getting(list);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();

	}

}
class OrderList<T> {
	LinkedList<T> list = new LinkedList<T>();
	
	synchronized void add(T data) {
		list.offer(data);
		notify();
		System.out.println("[ADD]"+list.toString());
	}
	synchronized T get() {
		T value = null;
		if( list.isEmpty() ) {
			try {
				wait();
			} catch(Exception e) {}
		}
		value = list.pop(); 
		return value; 
	}
}
class Adding implements Runnable {
	String[] menu = { "Kimchi", "Bulgogi", "Pizza", "Chiken", "Coke" };
	OrderList<String> list;
	public Adding(OrderList<String> list) {
		this.list = list;
	}
	public void run() {
		for(int i=0; i < 10; i++) {
			try {
				Thread.sleep(200);
			} catch(Exception e) {}
			String value = menu[(int)(Math.random() * 4)];
			list.add(value);
		}
	}
}
class Getting implements Runnable {
	OrderList<String> list;
	public Getting(OrderList<String> list) {
		this.list = list;
	}
	public void run() {
		while(true){
			try {
				Thread.sleep(500);
			} catch(Exception e) {}
			String value = list.get();
			System.out.println("Order Remove : "+value);
		}
	}
}


