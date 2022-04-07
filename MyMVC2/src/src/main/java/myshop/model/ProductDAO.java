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
	public ProductVO selectOneProductByPnum(String pnum) {

		ProductVO pvo = null;
		
		try {
			conn = ds.getConnection();
			
			String sql = "";
			
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
			
			String sql = "";
			
		} finally {
			close();
		}
		
		return imgList;
	}
	
}
