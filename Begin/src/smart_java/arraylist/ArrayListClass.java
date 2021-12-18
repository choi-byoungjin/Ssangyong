package smart_java.arraylist;
import java.util.*;

public class ArrayListClass {
	int x;
	ArrayListClass() {	// 생성자 메소드
		x = 10;
	}
	public String toString() {   // 메소드 오버라이딩
		return "[ArrayListClass] x= " +x; 
	}
	public void toPrint(Iterator<String> it) {
		System.out.print("[ ");
		while(it.hasNext()) 
			System.out.print(it.next()+" ");
		System.out.println("]");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayListClass als = new ArrayListClass();
		
		System.out.println("[모든 객체를 저장할 수 있는 ArrayList]");
		ArrayList list = new ArrayList();
		//List list = new ArrayList();
		
		list.add(new String("AAAA"));
		list.add(123);
		list.add(100.45);
		list.add(als);
		
		System.out.println("< 반복자 없이 출력 >");
		for(int i=0; i<list.size(); i++) {   // list.size() 는 4 
			System.out.println(list.get(i));
		}
		
		System.out.println("\n< Iterator 반복자 사용 >");
		Iterator it = list.iterator();
		//ListIterator it = list.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		System.out.println("\n[String 객체만 저장할 수 있는 ArrayList]");
		ArrayList<String> list1 = new ArrayList<String>();
		
		list1.add(new String("AA"));  // 추가
		list1.add(new String("BB"));
		list1.add(new String("DD"));
		list1.add(new String("EE"));
		//list1.add(123);
		als.toPrint(list1.iterator());
		
		System.out.println("add(2,CC)");  // 끼워넣기
		list1.add(2, new String("CC"));
		als.toPrint(list1.iterator());
		
		System.out.println("set(1,ZZ)");  // 수정하기
		list1.set(1, new String("ZZ"));
		als.toPrint(list1.iterator());

		System.out.println("remove(ZZ)");  // 삭제하기
		list1.remove("ZZ");
		als.toPrint(list1.iterator());
		
		System.out.println("contains(EE) : "+list1.contains("EE"));
		System.out.println("get(3) : "+list1.get(3));
		System.out.println("indexOf(CC) : "+list1.indexOf("CC"));
		System.out.println("size() : "+list1.size());
		System.out.println("isEmpty() : " +list1.isEmpty());
		
		Object[] arr = list1.toArray();
		System.out.print("toArray() : ");
		for(int i=0; i< arr.length; i++)
			System.out.print(arr[i]+" ");
		System.out.println();
		
		System.out.println("[ListIterator] Previous");
		ListIterator<String> lit = list1.listIterator();
		while(lit.hasNext()) lit.next();
		while(lit.hasPrevious()) {
			System.out.print(lit.previous()+" ");
		}
		System.out.println();
		
		list1.clear();
		System.out.println("clear() + isEmpty() : " +list1.isEmpty());
		
	}

}
