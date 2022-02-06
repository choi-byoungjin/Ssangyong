package my.day03.a.casting;

public class BokCastingMain {

	public static void main(String[] args) {

		byte no1 = 100;
		short sh = no1;
		System.out.println("sh => " + sh);
		
		int in = sh;
		System.out.println("in => " + in);
		
		long ln = in;
		System.out.println("ln => " + ln);
		
		float ft = 1.234F;
		System.out.println("ft => " + ft);
		
		double db = ft;
		System.out.println("db => " + db);
		
		System.out.println("\n ~~~~~~~~~~ 강제형변환 ~~~~~~~~~~ \n");
		
		double db2 = 1.23456789;
		System.out.println("db2 => " + db2);
		
		float ft2 = (float)db2;
		System.out.println("ft => " + ft2);
		
		int in2 = 30000;
		System.out.println("in2 => " + in2);
		
		short sh2 = (short)in2;
		System.out.println("sh2 => " + sh2);
		
		in2 = 50000;
		sh2 = (short)in2;
		System.out.println("sh2 => " + sh2);
		
		long ln2 = 3000000000L;
		float ft3 = ln2;
		
		System.out.println("ln2 => " + ln2);
		System.out.println("ft3 => " + ft3);
		
		double db3 = 123.98765;
		int in3 = (int)db3;
		System.out.println("db3 => " + db3);
		System.out.println("in3 => " + in3);
	}

}
