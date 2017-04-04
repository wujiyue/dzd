//================================================================
// 常量
//================================================================
// 手机验证匹配
var common_mobile_reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;

// 电话号码：0XXX-(7-8位号码)-分机号，区号和分机号可不填，
var common_mobile_isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}(-[0-9]{1,})?$/;

// 个人用户类型
var common_type_geren = 0;

// 企业用户类型
var common_type_com = 1;

// 企业对应服务单位列表
var common_unit_map = {};

var COMMON_SELECT_EMPTY = '<option value="" selected disabled>请选择</option>';

// .container 设置了 overflow 属性, 导致 Android 手机下输入框获取焦点时, 输入法挡住输入框的 bug
// 相关 issue: https://github.com/weui/weui/issues/15
// 解决方法:
// 0. .container 去掉 overflow 属性, 但此 demo 下会引发别的问题
// 1. 参考 http://stackoverflow.com/questions/23757345/android-does-not-correctly-scroll-on-input-focus-if-not-body-element
//    Android 手机下, input 或 textarea 元素聚焦时, 主动滚一把
$(function(){
	if (/Android/gi.test(navigator.userAgent)) {
	    window.addEventListener('resize', function () {
	        if (document.activeElement.tagName == 'INPUT' || document.activeElement.tagName == 'TEXTAREA') {
	            window.setTimeout(function () {
	                document.activeElement.scrollIntoViewIfNeeded();
	            }, 0);
	        }
	    });
	}
});

// 企业选择列表
var common_sel_company = null;
var common_sel_flag = false;
var common_buyerType = null;
var common_serviceUnit = null;
/**
 * 获取该用户所绑定的企业信息
 */
function common_companySel_fun(fun,fun2,fun3){
	common_buyerType = $('#common_buyerType').val();
	common_sel_company = $('#common_sel_company');
	common_tax = $('#common_tax');
	common_serviceUnit = $('#sel_serviceUnit');
	// 企业用户类型
	if (common_buyerType == common_type_com) {
		//var str = '<option value="'+$('#common_uid').val()+'" tax="'+common_tax.val()+'" selected>'+$('#common_name').val()+'</option>';
		//common_sel_company.html(str);
		
		selectToInput($('#common_uid').val(),common_tax.val(),$('#common_name').val());
		
		// 获取服务单位
		common_get_unit(fun,fun2,common_sel_company.val(),1);
	}
	// 个人用户绑定企业用户的情况
	else{
		common_sel_flag = true;
        $.ajax({
			url : 'user/center/getCompany.action',
			success : function(temp) {
				if (temp.resultCode == true){
					var list = temp.value;
					var ht = [];
					var len = list.length;
					if(len == 0){
						common_alert_('您当前登录账号类型为个人用户，暂无关联企业，暂不能查看企业相关信息。请在电脑端登录爱信诺一站式服务平台(http://www.jss.com.cn)在会员中心关联企业后再进行查看，谢谢！');
					}
					else if (len == 1){
						var a = list[0];
						selectToInput(a.enterId,a.taxNum,a.userName);
						common_get_unit(fun,fun2,common_sel_company.val(),1);
					}
					else{
						for (var i = 0; i < len; i++) {
							var a = list[i];
							ht.push('<option value="'+a.enterId+'" tax="'+a.taxNum+'">'+a.userName+'</option>');
						}
						var str_ = ht.join('');
						//common_sel_company.append(str_).change(function(){
						common_sel_company.html(str_).change(function(){
							var sel = this.value;
							$('#sel_serviceUnit').val('');
							if (fun3){fun3();};
							// 从缓存中取该企业的服务单位
							var str_2 = common_unit_map[sel];
							if (str_2) {
								// 当该企业没有对应的服务单位
								if (str_2.indexOf('option')!=-1) {input2ToSelect(fun2);$('#sel_serviceUnit').html(str_2);}
								else{var str_3 = str_2.split(',');selectToInput2(str_3[0],null,str_3[1]);}
							}
							// 当该企业对应的服务单位
							else{common_get_unit(fun,fun2,sel,len);}
						});
						// 初期触发下拉框事件
						common_sel_company.change();
					}
				}
			}
        });
	}
}

function selectToInput(uid,tax,common_name){
	common_sel_company.after('<input type="hidden" id="common_sel_company" value="'+uid+'" tax="'+tax+'">');
	common_sel_company.remove();
	common_sel_company = $('#common_sel_company');
	common_sel_company.after('<input class="word_hide" id="common_sel_company_" type="text" readonly="readonly" value="'+common_name+'">');
	common_sel_company.parent().addClass('e-pr15').parent().removeClass('weui_cell_select');
}

function selectToInput2(uid,tax,common_name){
	if (common_serviceUnit[0].tagName.toLowerCase()=='select'){
		common_serviceUnit.after('<input type="hidden" id="sel_serviceUnit" value="'+uid+'" tax="'+tax+'">');
		common_serviceUnit.remove();
		common_serviceUnit = $('#sel_serviceUnit');
		common_serviceUnit.after('<input class="word_hide" id="serviceUnit_" type="text" readonly="readonly" value="'+common_name+'">');
		common_serviceUnit.parent().addClass('e-pr15').parent().removeClass('weui_cell_select');
	}
}

function input2ToSelect(fun2){
	if (common_serviceUnit[0].tagName.toLowerCase()=='input'){
		$('#serviceUnit_').remove();
		common_serviceUnit.after('<select class="weui_select" id="sel_serviceUnit"></select>');
		common_serviceUnit.remove();
		common_serviceUnit = $('#sel_serviceUnit');
		common_serviceUnit.parent().removeClass('e-pr15').parent().addClass('weui_cell_select');
		if(fun2){common_serviceUnit.on('change',fun2);};
	}
}

/**
 * 获取服务单位
 * @param fun 回调方法
 * @param uid
 */
function common_get_unit(fun,fun2,uid,len_){
	if (uid){
		$.ajax({
			url : 'user/center/getUnit.action',
			data : {uid:uid},
			success : function(temp) {
				if (temp.resultCode == true){
					var list = temp.value;
					var ht = [];
					var len = list.length;
					ht.push(COMMON_SELECT_EMPTY);
					if(len==0){
						//ht.push(COMMON_SELECT_EMPTY);
						var str_ = ht.join('');
						common_serviceUnit.html(str_);
						common_unit_map[uid]=str_;
					}
					else if (len==1){
						var a = list[0];
						var b = a.serviceid+'_'+a.taxnum;
						selectToInput2(b,null,a.companyname);
						common_unit_map[uid]=b+','+a.companyname;
						if (fun2) {fun2();}
					}
					else{
						input2ToSelect(fun2);
						for (var i = 0; i < len; i++) {
							var a = list[i];
							ht.push('<option value="'+a.serviceid+'_'+a.taxnum+'">'+a.companyname+'</option>');
						}
						var str_ = ht.join('');
						common_serviceUnit.html(str_);
						common_unit_map[uid]=str_;
					}

					if (len_ != 1 && len != 1 && fun) {
						fun();
					}
				}
			}
		});
	}
}

/**
 * 共通消息提示
 * @param text 消息内容
 * @param time 消息提示时间
 */
var common_showToast=(function(){
    var instance=null;
        function show(text,timeOut){
            if(!instance){
                var $container=$('<div class="showToast"><div class="showToastInner"><div class="showToastTips fieldTipBounceIn"><div class="showToastCon">'+text+'</div></div></div></div>');
                $container.appendTo($("body"));
                instance=true;
                setTimeout(function(){
                    $container.remove();
                    instance=false;
                },timeOut?timeOut:2000);
            }
        }
   return show;
   //return {show:show};
})();

/**
 * 共通消息提示（微信）
 * @param text 消息内容
 * @param time 消息提示时间
 * @param isErr 是否是错误消息
 */
var common_showToast_=(function(){
    var instance=null;
    var content = null;
    var icon_ = null;
        function show(text,timeOut,isErr){
            if(!instance){
                var $container=$('<div id="toast" style="display: none;"><div class="weui_mask_transparent"></div><div class="weui_toast"><i id="_icon_"></i><p class="weui_toast_content"></p></div></div>');
                $container.appendTo($("body"));
                instance=$container;
                content = instance.find('.weui_toast_content');
                icon_ = instance.find('#_icon_');
            }
            if(isErr){icon_.removeClass().addClass("weui_icon_toast_err");}
            else{icon_.removeClass().addClass("weui_icon_toast");}
            content.html(text);
            instance.show();
            setTimeout(function(){instance.hide();},timeOut?timeOut:2000);
        }
   return show;
})();

/**
 * 共通消息提示（微信）弹出框
 * @param text 消息内容
 * @param time 消息提示时间
 * @param isErr 是否是错误消息
 */
var common_alert_=(function(){
    var instance=null;
    var content = null;
        function show(text){
            if(!instance){
                var $container=$('<div class="weui_dialog_alert"><div class="weui_mask"></div><div class="weui_dialog"><div class="weui_dialog_hd"><strong class="weui_dialog_title">提示</strong></div><div class="weui_dialog_bd" id="weui__alert"></div><div class="weui_dialog_ft"><a href="javascript:;" class="weui_btn_dialog primary" onclick="WeixinJSBridge.call(\'closeWindow\');">确定</a></div></div></div>');
                $container.appendTo($("body"));
                instance=$container;
                content = instance.find('#weui__alert');
            }
            content.html(text);
            instance.show();
        }
   return show;
})();
	
/**
 * 共通进度条提示（微信）
 * @param text 消息内容
 * @param time 消息提示时间
 */
var common_progress=(function(){
    var instance=null;
        function show(){
            if(!instance){
            	var arr = [];
            	arr.push('<div id="loadingToast" class="weui_loading_toast" style="display:none;"><div class="weui_mask_transparent"></div><div class="weui_toast"><div class="weui_loading">');
            	for (var i = 0; i < 12; i++) {arr.push('<div class="weui_loading_leaf weui_loading_leaf_'+i+'"></div>');}
            	arr.push('</div><p class="weui_toast_content">数据加载中</p></div></div>');
            	var htmstr = arr.join('');
                var $container=$(htmstr);
                $container.appendTo($("body"));
                instance=$container;
            }
            instance.show();
        }
        function hide(){
        	if(instance){instance.hide();};
        }
   return {show:show,hide:hide};
})();

/**
 * ajax请求错误提示
 */
$(document).on('ajaxError', function(e, xhr, options){
    switch (xhr.status){  
        case(500):common_showToast('服务器系统内部错误，请重稍后再尝试！');break;  
        case(401):common_showToast('未登录！');break;  
        case(403):common_showToast('无权限执行此操作！');break;  
        case(404):common_showToast('服务器内部错误，请重稍后再尝试！');break; 
        case(408):common_showToast('请求超时！');break;  
        default:common_showToast('未知错误，请重稍后再尝试！');
    }  
})
/**
 * ajax加载进度条设置
 */
.on('ajaxStart',(function(){common_progress.show();}))
.on('ajaxComplete',(function(e, xhr, options){
	common_progress.hide();
    var sessionstatus = xhr.getResponseHeader("sessionstatus");
    if (sessionstatus == "timeout") {
      window.location.replace("sessionErr.jsp");
    }
}));

/**
 * 短信计时
 */
var wait=120; 
function common_time(o) { 
	if (wait == 0) {$(o).removeClass('hui').html("获取验证码");wait = 120; 
	}else {$(o).addClass('hui ').html(wait + "秒"); wait--; setTimeout(function() {common_time(o);}, 1000);}
} 

var yzm_msg = {"mobile":"手机号已被绑定","exception":"服务器出错，请稍后重新尝试！","OOO":"验证码次数不足","wait":"发送过于频繁","code":"验证码不正确","match":"手机号与税号不匹配","tucode":"图文验证码不正确"};
/**
 * 验证码事件
 * @param obj
 * @param telId
 * @param btnId
 * @param yzm_type
 * @param tax
 * @returns {Boolean}
 */
function common_get_yzm(obj,telId,btnId,yzm_type,tax){
	var tel =  $(telId).val();
	if (!tel){
		common_showToast('请输入手机号码');
	    $(telId).focus();
		return;
	}
	var yzm__ = $('#yzm__').val();
	if (!yzm__){
		common_showToast('请输入图形验证码');
	    $('#yzm__').focus();
		return;
	}
	var reg = common_mobile_reg;
	if(reg.test(tel)){
	    if($(obj).hasClass('hui')) {return false;}
		$.ajax({
			url : "user/bind/getYzm.action",
	        data:{mobile:tel,yzm_type:yzm_type,tax:tax?tax:'',code:yzm__},  
			success : function(result) {
				if (!result){common_showToast(yzm_msg["exception"]);}
				else if (result.resultCode=='true'){
					common_showToast('验证码发送成功，请查看手机！');
					wait=120;
					common_time(obj) ;
				}else if (result.resultCode=='false' && result.cause){
					common_showToast(yzm_msg[result.cause]);
				}else{
					common_showToast('验证码发送失败，请重新尝试！');
				}
				$('#randCodeImage').click();
				$(btnId).removeAttr('disabled').removeClass('weui_btn_disabled');
			}
	    });
	}else{
		common_showToast('请输入正确的手机号码');
	    $(telId).focus();
	}
}

/**
 * 网页图片验证码获取
 * @param name 参数名
 * @returns
 */
$('#randCodeImage').click(function(){
	common_getYZM();
});

/**
 * 获取url参数
 * @param name 参数名
 * @returns
 */
function common_getYZM(obj){
    var date = new Date();
    var img = null;
    //if(obj){img=obj;}else{
    img=document.getElementById("randCodeImage");
    //
    img.src='randCodeImage?a=' + date.getTime();
}

/**
 * 获取url参数
 * @param name 参数名
 * @returns
 */
function common_getQueryString(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return unescape(r[2]); return null;
}

/******************wjy************************/
//确认提示框
function common_showConfirm(title,okCallBack,cancelCallBack){
	if(title==undefined||title==''||title==null||title=='undefined'){
		title='您确定这样做吗？';
	}
	var html=
	'<div class="weui_dialog_confirm" id="wjy-dialog" style="display: none;">'+
    '<div class="weui_mask"></div>'+
   ' <div class="weui_dialog">'+
        '<div class="weui_dialog_hd"><strong class="weui_dialog_title">用户确认</strong></div>'+
        '<div class="weui_dialog_bd">'+title+'</div>'+
        '<div class="weui_dialog_ft">'+
        '<a id="wjy-dialog-ok" href="javascript:;" class="weui_btn_dialog primary">确定</a>'+
        '<a id="wjy-dialog-cancel" href="javascript:;" class="weui_btn_dialog default">取消</a>'+
        '</div>'+
    '</div>'+
    '</div>';
	$("#g-container").append(html);
	$("#wjy-dialog").show();
	$("#wjy-dialog-ok").click(function(){
		$("#wjy-dialog").remove();
		if(typeof okCallBack=='function'){
			okCallBack();
		}
	});
	
	$("#wjy-dialog-cancel").click(function(){
		$("#wjy-dialog").remove();
		if(typeof cancelCallBack=='function'){
			cancelCallBack();
		}
	});
}
function common_showWarning(msg){
	$('.js_tooltips').html(msg).show();
    setTimeout(function (){
        $('.js_tooltips').hide();
    }, 1800);
}
function common_showToastNew(msg){
	if(msg){
		$('#toast').find("p.weui_toast_content").html(msg);
	}
	 $('#toast').show();
     setTimeout(function () {
         $('#toast').hide();
     }, 2000);
}