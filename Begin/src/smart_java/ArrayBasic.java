package smart_java;

public class ArrayBasic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 배열의 선언
		int a[];
		int[] b;
		
		// 배열의 생성
		int c[] = new int[5];
		int[] d = new int[5];
		int e[];
		e = new int[5];
		
		// 배열 초기화 
		
		int[] f = new int[5];
		f[0] = 10;
		f[1] = 20;
		f[2] = 30;
		f[3] = 40;
		f[4] = 50;
		
		//int[] g = new int[5] { 10,20,30,40,50 }; // 오류발생
		int[] g = new int[] { 10,20,30,40,50 };
		int[] h = { 10,20,30,40,50 };
		
		int[] i;
		i = new int[] { 10,20,30,40,50 };
		// i = {10,20,30,40,50};  // 오류발생
		
		// 배열요소의 개수
		System.out.println("배열 i의 요소 개수는 " + i.length);
		
		int[] j = new int[] { 10, 20, 30, 40, 50 };
		j[5] = 100;  // 실행 오류 발생
		
	}

}
