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
					<!-- 제목 클릭시 게시물 확인 가능 bId는 content.jsp로 넘겨준다. --> 
					<a href="content?bId=${dto.bId}">${dto.bTitle}</a>
				</td>
				<td>${dto.bDate}</td>
				<td>${dto.bHit}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5"><a href="write_view">글작성</a></td>
		</tr>
		<tr>
			<td colspan="5"><a href="dbtest">DB 연결테스트</a></td>
		</tr>
		<tr>
			<td colspan="5"><a href="dbcptest">DBCP 연결테스트</a></td>
		</tr>
	</table>
</body>
</html>