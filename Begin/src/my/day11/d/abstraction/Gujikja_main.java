package my.day11.d.abstraction;

import my.util.MyUtil;

public class Gujikja_main {

	public static void main(String[] args) {
		
		Gujikja[] guArr = new Gujikja[5];
		
		Gujikja gu1 = new Gujikja();
		gu1.userid = "eomjh";
		gu1.passwd = "qwEr1234$";
		gu1.name = "엄정화";
		
	//	String jubun = null;		==> false
		String jubun = "951220"; //	==> false
		
		boolean bool = MyUtil.isCheckJubun(jubun);
		
		System.out.println(bool); // false
		
	}// end of main ------------------------------------------------------

}
