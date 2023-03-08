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
<form id="frmInfo" method="get">
	<input name="id" value="${membervo.id }" type="text" readonly="readonly"><br>
	<input value="${membervo.passwd }" type="password" readonly="readonly"><br>
	<input value="${membervo.name }"  type="text" readonly="readonly"><br>
	<input value="${membervo.email }" type="text" readonly="readonly"><br>
	<button type="button" onclick="frmSubmit('update');">수 정</button>  
	<button type="button" onclick="frmSubmit('delete');">탈 퇴</button>
	
	<button type="button" >수 정</button>
	<button type="button" >탈 퇴</button>
</form>

<script>
	console.log($("button").eq(2).text());
	console.log($("button").get(2).innerText);
	$("button").eq(2).click('update' , frmSubmit2);
	$("button").eq(3).click('delete' , frmSubmit2);
	function frmSubmit2(event){
		console.log(this);  // this: click이 발생한 시점에 element 
		frmInfo.action = event.data;
		frmInfo.submit();
	}
	function frmSubmit(targetEle){
		console.log(this);  // this: window객체
		//form.action = targetEle;
		frmInfo.action = targetEle;
		frmInfo.submit();
		//document.getElementById("frmInfo").action = targetEle;
		//document.getElementsByClassName("aaa")[0]
		//document.querySelector(".aaa.bbb[type=text]").action = targetEle;
		//document.querySelectorAll(".aaa")[0].action = targetEle;
		//$("#frmInfo").attr("action", targetEle);
		//$("#frmInfo").submit();
	}
</script>


</body>
</html>