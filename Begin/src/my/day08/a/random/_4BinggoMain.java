package my.day08.a.random;

import java.util.Scanner;

public class _4BinggoMain {

	/*
	    PC가 만들수 있는 숫자는  1 ~ 100 까지중 아무거나 1개를 만든다.
	
	      예> 59 (PC가 랜덤하게 만든 숫자)
	         
	    1 부터 100 사이의 숫자입력 => 50엔터  1번시도
	    >> 50보다 큰값입니다.
	
	    1 부터 100 사이의 숫자입력 => 71엔터  2번시도
	    >> 71보다 적은값입니다. 
	
	    1 부터 100 사이의 숫자입력 => 60엔터  3번시도
	    >> 60보다 적은값입니다.
	
	    1 부터 100 사이의 숫자입력 => 58엔터  4번시도
	    >> 58보다 큰값입니다.
	
	    1 부터 100 사이의 숫자입력 => 59엔터  5번시도 
	    #### 빙고!!  5번만에 맞추었습니다. ####       
	*/  	 
	
	/*
	   위와같이 나오도록 하세요.
	   
	   1. 메일제목 : 과제3_숫자맞추기_김민정
	   2. 첨부파일 : 과제3_숫자맞추기_김민정.zip 파일로 제출
	               (_4BinggoMain.java)
	   3. 제출기한 : 2021.12.17 18:00 까지 제출
	   4. 보내는메일주소 : tjdudgkr0959@naver.com  
	*/   
	
	public static void main(String[] args) {
				
		Scanner sc = new Scanner(System.in);
		
		int pc_choice_no = (int)(Math.random()*(100-1+1))+1;
		//System.out.println(pc_choice_no);
		int count = 1;
		String result = "";
			do {
			
				try {
					System.out.print("1부터 100사이의 숫자입력 => ");
					String choice = sc.nextLine();
					int user_choice_no = Integer.parseInt(choice);
					
					if(user_choice_no > 100 || user_choice_no < 1) {
						System.out.println(">> [경고] 1 부터 100 사이의 값만 입력하세요 << \n");
					}
					else {
						
						if(pc_choice_no < user_choice_no) {
							result = ">> "+ user_choice_no + "보다 작은 값입니다.\n";
							++count;
						}
						else if(pc_choice_no > user_choice_no) {
							result = ">> " +user_choice_no + "보다 큰 값입니다.\n";
							++count;
						}
						else {
							System.out.println("#### 빙고!!  "+ count +"번만에 맞추었습니다. #### ");
							break;
						}
						System.out.println(result);
					}
				} catch(NumberFormatException e) {
					System.out.println(">> [경고] 정수만 입력하세요!! << \n");
					break;
				}
				
			} while (true);
			// end of do~while ------------------------------------------------
			
			sc.close();
			System.out.println(">> 프로그램 종료 <<");
	}

}
