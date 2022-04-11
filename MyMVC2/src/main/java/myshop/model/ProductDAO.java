package myshop.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements InterProductDAO {

	private DataSource ds;    // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 기본생성자
	public ProductDAO() {
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/mymvc_oracle");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	// 자원반납 해주는 메소드
	private void close() {
		
		try {
			if(rs != null)    {rs.close();	  rs = null;}
			if(pstmt != null) {pstmt.close(); pstmt = null;}
			if(conn != null)  {conn.close();  conn = null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}// end of private void close()-------------------------------------
	
	
	
	// 시작(메인)페이지에 보여주는 상품이미지파일명을 모두 조회(select)하는 메소드
	@Override
	public List<ImageVO> imageSelectAll() throws SQLException {
		
		List<ImageVO> imgList = new ArrayList<>();
		
		try {
			 conn = ds.getConnection();
			 
			 String sql = " select imgno, imgfilename "+
					      " from tbl_main_image "+
					      " order by imgno asc ";
			 
			 pstmt = conn.prepareStatement(sql);
			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 ImageVO imgvo = new ImageVO();
				 imgvo.setImgno(rs.getInt(1));
				 imgvo.setImgfilename(rs.getString(2));
				 
				 imgList.add(imgvo);
			 }// end of while(rs.next())-------------------------------
			 
		} finally {
			close();
		}
		
		return imgList;
		
	}// end of public List<ImageVO> imageSelectAll()----------------------------


	// tbl_category 테이블에서 카테고리 대분류 번호(cnum), 카테고리코드(code), 카테고리명(cname)을 조회해오기 
	// VO 를 사용하지 않고 Map 으로 처리해보겠습니다.
	@Override
	public List<HashMap<String, String>> getCategoryList() throws SQLException {
		
		List<HashMap<String, String>> categoryList = new ArrayList<>();
		
		try {
			 conn = ds.getConnection();
			 
			 String sql = " select cnum, code, cname "+
					      " from tbl_category "+
					      " order by cnum asc ";
			 
			 pstmt = conn.prepareStatement(sql);
			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 HashMap<String, String> map = new HashMap<>();
				 map.put("cnum", rs.getString(1));
				 map.put("code", rs.getString(2));
				 map.put("cname", rs.getString(3));
				 
				 categoryList.add(map);
			 }// end of while(rs.next())-------------------------------
			 
		} finally {
			close();
		}
		
		return categoryList;
	}


	// Ajax(JSON)를 사용하여 상품목록을 "더보기" 방식으로 페이징처리 해주기 위해 스펙별로 제품의 전체개수 알아오기 // 
	@Override
	public int totalPspecCount(String fk_snum) throws SQLException {
		
		int totalCount = 0;
		
		try {
			 conn = ds.getConnection();
			 
			 String sql = " select count(*) "+
					      " from tbl_product "+
					      " where fk_snum = ? ";
			 
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, fk_snum);
			 
			 rs = pstmt.executeQuery();
			 
			 rs.next();
			 
			 totalCount = rs.getInt(1);
			 
		} finally {
			close();
		}
		
		return totalCount;
	}


	// Ajax(JSON)를 사용하여 더보기 방식(페이징처리)으로 상품정보를 8개씩 잘라서(start ~ end) 조회해오기  
	@Override
	public List<ProductVO> selectBySpecName(Map<String, String> paraMap) throws SQLException {
		
		List<ProductVO> prodList = new ArrayList<>();
		
		try {
			 conn = ds.getConnection();
			 
			 String sql = " select pnum, pname, code, pcompany, pimage1, pimage2, pqty, price, saleprice, sname, pcontent, point, pinputdate "
				 		+ " from "
				 		+ " ( "
				 		+ "    select row_number() over(order by P.pnum desc) AS RNO "
				 		+ "         , P.pnum, P.pname, C.code, P.pcompany, P.pimage1, P.pimage2, P.pqty, P.price, P.saleprice, S.sname, P.pcontent, P.point "  
				 		+ "         , to_char(P.pinputdate, 'yyyy-mm-dd') AS pinputdate "
				 		+ "    from tbl_product P "
				 		+ "    JOIN tbl_category C "
				 		+ "    ON P.fk_cnum = C.cnum "
				 		+ "    JOIN tbl_spec S "
				 		+ "    ON P.fk_snum = S.snum "
				 		+ "    where S.sname = ? "
				 		+ " ) V "
				 		+ " where V.RNO between ? and ? ";
			 
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, paraMap.get("sname"));
			 pstmt.setString(2, paraMap.get("start"));
			 pstmt.setString(3, paraMap.get("end"));
			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 
				 ProductVO pvo = new ProductVO();
				 
				 pvo.setPnum(rs.getInt(1));     // 제품번호
				 pvo.setPname(rs.getString(2)); // 제품명
				 
				 CategoryVO categvo = new CategoryVO(); 
				 categvo.setCode(rs.getString(3)); 
				 
				 pvo.setCategvo(categvo);           // 카테고리코드 
				 pvo.setPcompany(rs.getString(4));  // 제조회사명
				 pvo.setPimage1(rs.getString(5));   // 제품이미지1   이미지파일명
				 pvo.setPimage2(rs.getString(6));   // 제품이미지2   이미지파일명
				 pvo.setPqty(rs.getInt(7));         // 제품 재고량
				 pvo.setPrice(rs.getInt(8));        // 제품 정가
				 pvo.setSaleprice(rs.getInt(9));    // 제품 판매가(할인해서 팔 것이므로)
					
				 SpecVO spvo = new SpecVO(); 
				 spvo.setSname(rs.getString(10)); 
				 
				 pvo.setSpvo(spvo); // 스펙 
					
				 pvo.setPcontent(rs.getString(11));	  // 제품설명
				 pvo.setPoint(rs.getInt(12));         // 포인트 점수
				 pvo.setPinputdate(rs.getString(13)); // 제품입고일자
				 
				 prodList.add(pvo);
			 }// end of while(rs.next())-------------------------------
			 
		} finally {
			close();
		}
		
		return prodList;
	}

	
	// spec 목록을 보여주고자 한다.
	@Override
	public List<SpecVO> selectSpecList() throws SQLException {
		
		List<SpecVO> specList = new ArrayList<>();
		
		try {
			 conn = ds.getConnection();
			 
			 String sql = " select snum, sname "+
					      " from tbl_spec "+
					      " order by snum asc";
			 
			 pstmt = conn.prepareStatement(sql);			 
			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 SpecVO spvo = new SpecVO();
				 spvo.setSnum(rs.getInt(1));
				 spvo.setSname(rs.getString(2));
				 specList.add(spvo);
			 }
			 
		} finally {
			close();
		}
		
		return specList;
	}

	
	// 제품번호 채번 해오기
	@Override
	public int getPnumOfProduct() throws SQLException {
		
		int pnum = 0;
		
		try {
			 conn = ds.getConnection();
			 
			 String sql = " select seq_tbl_product_pnum.nextval "
			 			+ " from dual ";
			 
			 pstmt = conn.prepareStatement(sql);			 
			 
			 rs = pstmt.executeQuery();			 
			 
			 rs.next();
			 pnum = rs.getInt(1);
			 
		} finally {
			close();
		}
		
		return pnum;
		
	}


	// tbl_product 테이블에 제품정보 insert 하기
	@Override
	public int productInsert(ProductVO pvo) throws SQLException {

		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " insert into tbl_product(pnum, pname, fk_cnum, pcompany, pimage1, pimage2, prdmanual_systemFileName, prdmanual_orginFileName, pqty, price, saleprice, fk_snum, pcontent, point) "
						+ " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pvo.getPnum());
			
			pstmt.setString(2, pvo.getPname());
	        pstmt.setInt(3, pvo.getFk_cnum());    
	        pstmt.setString(4, pvo.getPcompany()); 
	        pstmt.setString(5, pvo.getPimage1());    
	        pstmt.setString(6, pvo.getPimage2()); 
	        pstmt.setString(7, pvo.getPrdmanual_systemFileName());
	        pstmt.setString(8, pvo.getPrdmanual_orginFileName());
	        pstmt.setInt(9, pvo.getPqty()); 
	        pstmt.setInt(10, pvo.getPrice());
	        pstmt.setInt(11, pvo.getSaleprice());
	        pstmt.setInt(12, pvo.getFk_snum());
	        pstmt.setString(13, pvo.getPcontent());
	        pstmt.setInt(14, pvo.getPoint());
			
	        result = pstmt.executeUpdate();
	        
		} finally {
			close();
		}
		
		return result;
	}

	
	// tbl_product_imagefile 테이블에 insert 하기
	@Override
	public int product_imagefile_Insert(Map<String, String> paraMap) throws SQLException {

		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " insert into tbl_product_imagefile(imgfileno, fk_pnum, imgfilename) " + 
                    	 " values(seqImgfileno.nextval, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(paraMap.get("pnum")));
			pstmt.setString(2, paraMap.get("attachFileName"));
			
	        result = pstmt.executeUpdate();
	        
		} finally {
			close();
		}
		
		return result;
		
	}

	
	// 제품번호를 가지고서 해당 제품의 정보를 조회해오기 
	@Override
	public ProductVO selectOneProductByPnum(String pnum) throws SQLException {

		ProductVO pvo = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select S.sname, pnum, pname, pcompany, price, saleprice, point, pqty, pcontent, pimage1, pimage2, prdmanual_systemFileName, nvl( prdmanual_orginFileName, '없음' ) AS prdmanual_orginFileName "
					   + " from "
					   + " ( "
					   + " select fk_snum, pnum, pname, pcompany, price, saleprice, point, pqty, pcontent, pimage1, pimage2, prdmanual_systemFileName, prdmanual_orginFileName "
					   + " from tbl_product "
					   + " where pnum = ? "
					   + " ) P JOIN tbl_spec S "
					   + " ON P.fk_snum = S.snum ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pnum);
			
			rs = pstmt.executeQuery();
						
			if( rs.next() ) {
				
				String sname = rs.getString(1);     				// "HIT", "NEW", "BEST" 값을 가짐 
				int    npnum = rs.getInt(2);        				// 제품번호
				String pname = rs.getString(3);     				// 제품명
				String pcompany = rs.getString(4);  				// 제조회사명
				int    price = rs.getInt(5);        				// 제품 정가
				int    saleprice = rs.getInt(6);    				// 제품 판매가
				int    point = rs.getInt(7);        				// 포인트 점수
				int    pqty = rs.getInt(8);         				// 제품 재고량
				String pcontent = rs.getString(9);  				// 제품설명
				String pimage1 = rs.getString(10);  				// 제품이미지1
				String pimage2 = rs.getString(11);  				// 제품이미지2
				String prdmanual_systemFileName = rs.getString(12); // 파일서버에 업로드되어지는 실제 제품설명서 파일명
				String prdmanual_orginFileName = rs.getString(13);  // 웹클라이언트의 웹브라우저에서 파일을 업로드 할때 올리는 제품설명서 파일명
				
				pvo = new ProductVO();
				
				SpecVO spvo = new SpecVO();
				spvo.setSname(sname);
				
				pvo.setSpvo(spvo);
				pvo.setPnum(npnum);
	            pvo.setPname(pname);
	            pvo.setPcompany(pcompany);
	            pvo.setPrice(price);
	            pvo.setSaleprice(saleprice);
	            pvo.setPoint(point);
	            pvo.setPqty(pqty);
	            pvo.setPcontent(pcontent);
	            pvo.setPimage1(pimage1);
	            pvo.setPimage2(pimage2);
	            pvo.setPrdmanual_systemFileName(prdmanual_systemFileName);
	            pvo.setPrdmanual_orginFileName(prdmanual_orginFileName);
				
			} // end of if( rs.next() )--------------------------------------------
			
		} finally {
			close();
		}
		
		return pvo;
	}


	// 제품번호를 가지고서 해당 제품의 추가된 이미지 정보를 조회해오기
	@Override
	public List<String> getImagesByPnum(String pnum) throws SQLException {
 
		List<String> imgList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select imgfilename "
					   + " from tbl_product_imagefile "
					   + " where fk_pnum = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pnum);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				
				String imgfilename = rs.getString(1); // 이미지파일명				
				imgList.add(imgfilename);
				
			}// end of while---------------------------------------------------------
			
		} finally {
			close();
		}
		
		return imgList;
	}

	
	// 제품번호를 가지고서 해당 제품의 제품설명서 첨부파일의 서버에 업로드 되어진 파일명과 오리지널 파일명을 조회해오기
	@Override
	public Map<String, String> getPrdmanualFileName(String pnum) throws SQLException {

		Map<String, String> map = new HashMap<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select prdmanual_systemFileName, prdmanual_orginFileName "
						+ " from tbl_product "
						+ " where pnum = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pnum);
			
			rs = pstmt.executeQuery();
			
			if ( rs.next() ) {
											
				map.put("prdmanual_systemFileName", rs.getString(1));
				// 파일서버에 업로드되어지는 실제 제품설명서 파일명
				
				map.put("prdmanual_orginFileName", rs.getString(2));
				// 웹클라이언트의 웹브라우저에서 파일을 업로드 할때 올리는 제품설명서 파일명
				
			}// end of if---------------------------------------------------------
			
		} finally {
			close();
		}
		
		return map;
	}


	// 장바구니 담기 
	// 장바구니 테이블에 해당 제품이 존재하지 않는 경우에는 tbl_cart 테이블에 insert 를 해야하고,
	// 장바구니 테이블에 해당 제품이 존재하는 경우에는 또 그 제품을 추가해서 장바구니 담기를 한다라면 tbl_cart 테이블에 update 를 해야 한다.
	@Override
	public int addCart(Map<String, String> paraMap) throws SQLException {

		int n = 0;
		
		try {
			conn = ds.getConnection();
			
			/*
	            먼저 장바구니 테이블(tbl_cart)에 어떤 회원이 새로운 제품을 넣는 것인지,
	            아니면 또 다시 제품을 추가로 더 구매하는 것인지를 알아야 한다.
	            이것을 알기위해서 어떤 회원이 어떤 제품을  장바구니 테이블(tbl_cart) 넣을때
	            그 제품이 이미 존재하는지 select 를 통해서 알아와야 한다.
	            
	          -------------------------------------------
	           cartno   fk_userid     fk_pnum   oqty  
	          -------------------------------------------
	             1      seoyh          7         2     
	             2      seoyh          6         3     
	             3      leess          7         5     
	         */
			
			String sql = " select cartno "
					   + " from tbl_cart"
					   + " where fk_userid = ? and "
					   + " fk_pnum = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("pnum"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 어떤 제품을 추가로 장바구니에 넣고자 하는 경우
				
				int cartno = rs.getInt(1);
				
				sql = " update tbl_cart set oqty = oqty + ? "
					+ " where cartno = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(paraMap.get("oqty")) );
				pstmt.setInt(2, cartno);
				
				n = pstmt.executeUpdate();
				
			}
			else {
				// 장바구니에 존재하지 않는 새로운 제품을 넣고자 하는 경우
				
				sql = " insert into tbl_cart(cartno, fk_userid, fk_pnum, oqty, registerday) "
					+ " values(seq_tbl_cart_cartno.nextval, ?, ?, ?, default) ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, paraMap.get("userid"));
				pstmt.setInt(2, Integer.parseInt(paraMap.get("pnum")));
				pstmt.setInt(3, Integer.parseInt(paraMap.get("oqty")));
			
				n = pstmt.executeUpdate();
			}
			
		} finally {
			close();
		}
		
		return n;
	}


	// 로그인한 사용자의 장바구니 목록을 조회하기 
	@Override
	public List<CartVO> selectpProductCart(String userid) throws SQLException {

		List<CartVO> cartList = new ArrayList<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select A.cartno, A.fk_userid, A.fk_pnum, "
					   + " B.pname, B.pimage1, B.price, B.saleprice, B.point, A.oqty "
					   + " from tbl_cart A join tbl_product B "
					   + " on A.fk_pnum = B.pnum "
					   + " where A.fk_userid = ? "
					   + " order by A.cartno desc ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int cartno = rs.getInt("cartno");
	            String fk_userid = rs.getString("fk_userid");
	            int fk_pnum = rs.getInt("fk_pnum");
	            String pname = rs.getString("pname");
	            String pimage1 = rs.getString("pimage1");
	            int price = rs.getInt("price");
	            int saleprice = rs.getInt("saleprice");
	            int point = rs.getInt("point");
	            int oqty = rs.getInt("oqty");  // 주문량 
				
	            ProductVO prodvo = new ProductVO();
	            prodvo.setPnum(fk_pnum);
	            prodvo.setPname(pname);
	            prodvo.setPimage1(pimage1);
	            prodvo.setPrice(price);
	            prodvo.setSaleprice(saleprice);
	            prodvo.setPoint(point);
	            
	            // *** !!!! 중요함 !!!! **** //
	            
	            prodvo.setTotalPriceTotalPoint(oqty);
	            
	            // *** !!!! 중요함 !!!! **** //
	            
	            CartVO cvo = new CartVO();
	            cvo.setCartno(cartno);
	            cvo.setUserid(fk_userid);
	            cvo.setPnum(fk_pnum);
	            cvo.setOqty(oqty);
	            cvo.setProd(prodvo);
	            
	            cartList.add(cvo);
	            
			}// end of while------------------------------------------------------
			
		} finally {
			close();
		}
		
		return cartList;
	}


	@Override
	public int delCart(String cartno) throws SQLException {

		int n = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " delete from tbl_cart "
					   + " where cartno = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartno);
			
			n =pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return n;
	}


	// 장바구니 테이블에서 특정제품의 주문량을 변경하기
	@Override
	public int updateCart(Map<String, String> paraMap) throws SQLException {

		int n = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " update tbl_cart set oqty = ? "
					   + " where cartno = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("oqty"));
			pstmt.setString(2, paraMap.get("cartno"));
			
			n =pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return n;
	}


	// 로그인한 사용자의 장바구니에 담긴 주문총액합계 및 총포인트합계 알아오기
	@Override
	public Map<String, String> selectCartSumPricePoint(String userid) throws SQLException {

		Map<String, String> resultMap = new HashMap<>();
		
		try {
			conn = ds.getConnection();
			
			String sql = " select NVL(sum(B.saleprice * A.oqty), 0) as SUMTOTALPRICE "
					   + "      , NVL(sum(B.point * A.oqty), 0) as SUMTOTALPOINT "
					   + " from tbl_cart A join tbl_product B "
					   + " on A.fk_pnum = B.pnum "
					   + " where A.fk_userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			resultMap.put("SUMTOTALPRICE", rs.getString(1));
			resultMap.put("SUMTOTALPOINT", rs.getString(2));
						          
			
		} finally {
			close();
		}
		
		return resultMap;
	}


	// 주문번호(시퀀스 seq_tbl_order 값)을 채번해온 것.
	@Override
	public int getSeq_tbl_order() throws SQLException {
		
		int seq = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select seq_tbl_order.nextval "
					   + " from dual ";
			
			pstmt = conn.prepareStatement(sql);			
			
			rs = pstmt.executeQuery();
			rs.next();
			
			seq = rs.getInt(1);			          
			
		} finally {
			close();
		}
		
		return seq;
		
	}

	// ===== Transaction 처리하기 ===== // 
    // >> 앞에서 미리 했으므로 안함 1. 주문 테이블에 입력되어야할 주문전표를 채번(select)하기
	
    // 2. 주문 테이블에 채번해온 주문전표, 로그인한 사용자, 현재시각을 insert 하기(수동커밋처리)
    // 3. 주문상세 테이블에 채번해온 주문전표, 제품번호, 주문량, 주문금액을 insert 하기(수동커밋처리)
    // 4. 제품 테이블에서 제품번호에 해당하는 잔고량을 주문량 만큼 감하기(수동커밋처리) 
    
    // 5. 장바구니 테이블에서 cartnojoin 값에 해당하는 행들을 삭제(delete OR update)하기(수동커밋처리) 
    // >> 장바구니에서 주문을 한 것이 아니라 특정제품을 바로주문하기를 한 경우에는 장바구니 테이블에서 행들을 삭제할 작업은 없다. << 

    // 6. 회원 테이블에서 로그인한 사용자의 coin 액을 sumtotalPrice 만큼 감하고, point 를 sumtotalPoint 만큼 더하기(update)(수동커밋처리) 
    // 7. **** 모든처리가 성공되었을시 commit 하기(commit) **** 
    // 8. **** SQL 장애 발생시 rollback 하기(rollback) **** 
	@Override
	public int orderAdd(Map<String, Object> paraMap) throws SQLException {
		
		int isSuccess = 0;
		int n1=0, n2=0, n3=0, n4=0, n5=0;
		
		try {
			conn = ds.getConnection();
			
			conn.setAutoCommit(false); // 수동커밋
			
			// 2. 주문 테이블에 채번해온 주문전표, 로그인한 사용자, 현재시각을 insert 하기(수동커밋처리)
			String sql = " insert into tbl_order(odrcode, fk_userid, odrtotalPrice, odrtotalPoint, odrdate) "
					   + " values(?, ?, ?, ?, default) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String)paraMap.get("odrcode"));
			pstmt.setString(2, (String)paraMap.get("userid"));
			pstmt.setInt(3, Integer.parseInt((String)paraMap.get("sumtotalPrice")) );
			pstmt.setInt(4, Integer.parseInt((String)paraMap.get("sumtotalPoint")) );
			
			n1 = pstmt.executeUpdate();
			System.out.println("~~~~~~~~ n1 : " + n1);
			
			// 3. 주문상세 테이블에 채번해온 주문전표, 제품번호, 주문량, 주문금액을 insert 하기(수동커밋처리)
			if(n1 == 1) {
				
				String[] pnumArr = (String[]) paraMap.get("pnumArr");
				String[] oqtyArr = (String[]) paraMap.get("oqtyArr");
				String[] totalPriceArr = (String[]) paraMap.get("totalPriceArr");
				
				for(int i=0; i<pnumArr.length; i++) {
					
					sql = " insert into tbl_orderdetail(odrseqnum, fk_odrcode, fk_pnum, oqty, odrprice, deliverStatus "
						+ " values(seq_tbl_orderdetail.nextval, ?, ?, ?, ?, default) ";
					
				}// end of for---------------------------------
				
			}
			
			conn.setAutoCommit(false); // 자동커밋으로 전환
			
		} catch (SQLException e) {
			// 8. **** SQL 장애 발생시 rollback 하기(rollback) **** 
			conn.rollback();			
			conn.setAutoCommit(false); // 자동커밋으로 전환
		} finally {
			close();
		}
		
		
		return isSuccess;
	}
	
}
