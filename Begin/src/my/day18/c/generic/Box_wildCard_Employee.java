package my.day18.c.generic;

import java.util.*;

// === 필드 생성시 타입에 제한이 없거나 제한이 있는 클래스 생성 === //

public class Box_wildCard_Employee { 
	
	private List<Box_Anything<?>> anything_list = new ArrayList<>(); // ?는 제한없음을 의미
	//						 <?> 은 아무거나 
	
	public void register_anything(Box_Anything<?> item) {
		anything_list.add(item);
	}
	
	public List<Box_Anything<?>> get_anything(){
		return anything_list;
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	private List<Box_Anything<? super Employee_Child_Executive>> executive_list = new ArrayList<>(); 
	//  하한제한
	//	? super Employee_Child_Executive 은
	// 	Employee_Child_Executive 부터 Employee_Child_Executive 의 조상(부모의 부모의 부모...)클래스 까지만 타입으로 들어올 수 있다.
	
	public void register_executive(Box_Anything<? super Employee_Child_Executive> item) { // return 타입은 필요하지 않다.
		executive_list.add(item);
	}
	
	public List<Box_Anything<? super Employee_Child_Executive>> get_executive(){
		return executive_list;
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	private List<Box_Anything<? extends Employee_Child_Plain>> plain_list = new ArrayList<>();
	//  상한제한
	//	? extends Employee_Child_Plain 은
	// 	Employee_Child_Plain을 포함한 Employee_Child_Plain의 자식클래스 까지만 타입으로 들어올 수 있다.
	
	public void register_plain(Box_Anything<? extends Employee_Child_Plain> item) {
		plain_list.add(item);
	}
	
	public List<Box_Anything<? extends Employee_Child_Plain>> get_plain(){
		return plain_list;
	}
	
}
