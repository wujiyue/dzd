<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>编辑图片</title>
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
        url:'${ctx}/cms/image/json/save?'+params,
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
    <input type="hidden" value="${image.id}" id="id" name="id"/>
      <div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="yhid" datatype="*" value="${image.yhid}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>数据访问类型:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="datascope" datatype="*" value="${image.datascope}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>图片名称:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="name" datatype="*" value="${image.name}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="url" datatype="*" value="${image.url}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="url_small" datatype="*" value="${image.url_small}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>大小:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="size" datatype="*" value="${image.size}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>后缀:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="suffixes" datatype="*" value="${image.suffixes}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>关键词:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="keywords" datatype="*" value="${image.keywords}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>资源类型:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="resourcetypeid" datatype="*" value="${image.resourcetypeid}" />
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
