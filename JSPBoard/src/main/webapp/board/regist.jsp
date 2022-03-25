<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="vo" class="board.BoardVo"/>
<jsp:useBean id="dao" class="board.BoardDao"/>
<jsp:setProperty name="vo" property="*"/>

<%
	dao.insert(vo);
	
	//response.sendRedirect(request.getContextPath() + "/board/list.jsp") 아래 코드와 같은 기능이다.
%>
<c:redirect url="/board/list.jsp" />