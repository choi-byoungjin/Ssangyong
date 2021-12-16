package my.day06.quiz.For;

import java.util.Scanner;

public class Quiz1Main {

	public static void main(String[] args) {
		/*
		int large = 0;
		int small = 0;
		int num = 0;
		int special = 0;

		Scanner sc = new Scanner(System.in);
		System.out.print("▷ 문자열을 입력하세요 : ");
		String str = sc.nextLine();			
		
		System.out.println("입력한 문자열 : " + str);
		System.out.println("입력한 문자열 길이 : " + str.length());
		
		for(int i = 0; i < str.length(); i++) {			
			if(Character.isUpperCase(str.charAt(i))) {
				large++;
			}

			else if(Character.isLowerCase(str.charAt(i))){
				small++;
			}
		
			else if(Character.isDigit(str.charAt(i))) {
				num++;
			}
			
			else {
				special++;
			}
			
		}// end of for -------------------------------------------
		
		System.out.println("대문자 개수 : " + large);
		System.out.println("소문자 개수 : " + small);
		System.out.println("숫자 개수 : " + num);
		System.out.println("특수문자 개수 : " + special);
		sc.close();
		*/
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("▷ 문자열을 입력하세요 : ");
		
		String str = sc.nextLine();
		
		int upperCnt=0, lowerCnt=0, numberCnt=0, specialCnt=0;
		
		for(int i=0; i<str.length(); i++) {
			char ch = str.charAt(i); 
			
			if( Character.isUpperCase(ch) ) // 대문자인지 검사
				upperCnt++;
			
			else if ( Character.isLowerCase(ch) ) // 소문자인지 검사
				lowerCnt++;
			
			else if ( Character.isDigit(ch) ) // 숫자인지 검사
				numberCnt++;
			
			else
				specialCnt++;
			
		}// end of for------------------------------
		
		System.out.println("입력한 문자열 : "+ str+"\n"
				        + "입력한 문자열 길이 : "+str.length()+"\n"
				        + "대문자 개수 : "+upperCnt+"개\n"
				        + "소문자 개수 : "+lowerCnt+"개\n"
				        + "숫자 개수  : "+numberCnt+"개\n"
				        + "특수문자 개수 : "+specialCnt+"개\n");
		
		/*
		  입력한 문자열 : Abz3Sa0#$T 
		  입력한 문자열 길이 : 
		  대문자 개수 : 3개
		  소문자 개수 : 3개
		  숫자 개수  : 2개
		  특수문자 개수 : 2개
		*/

		sc.close();
	}

}
