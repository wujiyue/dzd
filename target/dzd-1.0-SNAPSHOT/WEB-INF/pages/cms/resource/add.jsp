<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>新增资源</title>
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
        url:'${ctx}/cms/resource/json/save?'+params,
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
      <div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>资源分类:</label>
		 <div class="formControls col-3">
			<input type="hidden" class="input-text" id="resourcetypeid" name="resourcetypeid" datatype="*" nullmsg="请选择分类" value="${resource.resourcetypeid}" />
			 <%--<input type="text" class="input-text" name="resourcetypename"  onclick="base_openZyflxzPage('resourcetypeid','resourcetypename')"  value="${resource.resourcetypename}" />--%>
			 <bro:broSelect idAttr="resourcetypeid"  path="resourcetypeSelect" url="/cms/resourcetype/json/treeSelect"></bro:broSelect>
		 </div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>资源名称:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="name" datatype="*" value="${resource.name}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">搜索关键词:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="keywords"  value="${resource.keywords}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">来源:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="from"  value="${resource.from}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">链接:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="url"  value="${resource.url}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">链接(小):</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="urlsmall"  value="${resource.urlsmall}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>


		<div class="row cl">
			<label class="form-label col-2">资源状态:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="resourcestate"  value="${resource.resourcestate}" />
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
