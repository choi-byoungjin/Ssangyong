package smart_java;

import java.util.Scanner;

public class ConSwitch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		// 숫자를 문자로 변경하는 프로그램
		int input;
		System.out.print("[숫자를 문자로 변경] 숫자를 입력하세요(1~3) : ");
		input = s.nextInt();
		switch (input) 
		{
		case 1: 
			System.out.println("ONE");
			break;
		case 2:
			System.out.println("TWO");
			break;                // 생략하면 어떻게 될까요?
		case 3:
			System.out.println("THREE");
			break;
		default:
			System.out.println("ERROR");
			break;
		}

		// 홀수와 짝수를 구분하는 프로그램
		System.out.print("[홀수와 짝수 구분하기] 숫자를 입력하세요 : ");
		input = s.nextInt();
		switch (input % 2)
		{
		case 0:
			System.out.println(input+"는 짝수입니다.");
			break;
		case 1:
			System.out.println(input+"는 홀수입니다.");
			break;
		}

	}

}
