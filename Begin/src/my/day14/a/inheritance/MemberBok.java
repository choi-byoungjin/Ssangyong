package my.day14.a.inheritance;

import my.util.MyUtilBok;

public class MemberBok {

	private String id;
	private String passwd;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		if( id != null && !id.trim().isEmpty()) {
			int len = id.length();
			
			if( 2 <= len && len <= 10)
				this.id = id;
			else
				System.out.println(">> [경고] 아이디는 2글자 이상 10글자 이하이어야 합니다. << \n");
		} else 
			System.out.println(">> [경고] 아이디는 null 또는 공백만으로 될 수는 없습니다. << \n");
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
	
}
