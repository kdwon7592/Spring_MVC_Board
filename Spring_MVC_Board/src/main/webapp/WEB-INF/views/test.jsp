<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

<script>
 $(function() {
  $('#AjaxStart').click(function() {
   var allData = "test1=1&test2=2";
   $.ajax({
    type : "POST",
    dataType: "json",
    //dataType : 'text',
    data : allData,
    url : "http://localhost:8080/myboard/ajax",
    success : function(data) {
    	alert("전송 완료!");
    	console.log(data);
    }
   });
  });
 });
</script>
</head>
<body>
 <button id="AjaxStart">Ajax Start</button>
  <div>
 	<div>
 		test1 = ${test1}
 	</div>
 	<div>
 		test2 = ${test2}
 	</div>
 </div>
</body>
</html>