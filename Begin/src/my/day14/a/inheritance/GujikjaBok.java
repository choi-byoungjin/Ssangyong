package my.day14.a.inheritance;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.util.MyUtilBok;

public class GujikjaBok extends Member{
	
	private String name;
	private String jubun;
	static int count;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		Pattern p = Pattern.compile("^[가-힣]{2,5}$");
		Matcher m = p.matcher(name);
		
		if( m.matches() )
			this.name = name;
		else
			System.out.println(">> [경고] 성명은 공백없이 한글로만 2글자 부터 5글자까지만 가능합니다. << \n");
	}
	
	public String getJubun() {
		return jubun;
	}
	public void setJubun(String jubun) {
		if( MyUtilBok.isCheckJubun(jubun) )
			this.jubun = jubun;
		else
			System.out.println(">> [경고] 주민번호 앞의 7자리가 올바르지 않습니다. << \n");
	}
	
	public boolean isUseGujikja() {
		if( getId()!= null &&
			getPasswd() != null &&
			name != null &&
			jubun != null)
			return true;
		else
			return false;
	}
	
	public int getAge() {
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
	
	public String getGender() {
		String sGender = jubun.substring(jubun.length()-1);
		
		if("1".equals(sGender) || "3".equals(sGender)) 
			return "남";
		else
			return "여";
	}
	
	public void showInfo() {
		System.out.println("1.아이디 : "+ getId() +"\n" +
						   "2.비밀번호 : "+ getPasswd() +"\n"+
						   "3.성명 : "+ name +"\n"+
						   "4.현재나이 : "+ getAge() +"\n"+
						   "5.성별 : "+ getGender() +"\n");
	}
	
	public void viewInfo() {
		System.out.printf("%-10s\t%-15s\t%-8s\t%3d\t%-2s\n",getId(),getPasswd(),name,getAge(),getGender());
	}
	
	public String getInfo() {
		return getId()+ "   " + getPasswd()+ "   " + name + "   " + getAge() + "   " + getGender();
	}
}
