package my.day18.c.generic;

import java.util.*;

public class Bok_Box_wildCard_Employee {

	private List<Bok_Box_Anything<?>> anything_list = new ArrayList<>();
	
	public void register_anything(Bok_Box_Anything<?> item) {
		anything_list.add(item);
	}
	
	public List<Bok_Box_Anything<?>> get_anything(){
		return anything_list;
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	private List<Bok_Box_Anything<? super Bok_Employee_Child_Executive>> executive_list = new ArrayList<>();
	
	public void register_executive(Bok_Box_Anything<? super Bok_Employee_Child_Executive> item) {
		executive_list.add(item);
	}
	
	public List<Bok_Box_Anything<? super Bok_Employee_Child_Executive>> get_executive(){
		return executive_list;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private List<Bok_Box_Anything<? extends Bok_Employee_Child_Plain>> plain_list = new ArrayList<>();
	
	public void register_plain(Bok_Box_Anything<? extends Bok_Employee_Child_Plain> item) {
		plain_list.add(item);
	}
	
	public List<Bok_Box_Anything<? extends Bok_Employee_Child_Plain>> get_plain(){
		return plain_list;
	} 
}
