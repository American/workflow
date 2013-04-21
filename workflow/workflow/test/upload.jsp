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
		
	});
</script>

</head>
<body >
	<form  name="demoForm" action="demo!upload" method="post" ENCTYPE="multipart/form-data" >
	<input type="file" name="image">
	<input type="submit" id="ok" value="提交">
	</form>
</body>
</html>