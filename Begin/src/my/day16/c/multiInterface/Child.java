package my.day16.c.multiInterface;

public class Child implements InterChild{

	@Override
	public void work() {
		System.out.println("보람차게 일합니다.!!");
		
	}

	@Override
	public void cook() {
		System.out.println("맛있는 요리를 합니다~~~");
		
	}

	@Override
	public void play() {
		System.out.println("재미나게 놀아요^^");
		
	}

	
}
