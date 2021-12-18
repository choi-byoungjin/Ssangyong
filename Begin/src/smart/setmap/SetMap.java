package smart.setmap;
import java.util.*;

public class SetMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[HashSet 클래스 실습]");
		Set<String> s = new HashSet<String>();
		s.add(new String("Java"));
		s.add(new String("Program"));
		s.add(new String("Eclipse"));
		s.add(new String("Java"));  // 중복 저장 안됨
		
		System.out.println("size() : "+s.size());
		Iterator<String> it = s.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		s.remove("Eclipse");
		System.out.println("remove(Eclipse), size() : "+s.size());
		it = s.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		System.out.println("contains(Java) : "+s.contains("Java"));
		System.out.println("isEmpty() : " +s.isEmpty());
		
		
		System.out.println("\n[HashMap 클래스 실습]");
		Map<Character, String> m = new HashMap<Character, String>();
		m.put('A', "Apple");
		m.put('B', "Banana");
		m.put('M', "Melon");
		m.put('O', "Orange");
		System.out.println("size() : "+ m.size());
		
		Set<Character> set = m.keySet();
		Iterator<Character> sit = set.iterator();
		while(sit.hasNext()) {
			Character key = sit.next();
			String value = m.get(key);
			System.out.println("K="+key+" V="+value);
		}
		
		m.remove('M');
		System.out.println("remove(M), size() : "+m.size());
		System.out.println("containsKey(B) : "+m.containsKey('B'));
		System.out.println("containsValue(Orange) : "+m.containsValue("Orange"));
		System.out.println("isEmpty() : " +s.isEmpty());
		
	}

}
