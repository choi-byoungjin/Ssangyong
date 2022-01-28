package jdbc.day03.board;

import java.util.*;

public interface InterBoardDAO {

	void close(); // 자원반납하기
	
	List<BoardDTO> boardList(); // 글목록보기

	BoardDTO viewContents(Map<String, String> paraMap); // 글내용보기

	int write(BoardDTO board); // 글쓰기
	
	int write_comment(BoardCommentDTO comment); // 댓글쓰기

	List<BoardCommentDTO> commentList(String boardno); // 원글번호에 딸린 댓글보여주기

	
	
}
