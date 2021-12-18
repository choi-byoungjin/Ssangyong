package smart_java;

import java.util.Scanner;

public class ArrayTotal {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int[][] arr = new int[3][4];  	// 2차원 배열
		int[] row_sum = new int[3];		// 행 합계 1차원 배열
		int[] col_sum = new int[4]; 	// 열 합계 1차원 배열

		System.out.println("[정수를 입력하세요]");
		for(int row=0; row<arr.length; row++) {
			for(int col=0; col<arr[row].length; col++) {
				System.out.print("["+row+"]["+col+"] = ");
				arr[row][col] = s.nextInt();
				row_sum[row] +=  arr[row][col];
				col_sum[col] +=  arr[row][col];
			}
		}

		System.out.println("[2차원 배열(행합계,열합계)]");
		for(int row=0; row<arr.length; row++) {
			for(int col=0; col<arr[row].length; col++) {
				System.out.print(arr[row][col]+"   ");
			}
			System.out.println("("+row_sum[row]+")");
		}
		for(int col=0; col<col_sum.length; col++) {
			System.out.print("("+col_sum[col]+") ");
		}

		s.close();
	}

}
