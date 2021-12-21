package my.day11.d.abstraction;

import java.util.Scanner;

import my.util.MyUtilBok;

public class Gujikja_main_Bok {

	public static void main(String[] args) {
		
		GujikjaBok[] guArr = new GujikjaBok[5];
		
		GujikjaBok gu1 = new GujikjaBok();
		gu1.userid = "eomjh";
		gu1.passwd = "qwEr1234$A";
		gu1.name = "엄정화";
		String jubun = "9512202";
		
		boolean bool = MyUtilBok.isCheckJubun(jubun);
		
		if(bool) {
			gu1.jubun = jubun;
			guArr[GujikjaBok.count++] = gu1;
		}
		
		GujikjaBok gu2 = new GujikjaBok();
		gu2.userid = "leess";
		gu2.passwd = "qwer1234$B";
		gu2.name = "이순신";
		gu2.jubun = "9710201";
		guArr[GujikjaBok.count++] = gu2;
		
		GujikjaBok gu3 = new GujikjaBok();
		gu3.userid = "youks";
		gu3.passwd = "qwer1234$C";
		gu3.name = "유관순";
		gu3.jubun = "8510202";
		guArr[GujikjaBok.count++] = gu3;
		
		GujikjaCtrlBok ctrl = new GujikjaCtrlBok();
		Scanner sc = new Scanner(System.in);
		
		String smenuNo = "";
		do {
			System.out.println(" === 메인메뉴 === \n"
					+ "1.구직자 회원가입   2.구직자 모두 보기   3.검색   4.프로그램 종료 \n");
			System.out.println("▷ 메뉴번호 선택 => ");
			smenuNo = sc.nextLine();
			
			switch (smenuNo) {
			case "1":
				if( GujikjaBok.count < guArr.length ) {
					GujikjaBok gu = ctrl.register(sc, guArr);
					guArr[GujikjaBok.count++] = gu;
					System.out.println(">> 회원가입 성공!! << \n");
				}
				else {
					System.out.println(">> [경고] 정원 "+guArr.length+"명이 다차서 더 이상 회원가입은 불가합니다. << \\n");
				}
				break;
				
			case "2":
				ctrl.showAll(guArr);
				break;
				
			case "3":
				searchMenu(sc, guArr, ctrl);
				break;
				
			case "4":
				break;
			
			default:
				System.out.println(">> [경고] 선택하신 번호는 메뉴에 없습니다. << \n");
				break;
			}
		} while (!("4".equals(smenuNo)));
		
		sc.close();
		System.out.println("\n~~~~~ 프로그램 종료 ~~~~~");
	}
	
	static void searchMenu(Scanner sc, GujikjaBok[] guArr, GujikjaCtrlBok ctrl) {
		
		String sMenuNo = "";
		do {
			System.out.println(" === 검색메뉴 === \n"
					+ "1.연령대검색   2.성별검색   3.연령대 및 성별검색   4.메인메뉴로 돌아가기 \n");
			System.out.println("▷ 검색메뉴번호 선택 => ");
			sMenuNo = sc.nextLine();
			
			switch(sMenuNo) {
				case "1":
					int ageline = 0;
					for(;;) {
						System.out.println("▷ 검색할 연령대[예: 20] => ");
						String sAgeline = sc.nextLine();
						try {
							ageline = Integer.parseInt(sAgeline);
							break;
						} catch (NumberFormatException e) {
							System.out.println(">> [경고] 숫자만 입력하세요!! << \n");
						}
					}
					ctrl.showByAgeline(guArr, ageline);
					break;
				
				case "2":
					for(;;) {
						System.out.println("▷ 검색할 성별[남/여] => ");
						String gender = sc.nextLine();
						if( !"남".equals(gender) && !"여".equals(gender))
							System.out.println(">> [경고] 남 또는 여 만 입력하세요!! << \n");
						else {
							ctrl.showByGender(guArr, gender);
							break;
						}
					}
					break;
					
				case "3":
					String gender = "";
					for(;;) {
						try {
							System.out.println("▷ 검색할 연령대[예: 20] => ");
							String sAgeline = sc.nextLine();
							ageline = Integer.parseInt(sAgeline);
						} catch (NumberFormatException e) {
							System.out.println(">> [경고] 숫자만 입력하세요!! << \n");
							continue;
						}
						
						System.out.println("▷ 검색할 성별[남/여] => ");
						gender = sc.nextLine();
						
						if( !"남".equals(gender) && !"여".equals(gender))
							System.out.println(">> [경고] 남 또는 여 만 입력하세요!! << \n");
						else {
							break;
						}
					}
					ctrl.showByAgelineGender(guArr, ageline, gender);
					break;
				
				case "4":
					break;
				
				default:
					System.out.println(">> [경고] 검색메뉴에 없는 번호입니다!! <<\n");
					break;
			}
		} while(!("4".equals(sMenuNo)));
	} 
}
