package my.day17.d.collection;

import java.util.Scanner;

public interface InterCompanyCtrl extends InterController {

	// == 구인회사 메뉴를 보여주는 메소드 생성하기 == //
	void showMenu(Scanner sc, Member[] mbrArr);
	
	// == 구인회사(Company) 신규 회원가입을 해주는 메소드 생성하기 == //
	void register(Scanner sc, Member[] mbrArr);
	
	// 관리자전용 메소드 생성하기 //
    void showAdminMenu(Scanner sc, Member[] mbrArr);
    
    // 모든 구인회사 정보조회 해주는 메소드 생성하기 //
 	void showAllCompany_info(Member[] mbrArr);
 	
    // 구인회사 업종별 검색 해주는 메소드 생성하기 //
 	void searchJobtype(Member[] mbrArr, Scanner sc);
    
	
}
