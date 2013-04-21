<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">

	function doDeploy(){
		document.forms[0].submit();
	}
</script>
</head>
<body>

	<form name="upform" action="${ctx}/service/processDefinition/deployAction.do" method="POST"
		enctype="multipart/form-data">
		&nbsp;&nbsp;&nbsp;&nbsp; <input type="file" name="file1" id="file1" />
	</form>

</body>
</html>






