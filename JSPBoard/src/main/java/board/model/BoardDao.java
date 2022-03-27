package board.model;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.JdbcUtil;

public class BoardDao implements InterBoardDao {

	private DataSource ds;    // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public BoardDao() {
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/erdtest_oracle");
		  
		} catch(NamingException e) {
			e.printStackTrace();
		} 
	}
	
	private void close() {
		try {
			if(rs != null)    {rs.close();	  rs = null;}
			if(pstmt != null) {pstmt.close(); pstmt = null;}
			if(conn != null)  {conn.close();  conn = null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 삽입(C)
	@Override
	public int insert(BoardVo vo) throws SQLException {
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into BOARD (NUM, TITLE, WRITER, CONTENT, REGDATE, CNT) "
					+ " values (BOARD_SEQ.nextval, ?, ?, ?, sysdate, 0) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			
			result = pstmt.executeUpdate(); // 잘 들어가면 숫자가 바뀔 것이다
			
		} finally {
			close();
		}
		
		return result;
	}
	
	// 조회(R)
	@Override
	public ArrayList<BoardVo> selectAll() throws SQLException {
		
		
		ArrayList<BoardVo> boardList = new ArrayList<BoardVo>();
		
		try {
			conn = ds.getConnection();
			
			String sql = "select \"NUM\", \"TITLE\", \"WRITER\", \"CONTENT\", \"REGDATE\", \"CNT\" from \"BOARD\" order by NUM desc ";
		
			pstmt = conn.prepareStatement(sql);			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo board = new BoardVo();
				board.setNum(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setRegdate(rs.getString(5));
				board.setCnt(rs.getInt(6));
				boardList.add(board);
			}
		} finally {
			close();
		}
		return boardList;
	}
	
	// 조건 조회(R)
	@Override
	public BoardVo selectOne(int num) throws SQLException {
			
		BoardVo board = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = "select \"NUM\", \"TITLE\", \"WRITER\", \"CONTENT\", \"REGDATE\", \"CNT\" from \"BOARD\" where \"NUM\"=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVo();				
				board.setNum(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setRegdate(rs.getString(5));
				board.setCnt(rs.getInt(6));		
			}
		} finally {
			close();
		}
		return board;
	}
	
	// 수정(U)
	public int update(BoardVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update \"BOARD\" set \"TITLE\"=?, \"CONTENT\"=? where \"NUM\"=?";
		int ret = -1; // 예외 발생시 리턴값
		try {
		//	con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());
			ret = pstmt.executeUpdate(); // 잘 들어가면 숫자가 바뀔 것이다
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				} // 풀에 반환
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				} // 풀에 반환
			}
		}
		return ret;
	}
	
	public int updateCnt(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update \"BOARD\" set \"CNT\"=\"CNT\"+1 where \"NUM\"=?";
		int ret = -1; // 예외 발생시 리턴값
		try {
		//	con = ju.getConnection();
			pstmt = con.prepareStatement(query);			
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate(); // 잘 들어가면 숫자가 바뀔 것이다
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				} // 풀에 반환
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				} // 풀에 반환
			}
		}
		return ret;
	}
	
	// 삭제(D)
	public int delete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "delete from BOARD where NUM = ? ";
		int ret = -1; // 예외 발생시 리턴값
		try {
		//	con = ju.getConnection();
			pstmt = con.prepareStatement(query);			
			pstmt.setInt(1, num);
			ret = pstmt.executeUpdate(); // 잘 들어가면 숫자가 바뀔 것이다
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				} // 풀에 반환
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				} // 풀에 반환
			}
		}
		return ret;
	}

	// 조회수 증가
	@Override
	public boolean updateCount(int noticeNo) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
