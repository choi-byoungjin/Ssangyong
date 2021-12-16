package my.day07.c.quiz;

import java.util.Scanner;

import javax.net.ssl.HostnameVerifier;

public class QuizMain1 {
	
	/*      
	   === 홀수 및 짝수의 합 구하기 ===
	   
	   ▷ 첫번째 정수 입력하세요: 1
	   ▷ 두번째 정수 입력하세요: 10
	   
	   >>> 결과 <<<
	   1부터 10까지의 홀수의 합 : 25
	   1+3+5+7+9 = 25
	   
	   1부터 10까지의 짝수의 합 : 30
	   2+4+6+8+10 = 30
	   
	   === 홀수 및 짝수의 합 구하기 ===
	   
	   ▷ 첫번째 정수 입력하세요: 2
	   ▷ 두번째 정수 입력하세요: 9
	   
	   >>> 결과 <<<
	   2부터 9까지의 홀수의 합 : 24
	   3+5+7+9 = 24
	   
	   2부터 9까지의 짝수의 합 : 20
	   2+4+6+8 = 20
	*/
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=== 홀수 및 짝수의 합 구하기 === \n");
		
		while(true) {
			
			try {
				System.out.print("▷ 첫번재 정수 입력하세요 : ");
				String strFirstNo = sc.nextLine();
				int firstNo = Integer.parseInt(strFirstNo);
				
				System.out.print("▷ 두번재 정수 입력하세요 : ");
				String strSecondNo = sc.nextLine();
				int secondNo = Integer.parseInt(strSecondNo);
				
				int holSum = 0; // 홀수의 누적의 합계를 저장하는 변수
				int jjakSum = 0; // 짝수의 누적의 합계를 저장하는 변수
				
				int holsu_start_no = 0, jjaksu_start_no = 0;
				
				if(firstNo%2 == 0){ // 첫번째 입력받은 값이 짝수라면
									// 2
					holsu_start_no = firstNo+1; // 3
					jjaksu_start_no = firstNo;
				}
				
				else {	// 첫번째 입력받은 값이 홀수라면
						// 1
					holsu_start_no = firstNo;
					jjaksu_start_no = firstNo + 1;
				}
				
				// == 홀수 및 짝수의 합을 구한다. ==
				String str_holsu = "", str_jjaksu = "";
				
				while(true) {
					
					String str_add = ("".equals(str_holsu))?"":"+";
					
					if(holsu_start_no <= secondNo) {
						holSum += holsu_start_no;		// 숫자 덧셈
						str_holsu += str_add+holsu_start_no;	// 문자열결합
						holsu_start_no += 2;			// 숫자 덧셈
					}
					
					if(jjaksu_start_no <= secondNo) {
						jjakSum += jjaksu_start_no;
						str_jjaksu += str_add+jjaksu_start_no;
						jjaksu_start_no += 2;
					}
					if(holsu_start_no > secondNo && jjaksu_start_no > secondNo) {
						break;
					}
				}// end of while -----------------------------
				
				System.out.println("\n>>> 결과 <<<");
				System.out.println(strFirstNo+"부터"+strSecondNo+"까지의 홀수의 합 : "+holSum);
				System.out.println(str_holsu+"="+holSum);
				System.out.println("");
				System.out.println(strFirstNo+"부터"+strSecondNo+"까지의 짝수의 합 : "+jjakSum);
				System.out.println(str_jjaksu+"="+jjakSum);
				
				sc.close();
				break;
				
			} catch (NumberFormatException e){
				System.out.println(">> [경고] 정수만 입력하세요!! << \n");
			}
		}// end of while ---------------------------------------------
		
		System.out.println("\n === 프로그램 종료 ===");
		
	}// end of main(String[] args)----------------------------

}
