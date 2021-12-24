package my.day15.b.abstractClass;

public abstract class AnimalBok {

	private String name;
	private int birthYear;
	
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		if(name != null && !name.trim().isEmpty())
			this.name = name;
	}
	
	protected int getBirthYear() {
		return birthYear;
	}
	protected void setBirthYear(int birthYear) {
		if(birthYear > 0)
			this.birthYear = birthYear;
	}
	
	protected void showInfo() {
		System.out.println("=== 동물정보 ===\n"
						+  "1. 이름 : "+ name + "\n"
						+  "2. 생년 : "+ birthYear + "년");
	}
	
	protected void cry() {
		System.out.println(">>> 동물들이 소리를 냅니다. <<<");
	}
	
	public abstract void move();
	
}
