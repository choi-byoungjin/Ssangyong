package my.day12.a.encapsulation;

public class Sungjuk_capsule {

/*
    -----------------------------------------------------------------------------------------------------------------------
	접근제한자(접근지정자, accessmodifier)   자기자신클래스내부      동일패키지에있는다른클래스      다른패키지에있는하위(자식)클래스        그외의영역  
	----------------------------------------------------------------------------------------------------------------------- 
	public                                    O                    O                         O                 O  
	protected                                 O                    O                         O                 X
	default                                   O                    O                         X                 X
	private                                   O                    X                         X                 X
*/	
	
	private String name;
	private int kor = -1;
	private int eng = -1;
	private int math = -1; // 잘못 넣었을 때만 -1이 나옴
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		
		if(name == null || name.trim().isEmpty()) {
			System.out.println(">> [경고] 입력하신 성명은 null 또는 공백만으로 이루어지면 안되며 성명은 null로 되어집니다. <<\n");
		}
		
		else {
			this.name = name;
		}
	}
	
	public int getKor() {
		return kor;
	}
	
	public void setKor(int kor) {
		
		if( 0 <= kor && kor <= 100) {
			this.kor = kor;
		}
		
		else {
			System.out.println(">> [경고] 입력하신 국어점수가 0 이상 100 이하가 아니므로 국어점수는 -1 으로 되어집니다. <<\n");
		}
	}
	
	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		if( 0 <= eng && eng<= 100) {
			this.eng = eng;
		}
		
		else {
			System.out.println(">> [경고] 입력하신 영어점수가 0 이상 100 이하가 아니므로 영어점수는 -1 으로 되어집니다. <<\n");
		}
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		if( 0 <= math && math<= 100) {
			this.math = math;
		}
		
		else {
			System.out.println(">> [경고] 입력하신 수학점수가 0 이상 100 이하가 아니므로 수학점수는 -1 으로 되어집니다. <<\n");
		} 
	}
	
	public boolean isPass() {
		
		if( name == null || kor == -1 || eng == -1 || math == -1 )
			return false;
		
		else
			return true;
	}
	
	
	public void showSungjuk() {
		System.out.println("1.성명 : " + name + "\n"
				         + "2.국어 : " + kor + "\n"
				         + "3.영어 : " + eng + "\n"
				         + "4.수학 : " + math + "\n");
	}
}
