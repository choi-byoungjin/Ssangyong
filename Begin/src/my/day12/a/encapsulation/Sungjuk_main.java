package my.day12.a.encapsulation;

public class Sungjuk_main {

	public static void main(String[] args) {
		
		Sungjuk_noncapsule lee_sj = new Sungjuk_noncapsule();
		
		lee_sj.name = "이순신";
		
		if( lee_sj.isCheckJumsu(95) ) {
			lee_sj.kor = 95;
		}
				
		lee_sj.eng = 200;
		lee_sj.math = -90;
		
		lee_sj.showSungjuk();
		
		//////////////////////////////////////////////////////
		
		System.out.println("\n~~~~~~~ 캡슐화 한것 ~~~~~~~~\n");
		
		Sungjuk_capsule eom_sj = new Sungjuk_capsule();
		
		eom_sj.setKor(300);
		System.out.println(">> 국어점수 : " + eom_sj.getKor());
//		eom_sj.showSungjuk();
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~\n");
		
		eom_sj.setKor(95);
		System.out.println(">> 국어점수 : " + eom_sj.getKor());
//		eom_sj.showSungjuk();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~\n");
/*		
		eom_sj.kor = -1232;
		eom_sj.showSungjuk();
*/		
		System.out.println("\n#############################################\n");
		
		Sungjuk_capsule[] sungjukArr = new Sungjuk_capsule[5];
	/*	
		Sungjuk_capsule sj_1 = new Sungjuk_capsule();
		sj_1.setName("     ");
		sj_1.setKor(90);
		sj_1.setEng(80);
		sj_1.setMath(100);
	*/	
	/*	
		Sungjuk_capsule sj_2 = new Sungjuk_capsule();
		sj_2.setName("서강준");
		sj_2.setKor(-300);
		sj_2.setEng(80);
		sj_2.setMath(100);
	*/
		
		Sungjuk_capsule sj_3 = new Sungjuk_capsule();
		sj_3.setName("이순신");
		sj_3.setKor(90);
		sj_3.setEng(80);
		sj_3.setMath(100);
		
		Sungjuk_ctrl ctrl = new Sungjuk_ctrl();
	//	ctrl.saveSungjuk(sungjukArr, sj_1);
	//	ctrl.saveSungjuk(sungjukArr, sj_2);
		ctrl.saveSungjuk(sungjukArr, sj_3);
		
	}// end of main(String[] args)-----------------------

}
