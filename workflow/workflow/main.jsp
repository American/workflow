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
<script type="text/javascript"></script>
</head>
<body class="easyui-layout">
	<!-- 正上方panel -->
	<div region="north" style="height: 85px; padding: 10px; background-color: #CECEFF;font-style:oblique;">
	 <h1>工作流管理系统</h1>
	</div>
	<!-- 正左边panel -->
	<div region="west" title="菜单栏" split="true" style="width: 200px; padding1: 2px; overflow: hidden;">
		<div class="easyui-accordion" fit="false" border="false">
			<!-- selected -->
			<div title="流程管理" selected="true">
				<ul>
					<li>
						<a href="javascript:addTab('workflowMgr','流程管理','workflowManager/workflowMgr.jsp');">管理流程</a></li>
					<li><a
						href="javascript:addTab('workflowInfo','流程查询','workflowManager/workflowMgr.jsp');">查看流程</a></li>
				</ul>
			</div>
			<div title="任务受理人管理"  selected="flase">
				<ul>
					<li><a
						href="#">用户管理</a></li>
					<li><a
						href="#">权限管理</a></li>
				</ul>
			</div>
			<div title="我的办公桌"  selected="flase">
				<ul>
					<li><a
						href="workflow!deployProcess">部署流程</a></li>
					<li><a
						href="#">创建流程实例</a></li>
				</ul>
			</div>
			<div title="数据统计"  selected="flase">
				<ul>
					<li><a
						href="#">用户管理</a></li>
					<li><a
						href="#">权限管理</a></li>
				</ul>
			</div>
		</div>
		
	</div>
	<!-- 正中间panel -->
	<div region="center" title="功能区">
		<div class="easyui-tabs" id="centerTab" fit="true" border="false">
			<div title="欢迎页" style="padding: 20px; overflow: hidden;">
				<div style="margin-top: 20px;">
					<h3>你好，欢迎来到工作流系统</h3>
				</div>
			</div>
		</div>
	</div>
</body>
</html>