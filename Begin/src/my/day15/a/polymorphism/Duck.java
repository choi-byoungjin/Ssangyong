package my.day15.a.polymorphism;

public class Duck extends Animal{
	
	// Duck 만 가지는 field 를 정의 (추상화)
	private int price;


	// Duck 만 가지는 method 를 정의 (추상화)
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		if(price > 0)
			this.price = price;
	}

	@Override
	protected void cry() {
		super.cry();
		System.out.println("오리 "+ getName() +"는 '꽥꽥' 하며 짖습니다.");		
	}

	@Override
	protected void showInfo() {
		System.out.println("=== 고양이정보 ===\n"
				+  "1. 성명 : " + getName() + "\n"
				+  "2. 생년 : " + getBirthYear() + "년\n"
				+  "3. 가격 : " + price + "원\n");
	}
	
	public void swim() {
		System.out.println("오리 "+ getName() +"가 물가에서 헤엄을칩니다.");
	}
}
