<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- ==== JSTL(Java Standard Tag Library) ==== --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<%-- === 변수의 선언방법 === --%>
<c:set var="result" value="${param.firstNum * param.secondNum}" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>두 개의 수를 입력받아서 곱셈한 결과 출력하기</title>
</head>
<body>

	${param.firstNum}와 ${param.secondNum}의 곱은 ${result} 입니다. <!-- c:set으로 넘겨준 변수를 받$로 받는다 -->

</body>
</html>