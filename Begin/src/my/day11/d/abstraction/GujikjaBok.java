package my.day11.d.abstraction;

import java.util.Calendar;

public class GujikjaBok {
	
	String userid;
	String passwd;
	String name;
	String jubun;
	static int count;
	
	int getAge() {
		
		Calendar currentDate = Calendar.getInstance();
		int currenYear = currentDate.get(Calendar.YEAR);
		String sGender = jubun.substring(jubun.length()-1);
		
		int birthYear = 0;
		if( "1".equals(sGender) || "2".equals(sGender)) 
			birthYear = Integer.parseInt(jubun.substring(0, 2)) + 1900;
		else 
			birthYear = Integer.parseInt(jubun.substring(0, 2)) + 2000;
		
		return currenYear-birthYear+1;
	}
	
	String getGender() {
		
		String sGender = jubun.substring(jubun.length()-1);
		
		if("1".equals(sGender) || "3".equals(sGender)) 
			return "남";
		else
			return "여";
	}
	
	void showInfo() {
		
		System.out.println("1.아이디 : "+ userid +"\n" +
						   "2.비밀번호 : "+ passwd +"\n"+
						   "3.성명 "+ name +"\n"+
						   "4.현재나이 "+ getAge() +"\n"+
						   "5.성별 "+ getGender() +"\n");
	}
	
	void viewInfo() {
		System.out.printf("%-10s\t%-15s\t%-8s\t%3d\t%-2s\n",userid,passwd,name,getAge(),getGender());
	}
}
