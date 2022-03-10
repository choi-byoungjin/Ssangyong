package chap05;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class PersonDAO_04 implements InterPersonDAO_03 {
	
	private DataSource ds;	// DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 기본생성자	
	public PersonDAO_04() {
		try {		
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle"); 		    
		} catch(NamingException e) {
			e.printStackTrace();
		}		
	}
	
	// 자원반납 해주는 메소드
	private void close() {
		
		try {
			if(rs != null) { rs.close(); rs = null; }
			if(pstmt != null) { pstmt.close(); pstmt = null; }
			if(conn != null) { conn.close(); conn = null; }
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}// end of private void close()---------------------------------------

	
	
	@Override
	public int personRegister(PersonDTO_02 psdto) throws SQLException {
		
		int n = 0;
		
		try {
					
			conn = ds.getConnection(); // Datasource(DBCP)에서 존재하는 Connection 을 하나 가지고 옴.
			
			String sql = " insert into tbl_person_interest(seq, name, school, color, food) "
					   + " values(person_seq.nextval, ?, ?, ?, ?) ";							
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, psdto.getName());
			pstmt.setString(2, psdto.getSchool());
			pstmt.setString(3, psdto.getColor());
			
			if(psdto.getFood() != null) {		
				pstmt.setString(4, String.join(",", psdto.getFood())); //, 로 묶어서 하나의 문자열로 나타낸다.
			}
			else {
				pstmt.setString(4, null);
			}
			
			n = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		return n;
		
	}// end of public int personRegister(PersonDTO_02 psdto)----------------------------------------

	
	// tbl_person_interest 테이블에 저장되어진 행을 select 해주는 추상메소드(미완성메소드)
	@Override
	public List<PersonDTO_02> selectAll() throws SQLException {
		
		List<PersonDTO_02> personList = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select seq, name, school, color, food, to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') AS registerday "
					   + " from tbl_person_interest "
					   + " order by seq ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				PersonDTO_02 psdto = new PersonDTO_02();
				psdto.setSeq(rs.getInt(1));
				psdto.setName(rs.getString(2));
				psdto.setSchool(rs.getString(3));
				psdto.setColor(rs.getString(4));
				
				String food = rs.getString(5);
				if(food != null) {
					psdto.setFood( food.split("\\,"));
				}
				else {
					psdto.setFood(null);
				}
				
				psdto.setRegisterday(rs.getString(6));
				
				personList.add(psdto);
								
			}// end of while----------------------------------------------------------
			
		} finally {
			close();
		}
		
		return personList;
	}// end of public List<PersonDTO_02> selectAll()------------------------------------------------
	
	
	
}
