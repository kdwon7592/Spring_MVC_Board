<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
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
					<li><a href="write_view">글작성</a></li>
					<li><a href="logout">로그 아웃</a></li>
					<%
						}
					%>
				</ul>
		</ul>
	</div>
	</nav>



	<table width="500" cellpadding="0" cellspacing="0" border="1">
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

	<ul>
		<c:if test="${paging.currentPage > 5 }">
			<!-- 이전 클릭시 전 페이지로 이동 -->
			<li><a href="list?pages=${paging.prevPage}">&lt&lt</a></li>
		</c:if>
		<c:if test="${paging.currentPage > 1 }">
			<!-- 이전 클릭시 전 페이지로 이동 -->
			<li><a href="list?pages=${paging.currentPage - 1}">&lt</a></li>
		</c:if>
		<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}"
			step="1">
			<c:choose>
				<c:when test="${i eq paging.currentPage}">
					<li>${i}</li>
				</c:when>
				<c:otherwise>
					<li><a href="list?pages=${i}">${i}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.currentPage < paging.finalPage}">
			<li><a href="list?pages=${paging.currentPage + 1}">&gt</a></li>
		</c:if>
		<c:if
			test="${paging.nextPage < paging.finalPage && paging.nextPage ne 0}">
			<li><a href="list?pages=${paging.nextPage}">&gt&gt</a></li>
		</c:if>
	</ul>

	<form method="post" action="list?pages=${paging.currentPage}">
		<select name='maxListExtend'>
			<option value='10' selected>10</option>
			<option value='20'>20</option>
			<option value='30'>30</option>
			<option value='40'>40</option>
			<option value='50'>50</option>
		</select>
		<button type="submit">변경</button>
	</form>

	<br />
	<br />


	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td colspan="5"><a href="dbtest">DB 연결테스트</a></td>
		</tr>
		<tr>
			<td colspan="5"><a href="dbcptest">DBCP 연결테스트</a></td>
		</tr>
	</table>
</body>
</html>