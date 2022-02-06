package my.day02.c.constructor;

public class BokMemberMain {

	public static void main(String[] args) {
		
		BokMember kimMbr = new BokMember();
		kimMbr.userid = "kim";
		kimMbr.passwd = "1234";
		kimMbr.name = "김김김";
		kimMbr.age = 20;
		kimMbr.point = 100;
		
		kimMbr.showInfo();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		BokMember leeMbr = new BokMember("lee", "1234", "이이이", 29, 300);
		leeMbr.showInfo();
		
		System.out.println("\n=====================================\n");
		
		BokMember parkMbr = new BokMember("park", "1234", "박박박", 25, 250);
		parkMbr.showInfo();
		
		System.out.println("\n#####################################\n");
		
		kimMbr.updatePasswd("1234", "5678");
		leeMbr.updatePasswd("1234", "5678");
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		int jangoPoint = kimMbr.pointPayment(30);
		System.out.println("남은 포인트 : " + jangoPoint);
		
		System.out.println("\n-------------------------------------\n");
		
		String info = kimMbr.changeInfo("5678", 30, 400);
		
		System.out.println(info);
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		info = leeMbr.changeInfo("5678", "리리리", 25, 8000);
		
		System.out.println(info);
	}

}
