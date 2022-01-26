package jdbc.day03.board;

import java.util.*;

public class TotalController {

	// field, attribute, property, 속성
	InterMemberDAO mdao = new MemberDAO();
	
	
	// operation, method, 기능
	
	// *** 시작메뉴 메소드 생성하기 *** //
	public void menu_Start(Scanner sc) {
		
		MemberDTO member = null;
		String s_Choice = "";		
				
		do {
			String loginName = ""; // 로그아웃때문에 do-while문 안에서 초기화한다.
			String login_logout = "로그인";
			
			if(member != null) {
				loginName = "["+ member.getName() +" 님 로그인중..]";
				login_logout = "로그아웃";
			}
		
			System.out.println("\n >>> ----- 시작메뉴 "+ loginName +" ----- <<< \n"
							 + "1.회원가입     2."+ login_logout +"     3.프로그램종료 \n"
							 + "------------------------------\n");	
			
			System.out.print("▷ 메뉴번호 선택 :");
			s_Choice = sc.nextLine();
			
			switch (s_Choice) {
				case "1": // 회원가입
					memberRegister(sc);
					break;
				
				case "2":
					if("로그인".equals(login_logout)) {
						
						member = login(sc); // 로그인 처리하기
						
						if(member != null) { // 로그인이 성공한 경우
							menu_Board(member, sc); 	 // 게시판 메뉴에 들어간다. // 게시판 번호 sc
						}
					}
					
					else {
						// 로그아웃
						member = null;
						System.out.println(">> 로그아웃 되었습니다. << \n");
					}
					
					break; 
				
				case "3":
						// 프로그램종료
					break;	
				
				case "4":
					
					break;
				
				default:
					System.out.println(">>> 메뉴에 없는 번호입니다. 다시 선택하세요!! <<< \n");
					break;
			}
		
		} while (!("3".equals(s_Choice)));
	}// end of public void menu_Start(Scanner sc)-----------------------------------------------------
	
	
	// *** 회원가입을 처리해주는 메소드 생성하기 *** //
	private void memberRegister(Scanner sc) {

		System.out.println("\n >>> ---- 회원가입 ---- <<<");
		String userid = "";
		
		do {
			System.out.print("1. 아이디 : ");
			userid = sc.nextLine();
			
			boolean isUse = mdao.isUse_userid(userid); // 먼저 DB에 갔다와야함
			
			if( !isUse ) { // false 일 때
				System.out.println("\n >>> "+ userid +" 아이디가 이미 사용중이므로 다른 아이디를 입력하세요. <<< \n");
			}
			else {
				System.out.println("\n >>> "+ userid +" 는 아이디로 사용 가능합니다.. <<< \n");
				break;
			}
			
		} while(true);
		
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
		
		int n = mdao.memberRegister(member); // DAO에 보내준다(Interface)
		
		if(n==1) {
			System.out.println("\n >>> 회원가입 성공!! <<<");
		}
		
	}// end of private void memberRegister(Scanner sc)--------------------------------------------------



	// *** 로그인 처리하기 메소드 생성하기 *** //
	private MemberDTO login(Scanner sc) {
		
		MemberDTO member = null;
		
		System.out.println("\n >>> ---- 로그인 ---- <<<");
		
		System.out.print("▷ 아이디 : ");
		String userid = sc.nextLine();
		
		System.out.print("▷ 비밀번호 : ");
		String passwd = sc.nextLine();
		
		// where 절은 map 사용(Spring은 전부 map으로 한다.)
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("userid", userid);
		paraMap.put("passwd", passwd);
		
		member = mdao.login(paraMap);
		
		if(member != null) {
			System.out.println("\n >> 로그인 성공!! << \n");
		}
		else {
			System.out.println("\n >> 로그인 실패!! << \n");
		}
		
		return member;
	}	
	
	
	// *** 게시판 메뉴 메소드 생성하기 *** //
	private void menu_Board(MemberDTO member, Scanner sc) {
		
		String menuNo = "";
		
		do {
			System.out.println("---------------------- 게시판 메뉴[ "+ member.getName() +"님 로그인중..]-----------------------\n "
							 + " 1.글목록보기    2.글내용보기    3.글쓰기    4.댓글쓰기 \n"
							 + " 5.글수정하기    6.글삭제하기    7.최근 일주일간 일자별 게시글 작성건수 \n"
							 + " 8.이번달 일자별 게시글 작성건수	9.나가기 \n"
							 + "------------------------------------------------------------------------------------------");
			
			System.out.print("▷ 메뉴번호 선택 : ");
			menuNo = sc.nextLine();
			
		} while( !("9".equals(menuNo)) );
		
	}// end of private void menu_Board()------------------------------- // 이 메소드가 끝나면 메소드를 호출한 곳으로 간다. menu_Board(member, sc) 

}// end of private MemberDTO login(Scanner sc)--------------------------------------------------------