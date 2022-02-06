package my.day02.b.dataType;

public class BokMainSungjuk {

	public static void main(String[] args) {
		
		BokSungjuk leeSj = new BokSungjuk();
		
		leeSj.hakbun = "091234";
		leeSj.name = "이이이";
		leeSj.korPoint = 68;
		leeSj.eng_point = 95;
		leeSj.mathpoint = 100;
		
		BokSungjuk kimSj = new BokSungjuk();
		
		kimSj.hakbun = "101234";
		kimSj.name = "김김김";
		kimSj.korPoint = 100;
		kimSj.eng_point = 100;
		kimSj.mathpoint = 100;
		
		leeSj.showSungjuk();
		kimSj.showSungjuk();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		BokSungjuk parkSj = new BokSungjuk();
		
		System.out.println("leeSj =>" + leeSj);
		
		parkSj = leeSj;
		System.out.println("kimSj =>" + kimSj);
		
		System.out.println("parkSj=>" + parkSj);
		
		System.out.println("\n#########################################\n");
		
		parkSj.name = "박박박";
		parkSj.korPoint = 5;
		
		parkSj.showSungjuk();
	}

}
