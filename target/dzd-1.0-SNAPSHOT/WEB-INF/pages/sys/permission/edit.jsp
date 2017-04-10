<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>编辑权限菜单</title>
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
  <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
  <style>
    .mui-switch {
      width: 52px;
      height:25px;
      position: relative;
      border: 1px solid #dfdfdf;
      background-color: #fdfdfd;
      box-shadow: #dfdfdf 0 0 0 0 inset;
      border-radius: 20px;
      border-top-left-radius: 20px;
      border-top-right-radius: 20px;
      border-bottom-left-radius: 20px;
      border-bottom-right-radius: 20px;
      background-clip: content-box;
      display: inline-block;
      -webkit-appearance: none;
      user-select: none;
      outline: none; }
    .mui-switch:before {
      content: '';
      width: 24px;
      height: 24px;
      position: absolute;
      top: 0px;
      left: 0;
      border-radius: 20px;
      border-top-left-radius: 20px;
      border-top-right-radius: 20px;
      border-bottom-left-radius: 20px;
      border-bottom-right-radius: 20px;
      background-color: #fff;
      box-shadow: 0 1px 3px rgba(0, 0, 0, 0.4); }
    .mui-switch:checked {
      border-color: #64bd63;
      box-shadow: #64bd63 0 0 0 16px inset;
      background-color: #64bd63; }
    .mui-switch:checked:before {
      left: 26px; }
    .mui-switch.mui-switch-animbg {
      transition: background-color ease 0.4s; }
    .mui-switch.mui-switch-animbg:before {
      transition: left 0.3s; }
    .mui-switch.mui-switch-animbg:checked {
      box-shadow: #dfdfdf 0 0 0 0 inset;
      background-color: #64bd63;
      transition: border-color 0.4s, background-color ease 0.4s; }
    .mui-switch.mui-switch-animbg:checked:before {
      transition: left 0.3s; }
    .mui-switch.mui-switch-anim {
      transition: border cubic-bezier(0, 0, 0, 1) 0.4s, box-shadow cubic-bezier(0, 0, 0, 1) 0.4s; }
    .mui-switch.mui-switch-anim:before {
      transition: left 0.3s; }
    .mui-switch.mui-switch-anim:checked {
      box-shadow: #64bd63 0 0 0 16px inset;
      background-color: #64bd63;
      transition: border ease 0.4s, box-shadow ease 0.4s, background-color ease 1.2s; }
    .mui-switch.mui-switch-anim:checked:before {
      transition: left 0.3s; }



  </style>
</head>
<body>
<div class="wrapper  animated fadeInRight">
  <div class="container-fluid">
    <form action="" id="form_show" method="post" class="form-horizontal " role="form">
      <input type="hidden" value="${permission.id}" id="id" name="id"/>
      <h2 class="text-center">编辑权限菜单</h2>
      <div class="form-group">
        <label class="control-label col-sm-2"><span class="text-danger">*</span>父权限：</label>
        <div class="col-sm-6">
          <input type="text" class="form-control" readonly  id="parentname"  name="parentname"    value="${permission.parentname}" />
          <input type="hidden"  id="parentid" name="parentid"  value="${permission.parentid}" />
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-2"><span class="text-danger">*</span>权限名称：</label>
        <div class="col-sm-6 formControls">
          <input type="text" class="form-control"  id="name"  name="name" placeholder="权限名称" value="${permission.name}" datatype="*" nullmsg="请输入权限名称" />
        </div>
        <div class="col-sm-4">
          <div class="Validform_checktip"></div>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-2"><span class="text-danger">*</span>权限url:</label>
        <div class="col-sm-6 formControls">
          <input type="text" class="form-control"  id="url"  name="url"   value="${permission.url}"  datatype="*" nullmsg="请输入权限url" />
        </div>
        <div class="col-sm-4">
          <div class="Validform_checktip"></div>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-2"><span class="text-danger">*</span>权限类型:</label>
        <input type="hidden" id="qxlx" name="qxlx" value="${permission.qxlx}">
        <div class="col-sm-6 formControls">

          <label><input type="radio" id="radio_page"  name="option1" >页面</label>

          <label><input type="radio" id="radio_btn" name="option1">按钮</label>
          <script>
         var qxlx=  $("#qxlx").val();
         if(qxlx==2||qxlx=='2'){

           $("#radio_btn").prop("checked","checked");
         }else{
           $("#radio_page").prop("checked","checked");
         }
            $("input[type='radio']").click(function(){
              if($("#radio_btn").is(":checked")){
                $("#qxlx").val("2");
              }else{
                $("#qxlx").val("1");
              }
            });
          </script>
        </div>

        <div class="col-sm-4">
          <div class="Validform_checktip"></div>
        </div>
      </div>
      <div class="form-group">
        <label class="control-label col-sm-2">权限代码:</label>
        <div class="col-sm-6 formControls">
          <input type="text" class="form-control"  id="code"  name="code" value="${permission.code}" />
        </div>
        <div class="col-sm-4">
          <div class="Validform_checktip"></div>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-2">图标:</label>
        <div class="col-sm-6 formControls">
          <input type="text" class="form-control"  id="icon"  value="${permission.icon}" name="icon" style="float:left;width: 50%;" onclick="base_openIconPage('icon',$(this).val())"/>
          <i style="line-height:35px;margin-left:10px;width: 20px;height:20px;display: inline-block;float:left;font-size: 20px;" class="${permission.icon}"></i>

        </div>
        <div class="col-sm-4">
          <div class="Validform_checktip"></div>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-2"><span class="text-danger">*</span>是否有效:</label>
        <div class="col-sm-10">
          <input type="hidden" id="available" name="available" value="${permission.available}"/>
          <c:choose>
            <c:when test="${permission.available==1}">
              <label><input class="mui-switch mui-switch-anim" type="checkbox" checked></label>
            </c:when>
            <c:otherwise>
              <label><input class="mui-switch mui-switch-anim" type="checkbox"></label>
            </c:otherwise>
          </c:choose>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-sm-2">描述:</label>
        <div class="col-sm-6 formControls">
          <textarea rows="2" style="height: 50px;" class="form-control"  placeholder="" id="description" name="description">${permission.description}</textarea>
        </div>
        <div class="col-sm-4">
          <div class="Validform_checktip"></div>
        </div>
      </div>

      <div class="form-group">
        <div class=" col-sm-10 col-sm-offset-2">
          <button type="button" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>
      </div>
    </form></div>
</div>


<script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>

<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/Validform/Validform_v5.3.2.js"></script>


<script type="text/javascript">
  var validform;
  function save(){
    var b=validform.check(false);
    if(!b)
    {
      return;
    }
    var params=$("#form_show").serialize();
    $.ajax({
      type:"post",
      url:'${ctx}/sys/permission/json/save?'+params,
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

    $(".mui-switch").on("click",function(){
      var v=$("#available").val();
      if(v==1||v=='1'){
        $("#available").val("0");
      }else{
        $("#available").val("1");
      }
    });
    validform=$("#form_show").Validform({
      btnReset:"#reset",
      tiptype:2,
      postonce:true,//至提交一次
      ajaxPost:false,//ajax方式提交
      showAllError:true //默认 即逐条验证,true验证全部
    });
  })
</script>

</body>
</html>
