package my.day18.c.generic;

public class Employee_Child_Plain_Child extends Employee_Child_Plain{

	int month; // 계약기간개월수

	public Employee_Child_Plain_Child() {}
	
	public Employee_Child_Plain_Child(String userid, String passwd, String name, String jik, int salary, int month) {// 부모클래스에서 파라미터가 저장되어있음
		super(userid, passwd, name, jik, salary); // 
		this.month = month;
	}
	
	@Override
	public String toString() {
		
		return super.toString() +
			   "6.계약개월수 : "+ month +"개월\n";
	}
	
}
