<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 필요한 클래스를 import 하려면 <%@ page %> page directive(페이지 지시어)를 사용하여 import를 한다. --%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>

<%
	// *** 현재시각을 알아오기 *** //
	Date now = new Date();	// 현재시각
	
	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    String today = sdformat.format(now);
    
    String currentTime = String.format("%tF %tT %tA", now, now, now); 
    
%>

<%= currentTime %>