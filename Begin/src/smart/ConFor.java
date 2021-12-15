package smart;

public class ConFor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("[동일한 문장 반복하여 3번 출력하기]");
		for (int cnt = 0; cnt < 3; cnt++)  
		{
			System.out.println("Hello");
		}
	//		System.out.println("Hello");
	//		System.out.println("Hello");
	//		System.out.println("Hello");
	//		for (int cnt = 0; cnt < 3; cnt++)
	//	 		 System.out.println("Hello");

		System.out.println("[1에서 10까지 출력, 합계출력]");
		int sum = 0;
		for (int cnt = 1; cnt <=10 ; cnt++)
		{
			System.out.print(cnt+" ");
			sum += cnt;
		}
		System.out.println("1~10까지의 합계는 "+sum);

		System.out.println("[10에서 1까지 출력]");
		for (int cnt = 10; cnt > 0; cnt--)
		{
			System.out.print(cnt+" ");
		}
		System.out.println();

		System.out.println("[1에서 10까지 짝수만 출력]");
		for (int cnt = 2; cnt <= 10; cnt+=2)
		{
			System.out.print(cnt+" ");
		}
		System.out.println();

		System.out.println("[1에서 10까지 홀수만 출력]");
		for (int cnt = 1; cnt <= 10; cnt+=2)
		{
			System.out.print(cnt+" ");
		}
		System.out.println();

		System.out.println("[구구단 2단 출력]");
		for (int cnt = 1; cnt <= 9; cnt++)
		{
			System.out.println("2 * "+cnt+" = "+(2*cnt));
		}

		System.out.println("[구구단 출력]");
		for (int dan = 2; dan <= 9; dan++)
		{
			for (int cnt = 1; cnt <= 9; cnt++)
			{
				System.out.print(dan+"*"+cnt+"="+(dan*cnt)+" ");
			}
			System.out.println();
		}
	}
}
