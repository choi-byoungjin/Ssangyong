package chap05;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/personDetail.do")
public class PersonDetail_06 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private InterPersonDAO_03 dao = new PersonDAO_04();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String seq = request.getParameter("seq"); /* personSelectAll 에서 넘겨준 seq */
		
		String path = "";
		
		try { // dao에서 예외 던졌으므로 예외처리 필요하다.
			PersonDTO_02 psdto = dao.selectOne(seq);
			request.setAttribute("psdto", psdto);
			
			if(psdto != null) {
				path = "/WEB-INF/chap05_ok/personDetail.jsp";
			}
			else {
				path = "/WEB-INF/chap05_ok/personDetail_funStop.jsp";
			}
				
		} catch (SQLException e) {			
			e.printStackTrace();
			path = "/WEB-INF/chap05_ok/personRegister_fail.jsp"; /* fail 따로 만들어두기 */
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
