<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.*"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% request.setCharacterEncoding("utf-8"); %>
<%
	String ctxPath = request.getContextPath();
%>

<jsp:useBean id="vo" class="board.model.BoardVo" />
<jsp:useBean id="dao" class="board.model.BoardDao" />
<jsp:setProperty name="vo" property="*"/>

<%
	dao.insert(vo);
	
	response.sendRedirect(request.getContextPath() + "/list.bd"); //아래 코드와 같은 기능이다.
%>
<%-- <c:redirect url="<%= ctxPath %>/list.bd" /> --%>