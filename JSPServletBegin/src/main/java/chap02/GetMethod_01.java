package chap02;

import javax.servlet.http.*;

public class GetMethod_01 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("~~~ 확인용 : doGet 메소드가 호출됨 ~~~");
		
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
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
			
		System.out.println("### 확인용 : doPost 메소드가 호출됨 ###");
		
	}
}
