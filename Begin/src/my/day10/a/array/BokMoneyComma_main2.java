package my.day10.a.array;

import java.text.DecimalFormat;
import java.util.Scanner;

public class BokMoneyComma_main2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("▷ 금액을 입력하세요(정수만) => ");
		
		long money = Long.parseLong(sc.nextLine().trim());
		
		DecimalFormat df = new DecimalFormat("#,###");
		String smoney = df.format(money);
		
		System.out.println(smoney);
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		System.out.print("▶ 금액을 입력하세요(정수로만) => ");
		String inputStr = sc.nextLine().trim();
		
		int len = inputStr.length();
		
		int commaCount = len/3;
		
		if(len%3 == 0) commaCount -= 1;
		
		char[] inputChArr = inputStr.toCharArray();
		
		char[] outputChArr = new char[len + commaCount];
		
		int cnt = 0;
		for (int i = outputChArr.length-1, j=inputChArr.length-1; i >= 0; i--, j--) {
			cnt++;
			if(cnt % 4 != 0) {
				outputChArr[i] = inputChArr[j];
			}
			else {
				outputChArr[i] = ',';
				j++;
			}	
		}
		
		String result = "";
		for (int i = 0; i < outputChArr.length; i++) {
			result += outputChArr[i];
		}
		
		System.out.println(result);
		
		sc.close();

	}

}
