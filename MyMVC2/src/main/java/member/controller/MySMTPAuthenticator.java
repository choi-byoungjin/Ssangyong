package member.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MySMTPAuthenticator extends Authenticator { //javax.mail 임포트
	
	@Override
	public PasswordAuthentication getPasswordAuthentication() {
	      
		// Gmail 의 경우 @gmail.com 을 제외한 아이디만 입력한다.
		return new PasswordAuthentication("cbj4056", "qnwbdlpqutjnpqkj"); 
		// qnwbdlpqutjnpqkj 은 Google에 로그인 하기위한 앱비밀번호이다.
	}

}
