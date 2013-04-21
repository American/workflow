<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程管理页面</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css"
	href="easyUi/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="easyUi/themes/icon.css">
<script src="easyUi/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="easyUi/jquery.easyui.min.js" type="text/javascript"></script>
<script type="text/javascript" src="easyUi/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/util.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#loginInfoTable').datagrid({
			title : '查询结果',
			iconCls : 'icon-save',
			rownumbers : true,//行号 
			striped : true,
			singleSelect : true,
			loadMsg : '数据加载中......',
			pagination : true,
			pageSize : 10,
            pageList : [ 10, 20, 30, 40 ],
			width : 965,
			border : true,
			url : 'workflow!getProcessDefinitionList',
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'key',
				title : '流程KEY',
				width : 150,
				align : 'center'
			}, {
				field : 'id',
				title : '流程ID',
				width : 150,
				align : 'center'
			}, {
				field : 'deploymentId',
				title : '发布ID',
				width : 150,
				align : 'center'
			}, {
				field : 'name',
				title : '流程NAME',
				width : 150,
				align : 'center'
			}, {
				field : 'imageResourceName',
				title : '流程图片',
				width : 150,
				align : 'center'
			}, {
				field : 'state',
				title : '状态',
				width : 150,
				align : 'center'
			} ] ],
			onLoadSuccess : function(data) {
			},
			toolbar : [ {//正上方工具栏
				text : '部署流程',
				iconCls : 'icon-add',
				handler : function() {
					//点击工具栏运行的js方法
					$('#dd').dialog('open');
				},
			}, '-', {
				text : '删除流程',
				iconCls : 'icon-cancel',
				handler : function() {
					var deployId = $('#loginInfoTable').datagrid('getSelected').deploymentId;
					if("" == deployId){
						$.messager.alert('Warning','请选择一行记录!');
						return false;
					}
					$.messager.confirm('确认','确认删除记录?',function(r){
						if(r){
							$.ajax({
								type:"GET",
								url:"workflow!deleteProcess?deployId=" + deployId ,
								success:function(data){
									if("successful" == data.result){
										$.messager.show({
											title:'提示',
											msg:'删除成功',
											timeout:2000,
											});
										$('#loginInfoTable').datagrid('reload'); 
									}else{
										$.messager.alert({
											title:'提示',
											msg:'删除失败',
											timeout:2000,
											});
									}
									
								},
								error:function(){
									$.messager.alert({
										title:'提示',
										msg:'删除失败',
										timeout:2000,
										});
								}
							});
						}
					});
					
				}
			}, '-', {
				text : '挂起流程',
				iconCls : 'icon-remove',
				handler : function() {
					batch('delete');
				}
			} ],
		});

		
	});
	
	function upload(){
		$("#workflowForm").submit();
	}
	
</script>
</head>
<body>
		<!-- 表格 -->
		<table align="center" id="loginInfoTable"></table>
			<div id="dd" closed="true" class="easyui-dialog"
				style="padding: 5px; width: 400px; height: 200px;" title="上传ZIP流程文件"
				iconCls="icon-save"  buttons="#dlg-buttons" >
				<form id="workflowForm" action="workflow!deployProcessReal" method="post" ENCTYPE="multipart/form-data">
				选择ZIP文件:  <input type="file" name="workflowFile" >
				</form>
		    </div>
    <div id="dlg-buttons">  
	    <a href="javascript:void();" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:upload();">确定</a>  
	    <a href="javascript:void();" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dd').dialog('close');">取消</a>  
	</div> 

</body>
</html>