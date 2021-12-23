package my.day14.a.inheritance;

import java.util.Scanner;

public class Controller {
	
	GujikjaCtrl guCtrl = new GujikjaCtrl(); // 객체를 만들어야 컨트롤을 사용할 수 있다.
	CompanyCtrl comCtrl = new CompanyCtrl();
	
	// == 메뉴를 보여주는 메소드 생성하기 == //
	public void startMenu(Scanner sc, Gujikja[] guArr, Company[] comArr) {
		
		String str_choice = "";
		do {
			System.out.println("\n==== >> 시작메뉴 << ====\n"
							+  "A.구직자 메뉴로 가기   B.구인회사 메뉴로 가기\n"
							+  "C.프로그램 종료");
			
			System.out.print("▷ 메뉴번호 선택 => ");
			str_choice = sc.nextLine();
			
			switch (str_choice.toUpperCase()) { // 대,소문자 입력 가능
				case "A": // 구직자 메뉴로 가기
					guCtrl.showMenu(sc, guArr);
					break;
		
				case "B": // 구인회사 메뉴로 가기
					comCtrl.showMenu(sc, comArr);
					break;
		
				case "C": // 프로그램 종료
					
					break;
		
				default:
					System.out.println(">> [경고] 메뉴에 없는 것을 선택하셨습니다. <<\n");
					break;
			}// end of switch (str_choice)----------------------------------
			
		} while( !("C".equals(str_choice.toUpperCase())));
		
	}// end of public void startMenu(Scanner sc)------------------------------------------
}
