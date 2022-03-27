<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.*"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="o d" class="w board.model.BoardD"/>

<%
	int num = Integer.parseInt(request.getParameter("num"));
	dao.delete(num);
%>

<c:redirect url="/board/list.jsp"/>