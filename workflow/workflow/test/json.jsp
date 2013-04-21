<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>

<html>
<head>
<title>主页面</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="easyUi/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyUi/themes/icon.css">
<script src="easyUi/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="easyUi/jquery.easyui.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#ok").click(function(){
			var userName = $("#userName").attr("value");
			var password = $("#password").attr("value");
			$.ajax({
				type:"POST",
				url:"demo!test1",
				data:{"userName":userName,"password":password},
				success:function(msg){
					alert(msg.userName );
					alert(msg.password);
					alert(msg.u.userName);
				}
			});
			
		});
	});
</script>

</head>
<body >
	用户名:<input id="userName" type="text" name="userName" >
	密码:<input type="text" id="password" name="password">
	<input type="button" id="ok" value="提交">
</body>
</html>