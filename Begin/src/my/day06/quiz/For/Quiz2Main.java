package my.day06.quiz.For;

public class Quiz2Main {

	public static void main(String[] args) {
		
		String LargeSmall = "abcdefghijklmnofqrstuvwxyz";
		String str = "";
		
		for (int i = 0; i < LargeSmall.length(); i++) {
			
			if(i%2 == 0) {
				str += (char)(LargeSmall.charAt(i) - 32);
				str += ",";
			}
			else {
				str += LargeSmall.charAt(i);
				str += ",";
				continue;
			}
		}
		System.out.println(str);
	}

}
