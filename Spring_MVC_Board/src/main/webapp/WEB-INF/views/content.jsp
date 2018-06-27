<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="update" method="post">
			<input type="hidden" name="bId" value="${content.bId}">
			<thead>
				<td>번호</td>
				<td>${content.bId}</td>
			</thead>
			<thead>
				<td>이름</td>
				<td>${content.bName}</td>
			</thead>
			<thead>
				<td>제목</td>
				<td>${content.bTitle}</td>
			</thead>
			<thead>
				<td>히트</td>
				<td>${content.bHit}</td>
			</thead>
			<tbody>
				<td>내용</td>
				<td>${content.bContent}</td>
			</tbody>
			<tr>
				<td><input type="submit" value="수정">
				<td><a href="list">목록보기</a></td>
				</td>
			</tr>
		</form>
		
		
		<!-- 댓글 구현 하기 -->
		<!-- div class="container">
			<form id="commentForm" name="commentForm" method="post">
				<div>
					<div>
						<span><strong>Comments</strong></span>
					</div>
					<div>
						<table class="table">
							<tr>
								<td>
									<textarea style="width:1000px" rows="3" cols="30" id="comment" name="comment" placeholder="댓글 입력,,"><
									</textarea>
							</td>
							</tr>
						</table>
					</div>
				</div>
			</form>
		</div -->
		<tr>
			<td colspan="2">&nbsp</td>
		</tr>
		<tr>
			<td colspan="2">댓글</td>
		</tr>
		<c:forEach items="${reply}" var="dto">

			<tr>
				<td>번호</td>
				<td>${dto.rId}</td>
			<tr>
			<tr>
				<td>작성자</td>
				<td>${dto.rName}</td>
			<tr>
			<tr>
				<td>댓글</td>
				<td>${dto.rComment}</td>
			</tr>
		</c:forEach>

		<tr>
			<td colspan="2">&nbsp</td>
		</tr>
		<form action="reply_write" method="post">
			<tr>
				<td>이름</td>
				<td>덧글</td>
			</tr>
			<tr>
				<td><input type="text" name="bName" size="20"></td>
				<td><input type="text" name="bName" size="150"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="입력"></td>
			</tr>
		</form>
	</table>

</body>
</html>