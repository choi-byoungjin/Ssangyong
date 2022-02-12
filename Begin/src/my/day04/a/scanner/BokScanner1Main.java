package my.day04.a.scanner;

import java.util.Scanner;

public class BokScanner1Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("첫번째 문장을 입력하세요 => ");
		String inputStr = sc.nextLine();
		System.out.print("첫번째 입력한 문장 => " + inputStr);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.print("정수를 입력하세요 => ");
		int inputNum = sc.nextInt();
		sc.nextLine();
		System.out.print("입력한 정수 : " + inputNum);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.print("두번째 문장을 입력하세요 => ");
		inputStr = sc.nextLine();
		System.out.print("두번째 입력한 문장 => " + inputStr);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.print("실수를 입력하세요 => ");
		double inputDbl = sc.nextDouble();
		sc.nextLine();
		System.out.print("입력한 실수 : " + inputDbl);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.print("단어를 입력하세요 => ");
		String inputWord = sc.next();
		sc.nextLine();
		System.out.print("입력한 단어 : " + inputWord);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.print("세번째 문장을 입력하세요 => ");
		inputStr = sc.nextLine();
		System.out.print("세번째 입력한 문장 => " + inputStr);
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		sc.close();
		
	}

}
