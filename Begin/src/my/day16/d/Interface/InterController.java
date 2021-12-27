package my.day16.d.Interface;

import java.util.Scanner;

public interface InterController { 

	// == 메뉴를 보여주는 메소드 생성하기 == //
	void startMenu(Scanner sc, Member [] mbrArr) ;
		
	// 구직자 또는 구인회사로 로그인 처리를 해주는 메소드 생성하기, 인터페이스 접근제한자는 항상 public
	Member login(Scanner sc, Member[] mbrArr);
		
}
