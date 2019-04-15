<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<% if(exception != null){ %>
<h4>jsp 오류 발생 : <%= exception.getMessage() %></h4>
<% }else{ %>
<h4>컨트롤러 오류 발생 : ${message }</h4>
<% } %>
<a href="main.do">시작페이지</a>
</body>
</html>
