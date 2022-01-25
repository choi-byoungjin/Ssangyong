package jdbc.day03;

import java.util.Scanner;

public class MemberCtrl {

	// attribute, field, property, 속성
	InterMemberDAO mdao = new MemberDAO(); // 다형성 - 메소드가 구현되어진 부모 인터페이스를 사용해 된다.
	
	
	// operation, method, 기능
	
	// *** 시작메뉴를 보여주는 메소드 *** //
	public void menu_Start(Scanner sc) {
		
		String s_Choice = "";
		
		do {
			System.out.println("\n >>> ----- 시작메뉴 ----- <<< \n"
							 + "1.회원가입     2.로그인     3.프로그램종료\n"
							 + "------------------------------\n");	
			
			System.out.println("▷ 메뉴번호 선택 :");
			s_Choice = sc.nextLine();
			
			switch (s_Choice) {
				case "1": // 회원가입
					memberRegister(sc);
					break;

				case "2": // 로그인 or 로그아웃
					
					break;

				case "3": // 프로그램종료
					
					break;

					
				default:
					break;
			}// end of switch (key) --------------------------
			
		} while( !("3".equals(s_Choice)));
		
	}// end of public void menu_start(Scanner sc)-------------------------------------
	
	
	// *** 회원가입을 해주는 메소드 *** //
	private void memberRegister(Scanner sc) {
		
		System.out.println("\n >>> ---- 회원가입 ---- <<<");
		
		System.out.print("1. 아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("2. 비밀번호 : ");
		String passwd = sc.nextLine();
		
		System.out.print("3. 회원명 : ");
		String name = sc.nextLine();
		
		System.out.print("4. 연락처(휴대폰) : ");
		String mobile = sc.nextLine();
		
		MemberDTO member = new MemberDTO();
		member.setUserid(userid);
		member.setPasswd(passwd);
		member.setName(name);
		member.setMobile(mobile);
		
		int n = mdao.memberRegister(member); // 인터페이스에 있는 메소드가 DAO에서 재정의되어진 것이 작동한다.
		
		if(n==1) {
			System.out.println("\n >>> 회원가입을 축하드립니다. <<<");
		}
		
		
	}// end of private void memberRegister(Scanner sc)--------------------------------------- 
}


