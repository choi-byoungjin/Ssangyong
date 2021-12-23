package my.day13.a.capsulation;

import java.util.Scanner;

public class GujikjaCtrlBok {

	public void showMenu(Scanner sc, GujikjaBok[] guArr) {
		String str_menuNo = "";
		GujikjaBok login_gu = null;
		String str_add = "";
		String str_login_logout = "";
		do {
			if(login_gu != null) {
				str_add = "["+ login_gu.getName() +"님 로그인중..]";
				str_login_logout = "로그아웃";
			}
			else {
				str_add = "";
				str_login_logout = "로그인";
			}
			System.out.println("\n====== >> 메인메뉴 << ====== \n"
					+ "1.구직자 회원가입   2."+ str_login_logout
					+ "4.관리자전용"
					+ "\n10.프로그램 종료 \n");
			
			System.out.print("▷ 메뉴번호 선택 => ");
			str_menuNo = sc.nextLine();
			
			switch (str_menuNo) {
				case "1":
					register(sc, guArr);
					break;
	
				case "2":
					if("로그인".equals(str_login_logout)) {
						login_gu = login(sc, guArr);
						if(login_gu != null)
							System.out.println(">> 로그인 성공!! <<\n");
						else
							System.out.println(">> 로그인 실패!! <<\n");
					}
					else
						login_gu = null;
					break;
					
				case "3":
					
					break;
					
				case "4":
					
					break;
					
				case "10":
					
					break;
					
				default:
					break;
			}
			
			
		} while (true);
	}

	private void register(Scanner sc, GujikjaBok[] guArr) {
		if(GujikjaBok.count < guArr.length) {
			System.out.println("\n>> === 구직자 신규회원 가입 === <<");
			
			String userid = null;
			for(;;) {
				System.out.print("1.아이디");
				userid = sc.nextLine();
				
				boolean isDuplicate = false;
				for (int i = 0; i < GujikjaBok.count; i++) {
					String stored_userid = guArr[i].getUserid();
					if(stored_userid.equals(userid)) {
						System.out.println(">> [경고] 아이디는 이미 사용중입니다. 다른 아이디를 입력하세요!! << \n");
						isDuplicate = true;
						break;
					}
				}
				if(!isDuplicate)
					break;
			}
			
			System.out.print("2.비밀번호 : ");
			String passwd = sc.nextLine();
			
			System.out.print("3.성명 : ");
			String name = sc.nextLine();
			
			System.out.print("4.주민번호7자리[예 : 9510092] : ");
			String jubun = sc.nextLine();
			
			GujikjaBok gu = new GujikjaBok();
			gu.setUserid(userid);
			gu.setPasswd(passwd);
			gu.setName(name);
			gu.setJubun(jubun);
			
			if(gu.isUseGujikja()) {
				guArr[Gujikja.count++] = gu;
				System.out.println(">> 회원가입 성공!! <<\n");
			}
			else
				System.out.println(">> 회원가입 실패!! <<\n");
		}
		else
			System.out.println(">> [경고] 정원 "+ guArr.length +"명이 다차서 더이상 구직자 신규가입이 불가합니다. <<\n");
		
	}
	
	private GujikjaBok login(Scanner sc, GujikjaBok[] guArr) {
		System.out.println("\n==== 로그인 하기 ====");
		System.out.println("");
		return null;
	}
}
