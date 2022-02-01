package my.day10.a.array;

public class BokSpaceDelete_main1 {

	public static void main(String[] args) {
		
		String str = "  korea   seou  l 쌍용 강북 교육센터   ";
		System.out.println(str);
		
		char[] chArr = str.toCharArray();
		
		str = "";
		for(int i = 0; i < chArr.length; i++) {
			if(chArr[i] != ' ') {
				str += chArr[i];
			}
		}
		
		System.out.println(str);
		System.out.println("글자길이" + str.length());

	}

}
