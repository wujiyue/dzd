<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>新增部门</title>
	<link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
	<link href="/resources/lib/bootstrap/table/bootstrap-table.min.css" rel="stylesheet">
	<link href="/resources/css/animate.min.css" rel="stylesheet">
	<link href="/resources/css/index/index.css" rel="stylesheet">
	<link href="/resources/css/bro.css" rel="stylesheet">

	<script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/H-ui.js"></script>
	<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
	<%--		<script src="/resources/js/content.min.js"></script>
    <script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
        <script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
        <script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>--%>


	<script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
	<script type="text/javascript" src="${ctx}/resources/lib/Validform/Validform_v5.3.2.js"></script>

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
	function reset(){
		alert(JSON.stringify(validform));
	//	validform.resetForm();
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
</head>
<body>
<div class="wrapper  animated fadeInRight">
	<div class="container-fluid">
    <form action="" id="form_show" method="post" class="form-horizontal" role="form">
		<h2 class="text-center">新增部门</h2>

		<div class="form-group">
			<label class="control-label col-sm-2"><span class="text-danger">*</span>上级部门：</label>
			<div class="col-sm-6">
				<input type="hidden" class="input-text" name="parentid" datatype="*" value="${parentid}" />
				<input type="text" class="form-control" readonly name="parentname"  value="${parentname}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2"><span class="text-danger">*</span>部门名称：</label>
			<div class="col-sm-6 formControls">
				<input type="text" class="form-control" name="name" datatype="*" nullmsg="请输入部门名称"/>
			</div>
			<div class="col-sm-4">
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2"><span class="text-danger">*</span>电话：</label>
			<div class="col-sm-6 formControls">
				<input type="text" class="form-control" name="phone" datatype="*" nullmsg="请输入电话"/>
			</div>
			<div class="col-sm-4">
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2"><span class="text-danger">*</span>传真：</label>
			<div class="col-sm-6 formControls">
				<input type="text" class="form-control" name="fax" datatype="*" nullmsg="请输入传真"/>
			</div>
			<div class="col-sm-4">
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-sm-2"><span class="text-danger">*</span>邮箱：</label>
			<div class="col-sm-6 formControls">
				<input type="text" class="form-control" name="email" datatype="*" nullmsg="请输入邮箱"/>
			</div>
			<div class="col-sm-4">
				<div class="Validform_checktip"></div>
			</div>
		</div>
		<div class="form-group formControls">
			<label class="control-label col-sm-2"><span class="text-danger">*</span>邮箱：</label>
			<div class="col-sm-6">
				<textarea rows="3" style="height: 70px;" class="form-control"  placeholder="" id="bz" name="bz"></textarea>
			</div>
			<div class="col-sm-4">
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="form-group">
      <%--  <div class="formControls col-3 col-offset-2">
          <button type="button" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>--%>
			<div class=" col-sm-10 col-sm-offset-2">
				<button type="submit" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
				<button   class="btn"  id="reset" onclick="reset()"  >重置</button>
			</div>
      </div>
    </form></div>
</div>
</body>
</html>
