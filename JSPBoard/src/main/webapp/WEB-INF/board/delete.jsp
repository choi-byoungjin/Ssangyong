<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.*"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- <jsp:useBean id="vo" class="board.model.BoardVo" />
<jsp:useBean id="dao" class="board.model.BoardDao" />
<jsp:setProperty name="vo" property="*"/> --%>

<%
	
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDao dao = new BoardDao();
	System.out.println(num);
	dao.delete(num);	

	response.sendRedirect(request.getContextPath() + "/list.bd");
%>
