<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 联系人</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">

    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/index/index.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/resources/lib/layui-v1.0.7/layui/css/layui.css"  media="all">
</head>

<body class="gray-bg">
    <div  class="wrapper wrapper-content animated fadeInRight">
        <div id="lxrs" class="row flow-default">
        </div>
        <%--<c:forEach var="user" items="${users}" >
            <div class="row">
                <div class="col-sm-4">
                    <div class="contact-box">
                        <a href="profile.html">
                            <div class="col-sm-4">
                                <div class="text-center">
                                    <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a2.jpg">
                                    <div class="m-t-xs font-bold">${user.nickname}</div>
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <h3><strong>${user.realname}</strong></h3>
                                <p><i class="fa fa-map-marker"></i> ${user.address}</p>
                                <address>
                                    <abbr title="Phone">Tel:</abbr> ${user.phone}<br>
                                    E-mail:${user.email}<br>
                                </address>
                            </div>
                            <div class="clearfix"></div>
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>--%>

        <%--<div class="row">
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a2.jpg">
                                <div class="m-t-xs font-bold">CTO</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>奔波儿灞</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 甘肃·兰州</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
           &lt;%&ndash; <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a1.jpg">
                                <div class="m-t-xs font-bold">营销总监</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>灞波儿奔</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 四川·成都</p>
                            <address>
                            <strong>Taobao, Inc.</strong><br>
                            E-mail:xxx@taobao.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a3.jpg">
                                <div class="m-t-xs font-bold">Marketing manager</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>Monica Smith</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a4.jpg">
                                <div class="m-t-xs font-bold">攻城师</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>Michael Zimber</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a5.jpg">
                                <div class="m-t-xs font-bold">射鸡师</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>作家崔成浩</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a6.jpg">
                                <div class="m-t-xs font-bold">射鸡师</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>韩寒</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a1.jpg">
                                <div class="m-t-xs font-bold">CEO</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>郭敬明</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a2.jpg">
                                <div class="m-t-xs font-bold">射鸡师</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>马云</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a3.jpg">
                                <div class="m-t-xs font-bold">市场总监</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>范爷</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a4.jpg">
                                <div class="m-t-xs font-bold">攻城师</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>李彦宏</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a5.jpg">
                                <div class="m-t-xs font-bold">射鸡师</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>小马哥</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a6.jpg">
                                <div class="m-t-xs font-bold">射鸡师</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>乔峰</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>

                </div>
            </div>
            <div class="col-sm-4">
                <div class="contact-box">
                    <a href="profile.html">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a1.jpg">
                                <div class="m-t-xs font-bold">CEO</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>慕容晓晓</strong></h3>
                            <p><i class="fa fa-map-marker"></i> 上海市闵行区绿地科技岛广场A座2606室</p>
                            <address>
                            <strong>Baidu, Inc.</strong><br>
                            E-mail:xxx@baidu.com<br>
                            Weibo:<a href="#">http://weibo.com/xxx</a><br>
                            <abbr title="Phone">Tel:</abbr> (123) 456-7890
                        </address>
                        </div>
                        <div class="clearfix"></div>
                    </a>
                </div>
            </div>&ndash;%&gt;

        </div>--%>
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
                elem: '#lxrs' //指定列表容器
                ,isAuto: false
                ,isLazyimg: true
                ,done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
                    setTimeout(function(){
                    var lis = [];
                    //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                    $.get('${ctx}/cms/user/json/find?page='+page+"&rows=3", function(res){
                        //假设你的列表返回在data集合中
                        var html="";
                       // alert(JSON.stringify(eval("("+res+")")));
                        var rows=eval("("+res+")").rows.items;
                        var total=eval("("+res+")").total;
                        var pages=Math.floor((Number(total)+2)/3);
                        layui.each(rows, function(index, item){

                            html="";
                            html+='<div class="col-sm-4">';
                            html+='<div class="contact-box">';
                            html+='<a href="profile.html">';
                            html+='<div class="col-sm-4">';
                            html+='<div class="text-center">';
                            html+='<img alt="image" class="img-circle m-t-xs img-responsive" src="/resources/img/a2.jpg">';
                            html+=' <div class="m-t-xs font-bold">'+item.nickname+'</div>';
                            html+=' </div>';
                            html+=' </div>';
                            html+=' <div class="col-sm-8">';
                            html+='<h3><strong>'+item.realname+'</strong></h3>';
                            html+=' <p><i class="fa fa-map-marker"></i> '+item.address+'</p>';
                            html+=' <address>';
                            html+='<abbr title="Phone">Tel:</abbr>'+ item.phone+'<br>';
                            html+='email:'+item.email+'<br>';
                            html+='  </address>';
                            html+=' </div>';
                            html+=' <div class="clearfix"></div>';
                            html+=' </a>';
                            html+=' </div>';
                            html+='</div>';

                            lis.push(html);
                        });
                        //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                        //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                        next(lis.join(''), page < pages);

                    });
                    }, 500);
                }
            });
        });
    </script>
    
</body>
</html>
