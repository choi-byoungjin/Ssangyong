package my.day17.d.collection;

import java.util.Scanner;

public class Controller implements InterController {
	
	
	// == 메뉴를 보여주는 메소드 생성하기 == //
	@Override
	public final void startMenu(Scanner sc, Member[] mbrArr) { 
		// 접근제한자 다음에 final을 붙이면 해당 메소드는 자식 클래스에서 override(재정의)가 불가하다는 말이다.
		
		GujikjaCtrl guCtrl = new GujikjaCtrl();
		CompanyCtrl comCtrl = new CompanyCtrl();
		
		String str_choice = "";
		
		do {
			System.out.println("\n==== >> 시작메뉴 << ====\n"
					         + "A.구직자 메뉴로 가기    B.구인회사 메뉴로 가기\n"
					         + "C.프로그램 종료");
			System.out.print("▷ 메뉴번호 선택 => ");
			
			str_choice = sc.nextLine();  // "A"  "a"
			
			switch (str_choice.toUpperCase()) { // "A"
				case "A":  // 구직자 메뉴로 가기
					guCtrl.showMenu(sc, mbrArr);
					break;
		
				case "B":  // 구인회사 메뉴로 가기
					comCtrl.showMenu(sc, mbrArr);
					break;
					
				case "C":  // 프로그램 종료
					
					break;	
					
				default:
					System.out.println(">> [경고] 메뉴에 없는 것을 선택하셨습니다. << \n");
					break;
			}// end of switch (str_choice)-------------
		
		} while( !("C".equals(str_choice.toUpperCase())) );
		
	}// end of public void startMenu(Scanner sc)----------------------------
	
	
	
	// 구직자 또는 구인회사로 로그인 처리를 해주는 메소드 생성하기 //
	@Override
	public final Member login(Scanner sc, Member[] mbrArr) {
		// 접근제한자 다음에 final을 붙이면 해당 메소드는 자식 클래스에서 override(재정의)가 불가하다는 말이다.
		
		System.out.println("\n==== 로그인 하기 ====");
		
		System.out.print("▷ 아이디: ");
		String id = sc.nextLine();
		
		System.out.print("▷ 비밀번호: ");
		String passwd = sc.nextLine();
		
		Member login_mbr = null;
		
		for(int i=0; i<Member.count; i++) {
			String stored_userid = mbrArr[i].getId();
			String stored_passwd = mbrArr[i].getPasswd();
			
			if( stored_userid.equals(id) && stored_passwd.equals(passwd) ) {
				login_mbr = mbrArr[i];
			}
			
		}// end of for------------------------
		
		return login_mbr;
		
	}// end of private Member login(Scanner sc, Member[] mbrArr)-------------------------	

	
}
