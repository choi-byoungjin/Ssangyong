package board.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InterBoardDao {

	int insert(BoardVo vo) throws SQLException;

	ArrayList<BoardVo> selectAll() throws SQLException;

	BoardVo selectOne(int num) throws SQLException;
	
	int updateCnt(int num) throws SQLException;

	int delete(int num) throws SQLException;
}
