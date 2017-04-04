<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.SysPara" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%--<%@include file="/WEB-INF/pages/include/common.jsp"%>--%>
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>登录</title>
        <script type="text/javascript" src="${ctx}/resources/lib/jquery/jquery.min.js"></script>
       <%-- <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>--%>
        <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/H-ui.css">
        <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/animate.min.css">
        <script type="text/javascript">
            var cookieName=getCookie('<%=cookieNameKey%>');
            var cookiePass=getCookie('<%=cookiePassKey%>');
            var cookieRm=getCookie('<%=pageRememberMeKey%>');
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
                if(cookieRm=="1")
                {
                    $("#jzmm").attr("checked",'true');
                }
                if("${warning}"!=""){
                    alert("${warning}");
                    $("#account").focus();
                }
                $("#account").val(cookieName);
                $("#password").val(cookiePass);

            })
        </script>
      <style>
           body.signin{    height: auto;
               background: url(/front/images/login-background.jpg) no-repeat center fixed;
               -webkit-background-size: cover;
               -moz-background-size: cover;
               -o-background-size: cover;
               background-size: cover;
               color: rgba(255,255,255,.95);
           }
           .signinpanel {
               width: 750px;
               margin: 10% auto 0;
           }
           .row {
               margin-right: -15px;
               margin-left: -15px;
           }
           .pull-left {
               float: left!important;
           }
           .signinpanel form {
               background: rgba(255,255,255,.2);
               border: 1px solid rgba(255,255,255,.3);
               -moz-box-shadow: 0 3px 0 rgba(12,12,12,.03);
               -webkit-box-shadow: 0 3px 0 rgba(12,12,12,.03);
               box-shadow: 0 3px 0 rgba(12,12,12,.03);
               -moz-border-radius: 3px;
               -webkit-border-radius: 3px;
               border-radius: 3px;
               padding: 30px;
           }
           .no-margins {
               margin: 0!important;
           }
           h3, h4, h5 {
               margin-top: 5px;
               font-weight: 600;
           }
           h4 {
               font-size: 14px;
           }
           .m-t-md {
               margin-top: 20px;
           }
           p {
               margin: 0 0 10px;
           }
           .signinpanel .uname {
               background: #fff url(/front/images/user.png) no-repeat 95% center;
               color: #333;
           }
           .signinpanel .form-control {
               display: block;
               margin-top: 15px;
           }
           .form-control {
               display: block;
               width: 100%;
               height: 34px;
               padding: 6px 12px;
               font-size: 14px;
               line-height: 1.42857143;
               color: #555;
               background-color: #fff;
               background-image: none;
               border: 1px solid #ccc;
               border-radius: 4px;
               -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
               box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
               -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
               -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
               transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
           }
           .form-control, .single-line {
               background-color: #FFF;
               background-image: none;
               border: 1px solid #e5e6e7;
               border-radius: 1px;
               color: inherit;
               display: block;
               padding: 6px 12px;
               -webkit-transition: border-color .15s ease-in-out 0s,box-shadow .15s ease-in-out 0s;
               transition: border-color .15s ease-in-out 0s,box-shadow .15s ease-in-out 0s;
               width: 100%;
               font-size: 14px;
           }
           .form-control, .form-control:focus, .has-error .form-control:focus, .has-success .form-control:focus, .has-warning .form-control:focus, .navbar-collapse, .navbar-form, .navbar-form-custom .form-control:focus, .navbar-form-custom .form-control:hover, .open .btn.dropdown-toggle, .panel, .popover, .progress, .progress-bar {
               box-shadow: none;
           }

           .signinpanel .pword {
               background: #fff url(/front/images/locked.png) no-repeat 95% center;
               color: #333;
           }
           a {
               cursor: pointer;
           }
           a {
               color: #337ab7;
               text-decoration: none;
           }
           a {
               background-color: transparent;
           }
           .signinpanel .btn {
               margin-top: 15px;
           }
           .btn-success {
               background-color: #1c84c6;
               border-color: #1c84c6;
               color: #FFF;
           }
           .btn {
               border-radius: 3px;
           }
           .btn-block {
               display: block;
               width: 100%;
           }
           .signup-footer {
               border-top: solid 1px rgba(255,255,255,.3);
               margin: 20px 0;
               padding-top: 15px;
           }
           .btn-success.active, .btn-success:active, .btn-success:focus, .btn-success:hover, .open .dropdown-toggle.btn-success {
               background-color: #1a7bb9;
               border-color: #1a7bb9;
               color: #FFF;
           }
           @media screen and (max-width: 768px) {
               .signup-footer, .signuppanel .form-control {
                   margin-bottom: 10px;
               }

               .signup-footer .pull-left, .signup-footer .pull-right {
                   float: none !important;
                   text-align: center;
               }

               .signinpanel, .signuppanel {
                   margin: 0 auto;
                   width: 420px !important;
                   padding: 20px;
               }
               .signinpanel .signin-info ul {
                   display: none;
               }
               [class^="col-"], [class*=" col-"] {
                   box-sizing: border-box;
                   float: none;
                   min-height: 1px;
                   position: relative;
               }
           }
           .row {
               margin-right: -15px;
               margin-left: -15px;
           }

               .signinpanel .signin-info ul {
                   list-style: none;
                   padding: 0;
                   margin: 20px 0;
               }
               .m-b {
                   margin-bottom: 15px;
               }
           .form-control:focus, .single-line:focus {
               border-color: #1ab394!important;
           }
           a:focus, a:hover {
               color: #23527c;
               text-decoration: none;
           }

        </style>
    </head>

    <body class="signin">

    <div class="signinpanel clearfix">
        <div class="row clearfix">
            <div class="col-sm-7 animated fadeInLeft">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>[ H+ ]</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>H+ 后台主题UI框架1111</strong></h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                    </ul>
                    <strong>还没有账号？ <a href="/reg">立即注册»</a></strong>
                </div>
            </div>
            <div class="col-sm-5 animated fadeInRight">
                <form method="post" action="/login">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到H+后台主题UI框架</p>
                    <input type="text" class="input-text uname m-b" id="account" value="${username}" name="account" placeholder="用户名" required="">
                    <input type="password"  class="input-text pword m-b" id="password" name="password" placeholder="密码" required="">
                    <c:if test="${sys:getConfig('loginWithValidateCode')}">
                        <bro:validateCode name="verycode"   inputClass="input-text m-b" ></bro:validateCode>
                    </c:if>
                    <label for="jzmm" style="font-size:12px;color:#333;"><input type="checkbox" id="jzmm" name="RememberMe">&nbsp;记住密码 </label><a class="f-r m-b" href="#">忘记密码了？</a>
                    <%--<button class="btn btn-success btn-block">登录</button>--%>
                    <input  type="submit" class="btn btn-success btn-block" style="font-weight: bold" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                © 2015 All Rights Reserved. H+
            </div>
        </div>
    </div>


    </body>
</html>

