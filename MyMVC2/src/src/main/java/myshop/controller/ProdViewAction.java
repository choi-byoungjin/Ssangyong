package myshop.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractController;
import myshop.model.*;

public class ProdViewAction extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 카테고리 목록을 조회해오기
		super.getCategoryList(request);
		
		String pnum = request.getParameter("pnum"); // 제품번호
		
		InterProductDAO pdao = new ProductDAO();
		
		// 제품번호를 가지고서 해당 제품의 정보를 조회해오기 
		ProductVO pvo = pdao.selectOneProductByPnum(pnum);
		
		
		// 제품번호를 가지고서 해당 제품의 추가된 이미지 정보를 조회해오기
		List<String> imgList = pdao.getImagesByPnum(pnum); // 파일이름만 가져오므로 string 하나
		
		
		
	}

}
