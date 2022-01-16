package my.day18.c.generic;

public class Bok_Employee_Child_Executive extends Bok_Employee {
	
	String cardNo;
	
	public Bok_Employee_Child_Executive() {}
	
	public Bok_Employee_Child_Executive(String userid, String passwd, String name, String jik, String cardNo) {
		super(userid,passwd,name,jik);
		this.cardNo = cardNo;
	}

	@Override
	public String toString() {
		return "== 임직원 ==\n"+
			   super.toString() +
			   "5.법인카드번호 : "+ cardNo + "\n";
	}
}