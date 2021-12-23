package my.day13.a.capsulation;

import java.util.Scanner;

public class Gujikja_main_bok {

	public static void main(String[] args) {
		
		GujikjaBok admin_gu = new GujikjaBok();
		admin_gu.setUserid("admin");
		admin_gu.setPasswd("qWer1234$");
		admin_gu.setName("관리자");
		admin_gu.setJubun("8905081");
		
		GujikjaBok choi_gu = new GujikjaBok();
		choi_gu.setUserid("choi");
		choi_gu.setPasswd("qWer1234$");
		choi_gu.setName("최가나");
		choi_gu.setJubun("9405081");
		
		GujikjaBok kim_gu = new GujikjaBok();
		kim_gu.setUserid("kim");
		kim_gu.setPasswd("qWer1234$");
		kim_gu.setName("김나다");
		kim_gu.setJubun("9505082");
		
		GujikjaBok lee_gu = new GujikjaBok();
		lee_gu.setUserid("lee");
		lee_gu.setPasswd("qWer1234$");
		lee_gu.setName("이다라");
		lee_gu.setJubun("9005081");
		
		GujikjaBok park_gu = new GujikjaBok();
		park_gu.setUserid("park");
		park_gu.setPasswd("qWer1234$");
		park_gu.setName("박라마");
		park_gu.setJubun("9005082");
		
		GujikjaBok[] guArr = new GujikjaBok[7];
		
		guArr[GujikjaBok.count++] = admin_gu;
		guArr[GujikjaBok.count++] = choi_gu;
		guArr[GujikjaBok.count++] = kim_gu;
		guArr[GujikjaBok.count++] = lee_gu;
		guArr[GujikjaBok.count++] = park_gu;
		
		Scanner sc = new Scanner(System.in);
		GujikjaCtrlBok ctrl = new GujikjaCtrlBok();
		ctrl.showMenu(sc, guArr);
		
		sc.close();
		System.out.println(">> == 프로그램 종료 == <<");
	}
}
