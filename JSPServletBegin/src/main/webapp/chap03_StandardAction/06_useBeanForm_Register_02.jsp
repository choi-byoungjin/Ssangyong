<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>form 태그를 사용한 데이터 전송시 useBean 을 사용하여 결과 보여주기</title>
</head>
<body>

	<h2>개인성향 입력 결과 정보(JSP 표준액션 중 useBean 을 사용한 것)</h2>
	
	<jsp:useBean id="psdto" class="chap03.PersonDTO"></jsp:useBean>
	<jsp:setProperty property="name" 	name="psdto" value="${param.name}"/>
	<jsp:setProperty property="school" 	name="psdto" value="${param.school}"/>
	<jsp:setProperty property="color" 	name="psdto" value="${param.color}"/>
	<jsp:setProperty property="food" 	name="psdto" value="${paramValues.food}"/>	<!-- 복수개이므로 param이 아니다 -->
	
	<ul>
		<li>성명 : <jsp:getProperty property="name" name="psdto"/> </li>
		<li>학력 : <jsp:getProperty property="school" name="psdto"/> </li>
		<li>색상 : <jsp:getProperty property="color" name="psdto"/> </li>
		<li>음식 : <jsp:getProperty property="strFood" name="psdto"/> </li>
	</ul>

	<br/>
	<hr style="border: solid 1px red;">
	<br/>

	<jsp:useBean id="psdto_2" class="chap03.PersonDTO"></jsp:useBean>
	<jsp:setProperty property="*" name="psdto_2"/>	<!-- 알아서 요소에 다 찾아간다 -->
	<%--
		위와 같이 <jsp:setProperty property="*" name="psdto_2"/> 을 사용하기 위한 전제조건은
		chap03.PersonDTO 클래스에 setXXX() 메소드의 XXX 이름과 form 태그에서 전달되어져 오는 name값이 같아야 한다.
		예:> setName 의 name 와 form 태그의 name="name"
			setName 의 school 와 form 태그의 name="school"
			setName 의 color 와 form 태그의 name="color"
			setFood 의 food와 form 태그의 name="food"
		
	 --%>

	<ul>
		<li>성명 : <jsp:getProperty property="name" name="psdto"/> </li>
		<li>학력 : <jsp:getProperty property="school" name="psdto"/> </li>
		<li>색상 : <jsp:getProperty property="color" name="psdto"/> </li>
		<li>음식 : <jsp:getProperty property="strFood" name="psdto"/> </li>
	</ul>
	
</body>
</html>