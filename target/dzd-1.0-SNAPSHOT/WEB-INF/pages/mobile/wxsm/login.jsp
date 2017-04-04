<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<!DOCTYPE html>
  <head>
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <meta http-equiv="pragma" content="no-cache"> 
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="expires" content="0">
    <title> 腾蛟起风登录</title>
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wxsm/css/m.reset.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wxsm/css/weui.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wxsm/css/validate.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wxsm/css/login.css" />
</head>
<body>
<section>
  <div class="g-container" id="g-container">
    <div class="person_reg m-reg">
        <form action="" id="myform_login"  >
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class="weui_cell_hd"><img src="/resources/mobile/wxsm/images/com_tax.png" alt="" style="width:26px;margin-right:5px;display:block"></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="text" id="username"  placeholder="请输入企业税号" name="username" data-required="username" data-descriptions="username" maxlength="35">
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><img src="/resources/mobile/wxsm/images/pwd.png" alt="" style="width:26px;margin-right:5px;display:block"></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="password" id="password" placeholder="请输入密码" name="password" data-required="true" data-descriptions="password" maxlength="35">
                </div>
            </div>
        </div>
        <div class="weui_btn_area e-mt20">
            <input type="submit" id="submit" class='weui_btn weui_btn_primary weui_btn_disabled' value="登录" disabled/>
        </div>
        <p class='dsf'><a href="reg.jsp">立即注册</a>
            <!-- ï½<a href="user/pwd/pwdView.action">忘记密码</a> --></p>
        </form>
    </div>
  </div>
</section>
</body>
<script type="text/javascript" src="/resources/mobile/wxsm/js/zepto.min.js"></script>
<script type="text/javascript" src="/resources/mobile/wxsm/js/zepto.fx.js"></script>
<script type="text/javascript" src="/resources/mobile/wxsm/js/zepto-mvalidate.js"></script>
<script type="text/javascript" src="/resources/mobile/wxsm/js/zepto.cookie.js"></script>
<script type="text/javascript" src="/resources/mobile/wxsm/js/common.js"></script>
<script type="text/javascript" src="/resources/mobile/wxsm/js/login.js"></script>
</html>
