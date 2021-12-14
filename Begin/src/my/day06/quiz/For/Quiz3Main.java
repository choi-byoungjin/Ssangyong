package my.day06.quiz.For;

import java.util.Scanner;

public class Quiz3Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("▷ 휴대폰 번호를 입력하세요(예> 010-1234-5678) : ");
		String phoneNum = sc.nextLine();
		String intPhoneNum = "";
		
		for (int i = 0; i < phoneNum.length(); i++) {
			if(phoneNum.charAt(i) == '-') {
				continue;
			}
			else {
				intPhoneNum += phoneNum.charAt(i);
			}
		}
		System.out.print(intPhoneNum);
		sc.close();
	}

}
