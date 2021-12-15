package smart;


public class OpExam_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 산술 연산자 실습
		int a = 100;
		int b = 30;
		
		int ab = a + b;
		System.out.println(a+" + "+b+" = "+ab);
		System.out.println(a+" - "+b+" = "+(a-b));
		System.out.println(a+" * "+b+" = "+(a*b));
		System.out.println(a+" / "+b+" = "+(a/b));
		System.out.println(a+" % "+b+" = "+(a%b));
		
		//int zero = a / 0;  // 컴파일시 에러가 발생되지는 않지만 실행
		//System.out.println(a+" / 0 = "+zero);
		
		ab = a + b * 3;
		System.out.println(a+" + "+b+" * 3 = "+ab);
		
		double c = 50.45;
		double d = 15.98;
		System.out.println(c+" + "+d+" = "+(c+d));
		System.out.println(c+" - "+d+" = "+(c-d));
		System.out.println(c+" * "+d+" = "+(c*d));
		System.out.println(c+" / "+d+" = "+(c/d));
		
		//ab = c+d;  // ERROR
		ab = (int)c+(int)d;
		System.out.println("(int)"+c+" + (int)"+d+" = "+ab);
		ab = (int)(c+d);
		System.out.println("(int)("+c+" + "+d+") = "+ab);
		//ab = c/d;  // ERROR
		ab = (int)c/(int)d;
		System.out.println("(int)"+c+" / (int)"+d+" = "+ab);
		ab = (int)(a/b);
		System.out.println("(int)("+a+" / "+b+") = "+ab);
		double cd = a/b;
		System.out.println(a+" / "+b+" = "+cd);
        cd = (double)(a/b);
        System.out.println("(double)("+a+" / "+b+") = "+cd);
		cd = (double)a/b;
		System.out.println("(double)"+a+" / "+b+" = "+cd);
		cd = a/(double)b;
		System.out.println(a+" / (double)"+b+" = "+cd);
		
		// 대입 연산자 실습
		int sum = 10;
		sum += 10;	// sum = sum + 10;
		System.out.println("sum += 10 > "+sum);
		sum -= 5;	// sum = sum - 5;
		System.out.println("sum -=  5 > "+sum);
		sum *= 2;	// sum = sum * 2;
		System.out.println("sum *=  2 > "+sum);
		sum /= 3;	// sum = sum / 3;
		System.out.println("sum /=  3 > "+sum);
		sum %= 3;	// sum = sum % 3;
		System.out.println("sum %=  3 > "+sum);
		
		// 증감 연산자 실습
		sum = 10;
		sum++;		// sum = sum + 1;
		System.out.println("sum++ > "+sum);
		sum--;		// sum = sum - 1;
		System.out.println("sum-- > "+sum);
		++sum;		// sum = sum + 1;
		System.out.println("++sum > "+sum);
		--sum;		// sum = sum - 1;
		System.out.println("--sum > "+sum);
		
		a = 10;
		b = 15;
		
		int total = a++;
		System.out.println("total = "+total+" a = "+ a);
		total = ++a;
		System.out.println("total = "+total+" a = "+ a);
		total = a--;
		System.out.println("total = "+total+" a = "+ a);
		total = --a;
		System.out.println("total = "+total+" a = "+ a);
		
		total = ++a - b--;
		System.out.println("total = "+total+" a = "+a+" b = "+b);
		
		// 관계 연산자 실습
		System.out.println(" 10 == 20 "+ (10 == 20));
		System.out.println(" 10 != 20 "+ (10 != 20));
		System.out.println(" 10 > 20 "+ (10 > 20));
		System.out.println(" 10 < 20 "+ (10 < 20));
		System.out.println(" 10 >= 20 "+ (10 >= 20));
		System.out.println(" 10 <= 20 "+ (10 <= 20));

		boolean bo = 10 > 20;
		System.out.println("boolean = "+bo);
		
	}

}
