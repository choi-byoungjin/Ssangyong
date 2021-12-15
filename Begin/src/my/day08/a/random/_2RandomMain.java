package my.day08.a.random;

import java.util.Random;
import java.util.Scanner;

public class _2RandomMain {

	public static void main(String[] args) {
		
		// === 랜덤한 정수를 뽑아낸다.
		
		// 보안상 Math.random() 보다는 new Random(); 을 사용하는 것이 더 안전하다.
		
		Random rnd = new Random();
		
		// int rndNum = rnd.nextint(마지막수 - 처음수 + 1) + 처음수;
		
		// 1 부터 10까지중 랜덤한 정수를 얻어와 본다.
		int rand1 = rnd.nextInt(10 - 1 + 1)+1;
		System.out.println("1 부터 10 까지의 랜덤한 정수 => " + rand1);
		
		// 3 부터 7까지중 랜덤한 정수를 얻어와 본다.
		int rand2 = rnd.nextInt(7 - 3 + 1)+3;
		System.out.println("3 부터 7 까지의 랜덤한 정수 => " + rand2);
		
		
		
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		////////////////////////////////////////////////////////

		// 1 부터 45까지중 랜덤한 정수를 얻어와 본다.
		int rand3 = rnd.nextInt(45 - 1 + 1) + 1;
		System.out.println("1 부터 45 까지의 랜덤한 정수 => " + rand3);
		
		// 'A' 부터 'Z'까지의 랜덤한 알파벳 대문자 한개를 얻어와 본다.
		int upper_int = rnd.nextInt('Z' - 'A' + 1)+'A';
		System.out.println("'A' 부터 'Z'까지의 랜덤한 알파벳 대문자 => " + (char)upper_int);
		
		// 'a' 부터 'z'까지의 랜덤한 알파벳 소문자 한개를 얻어와 본다.
		int lower_int = rnd.nextInt('z' - 'a' + 1)+'a';	// rnd.nextInt 자체가 랜덤하게 나온다.
		System.out.println("'a' 부터 'z'까지의 랜덤한 알파벳 소문자 => " + (char)lower_int);

		// 대,소문자를 같게하고자 한다. 예: 대문자 P 가 나오면 소문자 p 가 나오도록 하고 싶다.
		upper_int = rnd.nextInt('Z' - 'A' + 1)+'A';
		System.out.println("'A' 부터 'Z'까지의 랜덤한 알파벳 대문자 => " + (char)upper_int);
		System.out.println("'a' 부터 'z'까지의 랜덤한 알파벳 소문자 => " + (char)(upper_int+32));
		
		
		
		
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		///////////////////////////////////////////////////////////////////////
		
		// 인증키는 랜덤한 숫자 3개(0~9)와 랜덤한 소문자 4개로 만들어진다.
		// 예> 103qdtq	020abat
		
		String key = "";
		for(int i = 0; i < 3; i++) {
			int num = rnd.nextInt(9 - 0 + 1) + 0;
			key += num;
		}	
		for(int i = 0; i < 4; i++) {
			int num = rnd.nextInt('z' - 'a' + 1) + 'a';
			key += (char)num;
		}	
		
		System.out.println("인증키 => " + key);
		// 인증키 => 103qdtq
		
		
		
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~홀짝 맞추기~~~~~~~~~~~~~~~~~~~~~\n");
		
		Scanner sc = new Scanner(System.in);
		
		outer:
		do {
		
			try {
				System.out.print("선택[1:홀수 / 0:짝수] => ");
				String choice = sc.nextLine(); // "안녕하세요"	"1.2345"
											   // "9" 
				int user_choice_no = Integer.parseInt(choice);
			
				if(user_choice_no != 0 && user_choice_no != 1) {
					System.out.println(">> [경고] 0 또는 1 만 입력하세요!! << \n");
				}
				else {
					// PC에서 랜덤하게 1 또는 10 까지중 하나만 가지도록 만들자.
					int pc_choice_no = rnd.nextInt(10-1+1)+1;
					
					String result = "";
					
					if(pc_choice_no%2 == user_choice_no) {
						result = "맞추었습니다.";
					}
					else {
						result = "틀렸습니다.";
					}
					
					System.out.println("컴퓨터가 낸수 : " + pc_choice_no + "=> " + result);
					
					do {
						System.out.println(">> 또 할래?[Y/N] => ");
						String yn = sc.nextLine();
						
						if("Y".equalsIgnoreCase(yn)) {
							break;
						}
						else if("N".equalsIgnoreCase(yn)) {
							break outer;
						}
						else {
							System.out.println(">> [경고] Y 또는 N 만 입력하세요!! << \n");
						}
						
					} while (true);
					// end of do~while ---------------------------------------------------
				}
				
			} catch(NumberFormatException e) {
				System.out.println(">> [경고] 정수만 입력하세요!! << \n");
				
				break;
			}
			
		} while (true);
		// end of do~while ------------------------------------------------
		
		sc.close();
		System.out.println(">> 프로그램 종료 <<");
		
	}// end of main(String[] args)

}
