package jdbc.day03.board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDAO implements InterBoardDAO {

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
		
	
   // *** 글목록보기 메소드를 구현하기 *** //
   @Override
   public List<BoardDTO> boardList() {
      
      List<BoardDTO> boardList = new ArrayList<>();
      
      try {
         
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
         
         String sql = " select B.boardno, B.subject, M.name " +
	        		  "     , to_char(B.writeday, 'yyyy-mm-dd hh24:mi:ss'), B.viewcount "+
	        		  " from jdbc_board B join jdbc_member M "+
	        		  " ON B.fk_userid = M.userid "+
	        		  " order by boardno desc ";
         
         pstmt = conn.prepareStatement(sql);
                  
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            BoardDTO board = new BoardDTO();
            
            board.setBoardno(rs.getInt(1));
            board.setSubject(rs.getString(2));
            
            MemberDTO member = new MemberDTO();
            member.setName(rs.getString(3));
            board.setMember(member);// 위의 요소를 member를 넣고 다시 board에 넣어준다?
            
            board.setSubject(rs.getString(4));
            board.setWriteday(rs.getString(5));
            
            boardList.add(board);
         }// end of while(rs.next())---------------------
         
      
      } catch(ClassNotFoundException e) {
         System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
      } catch(SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }      
      
      return boardList;
   }// end of public List<BoardDTO> boardList()---------------------

   
   // *** 글내용보기 메소드를 구현하기 *** //
   @Override
	public BoardDTO viewContents(Map<String, String> paraMap) {
		
	   BoardDTO board = null;
	   
	   try {
	         
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
	         
	         String sql = " select * "
	        		 	+ " from jdbc_board "
	        		 	+ " where boardno = ? ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, paraMap.get("boardno"));
	         
	         rs = pstmt.executeQuery();
	      
	         if(rs.next()) {
	        	 // 입력한 글번호에 해당하는 글이 존재하는 경우
	        	 
	        	 // 로그인한 사용자가 쓴 글인지 (즉, 자신이 쓴 글을 자신이 보고자 하는 경우)
	        	 // 로그인한 사용자가 쓴 글이 아닌 다른 사용자가 쓴 글인지 구분한다.
	        	 sql = " select * "
        		 	 + " from jdbc_board "
        		 	 + " where boardno = ? and fk_userid = ? ";
	        	 
	        	 pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, paraMap.get("boardno"));
		         pstmt.setString(2, paraMap.get("fk_userid"));
		         
		         rs = pstmt.executeQuery();
		         
		         if(!rs.next()) {
		        	 // 로그인한 사용자가 쓴 글이 아닌 다른 사용자가 쓴 글이라면
		        	 
		        	 sql = " update jdbc_board_set viewcount = viewcount + 1 "
		        	 	 + " where boardno = ? ";
		        	 
		        	 pstmt = conn.prepareStatement(sql);
		        	 pstmt.setString(1, paraMap.get("boardno"));
		        	 
		        	 pstmt.executeUpdate();	 
		         }
		         
		         sql = " select boardno, subject, contents, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss'), viewcount, M.name "
        		 	 + " from jdbc_board B JOIN jdbc_member M "
        		 	 + " ON B.fk_userid = M.userid "
        		 	 + " where boardno = ? ";
		         
	        	 pstmt = conn.prepareStatement(sql);
	        	 pstmt.setString(1, paraMap.get("boardno"));
	        	 
	        	 rs = pstmt.executeQuery();
	        	 
	        	 rs.next();
	        	 
	        	 board = new BoardDTO();
	        	 board.setBoardno(rs.getInt(1));
	        	 board.setSubject(rs.getString(2));
	        	 board.setContents(rs.getString(3));
	        	 board.setWriteday(rs.getString(4));
	        	 board.setViewcount(rs.getInt(5));
	        	 
	        	 MemberDTO member = new MemberDTO();
	        	 member.setName(rs.getString(6));
	        	 
	        	 board.setMember(member);
	         }
	         else {
	        	 // 입력한 글번호에 해당하는 글이 존재하지 않는 경우
	        	 System.out.println(">> 조회하고자 하는 글번호 "+ paraMap.get("boardno") +" 에 해당하는 글은 없습니다. << \n");
	         }
	         
	      } catch(ClassNotFoundException e) {
	         System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
	      } catch(SQLException e) {
	    	 if(e.getErrorCode() == 1722) {
	    		 System.out.println(">> 조회하고자 하는 글번호는 정수로만 입력하세요 << \n");
	    	 }
	    	 else {
	    		 e.printStackTrace();
	    	 }
	      } finally {
	         close();
	      }
	   
	   return board;
	}// end of public BoardDTO viewContents(Map<String, String> paraMap)-----------------------------------

}
