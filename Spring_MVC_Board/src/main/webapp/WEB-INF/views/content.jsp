<%@page import="javax.swing.text.AbstractDocument.Content"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dowon.myboard.dto.BoardDTO"%>
<%@ page import="com.dowon.myboard.dto.ReplyDTO"%>
<%@ page import="java.util.ArrayList"%>
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
<title>스프링 게시판</title>
<style type="text/css">
a, a:hover {
	color: #000000;
	text-decoration: none;
}
</style>
</head>
<script>
	/* 
	 // Ajax 쓸때... 
	 $(function() {
	 $('#reply_write').click(function() {
	 var allData = $("form[name=reply_form]").serialize() + "&bId=" + $
	 {
	 content.bId
	 }
	 ;
	 $.ajax({
	 type : "POST",
	 dataType : "json",
	 //dataType : 'text',
	 data : allData,
	 url : "http://localhost:8080/myboard/reply_write",
	 success : function(data) {
	 alert("전송 완료!");
	 console.log(data);
	 }
	 });
	 });
	 });
	 */
</script>
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
				<form action="content_update" method="post">
					<input type="hidden" name="bId" value="${content.bId}">
					<thead>
						<td>번호</td>
						<td>이름</td>
						<td>제목</td>
						<td>조회수</td>
					</thead>
					<tbody>
						<td>${content.bId}</td>
						<td>${content.bName}</td>
						<td>${content.bTitle}</td>
						<td>${content.bHit}</td>
					</tbody>
					<tr>
						<td style="vertical-align: middle;">내용</td>
						<td height="150px" colspan="3" 
							style="vertical-align: middle; background-color: white;">${content.bContent}</td>

						<%
							BoardDTO board = (BoardDTO) request.getAttribute("content");
							if (board.getbName().equals(userId)) {
						%>
						<input type="submit" id="update" value="수정"
							class="btn btn-primary pull-right">
						<%
							}
						%>
						<a href="list" class="btn btn-primary pull-right">목록보기</a>
					</tr>
			</table>
			</form>
		</div>
	</div>
	<%
		int cnt = 0;
	%>
	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd;">
				<tr>
					<td colspan="4" style="text-align: left; padding-left: 15px;">댓글</td>
				</tr>
				<c:forEach items="${reply}" var="dto">
					<form action="reply_update" method="post">
						<input type="hidden" name="rId" value="${dto.rId}">
						<tr>
							<td>${dto.rName}</td>
							<td>${dto.rComment}</td>
							<%
								ArrayList<ReplyDTO> reply = (ArrayList<ReplyDTO>) request.getAttribute("reply");
									if (reply.get(cnt++).getrName().equals(userId)) {
							%>
							<td><button type="submit" value="수정"
									class="btn btn-primary pull-right">수정</button></td>
							<%
								} else {
							%>
							<td></td>
							<%
								}
							%>
						
						</tr>
					</form>
				</c:forEach>
			</table>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<table class="table table-striped"
				style="text-align: center; border: 1px solid #dddddd">
				<form action="reply_write" method="post">
					<!-- AJAX 씅떄..... -->
					<!-- form name="reply_form"-->
					<input type="hidden" name="bId" value="${content.bId}"> <input
						type="hidden" name="rId" value="${dto.rId}">

					<tr>
						<td>이름</td>
						<td>덧글</td>
						<td></td>
					</tr>
					<tr>
						<td><input type="text" value=<%=userId%> name="rName"
							id="rName" class="form-control" readonly></td>
						<td><input type="text" name="rComment" class="form-control" id="rComment"></td>
						<td><button type="submit" value="입력"
								class="btn btn-primary pull-right">입력</button></td>
					</tr>
				</form>
			</table>
		</div>
	</div>
</body>
</html>