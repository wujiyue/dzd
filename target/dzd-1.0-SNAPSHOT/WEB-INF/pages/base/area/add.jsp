<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>新增区域</title>
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

</head>
<body>
<div class="wrapper  animated fadeInRight">
  <div class="container-fluid">
    <form action="" id="form_show" method="post" class="form-horizontal" role="form">
      <h2 class="text-center">新增区域</h2>
      <div class="form-group">
        <label class="control-label col-sm-2"><span class="text-danger">*</span>区域类型：</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" readonly  id="areatypename"  name="areatypename"  value="${areatypename}" />
          <input type="hidden"  name="areatype"   value="${areatype}" />
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-2"><span class="text-danger">*</span>上级区域：</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" readonly  id="parentname"  name="parentname"  value="${parentname}" />
          <input type="hidden"   name="parentid"   value="${parentid}" />
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-2"><span class="text-danger">*</span>区域编码:</label>
        <div class="col-sm-10">
          <input type="text" class="form-control"  id="code"  name="code" placeholder="区域编码"  value="${code}" />
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-2"><span class="text-danger">*</span>区域名称:</label>
        <div class="col-sm-10">
          <input type="text" class="form-control"  id="name" name="name"   placeholder="区域名称" />
        </div>
      </div>

      <div class="form-group">
        <div class=" col-sm-10 col-sm-offset-2">
          <button type="submit" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>
      </div>
    </form></div>
</div>

<script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/resources/lib/Validform/Validform_v5.3.2.js"></script>--%>

<script src="/resources/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>

<script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
<script type="text/javascript">

  function save(){

    var b=$("#form_show").data('bootstrapValidator').isValid();
    if(!b)
    {
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

    $('form').bootstrapValidator({
      message: 'This value is not valid',
      excluded: [':disabled'],
      feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
        name: {
          message: '区域名称验证失败',
          validators: {
            notEmpty: {
              message: '区域名称不能为空'
            }
          }
        },
        code: {
          message: '区域编码验证失败',
          validators: {
            notEmpty: {
              message: '区域编码不能为空'
            }
          }
        }
      },
      submitHandler: function (validator, form, submitButton) {
        alert("submit");
      }
    }).on('success.form.bv', function (e) {
      e.preventDefault();
    });

  })
</script>

</body>
</html>
