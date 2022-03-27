package common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IndexController extends AbstractController {
	
	@Override
	public String toString() {
		return "=== 확인용 IndexController 클래스의 인스턴스 메소드인 toString() 호출함 === ";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		/*
			super.setRedirect(false);
			this.setRedirect(false);
			setRedirect(false);
			// 디폴트값이 false이므로 굳이 할 필요가 없다
		*/
	//	super.setRedirect(false);
		super.setViewPage("/WEB-INF/index.jsp");
	}

}
