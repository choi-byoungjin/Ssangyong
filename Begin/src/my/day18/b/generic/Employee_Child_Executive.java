package my.day18.b.generic;

public class Employee_Child_Executive extends Employee {

	String cardNo; // 법인카드번호

	public Employee_Child_Executive() {}
	
	public Employee_Child_Executive(String userid, String passwd, String name, String jik, String cardNo) {// 부모클래스에서 파라미터가 저장되어있음
		super(userid,passwd,name,jik);// 부모클래스의 생성자를 말하고 있다. // 호출만하면 this. 을 할 필요없음
		this.cardNo = cardNo;
	}
	
	@Override
	public String toString() {
		
		return "== 임직원 ==\n"+
			   super.toString() +
			   "5.법인카드번호 : "+ cardNo +"\n";
	}
	
	
}
