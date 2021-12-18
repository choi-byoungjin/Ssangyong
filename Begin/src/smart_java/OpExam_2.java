package smart_java;

public class OpExam_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 논리 연산자 실습
		boolean result;
		int a = 15, b = 25;
		result = !(a < b);
		System.out.println("!("+a+" < "+b+") : "+result);
		result = (a > 10) && (b < 20);
		System.out.println("("+a+">10) && ("+b+"<20) : "+result);
		result = (a > 10) || (b < 20);
		System.out.println("("+a+">10) || ("+b+"<20) : "+result);
		result = (a > 10) || (b < 20) && !(a < b);
		System.out.println("("+a+">10) || ("+b+"<20) && !("+a+"<"+b+") : "+result);

		// 조건 연산자 실습(삼항 연산자)
		int value;
		value = (a < b)  ?  10  : 20;
		System.out.println("("+a+"<"+b+") ? 10 : 20; value = "+value);
		value = (a < b)  ?   a  :   b;
		System.out.println("("+a+"<"+b+") ? a : b; value = "+value);
		value = (a < b)  ?   b - a  :   a - b;
		System.out.println("("+a+"<"+b+") ? b-a : a-b; value = "+value);

		// 비트 연산자 실습
		
		a = 1;
		value = ~a;
		System.out.println("~"+a+" : "+value);
		
		a = 3;
		b = 5;
		value = a & b;
		System.out.println(a+" & "+b+" : "+value);
		
		value = a | b;
		System.out.println(a+" | "+b+" : "+value);
		
		value = a ^ b;
		System.out.println(a+" ^ "+b+" : "+value);
		
		a = 10;
		value = a << 1;
		System.out.println(a+" << 1 : "+value);
		
		value = a >> 1;
		System.out.println(a+" >> 1 : "+value);

		a = -1;
		value = a >>> 1;
		System.out.println(a+" >>> 1 : "+value);
		
	}

}
