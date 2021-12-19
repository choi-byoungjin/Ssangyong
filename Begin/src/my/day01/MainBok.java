package my.day01;

public class MainBok {

	public static void main(String[] args) {
		
		MemberBok lssmbr = new MemberBok();
		
		lssmbr.id = "leess";
		lssmbr.pwd = "abcd";
		lssmbr.name = "이순신";
		lssmbr.email = "leess@naver.com";
		
		System.out.println("lssmbr : " + lssmbr);
		
		MemberBok eomjhmbr = new MemberBok();
		
		eomjhmbr.id = "eomjh";
		eomjhmbr.pwd = "qwer1234";
		eomjhmbr.name = "엄정화";
		eomjhmbr.email = "eomjh@gmail.net";
		
		System.out.println("eomjhmbr : " + eomjhmbr);

		lssmbr.showInfo();
		eomjhmbr.showInfo();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		MemberBok.address = "인천광역시 송도구";
		lssmbr.showInfo();
		eomjhmbr.showInfo();
		
		System.out.println("\n###################################\n");
		
		MemberBok.address = "서울특별시 마포구";
		lssmbr.showInfo();
		eomjhmbr.showInfo();
		
		System.out.println("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n");
		
		MemberBok.address = "경기도 군포시";
		lssmbr.showInfo();
		eomjhmbr.showInfo();
		
	}

}
