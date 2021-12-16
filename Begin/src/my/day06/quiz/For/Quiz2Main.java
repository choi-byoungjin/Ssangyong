package my.day06.quiz.For;

public class Quiz2Main {

	public static void main(String[] args) {
		
//		String LargeSmall = "abcdefghijklmnofqrstuvwxyz";
//		String str = "";
//		
//		for (int i = 0; i < LargeSmall.length(); i++) {
//			
//			if(i%2 == 0) {
//				str += (char)(LargeSmall.charAt(i) - 32);
//				str += ",";
//			}
//			else {
//				str += LargeSmall.charAt(i);
//				str += ",";
//				continue;
//			}
//		}
//		System.out.println(str);
		
		int cnt = 'Z'-'A'+1;
		
		for(int i=0; i<cnt; i++) {
			char ch = (char)('A'+i);   // 65+0 => 'A'
			                           // 97+1 => 'b'
			                           // 97+2 => 'c'
			if(ch%2==0)	ch+=32; 
			
			String add = (i<cnt-1)?",":"";
			System.out.print(ch+add);
		}// end of for----------------------------
	}

}
