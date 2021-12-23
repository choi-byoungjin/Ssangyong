package my.day14.b.inheritance;

import my.day14.a.inheritance.Member;

public class Child_Member extends Member {

	void showInfo() {
		System.out.println("ID : " + this.getId());
		System.out.println("ID : " + super.getId());
		System.out.println("ID : " + getId());

	}
}
