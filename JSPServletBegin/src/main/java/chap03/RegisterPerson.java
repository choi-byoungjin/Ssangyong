package chap03;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registerPerson.do")
public class RegisterPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getMethod(); // "GET" 또는 "POST"
		
	//	System.out.println("확인용 method => " + method);
		
		if("POST".equalsIgnoreCase(method)) {	// equalsIgnoreCase 대소문자 구분않는다.
			// POST 방식으로 들어온 경우
			
			String name = request.getParameter("name");
			String school = request.getParameter("school");
			String color = request.getParameter("color");
			String[] arrFood = request.getParameterValues("food"); // 체크박스는 다중상태이기 때문
			
			String foodes = String.join(",", arrFood);
		/*	
			System.out.println("확인용 name => " + name);
			System.out.println("확인용 school => " + school);
			System.out.println("확인용 color => " + color);
			System.out.println("확인용 foodes => " + foodes);
		*/
			
			Map<String, String> paraMap = new HashMap<>();	// Map
			paraMap.put("name", name);
			paraMap.put("school", school);
			paraMap.put("color", color);
			paraMap.put("foodes", foodes);
			
			request.setAttribute("paraMap", paraMap);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/chap03_StandardAction/04_forwardForm_view_02.jsp"); // jsp 파일에 paraMap 넘긴다 이 jsp 파일만 paraMap을 꺼내볼 수 있다.
			dispatcher.forward(request, response);
			
		}
		else {
			// http://localhost:9090/JSPServletBegin/registerPerson.do?name=엄정화&school=초등학교졸&color=검정&food=라면&food=떡복이&food=치킨
			// GET 방식으로 들어온 경우
			RequestDispatcher dispatcher = request.getRequestDispatcher("/chap03_StandardAction/04_forwardForm_error_03.jsp"); // 에러페이지 띄운다
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
