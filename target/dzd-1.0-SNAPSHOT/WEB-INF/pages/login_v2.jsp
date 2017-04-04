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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>H+ 后台主题UI框架 - 登录</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <link rel="shortcut icon" href="favicon.ico">

    <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">

    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/index/index.css" rel="stylesheet">
    <link href="/resources/css/login_h.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>
        if(window.top!==window.self){window.top.location=window.location};
    </script>
</head>
<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>[ H+ ]</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>H+ 后台主题UI框架vv</strong></h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                    </ul>
                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                <form method="post" action="/login">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到H+后台主题UI框架</p>
                    <input type="text" class="form-control uname" id="account" name="account"  value="${username}" placeholder="用户名" />
                    <input type="password" class="form-control pword m-b" id="password" name="password"  placeholder="密码" />
                    <c:if test="${sys:getConfig('loginWithValidateCode')}">
                        <bro:validateCode name="verycode"   inputClass="form-control m-b" ></bro:validateCode>
                    </c:if>
                    <label for="jzmm" style="font-size:12px;color:#555;"><input type="checkbox" id="jzmm" name="RememberMe">&nbsp;记住密码 </label>
                    <a href="#" style="float: right;">忘记密码了？</a>
                  <%--  <button class="btn btn-success btn-block">登录</button>--%>
                    <input  type="submit" class="btn btn-success btn-block" style="font-weight: bold" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2015 All Rights Reserved. H+
            </div>
        </div>
    </div>


    <script type="text/javascript" src="${ctx}/resources/lib/jquery/jquery.min.js"></script>
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
</body>
</html>
