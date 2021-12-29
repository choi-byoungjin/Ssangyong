package my.day18.c.generic;

import java.util.List;

public class Main_3 {

	public static void main(String[] args) {
		
		Box_wildCard_Employee box = new Box_wildCard_Employee();
		
		////////////////////////////////////////////////////////////////////////
		
		System.out.println("\n~~~~~~~ 모든 타입이 가능한 경우 ~~~~~~~~~\n");
		
		Box_Anything<Employee> emp = new Box_Anything<>();
		emp.register(new Employee("superman","1234","슈퍼맨","부장")); // List에 넣고있다
		emp.register(new Employee("wonderwoman","0070","원더우맨","대리"));
		emp.register(new Employee("batman","5678","배트맨","사원"));
		
		
		Box_Anything<String> str = new Box_Anything<>();
		str.register("한석규");
		str.register("두석규");
		str.register("세석규");
		
		Box_Anything<Integer> intg = new Box_Anything<>();
		
		intg.register(10);
		intg.register(20);
		intg.register(30);
		
		box.register_anything(emp);
		box.register_anything(str);
		box.register_anything(intg);
		
		List<Box_Anything<?>> anything_list = box.get_anything();
		
		for(Box_Anything<?> b : anything_list) {
			List<?> list = b.getList(); // ?는 아무거나
			for(Object obj : list) {
				System.out.println(obj.toString());
			}// end of for-------------------------
		}// end of for-------------------------
		
		Box_wildCard_Employee<Employee_Child_Executive> box_executive = new Box_wildCard_Employee<Employee_Child_Executive>(); 
		box_executive.register(new Employee_Child_Executive("hansk", "1234", "한석규", "사장", "1-01"));
		box_executive.register(new Employee_Child_Executive("dusk", "5678", "두석규", "전무", "2-01"));
		box_executive.register(new Employee_Child_Executive("sesk", "0070", "세석규", "상무", "3-01"));
		
		List<Employee_Child_Executive> executive_list = box_executive.getList();
		
		for (Employee_Child_Executive executive : executive_list) {
			System.out.println(executive);
		}
		/*
		 	== 임직원 ==
			1.아이디: hansk
			2.비밀번호: 1234
			3.성명: 한석규
			4.직급: 사장
			5.법인카드번호 : 1-01
			
			== 임직원 ==
			1.아이디: dusk
			2.비밀번호: 5678
			3.성명: 두석규
			4.직급: 전무
			5.법인카드번호 : 2-01
			
			== 임직원 ==
			1.아이디: sesk
			2.비밀번호: 0070
			3.성명: 세석규
			4.직급: 상무
			5.법인카드번호 : 3-01
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Box_wildCard_Employee<Employee_Child_Plain> box_plain = new Box_wildCard_Employee<>();
		box_plain.register(new Employee_Child_Plain("leess", "qwer", "이순신", "부장", 8000));
		box_plain.register(new Employee_Child_Plain("eomjh", "abcd", "엄정화", "과장", 6000));
		box_plain.register(new Employee_Child_Plain("hongkd", "1234", "홍길동", "대리", 4000));
		
		List<Employee_Child_Plain> plain_list = box_plain.getList();
		
		for (Employee_Child_Plain plain : plain_list) {	
			System.out.println(plain);
		}
		/*
		 	== 일반직원 ==
			1.아이디: leess
			2.비밀번호: qwer
			3.성명: 이순신
			4.직급: 부장
			5.연봉 : 8000만원
			
			== 일반직원 ==
			1.아이디: eomjh
			2.비밀번호: abcd
			3.성명: 엄정화
			4.직급: 과장
			5.연봉 : 6000만원
			
			== 일반직원 ==
			1.아이디: hongkd
			2.비밀번호: 1234
			3.성명: 홍길동
			4.직급: 대리
			5.연봉 : 4000만원
		 */
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Box_wildCard_Employee<Employee> box_all = new Box_wildCard_Employee<>();
		
		box_all.register(new Employee("superman","1234","슈퍼맨","부장"));
		box_all.register(new Employee_Child_Executive("batman","6789","배트맨","이사","4-01"));
		box_all.register(new Employee_Child_Plain("wonderwoman","0070","원더우맨","과장",9000));
		
		List<Employee> emp_list = box_all.getList();
		
		for (Employee emp : emp_list) {
			System.out.println(emp.toString());
		}
		/*
			1.아이디: superman
			2.비밀번호: 1234
			3.성명: 슈퍼맨
			4.직급: 부장
			
			== 임직원 ==
			1.아이디: batman
			2.비밀번호: 6789
			3.성명: 배트맨
			4.직급: 이사
			5.법인카드번호 : 4-01
			
			== 일반직원 ==
			1.아이디: wonderwoman
			2.비밀번호: 0070
			3.성명: 원더우맨
			4.직급: 과장
			5.연봉 : 9000만원
		 */
		
	}// end of main(String[] args)------------------------------------
	
}
