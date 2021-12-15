package my.day08.a.random;

import java.util.Scanner;

public class _1MathRandomMain {

	public static void main(String[] args) {
		
		// === 랜덤한 정수를 뽑아낸다.
		double random = Math.random();
		System.out.println("random => " + random);
		/*
	        java.lang.Math.random() 메소드는 
	        0.0 이상 1.0 미만의 실수(double)값을 랜덤하게 나타내어주는 메소드이다. 
	           즉,  0.0 <= 임의의 난수(실수) < 1.0 
	           
	        1 부터 10까지중 랜덤한 정수를 얻어와 본다.
	        
	        	랜덤한 정수 = (int)(Math.random()*구간범위)+시작값;
	        
	        0.0        (int)(0.0*(10-1+1))+1         ==>  1
	        0.23346438 (int)(0.23346438*(10-1+1))+1  ==>  3
	        0.67835431 (int)(0.67835431*(10-1+1))+1  ==>  7
	        0.99999999 (int)(0.99999999*(10-1+1))+1  ==> 10
	        
	        3 부터 7까지중 랜덤한 정수를 얻어와 본다.
	        
	        0.0        (int)(0.0*(7-3+1))+3         ==>  3
	        0.23346438 (int)(0.23346438*(7-3+1))+3  ==>  4
	        0.67835431 (int)(0.67835431*(7-3+1))+3  ==>  6
	        0.99999999 (int)(0.99999999*(7-3+1))+3  ==>  7
		 */
		
		// 1 부터 10까지중 랜덤한 정수를 얻어와 본다.
		int rand1 = (int)(Math.random()*(10-1+1))+1;
		System.out.println("1 부터 10 까지의 랜덤한 정수 => " + rand1);
		
		// 3 부터 7까지중 랜덤한 정수를 얻어와 본다.
		int rand2 = (int)(Math.random()*(7-3+1))+3;
		System.out.println("3 부터 7 까지의 랜덤한 정수 => " + rand2);
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		////////////////////////////////////////////////////////
		
		double db_random = Math.random();

		// 1 부터 45까지중 랜덤한 정수를 얻어와 본다.
		int rand3 = (int)(Math.random()*(45-1+1))+45;
		System.out.println("1 부터 45 까지의 랜덤한 정수 => " + rand3);
		
		// 'A' 부터 'Z'까지의 랜덤한 알파벳 대문자 한개를 얻어와 본다.
		int upper_int = (int)(db_random*('Z'-'A'+1))+'A';
		System.out.println("'A' 부터 'Z'까지의 랜덤한 알파벳 대문자 => " + (char)upper_int);
		
		// 'a' 부터 'z'까지의 랜덤한 알파벳 소문자 한개를 얻어와 본다.
		int lower_int = (int)(db_random*('z'-'a'+1))+'a';
		System.out.println("'a' 부터 'z'까지의 랜덤한 알파벳 소문자 => " + (char)lower_int);
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		///////////////////////////////////////////////////////////////////////
		
		// 인증키는 랜덤한 숫자 3개(0~9)와 랜덤한 소문자 4개로 만들어진다.
		// 예> 103qdtq	020abat
		
		String key = "";
		for(int i = 0; i < 3; i++) {
			int num = (int)(Math.random()*(9-0+1))+0;
			key += num;
		}	
		for(int i = 0; i < 4; i++) {
			int num = (int)(Math.random()*('z'-'a'+1))+'a';
			key += (char)num;
		}	
		
		System.out.println("인증키 => " + key);
		// 인증키 => 103qdtq
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~홀짝 맞추기~~~~~~~~~~~~~~~~~~~~~\n");
		
		Scanner sc = new Scanner(System.in);
		
		do {
			
		} while (true);
		// end of do~while ------------------------------------------------
		
		sc.close();
		
	}// end of main(String[] args)

}
