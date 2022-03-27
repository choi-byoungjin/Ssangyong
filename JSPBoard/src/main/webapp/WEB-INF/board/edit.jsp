<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.*"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
p

	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="o" class="w board.model.Board"/>
<jsp:useBean id="o d" class="w board.model.BoardD"/>
<jsp:setProperty name="vo" property="*"/>

<%
	dao.update(vo);
	pageContext.setAttribute("vo",vo);
	
%>
<c:redirect url="/board/boardDetail.jsp?num=${vo.num}"/>