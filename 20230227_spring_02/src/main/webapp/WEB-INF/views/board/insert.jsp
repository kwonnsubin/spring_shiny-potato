<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>board insert</h1>
	<!-- file upload : enctype  -->
	<form action="insert" method="post" enctype="multipart/form-data" >
		<input type="text" name="boardTitle" placeholder="제목"><br>
		<input type="text" name="boardContent" placeholder="내용"><br>
		<!-- file upload : multiple 하지 않음-->
		<input type="file" name="report" placeholder="첨부파일"><br>
		<button type="submit">게시글 등록</button>
	</form>
</body>
</html>