package my.day06.b.multiFor.gugudan;

import java.util.Scanner;

public class Gugudan_main1 {

	 /*
	    >> 몇단볼래? => 8엔터
	    
	    === 8단 ===
	    8*1=8
	    8*2=16 
	    8*3=24
	    8*4=32
	    8*5=40
	    8*6=48
	    8*7=56
	    8*8=64
	    8*9=72 
	    
	    >> 또 하시겠습니까?[Y/N] => y엔터 또는 Y엔터
	    
	    >> 몇단볼래? => 1.34엔터 또는 똘똘이엔터
	    >>> 2단부터 9단까지만 가능합니다 <<<
	    
	    >> 몇단볼래? => 345엔터
	    >>> 2단부터 9단까지만 가능합니다 <<<
	    
	    >> 몇단볼래? => 3엔터
	    
	    === 3단 ===
	    3*1=3
	    3*2=6 
	    3*3=9
	    3*4=12
	    3*5=15
	    3*6=18
	    3*7=21
	    3*8=24
	    3*9=27
	       
	    >> 또 하시겠습니까?[Y/N] => s엔터 또는 S엔터
	    >>> Y 또는 N 만 가능합니다!! <<<
	    
	    >> 또 하시겠습니까?[Y/N] => n엔터 또는 N엔터
	    
	    == 프로그램종료 ==   
	 */
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		/*
		 * === 레이블(라벨)을 사용하여 break; 하기 ===
		 *     레이블 뒤에는 : 을 붙이며 반드시 반복문 앞에 써야 한다.
		 */
		outer:	// outer: 을 레이블이라고 부르는데 그 레이블명이 지금은 outer이다. // 이름은 마음대로 짓는다.
		for(;;) {
			try {
				System.out.print(">> 몇 단 볼래? => ");
				String strDan = sc.nextLine();
				
				int dan = Integer.parseInt(strDan); // "1.34" 또는 "똘똘이"
													// "345"
				
				if( 2 <= dan && dan <= 9) {
					// 해당하는 단을 출력하기
					
					System.out.println("=== "+dan+"단 ===");
					for (int i = 0; i < 9; i++) {
						System.out.println(dan + "*" + (i+1) + "=" + (dan*(i+1)));
					}
					inner:
					for(;;) {
						System.out.print("\n >> 또 하시겠습니까?[Y/N] => ");
						String yn = sc.nextLine();
						
					//	if("N".equals(yn) || "n".endsWith(yn)) { // yn <== "n" 또는 "N"
					//	위와 같은 것이 아래이다.
						if("N".equalsIgnoreCase(yn)) { // yn <== "n" 또는 "N"
							sc.close();
							break outer; // 레이블명이 outer 라고 선언되어진 반복문을 빠져나가는 것이다.
						}
						
						else if("Y".equalsIgnoreCase(yn)) { // yn <== "y" 또는 "Y"
							break;	// break; 는 가장 가까운 반복문(for)을 빠져나오는 것이다.
						}
						
						else { // yn <== "y" 또는 "Y" 또는 "n" 또는 "N" 을 제외한 나머지 글자인 경우
							System.out.println(">>> [경고] Y 또는 N 만 가능합니다!! <<< \n");
						}
						
					} // end of for ---------------------------------------------------------
					
				}
				else {
					System.out.println(">>> [경고] 2단부터 9단까지만 가능합니다. <<< \n");
				}
				
			} catch(NumberFormatException e) {
				System.out.println(">>> [경고] 2단부터 9단까지만 가능합니다. <<< \n");
			}
		} // end of for --------------------------------------------------------
		
		System.out.println("\n== 프로그램종료 ==");

	} // end of main ----------------------------------------------------------
}
