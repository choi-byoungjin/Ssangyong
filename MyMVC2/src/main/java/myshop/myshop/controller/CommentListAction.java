package myshop.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import common.controller.AbstractController;
import myshop.model.*;

public class CommentListAction extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String fk_pnum = request.getParameter("fk_pnum");
		
		InterProductDAO pdao = new ProductDAO();
		List<PurchaseReviewsVO> commentList = pdao.commentList(fk_pnum);
		
		JSONArray jsArr = new JSONArray();
		
		if(commentList.size() > 0) {
			
			for(PurchaseReviewsVO reviewsVO : commentList) {
				JSONObject jsObj = new JSONObject();
				jsObj.put("review_seq", reviewsVO.getReview_seq()); // dao에서 set 되어져있ㄷ음
				jsObj.put("userid", reviewsVO.getFk_userid());
				jsObj.put("name", reviewsVO.getMvo().getName());
				jsObj.put("contents", reviewsVO.getContents());
				jsObj.put("writeDate", reviewsVO.getWriteDate());
				
				jsArr.put(jsObj);
			}// end of for------------------------
		}
		
		String json = jsArr.toString(); 
		// [] 또는 		
		// [{"contents":"후기테스트","review_seq":1,"name":"최병진","writeDate":"2022-04-13 15:19:51","userid":"choibj"}]
		
		request.setAttribute("json", json);

	//	super.setRedirect(false);
		super.setViewPage("/WEB-INF/jsonview.jsp");
	}

}
