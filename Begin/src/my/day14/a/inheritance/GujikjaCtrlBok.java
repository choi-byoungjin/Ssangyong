package my.day14.a.inheritance;

import java.util.Scanner;

public class GujikjaCtrlBok {


	public void showMenu(Scanner sc, GujikjaBok[] guArr) {
		String str_menuNo = "";
		GujikjaBok login_gu = null;
		String str_add = "";
		String str_login_logout = "";
		String str_menuno_3 = "";
		String str_menuno_4 = "";
		int ageline = 0;
		String gender = "";
		
		do {
			if(login_gu != null) {
				str_add = "["+ login_gu.getName() +"님 로그인중..]";
				str_login_logout = "로그아웃";
				str_menuno_3 = "   3.내정보보기";
				
				if("admin".equals(login_gu.getId()))
					str_menuno_4 = "\n4.관리자전용";
			}
			else {
				str_add = "";
				str_login_logout = "로그인";
				str_menuno_3 = "";
				str_menuno_4 = "";
			}
			System.out.println("\n====== >> 메인메뉴 "+ str_add +"<< ====== \n"
					+ "1.구직자 회원가입   2."+ str_login_logout + str_menuno_3
					+ str_menuno_4
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
					if( login_gu != null) {
						System.out.println("\n==== "+ login_gu.getName() +"님의 회원정보 ====");
						login_gu.showInfo();
					}
					else
						System.out.println(">> [경고] 메뉴에 없는 번호입니다. << \n");
					break;
					
				case "4":
					if( login_gu != null && "admin".equals(login_gu.getId()))
						showAdminMenu(sc, guArr);
					else
						System.out.println(">> [경고] 메뉴에 없는 번호 입니다. << \n");
					break;
					
				case "10":
					
					break;
					
				default:
					System.out.println(">> [경고] 메뉴에 없는 번호 입니다. << \n");
					break;
			}
			
			
		} while ( !("10".equals(str_menuNo)) );
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
					String stored_userid = guArr[i].getId();
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
			gu.setId(userid);
			gu.setPasswd(passwd);
			gu.setName(name);
			gu.setJubun(jubun);
			
			if(gu.isUseGujikja()) {
				guArr[GujikjaBok.count++] = gu;
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
		System.out.print("▷ 아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("▷ 비밀번호 : ");
		String passwd = sc.nextLine();
		
		GujikjaBok login_gu = null;
		for (int i = 0; i < GujikjaBok.count; i++) {
			String stored_userid = guArr[i].getId();
			String stored_passwd = guArr[i].getPasswd();
			
			if(stored_userid.equals(userid) && stored_passwd.equals(passwd))
				login_gu = guArr[i];
		}
		return login_gu;
	}
	
	private void showAdminMenu(Scanner sc, GujikjaBok[] guArr) {
		
		String str_menuNo = "";
		do {
			System.out.println("\n====== >> 관리자 전용 메뉴 << ======\n"
							+  "1.모든구독자 정보조회   2.연령대 및 성별 검색   3.메인메뉴로 돌아가기");
			System.out.print("▷ 메뉴번호 선택 => ");
			str_menuNo = sc.nextLine();
			
			switch (str_menuNo) {
				case "1":
					showAllGujikja_info(guArr);
					break;
		
				case "2":
					searchAgeLineGender(sc, guArr);
					break;
				default:
					break;
				}
		} while (!("3".equals(str_menuNo)));
	}

	private void showAllGujikja_info(GujikjaBok[] guArr) {

		if(GujikjaBok.count == 1)
			System.out.println(">> 등록되어진 구직자가 아무도 없습니다. <<");
		else {
			System.out.println("-----------------------------------------------------------");
			System.out.printf("%-10s\t%-15s\t%-8s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
		    System.out.println("-----------------------------------------------------------");
		    for (int i = 0; i < GujikjaBok.count; i++) {
				if(!"admin".equals(guArr[i].getId()))
					guArr[i].viewInfo();
			}
		}	
	}
	
	private void searchAgeLineGender(Scanner sc, GujikjaBok[] guArr) {
		
		if(GujikjaBok.count == 1)
			System.out.println(">> 등록되어진 구직자가 아무도 없습니다. <<");
		else {
			int ageline = 0;
			String gender = "";
				do {
					try {
						System.out.print("▷ 검색하고자 하는 연령대 => ");
						String str_ageline = sc.nextLine();
						
						ageline = Integer.parseInt(str_ageline);
						
						if(0 <= ageline && ageline <= 100)
							break;
						else
							System.out.print(">> [경고] 연령대는 숫자만 입력하세요 << \n");
					} catch(NumberFormatException e) {
						System.out.println(">> [경고] 연령대는 숫자만 입력하세요 << \n");
					}
				} while (true);
				do {
					System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
					gender = sc.nextLine();
					
					if(!"남".equals(gender) && !"여".equals(gender))
						System.out.println(">> [경고] 성별은 남 또는 여 만 입력하세요 <<\n");
					else
						break;
				} while(true);
				
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < GujikjaBok.count; i++) {
					if( !"admin".equals(guArr[i].getId()) ) {
						if( guArr[i].getAge()/10*10 == ageline &&
							guArr[i].getGender().equals(gender)) {
							sb.append( guArr[i].getInfo() +"\n" );
						}
					}
				}
				
				if(sb.length() > 0) {
					System.out.println("-----------------------------------------------------------");
					System.out.printf("%-10s\t%-15s\t%-8s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
				    System.out.println("-----------------------------------------------------------");
				    System.out.println(sb.toString());
				}
				else 
					System.out.println("\n>> 검색하신 연령대 "+ ageline +"대인 "+ gender +"자는 없습니다. << \n");
		}
	}
}































