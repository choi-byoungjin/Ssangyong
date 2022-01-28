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
         
         String sql = " select B.boardno, B.subject, M.name "+
	        		  "      , to_char(B.writeday, 'yyyy-mm-dd hh24:mi:ss'), B.viewcount "+
	        		  "      , nvl(C.Commentcnt, 0) "+
	        		  " from jdbc_board B JOIN jdbc_member M "+
	        		  " ON B.fk_userid = M.userid "+
	        		  " LEFT JOIN (select fk_boardno, count(*) as commentcnt "+
	        		  "            from jdbc_comment "+
	        		  "            group by fk_boardno) C "+
	        		  " ON B.boardno = C.fk_boardno "+
	        		  " order by 1 desc ";
         
         pstmt = conn.prepareStatement(sql);
                  
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            BoardDTO board = new BoardDTO();
            
            board.setBoardno(rs.getInt(1));
            board.setSubject(rs.getString(2));
            
            MemberDTO member = new MemberDTO();
            member.setName(rs.getString(3));
            board.setMember(member);// 위의 요소를 member를 넣고 다시 board에 넣어준다?
            
            board.setWriteday(rs.getString(4));
            board.setViewcount(rs.getInt(5));
            
            board.setCommentcnt(rs.getInt(6));// 댓글의 개수
            
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
		        	 
		        	 sql = " update jdbc_board set viewcount = viewcount + 1 "
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

   
   // *** 글쓰기 메소드를 구현하기 ***//
   /*
    	=== Transaction 처리를 해야 하는 경우이다. ===
    	첫번째, jdbc_board(게시판) 테이블에 insert 가 성공되어지면
    	두번째, jdbc_member(회원) 테이블에 있는 point 컬럼의 값을 10 증가(update)를 해야 한다.
    	즉, jdbc_board(게시판) 테이블에 insert 와 jdbc_member(회원) 테이블의 update 가 
    		둘 모두 성공해야만 commit을 해주고, 만약에 1개라도 실패하면 rollback 을 해주어야 한다.
    */
   
   
   @Override
   public int write(BoardDTO board) {
	   
	   int result = 0;
	   
	   try {
	         
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
	         conn.setAutoCommit(false); // 트랜잭션 처리를 위해서 수동커밋으로 전환한다.
	         
	         String sql = " insert into jdbc_board(boardno, fk_userid, subject, contents, boardpasswd) "
	         			+ " values(board_seq.nextval, ?, ?, ?, ?) ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, board.getFk_userid());
	         pstmt.setString(2, board.getSubject());
	         pstmt.setString(3, board.getContents());
	         pstmt.setString(4, board.getBoardpasswd());
	         
	         int n = pstmt.executeUpdate();
	         
	         int m = 0;
	         if(n==1) {
	        	 sql = " update jdbc_member set point = point + 10 "
	        	 	 + " where userid = ? ";
	        	 
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, board.getFk_userid());
		         
		         m = pstmt.executeUpdate();
	         }
	         
	         if(m == 1) {
	        	 conn.commit(); // 트랜잭션 처리를 한 것임.
	        	 result = m;
	         }
	         
	      } catch(ClassNotFoundException e) {
	         System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
	      } catch(SQLException e) {
	    	  try {
	    		  conn.rollback(); // 트랜잭션 처리를 한 것임.
	    	  } catch(SQLException e1) { }
	      } finally {
	         close();
	      }
	   
	   return result;
   }// end of public int write(BoardDTO board)-----------------------------------------------------------

   
   
   // *** 댓글쓰기 메소드를 구현하기 ***//
   /*
    	=== Transaction 처리를 해야 하는 경우이다. ===
    	첫번째, jdbc_comment(댓글) 테이블에 insert 가 성공되어지면
    	두번째, jdbc_member(회원) 테이블에 있는 point 컬럼의 값을 5 증가(update)를 해야 한다.
    	즉, jdbc_comment(댓글) 테이블에 insert 와 jdbc_member(회원) 테이블의 update 가 
    		둘 모두 성공해야만 commit을 해주고, 만약에 1개라도 실패하면 rollback 을 해주어야 한다.
    */
	@Override
	public int write_comment(BoardCommentDTO comment) {
		
		int result = 0;
		   
	   try {
	         
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
	         conn.setAutoCommit(false); // 트랜잭션 처리를 위해서 수동커밋으로 전환한다.
	         
	         String sql = " insert into jdbc_comment(commentno, fk_boardno, fk_userid, contents) "
					   + " values(seq_comment.nextval, to_number(?), ?, ?) ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, comment.getFk_boardno());
	         pstmt.setString(2, comment.getFk_userid());
	         pstmt.setString(3, comment.getContents());
	         
	         int n = pstmt.executeUpdate();
	         
	         int m = 0;
	         
	         if(n==1) {
	        	 sql = " update jdbc_member set point = point + 5 "
	        	 	 + " where userid = ? ";
	        	 
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, comment.getFk_userid());
		         
		         m = pstmt.executeUpdate();
	         }
	         
	         if(m == 1) {
	        	 conn.commit(); // 트랜잭션 처리를 한 것임.
	        	 result = m;
	         }
	         
	      } catch(ClassNotFoundException e) {
	         System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
	      } catch(SQLException e) {
	    	  try {
	    		  if(e.getErrorCode() == 2291) {
	    			  /*
	    			   	오류 보고 -
						ORA-02291: integrity constraint (HR.FK_JDBC_COMMENT_FK_BOARDNO) violated - parent key not found
	    			   */
	    			  System.out.println(">> [오류발생] 원글번호 "+ comment.getFk_boardno() +"은 존재하지 않습니다.<<");
	    			  result = -1;
	    		  }
	    		  
	    		  conn.rollback(); // 트랜잭션 처리를 한 것임.
	    	  } catch(SQLException e1) { }
	      } finally {
	         close();
	      }
	   
	   return result;
		
	}// end of public int write_comment(BoardCommentDTO comment)-------------------------------------------------------

	
	// *** 원글번호에 딸린 댓글보여주는 메소드 구현하기 *** //
	@Override
	public List<BoardCommentDTO> commentList(String boardno) {
		
		List<BoardCommentDTO> commentList = new ArrayList<>();
	      
	    try {
	         
	       Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	       conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "cclass");
	         
	       String sql = " select C.contents, M.name, C.writeday "
		       		  + " from "
		       		  + " (select contents, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday, fk_userid "
		       		  + " from jdbc_comment "
		       		  + " where fk_boardno = 4) C JOIN jdbc_member M "
		       		  + " ON C.fk_userid = M.userid ";
	    		   
	       pstmt = conn.prepareStatement(sql);
	                  
	       rs = pstmt.executeQuery();
	         
	       while(rs.next()) {
	          
	    	   BoardCommentDTO comment = new BoardCommentDTO();
	    	   comment.setContents( rs.getString(1));
	    	   
	    	   MemberDTO member = new MemberDTO();
	    	   member.setName(sql);
	    	   
	    	   comment.setMember(member);
	    	   
	    	   comment.setWriteday( rs.getString(3));
	    	   
	       }// end of while(rs.next())---------------------
	         
	      
	    } catch(ClassNotFoundException e) {
	       System.out.println(">> ojdbc6.jar 파일이 없습니다. <<");
	    } catch(SQLException e) {
	       e.printStackTrace();
	    } finally {
	       close();
	    }      
	      
	    return commentList;
	}// end of public List<BoardCommentDTO> commentList(String boardno)-----------------------------------------------

}
