package smart_java.linkedlist;
import java.util.*;

public class LinkedListClass {
	
	public void toPrint(ListIterator<String> it) {
		System.out.print("[ ");
		while(it.hasNext()) 
			System.out.print(it.next()+" ");
		System.out.println("]");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedListClass llc = new LinkedListClass();
		LinkedList<String> list = new LinkedList<String>();
		
		System.out.println("[LinkedList 자료 구현]");
		list.add(new String("AAA"));	// 추가하기
		list.add(new String("BBB"));
		list.add(new String("CCC"));
		list.add(new String("DDD"));
		llc.toPrint(list.listIterator());

		System.out.print("add(2,EEE) : ");		// 끼워넣기
		list.add(2, new String("EEE"));
		llc.toPrint(list.listIterator());
		
		System.out.print("set(1,ZZZ) : ");		// 수정하기
		list.set(1, new String("ZZZ"));
		llc.toPrint(list.listIterator());

		System.out.print("remove(ZZZ) : ");		// 삭제하기
		list.remove("ZZZ");
		llc.toPrint(list.listIterator());
		
		System.out.println("contains(EEE) : "+list.contains("EEE"));
		System.out.println("get(3) : "+list.get(3));
		System.out.println("indexOf(CCC) : "+list.indexOf("CCC"));
		System.out.println("size() : "+list.size());
		System.out.println("isEmpty() : " +list.isEmpty());
		
		System.out.println("\n[Queue 자료 구조 구현]");
		
		LinkedList<String> qlist = new LinkedList<String>();
		
		qlist.offer(new String("AAA"));	
		qlist.offer(new String("BBB"));
		qlist.offer(new String("CCC"));
		qlist.offer(new String("DDD"));
		llc.toPrint(qlist.listIterator());
		
		System.out.println("peek() : "+qlist.peek());
		System.out.println("peek() : "+qlist.peek());
		llc.toPrint(qlist.listIterator());
		System.out.println("poll() : "+qlist.poll());
		llc.toPrint(qlist.listIterator());
		System.out.println("poll() : "+qlist.poll());
		llc.toPrint(qlist.listIterator());
		System.out.println("poll() : "+qlist.poll());
		llc.toPrint(qlist.listIterator());
		System.out.println("poll() : "+qlist.poll());
		llc.toPrint(qlist.listIterator());
		
	}

}
