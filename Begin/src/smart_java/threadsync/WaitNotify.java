package smart_java.threadsync;
import java.util.*;

public class WaitNotify {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[멀티 스레드 동기화 제어 실습]");
		Data data = new Data();
		Runnable r1 = new Adding(data);
		Runnable r2 = new Getting(data);
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		
	}
}
class Data {
	LinkedList<Integer> list = new LinkedList<Integer>();
	
	synchronized void add(Integer data) {
		list.offer(data);
		//notify();
		System.out.println(list.toString());
	}
	synchronized Integer get() {
		Integer value = 0;
		if( list.isEmpty() ) value = -1;
		else value = list.pop(); 
		
		//try {
		//	if(list.isEmpty()) wait();
		//} catch(Exception e) {}
		//value = list.pop(); 
		
		return value;
	}
}
class Adding implements Runnable {
	Data data;
	public Adding(Data data) {
		this.data = data;
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(2000);
			} catch(Exception e) {}
			Integer value = (int)(Math.random() * 100);
			data.add(value);
		}
	}
}
class Getting implements Runnable {
	Data data;
	public Getting(Data data) {
		this.data = data;
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch(Exception e) {}
			Integer value = data.get();
			System.out.println("Getting : "+value);
		}
	}
}
