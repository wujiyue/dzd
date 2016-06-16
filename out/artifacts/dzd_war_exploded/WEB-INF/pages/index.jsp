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


<link href="${ctx}/resources/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/resources/lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
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
		<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A"><%=EhCacheUtils.getUserInfo("xm",(String) request.getAttribute("yhid"))%><i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="#">个人信息</a></li>
				<li><a href="${ctx}/tuichu">切换账户</a></li>
				<li><a href="${ctx}/tuichu">退出</a></li>
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
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 资讯管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="article-list.html" data-title="资讯管理" href="javascript:void(0)">资讯管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-picture">
			<dt><i class="Hui-iconfont">&#xe613;</i> 图片管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="picture-list.html" data-title="图片管理" href="javascript:void(0)">图片管理</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe620;</i> 产品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="product-brand.html" data-title="品牌管理" href="javascript:void(0)">品牌管理</a></li>
					<li><a _href="product-category.html" data-title="分类管理" href="javascript:void(0)">分类管理</a></li>
					<li><a _href="product-list.html" data-title="产品管理" href="javascript:void(0)">产品管理</a></li>
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
		<dl id="menu-comments">
			<dt><i class="Hui-iconfont">&#xe622;</i> 评论管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="http://h-ui.duoshuo.com/admin/" data-title="评论列表" href="javascript:;">评论列表</a></li>
					<li><a _href="feedback-list.html" data-title="意见反馈" href="javascript:void(0)">意见反馈</a></li>
				</ul>
			</dd>
		</dl>
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
			<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a _href="/sys/role/" data-title="角色管理" href="javascript:void(0)">角色管理</a></li>
					<li><a _href="admin-permission.html" data-title="权限管理" href="javascript:void(0)">权限管理</a></li>
					<li><a _href="/sys/user/" data-title="管理员列表" href="javascript:void(0)">管理员列表</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-tongji">
			<dt><i class="Hui-iconfont">&#xe61a;</i> 系统统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
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
					<li><a _href="/sys/settings" data-title="系统设置" href="javascript:void(0)">系统设置</a></li>
					<li><a _href="system-category.html" data-title="栏目管理" href="javascript:void(0)">栏目管理</a></li>
					<li><a _href="system-data.html" data-title="数据字典" href="javascript:void(0)">数据字典</a></li>
					<li><a _href="/sys/sensitivewords" data-title="屏蔽词" href="javascript:void(0)">屏蔽词</a></li>
					<li><a _href="/sys/syslog" data-title="系统日志" href="javascript:void(0)">系统日志</a></li>
					<li><i class="Hui-iconfont Hui-iconfont-middle"></i><a  _href="/druid/" data-title="连接池监控" href="javascript:void(0)">连接池监控</a></li>
					<li><i class="Hui-iconfont Hui-iconfont-middle"></i><a _href="/sys/menu" data-title="菜单管理" href="javascript:void(0)">菜单管理</a></li>

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
			<%--<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>--%>
			<div class="pd-20" style="padding-top:20px;">
				<p class="f-20 text-success">欢迎光临Bro.Admin <span class="f-14">V1.0</span>后台管理系统！</p>
				<div class="content cl">
					<p class="text-c f-20">关于Bro.admin</p>
					<p class="f-16">一.简介</p>
					<p class="indent"><span class="c-org">Bro.admin</span>是基于多个优秀的开源项目，高度整合封装而成的高效，高性能，强安全性的开源Java EE快速开发平台以及后台管理系统。
						<span class="c-org">Bro.admin</span>本身是以<span class="c-primary">Spring Framework</span> 为核心容器，<span class="c-blue">Spring MVC</span>为模型视图控制器，<span class="c-red">MyBatis</span>为数据访问层， <span class="c-error">Apache Shiro</span> 为权限控制层，<span class="c-success">Ehcahe</span> 对常用数据进行缓存。</p>
					<p class="indent">
						<span class="c-org">Bro.admin</span>主要定位于企业信息化领域，已内置企业信息化系统的基础功能和高效的代码生成工具，包括：<strong>系统权限组件、数据权限组件、数据字典组件、核心工具组件、视图操作组件、代码生成</strong>等。采用<strong>分层设计、双重验证、提交数据安全编码、密码加密、访问验证、数据权限验证</strong>。使用<strong>Maven</strong>做项目管理，提高项目的易开发性、扩展性。
					</p>
					<p class="f-16">二.内置功能</p>
					<ol class="linenums">
						<li>用户管理：用户是系统操作者，该功能主要完成系统用户配置。 </li>
						<li>机构管理：配置系统组织机构。 </li>
						<li>区域管理：系统城市区域模型，如：国家、省市、地市、区县的维护。</li>
						<li>菜单管理：配置系统菜单，操作权限，按钮权限标识等。</li>
						<li>角色管理：角色菜单权限分配、设置角色按机构进行数据范围权限划分。 </li>
						<li>字典管理：对系统中经常使用的一些较为固定的数据进行维护，如：是否、男女、类别、级别等。</li>
						<li>操作日志：系统正常操作日志记录和查询；系统异常信息日志记录和查询。</li>
						<li>连接池监视：监视当期系统数据库连接池状态，可进行分析SQL找出系统性能瓶颈。</li>
					</ol>
					<p class="f-16">三.开发环境</p>
					<ol class="linenums">
						<li>IntelliJ IDEA 14：采用Maven项目管理，模块化。</li>
						<li>Java JDK8</li>
						<li>Tomcat8.0</li>
						<li>Git</li>
					</ol>
					<p class="f-16">四.设计思想</p>
					<ol class="linenums">
						<li>分层设计：（数据访问层M，业务逻辑层C，视图层V）层次清楚，低耦合，各层必须通过接口才能接入并进行参数校验（如：在展示层不可直接操作数据库），保证数据操作的安全。</li>
						<li>双重验证：用户表单提交双验证：包括服务器端验证及客户端验证，防止用户通过浏览器恶意修改（如不可写文本域、隐藏变量篡改、上传非法文件等）而跳过客户端验证操作数据库。</li>
						<li>安全编码：用户表单提交所有数据，在服务器端都进行安全编码，防止用户提交非法脚本及SQL注入获取敏感数据等，确保数据安全。</li>
						<li>密码加密：登录用户密码进行SHA1散列加密，此加密方法是不可逆的。保证密文泄露后的安全问题。</li>
						<li>访问验证：系统对所有管理端链接都进行用户身份权限验证，防止用户直接通过URL进行未授权页面。</li>
						<li>快速编码：提供基本功能模块的源代码生成器，提高开发效率及质量。</li>
					</ol>
					<p class="f-16">五.技术选型</p>
					<ol class="linenums">
						<li>
							<p>后端</p>
							<ul style="list-style-type: disc">
								<li>核心框架：Spring Framework 4.14</li>
								<li>安全框架：Apache Shiro 1.2.3</li>
								<li>视图框架：Spring MVC 4.1.4</li>
								<li>服务端验证：Hibernate Validator 5.1.1</li>
								<li>任务调度：Spring Task 4.0</li>
								<li>持久层框架：MyBatis 3.2.7</li>
								<li>数据库连接池：Alibaba Druid 1.0.11</li>
								<li>缓存框架：Ehcache 2.6、Redis</li>
								<li>日志管理：SLF4J 1.7、Log4j</li>
								<li>工具类：Apache Commons、Jackson 2.4、Xstream 1.4、Dozer 5.5.1、		POI 3.9 、guava18.0</li>
								<li>...</li>
							</ul>
						</li>
						<li>
							<p>前端</p>
							<ul style="list-style-type:disc">
								<li>JS框架：JQuery 1.9.1</li>
								<li>CSS框架：主：H-ui，辅Bootstrap 3.3.5</li>
								<li>客户端验证：Validaform 5.3.2</li>
								<li>富文本：UMEcitor</li>
								<li>对话框：layer2.0</li>
								<li>树结构控件：jQuery zTree3.5</li>
								<li>日期控件： My97DatePicker</li>
								<li>...</li>
							</ul>
						</li>

					</ol>
					<p class="f-16">六.必须会的知识</p>
					<ol class="linenums">
						<li>软件工程基础、Java语言基础、JSP内置对象、EL表达式</li>
						<li>Spring Framework：类的依赖、自动注入、事务处理</li>
						<li>Spring MVC：URL映射、参数传递</li>
						<li>Apache Shiro：安全拦截方式，应用方法，控制按钮</li>
						<li>JSP标准标签库（JSTL）：if、choose、forEach、set</li>
						<li>Spring MVC表单标签库：form、input、textarea、select、checkbox</li>
						<li>文件存放规范，命名规范。</li>
						<li>...</li>
					</ol>
				</div>
			</div>
			<footer class="footer">
				<p>感谢jQuery、layer、laypage、Validform、UEditor、My97DatePicker、iconfont、Font Awesome、Datatables、jquery.fileupload、Lightbox插件<br>Copyright &copy;2015 Bro.admin v1.0 All Rights Reserved.<br>
			</footer>

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