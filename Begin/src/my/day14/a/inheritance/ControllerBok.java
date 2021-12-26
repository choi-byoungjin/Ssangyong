package my.day14.a.inheritance;

import java.util.Scanner;

public class ControllerBok {

	GujikjaCtrlBok guCtrl = new GujikjaCtrlBok();
	CompanyCtrlBok comCtrl = new CompanyCtrlBok();
	
	public void startmenu(Scanner sc, GujikjaBok[] guArr, CompanyBok[] comArr) {
		String str_choice = "";
		do {
			System.out.println("\n==== >> 시작메뉴 << ====\n"
					+ "A.구직자 메뉴로 가기   B.구인회사 메뉴로 가기\n"
					+ "C프로그램 종료");
			System.out.print("▷ 메뉴번호 선택 => ");
			
			str_choice = sc.nextLine();
			switch (str_choice.toUpperCase()) {
				case "A":
					guCtrl.showMenu(sc, guArr);
					break;

				case "B":
					comCtrl.showMenu(sc, comArr, guArr);
					break;

				case "C":
					
					break;

				default:
					System.out.println(">> [경고] 메뉴에 없는 것을 선택하셨습니다. << \n");
					break;
			}
		} while( !("C".equals(str_choice.toUpperCase())));
	}
}
