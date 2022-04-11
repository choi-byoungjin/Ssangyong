package myshop.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface InterProductDAO {
	
	// 시작(메인)페이지에 보여주는 상품이미지파일명을 모두 조회(select)하는 메소드	
	// DTO(Data Transfer Object) == VO(Value Object)
	List<ImageVO> imageSelectAll() throws SQLException;

	// tbl_category 테이블에서 카테고리 대분류 번호(cnum), 카테고리코드(code), 카테고리명(cname)을 조회해오기 
	// VO 를 사용하지 않고 Map 으로 처리해보겠습니다.
	List<HashMap<String, String>> getCategoryList() throws SQLException;

	// Ajax(JSON)를 사용하여 상품목록을 "더보기" 방식으로 페이징처리 해주기 위해  스펙별로 제품의 전체개수 알아오기 //
	int totalPspecCount(String string) throws SQLException;

	// Ajax(JSON)를 사용하여 더보기 방식(페이징처리) 으로 상품정보를 8개씩 잘라서(start ~ end) 조회해오기
	List<ProductVO> selectBySpecName(Map<String, String> paraMap) throws SQLException;

	// spec 목록을 보여주고자 한다.
	List<SpecVO> selectSpecList() throws SQLException;

	// 제품번호 채번 해오기
	int getPnumOfProduct() throws SQLException;
	
	// tbl_product 테이블에 제품정보 insert 하기
	int productInsert(ProductVO pvo) throws SQLException;

	// tbl_product_imagefile 테이블에 insert 하기
	int product_imagefile_Insert(Map<String, String> paraMap) throws SQLException;

	// 제품번호를 가지고서 해당 제품의 정보를 조회해오기 
	ProductVO selectOneProductByPnum(String pnum) throws SQLException;

	// 제품번호를 가지고서 해당 제품의 추가된 이미지 정보를 조회해오기
	List<String> getImagesByPnum(String pnum) throws SQLException;

	// 제품번호를 가지고서 해당 제품의 제품설명서 첨부파일의 서버에 업로드 되어진 파일명과 오리지널 파일명을 조회해오기
	Map<String, String> getPrdmanualFileName(String pnum) throws SQLException;

	// 장바구니 담기 
	// 장바구니 테이블에 해당 제품이 존재하지 않는 경우에는 tbl_cart 테이블에 insert 를 해야하고,
	// 장바구니 테이블에 해당 제품이 존재하는 경우에는 또 그 제품을 추가해서 장바구니 담기를 한다라면 tbl_cart 테이블에 update 를 해야 한다.
	int addCart(Map<String, String> paraMap) throws SQLException;

	// 로그인한 사용자의 장바구니 목록을 조회하기 
	List<CartVO> selectpProductCart(String userid) throws SQLException;

	// 장바구니 테이블에서 특정제품을 제거하기
	int delCart(String cartno) throws SQLException;

	// 장바구니 테이블에서 특정제품의 주문량을 변경하기
	int updateCart(Map<String, String> paraMap) throws SQLException;

	// 로그인한 사용자의 장바구니에 담긴 주문총액합계 및 총포인트합계 알아오기
	Map<String, String> selectCartSumPricePoint(String userid) throws SQLException;

	// 주문번호(시퀀스 seq_tbl_order 값)을 채번해온 것.
	int getSeq_tbl_order() throws SQLException;

	
	
	// ===== Transaction 처리하기 ===== // 
    // 1. 주문 테이블에 입력되어야할 주문전표를 채번(select)하기 
    // 2. 주문 테이블에 채번해온 주문전표, 로그인한 사용자, 현재시각을 insert 하기(수동커밋처리)
    // 3. 주문상세 테이블에 채번해온 주문전표, 제품번호, 주문량, 주문금액을 insert 하기(수동커밋처리)
    // 4. 제품 테이블에서 제품번호에 해당하는 잔고량을 주문량 만큼 감하기(수동커밋처리) 
    
    // 5. 장바구니 테이블에서 cartnojoin 값에 해당하는 행들을 삭제(delete OR update)하기(수동커밋처리) 
    // >> 장바구니에서 주문을 한 것이 아니라 특정제품을 바로주문하기를 한 경우에는 장바구니 테이블에서 행들을 삭제할 작업은 없다. << 

    // 6. 회원 테이블에서 로그인한 사용자의 coin 액을 sumtotalPrice 만큼 감하고, point 를 sumtotalPoint 만큼 더하기(update)(수동커밋처리) 
    // 7. **** 모든처리가 성공되었을시 commit 하기(commit) **** 
    // 8. **** SQL 장애 발생시 rollback 하기(rollback) **** 
	int orderAdd(Map<String, Object> paraMap) throws SQLException;

}
