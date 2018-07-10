<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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


	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="reply_updateAction" method="post">
			<input type="hidden" name="rId" value="${reply_update.rId}">
			<input type="hidden" name="bId" value="${reply_update.bId}">

			<thead>
				<td>작성자</td>
				<td colspan="4">내용</td>
			</thead>
			<tbody>
				<td>${reply_update.rName}</td>
				<td><textarea rows="3" name="rComment">${reply_update.rComment}</textarea></td>
				<td><input type="submit" value="수정">
				<td><a href="content?bId=${reply_update.bId}">취소</a></td>
				<td><a
					href="reply_delete?rId=${reply_update.rId}&bId=${reply_update.bId}">삭제</a></td>
			</tbody>
		</form>
	</table>

</body>
</html>