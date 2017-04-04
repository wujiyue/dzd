<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>编辑链接</title>
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
        url:'${ctx}/cms/link/json/save?'+params,
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
    <input type="hidden" value="${link.id}" id="id" name="id"/>
		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>所属模块:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="mkdm" datatype="*" value="${link.mkdm}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>用户id:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="userid" datatype="*" value="${link.userid}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>数据范围:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="datascope" datatype="*" value="${link.datascope}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>链接名称:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="name" datatype="*" value="${link.name}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>链接类型:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="linktypes" datatype="*" value="${link.linktypes}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>标签:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="tags" datatype="*" value="${link.tags}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>跳转链接:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="href" datatype="*" value="${link.href}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>跳转目标:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="target" datatype="*" value="${link.target}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">链接图片1:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="img"  value="${link.img}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">链接图片2:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="img2"  value="${link.img2}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>描述:</label>
			<div class="formControls col-3">
				<input type="text" class="input-text" name="description" value="${link.description}" />
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
