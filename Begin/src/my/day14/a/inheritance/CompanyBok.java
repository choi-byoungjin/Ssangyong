package my.day14.a.inheritance;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyBok extends MemberBok{
	private String comname;
	private String combunho;
	private String jobtype;
	private long seedmoney;
	
	static int count;

	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		if( comname != null && !comname.trim().isEmpty()) {
			int len = comname.length();
			if(2 <= len && len <= 10)
				this.comname = comname;
			else
				System.out.println(">> [경고] 회사명은 2글자 이상 10글자 이하이어야 합니다. <<\n");
		}
		else {
			System.out.println(">> [경고] 회사명은 null 또는 공백만으로 될 수는 없습니다. << \n");
		}
	}

	public String getCombunho() {
		return combunho;
	}
	public void setCombunho(String combunho) {
		Pattern p = Pattern.compile("^[1-9][0-9]-[0-9]{3}$");
		Matcher m = p.matcher(combunho);
		boolean bool = m.matches();
		if(bool)
			this.combunho = combunho;
		else
			System.out.println(">> [경고] 사업자 등록번호가 올바르지 않습니다. << \n");
	}

	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		if(jobtype != null && !jobtype.trim().isEmpty())
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
	
	public boolean isUseCompany() {
		if( super.getId() != null &&
			super.getPasswd() != null &&
			comname != null &&
			combunho != null &&
			jobtype != null &&
			seedmoney != 0 )
			return true;
		else
			return false;
	}
	
	public void showInfo() {
		DecimalFormat df = new DecimalFormat("#,###");
		System.out.println("1.아이디 : "+ super.getId() +"\n" +
				   		   "2.비밀번호 : "+ super.getPasswd() +"\n" +
						   "3.회사명 : "+ comname +"\n" +
						   "4.사업자등록번호 : "+ combunho +"\n" +
						   "5.업종 : "+ jobtype +"\n" +
						   "6.자본금 : "+ df.format(seedmoney) +"만원\n");
	}
	
	public void viewInfo() {
		DecimalFormat df = new DecimalFormat("#,###");
		System.out.printf("%-10s\t%-15s\t%-10s\t%8s\t%-10s\t%-10s\n",getId(),getPasswd(),comname,combunho,jobtype,df.format(seedmoney)+"만원"); 
	}
	
	public String getInfo() {
		DecimalFormat df = new DecimalFormat("#,###");
		return getId()+"   "+getPasswd()+"   "+comname+"   "+combunho+"   "+jobtype+"   "+df.format(seedmoney)+"만원";
	}
}
