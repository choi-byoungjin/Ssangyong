package smart_java.exceptionclass;

public class ExceptionClass {

	public void ex_1() {
		System.out.println("[예외 처리를 하지 않는 경우]");
		int a = 10;
		int b = 0;
		
		int result = a / b ;  // 예외 발생시 비정상 종료됨
		System.out.println(result);
	}
	public void ex_2() {
		System.out.println("[예외 처리를 하는 경우]");
		int a = 10;
		int b = 0;
		try {
			int result = a / b ;
			System.out.println(result);
		} catch(ArithmeticException ae) {
			ae.printStackTrace();
			System.out.println("예외 원인 : "+ae.getMessage());
		}
	}
	public void ex_3() throws ArithmeticException {
		System.out.println("[예외 처리를 던지는 경우]");
		int a = 10;
		int b = 0;
		int result = a / b ;
		System.out.println(result);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExceptionClass ec = new ExceptionClass();
		
		//ec.ex_1();
		ec.ex_2();
		
		try {
			ec.ex_3();
		} catch(ArithmeticException ae) {
			ae.printStackTrace();
			System.out.println("예외 원인 : "+ae.getMessage());
		}
		
	}

}
