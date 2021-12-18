package smart.rmcclass;
import java.util.*;

public class RMCClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("[Random 클래스]");
		Random r = new Random();
		for(int i=0; i<5; i++) 
			System.out.println("nextInt(100) : "+r.nextInt(100));
		
		for(int i=0; i<5; i++) 
			System.out.println("nextDouble() : "+r.nextDouble());

		System.out.println("\n[Math 클래스]");
		System.out.println("abs(-20):"+Math.abs(-20));
		System.out.println("max(10,20):"+Math.max(10,20));
		System.out.println("min(10,20):"+Math.min(10,20));
		System.out.println("log(10):"+Math.log(10));
		System.out.println("round(10.567):"+Math.round(10.567));
		System.out.println("sqrt(2):"+Math.sqrt(2));
		System.out.println("pow(2,10)"+Math.pow(2,10));
		System.out.println("sin(360):"+Math.sin(360));
	
		System.out.println("\n[Calendar 클래스]");
		Calendar now = Calendar.getInstance();
		char[] dow = { ' ','일','월','화','수','목','금','토' };
		String[] ap = { "AM", "PM" };

		String result = "Today : ";
		result += now.get(Calendar.YEAR) + "년 ";
		result += now.get(Calendar.MONTH) + "월 ";
		result += now.get(Calendar.DATE) + "일(";
		result += dow[now.get(Calendar.DAY_OF_WEEK)] + ")";
		System.out.println(result);
		
		result = "Time : ";
		result += ap[now.get(Calendar.AM_PM)]+ " ";
		result += now.get(Calendar.HOUR) +"시(";
		result += now.get(Calendar.HOUR_OF_DAY) +") ";
		result += now.get(Calendar.MINUTE) +"분 ";
		result += now.get(Calendar.SECOND) +"초 ";
		System.out.println(result);
		
		
	}

}
