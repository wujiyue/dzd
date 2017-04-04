<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
  <head>
  	
    <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <meta http-equiv="pragma" content="no-cache"> 
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="expires" content="0">
    <title> 腾蛟起风注册</title>
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/m.reset.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/weui.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/validate.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/login.css" />
</head>
<body>
<center>
<section>
<div> <img src="images/yhzc.png"></div>
<div style="height: 20px;"></div> 
  <div class="g-container" id="g-container">
    <div class="person_reg m-reg">
        <form action="" id="myform_reg"  >
        <div class="weui_cells weui_cells_form">
            <div class="weui_cell">
                <div class="weui_cell_hd"><img src="images/1_05.png" alt="" >&nbsp;&nbsp;<label>用&nbsp;户&nbsp;&nbsp;名:</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                
                    <input class="weui_input" type="text" id="username"  placeholder="请输入用户名" name="username" data-required="username" data-descriptions="username" maxlength="35">
                </div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><img src="images/1_05.png" alt="">&nbsp;&nbsp;<label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="password" id="password" placeholder="请输入密码" name="password" data-required="true" data-descriptions="password" maxlength="35">
                </div>
            </div>
             <div class="weui_cell">
                <div class="weui_cell_hd"><img src="images/1_05.png" alt="" >&nbsp;&nbsp;<label>确认密码</label></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="password" id="password2" placeholder="请再次输入密码" name="password2" data-required="true" data-descriptions="password" maxlength="35">
                </div>
            </div>
        </div>
        <div style="height: 20px;"></div>
        <div class="" > 
        <img src="images/bc.png" style="width: 90%;height: 40px;" id="bc">
<!--           <font style="color: white;"> <input type="submit" id="submit" style="background:url('images/bc.png'); width: 50%; height: 50px; center;"value="" /></font> -->
        </div> 
<!--         <p class='dsf'><a href="login.jsp">立即登录</a>ï½<a href="user/pwd/pwdView.action">忘记密码</a></p> -->
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
<script type="text/javascript" src="/resources/mobile/wx/js/reg.js"></script>
<script type="text/javascript" >
	$(function(){
		$("#bc").click(function(){
			$("#myform_reg").submit();
		});
	})

</script>
</html>
