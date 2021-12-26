package my.day14.a.inheritance;

import java.util.Scanner;

public class CompanyCtrlBok {

	public void showMenu(Scanner sc, CompanyBok[] comArr, GujikjaBok[] guArr) {
		CompanyBok login_com = null;
		String str_login_logout = "";
		String str_menuno_3 = "";
		String str_menuNo = "";
		String str_add = "";

		do {
			if(login_com != null) {
				str_add = "["+ login_com.getComname() +"님 로그인중..]";
				str_login_logout = "로그아웃";
				str_menuno_3 = "   3.내정보보기";
			}
			else {
				str_add = "";
				str_login_logout = "로그인";
				str_menuno_3 = "";
			}
			System.out.println("\n======= *** 구인회사 메뉴"+ str_add +" *** ======= \n"
			         + "1.구인회사 회원가입   2."+ str_login_logout + str_menuno_3   
			         + "\n4.관리자전용   5.시작메뉴로 가기\n"); 
			
			System.out.print("▷ 메뉴번호 선택 => ");
			str_menuNo = sc.nextLine();
			
			switch (str_menuNo) {
				case "1":
					register(sc, guArr);
					break;
	
				case "2":
					if("구인회사 로그인".equals(str_login_logout)) {
						login_com = login(sc, comArr);
						if(login_com != null)
							System.out.println(">> 로그인 성공!! <<\n");
						else
							System.out.println(">> 로그인 실패!! <<\n");
					}
					else
						login_com = null;
					break;
					
				case "3":
					if( login_com != null) {
						System.out.println("\n==== "+ login_com.getName() +"님의 회원정보 ====");
						login_com.showInfo();
					}
					else
						System.out.println(">> [경고] 메뉴에 없는 번호입니다. << \n");
					break;
					
				case "4":
					if( login_com != null && "admin".equals(login_com.getId()))
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

	private void register(Scanner sc, CompanyBok[] comArr) {
		if(CompanyBok.count < comArr.length) {
			System.out.println("\n>> === 구직자 신규회원 가입 === <<");
			
			String id = null;
			for(;;) {
				System.out.print("1.아이디");
				id = sc.nextLine();
				
				boolean isDuplicate = false;
				for (int i = 0; i < CompanyBok.count; i++) {
					String stored_userid = comArr[i].getId();
					if(stored_userid.equals(id)) {
						System.out.println(">> [경고] "+ id +"는 이미 사용중입니다. 다른 아이디를 입력하세요!! << \n");
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
			String comname = sc.nextLine();
			
			System.out.print("4.사업자등록번호 : ");
			String combunho= sc.nextLine();
			
			System.out.print("5.업종명 : ");
			String jobtype = sc.nextLine();
			
			long ln_seedmoney = 0;
			do {
				try {
					System.out.println("6.자본금:");
				}
			}
			CompanyBok gu = new CompanyBok();
			gu.setId(id);
			gu.setPasswd(passwd);
			gu.setName(comname);
			gu.setJubun(combunho);
			
			if(gu.isUseGujikja()) {
				comArr[CompanyBok.count++] = gu;
				System.out.println(">> 회원가입 성공!! <<\n");
			}
			else
				System.out.println(">> 회원가입 실패!! <<\n");
		}
		else
			System.out.println(">> [경고] 정원 "+ comArr.length +"명이 다차서 더이상 구직자 신규가입이 불가합니다. <<\n");
		
	}
	
	private GujikjaBok login(Scanner sc, GujikjaBok[] guArr) {
		System.out.println("\n==== 로그인 하기 ====");
		System.out.print("▷ 아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("▷ 비밀번호 : ");
		String passwd = sc.nextLine();
		
		GujikjaBok login_com = null;
		for (int i = 0; i < GujikjaBok.count; i++) {
			String stored_userid = guArr[i].getId();
			String stored_passwd = guArr[i].getPasswd();
			
			if(stored_userid.equals(userid) && stored_passwd.equals(passwd))
				login_com = guArr[i];
		}
		return login_com;
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
