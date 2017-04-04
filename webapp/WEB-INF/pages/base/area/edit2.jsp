<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>编辑区域</title>
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
				url:'${ctx}/base/area/json/save?'+params,
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
			<input type="hidden" value="${area.id}" id="id" name="id"/>
			<div class="row cl">
				<label class="form-label col-2">区域类型</label>
				<div class="formControls col-3">
					<input type="hidden" class="input-text" name="areatype" datatype="*" value="${area.areatype}" />
					<input type="text" class="input-text disabled" readonly name="areatypename"  value="${area.areatypename}" />

				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-2">上级区域</label>
				<div class="formControls col-3">
					<input type="hidden" class="input-text" name="parentid" datatype="*" value="${area.parentid}" />
					<input type="text" class="input-text disabled" readonly  name="parentname"  value="${area.parentname}" />
				</div>
				<div>
					<div class="Validform_checktip"></div>
				</div>
			</div>


			<div class="row cl">
				<label class="form-label col-2"><span style='color:red;'>*</span>区域编码</label>
				<div class="formControls col-3">
					<input type="text" class="input-text" name="code" datatype="*" value="${area.code}" nullmsg="请输入地区编码"/>
				</div>
				<div>
					<div class="Validform_checktip"></div>
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-2"><span style='color:red;'>*</span>区域名称</label>
				<div class="formControls col-3">
					<input type="text" class="input-text" name="name" datatype="*" value="${area.name}"  nullmsg="请输入区域名称" />
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
