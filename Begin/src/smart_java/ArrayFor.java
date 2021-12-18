package smart_java;

import java.util.Scanner;

public class ArrayFor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);

		// 배열의 값 출력하기
		System.out.println("[배열의 값 출력하기]");
		int[] a = { 1,2,3,4,5 };    
		//for (int i = 0; i < 5; i++) {
		for (int i = 0; i < a.length; i++) {
			System.out.println("a["+i+"] = "+a[i]);
		}

		// 배열의 값 저장하기
		System.out.println("[배열의 값 저장후 출력하기]");
		for (int i = 0; i < a.length; i++) {
			a[i] = (i + 1) * 10;    
		}
		for (int i = 0; i < a.length; i++) {
			System.out.println("a["+i+"] = "+a[i]);
		}

		// 배열의 합계와 평균 구하기
		System.out.println("[배열의 합계와 평균 구하기]");
		int[] arr = { 34, 10, 25, 71, 84 };
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];   // sum = sum + arr[i];
		}
		System.out.println("합계 = "+sum+", 평균 = "+((double)sum / a.length));

		// 2개 배열의 합계 배열 만들기
		System.out.println("[2개 배열의 합계 배열 만들기]");
		int[] arr1 = { 12, 32, 76, 51, 49 };
		int[] arr2 = { 34, 56, 29, 54, 21 };
		int[] arr3 = new int[5];

		for (int i = 0; i < arr1.length; i++) {
			arr3[i] = arr1[i] + arr2[i];
		}
		for (int i = 0; i < arr3.length; i++) {
			System.out.println(arr1[i]+" + "+arr2[i]+" = "+arr3[i]);
		}

		// 입력받은 값의 짝수와 홀수의 합계 계산하기, 0입력 종료
		System.out.println("[입력받은 값의 짝수와 홀수의 합계 계산하기, 0입력 종료]");
		int[] total = { 0, 0 };
		int input;
		do {
			System.out.print("숫자입력 : ");
			input = s.nextInt();
			total[input % 2] += input;
		} while (input != 0);
		System.out.println("짝수의 합 = "+total[0]+", 홀수의 합 = "+total[1]);

	}

}
