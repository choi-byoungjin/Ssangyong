package my.day10.a.array;

import java.util.Iterator;

public class SpaceDelete_main1 {

	public static void main(String[] args) {
		// === 입사문제 : 문자열 중 공백 제거하기 === //
		
		String str = "  korea   seou l 쌍용 강북 교육센터  ";
		System.out.println(str);

		char[] chArr = str.toCharArray();	// 배열길이나 문자열길이나 같다
		
	/*
       --------------------------------------------------------------------------------------------------------------------------
       |' '|' '|'k'|'o'|'r'|'e'|'a'|' '|' '|' '|'s'|'e'|'o'|'u'|' '|'l'|' '|'쌍'|'용'|' '|'강'|'북'|' '|'교'|'육'|'센'|'터'|' '|' '|         
       --------------------------------------------------------------------------------------------------------------------------    
     */
		str = "";
		for (int i = 0; i < chArr.length; i++) {
			if(chArr[i] != ' ') {
				str += chArr[i];
			}
		}
		
		System.out.println(str);
		System.out.println("글자길이" + str.length());
		
	}//koreaseoul쌍용강북교육센터"

}
