<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 - 회원목록</title>
<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
<style>
	.f-3 {
		display:flex;
		width: 300px;
		heigth: 50px;
		flex-wrap: nowrap;
	}
	.f-3>div{
		border: 1px block solid;
		width: 33%
	}
</style>
</head>
<body>
<div  class="f-3">
	<a href="<%=request.getContextPath()%/member>">
		<div>아이디</div>
		<div>이름</div>
		<div>이메일</div>	
	</a>
</div>
</body>
</html>