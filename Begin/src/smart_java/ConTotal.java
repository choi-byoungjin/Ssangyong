package smart_java;

import java.util.Scanner;

public class ConTotal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		do {
			System.out.print("구구단(1), 누적합계(2), 성적(3), 종료(0) : ");
			int menu = s.nextInt();
			if( menu == 0 ) break;  // 종료
			else if( menu == 1 ) {  // 구구단
				System.out.print("단수를 입력하시오 : ");
				int dan = s.nextInt();
				if(dan>=2 && dan <=9) {  // 단수가 2~9 사이이면
					for(int i=1; i<=9; i++)  // 구구단 출력
						System.out.print(dan+"*"+i+"="+(dan*i)+" \n");
					System.out.println();
				} else System.out.println("2에서 9사이를 입력하시오!!");
			} else if( menu == 2 ) { // 누적합계
				System.out.print("누적합계를 원하는 최종값을 입력하시오 : ");
				int last = s.nextInt();
				int sum = 0;
				int cnt = 1;
				while(cnt <= last) sum += cnt++;
				System.out.println("1에서 "+last+"까지의 누적합계는 "+sum+"입니다.");
			} else if( menu == 3 ) { // 성적
				System.out.print("점수를 입력하시오 : ");
				int jumsu = s.nextInt();
				switch(jumsu/10) {
				case 10:
				case 9:
					System.out.println("A학점!!");
					break;
				case 8:
					System.out.println("B학점!!");
					break;
				case 7:
					System.out.println("C학점!!");
					break;
				case 6:
					System.out.println("D학점!!");
					break;
				default:
					System.out.println("F학점!!");
					break;
				}
			}
		} while(true);
	}
}
