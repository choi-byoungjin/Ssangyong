package my.day19.c.ENUM;

public class Bok_EntranceFee_2 {

	public static final Bok_EntranceFee_2 CHILD = new Bok_EntranceFee_2(0);
	public static final Bok_EntranceFee_2 TEENAGER = new Bok_EntranceFee_2(150);
	public static final Bok_EntranceFee_2 ADULT = new Bok_EntranceFee_2(300);
	public static final Bok_EntranceFee_2 OLD = new Bok_EntranceFee_2(100);
	
	private final int FEE;
	
	private Bok_EntranceFee_2(int fee) {
		this.FEE = fee;
	}
	
	public int getFee() {
		return FEE;
	}
}
