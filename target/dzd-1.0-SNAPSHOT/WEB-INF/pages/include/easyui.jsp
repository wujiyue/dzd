<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--<meta charset="utf-8">--%>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<%--<meta http-equiv="Cache-Control" content="no-siteapp" />--%>
<META HTTP-EQUIV="Expires" CONTENT="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
<!-- name="viewport" 的详细用法  http://www.php100.com/html/webkaifa/HTML5/2012/0831/10979.html -->
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0, maximum-scale=1,user-scalable=no">
<%--<link href="/favicon.ico" type="image/x-icon" rel="shortcut icon">
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />--%>
<!-- 
width - viewport的宽度 height - viewport的高度
initial-scale - 初始的缩放比例
minimum-scale - 允许用户缩放到的最小比例
maximum-scale - 允许用户缩放到的最大比例
user-scalable - 用户是否可以手动缩放
 -->
<script type="text/javascript" src="${ctx}/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script  type="text/javascript">
	var basePath = "${ctx}";
	var sys_ctx="${ctx}";
</script>
<link href="${ctx}/resources/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />


<!-- EasyUI -->
<link rel="stylesheet" type="text/css" href="${ctx}/resources/lib/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/lib/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx}/resources/lib/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/easyui/easyloader.js"></script>


<link  href="${ctx}/resources/css/H-ui.css" rel="stylesheet" type="text/css"/>
<link  href="${ctx}/resources/css/bro.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
<script  type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/Validform/Validform_v5.3.2.js"></script>

<%if(request.getAttribute("prompt")!=null){%>
alert('<%=request.getAttribute("prompt")%>');
<%}%>
<%if(request.getAttribute("warning")!=null){%>
alert('<%=request.getAttribute("warning")%>');
<%}%>