<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>编辑文章</title>
  <script type="text/javascript" src="${ctx}/resources/js/H-ui.admin.js"></script>
  <script type="text/javascript">
    var validform;
    function save(){
      var b=validform.check(false);
      if(!b){
        return;
      }
      var params=$("#form_show").serialize();
      $.ajax({
        type:"post",
        url:'${ctx}/cms/article/json/save?'+params,
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
        tiptype:2,
        postonce:true,//至提交一次
        ajaxPost:false,//ajax方式提交
        showAllError:true //默认 即逐条验证,true验证全部
      });
    })
  </script>
</head>
<body>
<article class="page-container" >
  <div class="form form-horizontal responsive">
    <form action="" id="form_show" method="post">
    <input type="hidden" value="${article.id}" id="id" name="id"/>
      <div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>作者:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="userid" datatype="*" value="${article.userid}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>数据范围:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="datascope" datatype="*" value="${article.datascope}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>文章标题:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="title" datatype="*" value="${article.title}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>关键词:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="keywords" datatype="*" value="${article.keywords}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>摘要:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="description" datatype="*" value="${article.description}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>文章内容:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="content" datatype="*" value="${article.content}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>文章类别:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="articletypes" datatype="*" value="${article.articletypes}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>频道栏目ID:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="channelids" datatype="*" value="${article.channelids}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>来源:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="source" datatype="*" value="${article.source}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>标签:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="tags" datatype="*" value="${article.tags}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		
      <div class="row cl">
        <div class="formControls col-3 col-offset-2">
          <button type="button" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>
      </div>
    </form></div>
</article>
</body>
</html>
