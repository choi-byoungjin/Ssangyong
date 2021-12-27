package my.day16.g.exceptionThrows;

public class Member_main {

	public static void main(String[] args) {

		
		try {
			Member lssMbr = new Member();
			lssMbr.name = "이순신";
			lssMbr.kor = "90";
			lssMbr.eng = "80";
			lssMbr.math = "70";
			
			lssMbr.info();
			
			System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
			
			Member ejhMbr = new Member();
			ejhMbr.name = "엄정화";
			ejhMbr.kor = "90";
			ejhMbr.eng = "팔십";
			ejhMbr.math = "70";
		
			ejhMbr.info();
			
		} catch (Exception e) {
			System.out.println(">> 오류 메시지 내용 : " + e.getMessage());
		}
		
	}// end of main(String[] args)----------------------------

}
