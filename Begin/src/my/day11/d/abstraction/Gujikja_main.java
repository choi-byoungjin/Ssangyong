package my.day11.d.abstraction;

import java.util.Scanner;

import my.util.MyUtil;

public class Gujikja_main {

	public static void main(String[] args) {
		
		Gujikja[] guArr = new Gujikja[5];
		
		Gujikja gu1 = new Gujikja();
		gu1.userid = "eomjh";
		gu1.passwd = "qwEr1234$A";
		gu1.name = "엄정화";
		
	//	String jubun = null;	  	//	==> false
	//	String jubun = "951220"; 	//	==> false
	//	String jubun = "95122012";	//	==> false
	//	String jubun = "9512401";	//  ==> false
	//	String jubun = "9513201";   //	==> false
	//	String jubun = "9512t01";   //	==> false
	//	String jubun = "95122207";  //	==> false
		String jubun = "9512202";	//	==> true
		
		boolean bool = MyUtil.isCheckJubun(jubun);
		
		if(bool) {
			gu1.jubun = jubun;
			guArr[0] = gu1;
			
			Gujikja.count++;
		}
		
	//	System.out.println(bool); // false
		
		Gujikja gu2 = new Gujikja();
        gu2.userid = "leess";
        gu2.passwd = "qwer1234$B";
        gu2.name = "이순신";
        gu2.jubun = "9710201";
      
        guArr[1] = gu2;
        Gujikja.count++;
      
      
        Gujikja gu3 = new Gujikja();
        gu3.userid = "youks";
        gu3.passwd = "qwer1234$C";
        gu3.name = "유관순";
        gu3.jubun = "8510202";
      
        guArr[2] = gu3;
        Gujikja.count++;
        
        /////////////////////////////////////////////////////////////////////
        
        Scanner sc = new Scanner(System.in);

        String smenuNo = "";
        do {
        	System.out.println(" === 메인메뉴 === \n"
                    + "1.구직자 회원가입   2.구직자 모두 보기  3.검색   4.프로그램 종료 \n");
     
        	System.out.print("▷ 메뉴번호 선택 => "); 
        	smenuNo = sc.nextLine();
        	
        	switch (smenuNo) {
			case "1": // 구직자 회원가입
				
				break;

			case "2": // 구직자 모두 보기

				break;
				
			case "3": // 검색
				
				break;
				
			case "4": // 프로그램 종료
				
				break;
				
			default:  // 1 ~ 4 가 아닌 값
				System.out.println(">> [경고] 선택하신 번호는 메뉴에 없습니다. << \n");
				break;
			}
        	
        } while (!("4".equals(smenuNo)));	// null예외 오류 발생하지 않게 작성 
        // end of do~while -----------------------------------------------
        
        sc.close();
        
        System.out.println("\n~~~~~ 프로그램 종료 ~~~~~");

	}// end of main ------------------------------------------------------

}
