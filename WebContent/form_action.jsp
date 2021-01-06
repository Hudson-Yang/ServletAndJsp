<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="net.slipp.user.*" %>
<%@page import="net.slipp.db.*" %>
<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	String email = request.getParameter("email");

	out.println(userId+" : "+password+" : "+name+" : "+email);
	
	User user = new User(userId, password, name, email);
	Database.addUser(user);
	
	response.sendRedirect("/");
	
%>