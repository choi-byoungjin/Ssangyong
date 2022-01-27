package jdbc.day03.board;

import java.util.*;

public interface InterBoardDAO {

	void close(); // 자원반납하기
	
	List<BoardDTO> boardList(); // 글목록보기

	BoardDTO viewContents(Map<String, String> paraMap); // 글내용보기

	
	
}
