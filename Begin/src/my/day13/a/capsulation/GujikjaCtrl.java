package my.day13.a.capsulation;

import java.util.Scanner;

public class GujikjaCtrl {

	// == 메뉴를 보여주는 메소드 생성하기 == //
	public void showMenu(Scanner sc, Gujikja[] guArr) { // 메인에서 입력값과 배열을 넘겨주어야 한다.
		String str_menuNo = "";
		Gujikja login_gu = null;
		String str_add = "";
		String str_login_logout = "";
		String str_menuno_3 = "";
				
		do {
			
			if(login_gu != null) { // 로그인 한 경우
				str_add = "["+ login_gu.getName() +"님 로그인중..]";
				str_login_logout = "로그아웃";
				str_menuno_3 = "3.내정보보기";
			}
			else { // 로그인 하지 않은 경우
				str_add = "";
				str_login_logout = "로그인";
				str_menuno_3 = "";
			}
			System.out.println("\n====== >> 메인메뉴 "+ str_add +" << ====== \n"
	                + "1.구직자 회원가입   2."+str_login_logout+"\n"
	                + str_menuno_3
	                + "10.프로그램 종료 \n");
	 
	    	System.out.print("▷ 메뉴번호 선택 => ");
	    	str_menuNo = sc.nextLine();
	    	
	    	switch (str_menuNo) {
				case "1":  // 구직자 회원가입
					register(sc, guArr);
					break;
	
				case "2":  // 로그인
					
					if("로그인".equals(str_login_logout)) { // 로그인 처리해주기
						login_gu = login(sc, guArr);
						// 로그인이 성공되어지면 login_gu 이 null 이 아닌 값을 가진다.
						// 로그인이 실패되어지면 login_gu 이 null 값을 가진다.
						
						if(login_gu != null) {
							System.out.println(">> 로그인 성공!! <<\n");
						}
						else {
							System.out.println(">> 로그인 실패!! <<\n");
						}
					}
					else { // 로그아웃 처리해주기
						login_gu = null;
					}
					break;
						
				case "3":
					
					break;
				case "10": // 프로그램 종료
					
					break;
				
				default:  // 메뉴에 없는 번호를 선택할 경우
					System.out.println(">> [경고] 메뉴에 없는 번호 입니다. << \n");
					break;
					
			}// end of switch ----------------------------------------------------
	    	
		} while( !("10".equals(str_menuNo)) ); 
		
	}// end of showMenu(Scanner sc) ----------------------------------------------

	// == 구직자(Gujikja) 신규 회원가입을 해주는 메소드 생성하기 == //
	private void register(Scanner sc, Gujikja[] guArr) { // 데이터는 메인에서 만든다 // 보여주기만 하면 되므로 타입은 void // 같은 클래스에서 사용하므로 접근제한자는 private
		
		if(Gujikja.count < guArr.length) {
		
			System.out.println("\n>> === 구직자 신규회원 가입 === <<");
			
			String userid = null;
			for(;;) {
				System.out.print("1.아이디 : ");
				userid = sc.nextLine();
				
				boolean isDuplicate = false;
				
				for (int i = 0; i < Gujikja.count; i++) {
					String stored_userid = guArr[i].getUserid();
					if(stored_userid.equals(userid)) {
						System.out.println(">> [경고] "+ userid +"아이디는 이미 사용중입니다. 다른 아이디를 입력하세요!! << \n");
						isDuplicate = true;
						break;
					}
				}// end of for -------------------------------------------------
				if(!isDuplicate)
					break;
				
			}// end of for -------------------------------------------------
			
			System.out.print("2.비밀번호 : ");
			String passwd = sc.nextLine();
			
			System.out.print("3.성명 : ");
			String name= sc.nextLine();
			
			System.out.print("4.주민번호7자리[예 : 9510092] : ");
			String jubun= sc.nextLine();
			
			Gujikja gu = new Gujikja();
			gu.setUserid(userid);
			gu.setPasswd(passwd);
			gu.setName(name);
			gu.setJubun(jubun);
			
			if(gu.isUseGujikja()) {
				guArr[Gujikja.count++] = gu;
				System.out.println(">> 회원가입 성공!! <<\n");
			}
			else {
				System.out.println(">> 회원가입 실패!! <<\n");
			}
		}	
		else {
			System.out.println(">> [경고] 정원 "+ guArr.length +"명이 다차서 더이상 구직자 신규가입이 불가합니다. <<\n");
		}
	}// end of register--------------------------------------------------------
	
	
	private Gujikja login(Scanner sc, Gujikja[] guArr) {
		
		System.out.println("\n==== 로그인 하기 ====");
		System.out.print("▷ 아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("▷ 비밀번호 : ");
		String passwd = sc.nextLine();
		
		Gujikja login_gu = null;
		for(int i=0; i<Gujikja.count; i++) {
			String stored_userid = guArr[i].getUserid();
			String stored_passwd = guArr[i].getPasswd();
			
			if(stored_userid.equals(userid) && stored_passwd.equals(passwd)) {
				login_gu = guArr[i];
			}
			
		}// end of for-----------------------------------------------------------
		
		return login_gu;
		
	}// end of private Gujikja login(Scanner sc)--------------------------------
}
