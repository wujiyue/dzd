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
    <title>便捷开票</title>
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/m.reset.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/weui.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/validate.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/login.css" />
</head>
<body>
<center>
<section>
<div style="center; no-repeat"><img src="/resources/mobile/wx/images/1_02.png" style="center; no-repeat; width: 100%;"></div>
  <div class="g-container" id="g-container">
    <div class="person_reg m-reg">
        <form action="" id="myform_login"  >
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class=""><img src="/resources/mobile/wx/images/1_05.png" alt="">&nbsp;&nbsp;<label>用户名:</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="text" id="username"  placeholder="请输入用户名" name="username" data-required="username" data-descriptions="username" maxlength="35">
                </div>
            </div> 
            <div class="weui_cell">
                <div class=""><img src="/resources/mobile/wx/images/1_05.png" alt="" >&nbsp;&nbsp;<label>密&nbsp;&nbsp;&nbsp;&nbsp;码:</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="password" id="password" placeholder="请输入密码" name="password" data-required="true" data-descriptions="password" maxlength="35">
                </div>
            </div>
        </div>
        <div style="height: 20px;"></div>
        <div class="">
        <img src="/resources/mobile/wx/images/dl.png" id="dl" style="width: 90%; height: 40px;" >
<!--            <input id="dl" style="background:url('images/dl.png');width: 70%; height: 50px;"  type="submit" id="submit" value="" /> -->
        </div>
        <p class='dsf'><a href="reg.jsp"><img id="zc" src="/resources/mobile/wx/images/zc.png" style="width: 90%; height: 40px;"></a><!-- ï½<a href="user/pwd/pwdView.action">忘记密码</a> --></p>
        </form>
    </div>
  </div>
</section>
</center>
</body>
<script type="text/javascript" src="/resources/mobile/wx/js/zepto.min.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/zepto.fx.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/zepto-mvalidate.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/zepto.cookie.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/common.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/login.js"></script>
<script type="text/javascript" >
	$(function(){
		$("#dl").click(function(){
			$("#myform_login").submit();
		});
	})

</script>
</html>
