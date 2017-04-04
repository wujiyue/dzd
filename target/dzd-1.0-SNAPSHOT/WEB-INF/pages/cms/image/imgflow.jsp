<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 -图片流</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico">

    <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">

    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/index/index.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/resources/lib/layui-v1.0.7/layui/css/layui.css"  media="all">

    <style>
        .lightBoxGallery img {
            margin: 5px;
            width: 160px;
        }
    </style>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">

                    <div class="ibox-content">

                        <h2>相册插件</h2>
                        <p>
                            <strong>blueimp Gallery</strong>主要为移动设备而设计，同时也支持桌面浏览器。可定制视频和相片，支持触摸操作，支持全屏播放等。项目地址： <a href="https://github.com/blueimp/Gallery" target="_blank">https://github.com/blueimp/Gallery</a>
                        </p>

                        <div id="LAY_div" class="site-demo-flow" >
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a><br/>
                            <a href="/resources/img/p_big1.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p1.jpg"></a>
                            <a href="/resources/img/p_big2.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p2.jpg"></a>
                            <a href="/resources/img/p_big3.jpg" title="图片" data-gallery=""><img lay-src="/resources/img/p3.jpg"></a>

                          <%--  <div id="blueimp-gallery" class="blueimp-gallery">
                                <div class="slides"></div>
                                <h3 class="title"></h3>
                                <a class="prev"><</a>
                                <a class="next">></a>
                                <a class="close">×</a>
                                <a class="play-pause"></a>
                                <ol class="indicator"></ol>
                            </div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/resources/js/content.min.js"></script>

    <script type="text/javascript" src="${ctx}/resources/lib/layui-v1.0.7/layui/layui.js"></script>
    <script type="text/javascript">
      layui.use('flow', function(){
            var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
            var flow = layui.flow;
            flow.lazyimg({
              elem: '#LAY_div img'
              ,scrollElem: '#LAY_div' //一般不用设置，此处只是演示需要。
          });
        });
    </script>
</body>
</html>
