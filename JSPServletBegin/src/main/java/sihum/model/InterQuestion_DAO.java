package sihum.model;

import java.sql.SQLException;
import java.util.*;

public interface InterQuestion_DAO {

    // 과목번호 및 과목회차에 따른 시험문제 가져오기 메소드
    List<Question_VO> question_list(Map<String,String> paraMap) throws SQLException;
	
	// 특정 시험문항의 정답보기 가져오기 메소드	
    List<String> answers_list(String question_no) throws SQLException;
	
}
