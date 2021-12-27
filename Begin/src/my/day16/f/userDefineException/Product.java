package my.day16.f.userDefineException;

public class Product {
	
	// field
	private String prodName;  // 제품명 ("새우깡",   "감자깡",   "양파링")
	private int	   jango;	  // 잔고   ( 100  ,      50  ,     150)
	
	// method
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public int getJango() {
		return jango;
	}
	public void setJango(int jango) {
		this.jango = jango;
	}
	
	// 제품의 정보를 보여주는 메소드 생성
	public void info() {
		String info = "1.제품명 : "+ prodName +"\n"
					+ "2.잔고량 : "+ jango +"개\n";
		
		System.out.println(info);
	}
	
	// 주문을 받는 메소드 생성 1
	void order(int jumunSu) {
		
		
	}
	
	
	// 주문을 받는 메소드 생성 2
}
