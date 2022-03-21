package member.model;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements InterMemberDAO {

	private DataSource ds;    // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 기본생성자
	public MemberDAO() {
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/mymvc_oracle");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	// 자원반납 해주는 메소드
	private void close() {
		
		try {
			if(rs != null)    {rs.close();	  rs = null;}
			if(pstmt != null) {pstmt.close(); pstmt = null;}
			if(conn != null)  {conn.close();  conn = null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}// end of private void close()-------------------------------------
	
	
	// ID 중복검사 (tbl_member 테이블에서 userid 가 존재하면 true를 리턴해주고, userid 가 존재하지 않으면 false를 리턴한다)
	@Override
	public boolean idDuplicateCheck(String userid) throws SQLException {
		
		boolean isExist = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select * "
					   + " from tbl_member"
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			isExist = rs.next();  // 행이 있으면(중복된 userid)     true,
			                      // 행이 없으면(사용가능한 userid)  false
			
		} finally {
			close();
		}
		
		return isExist;
	}


	// email 중복검사 (tbl_member 테이블에서 email 이 존재하면 true를 리턴해주고, email 이 존재하지 않으면 false를 리턴한다) 
	@Override
	public boolean emailDuplicateCheck(String email) throws SQLException {

		boolean isExist = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select * "
					   + " from tbl_member"
					   + " where email = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			isExist = rs.next();  // 행이 있으면(중복된 userid)     true,
			                      // 행이 없으면(사용가능한 userid)  false
			
		} finally {
			close();
		}
		
		return isExist;		
	}


	
	// 회원가입을 해주는 메소드 (tbl_member 테이블에 insert)
	@Override
	public int registerMember(MemberVO member) throws SQLException {
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " insert into tbl_member(userid, pwd, name, email, mobile, postcode, address, detailaddress, extraaddress, gender, birthday) "  
					   + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";  
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  member.getUserid());
			pstmt.setString(2,  member.getPwd());      // 암호를 SHA256 알고리즘으로 단방향 암호화 시킨다.   
			pstmt.setString(3,  member.getName());
			pstmt.setString(4,  member.getEmail());    // 이메일을 AES256 알고리즘으로 양방향 암호화 시킨다.   
			pstmt.setString(5,  member.getMobile());   // 휴대폰번호를 AES256 알고리즘으로 양방향 암호화 시킨다.  
			pstmt.setString(6,  member.getPostcode());
			pstmt.setString(7,  member.getAddress());
			pstmt.setString(8,  member.getDetailaddress());
			pstmt.setString(9,  member.getExtraaddress());
			pstmt.setString(10, member.getGender());
			pstmt.setString(11, member.getBirthday());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
	}
	
	
	
	
	
	

}
