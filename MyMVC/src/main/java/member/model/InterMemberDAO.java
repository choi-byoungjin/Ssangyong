package member.model;

import java.sql.SQLException;

public interface InterMemberDAO {

	//// ID 중복검사 (tbl_member 테이블에서 userid 가 존재하면 true를 리턴해주고, userid 가 존재하지 않으면 false를 리턴한다)
	boolean idDuplicateCheck(String userid) throws SQLException;

	// EMAIL 중복검사 (tbl_member 테이블에서 userid 가 존재하면 true를 리턴해주고, userid 가 존재하지 않으면 false를 리턴한다)
	boolean emailDuplicateCheck(String userid) throws SQLException;

	// 회원가입을 해주는 메소드 (tbl_member 테이블에 insert)
	int registerMember(MemberVO member) throws SQLException;



}
