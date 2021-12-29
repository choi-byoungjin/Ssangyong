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
			
			System.out.println("######################");
			
		}// end of for-------------------------
		/*	
			1.아이디: superman
			2.비밀번호: 1234
			3.성명: 슈퍼맨
			4.직급: 부장
			
			1.아이디: wonderwoman
			2.비밀번호: 0070
			3.성명: 원더우맨
			4.직급: 과장
			
			1.아이디: batman
			2.비밀번호: 5678
			3.성명: 배트맨
			4.직급: 사원
			
			#################
			한석규
			두석규
			세석규
			#################
			10
			20
			30
			#################
		*/	
		
		///////////////////////////////////////////////////////////////////////////////////

		System.out.println("\n~~~ 와일드 카드의 하한제한 하기. T 와 그 조상클래스들만 사용가능한 경우 ~~~\n");
		
		Employee_Child_Executive executive_emp = new Employee_Child_Executive("leess","1234","이순신","사장","1-01");
		Employee employee = new Employee("eomjh","5678","엄정화","부장");
		
		Box_Anything<Employee_Child_Executive> box_executive_emp = new Box_Anything<>();
		box_executive_emp.register(executive_emp);
		
		Box_Anything<Employee> box_emp = new Box_Anything<>();
		box_emp.register(executive_emp);
		box_emp.register(employee);
		
		box.register_executive(box_executive_emp);
		box.register_executive(box_emp);
		
		Employee_Child_Plain plain_emp = new Employee_Child_Plain("hongkd","3456","홍길동","사원",4000);
		Box_Anything<Employee_Child_Plain> box_plain = new Box_Anything<>();
		box_plain.register(plain_emp);
		
	//	box.register_executive(box_plain); // 오류발생
		/*	
		box.register_executive(Box_Anything<? super Employee_Child_Executive> item) 이므로
		파라미터가 Employee_Child_Executive 을 포함한 Employee_Child_Executive 의 조상클래스들만 
		입력이 되어지므로 Employee_Child_Plain 을 형태를 띄는 box_plain 은 입력이 불가하기 때문이다.
		
	*/	
		
		List<Box_Anything<? super Employee_Child_Executive>> executive_super_list = box.get_executive();
		
		for(Box_Anything<? super Employee_Child_Executive> b : executive_super_list) {
			
			List<?> list = b.getList();
			for(Object obj : list) {
				System.out.println(obj);
			}// end of for---------------------
			
		}// end of for---------------------
		
		/*
			== 임직원 ==
			1.아이디: leess
			2.비밀번호: 1234
			3.성명: 이순신
			4.직급: 사장
			5.법인카드번호 : 1-01
			
			== 임직원 ==
			1.아이디: leess
			2.비밀번호: 1234
			3.성명: 이순신
			4.직급: 사장
			5.법인카드번호 : 1-01
			
			1.아이디: eomjh
			2.비밀번호: 5678
			3.성명: 엄정화
			4.직급: 부장
		
		*/
		
		///////////////////////////////////////////////////////////////////////////////////
		
		System.out.println("\n~~~ 와일드 카드의 상한제한 하기. T 와 그 자손클래스들만 사용가능한 경우 ~~~\n"); 
		
		Employee_Child_Plain_Child  plain_child_emp = new Employee_Child_Plain_Child("kangkc","1237","강감찬","사원",3600,24);
		Box_Anything<Employee_Child_Plain_Child> box_plain_child = new Box_Anything<>();
		box_plain_child.register(plain_child_emp);
		
		box.register_plain(box_plain);
		box.register_plain(box_plain_child);
		
	//	box.register_plain(box_emp);  // !!!!! 오류발생 !!!!!!
	/*	
		register_plain(Box_Anything<? extends Employee_Child_Plain> item) 이므로
		파라미터가 Employee_Child_Plain 를 포함한 자식클래스 까지만 입력이 가능한데
		box_emp 는 Box_Anything<Employee> 인데 이것은 Employee_Child_Plain 의 부모클래스 이므로
		상한제한에 걸려서 입력이 불가하다.  	
	*/			

		List<Box_Anything<? extends Employee_Child_Plain>> child_plain_list = box.get_plain();
		
		for(Box_Anything<? extends Employee_Child_Plain> b : child_plain_list) {
			
			List<Employee_Child_Plain> list = (List<Employee_Child_Plain>) b.getList();
			
			for(Employee_Child_Plain plain : list) {
				System.out.println(plain);
			}// end of for-----------------
			
		}// end of for----------------------
		
		/*
		 	== 일반직원 ==
			1.아이디: hongkd
			2.비밀번호: 3456
			3.성명: 홍길동
			4.직급: 사원
			5.연봉 : 4000만원
			
			== 일반직원 ==
			1.아이디: kangkc
			2.비밀번호: 1237
			3.성명: 강감찬
			4.직급: 사원
			5.연봉 : 3600만원
			6.계약개월수 : 24개월 
		 */
		
	}// end of main(String[] args)------------------------------------
	
}
