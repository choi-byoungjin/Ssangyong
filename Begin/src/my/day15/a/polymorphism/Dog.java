package my.day15.a.polymorphism;

public class Dog extends Animal{

	// Dog 만 가지는 field 를 정의 (추상화)
	private int weight;

	
	// Dog 만 가지는 method 를 정의 (추상화)
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		if(weight > 0)
			this.weight = weight;
	}
	
	// === 메소드 오버라이딩[override](메소드 재정의) === //
	// ==> 부모클래스로부터 상속받은 메소드를 내용물만 새롭게 바꾸어서 정의하는 것
	// 메소드 오버라이딩시 접근제한자는 부모클래스에서 주었던 접근제한자와 같거나 더 크게 허용하는 접근제한자만 가능하다.
	
	@Override   // @로 되어지는것을 "어노테이션"
	protected void cry() {
		
		System.out.println("강아지 "+ getName() +"이 '멍멍' 하며 짖습니다.");
		
	}

	@Override
	protected void showInfo() {
		super.showInfo();
		System.out.println("3. 체중 : " + weight + "kg\n");
	}
	
	public void run() {
		System.out.println("강아지 "+ getName() +"가 빠르게 달립니다.");
	}
	
}
