package my.day18.b.generic;

import java.util.List;

public class Bok_Main_2 {

	public static void main(String[] args) {
		
		Bok_Box_Employee<Bok_Employee_Child_Executive> bok_box_executive = new Bok_Box_Employee<Bok_Employee_Child_Executive>();
		bok_box_executive.register(new Bok_Employee_Child_Executive("hansk", "1234", "한석규", "사장", "1-01"));
		bok_box_executive.register(new Bok_Employee_Child_Executive("dusk", "5678", "두석규", "전무", "2-01"));
		bok_box_executive.register(new Bok_Employee_Child_Executive("sesk", "0070", "세석규", "상무", "3-01"));
		
		List<Bok_Employee_Child_Executive> bok_executive_list = bok_box_executive.getList();
		
		for (Bok_Employee_Child_Executive bok_executive : bok_executive_list) {
			System.out.println(bok_executive);
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Bok_Box_Employee<Bok_Employee_Child_Plain> bok_box_plain = new Bok_Box_Employee<Bok_Employee_Child_Plain>();
		bok_box_plain.register(new Bok_Employee_Child_Plain("leess", "qwer", "이순신", "부장", 8000));
		bok_box_plain.register(new Bok_Employee_Child_Plain("eomjh", "abcd", "엄정화", "과장", 6000));
		bok_box_plain.register(new Bok_Employee_Child_Plain("hongkd", "1234", "홍길동", "대리", 4000));
		
		List<Bok_Employee_Child_Plain> bok_plain_list = bok_box_plain.getList();
		
		for (Bok_Employee_Child_Plain bok_plain: bok_plain_list) {
			System.out.println(bok_plain);
		}
		
		System.out.println("\n==================================================\n");
		
		Bok_Box_Employee<Bok_Employee> bok_box_all = new Bok_Box_Employee<>();
		bok_box_all.register(new Bok_Employee("superman", "1234", "슈퍼맨", "부장"));
		bok_box_all.register(new Bok_Employee_Child_Executive("batman", "6789", "배트맨", "이사", "4-01"));
		bok_box_all.register(new Bok_Employee_Child_Plain("wonderwoman", "0070", "원더우먼", "과장", 9000));
		
		List<Bok_Employee> bok_emp_list = bok_box_all.getList();
		
		for (Bok_Employee bok_emp : bok_box_all.getList()) {
			System.out.println(bok_emp.toString());
		}
	}
	
}
