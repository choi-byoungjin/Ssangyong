package my.day05.f.For;

import java.util.Scanner;

import my.util.MyUtil;

public class MemberMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=== 회원가입 하기(유효성검사) ===\n");

		
		
		String userid = "";
		for(;;) {
			System.out.print("1. 아이디 => ");
			userid = sc.nextLine();
			if(userid.length() == 0) {
				System.out.println(">> [경고] 아이디는 필수 입력사항 입니다. << \n");
			}
			else {
				break;
			}
		}// end of for --------------------------------------------------------
		
		
		
		String passwd = "";
		for(;;){
			System.out.print("2. 암호 => ");
			passwd = sc.nextLine();
			
			if(!MyUtil.isCheckPasswd(passwd)) {
				System.out.println(">> [경고] 암호는 8글자 이상 15글자 이하의 대문자, 소문자, 숫자, 특수문자가 혼합되어져야만 합니다.<< \n");
			}
			else {
				break;
			}
		}// end of for --------------------------------------------------------
		
		
		
		String name = "";
		for(;;) {
			System.out.print("3. 성명 => ");
			name = sc.nextLine();
			if(name.length() == 0) {
				System.out.println(">> [경고] 성명은 필수 입력사항 입니다. << \n");
			}
			else {
				break;
			}
		}// end of for --------------------------------------------------------
		
		

		
		Member mbr = new Member();
		mbr.userid = userid;
		mbr.passwd = passwd;
		mbr.name = name;
		
		mbr.showInfo();
		
		sc.close();

	}

}
