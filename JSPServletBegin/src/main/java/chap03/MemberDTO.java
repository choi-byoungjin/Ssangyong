package chap03;

import java.util.Calendar;

public class MemberDTO {
	
	private String name;	// 성명
	private String jubun;	// 주민번호
	
	public MemberDTO() {}	// 기본생성자	
	
	public MemberDTO(String name, String jubun) {	// 파라미터를 통한 생성자	
		this.name = name;
		this.jubun = jubun;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getJubun() {
		return jubun;
	}
	
	public void setJubun(String jubun) {
		this.jubun = jubun;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	// 1.성별을 알아오는 메소드 생성하기
	public String getGender() {
		
		if(jubun != null) {
			String n = jubun.substring(6, 7);	// "9405081234567" 에서 성별을 나타내어주는 "2" 만 가져온다.
			
			if("1".equals(n) || "3".equals(n)) {
				return "남";
			}			
			else if("2".equals(n) || "4".equals(n)) {
				return "여";
			}
			else {
				return "";
			}
		}
		else {
			return "";
		}
	}// end of public String getGender()-----------------------------------------------------------
	
	// 2.나이르 알아오는 메소드 생성하기
	public int getAge() {
		
		// 먼저 현재년도를 알아와야 한다.
		Calendar now = Calendar.getInstance();		// 현재날짜와 시간을 얻어온다.
		int currentYear = now.get(Calendar.YEAR);	// 현재년도를 얻어온다.
		
		if(jubun != null) {
		
			String n = jubun.substring(6, 7);	// "1" 또는 "2" 이라면 1900년대 생,
												// "3" 또는 "4" 이라면 2000년대 생,
			
			String jubunYear = jubun.substring(0, 2);	// 주민번호에성 출생년도 2자리를 얻어온다.
			
			if("1".equals(n) || "2".equals(n)) {	// 1900년대 생
				return currentYear - (1900 + Integer.parseInt(jubunYear)) + 1;
			}
			else { // 2000년대 생
				return currentYear - (2000 + Integer.parseInt(jubunYear)) + 1;
			}
			
			
		}
		
		else {
			return 0;
		}
	}// end of public int getAge()------------------------------------------------------------
}
