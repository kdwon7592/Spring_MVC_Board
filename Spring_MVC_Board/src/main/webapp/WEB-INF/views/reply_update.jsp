<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<title>스프링 게시판</title>
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
		<a class="navbar-brand" href="list">스프링 웹 게시판</a>
	</div>
	<div class="colapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li><a href="list">메인</a></li>
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
					<li class="active"><a href='login'>로그인</a></li>
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
				<form action="reply_updateAction" method="post">
					<input type="hidden" name="rId" value="${reply_update.rId}">
					<input type="hidden" name="bId" value="${reply_update.bId}">
					<tr>
						<td>작성자</td>
						<td colspan="4">내용</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>${reply_update.rName}</td>
						<td><textarea rows="3" name="rComment" class="form-control">${reply_update.rComment}</textarea></td>
						<td><button type="submit" value="수정"
								class="btn btn-primary pull-right">수정</button></td>
						<td><button type="button" class="btn btn-primary pull-right">
								<a href="content?bId=${reply_update.bId}" style="color: white;">취소</a>
							</button></td>
						<td><button type="button" class="btn btn-primary pull-right" onClick="check_confirm()">
								<a style="color: white;">삭제</a>
							</button></td>
					</tr>
				</form>
			</table>
		</div>
	</div>
	<script>
		function check_confirm() {
			result = confirm('삭제 하시겠습니까');
			if (result == true) {
				location.href = "reply_delete?rId=${reply_update.rId}&bId=${reply_update.bId}";
			} else {
				return false;
			}
		}
	</script>
</body>
</html>