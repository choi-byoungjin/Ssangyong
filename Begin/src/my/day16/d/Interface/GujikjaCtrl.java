package my.day16.d.Interface;

import java.util.Scanner;

public class GujikjaCtrl extends Controller implements InterGujikjaCtrl {
	
	// == 구직자 메뉴를 보여주는 메소드 생성하기 == //
	@Override
	public void showMenu(Scanner sc, Member[] mbrArr) {
		
		String str_meunNo = "";
		Gujikja login_gu = null;
		String str_add = "";
		String str_login_logout = "";
		String str_menuno_3 = "";
		
		String str_menuno_4 = "";
		
		do {

			if(login_gu != null) { // 로그인 한 경우 
				str_add = "["+ login_gu.getName() +"님 로그인중..]";
				str_login_logout = "로그아웃";
				str_menuno_3 = "   3.내정보 보기";
				
				if( "admin".equals(login_gu.getId()) ) {
					// 관리자로 로그인 한 경우
					str_menuno_4 = "\n4.관리자전용";
				}
				
			}
			else { // 로그인을 하지 않은 경우 
				str_add = "";
				str_login_logout = "로그인";
				str_menuno_3 = "";
				str_menuno_4 = "";
			}
			
			System.out.println("\n======= *** 구직자 메뉴"+ str_add +" *** ======= \n"
	                + "1.구직자 회원가입   2."+ str_login_logout + str_menuno_3
	                + str_menuno_4 +"\n"); 

			System.out.print("▷ 메뉴번호 선택 => ");
			str_meunNo = sc.nextLine();
			
			switch (str_meunNo) {
				case "1":  // 구직자 회원가입 
					register(sc, mbrArr);
					break;
					
				case "2":  // 로그인 또는 로그아웃
					
					if("로그인".equals(str_login_logout)) { // 로그인 처리해주기 
					
						login_gu = (Gujikja) (super.login(sc, mbrArr));
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
				
					
				case "3": // 내정보 보기
					if(login_gu != null) { // 로그인 한 경우 
						System.out.println("\n===="+ login_gu.getName() +"님의 회원정보 ===="); 
						login_gu.showInfo();
					}
					
					else { // 로그인을 하지 않은 경우 
						System.out.println(">> [경고] 메뉴에 없는 번호 입니다.<<\n");
					}
					
					break;
					
				
				case "4": // 관리자전용
					if(login_gu != null && "admin".equals(login_gu.getId()) ) { 
						// 관리자로 로그인 한 경우 
						showAdminMenu(sc, mbrArr);
					}
					
					else { // 로그인을 하지 않거나 관리자가 아닌 일반구직자로 로그인한 경우 
						System.out.println(">> [경고] 메뉴에 없는 번호 입니다.<<\n");
					}
					
					break;	
	
				default:   // 메뉴에 없는 번호를 선택할 경우 
					System.out.println(">> [경고] 메뉴에 없는 번호 입니다.<<\n");
					break;
					
			}// end of switch (str_meunNo)-----------
			
		} while (!("2".equals(str_meunNo) && "로그아웃".equals(str_login_logout)));
		
		
	}// end of public void showMenu(Scanner sc)----------------------
	
	
	// == 구직자(Gujikja) 신규 회원가입을 해주는 메소드 생성하기 == //
	@Override
	public void register(Scanner sc, Member[] mbrArr) {
		
		if(Member.count < mbrArr.length) {
		
			System.out.println("\n>> === 구직자 신규회원 가입 === <<");
			
			String userid = null;
			for(;;) {
				System.out.print("1.아이디 : ");
				userid = sc.nextLine();
				
				boolean isDuplicate = false;
				
				for(int i=0; i<Member.count; i++) {
					String stored_userid = mbrArr[i].getId();
					if(stored_userid.equals(userid) ) {
						System.out.println(">> [경고] "+userid+" 아이디는 이미 사용중 입니다. 다른 아이디를 입력하세요!! << \n");
						isDuplicate = true;
						break;
					}
				}// end of for--------------------
				
				if(!isDuplicate)
					break;
				
			}// end of for--------------------------
			
			
			System.out.print("2.비밀번호 : ");
			String passwd = sc.nextLine();
			
			System.out.print("3.성명 : ");
			String name = sc.nextLine();
			
			System.out.print("4.주민번호7자리[예: 9510092] : ");
			String jubun = sc.nextLine();
			
			Gujikja gu = new Gujikja();
			gu.setId(userid);
			gu.setPasswd(passwd);
			gu.setName(name);
			gu.setJubun(jubun);
			
			if(gu.isUseGujikja()) {
				mbrArr[Member.count++] = gu;
				System.out.println(">> 회원가입 성공!! <<\n");
			}
			else {
				System.out.println(">> 회원가입 실패!! <<\n");
			}
		
		}
		
		else {
			System.out.println(">> [경고] 정원 "+mbrArr.length+"명이 다차서 더이상 구직자 신규가입이 불가합니다. <<\n"); 
		}
		
	}// end of private void register(Scanner sc, Gujikja[] guArr)---------
	
	
	// 로그인 처리해주는 메소드 생성하기 //
	/*
	private Gujikja login(Scanner sc, Member[] mbrArr) {
		
		System.out.println("\n==== 로그인 하기 ====");
		
		System.out.print("▷ 아이디: ");
		String userid = sc.nextLine();
		
		System.out.print("▷ 비밀번호: ");
		String passwd = sc.nextLine();
		
		Gujikja login_gu = null;
		
		for(int i=0; i<Member.count; i++) {
			String stored_userid = mbrArr[i].getId();
			String stored_passwd = mbrArr[i].getPasswd();
			
			if( stored_userid.equals(userid) && stored_passwd.equals(passwd) ) {
				login_gu = (Gujikja) mbrArr[i];
			}
			
		}// end of for------------------------
		
		return login_gu;
		
	}// end of private Gujikja login(Scanner sc)-------------------------
	*/
	
	
	// 관리자 전용 메뉴를 보여주는 메소드 생성하기 //
	@Override
	public void showAdminMenu(Scanner sc, Member[] mbrArr) {
		
		String str_menuNo = "";
		
		do {
			System.out.println("\n====== >> 관리자 전용 메뉴 << ======\n"
			         + "1.모든구직자 정보조회   2.연령대및성별 검색   3.구직자메뉴로 돌아가기");
			System.out.print("▷ 메뉴번호 선택 => ");
			
			str_menuNo = sc.nextLine();
			
			switch (str_menuNo) {
				case "1":
					showAllGujikja_info(mbrArr); 
					break;
		
				case "2":
					searchAgeLineGender(mbrArr, sc);
					break;	
					
				default:
					break;
			}// end of switch (str_menuNo)------------	
			
		} while (!("3".equals(str_menuNo)));
		
	}// end of private void showAdminMenu()------------------------------
	
	
	
	// 관리자를 제외한 모든 구직자 정보를 조회해주는 메소드 생성하기 //
	@Override
	public void showAllGujikja_info(Member[] mbrArr) {
		
		int count_gu = 0;
		
		for(int i=0; i<Member.count; i++) {
			
			if( mbrArr[i] instanceof Gujikja &&
				!"admin".equals(mbrArr[i].getId()) ) {
				count_gu++;
			}
			
		}// end of for----------------------
		
		if(count_gu == 0) {
		   // mbrArr 배열에 저장되어진 객체정보중 Gujikja 객체가 관리자(admin)만 저장된 경우
		   System.out.println(">> 등록되어진 구직자가 아무도 없습니다. << \n");	
		}
		
		else {
			// mbrArr 배열에 저장되어진 객체정보가 관리자(admin) 이외에 일반 구직자도 저장된 경우
			System.out.println("-----------------------------------------------------------");
			System.out.printf("%-10s\t%-15s\t%-8s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
			System.out.println("-----------------------------------------------------------");
			
			for(int i=0; i<Member.count; i++) {
				
				if( mbrArr[i] instanceof Gujikja &&
					!"admin".equals(mbrArr[i].getId()) ) {
					mbrArr[i].viewInfo();
				}
				
			}// end of for--------------------------------
		}	
		
	}// end of private void showAllGujikja_info(Gujikja[] guArr)--------------- 
	
	
	
	// 연령대및성별을 검색해주는 메소드 생성하기 //
	@Override
	public void searchAgeLineGender(Member[] mbrArr, Scanner sc) {
		
		int count_gu = 0;
		
		for(int i=0; i<Member.count; i++) {
			
			if( mbrArr[i] instanceof Gujikja &&
				!"admin".equals(mbrArr[i].getId()) ) {
				count_gu++;
			}
			
		}// end of for----------------------
		
		if(count_gu == 0) {
		   // mbrArr 배열에 저장되어진 객체정보중 Gujikja 객체가 관리자(admin)만 저장된 경우
		   System.out.println(">> 등록되어진 구직자가 아무도 없습니다. << \n");	
		}
				
		else {
			
			int ageline = 0;
			String gender = "";
			
			do {
				try {
					System.out.print("▷ 검색하고자 하는 연령대 => ");
					String str_ageline = sc.nextLine();
					
					ageline = Integer.parseInt(str_ageline);
					
					if( 0 <= ageline && ageline <= 100) 
						break;
					
					else 
						System.out.println(">> [경고] 올바른 연령대를 입력하세요!! <<\n");
					
				} catch(NumberFormatException e) {
					System.out.println(">> [경고] 연령대는 숫자만 입력하세요!! <<\n");
				}
				
			} while (true);
			// end of do~while---------------------------------
			
			
			do {
				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
				gender = sc.nextLine();
				
				if( !"남".equals(gender) && !"여".equals(gender) ) 
					System.out.println(">> [경고] 성별은 남 또는 여 만 입력하세요!! <<\n");
				
				else 
					break;
				
			} while(true);
			// end of do~while-----------------------------------
			
			StringBuilder sb = new StringBuilder();
			
			for(int i=0; i<Member.count; i++) {
				
				if( mbrArr[i] instanceof Gujikja && 
					!"admin".equals(mbrArr[i].getId()) ) {
					if( ((Gujikja) mbrArr[i]).getAge()/10*10 == ageline && 
						((Gujikja) mbrArr[i]).getGender().equals(gender) ) {
						sb.append(mbrArr[i].getInfo()+"\n");    
					}
				}
				
			}// end of for--------------------------------
			
			if(sb.length() > 0) {
			
				System.out.println("-----------------------------------------------------------");
				System.out.printf("%-10s\t%-15s\t%-8s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
				System.out.println("-----------------------------------------------------------");
			    System.out.println(sb.toString());
			}	
		
			else {
				System.out.println(">> 검색하신 연령대 "+ageline+"대인 "+gender+"자는 없습니다.<< \n");
			}
		
		}
	
	} // end of private void searchAgeLineGender(Gujikja[] guArr)---------------
	
	
}
