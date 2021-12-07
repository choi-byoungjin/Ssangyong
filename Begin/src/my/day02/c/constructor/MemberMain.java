package my.day02.c.constructor;

public class MemberMain {

	public static void main(String[] args) {
		
		Member hongMbr = new Member();	// 파라미터가 없는 생성자를 기본생성자(default constructor)라고 부른다.
		hongMbr.userid = "hongkd";
		hongMbr.passwd = "1234";
		hongMbr.name = "홍길동";
		hongMbr.age = 20;
		hongMbr.point = 100;
		
		hongMbr.showInfo();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Member iyouMbr = new Member("iyou", "abcd", "아이유", 29, 300);
		iyouMbr.showInfo();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Member chaewMbr = new Member("chaew", "qwer", "차은우");
		chaewMbr.showInfo();
	}

}
