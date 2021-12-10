package my.day05.d.For;

public class ForTest2_main {

	public static void main(String[] args) {
		
		System.out.println(">> 1. break <<");

		for(int i=0; i<10; i++) {
			if(i==5) break; // 반복문에서 break; 를 만나면 가장 가까운 반복문을 벗어나는 것이다.
			System.out.println(i+1);
		} // end of for---------------------------------------------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~\n");
		
		int cnt = 0;
		
		for(;;) {
			System.out.println(++cnt + "번째 반복");
		} // end of for ---------------------------------------------------------
			
	}

}
