package my.day11.d.abstraction;

import java.util.Scanner;

import my.util.MyUtil;
import my.util.MyUtilBok;

public class GujikjaCtrlBok {

	GujikjaBok register(Scanner sc, GujikjaBok[] guArr) {
		
		boolean isUseridDuplicate;
		String userid = null;
		String passwd = null;
		String name = null;
		String jubun = null;
		
		do {
			isUseridDuplicate = false;
			
			System.out.print("1. 아이디: ");
			userid = sc.nextLine();
			
			for (int i = 0; i < GujikjaBok.count; i++) {
				if(userid.equals(guArr[i].userid)) {
					System.out.println(">> [경고] 이미 사용중인 아이디 입니다. <<\n");
					break;
				}
			}
			if(userid.trim().isEmpty()) {
				System.out.println(">> [경고] 아이디는 필수 입력 사항입니다. <<\n");
				isUseridDuplicate = true;
			}
		} while(isUseridDuplicate);
		
		do {
			System.out.print("2. 비밀번호: ");
			passwd = sc.nextLine();
			
			if(!MyUtilBok.isCheckPasswd(passwd))
				System.out.println(">> [경고] 암호는 8글자 이상 15글자 이하의 대,소문자 및 숫자, 특수분자가 혼합되어야 합니다. << \n");
			else
				break;
		} while(true);
		
		do {
			System.out.print("3. 성명 : ");
			name = sc.nextLine();
			
			if(name.trim().isEmpty())
				System.out.println(">> [경고] 성명은 필수 입력 사항입니다. <<");
			else
				break;
		} while(true);
		
		do {
			System.out.print("4. 주민번호 앞 7자리 : ");
			jubun = sc.nextLine();
			
			if(!MyUtilBok.isCheckJubun(jubun))
				System.out.println(">> [경고] 주민번호 7자리를 올바르게 입력하세요 <<");
			else
				break;
		} while(true);
		
		GujikjaBok gu = new GujikjaBok();
		gu.userid = userid;
		gu.passwd = passwd;
		gu.name = name;
		gu.jubun = jubun;
		
		return gu;
	}

	void showAll(GujikjaBok[] guArr) {
		
		if(GujikjaBok.count == 0) 
			System.out.println(">> 현재 가입된 구직자가 아무도 없습니다. <<\n");
		else {
			System.out.println("-----------------------------------------------------------");
			System.out.printf("%-10s\t%-15s\t%-8s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
		    System.out.println("-----------------------------------------------------------");
		    
		    for (int i = 0; i < GujikjaBok.count; i++) {
				guArr[i].viewInfo();
			}
		    System.out.println("\n");
		}	
	}
	
	void showByAgeline(GujikjaBok[] guArr, int ageline) {
		
		if(GujikjaBok.count == 0)
			System.out.println(">> 현재 가입된 구직자가 아무도 없습니다. <<\n");
		else {
			System.out.println("-----------------------------------------------------------");
			System.out.printf("%-10s\t%-15s\t%-8s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
		    System.out.println("-----------------------------------------------------------");
		    
		    boolean isFind = false;
		    for (int i = 0; i < GujikjaBok.count; i++) {
				int stored_ageline = guArr[i].getAge()/10*10;
				if(stored_ageline == ageline) {
					isFind = true;
					guArr[i].viewInfo();
				}
			}
		    if(!isFind) {
		    	System.out.println(">> 검색하신 "+ ageline +"연령대는 없습니다. <<");
		    }
		}
	}

	void showByGender(GujikjaBok[] guArr, String gender) {
		
		if(GujikjaBok.count == 0)
			System.out.println(">> 현재 가입된 구직자가 아무도 없습니다. <<\n");
		else {
			System.out.println("-----------------------------------------------------------");
			System.out.printf("%-10s\t%-15s\t%-8s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
		    System.out.println("-----------------------------------------------------------");
		    
		    for (int i = 0; i < GujikjaBok.count; i++) {
				if(gender.equals(guArr[i].getGender()))
					guArr[i].viewInfo();
			}
		    System.out.println();
		}
			
		
	}

	void showByAgelineGender(GujikjaBok[] guArr, int ageline, String gender) {
		
		if(GujikjaBok.count == 0)
			System.out.println(">> 현재 가입된 구직자가 아무도 없습니다. << \n");
		else {
			System.out.println("-----------------------------------------------------------");
			System.out.printf("%-10s\t%-15s\t%-8s\t%-4s\t%-2s\n","아이디","암호","성명","현재나이","성별");
		    System.out.println("-----------------------------------------------------------");
		    
		    boolean isFind = false;
		    for (int i = 0; i < GujikjaBok.count; i++) {
				int stored_ageline = guArr[i].getAge()/10*10;
				String stored_gender = guArr[i].getGender();
				if(stored_ageline == ageline && stored_gender.equals(gender)) {
					isFind = true;
					guArr[i].viewInfo();
				}
			}
		    if(!isFind) {
				System.out.println(">> 검색하신 연령 "+ageline+"대에 속하는 "+gender+"자는 없습니다. <<\n");
			}
		    System.out.println();
		}
	}
}
