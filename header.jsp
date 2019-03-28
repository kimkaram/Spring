<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
	<!-- 메뉴 네비게이션 바 -->	
		<nav>
			<ul>
				<li><a href="home.do">home</a></li>
				<li><a href="nlist.do">공지사항</a></li>
				<li><a href="blist.do?page=1">페이징게시판</a></li>
				<li><a href="">QnA</a></li>
				<li><a href="">자료실</a></li>
			</ul>
		</nav>

	<!-- 로그인 폼 -->
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
