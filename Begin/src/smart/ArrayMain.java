package smart;

public class ArrayMain {

	public static void main(String[] args) { // args라는 배열로 입력가능한 문자열들을 받는다.
		
		System.out.println("main() 메소드 인수 값");
		
		for(int i=0; i < args.length; i++ ) {
			System.out.println((i+1)+"번째 인수의 값은 ["+args[i]+"] 입니다.");
		}
	}// run comfiguration에서 (x)=argument로 값 입력 가능
}
