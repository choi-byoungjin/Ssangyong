package smart_java.projectmember;

import java.util.Scanner;

public class MemberMain {
	MemberList list;
	Scanner s = new Scanner(System.in);
	
	public MemberMain() {
		list = new MemberList();
	}
	public void menu() {
		list.listing();
		int select = 0;
		do {
			System.out.print("회원등록(1), 회원삭제(2), 회원검색(3), 불러오기(4), 저장(5), 종료(0) : ");
			String input = s.nextLine();
			select = Integer.parseInt(input);
			switch(select) {
			case 0:
				s.close();
				break;
			case 1:
				addMember();
				list.listing();
				break;
			case 2:
				deleteMember();
				list.listing();
				break;
			case 3:
				searchMember();
				break;
			case 4:
				list.readFile();
				list.listing();
				break;
			case 5:
				list.saveFile();
				break;
			}
		} while( select != 0 );
	}
	public void addMember() {
		System.out.print("[이름] : ");
		String name = s.nextLine();
		System.out.print("[나이] : ");
		String age = s.nextLine();
		System.out.print("[전화번호] : ");
		String phone = s.nextLine();
		Member newm = new Member(name, Integer.parseInt(age), phone);
		list.add(newm);		
	}
	public void deleteMember() {
		System.out.print("[이름] : ");
		String name = s.nextLine();
		list.delete(name);
	}
	public void searchMember() {
		System.out.print("[이름] : ");
		String name = s.nextLine();
		list.search(name);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("[회원관리 프로그램]");
		MemberMain mm = new MemberMain();
		mm.menu();
	}

}
