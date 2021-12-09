package my.day04.a.scanner;

import java.util.Scanner;

public class Scanner5Main {

	public static void main(String[] args) {
		
		System.out.println("== 키보드로 부터 2개의 숫자를 입력받아서 사칙연산(+ - * /)한 결과 알아보기 == \n");
		
		String inputStr = "";
		
		Scanner sc = new Scanner(System.in);
		try {	
			System.out.println(">> 첫번째 정수 입력 : ");
			inputStr = sc.nextLine();	// "25"
										// "똘똘이"
			
			// "문자열" 을 int 타입으로 형변환 시켜주는 것
			int num1 = Integer.parseInt(inputStr);
			
			System.out.println(">> 두번째 정수 입력 : ");
			inputStr = sc.nextLine();	// "2"
										// "안녕"
			
			// "문자열" 을 int 타입으로 형변환 시켜주는 것
			int num2 = Integer.parseInt(inputStr);
			
			System.out.println(">> 사칙연산(+ - * /) 선택 : "); // "+"
															 // "-"
															 // "*"
															 // "/"
			
			String operator = sc.nextLine();
			
			double result = 0;
			boolean b_flag = false;
			
			switch (operator) {
			case "+":
				result = num1 + num2;
				break;
			
			case "-":
				result = num1 - num2;
				break;
				
			case "*":
				result = num1*num2;
				break;
				
			case "/":
				result = (double)num1/num2;
				break;
				
			default:
				b_flag = true;
				break;
			}// end of switch (operator)---------------------------------------------
			
			if(b_flag == false) {	// operator 가 "+" 또는 "-" 또는 "*" 또는 "/" 인 경우
				
				if(operator.equals("/")) {	// "문자열1" 과 "문자열2" 의 값을 같은지 비교할 때는 == 또는 != 를 쓰면 안된다.
											// "문자열1".equals("문자열2") 을 사용한다.
											// 만약에 "문자열1" 와 "문자열2" 의 값이 같다라면 "문자열1".equals("문자열2") 은 true 가 되고,
											// 만약에 "문자열1" 와 "문자열2" 의 값이 다르다면 "문자열1".equals("문자열2") 은 false 가 된다.
					System.out.println(">> 결과1 : "+num1+operator+num2+"="+result);
				}
				else {
					System.out.println(">> 결과2 : "+num1+operator+num2+"="+(int)result);
				}	
			}
			
			else {
				System.out.println(">>"+operator+" 사칙연산이 아니므로 계산이 불가합니다.!!");
			}
			
		} catch(NumberFormatException e) {
			System.out.println(">>"+inputStr+" 은(는) 정수가 아닙니다. 정수만 입력하세요!! <<");
		}
		sc.close();

	}// end of main(String[] args)-------------------------------------

}
