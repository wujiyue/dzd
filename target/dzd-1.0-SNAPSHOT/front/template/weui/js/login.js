//============================================================================
// 常量
//============================================================================
var LOGIN_MSG_LIST = {success:'登录成功！',BBB:'用户已被绑定！',AAA:'用户名或密码错误！',FFF:'登录失败！'};
//============================================================================
//初始化
//============================================================================
$(function(){
// 加载cookie	
var u = $.fn.cookie('weixinfore_username');
if (u){
	$('#username').val(u);
}
var pw = $.fn.cookie('weixinfore_password');
if (pw){
	$('#password').val(pw);
}
var fjh = $.fn.cookie('weixinfore_fjh');
if (fjh){
	$('#fjh').val(fjh);
}
$('#password,#username,#fjh').bind('propertychange input',function(e){
	var fjh=$('#fjh').val();
	if(fjh.length>1&&fjh.substr(0,1)=='0'){
		fjh=fjh.substr(1,fjh.length);
		$('#fjh').val(fjh);
	}
	var reg=/^[0-9]\d*$/;
	
    if($('#username').val()!='' && $('#password').val()!='' && reg.test(fjh)){
        $('#submit').removeClass('weui_btn_disabled').removeAttr('disabled');
    }else{
    	$('#submit').addClass('weui_btn_disabled').addAttr('disabled');
    }
});

if($('#username').val()!='' && $('#password').val()!='' && $('#fjh').val()!=''){
	 $('#submit').removeClass('weui_btn_disabled').removeAttr('disabled');
}


$("#myform_login").mvalidate({
    type:1,
    onKeyup:true,
    sendForm:true,
    firstInvalidFocus:false,
    valid:function(event,options){
		$.ajax({
			type : "post",
			url : sys_ctx+"/wx/default.do?method=login&flag=1",
	        data:{username:$('#username').val(),password:$('#password').val()},  
			success : function(result) {
				//alert(JSON.stringify(result));
				// 登录成功
				if (result.result == true||result.result == 'true') {
					$.fn.cookie('weixinfore_username', $('#username').val(), {expires: 99999});
					var qymc=result.qymc;
					$.fn.cookie('weixinfore_qymc', qymc, {expires: 99999});
					var pwd=$('#password').val();
					$.fn.cookie('weixinfore_password', pwd, {expires: 99999});
					var fjh=$('#fjh').val();
					$.fn.cookie('weixinfore_fjh', fjh, {expires: 99999});
					var url = '/zcpt/wxsm/index.jsp';
					common_showToast(result.msg);
					setTimeout(function(){window.location.href=sys_ctx+url;},1300);
				}else{
					common_showToast(result.msg);
				}
			}
		});
        event.preventDefault();
    },
    invalid:function(event, status, options){},
    eachField:function(event,status,options){},
    eachValidField:function(val){},
    eachInvalidField:function(event, status, options){},
    descriptions:{
        username:{required : '请输入用户名'},
        fjh:{required : '请输入分机号'},
        password:{required : '请输入密码'}
    }
});

});
