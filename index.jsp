<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <h1>spring framework test project</h1> -->
<%
	request.getRequestDispatcher("main.do").forward(request, response); //단순히 화면에 보여주는 것도 이젠 controller 거쳐서 보여줘야 함
%>



</body>
</html>
