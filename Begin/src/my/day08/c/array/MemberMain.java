package my.day08.c.array;

import java.util.Scanner;

public class MemberMain {

	public static void main(String[] args) {
	/*	
		Member mbr1 = new Member();
		Member mbr2 = new Member();
		Member mbr3 = new Member();
	*/	
		Member[] mbrArr = new Member[3];
	/*	
		for(int i=0; i<mbrArr.length; i++) {
			System.out.println(mbrArr[i]);
		}
	*/
		
		Scanner sc = new Scanner(System.in);
		int menuNo = 0;
		
		do {
		
			System.out.println("\n========= >> 메뉴 << =========== \n"
							+  "1.회원가입\t2.모든회원조회\t2.프로그램종료\n"
							+  "=================================");
			
			System.out.print("▷ 선택하세요 => ");
			
			try {
				menuNo = Integer.parseInt(sc.nextLine());
				// "똘똘이"	5	1	2	3
				
				if( !(1 <= menuNo && menuNo <= 3) ) {
					System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<\n");
				}
					
			} catch(NumberFormatException e){
				System.out.println(">> [경고] 정수만 입력하세요!! <<\n");
			}
			
		} while(!(menuNo == 3));
		// end of do~while -----------------------------------
		
		
		
	}// end of main(String[] args) ---------------------------

}
