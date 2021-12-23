package my.day13.a.capsulation;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.util.MyUtilBok;

public class GujikjaBok {
	
	private String userid;
	private String passwd;
	private String name;
	private String jubun;
	static int count;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		if( userid != null && !userid.trim().isEmpty()) {
			int len = userid.length();
			
			if( 2 <= len && len <= 10)
				this.userid = userid;
			else
				System.out.println(">> [경고] 아이디는 5글자 이상 10글자 이하이어야 합니다. << \n");
		}
	}
	
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		if( MyUtilBok.isCheckPasswd(passwd) )
			this.passwd = passwd;
		else
			System.out.println(">> [경고] 비밀번호는 8글자 이상 15글자 이하의 대, 소문자 및 숫자, 특수기호가 혼합되어야만 합니다. << \n");
	}
	
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
		if( userid != null &&
			passwd != null &&
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
		System.out.println("1.아이디 : "+ userid +"\n" +
						   "2.비밀번호 : "+ passwd +"\n"+
						   "3.성명 : "+ name +"\n"+
						   "4.현재나이 : "+ getAge() +"\n"+
						   "5.성별 : "+ getGender() +"\n");
	}
	
	public void viewInfo() {
		System.out.printf("%-10s\t%-15s\t%-8s\t%3d\t%-2s\n",userid,passwd,name,getAge(),getGender());
	}
	
	public String getInfo() {
		return userid + "   " + passwd + "   " + name + "   " + getAge() + "   " + getGender();
	}
}
