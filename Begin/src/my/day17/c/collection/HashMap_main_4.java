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
		else {
			System.out.println(">> 해당하는 key 값이 존재하지 않습니다. <<");
		}
		
		
		seolh_2 = mbrMap.get("seolh_2");
		if(seolh_2 != null) {
			seolh_2.infoPrint();
		}
		else {
			System.out.println(">> 해당하는 key 값이 존재하지 않습니다. <<");
		}
		
		System.out.println("\n===========================================\n");

		
		
		// === mbrMap 에 저장되어진 모든 회원들의 정보를 출력해보세요 === //
		// 1. mbrMap 에 저장되어진 모든 key들을 읽어오는 첫번째 방법
		Set<String> keySets = mbrMap.keySet();
		
		for (String key : keySets) {
			System.out.println(key);
		}
		/*
			eom
			youjs
			kimth
			kangkc
			seolh_2
			kanghd
			leess
			seolh_1
		 */
		
		for (String key : keySets) {
			mbrMap.get(key).infoPrint();
		}
		
		
		System.out.println("\n===========================================\n");		
		
		
		
		
		// 2. mbrMap 에 저장되어진 모든 key들을 읽어오는 두번째 방법
		Set<String> keySets2 = mbrMap.keySet();
		
		// Iterator 는 저장소가 아니라 Collection 에 저장되어진 요소(Element)를 반복해서 읽어오는 용도로 쓰인다.
		// 그래서 Iterator 를 반복자 라고도 부른다.
		
		Iterator<String> itrator_key = keySets2.iterator(); // key를 읽어오는 용도
		
		while(itrator_key.hasNext()) { // 키가 있으면 true가 되어 가져온다.
		
			/*
			 itrator_key.hasNext() 는
			 itrator_key 에서 현재 itrator_key 가 가지고 있는 여러 String 데이터중
							 하나를 끄집어내서 존재하는지를 알아보는 것이다.
							 존재하면 true, 존재하지 않으면 false 를 리턴해준다.
							 그러므로 현재는 키의 갯수가 8개 이므로 8번 반복을 할 것이다.
			 */
			
			String key = itrator_key.next();
			/*
			 	실제로 키값인 String 값을 itrator_key 에서 끄집어 낸다.
			 	그러면 끄집어 낸 String 값은 더 이상 itrator_key 에는 남아 있지 않게 된다.
			 */
			
			System.out.println(key);
		}// end of while-------------------------------
		
		
		
		System.out.println("\n===================== 또 한 번 더 ======================\n");

		while(itrator_key.hasNext()) { // 키가 있으면 true가 되어 가져온다.
			String key = itrator_key.next();	
			System.out.println(key); // 위에서(145줄) key를 끄집어 내었기 때문에 itrator_key에는 key가 남아있지 않다.
			
		}// end of while-------------------------------
		
		
		
		System.out.println("\n===================== 다시 처음부터 해본다. ======================\n");

		itrator_key = keySets2.iterator(); // 다시 key 값을 넣어준다
		
		while(itrator_key.hasNext()) { 
			String key = itrator_key.next();	
			System.out.println(key); 
			
		}// end of while-------------------------------
		
		
		
		
		
		System.out.println("\n===========================================\n");

		itrator_key = keySets2.iterator();
		
		while(itrator_key.hasNext()) { // 키가 있으면 true가 되어 가져온다.
			String key = itrator_key.next();	
			mbrMap.get(key).infoPrint();;
			
		}// end of while-------------------------------
		
		
		
		
		System.out.println("\n=================키값이 \"leess\" 인 데이터를 삭제한 후==========================\n");
		
		// === mbrMap 에 저장되어진 데이터중 키값이 "leess" 인 데이터를 삭제하세요 === //
		mbrMap.remove("leess");
		
		for (String key : keySets) {
			mbrMap.get(key).infoPrint();
		}
		
		
		
		// === mbrMap 에 저장되어진 모든 데이터를 삭제하세요 === //
		
		System.out.println("\n~~~~ 모든 데이터를 삭제한 후 ~~~~\n");
		
		mbrMap.clear();
		
		for (String key : keySets) {
			mbrMap.get(key).infoPrint();
		}
		
		System.out.println("mbrMap.isEmpty() => " + mbrMap.isEmpty());
		// mbrMap.isEmpty() => true
		
	}// end of main(String[] args)---------------------------------------------------------

}














