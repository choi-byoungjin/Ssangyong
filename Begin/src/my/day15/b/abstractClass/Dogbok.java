package my.day15.b.abstractClass;

public class Dogbok extends AnimalBok{

	private int weight;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		if(weight > 0)
			this.weight = weight;
	}

	@Override
	protected void showInfo() {
		System.out.println("=== 강아지정보 ===\n"
						+  "1. 성명 : "+ getName() +"\n"
						+  "2. 생년 : "+ getBirthYear() +"\n"
						+  "3. 무게 : "+ weight +"kg\n");
	}

	@Override
	protected void cry() {
		System.out.println("강아지 "+ getName() +"이 '멍멍' 하며 짖습니다.");
	}

	@Override
	public void move() {
		System.out.println("강아지 "+ getName() +"가 빠르게 달립니다.");
		
	}

}
