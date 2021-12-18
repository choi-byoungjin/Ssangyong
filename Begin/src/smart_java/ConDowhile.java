package smart_java;

import java.util.Scanner;

public class ConDowhile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
	    
		int sum = 0;
	    int cnt = 0;
	    int input = 0;

	    System.out.println("[1에서 10사이의 값을 출력, 누적 합계 출력]");
	    sum = 0;
	    cnt = 1;
	    do
	    {
	        System.out.print(cnt+" ");
	        sum = sum + cnt;
	        cnt++;
	    } while (cnt <= 10);
	    System.out.println(" sum = "+sum);

	    System.out.println("[숫자를 반복해서 입력, 0입력시 종료]");
	    input=0;
	    do
	    {
	        System.out.print("숫자를 입력하세요 : ");
	        input = s.nextInt();
	    } while( input != 0 ) ;

	    System.out.println("[구구단을 입력받아 출력하는 프로그램, 0입력시 종료]");
	    int dan = 0;

	    do {
	        System.out.print("단을 입력하세요(0:종료) => ");
	        dan = s.nextInt();
	        if (dan == 0) break;
	        if (dan < 2 || dan > 9) {
	            System.out.println("2에서 9사이를 선택하세요!!");
	            continue;
	        }
	        cnt = 1;
	        do {
	            System.out.print(dan+"*"+cnt+"="+(dan*cnt)+" ");
	            cnt++;
	        } while (cnt <= 9);
	        System.out.println();
	    } while (true);
		
		
	}

}
