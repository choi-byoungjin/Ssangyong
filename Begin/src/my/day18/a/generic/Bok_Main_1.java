package my.day18.a.generic;

import java.util.*;

public class Bok_Main_1 {

	public static void main(String[] args) {
		
		Bok_Box_Anything<Bok_Employee> bok_box_employee = new Bok_Box_Anything<Bok_Employee>();
		bok_box_employee.register(new Bok_Employee("leess", "qwer", "이순신", "이사"));
		bok_box_employee.register(new Bok_Employee("eomjh", "qwer", "엄정화", "부장"));
		bok_box_employee.register(new Bok_Employee("hongkd", "qwer", "홍길동", "대리"));
		
		List<Bok_Employee> empList = bok_box_employee.getList();
		
		for (Bok_Employee emp : empList) {
			System.out.println(emp.toString());
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Bok_Box_Anything<String> bok_box_string = new Bok_Box_Anything<>();
		bok_box_string.register("한석규");
		bok_box_string.register("두석규");
		bok_box_string.register("세석규");
		
		List<String> strList = bok_box_string.getList();
		
		for (String str : strList) {
			System.out.println(str.toString());
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Bok_Box_Anything<Integer> bok_box_int = new Bok_Box_Anything<>();
		bok_box_int.register(10);
		bok_box_int.register(20);
		bok_box_int.register(30);
		
		List<Integer> intg_list = bok_box_int.getList();
		
		for (Integer intg : intg_list) {
			System.out.println(intg.toString());
		}
	}
}
