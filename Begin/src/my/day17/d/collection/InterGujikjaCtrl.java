package my.day17.d.collection;

import java.util.Scanner;

public interface InterGujikjaCtrl extends InterController {
	
	// == 구직자 메뉴를 보여주는 메소드 생성하기 == //
    void showMenu(Scanner sc, Member[] mbrArr);
		
    // == 구직자(Gujikja) 신규 회원가입을 해주는 메소드 생성하기 == //
 	void register(Scanner sc, Member[] mbrArr);
 	 	
    // 관리자 전용 메뉴를 보여주는 메소드 생성하기 //
 	void showAdminMenu(Scanner sc, Member[] mbrArr);
 	
    // 관리자를 제외한 모든 구직자 정보를 조회해주는 메소드 생성하기 //
 	void showAllGujikja_info(Member[] mbrArr);
 	
    // 연령대및성별을 검색해주는 메소드 생성하기 //
 	void searchAgeLineGender(Member[] mbrArr, Scanner sc);
    
}
