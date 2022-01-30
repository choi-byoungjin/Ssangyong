package my.day0809.c.array;

import java.util.Scanner;

import my.util.MyUtil;

public class BokMemberMain {

	public static void main(String[] args) {
		
		BokMember[] mbrArr = new BokMember[4];
		
		BokMember mbr1 = new BokMember();
		mbr1.id = "leess";
		mbr1.passwd = "qWer1234$";
		mbr1.name = "이순신";
		
		BokMember mbr2 = new BokMember();
		mbr2.id = "leess";
		mbr2.passwd = "qWer1234$";
		mbr2.name = "엄정화";
		
		mbrArr[0] = mbr1;
		mbrArr[1] = mbr2;
		
		Scanner sc = new Scanner(System.in);
		int menuNo = 0;
		
		BokMember loginMbr = null;
		
		do {
			String loginMsg = "";
			String login_logout = "";
			String myInfoSearch = "";
			String myInfoUpdate = "";
			
			if(loginMbr == null) {
				login_logout = "로그인";
			} else {
				loginMsg = "["+ loginMbr.name +"님 로그인중]";
				login_logout = "로그아웃";
				myInfoSearch = "4.내정보조회   ";
				myInfoUpdate = "5.내정보수정   ";
			}
			
			System.out.println("\n========== >> 메뉴 "+ loginMsg +"<< ============ \n"
							 + "1.회원가입   2."+login_logout+"\n"
							 + "3.모든회원조회   "+ myInfoSearch + myInfoUpdate +"6.프로그램종료\n"
							 + "=========================================");
			
			System.out.print("▷ 선택하세요 => ");
			
			try {
				menuNo = Integer.parseInt(sc.nextLine());
				
				if( !(1 <= menuNo && menuNo <= 6) ) {
					System.out.println(">> [경고] 메뉴에 없는 번호입니다. <<\n");
					continue;
				}
				
				switch (menuNo) {
					case 1:
						
						if(BokMember.count < mbrArr.length) {
							
							BokMember mbr = new BokMember();
							
							for(;;) {
								System.out.print("\n1.아이디 : ");
								String id = sc.nextLine();
								
								boolean isIdDuplicate = false;
								
								for (int i = 0; i < BokMember.count; i++) {
									if(mbrArr[i].id.equals(id)) {
										isIdDuplicate = true;
										break;
									}
								}
								
								if( isIdDuplicate || id.length() == 0 ) {
									System.out.println(">> [경고] 아이디가 중복되었거나 아이디를 입력하지 않으셨습니다. 새로운 아이디를 입력하세요 <<\n");
								}
								else {
									mbr.id = id;
									break;
								}
							}
							
							for(;;) {
								System.out.print("2.비밀번호 : ");
								String passwd = sc.nextLine();
								
								if(!(MyUtil.isCheckPasswd(passwd))) {
									System.out.println(">> [경고] 비밀번호는 8글자 이상 15글자 이해에 대, 소문자, 숫자, 특수문자가 혼합되어져야만 합니다. <<\n");
								}
								else {
									mbr.passwd = passwd;
									break;
								}
							}
							
							for(;;) {
								System.out.print("3.성명 : ");
								String name = sc.nextLine();
								
								if(name.length() == 0) {
									System.out.println(">> [경고] 성명은 필수입력 사항입니다. <<\n");
								}
								else {
									mbr.name = name;
									break;
								}
							}
							
							for (int i = 0; i < mbrArr.length; i++) {
								if(mbrArr[i] == null) {
									mbrArr[i] = mbr;
									break;
								}
							}
							
							System.out.println(">> 회원가입 성공 !! ["+ (++BokMember.count) +" 번째 가입/정원 :"+ mbrArr.length +" 명] <<\n");
						} else {
							System.out.println(">> [경고] 정원마감으로 인해 더 이상 회원가입이 불가합니다. <<");
						}
						
						break;
	
				}
			}
			
		}
	}
}


























