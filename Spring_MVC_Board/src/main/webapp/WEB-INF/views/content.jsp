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
		<form action="modify" method="post">
			<input type="hidden" name="bId" value="${content.bId}">
			<thead>
				<td>번호</td>
				<td>${content.bId}</td>
			</thead>
			<thead>
				<td>이름</td>
				<td><input type="text" name="bName" value="${content.bName}"></td>
			</thead>
			<thead>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="${content.bTitle}"></td>
			</thead>
			<thead>
				<td>히트</td>
				<td>${content.bHit}</td>
			</thead>
			<tbody>
				<td>내용</td>
				<td><textarea rows="10" name="bContent">${content.bContent}</textarea></td>
			</tbody>
			<tr>
				<td><input type="submit" value="수정">
				<td><a href="list">목록보기</a></td>
				<td><a href="delete?bId=${content.bId}">삭제</a></td>
				</td>
			</tr>
		</form>
	</table>

</body>
</html>