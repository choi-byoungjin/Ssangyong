package my.day05.e.For;

import java.util.Scanner;

public class Sum2_main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		
		for(;;) {
			
			try {
			
				System.out.print(">> 누적해야할 시작 숫자 => ");
				int startNo = Integer.parseInt(sc.nextLine());
				//  startNo ==> 5
								
				System.out.print(">> 누적해야할 마지막 숫자 => ");
				int endNo = Integer.parseInt(sc.nextLine());
				//  endNo  ==> 1
								
				if(endNo < startNo ) {
					System.out.println("== [경고] 마지막 숫자는 시작 숫자보다 같거나 커야 합니다. == \n");
					continue;
				}
				
				// startNo 		=> 1	2	3
				// endNo   		=> 10	9	5
				// 반복해야 할 횟수  => endNo - startNo + 1
				// 반복해야 할 횟수  => 10	8	3  번
				
				int cnt = endNo - startNo + 1;
				int sum = 0; // 누적의 합을 저장시켜주는 변수. 0으로 초기화 함.
				
				int start = startNo;	// 누적때문에 넘겨줘야함
				for (int i = 0; i < cnt; i++) {
					
					sum += start++;		// sum = sum + startNo
					
											// sum = 0+3;
											// sum = 0+3+4;
											// sum = 0+3+4+5;
				} // end of for ------------------------------------------------------------
				
				System.out.println(startNo+" 부터 "+endNo+" 까지의 누적의 합은 "+sum+" 입니다.");
				
				break;
				
			} catch(NumberFormatException e) {
				System.out.println("== [경고] 정수만 입력해주세요~~ == \n");
			}
			
		} // end of for ------------------------------------------------
		
		sc.close();
	}

}
