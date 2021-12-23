package my.day14.a.inheritance;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Company extends Member{ // 자식클래스
	
	private String comname;  // 회사명
	private String combunho; // 사업자등록번호
	private String jobtype;	 // 업종명(예: 제조업, IT, 서비스업)
	private long seedmoney;  // 자본금
	static int count; 		 // Company 객체(인스턴스)의 개수를 알아오려는 용도
	
	public String getComname() {
		
		return comname;
	}
	public void setComname(String comname) {
		if( comname != null && !comname.trim().isEmpty()) { //!(id = null || id.trim().isEmpty())
			
			int len = comname.length();
			
			if( 5 <= len && len <= 10 )
				this.comname = comname;
			else
				System.out.println(">> [경고] 회사명은 5글자 이상 10글자 이하이어야 합니다. << \n");
		} 
		else {
			System.out.println(">> [경고] 회사명은 null 또는 공백만으로 될 수는 없습니다. << \n");
		}
	}
	
	public String getCombunho() {
		
		return combunho;
	}
	public void setCombunho(String combunho) {
		// 사업자등록번호는 12-345 이라는 형식을 취한다
		// 그런데 첫번째 오는 글자는 0은 올 수 없다라고 하자.
		
		Pattern p = Pattern.compile("^[1-9][0-9]-[0-9]{3}$");
		Matcher m = p.matcher(combunho);
		boolean bool = m.matches();
		
		if(bool) {
			this.combunho = combunho;
		}
		else {
			System.out.println(">> [경고] 사업자 등록번호가 올바르지 않습니다. << \n");
		}
	}
	
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		
		if( jobtype != null && !jobtype.trim().isEmpty()) //!(id = null || id.trim().isEmpty())
			this.jobtype = jobtype;
		else 
			System.out.println(">> [경고] 직종타입은 null 또는 공백만으로 될 수는 없습니다. << \n");
	}
	
	public long getSeedmoney() {
		return seedmoney;
	}
	public void setSeedmoney(long seedmoney) {
		
		if(seedmoney > 0)
			this.seedmoney = seedmoney;
	}
}
