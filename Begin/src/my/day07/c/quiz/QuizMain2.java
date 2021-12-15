package my.day07.c.quiz;

public class QuizMain2 {

/*
	== 입사문제 == 
	*********1
	********2
	*******3
	******4
	*****5
	****6
	***7
	**8
	*9	

위와같이 나오도록 하세요.
1. for 문을 사용해서 출력
2. while 문을 사용해서 출력
3. do~while 문을 사용해서 출력

1. 메일제목 : 과제2_입사문제_김민정
2. 첨부파일 : 과제2_입사문제_김민정.zip 파일로 제출
            (Quiz2Main.java)
3. 제출기한 : 2021.12.16 18:00 까지 제출
4. 보내는메일주소 : tjdudgkr0959@naver.com     
 */
	
	public static void main(String[] args) {
		
		System.out.println("\n=========1. for 문을 사용해서 출력============\n");
		
		
		for (int i = 9; i > 0; i--) {
			for (int j = 0; j <= i; j++) {
				if (j==i) {
					System.out.println(10-j);
				}
				else {
					System.out.print("*");
				}
			}
		}
		
		System.out.println("\n=========2. while 문을 사용해서 출력============\n");
		
		int i = 1;
		while (i < 10) {
			int j = 10;
			while (j > i) {
				System.out.print("*");
				j--;
			}
			System.out.println(i);
			i++;
		}
		
		System.out.println("\n=========3. do~while 문을 사용해서 출력============\n");
		
		i = 1;
		do {
			int j = 10;
			do{
				System.out.print("*");
				j--;
			}while (j > i);
			System.out.println(i);
			i++;
		}while (i < 10);
	}
}
