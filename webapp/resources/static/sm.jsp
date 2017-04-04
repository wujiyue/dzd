<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.EhCacheUtils" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<LINK rel="Bookmark" href="/favicon.ico" >
	<LINK rel="Shortcut Icon" href="/favicon.ico" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${ctx}/resources/lib/html5.js"></script>
	<script type="text/javascript" src="${ctx}/resources/lib/respond.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/lib/PIE_IE678.js"></script>
	<![endif]-->
	<%@include file="/WEB-INF/pages/include/common.jsp"%>

	<%if(request.getAttribute("prompt")!=null){%>
	alert('<%=request.getAttribute("prompt")%>');
	<%}%>
	<link href="${ctx}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
	<link href="/resources/lib/layui-v1.0.7/layui/css/layui.css" rel="stylesheet">
	<link href="${ctx}/resources/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
	<link href="${ctx}/resources/css/bro.css" rel="stylesheet" type="text/css" />
	<script  type="text/javascript" src="${ctx}/resources/js/common/main.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/H-ui.admin.js"></script>
	<script src="/resources/lib/layui-v1.0.7/layui/layui.js"></script>
	<!--[if IE 6]>
	<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<title>H-ui.admin v2.3</title>
	<meta name="keywords" content="H-ui.admin v2.3,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
	<meta name="description" content="H-ui.admin v2.3，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
	<style>
		.menu_dropdown dl dd ul li i{display:block;float:left;}
	</style>
</head>
<body>
<h3 style="text-align: center">使用说明</h3>
<div class="" style="padding: 20px;">
	<script type="text/javascript">
		function aa(obj){
			alert($(obj).val());
		}
$(function(){
	$("[name='available']").bootstrapSwitch();
})

	</script>
	<input type="hidden" id="testid">
<bro:broSelect  idAttr="testid" path="test" url="/sys/test"></bro:broSelect>
<bro:genderSelect path="sex"></bro:genderSelect>
<input type="text" id="userid" placeholder="隐藏域存id" />
<input type="text" id="username" placeholder="存显示名称"  onclick="base_openYhxzPage('userid','username')">
<br>图标选择：
	<input type="text" id="iconname" placeholder="存显示名称"  onclick="base_openIconPage('iconname',$(this).val())">
	<br>资源分类选择：
	<input type="text" id="resourcetypeid" placeholder="隐藏域存id" />
	<input type="text" id="resourcetypename" placeholder="存显示名称"  onclick="base_openZyflxzPage('resourcetypeid','resourcetypename',parent)">
	<br>资源分类选择：
	<input type="text" id="resourcetype_id" value="240">
	<bro:broSelect idAttr="resourcetype_id" onchange="aa(this)"  path="resourcetypeSelect" url="/cms/resourcetype/json/treeSelect"></bro:broSelect>
	<br>省市区选择：
	<bro:province id="area" name="area"></bro:province>
	<br>字典选择：
	<input type="text" id="deleted">
	<bro:broSelect idAttr="deleted"   path="dicSelect_deleted" url="/base/dictionary/json/select?type=deleted"></bro:broSelect>
	<br>字典选择：
	<bro:dicSelect id="sys_office_type" datatype="*" nullmsg="请选择区域类型"></bro:dicSelect>

	<br><br>
	<br>产品选择：
	<input type="text" id="cpdm" value="">
	<bro:broSelect idAttr="cpdm" onchange="aa(this)"  path="productSelect" url="/cms/product/json/select"></bro:broSelect>
	<form class="layui-form" action="">
	<div class="layui-form-item">
		<label class="layui-form-label">开关-开</label>
		<div class="layui-input-block">
			<input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" title="开关">
		</div>
	</div>

</form>
	<script>
		layui.use(['form', 'layedit', 'laydate'], function(){
			var form = layui.form()
					,layer = layui.layer
					,layedit = layui.layedit
					,laydate = layui.laydate;
		});
	</script>
</div>
</body>
</html>