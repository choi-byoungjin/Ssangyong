package my.day03.b.operator;

import java.util.Iterator;

public class OperatorMain {

	public static void main(String[] args) {
		
		// ~~~~~~~~~~~~ 연산자 ~~~~~~~~~~~~~~ //
		
		// #### 1. 산술연산자 ####
		// 		+ - * / %(나머지)		<<	>>	>>>
		
		System.out.println("=== 1.산술연산자 + - * / %(나머지) << >> >>>");
		
		int n = 10;
		System.out.println("n+3 = " + (n+3)); 		  		  // n+3 = 13
		System.out.println("n-3 = " + (n-3)); 		  		  // n-3 = 7
		System.out.println("n*3 = " + (n*3)); 				  // n*3 = 30
		System.out.println("n/3 = " + (n/3)); 		  		  // n/3 = 3
		System.out.println("(double)n+3 = " + ((double)n/3)); // n/3 = 3.333333333333
		System.out.println("n%3 = " + (n%3)); 				  // n%3 = 1	10을 3으로 나누었을 때의 나머지
		
		System.out.println("n<<3 = " + (n<<3)); 		      // n<<3 = 80
		/*
		 * << 는 왼족 쉬프트 연산자
		 * 
		 * int 는 4byte 이므로 32bit 로 구성된다.
		 * 10<<3은 정수 10을 이진수	00000000 00000000 00000000 00001010
		 * 왼쪽방향으로 3개를 밀어버린다.
		 * 그러면 000000 00000000 00000000 00001010___ 이 되는데
		 * 그런 다음에 _ 자리에 0으로 채운다.
		 * 즉, 000000 00000000 00000000 00001010000 이 된다.
		 * 이진수 00000000 00000000 00000000 001010000 이 되는데 이것을 십진수로 나타내면
		 * 1*(2의6승) + 1*(2의4승)
		 * 64 + 16 = 80 이 된다.
		 * 이것은 10 * (2의 3승) ==> 10*(2의 3승) 와 같은 것이 된다. 그러므로 80이다.
		 * 
		 */
		
		System.out.println("n>>2 = " + (n>>2));
		/*
		 * >> 는 오른쪽 쉬프트 연산자
		 * int 는 4byte 이므로 32bit 로 구성된다.
		 * 10>>2은 정수 10을 이진수	00000000 00000000 00000000 00001010
		 * 왼쪽방향으로 2개를 밀어버린다.
		 * 그러면 __000000 00000000 00000000 000010 이 되는데
		 * 그런 다음에 _ 자리에 정수 10의 부호비트(가장 왼쪽 비트1개, 0이면 양수이고, 1이면 음수를 말하는 것이다.)와 같은 값으로 채운다.
		 * 즉, 000000000 00000000 00000000 000010 이 된다.
		 * 이진수 00000000 00000000 00000000 000000010 이 되는데 이것을 십진수로 나타내면
		 * 1*(2의1승)
		 * 2 가 된다.
		 * 이것은 10 / (2의 2승) ==> 10/(2의 2승) 와 같은 것이 된다. 그러므로 2이다.
		 */
		
		
		System.out.println("n>>>3 = " + (n>>>3));
		/*
		 * >>> 는 오른쪽 쉬프트 연산자
		 * int 는 4byte 이므로 32bit 로 구성된다.
		 * 10>>>3은 정수 10을 이진수	00000000 00000000 00000000 00001010
		 * 오른쪽방향으로 3개를 밀어버린다.
		 * 그러면 ___00000000 00000000 00000000 00001 이 되는데
		 * 그런 다음에 _ 자리에 정수 0 으로 채운다.
		 * 즉, 00000000000 00000000 00000000 00001 이 된다.
		 * 이진수 00000000 00000000 00000000 000000001 이 되는데 이것을 십진수로 나타내면
		 * 1*(2의0승)
		 * 1 이 된다.
		 */
		
		n= -10;
		System.out.println("~~~~ n이 -10인 경우 ~~~~");
		/*
		 * >>> -10을 bit로 나타내어 주는 방법 <<<
		 * 
		 * int 는 4byte 이므로 32bit 로 구성된다.
          컴퓨터의 음수표현 방법은 2의 보수 표현 방법을 사용한다.
          2의 보수란 1의 보수(0은 1로, 1은 0로 바꿔주는 것)로 변환 후 +1 해주는 것이다.
          
          -10 을 이진수로 표현하면 다음과 같다.
          우선 10 을 이진수로 나타내면  00000000 00000000 00000000 00001010
          이것을 1의 보수로 만든다. 즉, 11111111 11111111 11111111 11110101 이다.
          여기에 +1 을 하면 		 11111111 11111111 11111111 11110110 이 된다.
          
          11111111 11111111 11111111 11110110 을 십진수로 나타내어 보겠습니다.
          
          첫번째 bit는 부호비트로 사용되어지는데
          0 은 +임(양수)을 의미하고,
          1 은 -임(음수)을 의미한다.
          
          첫번째 bit가 1인 경우, 즉 음수를 뜻할 경우에는 아래와 같이 구한다.
          똑같은 1이 나오는 것 중에서 마지막 1만 취하고, 여기에다가 그 나머지를 끝까지 취한다.
          10110
          첫번째 값만 -를 붙이고, 나머지 값은 + 로 연산을 하면 된다.
          -1*(2의4승) + 0*(2의3승) + 1*(2의2승) + 1*(2의1승) + 0(2의0승)
          -1*16 + 0 + 1*4 + 1*2 + 0
          -16 + 4 + 2 ==> -10
          
		 */
		System.out.println("n<<3 = " + (n<<3)); // -10 * (2의3승)
		
		/*
		 * << 는 왼쪽 쉬프트 연산자
		 * 
		 * int 는 4byte 이므로 32bit 로 구성된다.
		 * -10<<3은 정수 -10을 이진수 11111111 11111111 11111111 11110110 으로 나타내고 
		 * 왼쪽방향으로 3개를 밀어버린다.
		 * 그러면 11111 11111111 11111111 11110110___ 이 되는데
		 * 그런 다음에 _ 자리에 0으로 채운다.
		 * 즉, 11111 11111111 11111111 11110110000 이 된다.
		 * 이진수 11111111 11111111 11111111 10110000 이 되는데 이것을 십진수로 나타내면
		 * 음수 이므로
		 * 
		 * 10110000
		 * -1*(2의7승) + 1*(2의5승) + 1*(2의4승)
		 * -128 + 32 + 16 = -80
		 * 
		 */
		
		
		System.out.println("n>>2 = " + (n>>2));
		
		/*
		 * >> 는 오른쪽 쉬프트 연산자
		 * 
		 * int 는 4byte 이므로 32bit 로 구성된다.
		 * -10>>2 은 정수 -10 을 이진수 11111111 11111111 11111111 11110110 으로 나타내고 
		 * 오른쪽방향으로 2개를 밀어버린다.
		 * 그러면 __11111111 11111111 11111111 111101 이 되는데
		 * 그런 다음에 _ 자리에 정수 -10의 부호비트와 같은 값으로 채운다.
		 * 즉, 1 로 채운다.
		 * 그러면 1111111111 11111111 11111111 111101 이 된다.
		 * 이진수 11111111 11111111 11111111 11111101 이 되는데 이것을 십진수로 나타내면
		 * 음수 이므로
		 * 
		 * 101
		 * -1*(2의2승) + 1*(2의0승)
		 * -4 + 1 = -3
		 * 	
		 */
		
		
		System.out.println("n>>>3 = " + (n>>>3));
		
		/*
		 * int 는 4byte 이므로 32bit 로 구성된다.
		 * -10>>>3 은 정수 -10 을 이진수 11111111 11111111 11111111 11110110 으로 나타내고 
		 * 오른쪽방향으로 2개를 밀어버린다.
		 * 그러면 ___11111111 11111111 11111111 11110 이 되는데
		 * 그런 다음에 _ 자리에 0 값으로 채운다.
		 * 그러면 00011111111 11111111 11111111 11110 이 된다.
		 * 이진수 00011111 11111111 11111111 11111110 이 되는데 이것을 십진수로 나타내면
		 * 	
		 * 즉, 536870910 이 된다.
		 */
		
		// #### 2. 증감연산자 ####
		//		++  --
		
			System.out.println("\n=== 2.증감연산자  ++  -- ====");
			
		//  int a = 7;
		//  int b = 3;
		//  또는
			int a=7, b=3;
			System.out.println("a => " + a);	// a => 7
			
		//	a = a+1;
		//	또는
			a++;
			System.out.println("a => " + a);	// a => 8
			
			++a;
			System.out.println("a => " + a);	// a => 9
			
			System.out.println("b => " + b);	// b => 3
		//	b = b-1;
		//	또는
			b--;
			System.out.println("b => " + b);	// b => 2
			
			--b;
			System.out.println("b => " + b);	// b => 1
			
			// !!!! 꼭 암기하세요 !!!! //
			// 후위증감연산자(a++; b--;) 는 다른 연산을 다 마친 이후에 1씩 증감한다.
			// 전위증감연산자(++a; ++b;) 는 맨먼저 1씩 증감을 마친 이후에 다른 연산을 한다.
			int x=10, y=10;
			int z=++x; // ++x; x=x+1; z=11
			System.out.println("z => " + z); // z => 11
			System.out.println("x => " + x); // x => 11
			
			z=y++; // z=y; z=10; y++; y=y+1, y=11;
			System.out.println("z => " + z);
			System.out.println("y => " + y);
	}
}
