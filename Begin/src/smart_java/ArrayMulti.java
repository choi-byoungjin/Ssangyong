package smart_java;

public class ArrayMulti {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 2차원 배열 정의(선언,생성,초기화) 방법
		int[][] a = new int[2][3];
		a[0][0] = 1;
		a[0][1] = 2;
		a[0][2] = 3;
		a[1][0] = 4;
		a[1][1] = 5;
		a[1][2] = 6;
		
		int[][] b = new int[][] { 
			{ 1,2,3,4 }, 
			{ 5,6,7,8 }, 
			{ 9,10,11,12} 
		};
		
		int[][] c = { {1,2,3,4}, {5,6,7,8}, {9,10,11,12} };

		System.out.println("[2차원 배열 출력]");
		for(int row=0; row<b.length; row++) {	// 행의 수
			for(int col=0; col<b[row].length; col++) {	// 열의 수
				System.out.print(b[row][col]+" ");
			}
			System.out.println();
		}
		
		// 2차원 가변 배열
		int[][] d = new int[2][];
		d[0] = new int[3];
		d[1] = new int[4];
		d[0][0] = 1;
		d[0][1] = 2;
		d[0][2] = 3;
		d[1][0] = 4;
		d[1][1] = 5;
		d[1][2] = 6;
		d[1][3] = 7;
		
		int[][] e = new int[2][];
		e[0] = new int[] { 1,2,3,4,5 };
		e[1] = new int[] { 1,2,3 };
		
		int[][] f = new int[][] { { 1,2,3,4,5,6 }, { 7,8,9,10 }, { 11,12,13,14,15 } };


		System.out.println("[2차원 가변 배열 출력]");
		for(int row=0; row<f.length; row++) {
			for(int col=0; col<f[row].length; col++) {
				System.out.print(f[row][col]+" ");
			}
			System.out.println();
		}

		
		
	}

}
