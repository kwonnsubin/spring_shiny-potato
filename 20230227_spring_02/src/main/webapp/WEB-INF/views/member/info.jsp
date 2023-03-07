<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보보기</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
<h1>내정보</h1>
<form id="frmInfo" action="update" method="get">
	<input value="${membervo.id }" type="text" name="id" readonly="readonly"><br>
	<input value="${membervo.passwd }" type="password" name="passwd" readonly="readonly"><br>
	<input value="${membervo.name }"  type="text" name="name" readonly="readonly"><br>
	<input value="${membervo.email }" type="text" name="email"  readonly="readonly"><br>
	<button type="button" onclick="frmSubmit(this);">수 정</button>
	<button type="button" onclick="frmSubmit(this);">탈 퇴</button>
</form>

<script>
	function frmSubmit(targetEle) {
		if($(targetEle).text() == '수정') {
			$("#frmInfo").attr("action"."update");
			$("#frmInfo").submit();
		}else if($(targetEle).text() == '탈퇴'){
			
		}
	}
</script>
</body>
</html>