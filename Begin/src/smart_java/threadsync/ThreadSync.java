package smart_java.threadsync;
import java.util.*;

public class ThreadSync {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Runnable r = new ThreadRun();
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
				
		System.out.println("[멀티 스레드 동기화 실습]");
		t1.start();
		t2.start();
		
		//new Thread(r).start();
		//new Thread(r).start();
		
	}
}
class Counter {
	private Integer count = 10;
	public Integer getCount() {
		return count;
	}
	public synchronized void incCount(String tname) {
	//public void incCount(String tname) {
		//synchronized(count) {
			try {
				Thread.sleep(10);
			} catch(Exception e) { }
			this.count++;
			System.out.println(tname+": count increment : "+count);
		//}
	}
}
class ThreadRun implements Runnable {
	Counter c = new Counter();

	public void run() {
		for(int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch(Exception e) { }
			c.incCount(Thread.currentThread().getName());
		}
	}
}
