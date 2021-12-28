package my.day17.c.collection;

import java.util.*;

public class HashMap_main_4 {

/*
   === Map 계열 ===
   1. HashMap 과 Hashtable 이 있다.
   2. Map 계열은 List 계열(Vector, ArrayList, LinkedList)처럼 index 가 사용되어 저장되는 것이 아니라, 
      Map 계열은 key값과 value값을 쌍으로 사용하여 저장하는데  
      데이터 저장시 사용되는 메소드는 put(String key, Object value)메소드를 사용한다.
      이때 key값은 반드시 고유한 값을 가져야 하고, value값은 중복된 값이 와도 괜찮다.
   3. Map 계열에 저장된 key값들은 순서와는 상관없이 저장된다.  
   4. Map 계열에 저장된 value값을 추출하려면 key를 이용해서 가져오는데 
      value 값의 추출은 get(String key) 메소드를 사용한다. 
*/

	public static void main(String[] args) {
		
	//	HashMap<String, Member> mbrMap = new HashMap<String, Member>();
	//	또는 	
	//	HashMap<String, Member> mbrMap = new HashMap<>();
	//	또는 		
		Map<String, Member> mbrMap = new HashMap<>();
	//  Map<K, V>
		
		mbrMap.put("youjs", new Member("youjs","qwer1234$","유재석"));
		// mbrMap 이라는 HashMap 저장소에 new Member("youjs","qwer1234$","유재석")을 저장하는데 
	    // 저장된  new Member("youjs","qwer1234$","유재석")을 찾고자 할때는 
	    // "youjs" 만 넣어주면 new Member("youjs","qwer1234$","유재석") 이 나오도록 저장하겠다는 말이다. 
		
		mbrMap.put("eom", new Member("eom","qwer1234$","엄정화"));
		mbrMap.put("kanghd", new Member("kanghd","qwer1234$","강호동"));
		mbrMap.put("leess", new Member("leess","qwer1234$","이순신"));
		mbrMap.put("kimth", new Member("kimth","qwer1234$","김태희"));
		mbrMap.put("kangkc", new Member("kangkc","qwer1234$","강감찬"));	
		
		// == mbrMap 에 저장되어진 Member 객체들중에 key 가 "leess" 인 value값(지금은 Member)을 추출해서
	    //    추출된 회원(Member)의 정보를 출력하세요.
		String id = "leess";
	//	String id = "superman";
		
		Member mbr = mbrMap.get(id);// ArrayList는 add,get Map은 put,get
		
		if(mbr != null)
		   mbr.infoPrint();
		else
			System.out.println("검색하신 "+id+"에 해당하는 회원이 없습니다.");
		/*		
		== 이순신님의 회원정보 == 
			1.아이디 : leess
			2.비밀번호 : qwer1234$
			3.성명: 이순신
 		*/
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Member seolh_mbr_1 = new Member("seolh","qwer1234$","설현");
		Member seolh_mbr_2 = seolh_mbr_1;
		
		mbrMap.put("seolh_1", seolh_mbr_1);
		mbrMap.put("seolh_2", seolh_mbr_1);
		
		Member seolh_1 = mbrMap.get("seolh_1");
		if(seolh_1 != null) {
			seolh_1.infoPrint();
		}
		
		Member seolh_2 = mbrMap.get("seolh_2");
		if(seolh_2 != null) {
			seolh_2.infoPrint();
		}
		
		///////////////////////////////////////////////////////////////////
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Member suji_mbr = new Member("suji","qwer1234$","배수지");
		mbrMap.put("seolh_1", suji_mbr); // 덮어쓰기 되어짐
		
		seolh_1 = mbrMap.get("seolh_1");
		if(seolh_1 != null) {
			seolh_1.infoPrint();
		}
		
		seolh_2 = mbrMap.get("seolh_2");
		if(seolh_2 != null) {
			seolh_2.infoPrint();
		}
		
	}// end of main(String[] args)---------------------------------------------------------

}
