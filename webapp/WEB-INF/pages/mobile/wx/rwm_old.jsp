<%@ page language="java" import="java.util.*,sdcncsi.ict.zcpt.login.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ include file="checklogin.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
	content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0" />
	<meta name="MobileOptimized" content="200" />
    <title>企业名片</title>
    <link  href="${ctx}/css/front.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/js/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/sysutil.js"></script>
    <script type="text/javascript" src="jquery.qrcode.min.js"></script>
   <style type="text/css">
   		@charset "utf-8";
		*{margin:0;padding:0}
		/*全局控制*/
		body{margin:0;padding:0;font-size:12px;line-height:22px;background:#f4f4f4;font-family:"微软雅黑",Arial,"宋体";-webkit-text-size-adjust:none;}
		html,body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td,p{margin:0;padding:0;}
		input,select,textarea{font-size:12px;vertical-align:middle;}img{border:0;}ul,li{list-style-type:none;}
		a{color:#000;text-decoration:none;}
		a:hover{text-decoration:underline;}
		.tc{text-align:center;}.tl{text-align:left;}.tr{text-align:right;}
		.dis{display:block;}.undis{display:none;}
		.fl{float:left;}.fr{float:right;}.cl{clear:both;}.fb{font-weight:bold;}.fnb{font-weight:200;margin-left:-1px;}
		.hr_1,.hr_10{font-size:1px;line-height:1px;clear:both;}
		.hr_1{height:1px;}.hr_10{height:10px;}
		
		
		/*头部样式*/
		.header{height:90px;}
		.header  .logo{width:350px;height:63px;margin-top:22px;margin-left:10px;background:url('images/logo_login2.png') no-repeat;float:left;}
		.header  .logo a{display:block;width:100%;height:100%;}
		.header  .retxt{float:right;margin:25px 10px 0 0;}
		
		
		/*登录区域*/
		.mainbody{width:100%;margin:32px auto;padding:10px 10px 20px;background:#fff;border:1px solid #e0e0e0;border-radius:8px;}
		.mainbody input[type="text"]:focus,
		.mainbody input[type="password"]:focus,
		.mainbody textarea:focus,
		.mainbody select:focus{box-shadow:0 0 0;outline:none;}
		.mainbody #validate{text-transform:uppercase;}
		.mainbody .top{margin-bottom:8px;padding-bottom:12px;border-bottom:1px dashed #dadada;}
		.mainbody .top h2{font-size:20px;color:#9C0;}
		.mainbody .txt{margin-top:8px;color:#999;}
		.mainbody .note{color:#999;}
		.mainbody .input,
		.mainbody .inputon{width:222px;height:35px;margin-right:10px;padding:0 10px;line-height:30px;border:1px solid #ccc;font-size:14px;font-family:Verdana;}
		.mainbody .input{background-position:0 0;}
		.mainbody .inputon{background-position:0 35px;}
		.mainbody .sub, .mainbody .subon, .mainbody .subdown{width:119px;height:37px;background-image:url(images/userlog_btn_bg.png);border:0;color:#fff;font-size:14px;font-weight:bold;font-family:"微软雅黑";cursor:pointer;}
		.mainbody .sub{background-position:0 0;}
		.mainbody .subon{background-position:0 74px;}
		.mainbody .subdown{background-position:0 37px;}
		.mainbody .regbtn{position:relative;left:10px;width:320px;height:240px;text-align:center;font-weight:bold;font-size:14px;}
		.mainbody .regbtn a{text-decoration:underline;color:#900;}
		.mainbody .infor{margin-bottom:10px;margin-top:15px;position:relative;left:83px;color:red;border:1px solid #ccc;width:227px;padding:2px 9px;background:#fffff0;border-radius:3px;}
		.mainbody .findpwd{margin-left:20px;font-size:14px;}
		.mainbody .oqq,.mainbody .osina{height:24px;line-height:24px;display:inline-block;padding-left:29px;margin-right:15px;}
		.mainbody .oqq{background:url(../images/oauthico.png) no-repeat 0 0;}
		.mainbody .osina{background:url(../images/oauthico.png) no-repeat 0 -32px;}
		
		
		/*左侧区域*/
		.mainbody .leftarea{width:220px;float:left;border-right:1px solid #f0f0f0;}
		.mainbody .leftarea .userinfo{height:70px;margin-bottom:30px;_margin-bottom:15px;word-wrap:break-word;}
		.mainbody .leftarea .avater{height:80px;float:left;margin-right:12px;_margin-right:6px;position:relative;overflow:hidden;}
		.mainbody .leftarea .avater div{position:absolute;width:80px;height:18px;left:0;bottom:0;background:#000;filter:alpha(opacity=65);-moz-opacity:0.65;opacity:0.65;-webkit-border-radius:0 0 5px 5px;-moz-border-radius:0 0 5px 5px;border-radius:0 0 5px 5px;z-index:998;}
		.mainbody .leftarea .avater a{display:block;width:80px;position:absolute;z-index:999;left:0;bottom:0;line-height:18px;text-align:center;color:#fff;}
		.mainbody .leftarea .avater img{width:80px;height:80px;-webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;}
		.mainbody .leftarea .usertxt{float:left;}
		.mainbody .leftarea .username,.mainbody .leftarea .userenter{display:block;font-size:14px;font-weight:bold;color:#8D530A;}
		/* .mainbody .leftarea .userenter{background:url(../images/icon_v.png) 0 3px no-repeat;padding-left:20px;} */
		.mainbody .leftarea .usergroup{display:block;color:red;}
		.mainbody .leftarea .act a{width:180px;border-bottom:5px solid #fff;padding-left:10px;display:block;background:#f6f6f6;height:30px;line-height:30px;font-size:14px;color:#000;font-family:"宋体"}
		.mainbody .leftarea .act a:hover{background:#ccc;color:#fff;text-decoration:none;}
		.mainbody .leftarea .act a.on{background:#999;color:#fff;}
		
		
		/*右侧区域*/
		.mainbody .rightarea{width:609px;min-height:300px;padding:0 0 0 30px;float:right;}
		.mainbody .rightarea h3.dftitle,
		.mainbody .rightarea h3.subtitle{padding-bottom:8px;margin-bottom:8px;border-bottom:1px solid #ddd;font-weight:bold;font-size:14px;}
		.mainbody .rightarea h3.subtitle a{font-size:12px;font-weight:normal;float:right;}
		.mainbody .rightarea .loginfo{font-family:Verdana;font-size:14px;color:#900;}
		.mainbody .rightarea .list li{border-bottom:1px solid #f5f5f5;line-height:30px;}
		.mainbody .rightarea .list li a{text-decoration:none;}
		.mainbody .rightarea .list li a:hover{color:#f90;}
		.mainbody .rightarea .list span.time{float:right;font-family:Verdana;font-size:11px;color:#666;}
		.mainbody .rightarea .list span.dot{font-family:"宋体";}
		.mainbody .rightarea .msglist li{padding:10px 0;border-bottom:1px dashed #ccc;word-wrap:break-word;}
		.mainbody .rightarea .msglist p{display:block;line-height:20px;margin-bottom:5px;}
		.mainbody .rightarea .msglist span.time{font-family:Verdana;color:#999;color:#999;float:right;}
		.mainbody .rightarea .msglist span.from{float:left;color:#999;}
		.mainbody .rightarea .msglist span.from a{color:#999;}
		.mainbody .rightarea .nonelist{padding-top:5px;text-align:center;line-height:30px;}
		.mainbody .rightarea .more{margin:8px 0 20px 0;text-align:right;}
		.mainbody .rightarea .more a{color:#999;}
		
		.mainbody .rightarea .class_input,
		.mainbody .rightarea .class_input_on{width:320px;height:22px;line-height:22px;padding:2px 4px 0;}
		.mainbody .rightarea .class_input{background:#f3f9fc;border:1px solid #c0d0d8;}
		.mainbody .rightarea .class_input_on{background:#fff;border:1px solid #569dde;}
		.mainbody .rightarea .class_areatext,
		.mainbody .rightarea .class_areatext_on{width:320px;height:100px;line-height:18px;padding:4px;}
		.mainbody .rightarea .class_areatext{background:#f3f9fc;border:1px solid #c0d0d8;}
		.mainbody .rightarea .class_areatext_on{background:#fff;border:1px solid #569dde;}
		.mainbody .rightarea .btn_area{text-align:center;margin-top:25px;}
		/* .mainbody .rightarea .btn{width:78px;height:25px;background:url(../images/btn-style-gray.gif);margin-right:15px;font-size:14px;cursor:pointer;border:none;} */
		
		.mainbody .rightarea .upavatar{padding-top:10px;text-align:center;}
		.mainbody .rightarea .preavatar{padding:10px 0;text-align:center;}
		.mainbody .rightarea .preavatar img{margin:0 5px;}
		
		.mainbody .rightarea .options_b{padding-top:8px;line-height:22px;color:#666;}
		.mainbody .rightarea .options_b a{color:#005590;}
		
		
		/*分页样式*/
		.page_info{text-align:center;margin-top:10px;color:#333;}
		.page_info span{padding:0 2px;font-weight:bold;}
		.page_list{height:24px;line-height:24px;text-align:center;letter-spacing:0;font-family:"Arial Black";font-size:10px;margin-top:20px;}
		.page_list a{display:inline-block;color:#898989;border:1px solid #e8e8e8;background:#f8f8f8;text-align:center;padding:0 8px;margin:0 1px;}
		.page_list a:hover{color:#fff;border:1px solid #898989;background:#898989;}
		.page_list a.on{color:#333;border:1px solid #ccc;background:#E5EDF2;}
		
		
		/*我的订单*/
		.orderlist .thead{font-weight:bold;}
		.orderlist .title{margin-right:10px;}
		.orderlist .attr{margin-right:3px;color:#999;}
		.orderlist .action a{color:#900;}
		.orderlist .action a:hover{color:red;}
		.orderlist .status{color:blue;}
		.orderlist2 td{background:#f5f5f5;}
		
		
		.blue{color:blue;}
		.orderact{color:#666;font-weight:bold;}
		.orderact a{color:#900;font-weight:bold;}
		.orderact a:hover{color:red;}
		.total{margin-left:10px;}
		
		
		/*合作账号*/
		.mainbody .ounameqq,.mainbody .ounameweibo{width:80px;padding-left:20px;float:left;display:block;font-size:12px;font-weight:bold;color:#8D530A;background-image:url(../images/oauthico.png); background-repeat:no-repeat;}
		.mainbody .ounameqq{background-position:0 -61px;}
		.mainbody .ounameweibo{background-position:0 -88px;}
		.mainbody .pbArea{margin:12px 0;}
		.mainbody .pbArea a{width:180px;height:180px;display:inline-block;text-align:center;line-height:180px;color:#fff;font-weight:bold;font-size:14px;}
		.mainbody .pbArea a:hover{background:#f5f5f5;color:#333;text-decoration:none;}
		.mainbody .perfect{background:#398ae7;}
		.mainbody .binding{background:#7dc81e;margin-left:10px;}
		.mainbody .pbTips{color:#666;}
		.mainbody .pbTips li{line-height:22px;list-style-type:disc;list-style-position:inside;}
		
		.mainbody .removeoauth a{color:#09F;}
		
		.mainbody .oqqico,.mainbody .oweiboico{display:inline-block;width:16px;height:16px;margin:3px 5px 0 0;}
		.mainbody .oqqico{background:url(../images/oauthico.png) no-repeat 0 -64px;}
		.mainbody .oweiboico{background:url(../images/oauthico.png) no-repeat 0 -88px;}
   		.nowrap{white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
   		.red_btn,.green_btn {
		   
		   
		    color: #fff;
		    text-align: center;
		    border-radius: 2px;
		    display: inline-block;
		    text-decoration: none;
		    -webkit-transition: all .3s ease;
		    -moz-transition: all .3s ease;
		    -o-transition: all .3s ease;
		    transition: all .3s ease;
		}
		.red_btn{ background: #f45555; border: 1px solid #f45555;}
		.green_btn{ background: #4CAF50; border: 1px solid #4CAF50;}
		.red_btn:hover{
		    background: #e63832;
		    border: 1px solid #c42f2a;
		    color: #fff;
		    text-decoration: none;
		}
		.green_btn:hover {
		 	background: #2E8231;
		    border: 1px solid #2E8231;
		    color: #fff;
		    text-decoration: none;
		}
   </style>
     
     <script type="text/javascript">
   
     	function reset(){
     	
     		$('#infor').html("").hide();
     		$("#nsrsbh").val('');
     		$("#nsrmc").val('');
     		$("#yhzh").val('');
     		$("#dz").val('');
     	}
     	function getLast6(str){
     		var len=str.length;
     		if(len<=6){
     			var t="";
     			for(var i=0;i<6-len;i++){
     				t+="0";
     			}
     			str=t+str;
     		}else{
     			str=str.substring(len-6);
     			
     		}
     		return str;
     	}
     	//登录
     	function save(){
     		var nsrsbh=$("#nsrsbh").val().trim();
     		var nsrmc=$("#nsrmc").val().trim();
     		
     		var yhzh=$("#yhzh").val().trim();
     		var dz=$("#dz").val().trim();
     		nsrsbh=nsrsbh.replaceAll("$","").replaceAll(",","").replaceAll(";","");
     		nsrmc=nsrmc.replaceAll("$"," ").replaceAll(","," ").replaceAll(";"," ");
     		yhzh=yhzh.replaceAll("$","").replaceAll(",","").replaceAll(";","");
     		dz=dz.replaceAll("$"," ").replaceAll(","," ").replaceAll(";"," ");
     		if(nsrsbh==''){
     			//$('#infor').html("纳税人识别号不能为空！").css("display","block");
     			//return;
     		}else{
     			$('#infor').html("").css("display","none");
     			var reg=/^[A-Za-z\d]{1,30}$/;
     			if(!reg.test(nsrsbh)){
     			$('#infor').html("纳税人识别号不合法！").css("display","block");
     				return;
     			}
     		}
     		if(nsrmc==''){
     			$('#infor').html("纳税人名称不能为空！").css("display","block");
     			return false;
     			return;
     		}else{
     			$('#infor').html("").css("display","none");
     		}
     		nsrmc=toUtf8(nsrmc);
     		if(yhzh==''){
     			//$('#infor').html("银行帐号不能为空！").css("display","block");
     			//return;
     		}else{
     			$('#infor').html("").css("display","none");
     		}
     		yhzh=toUtf8(yhzh);
     		if(dz==''){
     			//$('#infor').html("地址不能为空！").css("display","block");
     			//return;
     		}else{
     			$('#infor').html("").css("display","none");
     		}
     		dz=toUtf8(dz);
     		var str=nsrsbh+','+nsrmc+','+yhzh+','+dz;
     		alert(str);
     		$('#code').html("");
     		$('#code').qrcode({width:240,height:240,text:str}); 
     		//var jm=getLast6(nsrsbh);
			var queryString=$("#form").serialize();
			//alert(queryString);
		
		sys_ajaxPost("/wx/default.do?method=queryrwm&username="+username+"&rnd="+new Date().getTime(), null, function (msg) {
					if(msg.result==true||msg.result=='true'){
						if(msg.insert_flag==true||msg.insert_flag=='true'){
    					
							sys_ajaxPost("/wx/default.do?method=saverwm&username="+username, queryString, function (json) {
								//alert(JSON.stringify(json));
								if(json.result==true||json.result=='true'){
									//var str=json.str
									//$('#code').qrcode({width:240,height:240,text:str}); 
									//alert("生成二维码成功！");
									$("#form_table").hide();
									$("#code").show();
									$("#infor").html("<font color='green'>您的名片简码是：</font><font style='font-weight:bold' color='red'>"+username+"</font>").css("left","48px").css("display","block");
									
								}else{
									$('#infor').html(json.msg).css("display","block");
								}
							});
							
    					}else{
    						//更新
    						//$("#form_table").hide();
    						//$("#code").show();
    						//$("#infor").html("<font color='red'>税号已经存在！您的税号简码是：</font><font style='font-weight:bold' color='red'>"+jm+"</font>").css("display","block");
    						sys_ajaxPost("/wx/default.do?method=updaterwm&username="+username, queryString, function (json) {
								//alert(JSON.stringify(json));
								if(json.result==true||json.result=='true'){
									//var str=json.str
									//$('#code').qrcode({width:240,height:240,text:str}); 
									//alert("生成二维码成功！");
									$("#form_table").hide();
									$("#code").show();
									$("#infor").html("<font color='green'>您的名片简码是：</font><font style='font-weight:bold' color='red'>"+username+"</font>").css("left","48px").css("display","block");
									
								}else{
									$('#infor').html(json.msg).css("display","block");
								}
							});
    					}
					}else{
						$("#infor").html("<font color='red'>服务器出现了错误！</font>").css("display","block");
						
					}
				})
		
			
		}
     	$(function(){
     	
     		 loadinfo();
     	})
     	function loadinfo(){
     		sys_ajaxPost("/wx/default.do?method=queryrwmlist&username="+username+"&rnd="+new Date().getTime(), null, function (json) {
     			alert(JSON.stringify(json));
     			if(json.result==true){
     				var total=json.formData.total;
     				var rows=json.formData.rows;
     				if(total==1){
     					var data=rows[0];
     					var id=data.id;
     					var nsrsbh=data.nsrsbh;
     					var nsrmc=data.nsrmc;
     					var yhzh=data.yhzh;
     					var dz=data.dz;
     					
     					bind(data);
     				}
     				if(total>1){
     					
     				}
     			}
				
			
			});
     	}
     	function nsrsbhBlur(){
     		var dd=$("#nsrsbh").val().trim();
     		if(dd==''){
     			//$('#infor').html("纳税人识别号不能为空！").css("display","block");
     			//return;
     		}else{
     			var reg=/^[A-Za-z\d]{1,50}$/;
     			if(!reg.test(dd)){
     				$('#infor').html("纳税人识别号不合法！").css("display","block");
     				return;
     			}
     			$('#infor').html("").css("display","none");
     			
     			/* var jm=getLast6(dd);
     			sys_ajaxPost("/cpxx/default.do?method=queryrwm&nsrsbh="+dd+"&rnd="+new Date().getTime(), null, function (json) {
    				bind(json);
    			
    			}); */
     			
     			
     		}
     	}
     	function nsrmcBlur(){
     		var mm=$("#nsrmc").val().trim();
     		if(mm==''){
     			$('#infor').html("纳税人名称不能为空！").css("display","block");
     			return;
     		}else{
     			$('#infor').html("").css("display","none");
     		}
     	}
     	function yhzhBlur(){
     		var mm=$("#yhzh").val().trim();
     		if(mm==''){
     			//$('#infor').html("银行帐号不能为空！").css("display","block");
     			//return;
     		}else{
     			$('#infor').html("").css("display","none");
     		}
     	}
     	function dzBlur(){
     		var mm=$("#dz").val().trim();
     		if(mm==''){
     			//$('#infor').html("地址不能为空！").css("display","block");
     			//return;
     		}else{
     			$('#infor').html("").css("display","none");
     		}
     	}
     	String.prototype.trim = function() { 
     		return this.replace(/(^\s*)|(\s*$)/g, ""); 
		} 
     	function toUtf8(str) {    
     	    var out, i, len, c;    
     	    out = "";    
     	    len = str.length;    
     	    for(i = 0; i < len; i++) {    
     	        c = str.charCodeAt(i);    
     	        if ((c >= 0x0001) && (c <= 0x007F)) {    
     	            out += str.charAt(i);    
     	        } else if (c > 0x07FF) {    
     	            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));    
     	            out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));    
     	            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
     	        } else {    
     	            out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));    
     	            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
     	        }    
     	    }    
     	    return out;    
     	} 
     	String.prototype.replaceAll = function(s1,s2){ 
     		return this.replace(new RegExp(s1,"gm"),s2); 
     	}
     </script>
</head>
<body>

	<!-- <div class="header">
		<div class="logo"></div>
	</div> -->
<div class="mainbody" style="position:relative;">
	<div class="top">
		<h2>企业名片</h2>
		<!-- <div class="txt">欢迎您试用本页面生成二维码</div> -->
	</div>
	

	<div id="infor" class="infor" style="display:none;"></div>
	<form id="form" method="post" action="" >
		<table id="form_table" width="100%" border="0" cellpadding="0" cellspacing="0">
			<tbody>
				<!-- <tr>
				<td width="50" style="text-align:right;" height="50"></td>
				<td colspan="2"></td>
				</tr> -->
				<input type="hidden" id="id" name="id"/>
				<tr>
				<td width="50" class="nowrap" style="text-align:right;" height="50">纳税人识别号：</td>
				<td colspan="2"><input type="text" name="nsrsbh" id="nsrsbh" class="input" onblur="nsrsbhBlur()" /></td>
				</tr>
				<tr>
					<td width="50" class="nowrap" style="text-align:right;" height="50">纳税人名称：</td>
					<td colspan="2"><input type="text"  id="nsrmc" name="nsrmc" class="input"  onblur="nsrmcBlur()" /></td>
				</tr>
				<tr>
					<td width="50" class="nowrap" style="text-align:right;" height="50">银行帐号：</td>
					<td colspan="2"><input type="text"  id="yhzh" name="yhzh" class="input" onblur="yhzhBlur()" /></td>
				</tr>
				<tr>
					<td width="50" class="nowrap" style="text-align:right;" height="50">地 址：</td>
					<td colspan="2"><input type="text"  id="dz" name="dz" class="input" onblur="dzBlur()" /></td>
				</tr>
				<tr>
					<td  height="70"> </td>
					<td width="110"><input type="button" class="sub green_btn " value="生成二维码" onclick="save()" /></td>
					<td width="1138">&nbsp;<input type="button" class="sub red_btn " value="重置" onclick="reset()" /></td>
				</tr>
			</tbody>
		</table>
	</form>
	<div id="code" style="display:none;"   class="regbtn"></div>
	<div class="cl"></div>
</div>

	<!-- footer start -->
  <!--  <footer class="footer mt-20" style="position:relative;">
    	<div class="container-fluid">
    		<p style="text-align:center;">Copyright &copy;2009-<script type="text/javascript">
   		document.write(new Date().getFullYear());
   </script> 山东腾蛟起凤软件技术有限公司 
		</p>
    	</div>
    </footer> -->
    <!-- footer end -->

</body>
</html>