<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.*"%>
    
<%
	String ctxPath = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 확인</title>
</head>
<body>

<%
	BoardDao dao = new BoardDao();
	int num = Integer.parseInt(request.getParameter("num"));
	BoardVo vo = dao.selectOne(num);
%>

<form action="<%= ctxPath%>/delete.bd?num=<%=num %>" method="post">
	<input type="hidden" value="<%=num %>" name="num">
	삭제 하시겠습니까? <input type="submit" value="예">
</form>
</body>
</html>