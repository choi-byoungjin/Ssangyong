package my.day15.c.polymorphism;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Company extends Member { // 자식클래스

	// field(속성) 생성
	private String comname;   // 회사명
	private String combunho;  // 사업자등록번호
	private String jobtype;   // 업종명(예: 제조업, IT, 서비스업)
	private long   seedmoney; // 자본금
	                  
	
//	static int count; // Company 객체(인스턴스)의 개수를 알아오려는 용도 	


	public String getComname() {
		return comname;
	}


	public void setComname(String comname) {
		
		if( comname != null && !comname.trim().isEmpty() ) {
			
			int len = comname.length();
			
			if(2 <= len && len <= 10)
				this.comname = comname;
			else 
				System.out.println(">> [경고] 회사명은 2글자 이상 10글자 이하이어야 합니다. << \n");
		}
		else {
			System.out.println(">> [경고] 회사명은 null 또는 공백만으로 될 수는 없습니다. << \n");	
		}
	}


	public String getCombunho() {
		return combunho;
	}


	public void setCombunho(String combunho) {
		// 사업자등록번호는 12-345 이라는 형식을 취한다 라고 하자.
		// 그런데 첫번째 오는 글자는 0 은 올수 없다라고 하자.
		
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
		
		if( jobtype != null && !jobtype.trim().isEmpty() ) 
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
		else 
			System.out.println(">> [경고] 자본금은 0 보다 커야 합니다. << \n");
		
	}
	
	
	// id, passwd, comname, combunho, jobtype 필드의 값이 모두 null 이 아니고
	// seedmoney 필드의 값이 0 이 아니라면 올바르게 데이터를 입력한 경우이므로 true 를 리턴해주고,
	// 적어도 1개 이상이 null 이거나 seedmoney 필드의 값이 0 이라면 잘못 입력된 데이터 이므로 false 를 리턴해주는 메소드를 생성한다.
	public boolean isUseCompany() {
		
		if( super.getId() != null &&
			super.getPasswd() != null &&
		    comname != null &&
		    combunho != null &&
		    jobtype != null &&
		    seedmoney != 0)
			return true;
		
		else
			return false;
		
	}
	
	
	// == 구인회사 1개의 정보를 보여주는 메소드 생성하기 == //
	@Override
	public void showInfo() {
		
		DecimalFormat df = new DecimalFormat("#,###");
				
		System.out.println("1.아이디 : "+super.getId()+"\n" + 
						   "2.비밀번호 : "+super.getPasswd()+"\n" + 
						   "3.회사명 : "+comname+"\n" + 
						   "4.사업자등록번호 : "+ combunho +"\n" + 
						   "5.업종 : "+ jobtype +"\n" +
						   "6.자본금 : "+ df.format(seedmoney) +"만원\n"
				          );
		
	}// end of void showInfo()---------------------------------------
		
	
	public void viewInfo() {
		
		DecimalFormat df = new DecimalFormat("#,###");
		
		System.out.printf("%-10s\t%-15s\t%-10s\t%8s\t%-10s\t%-10s\n",getId(),getPasswd(),comname,combunho,jobtype,df.format(seedmoney)+"만원"); 
	}  
	
	
	public String getInfo() {
		
		DecimalFormat df = new DecimalFormat("#,###");
		
		return getId()+"   "+getPasswd()+"   "+comname+"   "+combunho+"   "+jobtype+"   "+df.format(seedmoney)+"만원";
	}
	
	
}
