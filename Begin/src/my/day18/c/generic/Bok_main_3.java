package my.day18.c.generic;

import java.util.List;

public class Bok_main_3 {

	public static void main(String[] args) {
		
		Bok_Box_wildCard_Employee box = new Bok_Box_wildCard_Employee();
		
		System.out.println("\n~~~~~~~~~~~ 모든 타입이 가능한 경우 ~~~~~~~~~~~\n");
		
		Bok_Box_Anything<Employee> emp = new Bok_Box_Anything<>();
		emp.register(new Employee("superman","1234","슈퍼맨","부장"));
		emp.register(new Employee("wonderwoman","0070","원더우먼","대리"));
		emp.register(new Employee("batman","5678","배트맨","사원"));
		
		Bok_Box_Anything<String> str = new Bok_Box_Anything<String>();
		str.register("한석규");
		str.register("두석규");
		str.register("세석규");
		
		Bok_Box_Anything<Integer> intg = new Bok_Box_Anything<Integer>();
		
		intg.register(10);
		intg.register(20);
		intg.register(30);
		
		box.register_anything(emp);
		box.register_anything(str);
		box.register_anything(intg);
		
		List<Bok_Box_Anything<?>> anything_list = box.get_anything();
		
		for (Bok_Box_Anything<?> b : anything_list) {
			List<?> list = b.getList();
			for (Object obj : list) {
				System.out.println(obj.toString());
			}
			
			System.out.println("####################################");
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println("\n~~~ 와일드 카드의 하한제한 하기. T 와 그 조상클래스들만 사용가능한 경우 ~~~\n");
		
		Bok_Employee_Child_Executive executive_emp = new Bok_Employee_Child_Executive("leess","1234","이순신","사장","1-01");
		Bok_Employee employee = new Bok_Employee("eomjh","5678","엄정화","부장");
		
		Bok_Box_Anything<Bok_Employee_Child_Executive> box_executive_emp = new Bok_Box_Anything<>();
		box_executive_emp.register(executive_emp);
		
		Bok_Box_Anything<Bok_Employee> box_emp = new Bok_Box_Anything<>();
		box_emp.register(executive_emp);
		box_emp.register(employee);
		
		box.register_executive(box_executive_emp);
		box.register_executive(box_emp);
		
		Bok_Employee_Child_Plain plain_emp = new Bok_Employee_Child_Plain("hongkd","3456","홍길동","사원",4000);
		Bok_Box_Anything<Bok_Employee_Child_Plain> box_plain = new Bok_Box_Anything<>();
		box_plain.register(plain_emp);
		
		List<Bok_Box_Anything<? super Bok_Employee_Child_Executive>> executive_super_list = box.get_executive();
		
		for (Bok_Box_Anything<? super Bok_Employee_Child_Executive> b : executive_super_list) {
			List<?> list = b.getList();
			for(Object obj : list) {
				System.out.println(obj);
			}
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		System.out.println("\n~~~ 와일드 카드의 상한제한 하기. T 와 그 자손클래스들만 사용가능한 경우 ~~~\n");
		
		Bok_Employee_Child_Plain_Child plain_child_emp = new Bok_Employee_Child_Plain_Child("kangkc","1237","강감찬","사원",3600,24);
		Bok_Box_Anything<Bok_Employee_Child_Plain_Child> box_plain_child = new Bok_Box_Anything<>();
		box_plain_child.register(plain_child_emp);
		box.register_plain(box_plain);
		box.register_plain(box_plain_child);
		
		List<Bok_Box_Anything<? extends Bok_Employee_Child_Plain>> child_plain_list = box.get_plain();
		
		for (Bok_Box_Anything<? extends Bok_Employee_Child_Plain> b : child_plain_list) {
			
			List<Bok_Employee_Child_Plain> list = (List<Bok_Employee_Child_Plain>) b.getList();
			
			for(Bok_Employee_Child_Plain plain : list) {
				System.out.println(plain);
			}	
		}
		
		
	}

}
