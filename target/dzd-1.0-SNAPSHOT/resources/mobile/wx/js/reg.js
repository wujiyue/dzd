//============================================================================
// 常量
//============================================================================
var LOGIN_MSG_LIST = {success:'登录成功！',BBB:'用户已被绑定！',AAA:'用户名或密码错误！',FFF:'登录失败！'};
//============================================================================
//初始化
//============================================================================
$(function(){

$('#password2').bind('propertychange input',function(e){
    if($('#username').val()!=''&&this.value!=''&&this.value==$('#password').val()){
        $('#submit').removeClass('weui_btn_disabled').removeAttr('disabled');
    }else{
    	$('#submit').addClass('weui_btn_disabled').addAttr('disabled');
    }
});

//$.mvalidateExtend({
//    password:{required : true,pattern : /^[0-9A-Za-z]{1,35}$/,each:function(){}}
//});
    

$("#myform_reg").mvalidate({
    type:1,
    onKeyup:true,
    sendForm:true,
    firstInvalidFocus:false,
    valid:function(event,options){
		$.ajax({
			type : "post",
			url : sys_ctx+"/wx/default.do?method=addUser",
	        data:{username:$('#username').val(),password:$('#password').val()},  
			success : function(result) {
				// 绑定成功
				if (result.result == true) {
					$.fn.cookie('weixinfore_username', $('#username').val(), {expires: 7});
					$.fn.cookie('weixinfore_password', $('#password').val(), {expires: 7});
					var func = common_getQueryString("func");
					if(func){
					    // 弹出中奖信息栏
					    parent.bindSuccess();
					    return;
					}
					//var newURL = common_getQueryString("url");
					
					//var url = 'user/center/usrInfo.action';
					var url = '/zcpt/wx/login.jsp';
					// 手机已绑定的情况
					/*if (result.mobile){
						// 来自其他页面
						if (newURL) {
							url = newURL;
						}else{
							url = 'user/center/usrInfo.action';
						}
					}else{
						// 来自其他页面
						if (newURL) {
							url + '?url='+newURL;
						}
					}*/
					common_showToast("注册成功！");
					setTimeout(function(){window.location.href=sys_ctx+url;},1000);
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
        password:{required : '请输入密码'},
        password2:{required : '请再次输入密码'}
    }
});



});
