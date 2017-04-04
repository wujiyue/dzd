//============================================================================
// 常量
//============================================================================
var LOGIN_MSG_LIST = {success:'登录成功！',BBB:'用户已被绑定！',AAA:'用户名或密码错误！',FFF:'登录失败！'};
//============================================================================
//初始化
//============================================================================

$(function(){

	
});


function common_showWarning(msg){
	$('.js_tooltips').html(msg).show();
    setTimeout(function (){
        $('.js_tooltips').hide();
    }, 1800);
}
function common_showToast(msg){
	if(msg){
		$('#toast').find("p.weui_toast_content").html(msg);
	}
	 $('#toast').show();
     setTimeout(function () {
         $('#toast').hide();
     }, 2000);
}
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
//更新
function saveRwm(obj){
	var $this=$(obj);
	var form=$this.parent().parent();
	//common_showWarning(JSON.stringify(form));
	var params=form.serialize();
	params=params.replaceAll('_[0-9][=]','=');
	params=params.replaceAll("$","").replaceAll(",","").replaceAll(";","");
	//alert(params);
	sys_ajaxPost("/wx/default.do?method=updaterwm", params, function (json) {
		if(json.result==true){
			common_showToast("更新成功！");
			//$this.parents(".qyxx").remove();
			var str=json.str;
			changeRwm(str);
		}else{
			common_showToast(json.msg);
		}
	});
}
//新增
function addRwm(obj){
	var nsrmc=$("#nsrmc").val();
	if(nsrmc==''){
		common_showWarning("纳税人名称不能为空！");
		return;
	}else{
		if(nsrmc.length>50){
			common_showWarning("纳税人名称过长！");
			return;
		}
	}
	var nsrsbh=$("#nsrsbh").val();
	if(nsrsbh!=''){
		if(nsrsbh.length>30){
			common_showWarning("纳税人识别号过长！");
			return;
		}
	}		
	var yhzh=$("#yhzh").val();
	if(yhzh!=''){
		if(yhzh.length>50){
			common_showWarning("银行账号长度不能超过50个字符！");
			return;
		}
	}
	var dz=$("#dz").val();
	if(dz!=''){
		if(dz.length>50){
			common_showWarning("地址长度不能超过50个字符！");
			return;
		}
	}		
	var $this=$(obj);
	var form=$this.parent().parent();
	//common_showWarning(JSON.stringify(form));
	var params=form.serialize();
	params=params.replaceAll('_[0-9][=]','=');
	params=params.replaceAll("$","").replaceAll(",","").replaceAll(";","");
	
	var id=$("#id").val();
	if(id==''){
		sys_ajaxPost("/wx/default.do?method=saverwm&username="+username, params, function (json) {
			if(json.result==true){
				common_showToast("新增成功！");
				var str=json.str;
				var arr=str.split(",");
				var id=arr[0];
				$("#id").val(id);
				$("#qyjm").html(id);
				var nsrsbh=arr[1];
				var nsrmc=arr[2];
				var yhzh=arr[3];
				var dz=arr[4];
				nsrmc=toUtf8(nsrmc);
				yhzh=toUtf8(yhzh);
				dz=toUtf8(dz);
				var str2=nsrsbh+','+nsrmc+','+yhzh+','+dz;
				$('#code').html("");
				$('#code').qrcode({width:240,height:240,text:str2});
				$("#codebox").show();
			}else{
				common_showToast(json.msg);
			}
		});
	}else{
		sys_ajaxPost("/wx/default.do?method=updaterwm", params, function (json) {
			if(json.result==true){
				common_showToast("更新成功！");
				//$this.parents(".qyxx").remove();
				var str=json.str;
				var arr=str.split(",");
				var id=arr[0];
				$("#qyjm").html(id);
				var nsrsbh=arr[1];
				var nsrmc=arr[2];
				var yhzh=arr[3];
				var dz=arr[4];
				nsrmc=toUtf8(nsrmc);
				yhzh=toUtf8(yhzh);
				dz=toUtf8(dz);
				var str2=nsrsbh+','+nsrmc+','+yhzh+','+dz;
				$('#code').html("");
				$('#code').qrcode({width:240,height:240,text:str2});
				$("#codebox").show();
			}else{
				common_showToast(json.msg);
			}
		});
	}
	
}


//返回名片列表
function tolist(){
	window.location.href=sys_ctx+'/zcpt/wx/rwm.jsp';
}
//新增页面重置表单
function reset(){
	$("#nsrsbh").val('');
	$("#nsrmc").val('');
	$("#yhzh").val('');
	$("#dz").val('');
	$("#id").val('');
}
function delRwm(obj){
	var $this=$(obj);
	var form=$this.parent().parent();
	var id=form.find("input[type='hidden']").val();
	//alert("id==="+id);
	common_showConfirm('确定要删除该企业名片吗？',function(){
		//alert("id==="+id)
		sys_ajaxPost("/wx/default.do?method=deleterwm&id="+id+"&rnd="+new Date().getTime(), null, function (json) {
			if(json.result==true){
				common_showToast("删除成功！");
				$this.parents(".qyxx").remove();
			}else{
				common_showToast(json.msg);
			}
		});
	},
	function(){
			
	}
	);
}

String.prototype.trim = function() { 
		return this.replace(/(^\s*)|(\s*$)/g, ""); 
} 
	function toUtf8(str) {    
	    var out, i, len, c;    
	    out = "";    
	    len = str.length;    
	    for(i = 0; i < len; i++) {    
	        c = str.charCodeAt(i);    
	        if ((c >= 0x0001) && (c <= 0x007F)) {    
	            out += str.charAt(i);    
	        } else if (c > 0x07FF) {    
	            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));    
	            out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));    
	            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
	        } else {    
	            out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));    
	            out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));    
	        }    
	    }    
	    return out;    
	} 
	String.prototype.replaceAll = function(s1,s2){ 
		return this.replace(new RegExp(s1,"gm"),s2); 
	}