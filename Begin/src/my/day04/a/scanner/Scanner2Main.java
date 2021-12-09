package my.day04.a.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Scanner2Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("정수를 입력하세요 => ");
		
		try {
			int inputNum = sc.nextInt();	// 2021 엔터
											// 안녕하세요 엔터
			sc.nextLine();
			
			System.out.println("입력한 정수 : " + inputNum);
		} catch(InputMismatchException e) {
			System.out.println(">>> 정수만 입력하세요!! <<<");
			e.printStackTrace();
		}
		
		sc.close();

	}

}