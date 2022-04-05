package myshop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;

public class MallHome2Action extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 카테고리 목록을 가져오기
		super.getCategoryList(request);
		
	//	super.setRedirect(false);
		super.setViewPage("/WEB-INF/myshop/mallHome2.jsp");
		
	}

}
