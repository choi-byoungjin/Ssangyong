package board;

import java.sql.*;
import java.util.*;
import java.util.Date;
import common.JdbcUtil;

public class BoardDao {

	private JdbcUtil ju;
	
	public BoardDao() {
		ju = JdbcUtil.getInstance();
	}
	
	// 삽입(C)
	public int insert(BoardVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into BOARD (NUM, TITLE, WRITER, CONTENT, REGDATE, CNT) "
					+ " values (BOARD_SEQ.nextval, ?, ?, ?, sysdate, 0) ";
		int ret = -1; // 예외 발생시 리턴값
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
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
	
	// 조회(R)
	public List<BoardVo> selectAll() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "select \"NUM\", \"TITLE\", \"WRITER\", \"CONTENT\", \"REGDATE\", \"CNT\" from \"BOARD\" order by NUM desc ";
		ArrayList<BoardVo> ls = new ArrayList<BoardVo>();
		try {
			con = ju.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				BoardVo vo = new BoardVo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						new Date(rs.getDate(5).getTime()),
						rs.getInt(6));
				ls.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				} // 풀에 반환
			}
			if(stmt != null) {
				try {
					stmt.close();
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
		return ls;
	}
	
	// 조건 조회(R)
	public BoardVo selectOne(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo vo = null;
		String query = "select \"NUM\", \"TITLE\", \"WRITER\", \"CONTENT\", \"REGDATE\", \"CNT\" from \"BOARD\" where \"NUM\"=?";		
		try {
			con = ju.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs = pstmt.executeQuery(query);
			if(rs.next()) {
				updateCnt(num);	// 조회수 증가
				vo = new BoardVo(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						new Date(rs.getDate(5).getTime()),
						rs.getInt(6));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {					
					e.printStackTrace();
				} // 풀에 반환
			}
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
		return vo;
	}
	
	// 수정(U)
	public int update(BoardVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update \"BOARD\" set \"TITLE\"=?, \"CONTENT\"=? where \"NUM\"=?";
		int ret = -1; // 예외 발생시 리턴값
		try {
			con = ju.getConnection();
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
			con = ju.getConnection();
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
			con = ju.getConnection();
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
}
