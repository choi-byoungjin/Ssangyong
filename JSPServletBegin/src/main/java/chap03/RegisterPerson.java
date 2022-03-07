package chap03;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registerPerson.do")
public class RegisterPerson extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String school = request.getParameter("school");
		String color = request.getParameter("color");
		String[] arrFood = request.getParameterValues("food"); // 체크박스는 다중상태이기 때문
		
		String foodes = String.join(",", arrFood);
		
		System.out.println("확인용 name => " + name);
		System.out.println("확인용 school => " + school);
		System.out.println("확인용 color => " + color);
		System.out.println("확인용 foodes => " + foodes);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
