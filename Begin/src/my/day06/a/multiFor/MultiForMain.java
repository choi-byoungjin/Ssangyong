package my.day06.a.multiFor;

public class MultiForMain {

	public static void main(String[] args) {
		//	다중 for문에 대해서 알아봅니다.
		//	다중 for문이라 함은 for문 속에 또 다른 for문이 있는 것을 말한다. 
		char ch = 'a';
		
		for (int i=0; i<3; i++){//	바깥 for문 ==> 행
		
			for (int j=0; j<7; j++){//	내부 for문 ==> 열
				System.out.print(ch++);
			}// end of for ----------------------------------------
			
			System.out.println("\n");
		}// end of for --------------------------------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		/*
		 * == 단일 for문을 사용하여 아래처럼 나오도록 하세요 ==
		 * 
		 * abcdefg
		 * hijklmn
		 * opqrstu
		 */
		
		ch = 'a';
		for(int i=0; i<'u'-'a'+1; i++) {
			System.out.print(ch++);
			if((i+1)%7 == 0) {
				System.out.println("\n");
			}
		}
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print("["+i+","+j+"]");
			}
		System.out.println("\n");
		}

		
		// continue; 를 만나면 가장 가까운 반복문의 증감식으로 이동한다.
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		for (int i = 0; i < 4; i++) {
			
			if(i==2) continue;	// continue; 가 되어지면 밑으로 내려가지 않고 가장 가까운 반복문의 증감식으로 이동한다.
			
			for (int j = 0; j < 3; j++) {
				System.out.print("["+i+","+j+"]");
			}
			
			System.out.println("\n");
		}
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		for (int i = 0; i < 4; i++) {
			
			if(i==2) continue;	// continue; 가 되어지면 밑으로 내려가지 않고 가장 가까운 반복문의 증감식으로 이동한다.
			
			for (int j = 0; j < 3; j++) {
				if(j==1) continue;
				System.out.print("["+i+","+j+"]");
			}
			
			System.out.println("\n");
		}
		
		System.out.println("\n안녕하세요\t\"좋은아침\"\t입니다.\n");
		
		for (int i = 5; i > 0; i--) {
			if(i==4) continue;
			for (int j = 1; j < 6; j++) {
				if(j==4) continue;
				System.out.print(i+"0"+j+"호\t");
			}
			System.out.println("\n");
		}
		
	}

}
