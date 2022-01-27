package jdbc.day03.board;

import java.util.*;

public class TotalController {

	// field, attribute, property, 속성
	InterMemberDAO mdao = new MemberDAO();
	InterBoardDAO bdao = new BoardDAO();
	
	
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
		
			System.out.println("\n>>> --------- 시작메뉴 "+ loginName +" --------- <<< \n"
							 + "1.회원가입     2."+ login_logout +"     3.프로그램종료 \n"
							 + "------------------------------\n");	
			
			System.out.print("▷ 메뉴번호 선택 :");
			s_Choice = sc.nextLine();
			
			switch (s_Choice) {
				case "1": // 회원가입
					member = memberRegister(sc);
					break;
				
				case "2":
					if("로그인".equals(login_logout)) {
						
						member = login(sc); // 로그인 처리하기
						
						if(member != null) { 			 // 로그인이 성공한 경우
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
	private MemberDTO memberRegister(Scanner sc) { 

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
			menu_Board(member, sc); // 게시판 메뉴에 들어간다.
			return member; // 리턴값 멤버 // 회원가입 하면 바로 게시판 메뉴에 들어간다.
		}
		else {
			return null; // 회원가입이 잘못되면 멤버를 넘겨주지 않는다.
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
		
		String adminOnly_menu = ("admin".equals(member.getUserid()))?"10.모든회원정보보기":"";
		
		System.out.println("확인용 : " + member.getUserid());
		
		do {
			System.out.println("---------------------- 게시판 메뉴[ "+ member.getName() +"님 로그인중..]-----------------------\n"
							 + " 1.글목록보기    2.글내용보기    3.글쓰기    4.댓글쓰기 \n"
							 + " 5.글수정하기    6.글삭제하기    7.최근 일주일간 일자별 게시글 작성건수 \n"
							 + " 8.이번달 일자별 게시글 작성건수	9.나가기    "+ adminOnly_menu +"\n"
							 + "------------------------------------------------------------------------------------------");
			
			System.out.print("▷ 메뉴번호 선택 : ");
			menuNo = sc.nextLine();
			
			switch (menuNo) {
				
				case "1": // 글목록보기
					boardList();
					break;
	
				
				case "2": // 글내용보기
					viewContents(sc, member.getUserid()); // 아이디를 파라미터로 받아온다 // 보여만 주면 되므로 void
					break;

				
				case "3": // 글쓰기
					
					break;
				
				
				case "4": // 댓글쓰기
					
					break;
				
				
				case "5": // 글수정하기
					
					break;
				
				
				case "6": // 글삭제하기
					
					break;
				
				
				case "7": // 최근 일주일간 일자별 게시글 작성건수
					
					break;
				
				
				case "8": // 이번달 일자별 게시글 작성건수
					
					break;
				
				
				case "9": // 나가기
					
					break;
				
				
				case "10": // 모든회원정보보기
					if("admin".equals(member.getUserid())) {
						
						System.out.println("[1:회원명의 오름차순 / 2:회원명의 내림차순 / \n"
										 + " 3:가입일자의 오름차순 / 4:가입일자의 내림차순]");
						System.out.println("▷ 정렬선택 : ");
						String sortChoice = sc.nextLine();
						
						// 1 또는 2 또는 3 또는 4 를 제외한 나머지가 sortChoice 에 입력되면 1 로 보겠다.
						if( !("1".equals(sortChoice) || "2".equals(sortChoice) ||
							  "3".equals(sortChoice) || "4".equals(sortChoice)) ) {
							sortChoice = "1";
						}
						
						selectAllMember(sortChoice);   
						// 관리자를 제외한 모든 회원들을 선택한 정렬기준으로 보여주는 메소드 호출하기
					}
					else {
						System.out.println(">> 메뉴에 없는 번호입니다. <<\n");
					}
					break;
					
				
				default:
					System.out.println(">> 메뉴에 없는 번호 입니다. << \n");
					break;
			}
			
		} while( !("9".equals(menuNo)) );
		
	}// end of private void menu_Board()------------------------------- // 이 메소드가 끝나면 메소드를 호출한 곳으로 간다. menu_Board(member, sc) 

	
	// *** 글내용보기 메소드 생성하기 *** //
	private void viewContents(Scanner sc, String login_userid) {
		
		System.out.println("\n>>> 글내용 보기 <<<");
		
		System.out.print("▷ 글번호 : ");
		String boardno = sc.nextLine();
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("boardno", boardno);
		paraMap.put("fk_userid", login_userid);
		
		BoardDTO board = bdao.viewContents(paraMap);
		
		if(board != null) {
			// 존재하는 글번호를 입력한 경우
			System.out.println("\n>> ==== [글내용 보기] ==== <<");
			System.out.println("▷ 글번호 : " + board.getBoardno() + "\n"
							 + "▷ 작성자 : " + board.getMember().getName() + "\n"
							 + "▷ 글제목 : " + board.getSubject() + "\n"
							 + "▷ 글내용 : " + board.getContents() + "\n"
							 + "▷ 작성일자 : " + board.getWriteday() + "\n"
							 + "▷ 글조회수 : " + board.getViewcount() + "\n");
		}
		
	}// end of private void viewContents(Scanner sc, String userid)
	

	// *** 글목록보기 메소드 생성하기 *** //
	private void boardList() {
		
		List<BoardDTO> boardList = bdao.boardList();
		
		if( boardList.size() > 0) {
			// 게시글이 존재하는 경우
			System.out.println("\n-----------------------------------[게시글 목록]---------------------------------------");
			System.out.println("글번호\t글제목\t\t작성자\t작성일자\t조회수");
			System.out.println("--------------------------------------------------------------------------------------");
			
			StringBuilder sb = new StringBuilder();
			
			for( BoardDTO board : boardList ) {
				sb.append( board.getBoardno()+"\t"+
						   board.getSubject()+"\t"+
						   board.getMember().getName()+"\t"+
						   board.getWriteday()+"\t"+
						   board.getViewcount()+"\n"
						 );
			}// end of for-----------------------------------------------------------
			
			System.out.println(sb.toString());
		}
				
		else {
			// 게시글이 1개도 존재하지 않을 경우
			System.out.println(">> 글목록이 없습니다. \n <<");
		}
	}// end of private void boardList()-----------------------------


	// *** 관리자를 제외한 모든 회원들을 선택한 정렬기준으로 보여주는 메소드 생성하기 *** //
	private void selectAllMember(String sortChoice) {

		System.out.println("\n>>> ============ 모든 회원정보 ============ <<<");
		System.out.println("-----------------------------------------------");
		System.out.println("회원가입   아이디   성명   연락처   포인트   가입일자   탈퇴유무");
		System.out.println("-----------------------------------------------");
		
		List<MemberDTO> memberList = mdao.selectAllMember(sortChoice);
		
		int memberCount = memberList.size(); // "admin" 을 제외한 회원수
		
		if(memberCount > 0) {
			
			StringBuilder sb = new StringBuilder();
			
			for(MemberDTO member : memberList) {
			
				String str_status = ( member.getStatus() == 1 )?"가입중":"탈퇴함";
				
				sb.append( member.getUserseq() + "   " +
						   member.getUserid() + "   " +
						   member.getName() + "   " +
						   member.getMobile() + "   " +
						   member.getPoint() + "   " +
						   member.getRegisterday() + "   " +
						   str_status + "\n");
				
			}// end of for --------------------------------
			
			System.out.println(sb);
		}
		else {
			System.out.println("가입된 회원이 없습니다.");
		}
		
	}// end of private void selectAllMember(String sortChoice) ----------------------

}// end of private MemberDTO login(Scanner sc)--------------------------------------------------------