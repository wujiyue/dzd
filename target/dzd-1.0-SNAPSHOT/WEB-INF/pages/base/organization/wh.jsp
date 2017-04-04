<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>


<!DOCTYPE html>
<html>
<head>
  <title>组织机构信息维护</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <link rel="shortcut icon" href="favicon.ico">

  <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
  <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
  <link href="/resources/lib/icheck/custom.css" rel="stylesheet">
  <link href="/resources/css/animate.min.css" rel="stylesheet">
  <link href="/resources/css/index/index.css" rel="stylesheet">

</head>
<body>
<div class="container-fluid">
  <form action="" id="form_show" method="post" class="form-horizontal">
    <h2 class="text-center">组织机构信息维护</h2>
    <input type="hidden" value="${organization.id}" id="id" name="id"/>
    <div class="form-group">
      <label class="control-label col-sm-2"><span class="c-red">*</span>组织名称：</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" value="${organization.name}" placeholder="" id="name" name="name" datatype="*" nullmsg="请输入组织名称">
      </div>
      <div><div class="Validform_checktip"></div></div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2">描述：</label>
      <div class="col-sm-10">
        <textarea rows="3" style="height: 70px;" class="form-control"  placeholder="" id="description" name="description">${organization.description}</textarea>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2">电话：</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" value="${organization.phone}" placeholder="" id="phone" name="phone">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2">传真：</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" value="${organization.fax}" placeholder="" id="fax" name="fax">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2">添加时间：</label>
      <div class="col-sm-10">
        <input type="text" class="form-control " disabled readonly value="${organization.createTime}" placeholder="" id="createTime" name="createTime">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2">更新时间：</label>
      <div class="col-sm-10">
        <input type="text" class="form-control " disabled readonly value="${organization.updateTime}" placeholder="" id="updateTime" name="updateTime">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2">状态：</label>
      <div class="col-sm-10">
        <input type="text" class="form-control " disabled readonly value="${organization.available}" placeholder="" id="available" name="available">
      </div>
    </div>
    <div class="form-group" style="text-align: center">
      <div>
        <button type="button" onclick="save()" class="btn btn-primary radius" id="saveBtn" ><i class="icon-ok"></i> 确定</button>
      </div>
    </div>

  </form>
</div>
<script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script type="text/javascript" src="${ctx}/resources/lib/Validform/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/icheck/jquery.icheck.min.js"></script>
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
      url:'${ctx}/org/organization/json/save?'+params,
      data:null,
      success:function(json,textStatus){
        broAjaxReturnMsg(json);
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
</body>
</html>
