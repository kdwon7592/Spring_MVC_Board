<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

#non-click {
	background-color: #D8D8D8;
}
</style>
</head>
<body>
	<%
		String userId = null;
		if (session.getAttribute("User") != null) {
			userId = (String) session.getAttribute("User");
		}
		String opt = null;
		String cond = null;
		int maxList;
		if (session.getAttribute("opt") != null) {
			opt = (String) session.getAttribute("opt");
		}
		if (session.getAttribute("cond") != null) {
			cond = (String) session.getAttribute("cond");
		}
		if (session.getAttribute("maxList") != null) {
			maxList = (Integer) session.getAttribute("maxList");
		} else {
			maxList = 30;
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
				<%
					if (userId == null) {
				%>
				<li class="active"><a href="main">메인</a></li>
				<li><a href="list">게시판</a></li>
				<%
					} else {
				%>
				<li class="active"><a href="main">메인</a></li>
				<li><a href="list">게시판</a></li>
				<li><a href="write_view">글쓰기</a></li>
				<%
					}
				%>
			</ul>
			<%
				if (userId == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expended="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="login">로그인</a></li>
						<li><a href="join">회원가입</a></li>
					</ul></li>
			</ul>
			<%
				} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expended="false">회원관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href='user_update?userId=<%=userId%>'>화원 정보 수정</a></li>
						<li><a href='logout'>로그아웃</a></li>
					</ul></li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>


	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<tr>
					<td>번호</td>
					<td>이름</td>
					<td>제목</td>
					<td>날짜</td>
					<td>조회수</td>
				</tr>
				<c:forEach items="${list}" var="dto">
					<!-- list 정보 안에 BoardDTO객체들을 dto라고 명명한다. -->
					<tr>
						<td>${dto.bId}</td>
						<td>${dto.bName}</td>
						<td>
							<!-- 제목 클릭시 게시물 확인 가능 bId는 content.jsp로 넘겨준다. --> <a
							href="content?bId=${dto.bId}">${dto.bTitle}</a>
						</td>
						<td>${dto.bDate}</td>
						<td>${dto.bHit}</td>
					</tr>
				</c:forEach>
				<tr style="height: 10px;">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="list">더보기 +</a></td>
				</tr>
			</table>
		</div>
	</div>

	

	<div class="container">
		<form action="list" class="form-inline">
			<select name="opt" class="form-control">
				<option value="0">제목</option>
				<option value="1">내용</option>
				<option value="2">제목+내용</option>
				<option value="3">작성자</option>
			</select> <input type="text" size="20" name="cond" class="form-control" />
			<button type="submit" class="btn btn-success">검색</button>
		</form>
	</div>
	<br />
</body>
</html>