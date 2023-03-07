<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var msg = "${msg}"
	if(msg){
		alert(msg);
	}
	location.href="<%=request.getContextPath()%>/";
</script>