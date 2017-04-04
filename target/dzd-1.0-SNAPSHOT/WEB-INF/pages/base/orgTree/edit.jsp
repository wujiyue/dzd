<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>编辑部门</title>
<%--	<script type="text/javascript" src="${ctx}/resources/js/H-ui.admin.js"></script>--%>
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
				url:'${ctx}/org/department/json/save?'+params,
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
			<input type="hidden" value="${department.id}" id="id" name="id"/>
			<div class="row cl">
				<label class="form-label col-2"><span style='color:red;'>*</span>上级部门</label>
				<div class="formControls col-3">
					<input type="hidden" class="input-text" name="parentid" datatype="*" value="${department.parentid}" />
					<input type="text" class="input-text disabled" readonly name="parentname"  value="${department.parentname}" />
				</div>
				<div>
					<div class="Validform_checktip"></div>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-2"><span style='color:red;'>*</span>部门名称</label>
				<div class="formControls col-3">
					<input type="text" class="input-text" name="name" value="${department.name}" datatype="*" nullmsg="请输入部门名称"/>
				</div>
				<div>
					<div class="Validform_checktip"></div>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">电话</label>
				<div class="formControls col-3">
					<input type="text" class="input-text" value="${department.phone}" name="phone"/>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">传真</label>
				<div class="formControls col-3">
					<input type="text" class="input-text" value="${department.fax}" name="fax"  />
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">邮箱</label>
				<div class="formControls col-3">
					<input type="text" class="input-text" value="${department.email}" name="email" />
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-2">备注</label>
				<div class="formControls col-3">
					<textarea rows="3" style="height: 70px;" class="input-text"  placeholder=""  id="bz" name="bz">${department.bz}</textarea>
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
