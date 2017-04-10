<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="ID"%>
<%@ attribute name="width" type="java.lang.String" required="false" description="宽度"%>
<%@ attribute name="height" type="java.lang.String" required="false" description="高度"%>
<%@ attribute name="type" type="java.lang.String" required="false" description="圆点、数字、长方形"%>
<style>
    /*幻灯片*/
    .slider{position:relative;text-align:center; margin:0 auto;z-index:1}
    .slider .bd,.slider .bd li,.slider .bd img{width:960px; height:272px}/*请给每个幻灯片套个div并设置id，通过id重置这个地方的宽度，达到自定义效果*/
    .slider .bd{z-index:2;overflow:hidden}
    .slider .bd li{float:left;width: 100%;overflow:hidden; background-position:center; background-repeat:no-repeat}
    .slider .bd li a{ display:block; width: 100%; height: 100%}
    .slider .bd li img{display:block}
    .slider .hd{ position: absolute; z-index: 3; left: 0; right: 0; bottom:10px; padding: 0 10px; text-align: center}
    .slider .hd li{display:inline-block;text-align:center;margin-right:10px;cursor:pointer;background-color:#C2C2C2}
    .slider .hd li.active{background-color:#f8ac59}
    /*圆点*/
    .dots li{width:10px; height:10px;font-size:0px;line-height:0px;border-radius:50%}
    /*数字*/
    .numbox li{width:20px; height:20px; line-height:20px; font-size:13px;font-family:Arial;font-weight:bold; text-indent:inherit}
    .numbox li.active{color:#fff}
    /*长方条*/
    .rectangle li{width:40px; height:10px;font-size:0px;line-height:0px}

    #${id}{ width: 960px; height: 273px; overflow: hidden}/*这个是demo页面定义尺寸的样式，请根据自己幻灯片的尺寸重新定义。切莫生搬硬套。*/

</style>
<script type="text/javascript" src="/front/demo/banner/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">

    $(function(){
        jQuery("#${id} .slider").slide({mainCell:".bd ul",titCell:".hd li",trigger:"click",effect:"leftLoop",autoPlay:true,delayTime:700,interTime:7000,pnLoop:false,titOnClassName:"active"})
       /* jQuery("#${id}").css("width",${width}).css("height",${height});*/
    });
</script>

<div id="${id}">
    <div class="slider">
        <div class="bd">
            <ul>
                <li><a href="#" target="_blank"><img src="/front/demo/banner/images/banner-1.jpg" ></a></li>
                <li><a href="#" target="_blank"><img src="/front/demo/banner/images/banner-1.jpg" ></a></li>
                <li><a href="#" target="_blank"><img src="/front/demo/banner/images/banner-1.jpg" ></a></li>
                <li><a href="#" target="_blank"><img src="/front/demo/banner/images/banner-1.jpg" ></a></li>
            </ul>
        </div>
        <c:choose>
            <c:when test="${type}==''|| ${type} is null">
                <ol class="hd cl dots">
            </c:when>
            <c:otherwise>
                <ol class="hd cl ${type}">
            </c:otherwise>
        </c:choose>

            <li>1</li>
            <li>2</li>
            <li>3</li>
            <li>4</li>
        </ol>
    </div>
</div>