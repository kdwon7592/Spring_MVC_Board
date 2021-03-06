<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/ckeditor/ckeditor.js"></script>
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

	<%
		String userId = null;
		if (session.getAttribute("User") != null) {
			userId = (String) session.getAttribute("User");
		}
	%>
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
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expended="false">접속하기<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<%
						if (userId == null) {
					%>
					<li><a href='login'>로그인</a></li>
					<li><a href="join">회원가입</a></li>

					<%
						} else {
					%>
					<li><a href="logout">로그 아웃</a></li>
					<%
						}
					%>
				</ul>
		</ul>
	</div>
	</nav>

	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<form action="write" method="post">
					<tr>
						<td>이름</td>
						<td><input type="text" value="<%=userId%>" name="bName"
							size="50" class="form-control" readonly></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" class="form-control" name="bTitle"
							size="50" required></td>
					</tr>
					<tr>
						<td>내용</td>
						<td><textarea id="bContent" name="bContent"
								class="form-control" rows="10"></textarea></td>
					</tr>
					<tr>
						<td><button type="button" class="btn btn-primary pull-right">
								<a style="color: white;" href="list">취소</a>
							</button></td>
						<td><button type="submit" value="입력"
								class="btn btn-primary pull-right">입력</button></td>
					</tr> 
				</form>
			</table>
		</div>
	</div>
	<script>
		CKEDITOR.replace('bContent', {});
	</script>
</body>
</html>