<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.SysPara" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%

    String pageRememberMeKey = "RememberMe";
    String cookieNameKey = "sdtjqf_ict_username";//cookie中用户名key
    String cookiePassKey = "sdtjqf_ict_password";
    String cookieTokenKey = "sdtjqf_ict_token";
  try {
    pageRememberMeKey= SysPara.getValue("pageRememberMeKey");
    cookieNameKey=SysPara.getValue("cookieNameKey");
    cookiePassKey=SysPara.getValue("cookiePassKey");
    cookieTokenKey=SysPara.getValue("cookieTokenKey");
  } catch (Exception e) {
    e.printStackTrace();
  }

%>
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
    var cookieName=getCookie('<%=cookieNameKey%>');
    var cookiePass=getCookie('<%=cookiePassKey%>');
    function getCookie(name){
      //获取cookie字符串
      var strCookie=document.cookie;
      //将多cookie切割为多个名/值对
      var arrCookie=strCookie.split("; ");
      var value="";
      //遍历cookie数组，处理每个cookie对
      for(var i=0;i<arrCookie.length;i++){
        var arr=arrCookie[i].split("=");
        if(name==arr[0]){
          value=arr[1];
          break;
        }
      }
      return value;
    }
    $(function(){
     // $("#account").val('');//getCookie("sdtjqf_ict_username"));
      if("${warning}"!=""){
        alert("${warning}");
        $("#account").focus();
      }
      $("#account").val(cookieName);
      $("#password").val(cookiePass);

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
          <input id="account" value="${username}" name="account" autocomplete="false" type="text" placeholder="账户" datatype="*3-10" class="input-text  size-L" nullmsg="请输入账户名！" />
        </div>
        <div class="col-3">
          <div class="Validform_checktip"></div>
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-2"><i class="iconfont">&#xf00c9;</i></label>
        <div class="formControls col-7">
          <input id="password" name="password" type="password" placeholder="密码"  autocomplete="false" class="input-text  size-L" datatype="*" nullmsg="请输入密码！" />
        </div>
        <div class="col-3">
          <div class="Validform_checktip"></div>
        </div>
      </div>
      <c:if test="${sys:getConfig('loginWithValidateCode')}">
      <div class="row cl">
        <div class="formControls col-7 col-offset-2">
          <bro:validateCode name="verycode"   inputClass="input-text  size-L" ></bro:validateCode>
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
          <input name="" type="submit" class="btn btn-success  size-L" style="font-weight: bold" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default  size-L"  value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 你的公司名称</div>

</body>

</html>