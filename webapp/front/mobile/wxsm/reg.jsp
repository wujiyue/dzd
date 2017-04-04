<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
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
    <link rel="stylesheet" type="text/css" href="css/m.reset.css">
    <link rel="stylesheet" type="text/css" href="css/weui.min.css">
    <link rel="stylesheet" type="text/css" href="css/validate.css">
    <link rel="stylesheet" type="text/css" href="css/login.css" />
</head>
<body>
<section>
  <div class="g-container" id="g-container">
    <div class="person_reg m-reg">
        <form action="" id="myform_reg"  >
        <div class="weui_cells weui_cells_form">
        	<h4 style="text-align:center;color:#9C0;font-size:20px;">用户注册</h4>
            <div class="weui_cell">
                <div class="weui_cell_hd"><img src="images/com_tax.png" alt="" style="width:26px;margin-right:5px;display:block"></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="text" id="username"  placeholder="请输入企业税号作为用户名" name="username" data-required="username" data-descriptions="username" maxlength="35">
                </div>
            </div>
          
            <div class="weui_cell">
                            <div class="weui_cell_hd"><img src="images/com_name.png" alt="" style="width:26px;margin-right:5px;display:block"></div>
                            <div class="weui_cell_bd weui_cell_primary">
                                <input class="weui_input" type="text" placeholder="请输入企业名称" id="qymc" name="qymc"  maxlength="100">
                            </div>
                            <div class="weui_cell_ft"></div>
            </div>
            <div class="weui_cell">
                <div class="weui_cell_hd"><img src="images/pwd.png" alt="" style="width:26px;margin-right:5px;display:block"></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="password" id="password" placeholder="请输入密码" name="password" data-required="true" data-descriptions="password" maxlength="35">
                </div>
            </div>
             <div class="weui_cell">
                <div class="weui_cell_hd"><img src="images/pwd.png" alt="" style="width:26px;margin-right:5px;display:block"></div>
                <div class="weui_cell_bd weui_cell_primary">
                    <input class="weui_input" type="password" id="password2" placeholder="请再次输入密码" name="password2" data-required="true" data-descriptions="password" maxlength="35">
                </div>
            </div>
            
        </div>
        <div class="weui_btn_area e-mt20">
            <input type="submit" id="submit" class='weui_btn weui_btn_primary weui_btn_disabled' value="注册" disabled/>
        </div>
        <p class='dsf'><!-- <a href="login.jsp">立即登录</a> -->
        
        		<div style="padding: 10px 0;width: 60%;margin: 0 auto;text-align: center;">
        			<a href="login.jsp" class="weui_btn weui_btn_mini weui_btn_default">立即登录</a>
        		</div>
        </p>
        </form>
    </div>
  </div>
</section>
</body>
<script type="text/javascript" src="js/zepto.min.js"></script>
<script type="text/javascript" src="js/zepto.fx.js"></script>
<script type="text/javascript" src="js/zepto-mvalidate.js"></script>
<script type="text/javascript" src="js/zepto.cookie.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/reg.js"></script>
</html>
