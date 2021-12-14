package my.day07.b.Dowile;

import java.util.Scanner;

public class _2FactorialMain {

// === 입사문제 === //
/*
 * >> 알고 싶은 팩토리얼 수 입력 => 5엔터
 * >> 5! = 120
 * 
 * 5! ==> 5*4*3*2*1
 * 7! ==> 7*6*5*4*3*2*1
 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		outer :	// outer는 레이블이다.
		do {
			
			try {
				System.out.print(">> 알고 싶은 팩토리얼 수 입력 => ");
				int num = Integer.parseInt(sc.nextLine());	// "5" 가능
													// "1.234"	"안녕"	불가함
													// "-7"		"0"		불가함
				if(num <= 0) {
					System.out.println(">> [경고] 자연수만 입력하세요! << \\n");
					continue;
					// continue; 를 만나면 아래로 떨어지지 않고 do~while(조건식); 의 조건식으로 들어간다.	// for문의 continue는 증감식
				}
				
				long result = 1;
				for (int i = num; i > 0; i--) {
					
					result *= i;	// result = result * i;
									// result = 1*5;
									// result = 1*5*4;
									// result = 1*5*4*3; 
									// result = 1*5*4*3*2;
									// result = 1*5*4*3*2*1;
				}// end of for -----------------------------------------
				
				System.out.println(num+"! = "+result);
				
				do {
					System.out.print(">> 또 할래?[Y/N] => ");
					String yn = sc.nextLine();
					
					if("Y".equalsIgnoreCase(yn)) {
						break;	// 가장 가까운 반복문을 빠져나간다.
					}
					
					else if("N".equalsIgnoreCase(yn)){
						break outer;	// 레이블 지정된 바깥 반복문까지 빠져나간다.
					}
					else {	
						System.out.println(">> [경고] Y 또는 N만 입력하세요!! \n");
					}
					
				} while (true);
				// end of do~while ---------------------------------------------
				
			} catch(NumberFormatException e) {
				System.out.println(">> [경고] 정수만 입력하세요!! << \n");
			}
			
		} while (true);
		// end of do~while -----------------------------------------------------
		
		sc.close();
		
		System.out.println("\n~~~~~~~~~~~~~ 프로그램 종료 ~~~~~~~~~~~~~~~~");
	}// end of main(String[] args) ---------------------------------------------

}