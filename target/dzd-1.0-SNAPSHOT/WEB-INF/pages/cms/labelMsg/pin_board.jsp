<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 标签墙</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico">
 <%--   <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>--%>
    <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">

    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/index/index.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/resources/lib/layui-v1.0.7/layui/css/layui.css"  media="all">

</head>

<body class="gray-bg">
    <div class="row">
        <div class="col-sm-12">
            <div class="wrapper wrapper-content animated fadeInUp ">
                <div id="notes" class="row flow-default">

                   <!-- <li>
                        <div>
                            <small>2014年10月24日(星期五) 下午5:31</small>
                            <h4>HTML5 文档类型</h4>
                            <p>Bootstrap 使用到的某些 HTML 元素和 CSS 属性需要将页面设置为 HTML5 文档类型。在你项目中的每个页面都要参照下面的格式进行设置。</p>
                            <a href="pin_board.html#"><i class="fa fa-trash-o "></i></a>
                        </div>
                    </li>
                    <li>
                        <div>
                            <small>2014年10月24日(星期五) 下午5:31</small>
                            <h4>移动设备优先</h4>
                            <p>在 Bootstrap 2 中，我们对框架中的某些关键部分增加了对移动设备友好的样式。而在 Bootstrap 3 中，我们重写了整个框架，使其一开始就是对移动设备友好的。这次不是简单的增……</p>
                            <a href="pin_board.html#"><i class="fa fa-trash-o "></i></a>
                        </div>
                    </li>
                    <li>
                        <div>
                            <small>2014年10月24日(星期五) 下午5:31</small>
                            <h4>排版与链接</h4>
                            <p>Bootstrap 排版、链接样式设置了基本的全局样式。分别是： 为 body 元素设置 background-color: #fff; 使用 @font-family-base、@font-size-base 和……。</p>
                            <a href="pin_board.html#"><i class="fa fa-trash-o "></i></a>
                        </div>
                    </li>
                    <li>
                        <div>
                            <small>2014年10月24日(星期五) 下午5:31</small>
                            <h4>Normalize.css</h4>
                            <p>为了增强跨浏览器表现的一致性，我们使用了 Normalize.css，这是由 Nicolas Gallagher 和 Jonathan Neal 维护的一个CSS 重置样式库。</p>
                            <a href="pin_board.html#"><i class="fa fa-trash-o "></i></a>
                        </div>
                    </li>
                    <li>
                        <div>
                            <small>2014年10月24日(星期五) 下午5:31</small>
                            <h4>布局容器</h4>
                            <p>Bootstrap 需要为页面内容和栅格系统包裹一个 .container 容器。我们提供了两个作此用处的类。注意，由于 padding 等属性的原因，这两种 容器类不能互相嵌套。</p>
                            <a href="pin_board.html#"><i class="fa fa-trash-o "></i></a>
                        </div>
                    </li>
                    <li>
                        <div>
                            <small>2014年10月24日(星期五) 下午5:31</small>
                            <h4>栅格系统</h4>
                            <p>Bootstrap 提供了一套响应式、移动设备优先的流式栅格系统，随着屏幕或视口（viewport）尺寸的增加，系统会自动分为最多12列。它包含了易于使用的预定义类，还有强大的mixin 用于生成更具语义的布局。</p>
                            <a href="pin_board.html#"><i class="fa fa-trash-o "></i></a>
                        </div>
                    </li>-->


                    </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/resources/js/content.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/layui-v1.0.7/layui/layui.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){$(".contact-box").each(function(){animationHover(this,"pulse")})});
        layui.use('flow', function(){
            var $ = layui.jquery; //不用额外加载jQuery，flow模块本身是有依赖jQuery的，直接用即可。
            var flow = layui.flow;
            flow.load({
                elem: '#notes' //指定列表容器
                ,isAuto: false
                ,isLazyimg: true
                ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
                    setTimeout(function(){
                        var lis = [];
                        //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                        $.get('${ctx}/cms/labelMsg/json/find?page='+page+"&rows=4", function(res){
                            //假设你的列表返回在data集合中
                            var html="";
                            // alert(JSON.stringify(eval("("+res+")")));
                            var rows=eval("("+res+")").rows.items;
                            var total=eval("("+res+")").total;
                            var pages=Math.floor((Number(total)+2)/3);
                            layui.each(rows, function(index, item){
                                html="";
                                html+='<li>';
                                html+='<div>';
                                html+='<small>'+item.createTime+'</small>';
                                html+='<h4>'+item.title+'</h4>';
                                html+='<p>'+item.content+'</p>';
                                html+='<a href="#"><i class="fa fa-trash-o "></i></a>';
                                html+=' </div>';
                                html+=' </li>';

                                lis.push(html);
                            });
                            //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                            //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                            next("<ul class='notes'>"+lis.join('')+"</div>", page < pages);

                        });
                    }, 500);
                }
            });
        });
    </script>
    
</body>
</html>
