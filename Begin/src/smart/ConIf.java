package smart;

import java.util.Scanner;

public class ConIf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int input;

		// �ܼ� if()��
		System.out.print("[단순 if()문 연습] 숫자를 입력하세요 : ");
		input = s.nextInt();
		if (input == 10) 
		{
			input += 2;
		}
		input += 3;
		System.out.println("계산된 값은 :"+input);
		/* if문 단순화
		    if(input == 10) {
		        input +=2;
			}
			if(input == 10) 
			   input += 2;
			if(input == 10) input += 2;
		*/

		// if()~else문
		System.out.print("[if()~else문 연습] 숫자를 입력하세요 : ");
		input = s.nextInt();
		if (input < 10)
		{
			input += 2;
		}
		else
		{
			input -= 2;
		}
		input += 3;
		System.out.println("계산된 값은 : "+input);
		/*	if()~else 
		    if (input < 10) {
				input += 2;
			} else {
				input -= 2;
			}
			if (input < 10) 
			      input += 2;
			else 
			     input -= 2;
			if (input < 10) input += 2;
			else input -= 2;
		*/

		// if()~else if()��
		char result = 'F';
		System.out.print("[if()~else if()문 연습] 숫자를 입력하세요 : ");
		input = s.nextInt();
		if (input >= 90)
		{
			result = 'A';
		}
		else if( input >= 80)
		{
			result = 'B';
		}
		else if (input >= 70)
		{
			result = 'C';
		}
		System.out.println(input+" 점은 "+result+" 학점입니다.");

		// if()~else if() ~ else ��
		System.out.print("[if()~else if() ~ else 문 연습] 숫자를 입력하세요 : ");
		input = s.nextInt();
		if (input >= 90)
		{
			result = 'A';
		}
		else if (input >= 80)
		{
			result = 'B';
		}
		else
		{
			result = 'F';
		}
		System.out.println(input+" 점은 "+result+" 학점입니다.");
		
		s.close();
	}

}
