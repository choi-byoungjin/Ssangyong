package smart_java.updatemember;
import java.util.*;
import java.io.*;

public class MemberList {
	Vector<Member> list;
	public MemberList() {
		this.list = new Vector<Member>();
	}
	public void add(Member m) {
		list.add(m);
		System.out.println("[추가] : " + m.toString());
	}
	public void delete(String name) {
		Member result = search(name);
		if(list.contains(result)) {
			list.remove(result);
			System.out.println("[삭제] : "+result.toString());
		} else {
			System.out.println("[삭제] : ("+name+") 존재하지 않습니다!");
		}
	}
	public Member search(String name) {
		Member result = null;
		for(Member mem : list) {
			if( mem.getName().equals(name) ) {
				result = mem;
				break;
			}
		}
		if( result == null ) 
			 System.out.println("[검색] : ("+name+") 존재하지 않습니다!");
		else System.out.println("[검색] : " + result.toString());
		return result;
	}
	public void update(Member m) {
		Member result = search(m.getName());
		result.setName(m.getName());
		result.setAge(m.getAge());
		result.setPhone(m.getPhone());
	}

	public void listing() {
		if(!list.isEmpty()) {
			System.out.println("---------------------------------------------------");
			for(Member mem : list) {
				System.out.println(mem.toString());
			}
			System.out.println("---------------------------------------------------");
		}
	}
	public void saveFile() {
		if( list.isEmpty() ) {
			System.out.println("저장할 데이터가 없습니다!");
			return;
		}
		try {
			FileOutputStream fos = new FileOutputStream("member.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			for(Member mem : list ) {
				oos.writeObject(mem);
				System.out.println("[저장] : "+mem.toString());
			}
			
			fos.close();
			oos.close();
		} catch(IOException e) {
			System.out.println("파일 저장중 예외 발생!!");
		}
	}
	public void readFile() {
		Member read = null;
		list.clear();
		try {
			FileInputStream fis = new FileInputStream("member.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			while(( read = (Member)ois.readObject()) != null ) {
				list.add(read);
				System.out.println("[읽음] : "+read.toString());
			}
			
			fis.close();
			ois.close();
		} catch(EOFException e) {
			System.out.println("파일에서 모두 읽었습니다!");
		} catch(ClassNotFoundException e) {
			System.out.println("Member 클래스를 찾지 못함!!");
		} catch(IOException e) {
			System.out.println("파일 읽기 중 입출력 예외 발생!!");
		}
	}
}
