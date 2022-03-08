<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Map" %>    
    
<%
	Map<String, String> map = (Map<String, String>) request.getAttribute("paraMap"); /* return 타입이 object 이므로 Map으로 캐스팅 한다.*/
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 성향 테스트 결과화면</title> <!-- view는 결과물을 보여주는 곳 -->
</head>
<body>

	<h2>개인 성향 테스트 결과</h2>
	
	<h3>스크립틀릿을 사용한 것</h3>
	<span style="color: red;"><%= map.get("name") %></span>님의 개인 성향은 <br/><br/>
	학력은 <%= map.get("school") %>이며, <%= map.get("color") %>색을 좋아합니다.<br/>
	좋아하는 음식은 <%= map.get("food") %>입니다.<br/><br/>
	
	<hr style="border: solid 1px red;">
	
	<h3>EL을 사용한 것</h3>
	<span style="color: blue;">${requestScope.paraMap.name}</span>님의 개인 성향은 <br/><br/>	<!-- request 영역의 scope, request에 저장해둔 paraMap -->
	학력은 ${requestScope.paraMap.school}이며, ${requestScope.paraMap.color}색을 좋아합니다.<br/>
	좋아하는 음식은 ${requestScope.paraMap.food}입니다.<br/>
	EL을 사용하는 것이 더 쉽다.
	
</body>
</html>