package smart;

import java.util.Scanner;

public class ArrayString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		System.out.println("[과일]");
		String[] fruit = new String[] { "apple", "banana", "melon" };
		for( int i=0; i<fruit.length; i++) {
			System.out.print(fruit[i]+" ");
		}
		System.out.println();
		
		System.out.println("[책]");
		String[] book = new String[3];
		book[0] = "수학책";
		book[1] = "과학책";
		book[2] = "소설책";
		for( int i=0; i<book.length; i++) {
			System.out.print(book[i]+" ");
		}
		System.out.println();

		System.out.println("[드라마]");
		String[] drama = new String[3];
		drama[0] = new String("sunshine");
		drama[1] = new String("doctor");
		drama[2] = new String("things");
		for( int i=0; i<drama.length; i++) {
			System.out.print(drama[i]+" ");
		}
		System.out.println();
		
		// 문자열 입력받아 배열에 넣기
		String[] name = new String[5]; 
		for( int i=0; i<name.length; i++) {
			System.out.print((i+1)+"번째 이름을 입력하세요 : ");
			name[i] = s.nextLine();
		}
		System.out.println("[입력한 이름]");
		for( int i=0; i<name.length; i++) {
			System.out.print(name[i]+" ");
		}
		System.out.println();
	}
	
}
