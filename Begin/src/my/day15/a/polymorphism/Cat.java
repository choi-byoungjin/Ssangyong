package my.day15.a.polymorphism;

public class Cat extends Animal{

	// Cat 만 가지는 field 정의 (추상화)
	private String color;

	
	// Cat 만 가지는 method 정의 (추상화)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if(color != null && !color.trim().isEmpty())
			this.color = color;
	}

	@Override
	protected void cry() {
		
		System.out.println("고양이 "+ getName() +"은 '야옹야옹' 하며 짖습니다.");
		
	}

	@Override
	protected void showInfo() {
		System.out.println("=== 고양이정보 ===\n"
				+  "1. 성명 : " + getName() + "\n"
				+  "2. 생년 : " + getBirthYear() + "년\n"
				+  "3. 피부색 : " + color +"\n");
	}
	
	public void jump() {
		System.out.println("고양이 "+ getName() +"이 점프를 합니다.");
	}
	
}
