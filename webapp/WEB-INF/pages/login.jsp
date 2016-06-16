<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>

  <%@include file="/WEB-INF/pages/include/common.jsp"%>

  <link href="../../resources/css/H-ui.login.css" rel="stylesheet" type="text/css" />

  <title>Bro信息管理系统后台登录 - Bro.admin v1.0</title>
  <meta name="keywords" content="Bro.admin v1.0,Bro网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
  <meta name="description" content="Bro.admin v1.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
  <style type="text/css">
    /*重写样式让这里的样式更好看*/
    .Validform_checktip{margin-top: 7px;margin-left: 7px;}
  </style>
  <script type="text/javascript">


    $(function(){
     // $("#account").val('');//getCookie("sdtjqf_ict_username"));
      if("${warning}"!=""){
        alert("${warning}");
        $("#account").focus();
      }

      $("#loginForm").Validform({
        tiptype:2,
        /*  btnSubmit:"#sub",
         btnReset:"#rset",*/
        showAllError:true //默认 即逐条验证,true验证全部
      });
    })
  </script>
</head>
<body>

<div class="header"></div>
<div class="loginWraper">
  <div  class="loginBox">
    <form id="loginForm" class="form form-horizontal" action="/login" method="post">
      <div class="row cl">
        <div class="formControls col-10 col-offset-2">
        <bro:msg style="position:absolute;top:-35px;width:310px"></bro:msg>

        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-2"><i class="iconfont">&#xf00ec;</i></label>
        <div class="formControls col-7">
          <input id="account" value="${username}" name="account" type="text" placeholder="账户" datatype="*3-10" class="input-text radius size-L" nullmsg="请输入账户名！" />
        </div>
        <div class="col-3">
          <div class="Validform_checktip"></div>
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-2"><i class="iconfont">&#xf00c9;</i></label>
        <div class="formControls col-7">
          <input id="password" name="password" type="password" placeholder="密码" class="input-text radius size-L" datatype="*" nullmsg="请输入密码！" />
        </div>
        <div class="col-3">
          <div class="Validform_checktip"></div>
        </div>
      </div>
      <c:if test="${sys:getConfig('loginWithValidateCode')}">
      <div class="row cl">
        <div class="formControls col-7 col-offset-2">
          <bro:validateCode name="verycode"   inputClass="input-text radius size-L" ></bro:validateCode>
          </div>
        <div class="col-3">
          <div class="Validform_checktip"></div>
        </div>
      </div>
      </c:if>
      <div class="row">
        <div class="formControls col-7 col-offset-2">
          <label for="rememberMe">
            <input type="checkbox" name="rememberMe" id="rememberMe" ${rememberMe ? 'checked' : ''}>
            记住我</label>
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-2">
          <input name="" type="submit" class="btn btn-success radius size-L" style="font-weight: bold" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L"  value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 你的公司名称</div>

</body>

</html>