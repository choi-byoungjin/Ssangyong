package my.day18.b.generic;

import java.util.*;

public class Bok_Box_Employee<T extends Bok_Employee> {
		
	private List<T> list = new ArrayList<T>();
	
	public List<T> getList(){
		return list;
	}
	
	public void register(T item) {
		list.add(item);
	}
	
}
