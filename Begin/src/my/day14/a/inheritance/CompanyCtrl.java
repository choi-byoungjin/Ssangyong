package my.day14.a.inheritance;

import java.util.Scanner;

public class CompanyCtrl {
	
	// == 구인회사메뉴를 보여주는 메소드 생성하기 == //
	public void showMenu(Scanner sc, Company[] comArr) { // 메인에서 입력값과 배열을 넘겨주어야 한다.
		
		String str_login_logout = "";
		String str_menuNo = "";
		
		Company com = null;
		
		do {
			
			if(com == null)
				str_login_logout = "로그인";
			else
				str_login_logout = "로그아웃";
			
			System.out.println("\n====== *** 구인회사 메뉴 *** ====== \n"
	                + "1.구인회사 회원가입   2."+ str_login_logout);
	 
	    	System.out.print("▷ 메뉴번호 선택 => ");
	    	str_menuNo = sc.nextLine();
	    	
	    	switch (str_menuNo) {
				case "1":
					com = new Company();
					break;
	
				case "2":
					if(com == null)
						str_login_logout = "로그인";
					else {
						str_login_logout = "로그아웃";
						com = null;
					}
					break;

				default:
					System.out.println(">> [경고] 메뉴에 없는 번호 입니다. << \n");
					break;
			}// end of switch ----------------------------------------------------
	    	
	    	
		} while( !("2".equals(str_menuNo) && "로그아웃".equals(str_login_logout)));
		// end of switch--------------------------------------------------------
		
	}// end of showMenu(Scanner sc) ----------------------------------------------
}
