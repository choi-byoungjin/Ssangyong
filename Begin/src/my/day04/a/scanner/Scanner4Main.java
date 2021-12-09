package my.day04.a.scanner;

import java.util.Scanner;

public class Scanner4Main {

	public static void main(String[] args) {
		
		System.out.println("== 키보드로 부터 2개의 숫자를 입력받아서 더한 결과 알아보기 == \n");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("1. 첫번째 정수 : ");
		String inputStr = sc.nextLine();	// "10"
		
		Integer.parseInt(inputStr);
		
		sc.close();

	}

}
