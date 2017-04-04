/* -----------H-ui前端框架-------------
* H-ui.admin.js v2.3.1
* http://www.h-ui.net/
* Created & Modified by guojunhui
* Date modified 15:42 2016.02.28
*
* Copyright 2013-2016 北京颖杰联创科技有限公司 All rights reserved.
* Licensed under MIT license.
* http://opensource.org/licenses/MIT
*
*/
var num=0,oUl=$("#min_title_list"),hide_nav=$("#Hui-tabNav");

/*获取顶部选项卡总长度*/
function tabNavallwidth(){
	var taballwidth=0,
		$tabNav = hide_nav.find(".acrossTab"),
		$tabNavWp = hide_nav.find(".Hui-tabNav-wp"),
		$tabNavitem = hide_nav.find(".acrossTab li"),
		$tabNavmore =hide_nav.find(".Hui-tabNav-more");
	if (!$tabNav[0]){return}
	$tabNavitem.each(function(index, element) {
        taballwidth+=Number(parseFloat($(this).width()+60))
    });
	$tabNav.width(taballwidth+25);
	var w = $tabNavWp.width();
	if(taballwidth+25>w){
		$tabNavmore.show()}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0})
	}
}

/*左侧菜单响应式*/
function Huiasidedisplay(){
	if($(window).width()>=768){
		$(".Hui-aside").show()
	} 
}
function getskincookie(){
	var v = getCookie("Huiskin");
	var hrefStr=$("#skin").attr("href");
	if(v==null||v==""){
		v="default";
	}
	if(hrefStr!=undefined){
		var hrefRes=hrefStr.substring(0,hrefStr.lastIndexOf('skin/'))+'skin/'+v+'/skin.css';
		$("#skin").attr("href",hrefRes);
	}
}
function Hui_admin_tab(obj){
	if($(obj).attr('_href')){
		var bStop=false;
		var bStopIndex=0;
		var _href=$(obj).attr('_href');
		var _titleName=$(obj).attr("data-title");
		var topWindow=$(window.parent.document);
		var show_navLi=topWindow.find("#min_title_list li");
		show_navLi.each(function() {
			if($(this).find('span').attr("data-href")==_href){
				bStop=true;
				bStopIndex=show_navLi.index($(this));
				return false;
			}
		});
		if(!bStop){
			creatIframe(_href,_titleName);
			min_titleList();
		}
		else{
			show_navLi.removeClass("active").eq(bStopIndex).addClass("active");
			var iframe_box=topWindow.find("#iframe_box");
			iframe_box.find(".show_iframe").hide().eq(bStopIndex).show().find("iframe").attr("src",_href);
		}
	}

}
function min_titleList(){
	var topWindow=$(window.parent.document);
	var show_nav=topWindow.find("#min_title_list");
	var aLi=show_nav.find("li");
};
function creatIframe(href,titleName){
	var topWindow=$(window.parent.document);
	var show_nav=topWindow.find('#min_title_list');
	show_nav.find('li').removeClass("active");
	var iframe_box=topWindow.find('#iframe_box');
	show_nav.append('<li class="active"><span data-href="'+href+'">'+titleName+'</span><i></i><em></em></li>');
	var taballwidth=0,
		$tabNav = topWindow.find(".acrossTab"),
		$tabNavWp = topWindow.find(".Hui-tabNav-wp"),
		$tabNavitem = topWindow.find(".acrossTab li"),
		$tabNavmore =topWindow.find(".Hui-tabNav-more");
	if (!$tabNav[0]){return}
	$tabNavitem.each(function(index, element) {
        taballwidth+=Number(parseFloat($(this).width()+60))
    });
	$tabNav.width(taballwidth+25);
	var w = $tabNavWp.width();
	if(taballwidth+25>w){
		$tabNavmore.show()}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0})
	}
	var iframeBox=iframe_box.find('.show_iframe');
	iframeBox.hide();
	iframe_box.append('<div class="show_iframe"><div class="loading"></div><iframe frameborder="0" src='+href+'></iframe></div>');
	var showBox=iframe_box.find('.show_iframe:visible');
	showBox.find('iframe').load(function(){
		showBox.find('.loading').hide();
	});
}
function removeIframe(){
	var topWindow = $(window.parent.document);
	var iframe = topWindow.find('#iframe_box .show_iframe');
	var tab = topWindow.find(".acrossTab li");
	var showTab = topWindow.find(".acrossTab li.active");
	var showBox=topWindow.find('.show_iframe:visible');
	var i = showTab.index();
	tab.eq(i-1).addClass("active");
	iframe.eq(i-1).show();
	tab.eq(i).remove();
	iframe.eq(i).remove();
}
/*弹出层*/
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
function layer_show(title,url,w,h,oparent,endCallBack,options){
	var opts;
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	if(typeof endCallBack=='function'){
		opts={
			//skin: 'layui-layer-lan',
			type: 2,
			area: [w+'px', h +'px'],
			fix: false, //不固定
			maxmin: true,
			shadeClose:true,
			shade:0.4,
			title: title,
			end:endCallBack,
			content: url
		};
	}else{
		opts={
			//skin: 'layui-layer-lan',
			type: 2,
			area: [w+'px', h +'px'],
			fix: false, //不固定
			maxmin: true,
			shadeClose:true,
			shade:0.4,
			title: title,
			content: url
		};
	}
	opts= $.extend(opts,options);
	if (title == null || title == '') {
		opts.title=false;
	};
	/*if (url == null || url == '') {
	 url="404";
	 };*/
	if(oparent!=undefined&&oparent!=null&&oparent!=''){
		oparent.layer.open(opts);
	}else{
		layer.open(opts);
	}
}
//弹出即全屏
function layer_show_full(title,url,w,h,oparent,endCallBack,options){
	var opts;
	if (w == null || w == '') {
		w=800;
	};
	if (h == null || h == '') {
		h=($(window).height() - 50);
	};
	if(typeof endCallBack=='function'){
		opts={
			//skin: 'layui-layer-lan',
			type: 2,
			area: [w+'px', h +'px'],
			fix: false, //不固定
			maxmin: true,
			shadeClose:true,
			shade:0.4,
			title: title,
			end:endCallBack,
			content: url
		};
	}else{
		opts={
			//skin: 'layui-layer-lan',
			type: 2,
			area: [w+'px', h +'px'],
			fix: false, //不固定
			maxmin: true,
			shadeClose:true,
			shade:0.4,
			title: title,
			content: url
		};
	}
	opts= $.extend(opts,options);
	if (title == null || title == '') {
		opts.title=false;
	};
	/*if (url == null || url == '') {
		url="404";
	};*/
	var index;
	if(oparent!=undefined&&oparent!=null&&oparent!=''){
		index=oparent.layer.open(opts);
	}else{
		index=layer.open(opts);
	}
	layer.full(index);
}
/*关闭弹出框口*/
function layer_close(){
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}
$(function(){
	getskincookie();
	//layer.config({extend: 'extend/layer.ext.js'});
	Huiasidedisplay();
	var resizeID;
	$(window).resize(function(){
		clearTimeout(resizeID);
		resizeID = setTimeout(function(){
			Huiasidedisplay();
		},500);
	});
	
	$(".Hui-nav-toggle").click(function(){
		$(".Hui-aside").slideToggle();
	});
	$(".Hui-aside").on("click",".menu_dropdown dd li a",function(){
		if($(window).width()<768){
			$(".Hui-aside").slideToggle();
		}
	});
	/*左侧菜单*/
	$.Huifold(".menu_dropdown dl dt",".menu_dropdown dl dd","fast",1,"click");
	/*选项卡导航*/

	$(".Hui-aside").on("click",".menu_dropdown a",function(){
		Hui_admin_tab(this);
	});
	
	$(document).on("click","#min_title_list li",function(){
		var bStopIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		$("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();
	});
	$(document).on("click","#min_title_list li i",function(){
		var aCloseIndex=$(this).parents("li").index();
		$(this).parent().remove();
		$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();	
		num==0?num=0:num--;
		tabNavallwidth();
	});
	$(document).on("dblclick","#min_title_list li",function(){
		var aCloseIndex=$(this).index();
		var iframe_box=$("#iframe_box");
		if(aCloseIndex>0){
			$(this).remove();
			$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();	
			num==0?num=0:num--;
			$("#min_title_list li").removeClass("active").eq(aCloseIndex-1).addClass("active");
			iframe_box.find(".show_iframe").hide().eq(aCloseIndex-1).show();
			tabNavallwidth();
		}else{
			return false;
		}
	});
	tabNavallwidth();
	
	$('#js-tabNav-next').click(function(){
		num==oUl.find('li').length-1?num=oUl.find('li').length-1:num++;
		toNavPos();
	});
	$('#js-tabNav-prev').click(function(){
		num==0?num=0:num--;
		toNavPos();
	});
	
	function toNavPos(){
		oUl.stop().animate({'left':-num*100},100);
	}
	
	/*换肤*/
	$("#Hui-skin .dropDown-menu a").click(function(){
		var v = $(this).attr("data-val");
		setCookie("Huiskin", v);
		var hrefStr=$("#skin").attr("href");
		var hrefRes=hrefStr.substring(0,hrefStr.lastIndexOf('skin/'))+'skin/'+v+'/skin.css';
		
		$(window.frames.document).contents().find("#skin").attr("href",hrefRes);
		//$("#skin").attr("href",hrefResd);
	});

	$(document).on('contextmenu','#min_title_list li',function(e){
		$('#rightframe_menu').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		$(this).trigger("click");//右键tab页时，触发click事件，激活该tab页
		var curIndex =$(this).index();
		$('#rightframe_menu').data("curIndex",curIndex);

		return false;
	});
	tabCloseEven();
});




//右键菜单事件
function tabCloseEven(){
	//右键菜单---关闭当前
	$('#rightframe_menu_mm-tabclose').click(function(){
		var curIndex = $('#rightframe_menu').data("curIndex");

		if(curIndex>0){
			cloaseTab(curIndex);
		}
		$('#rightframe_menu').menu('hide');
	});
	//全部关闭
	$('#rightframe_menu_mm-tabcloseall').click(function(){
		//$('#min_title_list li').each(function(i,n){});
		//移除tab
		$('#min_title_list').find('li').eq(0).siblings().remove();
		//移除内容
		$('#iframe_box').find('.show_iframe').eq(0).siblings().remove();

		$("#min_title_list li").removeClass("active").eq(0-1).addClass("active");
		$("#iframe_box").find(".show_iframe").eq(0).show();
		tabNavallwidth();

		//右键菜单隐藏
		$('#rightframe_menu').menu('hide');
	});
	//关闭除当前之外的TAB
	$('#rightframe_menu_mm-tabcloseother').click(function(){
		var curIndex = $('#rightframe_menu').data("curIndex");
		var curTitle=	$("#min_title_list li").eq(curIndex).find("span").text();
		$("#min_title_list li").each(function(i,obj){
			var tempTitle=$(this).find("span").text();
			if(tempTitle!='我的桌面'&&tempTitle!=curTitle){
				$(this).remove();
			}
		});
		$("#iframe_box").find(".show_iframe").eq(0).siblings().each(function(i,obj){
			if($(this).is(":visible")){

			}else{
				$(this).remove();
			}
		});
		//alert(t);
	});
	//关闭当前右侧的TAB
	$('#rightframe_menu_mm-tabcloseright').click(function(){
		var curIndex = $('#rightframe_menu').data("curIndex");
		$("#min_title_list li").each(function(i,obj){
			if(i>curIndex){
				$(this).remove();
			}
		});
		$("#iframe_box").find(".show_iframe").each(function(i,obj){
			if(i>curIndex){
				$(this).remove();
			}
		});
	});
	//关闭当前左侧的TAB
	$('#rightframe_menu_mm-tabcloseleft').click(function(){
		var curIndex = $('#rightframe_menu').data("curIndex");
		$("#min_title_list li").each(function(i,obj){
			if(i<curIndex&&i!=0){
				$(this).remove();
			}
		});
		$("#iframe_box").find(".show_iframe").each(function(i,obj){
			if(i<curIndex&&i!=0){
				$(this).remove();
			}
		});
	});

	//退出
	$("#rightframe_menu_mm-exit").click(function(){
		$('#rightframe_menu').menu('hide');
	})

}
//关闭第i个tab
function cloaseTab(i){
	var aCloseIndex=i;
	var iframe_box=$("#iframe_box");
	if(aCloseIndex>0){//第一个选项卡不关闭
		//移除tab
		$('#min_title_list').find('li').eq(aCloseIndex).remove();
		//移除内容
		$('#iframe_box').find('.show_iframe').eq(aCloseIndex).remove();
		//num==0?num=0:num--;
		$("#min_title_list li").removeClass("active").eq(aCloseIndex-1).addClass("active");
		iframe_box.find(".show_iframe").hide().eq(aCloseIndex-1).show();
		tabNavallwidth();
	}else{
		return false;
	}
	$('#rightframe_menu').menu('hide');
}