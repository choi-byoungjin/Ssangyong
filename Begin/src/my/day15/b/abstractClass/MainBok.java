package my.day15.b.abstractClass;

public class MainBok {

	public static void main(String[] args) {
		
		Animal[] aniArr = new Animal[5];
		
		Dog dog = new Dog();
		dog.setName("뽀삐");
		dog.setBirthYear(2010);
		dog.setWeight(5);
		
		aniArr[0] = dog;
		
		Cat cat = new Cat();
		cat.setName("톰");
		cat.setBirthYear(2017);
		cat.setColor("검정");
		
		aniArr[1] = cat;
		
		Duck duck = new Duck();
		duck.setName("도날드");
		duck.setBirthYear(2018);
		duck.setPrice(5000);
		
		aniArr[2] = duck;
		
		for (int i = 0; i < aniArr.length; i++) {
			if(aniArr[i] != null)
			   aniArr[i].showInfo();
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for (int i = 0; i < aniArr.length; i++) {
			if(aniArr[i] != null)
			   aniArr[i].cry();
		}
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		for (int i = 0; i < aniArr.length; i++) {
			if(aniArr[i] != null)
			   aniArr[i].move();
		}

	}

}
