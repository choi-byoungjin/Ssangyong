package jdbc.day03.board;

import java.sql.*;
import java.util.*;

public class MemberDAO implements InterMemberDAO {
	
	// attribute, field, property, 속성
	Connection conn;// DAO -> Connection 필요 **
	PreparedStatement pstmt;
	ResultSet rs;
	
	// operation, method, 기능
	
	// *** 자원반납 메소드 구현하기 *** //
	@Override
	public void close() {
		
		try {
			if(rs != null) 		rs.close();
			if(pstmt != null) 	pstmt.close();
			if(conn != null) 	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}// end of public void close()--------------------------------------------------- 
	
	
	
	// *** 회원가입시 사용가능한 아이디인지 중복된 아이디라서 사용불가인지 알려주는 메소드 구현하기 *** //
		@Override
		public boolean isUse_userid(String userid) {
			
			boolean isUse = false;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
				
				String sql = " select * "
						   + " from jdbc_member "
						   + " where userid = ? "; // rs.next()가 있는지 확인
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					// userid 가 이미 존재하는 아이디 인 경우이다.
					isUse = false;
				}
				else {
					// userid 가 이미 존재하지 않는 아이디 인 경우이다.
					isUse = true;
				}
				
			} catch(ClassNotFoundException e) {
				System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
			} catch(SQLException e) {
				e.printStackTrace(); 
			} finally {
				close();
			}
			
			return isUse;
			
		}// end of public boolean isUse_userid(String userid)----------------------
		
	
	// *** 회원가입(insert)을 처리해주는 
		@Override
		public int memberRegister(MemberDTO member) {
			int result = 0;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
				
				String sql = " insert into jdbc_member(userseq, userid, passwd, name, mobile) "
						   + " values(userseq.nextval, ?, ?, ?, ?) ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, member.getUserid());
				pstmt.setString(2, member.getPasswd());
				pstmt.setString(3, member.getName());
				pstmt.setString(4, member.getMobile());
				
				result = pstmt.executeUpdate(); // 성공이라면 1 오류가 발생하면 0
				
			} catch(ClassNotFoundException e) {
				System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
			} catch(SQLIntegrityConstraintViolationException e) {
				if( e.getErrorCode() == 1) {
					System.out.println(">> 아이디가 중복되었습니다. 새로운 아이디를 입력하세요! <<");
				}
			} catch(SQLException e) {
			//	e.printStackTrace(); // 오류메세지 출력하지 않고 result가 0으로 넘어간다.
			} finally {
				close();
			}
			return result;
		}// end of public int memberRegister(MemberDTO member)---------------------------------------------------


	
	
	// *** 로그인 처리(select) 메소드 구현하기 *** //
	@Override
	public MemberDTO login(Map<String, String> paraMap) {
		
		MemberDTO member = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
			
			String sql = "select userseq, name, mobile, point, to_char(registerday, 'yyyy-mm-dd') AS registerday\n"+
						 "from jdbc_member\n"+
						 "where status = 1 and userid = ? and passwd = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid")); // TotalController에서 줌
			pstmt.setString(2, paraMap.get("passwd"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberDTO();
				
				member.setUserseq(rs.getInt(1));
				member.setName(rs.getString(2));
				member.setMobile(rs.getString(3));
				member.setPoint(rs.getInt(4));
				member.setRegisterday(rs.getString(5));
			
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return member;
	}// end of public MemberDTO login(Map<String, String> paraMap)---------------------------------------


	


}
