package my.day18.b.generic;

public class Employee_Child_Plain extends Employee{

	int salary; // 연봉

	public Employee_Child_Plain() {}
	
	public Employee_Child_Plain(String userid, String passwd, String name, String jik, int salary) {// 부모클래스에서 파라미터가 저장되어있음
		super(userid, passwd, name, jik);// 부모클래스의 생성자를 말하고 있다. // 호출만하면 this. 을 할 필요없음
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		
		return "== 일반직원 ==\n"+
			   super.toString() +
			   "5.연봉 : "+ salary +"만원\n";
	}
	
}
