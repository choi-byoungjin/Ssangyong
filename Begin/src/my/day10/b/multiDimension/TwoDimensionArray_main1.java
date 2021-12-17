package my.day10.b.multiDimension;

public class TwoDimensionArray_main1 {

	public static void main(String[] args) {
		
		// === 1차원 배열 ===
		int[] subjectArr = new int[5]; // 1행 5열
	/*
			 ---------------------
	index => | 0 | 1 | 2 | 3 | 4 |	
			 ---------------------
	*/
		
		
		// === 2차원 배열 ===
		int[][] pointArr = new int[4][3]; // 4행 3열
	/*
	index =>
			----------------------------
			| [0][0] | [0][1] | [0][2] |
			----------------------------
			| [1][0] | [1][1] | [1][2] |
			----------------------------
			| [2][0] | [2][1] | [2][2] |
			----------------------------
			| [3][0] | [3][1] | [3][2] |
			----------------------------
	*/
		pointArr[0][0] = 10;
		pointArr[0][1] = 20;
		pointArr[0][2] = 30;
		
		pointArr[1][0] = 40;
		pointArr[1][1] = 50;
		pointArr[1][2] = 60;
	
	/*
		pointArr[2][0] = 70;
		pointArr[2][1] = 80;
		pointArr[2][2] = 90;
	*/	
		pointArr[3][0] = 100;
		pointArr[3][1] = 110;
		pointArr[3][2] = 120;
		
		System.out.println("pointArr.length => " + pointArr.length);
		// pointArr.length => 4
		// 이차원배열명.length => 행의길이가 나온다.
		
		System.out.println("pointArr[0].length => " + pointArr[0].length);
		// pointArr[0].length => 3
		// 이차원배열명[행의인덱스].length => 그 행에 존재하는 열의 길이가 나온다.
		
		System.out.println("pointArr[1].length => " + pointArr[1].length);
		// pointArr[1].length => 3
		// 이차원배열명[행의인덱스].length => 그 행에 존재하는 열의 길이가 나온다.
		
		System.out.println("pointArr[3].length => " + pointArr[3].length);
		// pointArr[3].length => 3
		// 이차원배열명[행의인덱스].length => 그 행에 존재하는 열의 길이가 나온다.
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for (int i = 0; i < pointArr.length; i++) {	// 행
			
			for(int j=0; j<pointArr[i].length; j++) { // 열
				String str_add = (j<pointArr[i].length-1)?",":"\n";
				System.out.printf("%3d%s", pointArr[i][j], str_add);
			}// end of for -----------------------------------------------
			
		}// end of for --------------------------------------------------
		
		System.out.println("\n========== 성적결과 ==========\n");
		
							// 국어 영어 수학
		int[][] jumsuArr = { {90,80,70},	// 이순신 
							 {80,85,76},	// 엄정화
							 {85,70,90},	// 서강준
							 {60,80,50}		// 이혜리
							};
				
		int[] totalArr = new int[jumsuArr.length];
		// 각 학생들의 총점을 저장시켜두는 곳이다.
		
		// 1. 각 학생별로 총점을 나타내어 보자.
		for (int i = 0; i < jumsuArr.length; i++) {
			
			int sum = 0;
			for(int j=0; j<jumsuArr[i].length; j++) {
				sum += jumsuArr[i][j];
			}// end of for ----------------------------------------------------
			
			totalArr[i] = sum;
			
		}// end of for ----------------------------------------------------
		
		for(int total : totalArr) {
			System.out.println(total);
		}
		/*
			240
			241
			245
			190
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		int[] subjectTotalArr = new int[jumsuArr[0].length];
		// 각 과목별 총점을 저장시켜두는 곳이다.
		
		// 2. 각 과목별로 총점을 나타내어 보자. // 세로 합
		for (int i = 0; i < jumsuArr[0].length; i++) {
			
			int sum = 0;
			for (int j = 0; j < jumsuArr.length; j++) {
				sum += jumsuArr[j][i];
			}// end of for ----------------------------------------------------
		
			subjectTotalArr[i] = sum;
			
		}// end of for ----------------------------------------------------
		
		System.out.println(">> 과목별 총점 <<");
		for(int total : subjectTotalArr){
			System.out.println(total);
		}// end of for ----------------------------------------------------
		/*
		 	>> 과목별 총점 <<
		 	315
		 	315
		 	286
		 */
		
		String[] nameArr = {"이순신","엄정화","서강준","이혜리"};
		String[] subjectNameArr = {"국어","영어","수학"};
		/*
			=== 학생별 총점 ===
			이순신 : 240
			엄정화 : 241
			서강준 : 245
			이혜리 : 190
		 */

		System.out.println("\n===== 학생별 점수 집계 =====\n"
						+  "-----------------------------------\n"
						+  "학생명   국어   영어   수학   총점   평균\n"
						+  "-----------------------------------");
		
		
		// 1. 각 학생별로 총점을 나타내어 보자.
		String result = "";
		
		for (int i = 0; i < jumsuArr.length; i++) {
			
			String guamokJumsu = "";
			int sum = 0;
			for(int j=0; j<jumsuArr[i].length; j++) {
				guamokJumsu += " "+jumsuArr[i][j]+ "   ";
				sum += jumsuArr[i][j];
			}// end of for ----------------------------------------------------
		
			result += nameArr[i] + " : " + guamokJumsu + sum + "   " + Math.round((double)sum/3*10)/10.0 + "\n";
			
		}// end of for ----------------------------------------------------
		
		System.out.println(result);

		
		
		System.out.println("\n===== 과목별 평균 =====\n"
						+  "--------------");
		
		result = "";
		for(String subject : subjectNameArr) {
			result += subject + "  ";
		}
		
		System.out.println(result+"\n--------------");
		
		
		result = "";
		
		for (int i = 0; i < jumsuArr[0].length; i++) {
			
			int guamokSum = 0;
			for (int j = 0; j < jumsuArr.length; j++) {
				guamokSum += jumsuArr[j][i];
			}// end of for --------------------------------------------------
			
			result += Math.round( (double)guamokSum/jumsuArr.length*10 )/10.0 + " ";
			
		}// end of for -----------------------------------------------------
		
		System.out.println(result);
		
	}// end of main(String[] args) ----------------------------------------------

}
