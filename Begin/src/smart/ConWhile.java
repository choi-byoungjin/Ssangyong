package smart;

import java.util.Scanner;

public class ConWhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
	    int cnt = 1;
	    int sum = 0;

	    System.out.println("[1에서 10까지 출력하고, 합계 출력하기]");
	    while (cnt <= 10) {
	        System.out.print(cnt+" ");
	        sum += cnt++;
	    }
	    System.out.println("누적합계는 "+sum+"입니다.");

	    System.out.println("[1에서 100까지 누적합계가 100이 넘어가는 숫자 출력하기]");
	    cnt = 0;
	    sum = 0;
	    while (sum < 100)
	    {
	        cnt++;
	        sum += cnt;
	    }
	    System.out.println("count = "+cnt+" sum = "+sum);

	    System.out.println("[정수값을 반복해서 입력받아 화면에 출력(0입력시 종료)]");
	    int input;
	    while (true)  // 무한반복
	    {
	        System.out.print("숫자를 입력하세요(0은 종료) : ");
	        input = s.nextInt();
	        if (input == 0) break;   // 0 입력시 반복문 종료, 0이 아니면 반복
	        System.out.println("입력받은 값은 = "+input);
	    }

	    System.out.println("[입력받은 값이 짝수이면 누적 합계, 홀수이면 다시 입력 받기(0입력시 종료)]");
	    input = sum = 0;
	    while (true)
	    {
	        System.out.print("숫자를 입력하세요(0은 종료) : ");
	        input = s.nextInt();
	        if (input == 0) break;   // 0 입력시 반복문 종료, 0이 아니면 반복
	        if ((input % 2)==1) continue; // 홀수이면 반복
	        sum += input;  // 짝수인 경우 누적합계
	    }
	    System.out.println("짝수 합계는 "+sum);


	}

}
