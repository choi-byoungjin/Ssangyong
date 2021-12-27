package my.day16.c.multiInterface;

public class MainChild {

	public static void main(String[] args) {
		
		Child cd1 = new Child();
		cd1.work();
		cd1.cook();
		cd1.play();

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		InterChild inchild = new Child();
		inchild.work();
		inchild.cook();
		inchild.play();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		InterFather interfather = new Child();
		interfather.work();
		
		InterMother intermother = new Child();
		intermother.cook();
	}

}
