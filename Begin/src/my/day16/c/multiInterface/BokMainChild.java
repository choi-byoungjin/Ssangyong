package my.day16.c.multiInterface;

public class BokMainChild {

	public static void main(String[] args) {
		
		BokChild cd1 = new BokChild();
		cd1.work();
		cd1.cook();
		cd1.play();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		BokInterChild inchild = new BokChild();
		inchild.work();
		inchild.cook();
		inchild.play();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

		InterFather interfather = new Child();
		interfather.work();
		
		InterMother intermother = new Child();
		intermother.cook();
	}

}
