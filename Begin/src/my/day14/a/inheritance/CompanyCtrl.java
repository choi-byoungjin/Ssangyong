package my.day14.a.inheritance;

import java.util.Scanner;

public class CompanyCtrl {

	// == 구인회사 메뉴를 보여주는 메소드 생성하기 == //
	public void showMenu(Scanner sc, Company[] comArr, Gujikja[] guArr) {

		Company login_com = null;
		String str_login_logout = "";
		String str_menuno_3 = "";
		String str_menuNo = "";
		String str_add = "";
		
		do {
			
			if(login_com != null) { // 로그인 한 경우 
				str_add = "["+ login_com.getComname() +"님 로그인중..]";
				str_login_logout = "구인회사 로그아웃";
				str_menuno_3 = "   3.구인회사 내정보 보기";
			}
			else { // 로그인을 하지 않은 경우 
				str_add = "";
				str_login_logout = "구인회사 로그인";
				str_menuno_3 = "";
			}
			
			System.out.println("\n======= *** 구인회사 메뉴"+ str_add +" *** ======= \n"
					         + "1.구인회사 회원가입   2."+ str_login_logout + str_menuno_3   
					         + "\n4.관리자전용   5.시작메뉴로 가기\n"); 
	
			System.out.print("▷ 메뉴번호 선택 => ");
			str_menuNo = sc.nextLine();
			
			switch (str_menuNo) {
				case "1":
					register(sc, comArr);
					break;
					
				case "2":
					if("구인회사 로그인".equals(str_login_logout)) { // 로그인 처리해주기 
						
						login_com = login(sc, comArr);
						// 로그인이 성공되어지면 login_com 이 null 이 아닌 값을 가진다.
						// 로그인이 실패되어지면 login_com 이 null 값을 가진다.
						
						if(login_com != null) {
							System.out.println(">> 구인회사 로그인 성공!! <<\n");
						}
						else {
							System.out.println(">> 구인회사 로그인 실패!! <<\n");
						}
					}
					
					else { // 로그아웃 처리해주기
						login_com = null;
					}
					
					break;					
	
					
				case "3": // 구인회사 내정보 보기
					if(login_com != null) { // 로그인 한 경우 
						System.out.println("\n===="+ login_com.getComname() +"님의 회원정보 ===="); 
						login_com.showInfo();
					}
					
					else { // 로그인을 하지 않은 경우 
						System.out.println(">> [경고] 메뉴에 없는 번호 입니다.<<\n");
					}
					
					break;					
					
					
				case "4": // 관리자전용	
					
					showAdminMenu(sc, comArr, guArr);
					
					break;
					
				
				case "5": // 시작메뉴로 가기	
		
					break;	
					
					
				default:
					System.out.println(">> [경고] 메뉴에 없는 번호 입니다.<<\n");
					break;
					
			}// end of switch (str_menuNo)--------------
			
			
		} while( !( ("2".equals(str_menuNo) && "구인회사 로그아웃".equals(str_login_logout))  || "5".equals(str_menuNo) ) );
        // end of do~while----------------------------------
		
	}// end of public void showMenu(Scanner sc)----------------------	
	
	
	// == 구인회사(Company) 신규 회원가입을 해주는 메소드 생성하기 == //
	private void register(Scanner sc, Company[] comArr) {
		
		if(Company.count < comArr.length) {
		
			System.out.println("\n>> === 구인회사 신규회원 가입 === <<");
			
			String id = null;
			for(;;) {
				System.out.print("1.아이디 : ");
				id = sc.nextLine();
				
				boolean isDuplicate = false;
				
				for(int i=0; i<Company.count; i++) {
					String stored_id = comArr[i].getId();
					if(stored_id.equals(id) ) {
						System.out.println(">> [경고] "+id+" 아이디는 이미 사용중 입니다. 다른 아이디를 입력하세요!! << \n");
						isDuplicate = true;
						break;
					}
				}// end of for--------------------
				
				if(!isDuplicate)
					break;
				
			}// end of for--------------------------
			
			
			System.out.print("2.비밀번호 : ");
			String passwd = sc.nextLine();
			
			System.out.print("3.회사명 : ");
			String comname = sc.nextLine();
			
			System.out.print("4.사업자등록번호[예: 12-345] : ");
			String combunho = sc.nextLine();
			
			System.out.print("5.업종명 : ");
			String jobtype = sc.nextLine();
			
			long ln_seedmoney = 0;
			do {
				try {
					System.out.print("6.자본금 : ");
					String seedmoney = sc.nextLine();
					ln_seedmoney = Long.parseLong(seedmoney);
					break;
				} catch(NumberFormatException e) {
					System.out.println(">> [경고] 자본금은 숫자로만 입력하셔야 합니다!! << \n");
				}
			} while(true);
			
			Company comp = new Company();
			comp.setId(id);
			comp.setPasswd(passwd);
			comp.setComname(comname);
			comp.setCombunho(combunho);
			comp.setJobtype(jobtype);
			comp.setSeedmoney(ln_seedmoney);
			
			if(comp.isUseCompany()) {
				comArr[Company.count++] = comp;
				System.out.println(">> 구인회사 회원가입 성공!! <<\n");
			}
			else {
				System.out.println(">> 구인회사 회원가입 실패!! <<\n");
			}
		
		}
		
		else {
			System.out.println(">> [경고] 구인회사 정원 "+comArr.length+"개가 다차서 더이상 구인회사 신규가입이 불가합니다. <<\n"); 
		}
		
	}// end of private void register(Scanner sc, Company[] comArr)---------
	
	
	// 로그인 처리해주는 메소드 생성하기 //
	private Company login(Scanner sc, Company[] comArr) {
		
		System.out.println("\n==== 로그인 하기 ====");
		
		System.out.print("▷ 아이디: ");
		String id = sc.nextLine();
		
		System.out.print("▷ 비밀번호: ");
		String passwd = sc.nextLine();
		
		Company login_com = null;
		
		for(int i=0; i<Company.count; i++) {
			String stored_userid = comArr[i].getId();
			String stored_passwd = comArr[i].getPasswd();
			
			if( stored_userid.equals(id) && stored_passwd.equals(passwd) ) {
				login_com = comArr[i];
			}
			
		}// end of for------------------------
		
		return login_com;
		
	}// end of private Company login(Scanner sc)-------------------------	
	
	
	// 관리자전용 메소드 생성하기 //
	private void showAdminMenu(Scanner sc, Company[] comArr, Gujikja[] guArr) {
		
		System.out.println("\n=== *** 관리자 인증받기 *** ===");
		
		System.out.print("◇ 관리자 ID : ");
		String id = sc.nextLine();
		
		System.out.print("◇ 비밀번호 : ");
		String passwd = sc.nextLine();
		
		boolean isAdminOK = false;
		
		for(int i=0; i<Gujikja.count; i++) {
			
			String stored_id = guArr[i].getId();
			String stored_passwd = guArr[i].getPasswd();
			
			if( (stored_id.equals(id) && stored_passwd.equals(passwd)) ) {
				// 관리자의 ID 및 비밀번호가 올바른 경우
				isAdminOK = true;
				break;
			}
			
		}// end of for------------------------------
		
		
		if(!isAdminOK) {
			// 관리자의 ID 및 비밀번호가 틀린 경우
			System.out.println(">> [관리자 인증 실패!!] <<\n");
		}
		
		else {
			// 관리자의 ID 및 비밀번호가 올바른 경우
			// 관리자 전용 메뉴를 보여준다.
			
			String str_menuNo = "";
			
			do {
				System.out.println("\n====== >> 관리자 전용 메뉴 << ======\n"
				         + "1.모든 구인회사 정보조회   2.업종별 검색   3.구인회사 메뉴로 돌아가기");
				System.out.print("▷ 메뉴번호 선택 => ");
				
				str_menuNo = sc.nextLine();
				
				switch (str_menuNo) {
					case "1":
						showAllCompany_info(comArr); 
						break;
			
					case "2":
						searchJobtype(comArr, sc);
						break;	
						
					default:
						break;
				}// end of switch (str_menuNo)------------	
				
			} while (!("3".equals(str_menuNo)));
			
		}// end of if~else-------------------------------------
		
	}// end of private void showAdminMenu(Scanner sc, Company[] comArr, Gujikja[] guArr)-------
	
	
	// 모든 구인회사 정보조회 해주는 메소드 생성하기 //
	private void showAllCompany_info(Company[] comArr) {
		
		if(comArr.length == 0) {
		   // comArr 배열에 저장되어진 객체정보가 없을 경우
		   System.out.println(">> 등록되어진 구인회사가 아무도 없습니다. << \n");	
		}
		
		else {
			// guArr 배열에 저장되어진 객체정보가 존재하는 경우
			System.out.println("----------------------------------------------------------------------------------------");
			System.out.printf("%-10s\t%-15s\t%-10s\t%-8s\t%-10s\t%-10s\n","아이디","암호","회사명","사업자등록번호","업종","자본금");
			System.out.println("----------------------------------------------------------------------------------------");
			
			for(int i=0; i<Company.count; i++) {
				comArr[i].viewInfo();
			}// end of for--------------------------------
		}		
		
	}// end of private void showAllCompany_info(Company[] comArr)----------------
	
	
	// 구인회사 업종별 검색 해주는 메소드 생성하기 //
	private void searchJobtype(Company[] comArr, Scanner sc) {
		
		System.out.print("▷ 검색하실 업종명 => ");
		
		String jobtype = sc.nextLine();
		
		StringBuilder sb = new StringBuilder();
		
		if(!jobtype.trim().isEmpty()) {
		
			for(int i=0; i<Company.count; i++) {
				
				if(comArr[i].getJobtype().toLowerCase().indexOf(jobtype.toLowerCase()) != -1) {
					sb.append(comArr[i].getInfo()+"\n");  
				}
				
			}// end of for--------------------------
		}
		
		if(sb.length() > 0) {
			System.out.println("----------------------------------------------------------------------------");
			System.out.printf("%-10s\t%-15s\t%-10s\t%-8s\t%-10s\t%-10s\n","아이디","암호","회사명","사업자등록번호","업종","자본금");
			System.out.println("----------------------------------------------------------------------------");
		    System.out.println(sb.toString());
		}	
	
		else {
			System.out.println(">> 검색하신 업종 "+jobtype+"는(은) 없습니다.<< \n");
		}
		
	}// end of private void searchJobtype(Company[] comArr, Scanner sc)-----------
	
	
	
}
