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
$('#password').bind('propertychange input',function(e){
    if($('#username').val()!=''&&this.value!=''){
        $('#submit').removeClass('weui_btn_disabled').removeAttr('disabled');
    }else{
    	$('#submit').addClass('weui_btn_disabled').addAttr('disabled');
    }
});

if($('#username').val()!=''&&$('#password').val()!=''){
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
			url : sys_ctx+"/wx/login?flag=1",
	        data:{username:$('#username').val(),password:$('#password').val()},  
			success : function(result) {
				// 登录成功
				if (result.result == true) {
					$.fn.cookie('weixinfore_username', $('#username').val(), {expires: 99999});
					var qymc=result.qymc;
					$.fn.cookie('weixinfore_qymc', qymc, {expires: 99999});
					var url = '/mobile/wx/wxsm/index';
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
        password:{required : '请输入密码'}
    }
});

});
