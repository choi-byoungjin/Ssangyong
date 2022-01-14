package my.day18.a.generic;

import java.util.*;

public class Bok_Box_Anything<T> {

	private List<T> list = new ArrayList<T>();
	
	public List<T> getList(){
		return list;
	}
	
	public void register(T item) {
		list.add(item);
	}
	
}
