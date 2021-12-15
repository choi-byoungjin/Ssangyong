package my.day08.c.array;

import java.util.Scanner;

import my.util.MyUtil;

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
							+  "1.회원가입	 2.모든회원조회 3.프로그램종료\n"
							+  "=================================");
			
			System.out.print("▷ 선택하세요 => ");
			
			try {
				menuNo = Integer.parseInt(sc.nextLine());
				// "똘똘이"	5	1	2	3
				
				if( !(1 <= menuNo && menuNo <= 3) ) {
					System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<\n");
					continue;
				}
				
				switch (menuNo) {
					case 1:	// 회원가입
						
						Member mbr = new Member(); // 기본생성자
						
						for(;;) {
							System.out.print("\n1.아이디 : ");
							String id = sc.nextLine();

							if(id.length() == 0) {
								System.out.println(">> [경고] 아이디는 필수입력 사항입니다. <<\n");
							}
							else {
								mbr.id = id;
								break;
							}
						}// end of for ----------------------------------------

						for(;;) {
							System.out.print("2.비밀번호 : ");
							String passwd = sc.nextLine();

							if(!(MyUtil.isCheckPasswd(passwd))) {
								System.out.println(">> [경고] 비밀번호는 8글자 이상 15글자 이하에 대, 소문자, 숫자, 특수문자가 혼합되어져야만 합니다. <<\n");
							}
							else {
								mbr.passwd = passwd;
								break;
							}
						}// end of for ----------------------------------------
						
						for(;;) {
							System.out.print("3.성명 : ");
							String name = sc.nextLine();
							
							if(name.length() == 0) {
								System.out.println(">> [경고] 성명은 필수입력 사항입니다. <<\n");
							}
							else {
								mbr.name = name;
								break;
							}
						}// end of for ----------------------------------------
						
						for (int i = 0; i < mbrArr.length; i++) {
							if(mbrArr[i] == null) {
								mbrArr[i] = mbr;
								break;
							}
						}// end of --------------------------------------------
						/*
						 * mbrArr[0] <== "이순신" 객체
						 * mbrArr[1] <== "엄정화" 객체
						 * mbrArr[2] <== 두명만 넣으면 null로 되어있음
						 */
						System.out.println(">> 회원가입 성공!! ["+(++Member.count)+" 번째 가입/정원 :"+mbrArr.length+" 명]<< \n");
						break; // switch의 break; 이다.
						
					case 2:	// 모든회원조회
						
						for (int i = 0; i < mbrArr.length; i++) {
							System.out.println(mbrArr[i].showInfo()+ "\n");	// 멤버 객체 // showInfo 리턴타입 String
						}// end of for ----------------------------------------
						
						break;
				}// end of switch (menuNo) ------------------------------------
					
			} catch(NumberFormatException e){
				System.out.println(">> [경고] 정수만 입력하세요!! <<\n");
			}
			
		} while(!(menuNo == 3));
		// end of do~while -----------------------------------
		// 3일 때는 프로그램 종료로 이동
		
		sc.close();
		System.out.println("\n >> 프로그램 종료 <<");
		
	}// end of main(String[] args) ---------------------------

}
