package chap02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

public class PostMethod_02 extends HttpServlet { // java 이면서 servlet 인 것

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("~~~ 확인용 : doGet 메소드가 호출됨 ~~~");
		
		
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
			
		System.out.println("### 확인용 : doPost 메소드가 호출됨 ###");
				
		/*
	        post 방식으로 넘어온 데이터중 영어는 글자가 안깨지지만,
           한글은 글자모양이 깨져나온다.
           그래서  post 방식에서 넘어온 한글 데이터가 글자가 안깨지게 하려면 
           아래처럼 request.setCharacterEncoding("UTF-8"); 을 해야 한다.
           주의할 것은 request.getParameter("변수명"); 보다 먼저 기술을 해주어야 한다는 것이다.      
	     */
		
		request.setCharacterEncoding("UTF-8"); // POST 방식으로 넘어온 데이터는 한글일 수 있다.
		
		// HttpServletRequest request 객체는 전송되어져온 데이터를 처리해주는 용도로 쓰인다.
		String name = request.getParameter("name");
		String school= request.getParameter("school");
		String color = request.getParameter("color");
		
		String[] arrFood = request.getParameterValues("food");
		String food = "";
		
		if(arrFood != null) {
			food = String.join(",", arrFood);
		}
		else {
			// 좋아하는 음식이 1개도 없을 경우
			food = "좋아하는 음식이 없습니다.";
		}				
		
		/*
			// === 8. String.join("구분자", 문자열배열명) ===
		    // 문자열배열을 "구분자"로 합쳐서 String 타입으로 돌려주는 것이다.
			   String[] nameArr = {"한석규","두석규","세석규","네석규","오석규"};  
			   String names = String.join("-", nameArr);
			   System.out.println(names);
		    // "한석규","두석규","세석규","네석규","오석규"
	    */
		
		// *** 콘솔에 출력하기 시작 *** //
		System.out.println("name => " + name);
		System.out.println("school => " + school);
		System.out.println("color => " + color);
		System.out.println("food => " + food);
					
		// *** 콘솔에 출력하기 끝 *** //
		
		
		// *** 웹 브라우저에 출력하기 시작 *** //
		// HttpServeletResponse request 객체는 넘어온 데이터를 조작해서 결과물을 나타내고자 할 때 쓰인다.
		response.setContentType("text/html; charset=UTF-8"); // 웹페이지에 응답을 할 수 있게 된다. // 전체 내용물이 한글로 되어있다.
		
		PrintWriter out = response.getWriter(); // 웹서비스인지 알려준다. // IO 임포트 하고 예외처리 던져준다.
		// out 은 웹브라우저에 기술하는 대상체라고 생각하자.
		
		out.println("<html>");
		out.println("<head><title>개인 성향 테스트 결과화면</title></head>");
		out.println("<body>");
		out.println("<h2>개인 성향 테스트 결과(POST)</h2>");
		out.printf("<span style='color: red; font-weight: bold;'> %s</span>님의 개인성향은<br/><br/>", name);
		
		if(color != null) {
			out.printf("학력은 %s이며, %s색을 좋아합니다.<br/><br/>", school, color);
		}
		else {
			out.printf("학력은 %s이며, 좋아하는 색이 없습니다.<br/><br/>", school);
		}
		/*
		 	%d ==> decimal	정수
		 	%f ==> float	실수
			%s ==> string	문자열
		 */
		
		out.println("좋아하는 음식은 " + food);
		
		out.println("</body>");
		out.println("</html>");
		
		// *** 웹 브라우저에 출력하기 끝 *** //		
	}
}
