package my.day19.e.ENUM;

public enum Bok_EntranceFee_2 {

	CHILD(0){
		@Override
		int getRealFee(int inwonsu) {
			return 0;
		}
	},
	
	TEENAGER(200){
		@Override
		int getRealFee(int inwonsu) {
			if(inwonsu >= 20)
				return (int)(FEE * inwonsu * 0.9);
			else
				return FEE * inwonsu;
		}
	},
	
	ADULT(300){
		@Override
		int getRealFee(int inwonsu) {
			if(inwonsu >= 20)
				return (int)(FEE * inwonsu * 0.8);
			else
				return FEE * inwonsu;
		}
	},
	
	OLD(50){
		@Override
		int getRealFee(int inwonsu) {
			if(inwonsu >= 20)
				return (int)(FEE * inwonsu * 0.7);
			else
				return FEE * inwonsu;
		}
	};
	
	protected final int FEE;
	
	Bok_EntranceFee_2(int fee){
		this.FEE = fee;
	}
	
	public int getFee() {
		return FEE;
	}
	
	abstract int getRealFee(int inwonsu);
	
}
