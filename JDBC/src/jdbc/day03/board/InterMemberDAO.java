package jdbc.day03.board;

import java.util.Map;

public interface InterMemberDAO {
	
	// 자원반납 메소드 // 
	void close();
	
	// 회원가입시 사용가능한 아이디인지 중복된 아이디라서 사용불가인지 알려주는 메소드
	boolean isUse_userid(String userid);
	
	// 회원가입(insert) 메소드
	int memberRegister(MemberDTO member);

	// 로그인 처리(select) 메소드
	MemberDTO login(Map<String, String> paraMap); // where의 map 키 벨류값을 주면 MemberDTO를 주겠다

	
}
