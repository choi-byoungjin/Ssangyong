package my.day04.c.wrapper;

import java.util.Scanner;

/*
=== *** wrapper 클래스 *** ===

------------------------------------------------
  기본자료형(원시형, primitive)     wrapper 클래스
------------------------------------------------
 byte                           Byte 
 short                          Short
 int                            Integer  
 long                           Long
 char                           Character
 float                          Float
 double                         Double
 boolean                        Boolean 
 ------------------------------------------------
 
  기본자료형(원시형)은 데이터 저장 및 4칙연산 에서만 사용하는 것이고, 
 wrapper 클래스는  데이터 저장 및 4칙연산 뿐만아니라 
  아주 다양한 기능의 메소드가 제공되므로 다방면으로 사용되어진다. 
*/
public class WrapperMain {

	public static void main(String[] args) {
		
		System.out.println("=== auto Boxing 과 auto UnBoxing 에 대해서 알아 봅니다. ===");
		// Boxing(박싱, 포장을 하는것) 이란 ?
		// ==> 기본자료형(boolean, byte, short, int, long, char, float, double)으로 되어진 변수를 
        //     객체타입인 Wrapper 클래스(Boolean, Byte, Short, Integer, Long, Character, Float, Double)   
        //     타입의 객체로 만들어주는 것을 말한다.
		
		// UnBoxing(언박싱, 포장을 푸는것) 이란?   
	    // ==> Wrapper 클래스(Boolean, Byte, Short, Integer, Long, Character, Float, Double)로 
	    //     되어져 있는 객체를 기본자료형(boolean, byte, short, int, long, char, float, double)으로 
	    //     만들어주는 것을 말한다.
		
		int a1 = 10;
		Integer a2 = new Integer(a1);	// Boxing(박싱)
		// deprecated (조만간 더 이상 사용되지 않는다.)
		
		System.out.println("a2 => " + a2.intValue()); // a2.intValue() 이 UnBoxing(언박싱)이다.
		// a2 => 10
		
		int a3 = 20;
		Integer a4 = a3;	// auto Boxing(언박싱)
		System.out.println("a4 => " + a4);	// auto UnBoxing(언박싱)
		// a4 => 20
		
		
		double db1 = 1.234567;
		Double db2 = new Double(db1);	// Boxing(박싱)
		// deprecated (조만간 더 이상 사용되지 않는다.)
		
		System.out.println("db2 => " + db2.doubleValue()); // db2.doubleValue() 이 UnBoxing(언박싱)이다.
		// db2 => 1.234567
		
		double db3 = 1.234567;
		Double db4 = db3;	// auto Boxing(언박싱)
		System.out.println("db4 => " + db4);	// auto UnBoxing(언박싱)
		// db4 => 1.234567
		
		
		Character ch = new Character('a');	// Boxing(박싱)
		// deprecated (조만간 더 이상 사용되지 않는다.)
		
		Character chr = 'A';	// auto Boxing(박싱)
		System.out.println("chr => " + chr);
		// chr => A
		
		char ch2 = (char)(ch + 32);	// auto UnBoxing(언박싱)
		System.out.println("ch2 => " + ch2);
		//chr2 => a
		
		char ch3 =  Character.toUpperCase('b');		// 'B'
		System.out.println("ch3 => " + ch3);
		// ch3 => B
		
		char ch4 =  Character.toLowerCase('T');		// 't'
		System.out.println("ch4 => " + ch4);
		// ch4 => t
		
		System.out.println(Character.toUpperCase('a' + 1));
		// 66
		
		System.out.println(Character.toLowerCase('A' + 1));
		// 98
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print(">> 아무글자 1개를 입력하세요 => ");		// 아스키코드 사용
		
		String inputStr = sc.nextLine(); // 		 "abcdefg"
										 //  index => 0123456
		
	//	char ch_1 = inputStr.charAt(3);	 // 'd'
		char ch_1 = inputStr.charAt(0);	 // 'a'
		
	//	System.out.println(ch_1);	// "a"
	
	// char 타입은 비교연산자( ==  !=  >  <  >=  <= )를 만나면 자동적으로 int 타입으로 형변환 되어진다. 
		
		String result = "";
				
		if( 65 <= ch_1 && ch_1 <=90 )	// 대문자인지 알아본다.
			result = "대문자";
		
		else if( 97 <= ch_1 && ch_1 <= 122 )	//소문자인지 알아본다.
			result = "소문자";
		
		else if( 48 <= ch_1 && ch_1 <=57 )
			result = "숫자";
		
		else
			result = "특수문자";
			
		System.out.println(ch_1+"는 "+result+" 입니다.");
		// a는 소문자 입니다.
		
		
		System.out.print(">> 또 아무글자 1개를 입력하세요 => ");	// Wrapper 클래스 사용
		
		inputStr = sc.nextLine(); 
		ch_1 = inputStr.charAt(0);
		
		result = "";
		
		if( Character.isUpperCase(ch_1) )	// 대문자인지 알아본다.
			result = "대문자";
		
		else if( Character.isLowerCase(ch_1) )	//소문자인지 알아본다.
			result = "소문자";
		
		else if( Character.isDigit(ch_1) )
			result = "숫자";
		
		else
			result = "특수문자";
			
		System.out.println(ch_1+"는 "+result+" 입니다.");
		
		
		sc.close();
	}

}
