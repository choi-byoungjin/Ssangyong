package my.day15.a.polymorphism;

public class DuckBok extends AnimalBok{
	
	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if(price > 0)
			this.price = price;
	}

	@Override
	protected void showInfo() {
		System.out.println("=== 오리정보 ===\n"
						+  "1. 성명 : "+ getName() +"\n"
						+  "2. 생년 : "+ getBirthYear() +"\n"
						+  "3. 가격 : "+ price +"원\n");
	}

	@Override
	protected void cry() {
		System.out.println("오리 "+ getName() +"는 '꽥꽥' 하며 짖습니다;");
	}
	
	public void swim() {
		System.out.println("오리 "+ getName() +"가 물가에서 헤업을 칩니다.");
	}
	

}
