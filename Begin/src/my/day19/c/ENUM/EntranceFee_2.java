package my.day19.c.ENUM;

public class EntranceFee_2 {

	public static final EntranceFee_2 CHILD = new EntranceFee_2(0); // 어린이는 입장료가 0원이다.
	public static final EntranceFee_2 TEENAGER = new EntranceFee_2(150);
	public static final EntranceFee_2 ADULT = new EntranceFee_2(300);
	public static final EntranceFee_2 OLD = new EntranceFee_2(100);

	private final int FEE;
	// 외부에서 FEE 에 접근하지 못하도록 접근제한자에 private 을 준다.
	
	
	
	private EntranceFee_2(int fee) {
	// 생성자의 접근제한자에 private 을 주어서 외부에서 객체생성을 못하도록 막아버린다.
		this.FEE = fee;
	}
	
	public int getFee() {
	// 외부에서 FEE 값을 읽을 수 있도록 접근제한자를 public 으로 준다.
		return FEE;
	}
	
	
}
