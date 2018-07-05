<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

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