<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<title>자유로운 게시판</title>
<style type="text/css">
a, a:hover {
	color: #000000;
	text-decoration: none;
}
</style>
</head>
<body>

	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expended="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="main">자유로운 게시판</a>
		</div>
		<div class="colapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main">메인</a></li>
				<li><a href="list">게시판</a></li>
			</ul>
		</div>
	</nav>


	<div class="container">
		<div class="jumbotron" style="padding-top: 20px;">
			<form method="post" action="loginAction">
				<h3 style="text-align: center;">로그인화면</h3>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="아이디"
						name="userId" maxlength="20"> <input type="password"
						class="form-control" placeholder="비밀번호" name="userPassword"
						maxlength="20">
				</div>
				<input type="submit" class="btn btn-primary form-control"
					value="로그인"> 
			</form>
			</br>
				<a href="join"><input type="submit"
					class="btn btn-primary form-control" value="회원가입"></a>
		</div>
	</div>
</body>
</html>