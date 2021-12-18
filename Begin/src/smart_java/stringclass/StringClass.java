package smart_java.stringclass;

public class StringClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("== String 클래스 ==");

		System.out.println("\n[빈 문자열과 NULL]");
		String s1;
		String s2 = "";
		String s3 = null;

		//if( s1 == null ) System.out.println("String s1; : true"); // s1은 초기화가 되지 않아 사용할 수 없음
 
		System.out.println("빈문자열 비교 : "+ (s2==""));
		System.out.println("NULL 문자 비교 : "+ (s3==null));

		System.out.println("\n[문자열 상수를 직접지정하여 객체 생성]");
		String s4 = "AAA";
		String s5 = "AAA";
		System.out.println("s4 == s5 : "+(s4 == s5));
		System.out.println("s4 == 'AAA' : " +(s4=="AAA"));
		
		System.out.println("\n[생성자 메소드를 이용하여 객체 생성]");
		String s6 = new String("AAA");
		String s7 = new String("AAA");
		System.out.println("s6 == s7 : "+(s6 == s7));
		System.out.println("s6 == 'AAA' : " +(s6=="AAA"));
		System.out.println("s6.equlas(s7) : "+s6.equals(s7));
		System.out.println("s6.equlas('AAA') : "+s6.equals("AAA"));
		
		System.out.println("\n[주요 메소드 사용]");
		String s8 = " Java Program ";
		System.out.println("length() : "+s8.length());
		System.out.println("subString(6) : "+s8.substring(6));
		System.out.println("concat('Coding') : " + s8.concat("Coding"));
		System.out.println("replace('a','A') : " + s8.replace('a', 'A'));
		System.out.println("toLowerCase() : "+s8.toLowerCase());
		System.out.println("toUpperCase() : "+s8.toUpperCase());
		System.out.println("charAt(6) : "+s8.charAt(6));
		System.out.println("indexOf('Pro') : "+s8.indexOf("Pro"));
		System.out.println("lastIndexOf('Pro') : "+s8.lastIndexOf("Pro"));
		System.out.println("trim() : "+s8.trim());

		System.out.println("\n== StringBuffer 클래스 ==");
		StringBuffer sb = new StringBuffer("Java Programming");
		System.out.println("length() : "+sb.length());
		System.out.println("capacity() : "+sb.capacity());
		System.out.println("append(' Stduy') : "+sb.append(" Study"));
		System.out.println("insert(5,'Application ') : "+sb.insert(5, "Application "));
		System.out.println("toString() : "+sb.toString());
		System.out.println("delete(17,28) : "+sb.delete(17,28));
		System.out.println("replcae(0,4,'자바') : "+sb.replace(0, 4, "자바"));
		System.out.println("reverse() : "+sb.reverse());

	}

}
