<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글</title>
	<script src="https://code.jquery.com/jquery-3.6.3.js" ></script>
</head>
<body>
	<h1>${board.boardNum } 게시글</h1>
	<div>
		<p>${board.boardTitle }</p>
	</div>
	<div>
		<p>${board.boardContent }</p>
	</div>
	
	<form id="frmReply">
		<fieldset>
			<legend>답글작성</legend>
				<div>제목<input type="text" name="boardTitle"></div>
				<div>내용<input type="text" name="boardContent"></div>
				<input type="hidden" name="boardNum" value=">${board.boardNum }">
				<button type="button" class="btn reply">답글작성</button>
				<button type="reset">초기화</button>			
		</fieldset>
	</form>
	<hr>
	<c:forEach items="${replyList }" var="reply">
		<h1>게시판 글목록</h1>
		<tr>
			<td>${board.boardNum }</td>
			<td><a href="<%=request.getContextPath()%>/board/read?boardNum=${board.boardNum }">${board.boardTitle }</a></td>
			<td>${board.boardWriter }</td>
			<td>${board.boardDate }</td>
			<td>${board.boardReadcount }</td>		
		</tr>
</c:forEach>			
</table>
	</c:forEach>
	
<script>
	$(".btn.reply").on("click",replyClickHandler);
	
	function replyClickHandler(){
		console.log(this); // this(DOM)
		console.log($(this)); // this를 jquery 형태로 변형
		//$(this).parants("form")
		console.log($("#frmReply").serialize())
		$.ajax({ 
			url:"<%=request.getContextPath()%>/board/insertReplyAjax"
			, type: "post"
			//, contentType:
			, data: $("#frmReply").serialize() // QueryString // js object
			, success: function(result){
				console.log(result);
				console.log(result[0]);
				console.log(result[0].boardDate);
				//$("#frmReply").eq(0).reset(); // 작성된 글 초기화. 클래스는 여러개가있을수있기때문에 반드시 뒤에 선택해줘함. 
				frmReply.reset(); // form태그에 한해 document.get~ 생략가능 
				if(result.length > 0) {
					alert("댓글작성되었습니다.")
				} else {
					alert("댓글작성되지 않았습니다. 다시 작성해주세요.")
				}
				// 답글 부분 화면 업데이트
				displayReply(result);
			}
			, error: function() {
			}
		
		
		
		});
	}
	// 답글 부분 화면 업데이트
	function displayReply(result) {
		console.log(result);
		console.log(result[0]);
		console.log(result[0].boardDate);
		
		var html = '';
		for(i = 0; i< result.length; i++){
			var reply = result[i];
			htmlVal += '<tr>';
			htmlVal += '<td>'+reply.boardNum+'</td>';
			htmlVal += '<td><a href="<%=request.getContextPath()%>/board/read?boardNum='+reply.boardNum+'">'+reply.boardTitle+'</a></td>';
			htmlVal += '<td>'+reply.boardWriter+'</td>';
			htmlVal += '<td>'+reply.boardDate+'</td>';
			htmlVal += '<td>'+reply.boardReadcount+'</td>';
			htmlVal += '</tr>';
		}
		$("tbody").html(htmlVal);
	}
	
</script>
</body>
</html>