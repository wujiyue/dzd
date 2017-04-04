<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
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
    <title>企业名片列表</title>
   <!--  <link rel="stylesheet" type="text/css" href="css/m.reset.css"> -->
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/weui.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/validate.css">
    <link rel="stylesheet" type="text/css" href="/resources/mobile/wx/css/login.css" />
    <style type="text/css">
    .weui_label{  
    display: block;
    width: 105px;
    word-wrap: break-word;
    word-break: break-all;
    width:100px;text-align:right;
    font-size:14px;
    }
    .rwmimg{width:100%;height:240px;position:relative;left:25%;}
    </style>
</head>
<body>
<section>
<div><img src=""></div>
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
  <!-- 企业列表start -->
  <div class="weui_panel">
        <div class="weui_panel_hd" style="font-size:20px;color: #9C0; background-color: #F0F0F0">
        <img src="/resources/mobile/wx/images/1_05.png" style="">
        <font style="color: black;">企业名片</font>
        	<a href="javascript:;" onclick="toAdd()" style="float:right;position:relative;top:-5px;" class=""><img src="/resources/mobile/wx/images/xz.png" ></a>
        </div>
        
        <div style="height: 30px;"></div>
        
        <div class="weui_panel_bd">
            <div class="weui_media_box weui_media_small_appmsg">
                <div  id="qylist" class="weui_cells weui_cells_access">
                </div>
            </div>
        </div>
    </div>
    <!-- 企业列表end -->	
  
  		
  
        
  </div>
</section>
</body>
<script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/js/common/broutil.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/common.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/rwm.js"></script>

</html>
