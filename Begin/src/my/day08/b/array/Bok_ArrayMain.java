package my.day08.b.array;

public class Bok_ArrayMain {

	public int hapage(int ... point) {
		
		int total = 0;
		for (int i = 0; i < point.length; i++) {
			total += point[i];
		}
		
		return total;
	}// end of public int hapage(int ... point)--------------------------------
	
	
	public static void main(String[] args) {
		
		int[] subjectArr;
		
		subjectArr = new int[7];
		
		System.out.println("배열 subjectArr 의 길이 : " + subjectArr.length);
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		
		for (int i = 0; i < subjectArr.length; i++) {
			int val = subjectArr[i];
			System.out.println("subjectArr["+ i +"] => " + val);
		}// end of for ----------------------------------------------------------
		
		subjectArr[0] = 100;
		subjectArr[1] = 90;
		subjectArr[2] = 95;
		subjectArr[3] = 70;
		subjectArr[4] = 98;
		subjectArr[5] = 100;
		subjectArr[6] = 90;
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		
		
		int sum = 0;		
		for (int i = 0; i < subjectArr.length; i++) {
			sum += subjectArr[i];
		}// end of for ------------------------------------------------------------
		
		System.out.println("총점 : "  + sum);
		
		double avg = Math.round((double)sum/subjectArr.length*10)/10.0;
		
		System.out.println("평균 : " + avg);
		
		System.out.println("\n=======================================\n");
		

		
		
		int[] subjectArr2 = new int[7];
		
		subjectArr2[0] = 100;
		subjectArr2[1] = 90;
		subjectArr2[2] = 95;
		subjectArr2[3] = 70;
		subjectArr2[4] = 98;
		subjectArr2[5] = 100;
		subjectArr2[6] = 90;
		
		for (int i = 0; i < subjectArr2.length; i++) {
			int val = subjectArr2[i];
			System.out.println("subjectArr["+ i +"] => " + val);
		}// end of for ----------------------------------------------------------
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		
		
		sum = 0;
		for (int i = 0; i < subjectArr2.length; i++) {
			sum += subjectArr2[i];	
		}
		System.out.println("총점 : " + sum);
		
		avg = Math.round((double)sum/subjectArr2.length*10)/10.0;
		
		System.out.println("평균 : " + avg);
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		
		
		int[] subjectArr3 = new int[] {100,90,95,70,98,100,90};
		
		for (int i = 0; i < subjectArr3.length; i++) {
			int val = subjectArr3[i];
			System.out.println("subjectArr3["+ i +"] => " + val);	
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		
		
		sum = 0;
		for (int i = 0; i < subjectArr3.length; i++) {
			sum += subjectArr3[i];
		}
		System.out.println("총점 : " + sum);
		
		avg = Math.round((double)sum/subjectArr3.length*10)/10.0;
		
		System.out.println("평균 : " + avg);
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		
		
		
		int[] subjectArr4 = {100,90,95,70,98,100,90};
		
		for (int i = 0; i < subjectArr4.length; i++) {
			int val = subjectArr[i];
			System.out.println("subjectArr4["+ i +"] => " + val); 
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		
		
		sum = 0;
		for (int i = 0; i < subjectArr4.length; i++) {
			sum += subjectArr4[i];
		}
		System.out.println("총점 : " + sum);
		
		avg = Math.round((double)sum/subjectArr4.length*10)/10.0;
		
		System.out.println("평균 : " + avg);
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		
		
		
		int total = 0;
		for (int subj : subjectArr4) {
			System.out.println(subj);
			total += subj;
		} 
		
		System.out.println("총점 : " + total);
		
		System.out.println("\n ===== 메소드 파라미터 가변인수 사용하기 =====");
		
		ArrayMain am1 = new ArrayMain();
		
		System.out.println("총점(100,90,95,70,98,100,90) : " + am1.hapage(100,90,95,70,98,100,90));
		
		System.out.println("총점(90,95,70,98,90) : " + am1.hapage(90,95,70,98,90));
		
		
	}// end of public static void main(String[] args)--------------------------------

}
