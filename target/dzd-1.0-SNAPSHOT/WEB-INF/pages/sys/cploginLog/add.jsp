<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>新增登录日志</title>
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
        url:'${ctx}/sys/cploginLog/json/save?'+params,
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
			<label class="form-label col-2"><span style='color:red;'>*</span>用户ID:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="yhid" datatype="*" value="${cploginLog.yhid}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>用户名称:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="yhmc" datatype="*" value="${cploginLog.yhmc}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="fjh" datatype="*" value="${cploginLog.fjh}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>登录的省份:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="sf" datatype="*" value="${cploginLog.sf}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>产品代码:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="cpdm" datatype="*" value="${cploginLog.cpdm}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>产品名称:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="cpmc" datatype="*" value="${cploginLog.cpmc}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>版本号:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="version" datatype="*" value="${cploginLog.version}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>登录时间:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="logintime" datatype="*" value="${cploginLog.logintime}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>登录IP:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="ip" datatype="*" value="${cploginLog.ip}" />
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
