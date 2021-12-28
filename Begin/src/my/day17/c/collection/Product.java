package my.day17.c.collection;

public class Product {

	private String pname;  // 제품명
	private int price;	   // 가격
	private double weight; // 무게
	
	public Product() {}
	
	public Product(String pname, int price, double weight) {
		this.pname = pname;
		this.price = price;
		this.weight = weight;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public void info() {
		System.out.println("== 제품 "+ pname + "의 정보 == \n"
						+  "1.제품명 : "+ pname +"\n"
						+  "2.가격 : "+ price +"원\n"
						+  "3.무게 : "+ weight +"kg\n");
	}
	
	//////////////////////////////////////////////////////////////////////
	@Override
	public String toString() {
		
		return "1.제품명 : "+ pname +"\n"
			+  "2.가격 : "+ price +"원\n"
			+  "3.무게 : "+ weight +"kg\n";
	}	
}