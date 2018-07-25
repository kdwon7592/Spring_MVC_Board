<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
	<script type="text/javascript">
		var message = '${message}';
		var returnUrl = '${returnUrl}';
		alert(message);

		document.location.href = returnUrl;
	</script>
	<p>test</p>
</body>
</html>