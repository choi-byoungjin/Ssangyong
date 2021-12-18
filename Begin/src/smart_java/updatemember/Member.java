package smart_java.updatemember;
import java.io.*;

public class Member implements Serializable {
	private String name;
	private int age;
	private String phone;
	
	public Member() {
		name = "";
		age = 0;
		phone = "";
	}
	public Member(String name) {
		this.name = name;
	}
	public Member(String name, int age, String phone) {
		this(name);
		this.age = age;
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getPhone() {
		return phone;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String toString() {
		String msg = "";
		msg += "["+name;
		msg += ", "+age;
		msg += ", "+phone+"]";
		return msg;
	}
}
