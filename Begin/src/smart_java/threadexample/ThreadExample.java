package smart_java.threadexample;
import java.util.*;

public class ThreadExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
/*
		System.out.println("[싱글 스레드 실행]:");
		System.out.println(Thread.currentThread().getName());
		for(int i = 0; i < 5; i++) System.out.print(i);
		System.out.println();
		System.out.println(Thread.currentThread().getName());
		for(int i = 0; i < 5; i++) System.out.print(i);
		System.out.println();
*/
/*		
		System.out.println("[멀티 스레드 실행]");
		ThreadA ta = new ThreadA();
		Runnable rb = new ThreadB();
		Thread tb = new Thread(rb);
		tb.setPriority(10);
		ta.start();
		tb.start();
*/		
/*
		System.out.println("[데몬 스레드 실행]");
		ThreadC tc = new ThreadC();
		ThreadD td = new ThreadD();
		td.setDaemon(true);
		tc.start();
		td.start();
*/	

		System.out.println("[join() & yield() 실행]");
		ThreadE te = new ThreadE();
		ThreadF tf = new ThreadF();
		te.start();
		try {			
			te.join();
		} catch(Exception e) { }
		System.out.println("join() 종료");
		tf.start();

	}
}
class ThreadA extends Thread {
	public void run() {
		for(int i = 0; i < 5; i++)
			System.out.println("Thread A : "+getName()+":"+i);
	}
}
class ThreadB implements Runnable {
	public void run() {
		for(int i = 0; i < 5; i++) 
			System.out.println("Thread B : "+Thread.currentThread().getName()+":"+i);
	}
}
class ThreadC extends Thread {
	public void run() {
		for(int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch(Exception e) { }
			System.out.println("Thread C : "+getName()+":"+i);
		}
	}
}
class ThreadD extends Thread {
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch(Exception e) { }
			System.out.println("Thread D : "+getName()+" : Daemon Run!!");
		}
	}
}
class ThreadE extends Thread {
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("Thread E : "+getName()+":"+i);
			Thread.yield();
		}
	}
}
class ThreadF extends Thread {
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("Thread F : "+getName()+":"+i);
			Thread.yield();
		}
	}
}
