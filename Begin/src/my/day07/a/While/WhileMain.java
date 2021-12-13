package my.day07.a.While;

/*
	=== while 문 형식 ===
	
	변수의 초기화;
	   
	while(조건식) {
	   조건식이 참(true)이라면 반복해서 실행할 명령문을 실행하고,
	   조건식이 거짓(false)이라면 while의 { } 이부분을 빠져나간다. 
	   
	   반복해서 실행할 명령문;
	   증감식;
	}
*/   

public class WhileMain {

	public static void main(String[] args) {
		
		int cnt=5, loop=0;
		while(loop < cnt) {	// 0<5(참)		1<5(참)		2<5(참)		3<5(참)		4<5(참)		5<5(거짓)
			System.out.println((loop+1)+".안녕 자바~~");
			loop++;
		}// end of while ---------------------------------------------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		cnt=5; loop=0;
		while(loop++ < cnt) {	// 0<5(참)		1<5(참)		2<5(참)		3<5(참)		4<5(참)		5<5(거짓)
			System.out.println(loop+".Hello Java~~");
		}// end of while ---------------------------------------------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		cnt=5; loop=0;
		while(loop < cnt) {	// 0<5(참)		1<5(참)		2<5(참)		3<5(참)		4<5(참)		5<5(거짓)
			System.out.println(++loop+".안녕 이클립스~~");
		}// end of while ---------------------------------------------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		loop=0;
		while(true) {
			System.out.println(++loop+".안녕 Eclipse~~");
			if(loop == 5)
				break;
		}// end of while ---------------------------------------------------------
		
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		loop=0;
		while(!(++loop > 5)) {	// if break와 같다
			//!( ) ==> 괄호 속에는 while 반복문을 빠져나갈 조건식의 정의를 내려주는 것이다.
			System.out.println(loop+".안녕 오라클~~");
		}// end of while ---------------------------------------------------------
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		loop=0;
		while(true) {
			
			if(++loop > 10) { // 탈출조건 
				break;
			}
			
			if(loop % 2 == 0) {
				continue;	// 아래로 내려가지 않고 while()의 괄호()속의 조건식으로 이동한다.
			}
			
			System.out.println(loop+".Hi Oracle~~");
		}
		
		/*
		 * 1.Hi Oracle~~
		 * 3.Hi Oracle~~
		 * 5.Hi Oracle~~
		 * 7.Hi Oracle~~
		 * 9.Hi Oracle~~
		 */
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.println("\n=== 5단 ===");
		
		loop=0;
		while(++loop < 10) {
			System.out.println("5*" + loop + "=" + (5*loop));
		}
		
		System.out.println("\n>> 프로그램 종료 <<");

	}// end of main ---------------------------------------------

}
