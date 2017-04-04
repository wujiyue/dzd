<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>

<html>
<head>
    <title>图标选择</title>
	<script type="text/javascript" src="${ctx}/resources/lib/jquery/jquery.min.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/H-ui.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/lib/fontawesome/css/font-awesome.min.css">
	<link type="text/css" rel="stylesheet" href="${ctx}/resources/css/animate.min.css">
    <style type="text/css">
    	.page-header {clear:both;margin:0 20px;padding-top:20px;}
		.the-icons {padding:25px 10px 15px;list-style:none;}
		.the-icons li {float:left;width:22%;line-height:25px;margin:2px 5px;cursor:pointer;}
		.the-icons i {margin:1px 5px;font-size:16px;} .the-icons li:hover {background-color:#efefef;}
        .the-icons li.active {background-color:#0088CC;color:#ffffff;}
       /* .the-icons li:hover i{font-size:20px;}*/

		body {
			font-family: "open sans","Helvetica Neue",Helvetica,Arial,sans-serif;
			font-size: 13px;
			color: #676a6c;
			overflow-x: hidden;
			padding:5px;
		}
		.gray-bg {
			background-color: #f3f3f4;
		}
		section {
			margin-top: 5px;
		}
		.page-header {
			padding-bottom: 9px;
			margin: 40px 0 20px;
			border-bottom: 1px solid #eeeeee;
		}
		.fontawesome-icon-list {
			margin-top: 22px;
		}
		.row {
			margin-left: 0px;
			margin-right: 0px;
		}
		.row:before {
			content: " ";
			display: table;
		}
		.row:after {
			clear: both;
			content: " ";
			display: table;
		}
		a {
			color: #21b384;
			text-decoration: none;
			background-color: transparent;
		}
		.fontawesome-icon-list .fa-hover a {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
			display: block;
			color: #222222;
			line-height: 32px;
			height: 32px;
			padding-left: 10px;
			border-radius: 4px;
		}
		.fontawesome-icon-list .fa-hover a:hover ,a.active{
			background-color: #1d9d74;
			color: #ffffff;
			text-decoration: none;
		}
		.fontawesome-icon-list .fa-hover a:hover .fa,a.active .fa{
			font-size: 30px;
			vertical-align: -6px;
			background-color: #1d9d74;
			color: #ffffff;
			text-decoration: none;
		}
		@media only screen and (min-width: 768px){
			.col-sm-4 {
				width: 33.33333333%;
			}
		}
		@media only screen and (min-width: 992px){
			.col-md-3 {
				width: 25%;
			}
		}
		@media only screen and (max-width: 768px){
			.col-md-3 {
				width: 50%;
			}
		}

    </style>
    <script type="text/javascript">
		var winindex = parent.layer.getFrameIndex(window.name);
		var nowValue='${value}';
		var idInput='${idInput}';
		// nowValue='fa fa-fa';
		//var mcInput='${mcInput}';
	    $(document).ready(function(){
			$("#search").keyup(function(){
				if($(this).val()!='')
				{
					$("#icons section div  div").hide();
				}else
				{
					$("#icons section div  div").show();
				}

				var $tem=$("#icons section div  div ").filter(":contains('"+($(this).val())+"')");
				if(!$tem.is(":animated")){
					$tem.show();
				}

			})

	    	$("#icons a").click(function(){
	    		/*$("#icons li").removeClass("active");
	    		$("#icons li i").removeClass("icon-white");*/
	    		$(this).addClass("active");
				$(this).parent().siblings().find("a").removeClass("active");
				$(this).parents("section").siblings().find("a").removeClass("active");
	    		//$(this).children("i").addClass("icon-white");
	    		//$("#icon_value").val($(this).text());
				nowValue=$(this).find("i").attr('class');
	    	});
	    	$("#icons a i").each(function(){
	    		if ($(this).attr('class')==nowValue){
					$(this).parent().addClass("active");
	    		}
	    	});
			$("#icons a").dblclick(function(){
				/*$("#icons li").removeClass("active");
				 $("#icons li i").removeClass("icon-white");*/
				$(this).addClass("active");
				$(this).parent().siblings().find("a").removeClass("active");
				$(this).parents("section").siblings().find("a").removeClass("active");
				//$(this).children("i").addClass("icon-white");
				//$("#icon_value").val($(this).text());
				nowValue=$(this).find("i").attr('class');

				parent.$('#'+idInput).val(nowValue);
				parent.$('#'+idInput).next('i').attr("class",nowValue);
				parent.layer.close(winindex);
			});
	    });

		function save(){
			//alert(nowValue);
			//nowValue=$("#icon_value").val();
			parent.$('#'+idInput).val(nowValue);
			parent.$('#'+idInput).next('i').attr("class",nowValue);
			parent.layer.close(winindex);
		}

		function clos(){
			parent.layer.close(winindex);
		}

    </script>
</head>
<body  class="gray-bg">
<input type="hidden" id="icon_value" value="${value}" />

<div class=" cl" style="position: fixed;top:0px;width:100%;z-index: 99;padding:8px;background-color:#F3F3F4;">

		<div class="text-l f-l" style="width: 50%;">
			<label for="search">筛选：</label><input type="text" id="search" />
		</div>
		<div class="text-r f-r" style="width: 50%;right:30px;position: relative;">
			<button type="button" onclick="clos()" class="btn btn-default size-S  fr" id="" name=""><i class="icon-ok"></i> 关闭</button>
			&nbsp;&nbsp;
			<button type="button" onclick="save()" class="btn btn-primary size-S  fr" id="" name=""><i class="icon-ok"></i> 确定</button>
		</div>
</div>

	<div id="icons" style="position: relative;top:42px;padding:0 25px;">

		<h3 class="text-c c-green">4.6.3版fontawesome图标</h3>
		<section id="new">
			<h2 class="page-header">4.6.3版新增30个全新的图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i> <span class="sr-only">Example of </span>american-sign-language-interpreting</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-asl-interpreting" aria-hidden="true"></i> <span class="sr-only">Example of </span>asl-interpreting <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-assistive-listening-systems" aria-hidden="true"></i> <span class="sr-only">Example of </span>assistive-listening-systems</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-audio-description" aria-hidden="true"></i> <span class="sr-only">Example of </span>audio-description</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-blind" aria-hidden="true"></i> <span class="sr-only">Example of </span>blind</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-braille" aria-hidden="true"></i> <span class="sr-only">Example of </span>braille</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deaf" aria-hidden="true"></i> <span class="sr-only">Example of </span>deaf</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deafness" aria-hidden="true"></i> <span class="sr-only">Example of </span>deafness <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envira" aria-hidden="true"></i> <span class="sr-only">Example of </span>envira</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fa" aria-hidden="true"></i> <span class="sr-only">Example of </span>fa <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-first-order" aria-hidden="true"></i> <span class="sr-only">Example of </span>first-order</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-font-awesome" aria-hidden="true"></i> <span class="sr-only">Example of </span>font-awesome</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gitlab" aria-hidden="true"></i> <span class="sr-only">Example of </span>gitlab</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-glide" aria-hidden="true"></i> <span class="sr-only">Example of </span>glide</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-glide-g" aria-hidden="true"></i> <span class="sr-only">Example of </span>glide-g</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-plus-circle" aria-hidden="true"></i> <span class="sr-only">Example of </span>google-plus-circle <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-plus-official" aria-hidden="true"></i> <span class="sr-only">Example of </span>google-plus-official</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hard-of-hearing" aria-hidden="true"></i> <span class="sr-only">Example of </span>hard-of-hearing <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-instagram" aria-hidden="true"></i> <span class="sr-only">Example of </span>instagram</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-low-vision" aria-hidden="true"></i> <span class="sr-only">Example of </span>low-vision</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pied-piper" aria-hidden="true"></i> <span class="sr-only">Example of </span>pied-piper</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-question-circle-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>question-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sign-language" aria-hidden="true"></i> <span class="sr-only">Example of </span>sign-language</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-signing" aria-hidden="true"></i> <span class="sr-only">Example of </span>signing <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-snapchat" aria-hidden="true"></i> <span class="sr-only">Example of </span>snapchat</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-snapchat-ghost" aria-hidden="true"></i> <span class="sr-only">Example of </span>snapchat-ghost</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-snapchat-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>snapchat-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-themeisle" aria-hidden="true"></i> <span class="sr-only">Example of </span>themeisle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-universal-access" aria-hidden="true"></i> <span class="sr-only">Example of </span>universal-access</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-viadeo" aria-hidden="true"></i> <span class="sr-only">Example of </span>viadeo</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-viadeo-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>viadeo-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-control-phone" aria-hidden="true"></i> <span class="sr-only">Example of </span>volume-control-phone</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair-alt" aria-hidden="true"></i> <span class="sr-only">Example of </span>wheelchair-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wpbeginner" aria-hidden="true"></i> <span class="sr-only">Example of </span>wpbeginner</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wpforms" aria-hidden="true"></i> <span class="sr-only">Example of </span>wpforms</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yoast" aria-hidden="true"></i> <span class="sr-only">Example of </span>yoast</a></div>
			</div>
		</section>
		<section id="web-application">
			<h2 class="page-header">网页常用图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-adjust" aria-hidden="true">


				</i> <span class="sr-only">Example of </span>adjust</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i> <span class="sr-only">Example of </span>american-sign-language-interpreting</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-anchor" aria-hidden="true"></i> <span class="sr-only">Example of </span>anchor</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-archive" aria-hidden="true"></i> <span class="sr-only">Example of </span>archive</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-area-chart" aria-hidden="true"></i> <span class="sr-only">Example of </span>area-chart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows" aria-hidden="true"></i> <span class="sr-only">Example of </span>arrows</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-h" aria-hidden="true"></i> <span class="sr-only">Example of </span>arrows-h</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-v" aria-hidden="true"></i> <span class="sr-only">Example of </span>arrows-v</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-asl-interpreting" aria-hidden="true"></i> <span class="sr-only">Example of </span>asl-interpreting <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-assistive-listening-systems" aria-hidden="true"></i> <span class="sr-only">Example of </span>assistive-listening-systems</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-asterisk" aria-hidden="true"></i> <span class="sr-only">Example of </span>asterisk</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-at" aria-hidden="true"></i> <span class="sr-only">Example of </span>at</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-audio-description" aria-hidden="true"></i> <span class="sr-only">Example of </span>audio-description</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-automobile" aria-hidden="true"></i> <span class="sr-only">Example of </span>automobile <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-balance-scale" aria-hidden="true"></i> <span class="sr-only">Example of </span>balance-scale</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ban" aria-hidden="true"></i> <span class="sr-only">Example of </span>ban</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bank" aria-hidden="true"></i> <span class="sr-only">Example of </span>bank <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bar-chart" aria-hidden="true"></i> <span class="sr-only">Example of </span>bar-chart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bar-chart-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>bar-chart-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-barcode" aria-hidden="true"></i> <span class="sr-only">Example of </span>barcode</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bars" aria-hidden="true"></i> <span class="sr-only">Example of </span>bars</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-0" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-0 <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-1" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-1 <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-2" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-2 <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-3" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-3 <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-4" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-4 <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-empty" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-empty</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-full" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-full</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-half" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-half</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-quarter" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-quarter</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-battery-three-quarters" aria-hidden="true"></i> <span class="sr-only">Example of </span>battery-three-quarters</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bed" aria-hidden="true"></i> <span class="sr-only">Example of </span>bed</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-beer" aria-hidden="true"></i> <span class="sr-only">Example of </span>beer</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bell" aria-hidden="true"></i> <span class="sr-only">Example of </span>bell</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bell-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>bell-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bell-slash" aria-hidden="true"></i> <span class="sr-only">Example of </span>bell-slash</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bell-slash-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>bell-slash-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bicycle" aria-hidden="true"></i> <span class="sr-only">Example of </span>bicycle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-binoculars" aria-hidden="true"></i> <span class="sr-only">Example of </span>binoculars</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-birthday-cake" aria-hidden="true"></i> <span class="sr-only">Example of </span>birthday-cake</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-blind" aria-hidden="true"></i> <span class="sr-only">Example of </span>blind</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bluetooth" aria-hidden="true"></i> <span class="sr-only">Example of </span>bluetooth</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bluetooth-b" aria-hidden="true"></i> <span class="sr-only">Example of </span>bluetooth-b</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bolt" aria-hidden="true"></i> <span class="sr-only">Example of </span>bolt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bomb" aria-hidden="true"></i> <span class="sr-only">Example of </span>bomb</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-book" aria-hidden="true"></i> <span class="sr-only">Example of </span>book</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bookmark" aria-hidden="true"></i> <span class="sr-only">Example of </span>bookmark</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bookmark-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>bookmark-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-braille" aria-hidden="true"></i> <span class="sr-only">Example of </span>braille</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-briefcase" aria-hidden="true"></i> <span class="sr-only">Example of </span>briefcase</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bug" aria-hidden="true"></i> <span class="sr-only">Example of </span>bug</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-building" aria-hidden="true"></i> <span class="sr-only">Example of </span>building</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-building-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>building-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bullhorn" aria-hidden="true"></i> <span class="sr-only">Example of </span>bullhorn</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bullseye" aria-hidden="true"></i> <span class="sr-only">Example of </span>bullseye</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bus" aria-hidden="true"></i> <span class="sr-only">Example of </span>bus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cab" aria-hidden="true"></i> <span class="sr-only">Example of </span>cab <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calculator" aria-hidden="true"></i> <span class="sr-only">Example of </span>calculator</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar" aria-hidden="true"></i> <span class="sr-only">Example of </span>calendar</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-check-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>calendar-check-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-minus-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>calendar-minus-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>calendar-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-plus-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>calendar-plus-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-calendar-times-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>calendar-times-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-camera" aria-hidden="true"></i> <span class="sr-only">Example of </span>camera</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-camera-retro" aria-hidden="true"></i> <span class="sr-only">Example of </span>camera-retro</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-car" aria-hidden="true"></i> <span class="sr-only">Example of </span>car</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>caret-square-o-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-left" aria-hidden="true"></i> <span class="sr-only">Example of </span>caret-square-o-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-right" aria-hidden="true"></i> <span class="sr-only">Example of </span>caret-square-o-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>caret-square-o-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cart-arrow-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>cart-arrow-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cart-plus" aria-hidden="true"></i> <span class="sr-only">Example of </span>cart-plus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc" aria-hidden="true"></i> <span class="sr-only">Example of </span>cc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-certificate" aria-hidden="true"></i> <span class="sr-only">Example of </span>certificate</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check" aria-hidden="true"></i> <span class="sr-only">Example of </span>check</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-circle" aria-hidden="true"></i> <span class="sr-only">Example of </span>check-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-circle-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>check-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>check-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-square-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>check-square-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-child" aria-hidden="true"></i> <span class="sr-only">Example of </span>child</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle" aria-hidden="true"></i> <span class="sr-only">Example of </span>circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-o-notch" aria-hidden="true"></i> <span class="sr-only">Example of </span>circle-o-notch</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-thin" aria-hidden="true"></i> <span class="sr-only">Example of </span>circle-thin</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-clock-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>clock-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-clone" aria-hidden="true"></i> <span class="sr-only">Example of </span>clone</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-close" aria-hidden="true"></i> <span class="sr-only">Example of </span>close <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cloud" aria-hidden="true"></i> <span class="sr-only">Example of </span>cloud</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cloud-download" aria-hidden="true"></i> <span class="sr-only">Example of </span>cloud-download</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cloud-upload" aria-hidden="true"></i> <span class="sr-only">Example of </span>cloud-upload</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-code" aria-hidden="true"></i> <span class="sr-only">Example of </span>code</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-code-fork" aria-hidden="true"></i> <span class="sr-only">Example of </span>code-fork</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-coffee" aria-hidden="true"></i> <span class="sr-only">Example of </span>coffee</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cog" aria-hidden="true"></i> <span class="sr-only">Example of </span>cog</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cogs" aria-hidden="true"></i> <span class="sr-only">Example of </span>cogs</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-comment" aria-hidden="true"></i> <span class="sr-only">Example of </span>comment</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-comment-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>comment-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-commenting" aria-hidden="true"></i> <span class="sr-only">Example of </span>commenting</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-commenting-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>commenting-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-comments" aria-hidden="true"></i> <span class="sr-only">Example of </span>comments</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-comments-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>comments-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-compass" aria-hidden="true"></i> <span class="sr-only">Example of </span>compass</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-copyright" aria-hidden="true"></i> <span class="sr-only">Example of </span>copyright</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-creative-commons" aria-hidden="true"></i> <span class="sr-only">Example of </span>creative-commons</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-credit-card" aria-hidden="true"></i> <span class="sr-only">Example of </span>credit-card</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-credit-card-alt" aria-hidden="true"></i> <span class="sr-only">Example of </span>credit-card-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-crop" aria-hidden="true"></i> <span class="sr-only">Example of </span>crop</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-crosshairs" aria-hidden="true"></i> <span class="sr-only">Example of </span>crosshairs</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cube" aria-hidden="true"></i> <span class="sr-only">Example of </span>cube</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cubes" aria-hidden="true"></i> <span class="sr-only">Example of </span>cubes</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cutlery" aria-hidden="true"></i> <span class="sr-only">Example of </span>cutlery</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dashboard" aria-hidden="true"></i> <span class="sr-only">Example of </span>dashboard <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-database" aria-hidden="true"></i> <span class="sr-only">Example of </span>database</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deaf" aria-hidden="true"></i> <span class="sr-only">Example of </span>deaf</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deafness" aria-hidden="true"></i> <span class="sr-only">Example of </span>deafness <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-desktop" aria-hidden="true"></i> <span class="sr-only">Example of </span>desktop</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-diamond" aria-hidden="true"></i> <span class="sr-only">Example of </span>diamond</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dot-circle-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>dot-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-download" aria-hidden="true"></i> <span class="sr-only">Example of </span>download</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-edit" aria-hidden="true"></i> <span class="sr-only">Example of </span>edit <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ellipsis-h" aria-hidden="true"></i> <span class="sr-only">Example of </span>ellipsis-h</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ellipsis-v" aria-hidden="true"></i> <span class="sr-only">Example of </span>ellipsis-v</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope" aria-hidden="true"></i> <span class="sr-only">Example of </span>envelope</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>envelope-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-envelope-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>envelope-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eraser" aria-hidden="true"></i> <span class="sr-only">Example of </span>eraser</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exchange" aria-hidden="true"></i> <span class="sr-only">Example of </span>exchange</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exclamation" aria-hidden="true"></i> <span class="sr-only">Example of </span>exclamation</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exclamation-circle" aria-hidden="true"></i> <span class="sr-only">Example of </span>exclamation-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exclamation-triangle" aria-hidden="true"></i> <span class="sr-only">Example of </span>exclamation-triangle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-external-link" aria-hidden="true"></i> <span class="sr-only">Example of </span>external-link</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-external-link-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>external-link-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eye" aria-hidden="true"></i> <span class="sr-only">Example of </span>eye</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eye-slash" aria-hidden="true"></i> <span class="sr-only">Example of </span>eye-slash</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eyedropper" aria-hidden="true"></i> <span class="sr-only">Example of </span>eyedropper</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fax" aria-hidden="true"></i> <span class="sr-only">Example of </span>fax</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-feed" aria-hidden="true"></i> <span class="sr-only">Example of </span>feed <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-female" aria-hidden="true"></i> <span class="sr-only">Example of </span>female</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fighter-jet" aria-hidden="true"></i> <span class="sr-only">Example of </span>fighter-jet</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-archive-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-archive-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-audio-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-audio-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-code-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-code-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-excel-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-excel-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-image-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-image-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-movie-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-movie-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-pdf-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-pdf-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-photo-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-photo-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-picture-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-picture-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-powerpoint-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-powerpoint-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-sound-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-sound-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-video-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-video-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-word-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-word-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-zip-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-zip-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-film" aria-hidden="true"></i> <span class="sr-only">Example of </span>film</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-filter" aria-hidden="true"></i> <span class="sr-only">Example of </span>filter</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fire" aria-hidden="true"></i> <span class="sr-only">Example of </span>fire</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fire-extinguisher" aria-hidden="true"></i> <span class="sr-only">Example of </span>fire-extinguisher</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flag" aria-hidden="true"></i> <span class="sr-only">Example of </span>flag</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flag-checkered" aria-hidden="true"></i> <span class="sr-only">Example of </span>flag-checkered</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flag-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>flag-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flash" aria-hidden="true"></i> <span class="sr-only">Example of </span>flash <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flask" aria-hidden="true"></i> <span class="sr-only">Example of </span>flask</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-folder" aria-hidden="true"></i> <span class="sr-only">Example of </span>folder</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-folder-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>folder-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-folder-open" aria-hidden="true"></i> <span class="sr-only">Example of </span>folder-open</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-folder-open-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>folder-open-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-frown-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>frown-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-futbol-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>futbol-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gamepad" aria-hidden="true"></i> <span class="sr-only">Example of </span>gamepad</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gavel" aria-hidden="true"></i> <span class="sr-only">Example of </span>gavel</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gear" aria-hidden="true"></i> <span class="sr-only">Example of </span>gear <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gears" aria-hidden="true"></i> <span class="sr-only">Example of </span>gears <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gift" aria-hidden="true"></i> <span class="sr-only">Example of </span>gift</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-glass" aria-hidden="true"></i> <span class="sr-only">Example of </span>glass</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-globe" aria-hidden="true"></i> <span class="sr-only">Example of </span>globe</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-graduation-cap" aria-hidden="true"></i> <span class="sr-only">Example of </span>graduation-cap</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-group" aria-hidden="true"></i> <span class="sr-only">Example of </span>group <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-grab-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-grab-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-lizard-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-lizard-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-paper-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-paper-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-peace-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-peace-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-pointer-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-pointer-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-rock-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-rock-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-scissors-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-scissors-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-spock-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-spock-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-stop-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-stop-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hard-of-hearing" aria-hidden="true"></i> <span class="sr-only">Example of </span>hard-of-hearing <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hashtag" aria-hidden="true"></i> <span class="sr-only">Example of </span>hashtag</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hdd-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hdd-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-headphones" aria-hidden="true"></i> <span class="sr-only">Example of </span>headphones</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heart" aria-hidden="true"></i> <span class="sr-only">Example of </span>heart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heart-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>heart-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heartbeat" aria-hidden="true"></i> <span class="sr-only">Example of </span>heartbeat</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-history" aria-hidden="true"></i> <span class="sr-only">Example of </span>history</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-home" aria-hidden="true"></i> <span class="sr-only">Example of </span>home</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hotel" aria-hidden="true"></i> <span class="sr-only">Example of </span>hotel <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass" aria-hidden="true"></i> <span class="sr-only">Example of </span>hourglass</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-1" aria-hidden="true"></i> <span class="sr-only">Example of </span>hourglass-1 <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-2" aria-hidden="true"></i> <span class="sr-only">Example of </span>hourglass-2 <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-3" aria-hidden="true"></i> <span class="sr-only">Example of </span>hourglass-3 <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-end" aria-hidden="true"></i> <span class="sr-only">Example of </span>hourglass-end</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-half" aria-hidden="true"></i> <span class="sr-only">Example of </span>hourglass-half</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hourglass-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hourglass-start" aria-hidden="true"></i> <span class="sr-only">Example of </span>hourglass-start</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-i-cursor" aria-hidden="true"></i> <span class="sr-only">Example of </span>i-cursor</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-image" aria-hidden="true"></i> <span class="sr-only">Example of </span>image <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-inbox" aria-hidden="true"></i> <span class="sr-only">Example of </span>inbox</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-industry" aria-hidden="true"></i> <span class="sr-only">Example of </span>industry</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-info" aria-hidden="true"></i> <span class="sr-only">Example of </span>info</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-info-circle" aria-hidden="true"></i> <span class="sr-only">Example of </span>info-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-institution" aria-hidden="true"></i> <span class="sr-only">Example of </span>institution <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-key" aria-hidden="true"></i> <span class="sr-only">Example of </span>key</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-keyboard-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>keyboard-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-language" aria-hidden="true"></i> <span class="sr-only">Example of </span>language</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-laptop" aria-hidden="true"></i> <span class="sr-only">Example of </span>laptop</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-leaf" aria-hidden="true"></i> <span class="sr-only">Example of </span>leaf</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-legal" aria-hidden="true"></i> <span class="sr-only">Example of </span>legal <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lemon-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>lemon-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-level-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>level-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-level-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>level-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-life-bouy" aria-hidden="true"></i> <span class="sr-only">Example of </span>life-bouy <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-life-buoy" aria-hidden="true"></i> <span class="sr-only">Example of </span>life-buoy <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-life-ring" aria-hidden="true"></i> <span class="sr-only">Example of </span>life-ring</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-life-saver" aria-hidden="true"></i> <span class="sr-only">Example of </span>life-saver <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lightbulb-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>lightbulb-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-line-chart" aria-hidden="true"></i> <span class="sr-only">Example of </span>line-chart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-location-arrow" aria-hidden="true"></i> <span class="sr-only">Example of </span>location-arrow</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lock" aria-hidden="true"></i> <span class="sr-only">Example of </span>lock</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-low-vision" aria-hidden="true"></i> <span class="sr-only">Example of </span>low-vision</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-magic" aria-hidden="true"></i> <span class="sr-only">Example of </span>magic</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-magnet" aria-hidden="true"></i> <span class="sr-only">Example of </span>magnet</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mail-forward" aria-hidden="true"></i> <span class="sr-only">Example of </span>mail-forward <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mail-reply" aria-hidden="true"></i> <span class="sr-only">Example of </span>mail-reply <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mail-reply-all" aria-hidden="true"></i> <span class="sr-only">Example of </span>mail-reply-all <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-male" aria-hidden="true"></i> <span class="sr-only">Example of </span>male</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map" aria-hidden="true"></i> <span class="sr-only">Example of </span>map</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map-marker" aria-hidden="true"></i> <span class="sr-only">Example of </span>map-marker</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>map-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map-pin" aria-hidden="true"></i> <span class="sr-only">Example of </span>map-pin</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-map-signs" aria-hidden="true"></i> <span class="sr-only">Example of </span>map-signs</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-meh-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>meh-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-microphone" aria-hidden="true"></i> <span class="sr-only">Example of </span>microphone</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-microphone-slash" aria-hidden="true"></i> <span class="sr-only">Example of </span>microphone-slash</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus" aria-hidden="true"></i> <span class="sr-only">Example of </span>minus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-circle" aria-hidden="true"></i> <span class="sr-only">Example of </span>minus-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>minus-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-square-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>minus-square-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mobile" aria-hidden="true"></i> <span class="sr-only">Example of </span>mobile</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mobile-phone" aria-hidden="true"></i> <span class="sr-only">Example of </span>mobile-phone <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-money" aria-hidden="true"></i> <span class="sr-only">Example of </span>money</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-moon-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>moon-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mortar-board" aria-hidden="true"></i> <span class="sr-only">Example of </span>mortar-board <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-motorcycle" aria-hidden="true"></i> <span class="sr-only">Example of </span>motorcycle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mouse-pointer" aria-hidden="true"></i> <span class="sr-only">Example of </span>mouse-pointer</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-music" aria-hidden="true"></i> <span class="sr-only">Example of </span>music</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-navicon" aria-hidden="true"></i> <span class="sr-only">Example of </span>navicon <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-newspaper-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>newspaper-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-object-group" aria-hidden="true"></i> <span class="sr-only">Example of </span>object-group</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-object-ungroup" aria-hidden="true"></i> <span class="sr-only">Example of </span>object-ungroup</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paint-brush" aria-hidden="true"></i> <span class="sr-only">Example of </span>paint-brush</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paper-plane" aria-hidden="true"></i> <span class="sr-only">Example of </span>paper-plane</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paper-plane-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>paper-plane-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paw" aria-hidden="true"></i> <span class="sr-only">Example of </span>paw</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pencil" aria-hidden="true"></i> <span class="sr-only">Example of </span>pencil</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pencil-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>pencil-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pencil-square-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>pencil-square-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-percent" aria-hidden="true"></i> <span class="sr-only">Example of </span>percent</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-phone" aria-hidden="true"></i> <span class="sr-only">Example of </span>phone</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-phone-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>phone-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-photo" aria-hidden="true"></i> <span class="sr-only">Example of </span>photo <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-picture-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>picture-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pie-chart" aria-hidden="true"></i> <span class="sr-only">Example of </span>pie-chart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plane" aria-hidden="true"></i> <span class="sr-only">Example of </span>plane</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plug" aria-hidden="true"></i> <span class="sr-only">Example of </span>plug</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus" aria-hidden="true"></i> <span class="sr-only">Example of </span>plus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-circle" aria-hidden="true"></i> <span class="sr-only">Example of </span>plus-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>plus-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>plus-square-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-power-off" aria-hidden="true"></i> <span class="sr-only">Example of </span>power-off</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-print" aria-hidden="true"></i> <span class="sr-only">Example of </span>print</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-puzzle-piece" aria-hidden="true"></i> <span class="sr-only">Example of </span>puzzle-piece</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-qrcode" aria-hidden="true"></i> <span class="sr-only">Example of </span>qrcode</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-question" aria-hidden="true"></i> <span class="sr-only">Example of </span>question</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-question-circle" aria-hidden="true"></i> <span class="sr-only">Example of </span>question-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-question-circle-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>question-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-quote-left" aria-hidden="true"></i> <span class="sr-only">Example of </span>quote-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-quote-right" aria-hidden="true"></i> <span class="sr-only">Example of </span>quote-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-random" aria-hidden="true"></i> <span class="sr-only">Example of </span>random</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-recycle" aria-hidden="true"></i> <span class="sr-only">Example of </span>recycle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-refresh" aria-hidden="true"></i> <span class="sr-only">Example of </span>refresh</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-registered" aria-hidden="true"></i> <span class="sr-only">Example of </span>registered</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-remove" aria-hidden="true"></i> <span class="sr-only">Example of </span>remove <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reorder" aria-hidden="true"></i> <span class="sr-only">Example of </span>reorder <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reply" aria-hidden="true"></i> <span class="sr-only">Example of </span>reply</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reply-all" aria-hidden="true"></i> <span class="sr-only">Example of </span>reply-all</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-retweet" aria-hidden="true"></i> <span class="sr-only">Example of </span>retweet</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-road" aria-hidden="true"></i> <span class="sr-only">Example of </span>road</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rocket" aria-hidden="true"></i> <span class="sr-only">Example of </span>rocket</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rss" aria-hidden="true"></i> <span class="sr-only">Example of </span>rss</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rss-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>rss-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-search" aria-hidden="true"></i> <span class="sr-only">Example of </span>search</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-search-minus" aria-hidden="true"></i> <span class="sr-only">Example of </span>search-minus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-search-plus" aria-hidden="true"></i> <span class="sr-only">Example of </span>search-plus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-send" aria-hidden="true"></i> <span class="sr-only">Example of </span>send <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-send-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>send-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-server" aria-hidden="true"></i> <span class="sr-only">Example of </span>server</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share" aria-hidden="true"></i> <span class="sr-only">Example of </span>share</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-alt" aria-hidden="true"></i> <span class="sr-only">Example of </span>share-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-alt-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>share-alt-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>share-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-square-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>share-square-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shield" aria-hidden="true"></i> <span class="sr-only">Example of </span>shield</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ship" aria-hidden="true"></i> <span class="sr-only">Example of </span>ship</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shopping-bag" aria-hidden="true"></i> <span class="sr-only">Example of </span>shopping-bag</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shopping-basket" aria-hidden="true"></i> <span class="sr-only">Example of </span>shopping-basket</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shopping-cart" aria-hidden="true"></i> <span class="sr-only">Example of </span>shopping-cart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sign-in" aria-hidden="true"></i> <span class="sr-only">Example of </span>sign-in</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sign-language" aria-hidden="true"></i> <span class="sr-only">Example of </span>sign-language</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sign-out" aria-hidden="true"></i> <span class="sr-only">Example of </span>sign-out</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-signal" aria-hidden="true"></i> <span class="sr-only">Example of </span>signal</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-signing" aria-hidden="true"></i> <span class="sr-only">Example of </span>signing <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sitemap" aria-hidden="true"></i> <span class="sr-only">Example of </span>sitemap</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sliders" aria-hidden="true"></i> <span class="sr-only">Example of </span>sliders</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-smile-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>smile-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-soccer-ball-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>soccer-ball-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-alpha-asc" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-alpha-asc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-alpha-desc" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-alpha-desc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-amount-asc" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-amount-asc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-amount-desc" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-amount-desc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-asc" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-asc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-desc" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-desc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-down <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-numeric-asc" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-numeric-asc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-numeric-desc" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-numeric-desc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sort-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>sort-up <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-space-shuttle" aria-hidden="true"></i> <span class="sr-only">Example of </span>space-shuttle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-spinner" aria-hidden="true"></i> <span class="sr-only">Example of </span>spinner</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-spoon" aria-hidden="true"></i> <span class="sr-only">Example of </span>spoon</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-square" aria-hidden="true"></i> <span class="sr-only">Example of </span>square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-square-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>square-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star" aria-hidden="true"></i> <span class="sr-only">Example of </span>star</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-half" aria-hidden="true"></i> <span class="sr-only">Example of </span>star-half</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-half-empty" aria-hidden="true"></i> <span class="sr-only">Example of </span>star-half-empty <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-half-full" aria-hidden="true"></i> <span class="sr-only">Example of </span>star-half-full <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-half-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>star-half-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-star-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>star-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sticky-note" aria-hidden="true"></i> <span class="sr-only">Example of </span>sticky-note</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sticky-note-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>sticky-note-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-street-view" aria-hidden="true"></i> <span class="sr-only">Example of </span>street-view</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-suitcase" aria-hidden="true"></i> <span class="sr-only">Example of </span>suitcase</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sun-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>sun-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-support" aria-hidden="true"></i> <span class="sr-only">Example of </span>support <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tablet" aria-hidden="true"></i> <span class="sr-only">Example of </span>tablet</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tachometer" aria-hidden="true"></i> <span class="sr-only">Example of </span>tachometer</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tag" aria-hidden="true"></i> <span class="sr-only">Example of </span>tag</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tags" aria-hidden="true"></i> <span class="sr-only">Example of </span>tags</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tasks" aria-hidden="true"></i> <span class="sr-only">Example of </span>tasks</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-taxi" aria-hidden="true"></i> <span class="sr-only">Example of </span>taxi</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-television" aria-hidden="true"></i> <span class="sr-only">Example of </span>television</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-terminal" aria-hidden="true"></i> <span class="sr-only">Example of </span>terminal</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumb-tack" aria-hidden="true"></i> <span class="sr-only">Example of </span>thumb-tack</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>thumbs-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-o-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>thumbs-o-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>thumbs-o-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>thumbs-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ticket" aria-hidden="true"></i> <span class="sr-only">Example of </span>ticket</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times" aria-hidden="true"></i> <span class="sr-only">Example of </span>times</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times-circle" aria-hidden="true"></i> <span class="sr-only">Example of </span>times-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-times-circle-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>times-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tint" aria-hidden="true"></i> <span class="sr-only">Example of </span>tint</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>toggle-down <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-left" aria-hidden="true"></i> <span class="sr-only">Example of </span>toggle-left <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-off" aria-hidden="true"></i> <span class="sr-only">Example of </span>toggle-off</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-on" aria-hidden="true"></i> <span class="sr-only">Example of </span>toggle-on</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-right" aria-hidden="true"></i> <span class="sr-only">Example of </span>toggle-right <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>toggle-up <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trademark" aria-hidden="true"></i> <span class="sr-only">Example of </span>trademark</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trash" aria-hidden="true"></i> <span class="sr-only">Example of </span>trash</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trash-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>trash-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tree" aria-hidden="true"></i> <span class="sr-only">Example of </span>tree</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trophy" aria-hidden="true"></i> <span class="sr-only">Example of </span>trophy</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-truck" aria-hidden="true"></i> <span class="sr-only">Example of </span>truck</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tty" aria-hidden="true"></i> <span class="sr-only">Example of </span>tty</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tv" aria-hidden="true"></i> <span class="sr-only">Example of </span>tv <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-umbrella" aria-hidden="true"></i> <span class="sr-only">Example of </span>umbrella</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-universal-access" aria-hidden="true"></i> <span class="sr-only">Example of </span>universal-access</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-university" aria-hidden="true"></i> <span class="sr-only">Example of </span>university</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-unlock" aria-hidden="true"></i> <span class="sr-only">Example of </span>unlock</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-unlock-alt" aria-hidden="true"></i> <span class="sr-only">Example of </span>unlock-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-unsorted" aria-hidden="true"></i> <span class="sr-only">Example of </span>unsorted <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-upload" aria-hidden="true"></i> <span class="sr-only">Example of </span>upload</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user" aria-hidden="true"></i> <span class="sr-only">Example of </span>user</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-plus" aria-hidden="true"></i> <span class="sr-only">Example of </span>user-plus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-secret" aria-hidden="true"></i> <span class="sr-only">Example of </span>user-secret</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-times" aria-hidden="true"></i> <span class="sr-only">Example of </span>user-times</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-users" aria-hidden="true"></i> <span class="sr-only">Example of </span>users</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-video-camera" aria-hidden="true"></i> <span class="sr-only">Example of </span>video-camera</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-control-phone" aria-hidden="true"></i> <span class="sr-only">Example of </span>volume-control-phone</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>volume-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-off" aria-hidden="true"></i> <span class="sr-only">Example of </span>volume-off</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>volume-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-warning" aria-hidden="true"></i> <span class="sr-only">Example of </span>warning <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair" aria-hidden="true"></i> <span class="sr-only">Example of </span>wheelchair</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair-alt" aria-hidden="true"></i> <span class="sr-only">Example of </span>wheelchair-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wifi" aria-hidden="true"></i> <span class="sr-only">Example of </span>wifi</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wrench" aria-hidden="true"></i> <span class="sr-only">Example of </span>wrench</a></div>
			</div>
		</section>
		<section id="hand1">
			<h2 class="page-header">辅助功能图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i> <span class="sr-only">Example of </span>american-sign-language-interpreting</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-asl-interpreting" aria-hidden="true"></i> <span class="sr-only">Example of </span>asl-interpreting <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-assistive-listening-systems" aria-hidden="true"></i> <span class="sr-only">Example of </span>assistive-listening-systems</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-audio-description" aria-hidden="true"></i> <span class="sr-only">Example of </span>audio-description</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-blind" aria-hidden="true"></i> <span class="sr-only">Example of </span>blind</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-braille" aria-hidden="true"></i> <span class="sr-only">Example of </span>braille</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc" aria-hidden="true"></i> <span class="sr-only">Example of </span>cc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deaf" aria-hidden="true"></i> <span class="sr-only">Example of </span>deaf</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deafness" aria-hidden="true"></i> <span class="sr-only">Example of </span>deafness <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hard-of-hearing" aria-hidden="true"></i> <span class="sr-only">Example of </span>hard-of-hearing <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-low-vision" aria-hidden="true"></i> <span class="sr-only">Example of </span>low-vision</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-question-circle-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>question-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sign-language" aria-hidden="true"></i> <span class="sr-only">Example of </span>sign-language</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-signing" aria-hidden="true"></i> <span class="sr-only">Example of </span>signing <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tty" aria-hidden="true"></i> <span class="sr-only">Example of </span>tty</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-universal-access" aria-hidden="true"></i> <span class="sr-only">Example of </span>universal-access</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-volume-control-phone" aria-hidden="true"></i> <span class="sr-only">Example of </span>volume-control-phone</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair" aria-hidden="true"></i> <span class="sr-only">Example of </span>wheelchair</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair-alt" aria-hidden="true"></i> <span class="sr-only">Example of </span>wheelchair-alt</a></div>
			</div>
		</section>
		<section id="hand2">
			<h2 class="page-header">手形图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-grab-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-grab-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-lizard-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-lizard-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-o-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-left" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-o-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-right" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-o-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-o-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-paper-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-paper-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-peace-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-peace-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-pointer-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-pointer-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-rock-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-rock-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-scissors-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-scissors-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-spock-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-spock-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-stop-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>hand-stop-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>thumbs-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-o-down" aria-hidden="true"></i> <span class="sr-only">Example of </span>thumbs-o-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>thumbs-o-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-thumbs-up" aria-hidden="true"></i> <span class="sr-only">Example of </span>thumbs-up</a></div>
			</div>
		</section>
		<section id="transportation">
			<h2 class="page-header">交通运输图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ambulance" aria-hidden="true"></i> <span class="sr-only">Example of </span>ambulance</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-automobile" aria-hidden="true"></i> <span class="sr-only">Example of </span>automobile <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bicycle" aria-hidden="true"></i> <span class="sr-only">Example of </span>bicycle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bus" aria-hidden="true"></i> <span class="sr-only">Example of </span>bus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cab" aria-hidden="true"></i> <span class="sr-only">Example of </span>cab <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-car" aria-hidden="true"></i> <span class="sr-only">Example of </span>car</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fighter-jet" aria-hidden="true"></i> <span class="sr-only">Example of </span>fighter-jet</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-motorcycle" aria-hidden="true"></i> <span class="sr-only">Example of </span>motorcycle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plane" aria-hidden="true"></i> <span class="sr-only">Example of </span>plane</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rocket" aria-hidden="true"></i> <span class="sr-only">Example of </span>rocket</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ship" aria-hidden="true"></i> <span class="sr-only">Example of </span>ship</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-space-shuttle" aria-hidden="true"></i> <span class="sr-only">Example of </span>space-shuttle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-subway" aria-hidden="true"></i> <span class="sr-only">Example of </span>subway</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-taxi" aria-hidden="true"></i> <span class="sr-only">Example of </span>taxi</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-train" aria-hidden="true"></i> <span class="sr-only">Example of </span>train</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-truck" aria-hidden="true"></i> <span class="sr-only">Example of </span>truck</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair" aria-hidden="true"></i> <span class="sr-only">Example of </span>wheelchair</a></div>
			</div>
		</section>
		<section id="gender">
			<h2 class="page-header">性别图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-genderless" aria-hidden="true"></i> <span class="sr-only">Example of </span>genderless</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-intersex" aria-hidden="true"></i> <span class="sr-only">Example of </span>intersex <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars" aria-hidden="true"></i> <span class="sr-only">Example of </span>mars</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars-double" aria-hidden="true"></i> <span class="sr-only">Example of </span>mars-double</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars-stroke" aria-hidden="true"></i> <span class="sr-only">Example of </span>mars-stroke</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars-stroke-h" aria-hidden="true"></i> <span class="sr-only">Example of </span>mars-stroke-h</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mars-stroke-v" aria-hidden="true"></i> <span class="sr-only">Example of </span>mars-stroke-v</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mercury" aria-hidden="true"></i> <span class="sr-only">Example of </span>mercury</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-neuter" aria-hidden="true"></i> <span class="sr-only">Example of </span>neuter</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-transgender" aria-hidden="true"></i> <span class="sr-only">Example of </span>transgender</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-transgender-alt" aria-hidden="true"></i> <span class="sr-only">Example of </span>transgender-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-venus" aria-hidden="true"></i> <span class="sr-only">Example of </span>venus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-venus-double" aria-hidden="true"></i> <span class="sr-only">Example of </span>venus-double</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-venus-mars" aria-hidden="true"></i> <span class="sr-only">Example of </span>venus-mars</a></div>
			</div>
		</section>
		<section id="file-type">
			<h2 class="page-header">文件类型图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file" aria-hidden="true"></i> <span class="sr-only">Example of </span>file</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-archive-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-archive-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-audio-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-audio-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-code-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-code-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-excel-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-excel-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-image-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-image-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-movie-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-movie-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-pdf-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-pdf-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-photo-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-photo-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-picture-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-picture-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-powerpoint-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-powerpoint-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-sound-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-sound-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-text" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-text</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-text-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-text-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-video-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-video-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-word-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-word-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-zip-o" aria-hidden="true"></i> <span class="sr-only">Example of </span>file-zip-o <span class="text-muted">(alias)</span></a></div>
			</div>
		</section>
		<section id="spinner">
			<h2 class="page-header">可旋转图标</h2>
			<div class="alert alert-success">
				<ul class="fa-ul">
					<li>
						<i class="fa fa-info-circle fa-lg fa-li"></i>
						结合<code>fa-spin</code>一起使用，才能让这些图标有更优异的表现。查看<a href="http://fontawesome.io/examples/#animated" class="alert-link" target="_blank">旋转图标的例子</a>。
					</li>
				</ul>
			</div>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-o-notch"></i> circle-o-notch</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cog"></i> cog</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gear"></i> gear <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-refresh"></i> refresh</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-spinner"></i> spinner</a></div>
			</div>
		</section>
		<section id="form-control">
			<h2 class="page-header">表单图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-square"></i> check-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-check-square-o"></i> check-square-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle"></i> circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-circle-o"></i> circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dot-circle-o"></i> dot-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-square"></i> minus-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-minus-square-o"></i> minus-square-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square"></i> plus-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square-o"></i> plus-square-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-square"></i> square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-square-o"></i> square-o</a></div>
			</div>
		</section>
		<section id="payment">
			<h2 class="page-header">支付图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-amex"></i> cc-amex</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-diners-club"></i> cc-diners-club</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-discover"></i> cc-discover</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-jcb"></i> cc-jcb</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-mastercard"></i> cc-mastercard</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-paypal"></i> cc-paypal</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-stripe"></i> cc-stripe</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-visa"></i> cc-visa</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-credit-card"></i> credit-card</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-credit-card-alt"></i> credit-card-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-wallet"></i> google-wallet</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paypal"></i> paypal</a></div>
			</div>
		</section>
		<section id="chart">
			<h2 class="page-header">图表图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-area-chart"></i> area-chart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bar-chart"></i> bar-chart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bar-chart-o"></i> bar-chart-o <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-line-chart"></i> line-chart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pie-chart"></i> pie-chart</a></div>
			</div>
		</section>
		<section id="currency">
			<h2 class="page-header">货币图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bitcoin"></i> bitcoin <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-btc"></i> btc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cny"></i> cny <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dollar"></i> dollar <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eur"></i> eur</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-euro"></i> euro <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gbp"></i> gbp</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gg"></i> gg</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gg-circle"></i> gg-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ils"></i> ils</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-inr"></i> inr</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-jpy"></i> jpy</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-krw"></i> krw</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-money"></i> money</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rmb"></i> rmb <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rouble"></i> rouble <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rub"></i> rub</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ruble"></i> ruble <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rupee"></i> rupee <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shekel"></i> shekel <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sheqel"></i> sheqel <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-try"></i> try</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-turkish-lira"></i> turkish-lira <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-usd"></i> usd</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-won"></i> won <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yen"></i> yen <span class="text-muted">(alias)</span></a></div>
			</div>
		</section>
		<section id="text-editor">
			<h2 class="page-header">文本编辑图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-align-center"></i> align-center</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-align-justify"></i> align-justify</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-align-left"></i> align-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-align-right"></i> align-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bold"></i> bold</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chain"></i> chain <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chain-broken"></i> chain-broken</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-clipboard"></i> clipboard</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-columns"></i> columns</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-copy"></i> copy <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cut"></i> cut <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dedent"></i> dedent <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eraser"></i> eraser</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file"></i> file</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-o"></i> file-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-text"></i> file-text</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-file-text-o"></i> file-text-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-files-o"></i> files-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-floppy-o"></i> floppy-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-font"></i> font</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-header"></i> header</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-indent"></i> indent</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-italic"></i> italic</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-link"></i> link</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-list"></i> list</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-list-alt"></i> list-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-list-ol"></i> list-ol</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-list-ul"></i> list-ul</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-outdent"></i> outdent</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paperclip"></i> paperclip</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paragraph"></i> paragraph</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paste"></i> paste <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-repeat"></i> repeat</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rotate-left"></i> rotate-left <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rotate-right"></i> rotate-right <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-save"></i> save <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-scissors"></i> scissors</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-strikethrough"></i> strikethrough</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-subscript"></i> subscript</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-superscript"></i> superscript</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-table"></i> table</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-text-height"></i> text-height</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-text-width"></i> text-width</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-th"></i> th</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-th-large"></i> th-large</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-th-list"></i> th-list</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-underline"></i> underline</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-undo"></i> undo</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-unlink"></i> unlink <span class="text-muted">(alias)</span></a></div>
			</div>
		</section>
		<section id="directional">
			<h2 class="page-header">指示方向图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-double-down"></i> angle-double-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-double-left"></i> angle-double-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-double-right"></i> angle-double-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-double-up"></i> angle-double-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-down"></i> angle-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-left"></i> angle-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-right"></i> angle-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angle-up"></i> angle-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-down"></i> arrow-circle-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-left"></i> arrow-circle-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-o-down"></i> arrow-circle-o-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-o-left"></i> arrow-circle-o-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-o-right"></i> arrow-circle-o-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-o-up"></i> arrow-circle-o-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-right"></i> arrow-circle-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-circle-up"></i> arrow-circle-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-down"></i> arrow-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-left"></i> arrow-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-right"></i> arrow-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrow-up"></i> arrow-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows"></i> arrows</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-alt"></i> arrows-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-h"></i> arrows-h</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-v"></i> arrows-v</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-down"></i> caret-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-left"></i> caret-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-right"></i> caret-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-down"></i> caret-square-o-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-left"></i> caret-square-o-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-right"></i> caret-square-o-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-square-o-up"></i> caret-square-o-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-caret-up"></i> caret-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-circle-down"></i> chevron-circle-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-circle-left"></i> chevron-circle-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-circle-right"></i> chevron-circle-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-circle-up"></i> chevron-circle-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-down"></i> chevron-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-left"></i> chevron-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-right"></i> chevron-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chevron-up"></i> chevron-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-exchange"></i> exchange</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-down"></i> hand-o-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-left"></i> hand-o-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-right"></i> hand-o-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hand-o-up"></i> hand-o-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-long-arrow-down"></i> long-arrow-down</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-long-arrow-left"></i> long-arrow-left</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-long-arrow-right"></i> long-arrow-right</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-long-arrow-up"></i> long-arrow-up</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-down"></i> toggle-down <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-left"></i> toggle-left <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-right"></i> toggle-right <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-toggle-up"></i> toggle-up <span class="text-muted">(alias)</span></a></div>
			</div>
		</section>
		<section id="video-player">
			<h2 class="page-header">视频播放图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-arrows-alt"></i> arrows-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-backward"></i> backward</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-compress"></i> compress</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-eject"></i> eject</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-expand"></i> expand</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fast-backward"></i> fast-backward</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fast-forward"></i> fast-forward</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-forward"></i> forward</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pause"></i> pause</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pause-circle"></i> pause-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pause-circle-o"></i> pause-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-play"></i> play</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-play-circle"></i> play-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-play-circle-o"></i> play-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-random"></i> random</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-step-backward"></i> step-backward</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-step-forward"></i> step-forward</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stop"></i> stop</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stop-circle"></i> stop-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stop-circle-o"></i> stop-circle-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-youtube-play"></i> youtube-play</a></div>
			</div>
		</section>
		<section id="brand">
			<h2 class="page-header">著名商标图标</h2>
			<div class="alert alert-warning">
				<h4><i class="fa fa-warning"></i> 特别提醒!</h4>
				Adblock Plus 插件会通过设置“Remove Social Media Buttons”来移除 Font Awesome 的这些标志图标。 然而我们并不会用一些特殊方法来强行显示。如果您认为这是一个错误，请 向 <a href="https://adblockplus.org/en/bugs" class="alert-link" target="_blank">Adblock Plus</a>  报告这个问题。 在Adblock Plus修复这个问题之前，您需要自行修改这些图标的类名来解决这个问题。
			</div>
			<div class="row fontawesome-icon-list margin-bottom-lg">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-500px"></i> 500px</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-adn"></i> adn</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-amazon"></i> amazon</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-android"></i> android</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-angellist"></i> angellist</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-apple"></i> apple</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-behance"></i> behance</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-behance-square"></i> behance-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bitbucket"></i> bitbucket</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bitbucket-square"></i> bitbucket-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bitcoin"></i> bitcoin <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-black-tie"></i> black-tie</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bluetooth"></i> bluetooth</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-bluetooth-b"></i> bluetooth-b</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-btc"></i> btc</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-buysellads"></i> buysellads</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-amex"></i> cc-amex</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-diners-club"></i> cc-diners-club</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-discover"></i> cc-discover</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-jcb"></i> cc-jcb</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-mastercard"></i> cc-mastercard</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-paypal"></i> cc-paypal</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-stripe"></i> cc-stripe</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-cc-visa"></i> cc-visa</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-chrome"></i> chrome</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-codepen"></i> codepen</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-codiepie"></i> codiepie</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-connectdevelop"></i> connectdevelop</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-contao"></i> contao</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-css3"></i> css3</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dashcube"></i> dashcube</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-delicious"></i> delicious</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-deviantart"></i> deviantart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-digg"></i> digg</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dribbble"></i> dribbble</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-dropbox"></i> dropbox</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-drupal"></i> drupal</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-edge"></i> edge</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-empire"></i> empire</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-expeditedssl"></i> expeditedssl</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-facebook"></i> facebook</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-facebook-f"></i> facebook-f <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-facebook-official"></i> facebook-official</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-facebook-square"></i> facebook-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-firefox"></i> firefox</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-flickr"></i> flickr</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fonticons"></i> fonticons</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-fort-awesome"></i> fort-awesome</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-forumbee"></i> forumbee</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-foursquare"></i> foursquare</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ge"></i> ge <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-get-pocket"></i> get-pocket</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gg"></i> gg</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gg-circle"></i> gg-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-git"></i> git</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-git-square"></i> git-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-github"></i> github</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-github-alt"></i> github-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-github-square"></i> github-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gittip"></i> gittip <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google"></i> google</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-plus"></i> google-plus</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-plus-square"></i> google-plus-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-google-wallet"></i> google-wallet</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-gratipay"></i> gratipay</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hacker-news"></i> hacker-news</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-houzz"></i> houzz</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-html5"></i> html5</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-instagram"></i> instagram</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-internet-explorer"></i> internet-explorer</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ioxhost"></i> ioxhost</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-joomla"></i> joomla</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-jsfiddle"></i> jsfiddle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lastfm"></i> lastfm</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-lastfm-square"></i> lastfm-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-leanpub"></i> leanpub</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-linkedin"></i> linkedin</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-linkedin-square"></i> linkedin-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-linux"></i> linux</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-maxcdn"></i> maxcdn</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-meanpath"></i> meanpath</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-medium"></i> medium</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-mixcloud"></i> mixcloud</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-modx"></i> modx</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-odnoklassniki"></i> odnoklassniki</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-odnoklassniki-square"></i> odnoklassniki-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-opencart"></i> opencart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-openid"></i> openid</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-opera"></i> opera</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-optin-monster"></i> optin-monster</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pagelines"></i> pagelines</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-paypal"></i> paypal</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pied-piper"></i> pied-piper</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pied-piper-alt"></i> pied-piper-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pinterest"></i> pinterest</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pinterest-p"></i> pinterest-p</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-pinterest-square"></i> pinterest-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-product-hunt"></i> product-hunt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-qq"></i> qq</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ra"></i> ra <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-rebel"></i> rebel</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reddit"></i> reddit</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reddit-alien"></i> reddit-alien</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-reddit-square"></i> reddit-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-renren"></i> renren</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-safari"></i> safari</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-scribd"></i> scribd</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-sellsy"></i> sellsy</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-alt"></i> share-alt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-share-alt-square"></i> share-alt-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-shirtsinbulk"></i> shirtsinbulk</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-simplybuilt"></i> simplybuilt</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-skyatlas"></i> skyatlas</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-skype"></i> skype</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-slack"></i> slack</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-slideshare"></i> slideshare</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-soundcloud"></i> soundcloud</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-spotify"></i> spotify</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stack-exchange"></i> stack-exchange</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stack-overflow"></i> stack-overflow</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-steam"></i> steam</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-steam-square"></i> steam-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stumbleupon"></i> stumbleupon</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stumbleupon-circle"></i> stumbleupon-circle</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tencent-weibo"></i> tencent-weibo</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-trello"></i> trello</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tripadvisor"></i> tripadvisor</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tumblr"></i> tumblr</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-tumblr-square"></i> tumblr-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-twitch"></i> twitch</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-twitter"></i> twitter</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-twitter-square"></i> twitter-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-usb"></i> usb</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-viacoin"></i> viacoin</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vimeo"></i> vimeo</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vimeo-square"></i> vimeo-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vine"></i> vine</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-vk"></i> vk</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wechat"></i> wechat <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-weibo"></i> weibo</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-weixin"></i> weixin</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-whatsapp"></i> whatsapp</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wikipedia-w"></i> wikipedia-w</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-windows"></i> windows</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wordpress"></i> wordpress</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-xing"></i> xing</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-xing-square"></i> xing-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-y-combinator"></i> y-combinator</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-y-combinator-square"></i> y-combinator-square <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yahoo"></i> yahoo</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yc"></i> yc <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yc-square"></i> yc-square <span class="text-muted">(alias)</span></a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-yelp"></i> yelp</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-youtube"></i> youtube</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-youtube-play"></i> youtube-play</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-youtube-square"></i> youtube-square</a></div>
			</div>
			<div class="alert alert-success">
				<ul class="margin-bottom-none padding-left-lg">
					<li>所有标志图标都分别是其所有者的注册商标。</li>
					<li>使用这些商标并不代表Font Awesome拥有它们。</li>
				</ul>
			</div>
		</section>
		<section id="medical">
			<h2 class="page-header">医疗图标</h2>
			<div class="row fontawesome-icon-list">
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-ambulance"></i> ambulance</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-h-square"></i> h-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heart"></i> heart</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heart-o"></i> heart-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-heartbeat"></i> heartbeat</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-hospital-o"></i> hospital-o</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-medkit"></i> medkit</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-plus-square"></i> plus-square</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-stethoscope"></i> stethoscope</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-user-md"></i> user-md</a></div>
				<div class="fa-hover col-md-3 col-sm-4"><a><i class="fa fa-wheelchair"></i> wheelchair</a></div>
			</div>
		</section>
	</div>
</body>