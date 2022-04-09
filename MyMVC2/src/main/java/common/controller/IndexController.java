package common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myshop.model.ImageVO;
import myshop.model.InterProductDAO;
import myshop.model.ProductDAO;

public class IndexController extends AbstractController {
	
	@Override
	public String toString() {
		return "=== 확인용 IndexController 클래스의 인스턴스 메소드인 toString() 호출함 === ";
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 로그인 또는 로그아웃을 하면 시작페이지로 가는 것이 아니라 방금 보았던 그 페이지로 그대로 가기 위한 것임.
		super.goBackURL(request);
		
		InterProductDAO pdao = new ProductDAO();
		List<ImageVO> imgList = pdao.imageSelectAll();
		
		request.setAttribute("imgList", imgList);
		
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
