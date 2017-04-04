<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="checklogin.jsp"%>
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
   <!--  <link rel="stylesheet" type="text/css" href="css/m.reset.css"> -->
    <link rel="stylesheet" type="text/css" href="css/weui.css">
    <link rel="stylesheet" type="text/css" href="css/validate.css">
    <link rel="stylesheet" type="text/css" href="css/login.css" />
    <style type="text/css">
    .weui_label{  
    display: block;
    width: 105px;
    word-wrap: break-word;
    word-break: break-all;
    width:100px;text-align:right;
    font-size:14px;
    }
    .rwmimg{width:240px;height:240px;position:relative;left:25%;}
    </style>
</head>
<body>
<section>
  <div class="g-container" id="g-container">
  <!-- 顶部的红色警告条 -->
  <div class="weui_toptips weui_warn js_tooltips" style="display: none;">格式不对</div>
  	<div id="toast" style="display: none;">
	    <div class="weui_mask_transparent"></div>
	    <div class="weui_toast">
	        <i class="weui_icon_toast"></i>
	        <p class="weui_toast_content">已完成</p>
	    </div>
	</div>
 <!-- 企业信息详情 -->
						  <div class="weui_panel weui_panel_access" >
						  <div class="weui_panel_hd" style="font-size:20px;color: #9C0;">新增企业名片
						  
						  <a href="javascript:;" onclick="tolist()" style="float:right;" class="weui_btn weui_btn_mini weui_btn_plain_primary">返回</a>
        
						  </div>
						    <div class="weui_panel_bd">
						      <div id="codebox" class="weui_media_box weui_media_text" style="display:none;">
						        <h5 class="weui_media_title">
						          <font color='green'>您的企业简码是：</font>
						          <span id="qyjm" style="color:red;"></span></h5>
						        <p class="weui_media_desc">
						          <div id="code" class="rwmimg"></div>
						        </p>
						      </div>
						      <div class="weui_media_box weui_media_text">
						        <h4 class="weui_media_title">企业信息</h4>
						        <form action="" id="myform_reg">
						          <input type="hidden" id="id" name="id" />
						          <div class="weui_cells weui_cells_form">
						            <div class="weui_cell">
						              <div class="weui_cell_hd">
						                <label class="weui_label">纳税人识别号:</label></div>
						              <div class="weui_cell_bd weui_cell_primary">
						                <input class="weui_input" type="text" id="nsrsbh" name="nsrsbh" placeholder="请输入纳税人识别号"></div>
						            </div>
						            <div class="weui_cell">
						              <div class="weui_cell_hd">
						                <label class="weui_label"><font color='red'>*&nbsp;&nbsp;</font>纳税人名称:</label></div>
						              <div class="weui_cell_bd weui_cell_primary">
						                <input class="weui_input" type="text" id="nsrmc" name="nsrmc" placeholder="请输入纳税人名称"></div>
						            </div>
						            <div class="weui_cell">
						              <div class="weui_cell_hd">
						                <label class="weui_label">银行帐号:</label></div>
						              <div class="weui_cell_bd weui_cell_primary">
						                <input class="weui_input" type="text" id="yhzh" name="yhzh" placeholder="请输入银行帐号"></div>
						            </div>
						            <div class="weui_cell">
						              <div class="weui_cell_hd">
						                <label class="weui_label">地址:</label></div>
						              <div class="weui_cell_bd weui_cell_primary">
						                <input class="weui_input" type="text" id="dz" name="dz" placeholder="请输入地址"></div>
						            </div>
						            <!-- <div class="weui_cell">
						            <div class="weui_cell_hd"><img src="images/pwd.png" alt="" style="width:26px;margin-right:5px;display:block"></div>
						            <div class="weui_cell_bd weui_cell_primary">
						            <input class="weui_input" type="password" id="password2" placeholder="请再次输入密码" name="password2" data-required="true" data-descriptions="password" maxlength="35"></div>
						            </div> -->
						          </div>
						          <div class="weui_btn_area e-mt20">
						            <a class='weui_btn weui_btn_primary saveBtn' onclick="addRwm(this)">保存</a>
						            <a href="javascript:void(0);"  class="weui_btn weui_btn_warn deleteBtn" onclick="reset(this)">重置</a></div>
						        </form>
						      </div>
						    </div>
						  </div>
  		
  
        
  </div>
</section>
</body>
<script type="text/javascript" src="${ctx}/js/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/sysutil.js"></script>
    <script type="text/javascript" src="jquery.qrcode.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/addrwm.js"></script>
</html>
