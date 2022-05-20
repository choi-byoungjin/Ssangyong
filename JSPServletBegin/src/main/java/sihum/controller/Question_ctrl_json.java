package sihum.controller;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import sihum.model.*;


@WebServlet("/sihum/question_json.do")
public class Question_ctrl_json extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String subject_no = "1";  // 과목번호
			String test_round = "1";  // 과목회차 
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("subject_no", subject_no);
			paraMap.put("test_round", test_round);
			
			InterQuestion_DAO dao = new Question_DAO();
			List<Question_VO> question_list = dao.question_list(paraMap);
			
			JSONArray json_arr = new JSONArray();
			
			for(Question_VO questionvo : question_list) { 
				JSONObject json = new JSONObject();
				json.put("question", questionvo.getQuestion());
				
				List<String> answers_list = dao.answers_list(questionvo.getQuestion_no()); 
				JSONObject json_answers = new JSONObject();
				for(int i=0; i<answers_list.size(); i++) {
					json_answers.put(String.valueOf(i+1), answers_list.get(i));
				}// end of for---------------------------
				json.put("answers", json_answers);
				
				json.put("correct", questionvo.getCorrect());
				
				json_arr.put(json);
			}// end of for-------------------------------
			
			String str_json = json_arr.toString();
			
			// **** 웹브라우저에 출력하기 시작 **** //
			// HttpServletResponse response 객체는 넘어온 데이터를 조작해서 결과물을 나타내고자 할때 쓰인다.
			response.setContentType("text/html; charset=UTF-8");
							
			PrintWriter out = response.getWriter();
			// out 은 웹브라우저에 기술하는 대상체라고 생각하자.
					
			out.print(str_json);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
