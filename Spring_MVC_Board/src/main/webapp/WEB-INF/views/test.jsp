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
    }
   });
  });
 });
</script>
</head>
<body>
 <a href="test2"><button  id="AjaxStart">Ajax Start</button></a>
</body>
</html>