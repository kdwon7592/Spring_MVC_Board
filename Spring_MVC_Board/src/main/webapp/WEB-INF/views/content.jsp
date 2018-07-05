<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<td><input type="submit" id="update" value="수정">
				<td><a href="list">목록보기</a></td>
			</tbody>
		</table>
	</form>
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
				<td><input type="submit" id="reply_update" value="수정"></td>
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
		<input type="hidden" name="bId" value="${content.bId}">
		<input type="hidden" name="rId" value="${dto.rId}">

		<tr>
			<td>이름</td>
			<td>덧글</td>
		</tr>
		<tr>
			<td><input type="text" name="rName" id="rName"></td>
			<td><input type="text" name="rComment" id="rComment"></td>
			<td><input type="submit" id="reply_write" value="입력"></td>
		</tr>
		</form>
	</table>
</body>
</html>