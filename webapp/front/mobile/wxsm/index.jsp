<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="checklogin.jsp"%>
<% 
	String fromlist=request.getParameter("fromlist")==null?"":request.getParameter("fromlist");

%>
<script type="text/javascript">
		var fromlist='<%=fromlist%>';
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
    <title>二维码扫描</title>
   <!--  <link rel="stylesheet" type="text/css" href="css/m.reset.css"> -->
    <link rel="stylesheet" type="text/css" href="css/weui.css">
    <link rel="stylesheet" type="text/css" href="css/validate.css">
    <link rel="stylesheet" type="text/css" href="css/login.css" />
    
       <script type="text/javascript"
	src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
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
    .cl:after,.clearfix:after{content:"\20";display:block;height:0;clear:both;visibility:hidden}.cl,.clearfix{zoom:1}
    
    a.weui_tabbar_item.weui_bar_item_on{ display:inline-block;   background-color: #EAEAEA;}
    
    .ms-mt{position:relative;bottom:0px;}
    </style>
</head>
<body>
<section>
  <div class="g-container" id="g-container">
  	<!-- 顶部的红色警告条 -->
  	<div class="weui_toptips weui_warn js_tooltips" style="display: none;">格式不对</div>
  	<!-- toast提示框 -->
  	<div id="toast" style="display: none;">
	    <div class="weui_mask_transparent"></div>
	    <div class="weui_toast">
	        <i class="weui_icon_toast"></i>
	        <p class="weui_toast_content">已完成</p>
	    </div>
 	</div>
	
	
	
<div class="weui_tab">
		        
	<div class="weui_tab_bd">
				<div class="main-content cl">
				
					<!-- <div class="qyxx">
					  <a class="weui_cell" onclick="openQyxx(this)" href="javascript:void(0);">
					    <div class="weui_cell_bd weui_cell_primary">
					      <span style="font-weight:bold;">腾蛟起风软件技术有限公司</span></div>
					    <span class="weui_cell_ft"></span>
					  </a>
					  企业信息详情
					  <div class="weui_panel weui_panel_access" style="display: none;">
					    <div class="weui_panel_bd">
					      <div class="weui_media_box weui_media_text">
					        <h5 class="weui_media_title">
					          <font color="green">您的企业简码是：</font>
					          <font color="red">0yvynl</font></h5>
					        <p class="weui_media_desc"></p>
					        <div id="code_0yvynl" class="rwmimg">
					          <canvas width="240" height="240"></canvas>
					        </div>
					        <p>
					        </p>
					      </div>
					      <div class="weui_media_box weui_media_text">
					        <h4 class="weui_media_title">企业信息</h4>
					        <form action="" id="myform_0">
					          <input type="hidden" id="id_0" name="id_0" value="0yvynl">
					          <div class="weui_cells weui_cells_form">
					            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
					              <div class="weui_cell_hd">
					                <label class="weui_label">纳税人识别号:</label></div>
					              <div class="weui_cell_bd weui_cell_primary">
					                <input class="weui_input" type="text" id="nsrsbh_0" name="nsrsbh_0" value="370112054863768" placeholder="请输入纳税人识别号"></div>
					            </div>
					            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
					              <div class="weui_cell_hd">
					                <label class="weui_label">
					                  <font color="red">*&nbsp;&nbsp;</font>纳税人名称:</label></div>
					              <div class="weui_cell_bd weui_cell_primary">
					                <input class="weui_input" type="text" id="nsrmc_0" name="nsrmc_0" value="腾蛟起风软件技术有限公司" placeholder="请输入纳税人名称"></div>
					            </div>
					            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
					              <div class="weui_cell_hd">
					                <label class="weui_label">银行帐号:</label></div>
					              <div class="weui_cell_bd weui_cell_primary">
					                <input class="weui_input" type="text" id="yhzh_0" name="yhzh_0" value="中国银行" placeholder="请输入银行帐号"></div>
					            </div>
					            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
					              <div class="weui_cell_hd">
					                <label class="weui_label">地址:</label></div>
					              <div class="weui_cell_bd weui_cell_primary">
					                <input class="weui_input" type="text" id="dz_0" name="dz_0" value="济南高新区123" placeholder="请输入地址"></div>
					            </div>
					          </div>
					          <div class="weui_btn_area e-mt20">
					            <a class="weui_btn weui_btn_primary saveBtn" onclick="saveRwm(this)">保存</a>
					            <a class="weui_btn weui_btn_warn deleteBtn" onclick="delRwm(this)">删除</a></div>
					        </form>
					      </div>
					    </div>
					  </div>
					</div>		 -->			
					
					
	<div class="qyxx">
	
	    <div class="weui_panel_bd">
	      
	      <div class="weui_media_box weui_media_text">
	        
	        <form action="" id="myform_0">
	         <!--  <input type="hidden" id="id_0" name="id_0" value="0yvynl"> -->
	          <div class=" weui_cells_form">
	           <h4 style="text-align:center;color:#9C0;font-size:20px;">发票扫描</h4>
	          <h4 class="weui_media_title">企业信息</h4>
	          <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
			                <div class="weui_cell_hd"><label class="weui_label">企业税号:</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <input class="weui_input " readonly type="text" id="username" style="background:#EAE9E9"    name="username" maxlength="35">
			                </div>
			            </div>
			            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
			                <div class="weui_cell_hd"><label class="weui_label">分机号:</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <input class="weui_input" type="text" id="fjh" readonly  style="background:#EAE9E9"   name="fjh" data-required="fjh" data-descriptions="fjh" >
			                </div>
			            </div>
			            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
			                <div class="weui_cell_hd"><label class="weui_label">企业名称:</label></div>
			                <div class="weui_cell_bd weui_cell_primary">
			                    <input class="weui_input" type="text" id="qymc" readonly  style="background:#EAE9E9"   name="qymc" data-required="username" data-descriptions="username" maxlength="35">
			                </div>
			            </div>
			            
	          <h4 class="weui_media_title">发票信息</h4>
	            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
	              <div class="weui_cell_hd">
	                <label class="weui_label">发票代码:</label></div>
	              <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="fpdm" readonly name="fpdm" value="" placeholder="等待扫描"></div>
	            </div>
	            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
	              <div class="weui_cell_hd">
	                <label class="weui_label">
	                 	发票号码:</label></div>
	              <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="fphm" readonly name="fphm" value="" placeholder="等待扫描"></div>
	            </div>
	            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
	              <div class="weui_cell_hd">
	                <label class="weui_label">金&nbsp;&nbsp;&nbsp;&nbsp;额:</label></div>
	              <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="je" readonly name="je" value=""  placeholder="等待扫描"></div>
	            </div>
	            <div class="weui_cell" style="padding: 10px 0px;font-size:13px;">
	              <div class="weui_cell_hd">
	                <label class="weui_label">开票日期:</label></div>
	              <div class="weui_cell_bd weui_cell_primary">
	                <input class="weui_input" type="text" id="kpsj" readonly name="kpsj" value="" placeholder="等待扫描" ></div>
	            </div>
	          </div>
	        </form>
	      </div>
	    </div>
	</div>
	
					<div class="" style="margin:5px 15px;">
						 <input type="button" id="scan" class='weui_btn weui_btn_primary' value="扫描二维码" onclick="scan()"/>
					</div>
				</div>
				
				<!-- 发票列表DIV -->
				<div class="main-content cl" style="display: none;">
				
					<div class=" weui_cells_form">
						
	           			<h4 style="text-align:center;color:#9C0;margin:15px 0;font-size:20px;">发票查询</h4>
				        <div class="weui_cell">
				            <div class="weui_cell_hd"><label class="weui_label">发票代码:</label></div>
				            <div class="weui_cell_bd weui_cell_primary">
				                <input class="weui_input" type="number" pattern="[0-9]*" id="cx_fpdm" placeholder="请输入发票代码">
				            </div>
				        </div>
				        <div class="weui_cell">
				            <div class="weui_cell_hd"><label for="" class="weui_label">开票日期起:</label></div>
				            <div class="weui_cell_bd weui_cell_primary">
				                <input class="weui_input" type="date" id="kprq_q" placeholder="请选择开票日期起" value="">
				            </div>
				        </div>
				        <div class="weui_cell">
				            <div class="weui_cell_hd"><label for="" class="weui_label">开票日期止:</label></div>
				            <div class="weui_cell_bd weui_cell_primary">
				                <input class="weui_input" type="date"  id="kprq_z" placeholder="请选择开票日期止"  value="">
				            </div>
				        </div>
			        
		    		</div>
		    		
		    		
					<div class="weui_btn_area">
				        <a class="weui_btn weui_btn_primary" href="javascript:" id="showTooltips" onclick="query()">查询</a>
				        <a href="javascript:void(0);"  class="weui_btn weui_btn_warn " onclick="reset()">重置</a>
				    </div>
				   <!--  <div class="weui_btn_area" style="margin-bottom:15px;">
				    	<a href="login.jsp"  class="weui_btn weui_btn_warn " >切换帐号</a>
				    </div> -->
				</div>
	</div>

	
   <div class="weui_tabbar">
        <a href="javascript:;" class="weui_tabbar_item weui_bar_item_on">
            <div class="weui_tabbar_icon">
                <img src="./images/icon_nav_button.png" alt="">
            </div>
            <p class="weui_tabbar_label">发票扫描</p>
        </a>
       
        <a href="javascript:;" id="fpcx_tab" class="weui_tabbar_item">
            <div class="weui_tabbar_icon">
                <img src="./images/icon_nav_article.png" alt="">
            </div>
            <p class="weui_tabbar_label">发票查询</p>
        </a>
       
    </div>     
  </div>
</section>
</body>
<script type="text/javascript" src="${ctx}/js/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/sysutil.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
function config(){
	//alert("当前域名不是80端口会导致调用扫描二维码功能无法正常使用！");
	var targetUrl=encodeURIComponent(location.href.split("#")[0]);
	$.ajax({
        async: false,
		type : "post",
		url : "http://www.sdtjqf.com.cn/wxserver/servlet/wxsm?targetUrl="+targetUrl+"&username="+username,
		data : '',
		dataType : "json",
		success : function(json) {
			//alert(JSON.stringify(json));
			var obj = eval(json);
			console.log(obj.signature);
			console.log(obj.nonceStr);
			wx.config({
				debug : false,
				appId : 'wx1cffd3f02e9c5a9f',
				timestamp : obj.timestamp,
				nonceStr : obj.nonceStr,
				signature : obj.signature,
				jsApiList : [ 'checkJsApi', 'onMenuShareTimeline',
						'onMenuShareAppMessage', 'onMenuShareQQ',
						'onMenuShareWeibo', 'onMenuShareQZone',
						'hideMenuItems', 'showMenuItems',
						'hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem',
						'translateVoice', 'startRecord', 'stopRecord',
						'onVoiceRecordEnd', 'playVoice', 'onVoicePlayEnd',
						'pauseVoice', 'stopVoice', 'uploadVoice',
						'downloadVoice', 'chooseImage', 'previewImage',
						'uploadImage', 'downloadImage', 'getNetworkType',
						'openLocation', 'getLocation', 'hideOptionMenu',
						'showOptionMenu', 'closeWindow', 'scanQRCode',
						'chooseWXPay', 'openProductSpecificView',
						'addCard', 'chooseCard', 'openCard' ]
			});
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert(errorThrown);
		}
	}); 
}
function reset(){
	$("#cx_fpdm").val('');
	
	$("#kprq_q").val('');
	$("#kprq_z").val('');
}
function scan(){
	
	setTimeout("smRwm()",1000);
}
//扫描二维码
function smRwm(){
	//$("#scan").addClass('weui_btn_disabled').addAttr('disabled');
	config();
	wx.ready(function() {
		
		 wx.scanQRCode({
			needResult : 1,
			desc : 'scanQRCode desc',
			success : function(res) {
				
				//alert(JSON.stringify(res));
				var data=res.resultStr;
				var arr=data.split(",");
				var fpdm=arr[2];
				var fphm=arr[3];
				var je=arr[4];
				var kpsj=arr[5];
				$("#fpdm").val(fpdm);
				$("#fphm").val(fphm);
				$("#je").val(je);
				$("#kpsj").val(kpsj);
				if(fpdm==''||fphm==''||je==''||kpsj==''||fpdm=='undefined'||fphm=='undefined'||je=='undefined'||kpsj=='undefined'||fpdm==undefined||fphm==undefined||je==undefined||kpsj==undefined){
					alert('这不是有效的发票二维码');
				}else{
					var params="&username="+username+"&fpdm="+fpdm+"&fphm="+fphm+"&je="+je+"&kpsj="+kpsj+"&fjh="+fjh+"&lrr="+qymc;
					$('#scan').val('处理中。。。');
					sys_ajaxPost("/wx/default.do?method=saverwmsm"+params, null, function (json) {
						if(json.result==true||json.result=='true'){
							common_showToastNew("扫描成功！");
						}else{
							common_showWarningLong(json.msg);
						}
						//$('#scan').removeClass('weui_btn_disabled').removeAttr('disabled');
						$('#scan').val('继续扫描');
					}); 
				
				}
				
				 
			}
		}); 
	});
	wx.error(function(res) {
		common_showWarning("Error:"+res.errMsg);
		 $('#scan').removeClass('weui_btn_disabled').removeAttr('disabled');
	});
}
//查询
function  query(){
	var fpdm=$("#cx_fpdm").val();
	var kprq_q=$("#kprq_q").val();
	var kprq_z=$("#kprq_z").val();
	window.location.href=sys_ctx+"/zcpt/wxsm/list.jsp?fpdm="+fpdm+"&kprq_q="+kprq_q+"&fjh="+fjh+"&kprq_z="+kprq_z;
}
$(function(){
	$("#username").val(username);
	$("#qymc").val(qymc);
	$("#fjh").val(fjh);
	var $content = $('.weui_tab_bd .main-content');
	$('.g-container').on('click', '.weui_tabbar_item', function (e) {
	    $(this).addClass('weui_bar_item_on').siblings('.weui_bar_item_on').removeClass('weui_bar_item_on');
	  
	    var index = $(this).index();
	    //$(sub[index]).addClass('weui_btn_disabled');
	    var $div=$content.eq(index);
	    
	    $div.show().siblings().hide();
	    e.preventDefault();
	});
	if(fromlist=='1'||fromlist==1){
		$("#fpcx_tab").click();
	}
});
</script>
</html>
