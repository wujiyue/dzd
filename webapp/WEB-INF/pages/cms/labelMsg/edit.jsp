<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>



<!DOCTYPE html>
<html>
<head>
  <title>编辑便签管理</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
	<meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

	<link rel="shortcut icon" href="favicon.ico">

	<link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
	<link href="/resources/lib/bootstrap/table/bootstrap-table.min.css" rel="stylesheet">
	<link href="/resources/css/animate.min.css" rel="stylesheet">
	<link href="/resources/css/index/index.css" rel="stylesheet">
	<link href="/resources/css/bro.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
</head>
<body>
<div class="wrapper  animated fadeInRight">
  <div class="container-fluid">
    <form action="" id="form_show" method="post" class="form-horizontal" role="form">
    <input type="hidden" value="${labelMsg.id}" id="id" name="id"/>
		<h2 class="text-center">编辑便签管理</h2>

        <div class="form-group">
              <label class="col-sm-2 control-label">便签类型：</label>
               <div class="col-sm-6 formControls">
                   <input type="hidden" id="label_msg_type" name="label_msg_type" placeholder="记录类型" datatype="*" nullmsg="请输入便签类型"  value="${labelMsg.label_msg_type}" class="form-control" />
                   <bro:broSelect idAttr="label_msg_type"   path="dicSelect_label_msg_type" url="/base/dictionary/json/select?type=label_msg_type"></bro:broSelect>
               </div>
              <div class="col-sm-4">
              		<div class="Validform_checktip"></div>
              </div>
         </div>
        <div class="form-group">
              <label class="col-sm-2 control-label">便签标题：</label>
               <div class="col-sm-6 formControls">
  <input type="text" id="title" name="title" placeholder="便签标题" value="${labelMsg.title}" class="form-control" datatype="*" nullmsg="请输入便签标题" />
              </div>
              <div class="col-sm-4">
              		<div class="Validform_checktip"></div>
              </div>
         </div>
        <div class="form-group">
              <label class="col-sm-2 control-label">便签内容：</label>
               <div class="col-sm-6 formControls">
                   <textarea rows="6" style="width:100%;"  id="content" name="content"  placeholder="记录内容" class="form-control" datatype="*" nullmsg="请输入便签内容">${labelMsg.content}</textarea>
               </div>
              <div class="col-sm-4">
              		<div class="Validform_checktip"></div>
              </div>
         </div>


		<div class="form-group">
        <div class=" col-sm-10 col-sm-offset-2">
          <button type="button" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>
      </div>
    </form></div>
</div>


<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>

<script type="text/javascript" src="${ctx}/resources/lib/Validform/Validform_v5.3.2.js"></script>

<script type="text/javascript">
	   var validform;
	function save(){
	    var b=validform.check(false);
		if(!b)
		{
			return;
		}
		var params=$("#form_show").serialize();
		$.ajax({
			type:"post",
			url:'${ctx}/cms/labelMsg/json/save?'+params,
			data:null,
			success:function(json,textStatus){
				broAjaxReturnMsg(json);
				setTimeout(function(){
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				},1000);
			}
		});
	}
	$(function(){
	 validform=$("#form_show").Validform({
     		  btnReset:"#reset",
             tiptype:2,
             postonce:true,//至提交一次
             ajaxPost:false,//ajax方式提交
             showAllError:true //默认 即逐条验证,true验证全部
           });


	})
</script>

</body>
</html>
