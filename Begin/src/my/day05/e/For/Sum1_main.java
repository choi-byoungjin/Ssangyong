package my.day05.e.For;

public class Sum1_main {

	public static void main(String[] args) {
		
		int sum = 0; // sum 은 누적의 합을 저장시키는 변수
		
		// 0+1 ==> 1번 반복
        // 0+1+2 ==> 2번 반복
        // 0+1+2+3 ==> 3번 반복
        // 0+1+2+3+4 ==> 4번 반복
        // 0+1+2+3+4+5 ==> 5번 반복
        // 0+1+2+3+4+5+6 ==> 6번 반복
        // 0+1+2+3+4+5+6+7 ==> 7번 반복
        // 0+1+2+3+4+5+6+7+8 ==> 8번 반복
        // 0+1+2+3+4+5+6+7+8+9 ==> 9번 반복
        // 0+1+2+3+4+5+6+7+8+9+10 ==> 10번 반복
      
        // 1+2+3+4+5+6+7+8+9+10 ==> 55
		
		for (int i = 1; i <= 10; i++) {
			sum += i;	// sum = sum + i; 와 같은 말이다.
						
						// sum = 0 + 1; ==> 1번 반복
						// sum = 0 + 1 + 2; ==> 2번 반복
						// sum = 0 + 1 + 2 + 3; ==> 3번 반복
						// sum = 0 + 1 + 2 + 3 + 4; ==> 4번 반복
						// .........
						// sum = 0 + 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10; ==> 10번 반복 
		} // end of for -----------------------------------------------------
		
		
		System.out.println("1 부터 10 까지의 누적의 합계 : " + sum);
		// 1 부터 10 까지의 누적의 합계 : 55
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		String str = "";
		sum = 0; // sum은 누적의 합을 저장시키는 변수
		
		for(int i=1; i<=10; i++) {
			
			if(i<10)
				str += i + "+";	// str = str + i + "+";
				
								// str =  "" + 1 + "+";							"1+"
								// str =  "" + 1 + "+" + 2 + "+";				"1+2+"
								// str =  "" + 1 + "+" + 2 + "+" + 3 + "+";		"1+2+3+"
								// .........................................
								// str = "1+2+3+5+6+7+8+9+10+"
			else	
				str += i;		// str = str + i;
			
			sum += i;
		}// end of for --------------------------------------------------------
		
		
		System.out.println(str + " = " + sum);
		// 1+2+3+4+5+6+7+8+9+10=55
	
	}// end of void main(String[] args) ---------------------------------------

}
