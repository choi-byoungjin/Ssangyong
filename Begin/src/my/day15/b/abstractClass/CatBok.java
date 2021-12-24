package my.day15.b.abstractClass;

public class CatBok extends AnimalBok{

	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if(color != null && !color.trim().isEmpty())
			this.color = color;
	}

	@Override
	protected void showInfo() {
		System.out.println("=== 고양이정보 ===\n"
						+  "1. 성명 : " + getName() + "\n"
						+  "2. 생년 : " + getBirthYear() + "년\n"
						+  "3. 피부색 : " + color +"\n");
	}

	@Override
	protected void cry() {
		System.out.println("고양이 "+ getName() +"은 '야옹야옹' 하며 짖습니다.");
	}
	
	@Override
	public void move() {
		System.out.println("고양이 "+ getName() +"이 점프를 합니다.");
	}
	
}
