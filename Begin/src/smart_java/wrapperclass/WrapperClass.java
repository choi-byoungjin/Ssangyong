package smart_java.wrapperclass;

public class WrapperClass {

	public static void main(String[] args) {

		// Wrapper 클래스 객체 생성(오토 박싱)
		Boolean bn = true;
		Byte be = 100;
		Character cr = 'a';
		Short st = 200;
		Integer ir = 300;
		Long lg = 400L;
		Float ft = 10.5f;
		Double de = 10.5;
		
		// Wrapper 클래스 객체 출력(오토 언박싱)
		System.out.println("Boolean : "+bn);
		System.out.println("Byte : "+be);
		System.out.println("Character : "+cr);
		System.out.println("Short : "+st);
		System.out.println("Integer : "+ir);
		System.out.println("Long : "+lg);
		System.out.println("Float : "+ft);
		System.out.println("Double : "+de);
		
		int num = Integer.parseInt("300"); // 문자열 숫자를 숫자로 변환
		Integer in = num; // 오토 박싱

		float fin = in.floatValue();   //Integer 값을 float 값으로 변환 
		System.out.println("Integer --> float : "+fin);
		
		System.out.println("Binary : "+Integer.toBinaryString(in));
		System.out.println("Octal : "+Integer.toOctalString(in));
		System.out.println("Hexdecimal : "+Integer.toHexString(in));

		char[] data = { 'a', 'A', '3', ' ' };
		for(int i=0;i<data.length;i++) {
			if( Character.isDefined(data[i]) )
				System.out.println(data[i]+"=Unicode");
			if( Character.isDigit(data[i]) )
				System.out.println(data[i]+"=Digit");
			if( Character.isLetter(data[i]) )
				System.out.println(data[i]+"=Letter");
			if( Character.isLetterOrDigit(data[i]) )
				System.out.println(data[i]+"=Letter Or Digit");
			if( Character.isLowerCase(data[i]) ) 
				System.out.println(data[i]+"=LowerCase");
			if( Character.isUpperCase(data[i]) ) 
				System.out.println(data[i]+"=UpperCase");
		}

		Double value = 11.0;
		double result = value.doubleValue() / 0.0;  // 0으로 나누면 무한대
		
		System.out.println("result="+result);
		System.out.println("Infinite="+Double.isInfinite(result));
		System.out.println("MAX="+Double.MAX_VALUE);
		System.out.println("MIN="+Double.MIN_VALUE);
	}

}
