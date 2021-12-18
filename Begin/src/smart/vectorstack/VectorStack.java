package smart.vectorstack;
import java.util.*;

public class VectorStack {
	
	public void toPrint(ListIterator<String> it) {
		System.out.print("[ ");
		while(it.hasNext()) 
			System.out.print(it.next()+" ");
		System.out.println("]");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VectorStack vs = new VectorStack();
		Vector<String> v = new Vector<String>();
		
		System.out.println("[Vector 자료 구현]");
		v.add(new String("AAA"));	// 추가하기
		v.add(new String("BBB"));
		v.add(new String("CCC"));
		v.add(new String("DDD"));
		vs.toPrint(v.listIterator());

		System.out.print("add(2,EEE) : ");		// 끼워넣기
		v.add(2, new String("EEE"));
		vs.toPrint(v.listIterator());
		
		System.out.print("set(1,ZZZ) : ");		// 수정하기
		v.set(1, new String("ZZZ"));
		vs.toPrint(v.listIterator());

		System.out.print("remove(ZZZ) : ");		// 삭제하기
		v.remove("ZZZ");
		vs.toPrint(v.listIterator());
		
		System.out.println("contains(EEE) : "+v.contains("EEE"));
		System.out.println("get(3) : "+v.get(3));
		System.out.println("indexOf(CCC) : "+v.indexOf("CCC"));
		System.out.println("isEmpty() : " +v.isEmpty());
		
		System.out.println("\n[Vector 용량]");
		Vector v1 = v;
		Vector v2 = new Vector(5);
		Vector v3 = new Vector(3,2);
		System.out.println("v1 : capacity() : "+v1.capacity());
		System.out.println("v1 : size() : "+v1.size());
		v1.trimToSize();
		System.out.println("v1 : trimToSize() : capacity() : "+v1.capacity());
		System.out.println("v1 : trimToSize() : size() : "+v1.size());

		System.out.println("v2 : capacity() : "+v2.capacity());
		v2.setSize(25);
		System.out.println("v2 : setSize(25) : capacity() : "+v2.capacity());
		
		System.out.println("v3 : capacity() : "+v3.capacity());
		System.out.println("v3 : size() : "+v3.size());
		v3.add("111");
		v3.add("222");
		v3.add("333");
		v3.add("444"); // v3 용량이 차서 용량 2개를 추가하여 5개가 됨.
		System.out.println("v3 : capacity() : "+v3.capacity());
		System.out.println("v3 : size() : "+v3.size());
		
		System.out.println("\n[Stack 자료 구조 구현]");
		Stack<String> st = new Stack<String>();
		st.push(new String("AAA"));
		st.push(new String("BBB"));
		st.push(new String("CCC"));
		st.push(new String("DDD"));
		vs.toPrint(st.listIterator());
		
		System.out.println("peek() : "+st.peek());
		System.out.println("peek() : "+st.peek());
		
		vs.toPrint(st.listIterator());
		System.out.println("pop() : "+st.pop());
		vs.toPrint(st.listIterator());
		System.out.println("pop() : "+st.pop());
		vs.toPrint(st.listIterator());
		System.out.println("pop() : "+st.pop());
		vs.toPrint(st.listIterator());
		System.out.println("pop() : "+st.pop());
		vs.toPrint(st.listIterator());
		
	}

}
