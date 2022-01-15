package my.day18.b.generic;

public class Bok_Employee_Child_Plain extends Bok_Employee{

	int salary;
	
	public Bok_Employee_Child_Plain() {}
	
	public Bok_Employee_Child_Plain(String userid, String passwd, String name, String jik, int salary) {
		super(userid, passwd, name, jik);
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "== 일반직원 ==\n" +
			   super.toString() +
			   "5.연봉 : " + salary + "만원\n";
	}
	
}
