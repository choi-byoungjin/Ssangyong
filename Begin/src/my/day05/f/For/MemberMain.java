package my.day05.f.For;

import java.util.Scanner;

import my.util.MyUtil;

public class MemberMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=== 회원가입 하기 ===\n");
		
	//	System.out.print("1. 아이디 => ");
	//	String userid = sc.nextLine();
		
		System.out.print("2. 암호 => ");
		String passwd = sc.nextLine();
		
		// 암호는 8글자 이상 15글자 이하의 대문자, 소문자, 숫자, 특수문자가 혼합되어져야만 통과한다.
		System.out.println(MyUtil.isCheckPasswd(passwd));
		
	//	System.out.print("3. 성명 => ");
	//	String name = sc.nextLine();
		
		sc.close();

	}

}
