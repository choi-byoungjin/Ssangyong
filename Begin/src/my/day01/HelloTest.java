package my.day01;

//import java.lang.*;	항상 생략되어있음
import java.util.Date;

public class HelloTest {

	public static void main(String[] args) {
		
		System.out.println("Hello World");
		System.out.println("안녕하세요~~");
		
		//java.util.Date now = new java.util.Date(); // import 를 할 필요가 없는 경우
		Date now = new Date();						 //import 를 해야 할 경우
		
		System.out.println("현재시간 : " + now);
	}

}
