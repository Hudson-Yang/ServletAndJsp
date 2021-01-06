<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.slipp.user.*" %>
<%@page import="net.slipp.db.*" %>
<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	
	User user = Database.findByUserId(userId);
	if(user == null){
		// 대충 사용자가 존재하지 않는다는 로직
	}
	
	if(password.equals(user.getPassword())){
		// 대충 로그인 처리하는 로직
	}
%>