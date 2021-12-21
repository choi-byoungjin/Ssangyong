package my.day12.a.encapsulation;

public class Sungjuk_ctrl {
	
	public void saveSungjuk(Sungjuk_capsule[] sungjukArr, Sungjuk_capsule sj) { // sj.check를 위해 Sungjuk_capsule 클래스에 메소드 만들어야 함
		
		if( sj.isPass() ) { // sj인스턴스의 이름, 국어, 영어, 수학이 올바른지 검사한다.
			sungjukArr[0] = sj;		
			System.out.println(">> 저장 성공 !! << \n");
		}
		
		else {
			System.out.println(">> 저장 실패 !! << \n");
		}
		
	}
	
}
