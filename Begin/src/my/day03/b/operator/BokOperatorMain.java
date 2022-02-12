package my.day03.b.operator;

public class BokOperatorMain {

	public static void main(String[] args) {
		
		System.out.println("=== 1.산술연산자 + - * / %(나머지) << >> >>>");
		
		int n = 10;
		System.out.println("n+3 = " + (n+3));
		System.out.println("n-3 = " + (n-3));
		System.out.println("n*3 = " + (n*3));
		System.out.println("n/3 = " + (n/3));
		System.out.println("(double)n/3 = " + ((double)n/3));
		System.out.println("n%3 = " + (n%3));
		System.out.println("n<<3 = " + (n<<3));
		System.out.println("n>>3 = " + (n>>3));
		System.out.println("n>>>3 = " + (n>>>3));
		
		n = -10;
		System.out.println("\n~~~~ n이 -10인 경우 ~~~~");
		System.out.println("n<<3 = " + (n<<3));
		System.out.println("n>>3 = " + (n>>3));
		System.out.println("n>>>3 = " + (n>>>3));
		
		//////////////////////////////////////////////////////////////////////
		
		System.out.println("\n=== 2.증감연산자 ++ -- ====");
		
		int a=7, b=3;
		System.out.println("a => " + a);
		System.out.println("b => " + b);
		
		a++;
		System.out.println("a => " + a);
		
		++a;
		System.out.println("a => " + a);
		
		b--;
		System.out.println("b => " + b);
		
		--b;
		System.out.println("b => " + b);
		
		int x=10, y=10;
		int z=++x;
		System.out.println("z => " + z);
		System.out.println("x => " + x);
		
		z=y++;
		System.out.println("z => " + z);
		System.out.println("y => " + y);
		System.out.println("\n");
		
		////////////////////////////////////////////////////////
		
		System.out.println("\n==== 3. bit별 not 연산자 ~ ====");
		
		int m = 42;
		System.out.println("~m : " + ~m);
		
		////////////////////////////////////////////////////////
		
		System.out.println("\n==== 4. 논리 부정 연산자 ! ====");
		boolean bool = false;
		System.out.println("bool : " + bool);
		System.out.println("!bool : " + !bool);
		
		////////////////////////////////////////////////////////
		
		System.out.println("\n==== 5. bit 연산자 & | ^ ====");
		
		int x1=3, y1=5;
		System.out.println("x1 & y1 => " + (x1 & y1));
		System.out.println("x1 | y1 => " + (x1 | y1));
		System.out.println("x1 ^ y1 => " + (x1 ^ y1));
		
		////////////////////////////////////////////////////////
		
		System.out.println("\n==== 6. 논리연산자 & | && || ====");
		
		int c=50, d=60, e=70;
		bool = (c > d) && (d < e) && (c == 3);
		System.out.println("bool => " + bool);
		
		bool = (c > d) || (d < e) || (c == 3);
		System.out.println("bool => " + bool);
		
		bool = (c > d) & (d < e) & (c == 3);
		System.out.println("bool => " + bool);
		
		bool = (c > d) | (d < e) | (c == 3);
		System.out.println("bool => " + bool);
		
		////////////////////////////////////////////////////////
				
		System.out.println("\n~~~~~~~~~~~~~~~~ 퀴즈1 ~~~~~~~~~~~~~~~~~~\n");
		
		int i = 1;
		int j = i++;
		
		if ((i > ++j) & (i++ == j)) {
			i = i + j;
		}
		
		System.out.println("i=" + 1);
		
		if((i > ++j) && (i++ == j)) {
			i = i + j;
		}
		
		System.out.println("i=" + i);
		
		System.out.println("\n~~~~~~~~~~~~~~~~ 퀴즈2 ~~~~~~~~~~~~~~~~~~\n");
		
		int m1 = 0;
		int n1 = 1;
		
		if((m1++ == 0) || (n1++ == 2)) {
			m1 = 42;
		}
		System.out.println("m1 => " + m1 + ", n1 => " +n1); 
		
		m1 = 0;
		n1 = 1;
		if((m1++ == 0) | (n1++ == 2)) {
			m1 = 42;
		}
		
		System.out.println("m1 => " + m1 + ", n1 => " + n1);
		
		////////////////////////////////////////////////////////
				
		System.out.println("\n==== 8. 할당연산자(연산후 대입 연산자) += -= *= /= %= ====");
		
		int no = 1;
		no += 3;
		System.out.println("no = " + no);
		
		no -= 2;
		System.out.println("no = " + no);
		
		no *= 5;
		System.out.println("no = " + no);
		
		no /= 4;
		System.out.println("no = " + no);
		
		no %= 3;
		System.out.println("no = " + no);
		
		System.out.println("\n==== 8. 할당연산자(연산후 대입 연산자) += -= *= /= %= ====");
		
		int num1 = 50, num2 = 60;
		int num3 = (num1 > num2) ? num1 : num2;
		System.out.println("num3 => " + num3);
	}

}
