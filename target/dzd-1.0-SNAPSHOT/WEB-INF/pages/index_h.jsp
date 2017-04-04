<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.markbro.asoiaf.core.utils.EhCacheUtils" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
  <%--  <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />--%>
    <title>H+ 后台主题UI框架 - 主页</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
    <link type="text/css" rel="stylesheet" href="/resources/css/animate.min.css">
    <link href="/resources/css/index/index.css" rel="stylesheet">


</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav id="sliderbar" class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src="/resources/img/profile_small.jpg" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold"><%=EhCacheUtils.getUserInfo("xm",(String) request.getAttribute("yhid"))%></strong></span>
                                <span class="text-muted text-xs block">超级管理员<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                                </li>
                                <li><a class="J_menuItem" href="/account/personalInfo">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="contacts.html">联系我们</a>
                                </li>
                                <li><a class="J_menuItem" href="mailbox.html">信箱</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="/logout">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">H+
                        </div>
                    </li>

                <%--    <li>
                        <a class="J_menuItem" href="/sys/front" data-index="0"><i class="fa fa-home"></i> <span class="nav-label">首页</span></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">个人中心</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/account/personalInfo">个人资料</a>
                            </li>
                            <li><a class="J_menuItem" href="article-list.html">修改密码</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">内容管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/cms/image">图片管理</a></li>
                            <li><a class="J_menuItem" href="/cms/tpq">相册</a></li>
                            <li><a class="J_menuItem" href="/cms/resourcetype">资源分类</a></li>
                            <li><a class="J_menuItem" href="/cms/resource">资源</a></li>

                            <li><a class="J_menuItem" href="/cms/channel">栏目管理</a></li>
                            <li><a class="J_menuItem" href="/cms/channelBig">栏目大类管理</a></li>

                            <li><a class="J_menuItem" href="/cms/article">资讯管理</a></li>
                            <li><a class="J_menuItem" href="/cms/link">链接管理</a></li>
                            <li><a class="J_menuItem" href="/cms/contacts">联系人</a></li>

                            <li><a class="J_menuItem" href="/cms/sendEmail">邮件管理</a></li>
                            <li><a class="J_menuItem" href="/cms/labelMsg">便签管理</a></li>
                            <li><a class="J_menuItem" href="/cms/pin_board">便签墙</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">产品管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/cms/brand">品牌分类</a></li>
                            <li><a class="J_menuItem" href="/cms/product">产品信息维护</a></li>
                            <li><a class="J_menuItem" href="/cms/onlineupdate">在线升级管理</a></li>
                            <li><a class="J_menuItem" href="/cms/cpupdateLog">更新日志管理</a></li>
                            <li><a class="J_menuItem" href="/sys/cploginLog">登录日志管理</a></li>
                            <li><a class="J_menuItem" href="/cms/timeline">时光轴管理</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">Demos</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a  target="_blank" href="/front/Hplus/index.html">H+</a></li>
                            <li><a  target="_blank"  href="/front/mobile/weui/demos/index.html">weui</a></li>
                            <li>
                                <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">页面模版</span><span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a class="J_menuItem" href="/front/template/article/1/article.html">文章模版-cnblog</a></li>

                                    <li><a class="J_menuItem" href="/front/template/mobile/form1/form1.html">手机表单</a></li>

                                    <li><a class="J_menuItem" href="/front/template/email/1/tem_email.html">邮件模版</a></li>
                                    <li><a class="J_menuItem" href="/front/page/timeline.html">时光轴</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">微信页面模版</span><span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a class="J_menuItem" href="/front/template/weui/login.html">登录页面</a></li>

                                    <li><a class="J_menuItem" href="/front/template/weui/reg.html">注册页面</a></li>

                                    <li><a class="J_menuItem" href="/front/template/weui/tabbar.html">tab</a></li>
                                    <li><a class="J_menuItem" href="/front/template/weui/navbar.html">nav</a></li>
                                </ul>
                            </li>
                            <li><a class="J_menuItem" href="/front/demo/demo1/index.html">随机飘动的广告</a></li>

                            <li>
                                <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">图表</span><span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-1.html">折线图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-2.html">时间轴折线图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-3.html">区域图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-4.html">柱状图</a></li>

                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-5.html">饼状图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-6.html">3D柱状图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-7.html">3D柱状图</a></li>
                                </ul>
                            </li>


                        </ul>
                    </li>

                    <li>
                        <a class="J_menuItem"    href="/resources/static/sm.jsp"><i class="fa fa-hand-o-right"></i> <span class="nav-label">演示测试</span></a>
                    </li>
                    <li>
                        <a class="J_menuItem"    href="/resources/static/table_bootstrap.jsp"><i class="fa fa-hand-o-right"></i> <span class="nav-label">演示测试</span></a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">代码生成</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/gen/config"><i class="fa fa-cog"></i> 代码生成配置</a></li>


                        </ul>
                    </li>

                    <li>
                        <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">权限管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/permission">菜单权限</a></li>

                            <li><a class="J_menuItem" href="/org/role/">角色管理</a></li>

                            <li><a class="J_menuItem" href="/org/user/">管理员列表</a></li>

                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-cog"></i> <span class="nav-label">系统设置</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/org/organization">组织机构</a>
                            </li>
                            <li><a class="J_menuItem" href="/org/organization/wh">组织信息维护</a>
                            </li>
                            <li><a class="J_menuItem" href="/org/tree">组织目录管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/org/department">部门管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/org/position">岗位管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/sys/settings">系统设置</a>
                            </li>
                            <li><a class="J_menuItem" href="/base/mkdm">模块管理</a></li>
                            <li><a class="J_menuItem" href="/base/para">系统参数管理</a></li>
                            <li><a class="J_menuItem" href="/base/securityQueston">安全问题管理</a></li>
                            <li><a class="J_menuItem" href="/sys/cache/">系统缓存</a></li>
                            <li><a class="J_menuItem" href="/base/dictionary">数据字典</a></li>
                            <li><a class="J_menuItem" href="/sys/sensitivewords">屏蔽词</a></li>
                            <li><a class="J_menuItem" href="/base/actionlog">系统日志</a></li>
                            <li><a class="J_menuItem" href="/druid/">连接池监控</a></li>
                            <li><a class="J_menuItem" href="/base/area">区域管理</a></li>

                        </ul>
                    </li>



                    <li>
                        <a href="#"><i class="fa fa-cutlery"></i> <span class="nav-label">工具 </span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/front/Hplus/css_animation.html"><i class="fa fa-magic"></i> <span class="nav-label">CSS动画</span></a>
                            </li>
                            <li><a class="J_menuItem" href="form_builder.html">表单构建器</a>
                            </li>
                        </ul>
                    </li>--%>
            <c:forEach items="${qxlist}" var="qx">
                <li>
                <c:set var="children2" value="${qx.children}"></c:set>
               <%-- <c:if test="${list== null || fn:length(list) == 0}"></c:if>--%>
                <c:choose>
                    <c:when test="${children2!=null && fn:length(children2)> 0}">
                        <a href="#"><i class="${qx.icon}"></i> <span class="nav-label">${qx.name}</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <c:forEach items="${children2}" var="qx2">
                                    <c:set var="children3" value="${qx2.children}"></c:set>
                                    <c:choose>
                                        <c:when test="${children3!=null && fn:length(children3) > 0}">
                                            <li>
                                            <a href="#"><i class="${qx2.icon}"></i> <span class="nav-label">${qx2.name}</span><span class="fa arrow"></span></a>
                                            <ul class="nav nav-second-level">
                                            <c:forEach items="${children3}" var="qx3">
                                                <c:choose>
                                                    <c:when test="${qx3.target==''||qx3.target=='undefined'||qx3.target==null}">
                                                    <a class="J_menuItem" href="${qx3.url}" ><i class="${qx3.icon}"></i> <span class="nav-label">${qx3.name}</span></a>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <a href="${qx3.url}" target="${qx3.target}"><i class="${qx3.icon}"></i> <span class="nav-label">${qx3.name}</span></a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                             </li>
                                            </ul>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${qx2.target==''||qx2.target=='undefined'||qx2.target==null}">
                                                    <a class="J_menuItem" href="${qx2.url}" ><i class="${qx2.icon}"></i> <span class="nav-label">${qx2.name}</span></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${qx2.url}" target="${qx2.target}"><i class="${qx2.icon}"></i> <span class="nav-label">${qx2.name}</span></a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${qx.target==''||qx.target=='undefined'||qx.target==null}">
                                <a class="J_menuItem" href="${qx.url}" ><i class="${qx.icon}"></i> <span class="nav-label">${qx.name}</span></a>
                            </c:when>
                            <c:otherwise>
                                <a href="${qx.url}" target="${qx.target}"><i class="${qx.icon}"></i> <span class="nav-label">${qx.name}</span></a>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                </li>
            </c:forEach>
                  <%--  <li><a class="J_menuItem" href="/sys/front" data-index="0"><i class="fa fa-first-order"></i> <span class="nav-label">首页</span></a></li>
                    <li><a href="#"><i class="fa fa-user"></i> <span class="nav-label">个人中心</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="1">个人信息</a></li>
                            <li><a class="J_menuItem" href="2">修改密码</a></li>
                        </ul></li>
                    <li><a href="#"><i class="fa fa-glide"></i> <span class="nav-label">内容管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/cms/image">图片管理</a></li>
                            <li><a class="J_menuItem" href="/cms/tpq">相册</a></li>
                            <li><a class="J_menuItem" href="/cms/resourcetype">资源分类管理</a></li>
                            <li><a class="J_menuItem" href="/cms/resource">资源管理</a></li>
                            <li><a class="J_menuItem" href="/cms/channel">栏目管理</a></li>
                            <li><a class="J_menuItem" href="/cms/channelBig">栏目大类管理</a></li>
                            <li><a class="J_menuItem" href="/cms/article">资讯管理</a></li>
                            <li><a class="J_menuItem" href="/cms/link">链接管理</a></li>
                            <li><a class="J_menuItem" href="/cms/contacts">联系人</a></li>
                            <li><a class="J_menuItem" href="/cms/sendEmail">邮件管理</a></li>
                        </ul></li>
                    <li><a href="#"><i class="fa fa-calendar-minus-o"></i> <span class="nav-label">Demos</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a target="_blank" href="/front/Hplus/index.html">H+</a></li>
                            <li><a target="_blank" href="/front/mobile/weui/demos/index.html">WeUi</a></li>
                            <li><a target="_blank" href="/resources/static/sm.jsp">演示测试1</a></li>
                            <li><a href="#"><i class=""></i> <span class="nav-label">页面模版</span><span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a class="J_menuItem" href="/front/demo/demo1/index.html">随机飘动的广告</a></li>
                                    <li><a class="J_menuItem" href="/front/template/article/1/article.html">文章模版-cnblog</a></li>
                                    <li><a class="J_menuItem" href="/front/template/mobile/form1/form1.html">手机表单</a></li>
                                    <li><a class="J_menuItem" href="/front/template/email/1/tem_email.html">邮件模版</a></li>
                                    <li><a class="J_menuItem" href="/front/page/timeline.html">时光轴</a></li>
                                </ul></li>
                            <li><a class="J_menuItem" href="">微信页面模版</a></li>
                            <li><a href="#"><i class=""></i> <span class="nav-label">图表</span><span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-1.html">折线图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-2.html">时间轴折线图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-3.html">区域图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-4.html">柱状图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-5.html">饼状图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-6.html">3D柱状图</a></li>
                                    <li><a class="J_menuItem" href="/resources/static/charts/charts-7.html">3D柱状图</a></li>
                                </ul></li>
                        </ul></li>
                    <li><a href="#"><i class="fa fa-server"></i> <span class="nav-label">权限管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/sys/permission">菜单权限</a></li>
                            <li><a class="J_menuItem" href="/org/role">角色管理</a></li>
                            <li><a class="J_menuItem" href="/org/user/">管理员列表</a></li>
                        </ul></li>
                    <li><a href="#"><i class="fa fa-adjust"></i> <span class="nav-label">系统管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/org/organization">组织机构</a></li>
                            <li><a class="J_menuItem" href="/org/organization/wh">组织信息维护</a></li>
                            <li><a class="J_menuItem" href="/org/department">部门管理</a></li>
                            <li><a class="J_menuItem" href="/org/position">岗位管理</a></li>
                            <li><a class="J_menuItem" href="/org/tree">组织目录</a></li>
                            <li><a class="J_menuItem" href="/sys/settings">系统设置</a></li>
                            <li><a class="J_menuItem" href="/base/mkdm">模块管理</a></li>
                            <li><a class="J_menuItem" href="/base/para">系统参数管理</a></li>
                            <li><a class="J_menuItem" href="/sys/cache">系统缓存</a></li>
                            <li><a href="#"><i class=""></i> <span class="nav-label">数据字典</span><span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a class="J_menuItem" href="/base/dictionary/view">查询</a></li>
                                </ul></li>
                            <li><a class="J_menuItem" href="/base/actionlog">系统日志</a></li>
                            <li><a class="J_menuItem" href="/sys/sensitivewords">屏蔽词</a></li>
                            <li><a class="J_menuItem" href="/druid/">连接池监控</a></li>
                        </ul></li>
                    <li><a href="#"><i class="fa fa-gitlab"></i> <span class="nav-label">工具箱</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="">CSS动画</a></li>
                            <li><a class="J_menuItem" href="">表单构建器</a></li>
                        </ul></li>--%>

                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="page-wrapper gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#" style="display: none"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" style="margin-bottom: 0;" method="post" action="http://www.zi-han.net/theme/hplus/search_results.html">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                                <i class="fa fa-envelope"></i> <span class="label label-warning">16</span>
                            </a>
                            <ul class="dropdown-menu dropdown-messages">
                                <li class="m-t-xs">
                                    <div class="dropdown-messages-box">
                                        <a href="profile.html" class="pull-left">
                                            <img alt="image" class="img-circle" src="/resources/img/a7.jpg">
                                        </a>
                                        <div class="media-body">
                                            <small class="pull-right">46小时前</small>
                                            <strong>小四</strong> 这个在日本投降书上签字的军官，建国后一定是个不小的干部吧？
                                            <br>
                                            <small class="text-muted">3天前 2014.11.8</small>
                                        </div>
                                    </div>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="dropdown-messages-box">
                                        <a href="profile.html" class="pull-left">
                                            <img alt="image" class="img-circle" src="/resources/img/a4.jpg">
                                        </a>
                                        <div class="media-body ">
                                            <small class="pull-right text-navy">25小时前</small>
                                            <strong>国民岳父</strong> 如何看待“男子不满自己爱犬被称为狗，刺伤路人”？——这人比犬还凶
                                            <br>
                                            <small class="text-muted">昨天</small>
                                        </div>
                                    </div>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a class="J_menuItem" href="mailbox.html">
                                            <i class="fa fa-envelope"></i> <strong> 查看所有消息</strong>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                                <i class="fa fa-bell"></i> <span class="label label-primary">8</span>
                            </a>
                            <ul class="dropdown-menu dropdown-alerts">
                                <li>
                                    <a href="mailbox.html">
                                        <div>
                                            <i class="fa fa-envelope fa-fw"></i> 您有16条未读消息
                                            <span class="pull-right text-muted small">4分钟前</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="profile.html">
                                        <div>
                                            <i class="fa fa-qq fa-fw"></i> 3条新回复
                                            <span class="pull-right text-muted small">12分钟钱</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a class="J_menuItem" href="notifications.html">
                                            <strong>查看所有 </strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li class="hidden-xs">
                            <a href="index_v1.html" class="J_menuItem" data-index="0"><i class="fa fa-cart-arrow-down"></i> 购买</a>
                        </li>
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i> 主题
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="/sys/front">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="/sys/front" frameborder="0" data-id="/sys/front" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2014-2015 <a href="http://www.zi-han.net/" target="_blank">zihan's blog</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div id="right-sidebar" class="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-3">

                    <li class="active">
                        <a data-toggle="tab" href="#tab-1">
                            <i class="fa fa-gear"></i> 主题
                        </a>
                    </li>
                    <li class=""><a data-toggle="tab" href="#tab-2">
                        通知
                    </a>
                    </li>
                    <li><a data-toggle="tab" href="#tab-3">
                        项目进度
                    </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                            <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">主题设置</div>
                           <!-- <div class="setings-item">
                                <span>收起左侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                        <label class="onoffswitch-label" for="collapsemenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>-->
                            <div class="setings-item">
                                <span>右侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="rightmenu" class="onoffswitch-checkbox" id="rightmenu">
                                        <label class="onoffswitch-label" for="rightmenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定顶部</span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                        <label class="onoffswitch-label" for="fixednavbar">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>
                        固定宽度
                    </span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                        <label class="onoffswitch-label" for="boxedlayout">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="title">皮肤选择</div>
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         <a href="#" class="s-skin-0">
                             默认皮肤
                         </a>
                    </span>
                            </div>
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-1">
                            蓝色主题
                        </a>
                    </span>
                            </div>
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-3">
                            黄色/紫色主题
                        </a>
                    </span>
                            </div>
                        </div>
                    </div>
                    <div id="tab-2" class="tab-pane">

                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 最新通知</h3>
                            <small><i class="fa fa-tim"></i> 您当前有10条未读信息</small>
                        </div>

                        <div>

                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="/resources/img/a1.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">

                                        据天津日报报道：瑞海公司董事长于学伟，副董事长董社轩等10人在13日上午已被控制。
                                        <br>
                                        <small class="text-muted">今天 4:21</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="/resources/img/a2.jpg">
                                    </div>
                                    <div class="media-body">
                                        HCY48之音乐大魔王会员专属皮肤已上线，快来一键换装拥有他，宣告你对华晨宇的爱吧！
                                        <br>
                                        <small class="text-muted">昨天 2:45</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="/resources/img/a3.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">
                                        写的好！与您分享
                                        <br>
                                        <small class="text-muted">昨天 1:10</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="/resources/img/a4.jpg">
                                    </div>

                                    <div class="media-body">
                                        国外极限小子的炼成！这还是亲生的吗！！
                                        <br>
                                        <small class="text-muted">昨天 8:37</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="/resources/img/a8.jpg">
                                    </div>
                                    <div class="media-body">

                                        一只流浪狗被收留后，为了减轻主人的负担，坚持自己觅食，甚至......有些东西，可能她比我们更懂。
                                        <br>
                                        <small class="text-muted">今天 4:21</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="/resources/img/a7.jpg">
                                    </div>
                                    <div class="media-body">
                                        这哥们的新视频又来了，创意杠杠滴，帅炸了！
                                        <br>
                                        <small class="text-muted">昨天 2:45</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="/resources/img/a3.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">
                                        最近在补追此剧，特别喜欢这段表白。
                                        <br>
                                        <small class="text-muted">昨天 1:10</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="/resources/img/a4.jpg">
                                    </div>
                                    <div class="media-body">
                                        我发起了一个投票 【你认为下午大盘会翻红吗？】
                                        <br>
                                        <small class="text-muted">星期一 8:37</small>
                                    </div>
                                </a>
                            </div>
                        </div>

                    </div>
                    <div id="tab-3" class="tab-pane">

                        <div class="sidebar-title">
                            <h3> <i class="fa fa-cube"></i> 最新任务</h3>
                            <small><i class="fa fa-tim"></i> 您当前有14个任务，10个已完成</small>
                        </div>

                        <ul class="sidebar-list">
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9小时以后</div>
                                    <h4>市场调研</h4> 按要求接收教材；

                                    <div class="small">已完成： 22%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                    </div>
                                    <div class="small text-muted m-t-xs">项目截止： 4:00 - 2015.10.01</div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9小时以后</div>
                                    <h4>可行性报告研究报上级批准 </h4> 编写目的编写本项目进度报告的目的在于更好的控制软件开发的时间,对团队成员的 开发进度作出一个合理的比对

                                    <div class="small">已完成： 48%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9小时以后</div>
                                    <h4>立项阶段</h4> 东风商用车公司 采购综合综合查询分析系统项目进度阶段性报告武汉斯迪克科技有限公司

                                    <div class="small">已完成： 14%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-primary pull-right">NEW</span>
                                    <h4>设计阶段</h4>
                                    <!--<div class="small pull-right m-t-xs">9小时以后</div>-->
                                    项目进度报告(Project Progress Report)
                                    <div class="small">已完成： 22%</div>
                                    <div class="small text-muted m-t-xs">项目截止： 4:00 - 2015.10.01</div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9小时以后</div>
                                    <h4>拆迁阶段</h4> 科研项目研究进展报告 项目编号: 项目名称: 项目负责人:

                                    <div class="small">已完成： 22%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                    </div>
                                    <div class="small text-muted m-t-xs">项目截止： 4:00 - 2015.10.01</div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9小时以后</div>
                                    <h4>建设阶段 </h4> 编写目的编写本项目进度报告的目的在于更好的控制软件开发的时间,对团队成员的 开发进度作出一个合理的比对

                                    <div class="small">已完成： 48%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9小时以后</div>
                                    <h4>获证开盘</h4> 编写目的编写本项目进度报告的目的在于更好的控制软件开发的时间,对团队成员的 开发进度作出一个合理的比对

                                    <div class="small">已完成： 14%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                    </div>
                                </a>
                            </li>

                        </ul>

                    </div>
                </div>

            </div>
        </div>
        <!--右侧边栏结束-->
        <!--mini聊天窗口开始-->
        <div class="small-chat-box fadeInRight animated">

            <div class="heading" draggable="true">
                <small class="chat-date pull-right">
                    2015.9.1
                </small> 与 Beau-zihan 聊天中
            </div>

            <div class="content">

                <div class="left">
                    <div class="author-name">
                        Beau-zihan <small class="chat-date">
                        10:02
                    </small>
                    </div>
                    <div class="chat-message active">
                        你好
                    </div>

                </div>
                <div class="right">
                    <div class="author-name">
                        游客
                        <small class="chat-date">
                            11:24
                        </small>
                    </div>
                    <div class="chat-message">
                        你好，请问H+有帮助文档吗？
                    </div>
                </div>
                <div class="left">
                    <div class="author-name">
                        Beau-zihan
                        <small class="chat-date">
                            08:45
                        </small>
                    </div>
                    <div class="chat-message active">
                        有，购买的H+源码包中有帮助文档，位于docs文件夹下
                    </div>
                </div>
                <div class="right">
                    <div class="author-name">
                        游客
                        <small class="chat-date">
                            11:24
                        </small>
                    </div>
                    <div class="chat-message">
                        那除了帮助文档还提供什么样的服务？
                    </div>
                </div>
                <div class="left">
                    <div class="author-name">
                        Beau-zihan
                        <small class="chat-date">
                            08:45
                        </small>
                    </div>
                    <div class="chat-message active">
                        1.所有源码(未压缩、带注释版本)；
                        <br> 2.说明文档；
                        <br> 3.终身免费升级服务；
                        <br> 4.必要的技术支持；
                        <br> 5.付费二次开发服务；
                        <br> 6.授权许可；
                        <br> ……
                        <br>
                    </div>
                </div>
            </div>
            <div class="form-chat">
                <div class="input-group input-group-sm">
                    <input type="text" class="form-control"> <span class="input-group-btn"> <button
                        class="btn btn-primary" type="button">发送
                </button> </span>
                </div>
            </div>

        </div>
        <div id="small-chat">
            <span class="badge badge-warning pull-right">5</span>
            <a class="open-small-chat">
                <i class="fa fa-comments"></i>
            </a>
        </div>
    </div>

   <%-- <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.js"></script>
    <script src="/resources/lib/metisMenu/jquery.metisMenu.js"></script>
    <script src="/resources/lib/slimscroll/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="/resources/lib/layer/1.9.3/layer.js"></script>
    <script src="/resources/js/hplus.js"></script>
    <script type="text/javascript" src="/resources/lib/contabs.min.js"></script>
    <script src="/resources/lib/pace/pace.min.js"></script>
    <script src="/resources/js/index_h.js"></script>--%>
    <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/resources/lib/metisMenu/jquery.metisMenu.js"></script>
    <script src="/resources/lib/slimscroll/jquery.slimscroll.min.js"></script>

    <script type="text/javascript" src="/resources/lib/layer/3.0.3/layer.js"></script>
    <script src="/resources/js/common/broutil.js"></script>
    <script src="/resources/js/index_h.js"></script>
    <script src="/resources/js/hplus.js"></script>

    <script type="text/javascript" src="/resources/lib/contabs.min.js"></script>
    <script src="/resources/lib/pace/pace.min.js"></script>

</body>
</html>
