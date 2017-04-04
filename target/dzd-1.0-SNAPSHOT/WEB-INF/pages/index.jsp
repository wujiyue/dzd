<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.EhCacheUtils" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="${ctx}/resources/lib/html5.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/respond.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/PIE_IE678.js"></script>
<![endif]-->
	<%@include file="/WEB-INF/pages/include/common.jsp"%>

	<%if(request.getAttribute("prompt")!=null){%>
	alert('<%=request.getAttribute("prompt")%>');
	<%}%>
<link href="${ctx}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/resources/lib/fontawesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="${ctx}/resources/css/bro.css" rel="stylesheet" type="text/css" />
<script  type="text/javascript" src="${ctx}/resources/js/common/main.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/H-ui.admin.js"></script>
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>H-ui.admin v2.3</title>
<meta name="keywords" content="H-ui.admin v2.3,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v2.3，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
	<style>
		.menu_dropdown dl dd ul li i{display:block;float:left;}
	</style>
</head>
<body>
<header class="Hui-header cl"> <a class="Hui-logo l" title="H-ui.admin v2.3" href="/">H-ui.admin</a> <a class="Hui-logo-m l" href="/" title="H-ui.admin">H-ui</a> <span class="Hui-subtitle l">V2.3</span>
	<nav class="mainnav cl" id="Hui-nav">
		<ul>
			<li class="dropDown dropDown_click"><a href="javascript:;" aria-expanded="true" aria-haspopup="true" data-toggle="dropdown" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
				<ul class="dropDown-menu radius box-shadow">
					<li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
					<li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
					<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
					<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
				</ul>
			</li>
		</ul>
	</nav>
	<ul class="Hui-userbar">
		<li>超级管理员</li>
		<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A"><%=EhCacheUtils.getUserInfo("xm",(String) request.getAttribute("yhid"))%><%--<i class="Hui-iconfont">&#xe6d5;</i>--%></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="#">个人信息</a></li>
				<li><a href="${ctx}/logout">切换账户</a></li>
				<li><a href="${ctx}/logout">退出</a></li>
			</ul>
		</li>
		<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
		<li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
				<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
				<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
				<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
				<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
				<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
			</ul>
		</li>
	</ul>
	<a href="javascript:;" class="Hui-nav-toggle Hui-iconfont" aria-hidden="false">&#xe667;</a> </header>
<aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	<div class="menu_dropdown bk_2">
		<dl id="menu-personal">
			<dt><%--<i class="Hui-iconfont">&#xe62d;</i>--%><i class="fa fa-user"></i> 个人中心<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="/account/personalInfo" data-title="个人信息" href="javascript:void(0)">个人信息</a></li>
					<li><a _href="article-list.html" data-title="修改密码" href="javascript:void(0)">修改密码</a></li>


				</ul>
			</dd>
		</dl>
		<dl id="menu-cms">
			<dt><i class="Hui-iconfont">&#xe616;</i> 内容管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="/cms/image" data-title="图片管理" href="javascript:void(0)">图片管理</a></li>

					<li><a _href="/cms/resourcetype" data-title="资源分类" href="javascript:void(0)">资源分类</a></li>
					<li><a _href="/cms/resource" data-title="资源" href="javascript:void(0)">资源</a></li>
					<li><a _href="/cms/channel" data-title="栏目管理" href="javascript:void(0)">栏目管理</a></li>
					<li><a _href="/cms/channelBig" data-title="栏目大类管理" href="javascript:void(0)">栏目大类管理</a></li>

					<li><a _href="/cms/article" data-title="资讯管理" href="javascript:void(0)">资讯管理</a></li>
					<li><a _href="/cms/link" data-title="链接管理" href="javascript:void(0)">链接管理</a></li>


				</ul>
			</dd>
		</dl>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe620;</i> 产品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="/cms/brand" data-title="品牌管理" href="javascript:void(0)">品牌管理</a></li>
					<li><a _href="/cms/product" data-title="产品管理" href="javascript:void(0)">产品管理</a></li>
					<li><a _href="/cms/onlineupdate" data-title="产品在线升级管理" href="javascript:void(0)">产品在线升级管理</a></li>

				</ul>
			</dd>
		</dl>
		<!--<dl id="menu-page">
			<dt><i class="Hui-iconfont">&#xe626;</i> 页面管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="page-home.html" href="javascript:void(0)">首页管理</a></li>
					<li><a _href="page-flinks.html" href="javascript:void(0)">友情链接</a></li>
				</ul>
			</dd>
		</dl>-->

		<!--<dl id="menu-order">
			<dt><i class="Hui-iconfont">&#xe63a;</i> 财务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="order-list.html" href="javascript:void(0)">订单列表</a></li>
					<li><a _href="recharge-list.html" href="javascript:void(0)">充值管理</a></li>
					<li><a _href="invoice-list.html" href="javascript:void(0)">发票管理</a></li>
				</ul>
			</dd>
		</dl>-->
		<dl id="menu-member">
			<dt><i class="Hui-iconfont">&#xe60d;</i> 用户管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="member-list.html" data-title="用户列表" href="javascript:;">会员列表</a></li>
					<li><a _href="member-del.html" data-title="删除的用户" href="javascript:;">删除的会员</a></li>
					<li><a _href="member-level.html" data-title="等级管理" href="javascript:;">等级管理</a></li>
					<li><a _href="member-scoreoperation.html" data-title="积分管理" href="javascript:;">积分管理</a></li>
					<li><a _href="member-record-browse.html" data-title="浏览记录" href="javascript:void(0)">浏览记录</a></li>
					<li><a _href="member-record-download.html" data-title="下载记录" href="javascript:void(0)">下载记录</a></li>
					<li><a _href="member-record-share.html" data-title="分享记录" href="javascript:void(0)">分享记录</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-admin">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 权限管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="/sys/permission" data-title="菜单权限" href="javascript:void(0)">菜单权限</a></li>

					<li><a _href="/org/role/" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>

					<li><a _href="/org/user/" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-template">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 网页模版<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/front/template/article/1/article.html" target="_blank" data-title="文章模版-cnblog" href="javascript:void(0)">文章模版一</a></li>

					<li><a href="/front/template/mobile/form1/form1.html" target="_blank" data-title="手机表单" href="javascript:void(0)">手机表单一</a></li>

					<li><a href="/front/template/email/1/tem_email.html" target="_blank" data-title="邮件模版" href="javascript:void(0)">邮件模版</a></li>

				</ul>
			</dd>
		</dl>
		<dl id="menu-template-wx">
			<dt><i class="Hui-iconfont">&#xe62d;</i> 微信端网页模版<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/front/template/weui/login.html" target="_blank" data-title="登录页面" href="javascript:void(0)">登录页面</a></li>
					<li><a href="/front/template/weui/reg.html" target="_blank" data-title="注册页面" href="javascript:void(0)">注册页面</a></li>
					<li><a href="/front/template/weui/tabbar.html" target="_blank" data-title="tab页面" href="javascript:void(0)">tab页面</a></li>
					<li><a href="/front/template/weui/navbar.html" target="_blank" data-title="nav页面" href="javascript:void(0)">nav页面</a></li>
					<li><a href="/front/template/weui/actionSheet.html" target="_blank" data-title="弹出菜单" href="javascript:void(0)">弹出菜单</a></li>
					<li><a href="/front/template/weui/article.html" target="_blank" data-title="文章" href="javascript:void(0)">文章</a></li>

				</ul>
			</dd>
		</dl>
		<dl id="menu-demo">
			<dt><i class="Hui-iconfont">&#xe62d;</i> Demo<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="/front/demo/demo1/index.html" data-title="随机飘动的广告" href="javascript:void(0)">随机飘动的广告</a></li>

				</ul>
			</dd>
		</dl>
		<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i> 功能演示<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="/resources/static/sm.jsp" data-title="使用说明" href="javascript:void(0)">使用说明</a></li>

					<li><a _href="/resources/static/doc.html" data-title="框架简介" href="javascript:void(0)">框架简介</a></li>

					<li><i class="Hui-iconfont Hui-iconfont-tongji-xian"></i><a _href="/resources/static/charts/charts-1.html" data-title="折线图" href="javascript:void(0)">折线图</a></li>
					<li><a _href="/resources/static/charts/charts-2.html" data-title="时间轴折线图" href="javascript:void(0)">时间轴折线图</a></li>
					<li><a _href="/resources/static/charts/charts-3.html" data-title="区域图" href="javascript:void(0)">区域图</a></li>
					<li><i class="Hui-iconfont Hui-iconfont-tongji-zhu"></i><a _href="/resources/static/charts/charts-4.html" data-title="柱状图" href="javascript:void(0)">柱状图</a></li>
					<li><i class="Hui-iconfont Hui-iconfont-tongji-bing"></i><a _href="/resources/static/charts/charts-5.html" data-title="饼状图" href="javascript:void(0)">饼状图</a></li>
					<li><a _href="/resources/static/charts/charts-6.html" data-title="3D柱状图" href="javascript:void(0)">3D柱状图</a></li>
					<li><a _href="/resources/static/charts/charts-7.html" data-title="3D饼状图" href="javascript:void(0)">3D饼状图</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-system">
			<dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="/org/organization" data-title="组织机构" href="javascript:void(0)">组织机构</a></li>
					<li><a _href="/org/organization/wh" data-title="组织信息维护" href="javascript:void(0)">组织信息维护</a></li>

					<li><a _href="/org/department" data-title="部门管理" href="javascript:void(0)">部门管理</a></li>

					<li><a _href="/org/position" data-title="岗位管理" href="javascript:void(0)">岗位管理</a></li>

					<li><a _href="/org/tree" data-title="组织目录" href="javascript:void(0)">组织目录</a></li>

					<li><a _href="/sys/settings" data-title="系统设置" href="javascript:void(0)">系统设置</a></li>
					<li><a _href="/base/mkdm" data-title="模块管理" href="javascript:void(0)">模块管理</a></li>
					<li><a _href="/base/para" data-title="系统参数管理" href="javascript:void(0)">系统参数管理</a></li>
					<li><a _href="/base/securityQueston" data-title="安全问题管理" href="javascript:void(0)">安全问题管理</a></li>

					<li><a _href="/sys/cache/" data-title="系统缓存" href="javascript:void(0)">系统缓存</a></li>
					<li><a _href="/base/dictionary" data-title="数据字典" href="javascript:void(0)">数据字典</a></li>
					<li><a _href="/sys/sensitivewords" data-title="屏蔽词" href="javascript:void(0)">屏蔽词</a></li>
					<li><a _href="/base/actionlog" data-title="系统日志" href="javascript:void(0)">系统日志</a></li>
					<li><i class="Hui-iconfont Hui-iconfont-middle"></i><a  _href="/druid/" data-title="连接池监控" href="javascript:void(0)">连接池监控</a></li>

					<li><i class="iconfont">&#xf0005;</i><a _href="/base/area" data-title="区域管理" href="javascript:void(0)">区域管理</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">

		<!--我的桌面-->
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="/resources/static/welcome.html"></iframe>


		</div>
	</div>
</section>



<script type="text/javascript">
/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 



<div id="rightframe_menu" class="easyui-menu" style="width:150px;">
	<%--<div id="rightframe_menu_mm-refrasht">刷新</div>
      <div class="menu-sep"></div>--%>
	<div id="rightframe_menu_mm-tabclose">关闭</div>
	<div id="rightframe_menu_mm-tabcloseall">全部关闭</div>
	<div id="rightframe_menu_mm-tabcloseother">除此之外全部关闭</div>
	<div class="menu-sep"></div>
	<div id="rightframe_menu_mm-tabcloseright">当前页右侧全部关闭</div>
	<div id="rightframe_menu_mm-tabcloseleft">当前页左侧全部关闭</div>
	<div class="menu-sep"></div>
	<div id="rightframe_menu_mm-exit">取消</div>
</div>

</body>
</html>