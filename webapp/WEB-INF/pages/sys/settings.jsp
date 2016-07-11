﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>系统设置</title>
<link href="${ctx}/resources/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />


	<script type="text/javascript">
		$(function(){
			/*$('.skin-minimal input').iCheck({
				checkboxClass: 'icheckbox-blue',
				radioClass: 'iradio-blue',
				increaseArea: '20%'
			});*/
			$.Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
		});
		function save(){

		}
	</script>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span>系统设置 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-article-add">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar tabBar-blue cl"><span>系统设置</span><span>站点设置</span><span>安全设置</span><span>邮件设置</span><span>其他设置</span></div>
			<div class="tabCon">

			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>网站名称：</label>
					<div class="formControls col-10">
						<input type="text" id="website-title" placeholder="控制在25个字、50个字节以内" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>关键词：</label>
					<div class="formControls col-10">
						<input type="text" id="website-Keywords" placeholder="5个左右,8汉字以内,用英文,隔开" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>描述：</label>
					<div class="formControls col-10">
						<%--<input type="text" id="website-description" placeholder="空制在80个汉字，160个字符以内" value="" class="input-text">--%>
						<textarea id="website-description" class="textarea" placeholder="空制在80个汉字，160个字符以内" ></textarea>
					</div>
				</div>
				<%--<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>css、js、images路径配置：</label>
					<div class="formControls col-10">
						<input type="text" id="website-static" placeholder="默认为空，为相对路径" value="" class="input-text">
					</div>
				</div>--%>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>上传目录配置：</label>
					<div class="formControls col-10">
						<input type="text" id="website-uploadfile" placeholder="默认为uploadfile" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2"><span class="c-red">*</span>底部版权信息：</label>
					<div class="formControls col-10">
						<input type="text" id="website-copyright" placeholder="&copy; 2014 H-ui.net" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">备案号：</label>
					<div class="formControls col-10">
						<input type="text" id="website-icp" placeholder="京ICP备00000000号" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">统计代码：</label>
					<div class="formControls col-10">
						<textarea id="website-tjdm" class="textarea" placeholder="统计代码"></textarea>
					</div>
				</div>
			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-2">允许访问后台的IP列表：</label>
					<div class="formControls col-10">
						<textarea id="security-accessip" class="textarea"  placeholder="允许访问后台的IP列表"></textarea>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">后台登录失败最大次数：</label>
					<div class="formControls col-10">
						<input type="text" id="security-failuretime" value="5" class="input-text">
					</div>
				</div>
			</div>
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-2">邮件发送模式：</label>
					<div class="formControls col-10">
						<input type="text" id="" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">SMTP服务器：</label>
					<div class="formControls col-10">
						<input type="text" id="" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">SMTP 端口：</label>
					<div class="formControls col-10">
						<input type="text" id="" value="25" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">邮箱帐号：</label>
					<div class="formControls col-10">
						<input type="text" id="email-name" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">邮箱密码：</label>
					<div class="formControls col-10">
						<input type="password" id="email-password" value="" class="input-text">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-2">收件邮箱地址：</label>
					<div class="formControls col-10">
						<input type="text" id="email-address" value="" class="input-text">
					</div>
				</div>
			</div>
			<div class="tabCon">
				
			</div>
		</div>
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<button onClick="save();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
			</div>
		</div>
	</form>
</div>


</body>
</html>