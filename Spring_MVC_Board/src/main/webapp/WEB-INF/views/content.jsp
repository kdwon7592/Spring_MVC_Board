<%@page import="javax.swing.text.AbstractDocument.Content"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dowon.myboard.dto.BoardDTO"%>
<%@ page import="com.dowon.myboard.dto.ReplyDTO"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<title>Insert title here</title>
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



	<form action="update" method="post">
		<table width="500" cellpadding="0" cellspacing="0" border="1">
			<input type="hidden" name="bId" value="${content.bId}">
			<thead>
				<td>번호</td>
				<td>이름</td>
				<td>제목</td>
				<td>내용</td>
				<td>조회수</td>
			</thead>
			<tbody>
				<td>${content.bId}</td>
				<td>${content.bName}</td>
				<td>${content.bTitle}</td>
				<td>${content.bContent}</td>
				<td>${content.bHit}</td>
				<%
					BoardDTO board = (BoardDTO) request.getAttribute("content");
					if (board.getbName().equals(userId)) {
				%>
				<td><input type="submit" id="update" value="수정"></td>
				<%
					}
				%>
				<td><a href="list">목록보기</a></td>
			</tbody>
		</table>
	</form>
	<%
		int cnt = 0;
	%>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td colspan="3">&nbsp</td>
		</tr>
		<tr>
			<td colspan="3">댓글</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>내용</td>
		</tr>
		<c:forEach items="${reply}" var="dto">
			<form action="reply_update" method="post">
				<input type="hidden" name="rId" value="${dto.rId}">
				<tr>
					<td>${dto.rName}</td>
					<td>${dto.rComment}</td>
					<%
						ArrayList<ReplyDTO> reply = (ArrayList<ReplyDTO>) request.getAttribute("reply");
							if(reply.get(cnt++).getrName().equals(userId)){
					%>
					<td><input type="submit" id="reply_update" value="수정"></td>
					<%
						}
					%>
				
				<tr>
			</form>
		</c:forEach>
	</table>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="reply_write" method="post">
			<tr>
				<td colspan="3">&nbsp</td>
			</tr>
			<!-- AJAX 씅떄..... -->
			<!-- form name="reply_form"-->
			<input type="hidden" name="bId" value="${content.bId}"> <input
				type="hidden" name="rId" value="${dto.rId}">

			<tr>
				<td>이름</td>
				<td>덧글</td>
			</tr>
			<tr>
				<td><input type="text" value=<%=userId%> name="rName"
					id="rName" readonly></td>
				<td><input type="text" name="rComment" id="rComment"></td>
				<td><input type="submit" id="reply_write" value="입력"></td>
			</tr>
		</form>
	</table>
</body>
</html>