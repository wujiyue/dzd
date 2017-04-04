<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ include file="checklogin.jsp"%>
<%
	String fpdm=request.getParameter("fpdm")==null?"":request.getParameter("fpdm");
	String kprq_q=request.getParameter("kprq_q")==null?"":request.getParameter("kprq_q");
	String kprq_z=request.getParameter("kprq_z")==null?"":request.getParameter("kprq_z");

%>
<script type="text/javascript">
var fpdm='<%=fpdm%>';
var kprq_q='<%=kprq_q%>';
var kprq_z='<%=kprq_z%>';
</script>
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
    <title>发票列表</title>
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
    .weui_grid:after {
    content: " ";
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
    height: 1px;
    border-bottom: 1px solid #D9D9D9;
    color: #D9D9D9;
    -webkit-transform-origin: 0 100%;
    transform-origin: 0 100%;
    -webkit-transform: scaleY(0.5);
    transform: scaleY(0.5);
}
.weui_grid:nth-child(3n):before {
    border-right-width: 1; 
}
.weui_grid {
    position: relative;
    float: left;
        padding: 5px 5px;
    width: 25%;
    box-sizing: border-box;
    text-align:center;
}
.grid_title{text-align:center;font-size:14px;}
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
  <!-- 发票列表start -->
 <!--  <div class="weui_panel">
        <div class="weui_panel_hd" style="font-size:20px;color: #9C0;"> -->
        <div class="hd" style="clear:both;padding:5px;">
       
        <a href="javascript:;" onclick="toIndex()" style="float:left;" class="weui_btn weui_btn_mini weui_btn_plain_primary">返回</a>
        </div>
        <!-- <div class="weui_panel_bd">
            <div class="weui_media_box weui_media_small_appmsg">
                <div  id="fplist" class="weui_cells weui_cells_access">
                
                </div>
            </div> -->
            <div class="bd" style="clear:both; margin-top:27px;">
            <div id="fplist" class="weui_grids" style="margin:5px;font-size:12px;">
            	
            </div>
        </div>
    </div>
    <!-- 发票列表end -->	
  
  		
  
        
  </div>
</section>
</body>
<script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/common/broutil.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/fplist.js"></script>
</html>
