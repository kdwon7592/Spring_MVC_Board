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
<title>JSP 웹 게시판</title>
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
	%>
	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expended="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="list">JSP 웹 게시판</a>
		</div>
		<div class="colapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<%
					if (userId == null) {
				%>
				<li><a href="list">메인</a></li>
				<li class="active"><a href="list">게시판</a></li>
				<%
					} else {
				%>
				<li><a href="list">메인</a></li>
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
					<td>히트</td>
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
			</table>
		</div>
	</div>

	<div class="container">
		<nav>
			<ul class="pagination">
				<c:if test="${paging.currentPage > 5 }">
					<!-- 이전 클릭시 전 페이지로 이동 -->
					<li><a href="list?pages=${paging.prevPage}"
						class="btn btn-success btn-arraw-left">&lt&lt</a></li>
				</c:if>
				<c:if test="${paging.currentPage > 1 }">
					<!-- 이전 클릭시 전 페이지로 이동 -->
					<li><a href="list?pages=${paging.currentPage - 1}"
						class="btn btn-success btn-arraw-left">&lt</a></li>
				</c:if>
				<c:forEach var="i" begin="${paging.startPage}"
					end="${paging.endPage}" step="1">
					<c:choose>
						<c:when test="${i eq paging.currentPage}">
							<li><a href="#" id="non-click">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="list?pages=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${paging.currentPage < paging.finalPage}">
					<li><a href="list?pages=${paging.currentPage + 1}"
						class="btn btn-success btn-arraw-left">&gt</a></li>
				</c:if>
				<c:if
					test="${paging.nextPage < paging.finalPage && paging.nextPage ne 0}">
					<li><a href="list?pages=${paging.nextPage}"
						class="btn btn-success btn-arraw-left">&gt&gt</a></li>
				</c:if>
			</ul>
		</nav>
	</div>
</body>
</html>