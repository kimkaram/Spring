<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		header nav ul {list-style:none;}
		header nav ul li{
			float: left;
			width: 200px;
			height: 25px;
			background-color: #ccff00;
			margin-right: 10px;
			text-align: center;
		}
		header nav ul li a {
			display: block;
			color: navy;
			text-decoration: none;
			font-weight: bold;
		}
		header nav ul li a:hover {
			background-color: #ffcc00;
			font-size: 12pt;
		}
	</style>
</head>
<body>
	<header>
		<nav>
			<ul>
				<li><a href="main.do">home</a></li>
				<li><a href="moveAOP.do">AOP란?</a></li>
				<li><a href="moveFileUp.do">파일업로드 테스트</a></li>
				<li><a href="moveAjax.do">Ajax 테스트</a></li>
				<li><a href="testView.do">패스워드 암호화 테스트</a></li>
			</ul>
		</nav>
		
			<c:if test="${empty sessionScope.loginMember }">
		<form action="login.do" method="post">
			<fieldset>
				<legend>로그인 하세요</legend>
				아이디 : <input type="text" name="userid"> <br>
				암  호: <input type="password" name="userpwd"> <br>
				<input type="submit" value="로그인"> &nbsp;
				<a href="enrollForm.do">회원가입</a>
				<a>아이디/암호확인</a>
			</fieldset>
		</form>
	</c:if>
	<c:if test="${!empty sessionScope.loginMember }">
		<table width="250" height="80" cellsapacing="0" border="0" bgcolor="#99ccff">
			<tr><th>${loginMember.userid } 님</th>
				<th><a href="logout.do">로그아웃</a></th></tr>
					<c:url var="mi" value="myinfo.do">
						<c:param name="userid" value="${loginMember.userid }" />	
					</c:url>
				<tr><th><a href="${mi }">내 정보보기</a></th>
			<th>쪽지</th></tr>
		</table>
	</c:if>
	</header>	

</body>
</html>
