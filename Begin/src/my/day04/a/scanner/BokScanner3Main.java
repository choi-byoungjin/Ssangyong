package my.day04.a.scanner;

import java.util.Scanner;

public class BokScanner3Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수를 입력하세요 => ");
		try {
			String inputStr = sc.nextLine();
			int n = Integer.parseInt(inputStr) + 10;
			System.out.println("입력한 정수에 10을 더한 값 : " + n);
		} catch(NumberFormatException e) {
			System.out.println(">>> 정수만 입력하세요!! <<<");
			e.printStackTrace();
		}
		sc.close();
	}

}
