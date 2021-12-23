package my.day14.a.inheritance;

public class Main {

	public static void main(String[] args) {
		
		Gujikja[] guArr = new Gujikja[5];
		Company[] comArr = new Company[3];
		
		Gujikja admin_gu = new Gujikja();
		admin_gu.setId("admin");
		admin_gu.setPasswd("qWer1234$");
		admin_gu.setName("관리자");
		admin_gu.setJubun("8905071");
		
		Gujikja lss_gu = new Gujikja();
		lss_gu.setId("leess");
		lss_gu.setPasswd("qWer1234$");
		lss_gu.setName("이순신");
		lss_gu.setJubun("9005071");
		
		Gujikja ejh_gu = new Gujikja();
		ejh_gu.setId("eomjh");
		ejh_gu.setPasswd("qWer1234$");
		ejh_gu.setName("엄정화");
		ejh_gu.setJubun("9810072");
		
		Gujikja yks_gu = new Gujikja();
		yks_gu.setId("youks");
		yks_gu.setPasswd("qWer1234$");
        yks_gu.setName("유관순");
        yks_gu.setJubun("9103012");
		
        guArr[Gujikja.count++] = admin_gu;
		guArr[Gujikja.count++] = lss_gu;
		guArr[Gujikja.count++] = ejh_gu;
		guArr[Gujikja.count++] = yks_gu;
		
		Company com1 = new Company();
		com1.setId("samsung");
		com1.setPasswd("aBcd1234$");
		
		

	}

}
