package my.day19.d.ENUM;

public enum Bok_EntranceFee_2 {
	
	CHILD(0), TEENAGER(200), ADULT(300), OLD(50);
	
	private final int FEE;
	
	Bok_EntranceFee_2(int fee){
		this.FEE = fee;
	}
	
	public int getFee() {
		return FEE;
	}
	
}
