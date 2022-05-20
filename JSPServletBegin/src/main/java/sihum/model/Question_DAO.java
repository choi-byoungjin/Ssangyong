package sihum.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Question_DAO implements InterQuestion_DAO {

	private DataSource ds;   // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 생성자
	public Question_DAO() {
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle");
		} catch(NamingException e) {
			e.printStackTrace();
		} 
	}
		
	// 사용한 자원을 반납하는 close() 메소드 생성하기 
	private void close() {
		try {
			if(rs != null)    {rs.close();    rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(conn != null)  {conn.close();  conn=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	

    // 과목번호 및 과목회차에 따른 시험문제 가져오기 메소드 구현하기 
	@Override
	public List<Question_VO> question_list(Map<String, String> paraMap) throws SQLException {
		
		List<Question_VO> question_list = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select question_no, question, correct "
					   + " from tbl_question "
					   + " where fk_subject_no = ? and fk_test_round = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("subject_no"));
			pstmt.setString(2, paraMap.get("test_round"));
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Question_VO vo = new Question_VO();
				vo.setQuestion_no(rs.getString(1));
				vo.setQuestion(rs.getString(2));
				vo.setCorrect(rs.getString(3));
				
				question_list.add(vo);
			}// end of while---------------------
			
		} finally {
			close();
		}
		
		return question_list;
	}
	

	// 특정 시험문항의 정답보기 가져오기 메소드 구현하기 
	@Override
	public List<String> answers_list(String question_no) throws SQLException {
		
		List<String> answers_list = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select answer  "
					   + " from tbl_answers "
					   + " where fk_question_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, question_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				answers_list.add(rs.getString(1));
			}// end of while---------------------
			
		} finally {
			close();
		}
		
		return answers_list;
	}
	
}
