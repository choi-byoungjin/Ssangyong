package my.day18.c.generic;

public class Bok_Employee_Child_Plain_Child extends Bok_Employee_Child_Plain{
	
	int month;
	
	public Bok_Employee_Child_Plain_Child() {}
	
	public Bok_Employee_Child_Plain_Child(String userid, String passwd, String name, String jik, int salary, int month) {
		super(userid,passwd,name,jik,salary);
		this.month = month;
	}

	@Override
	public String toString() {
		return super.toString() +
				"6.계약개월수 : "+ month +"개월\n";
	}

}
