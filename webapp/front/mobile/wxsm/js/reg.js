//============================================================================
// 常量
//============================================================================
var LOGIN_MSG_LIST = {success:'登录成功！',BBB:'用户已被绑定！',AAA:'用户名或密码错误！',FFF:'登录失败！'};
//============================================================================
//初始化
//============================================================================
$(function(){

$('#password2,#username,#qymc,#password').bind('propertychange input',function(e){
    if($('#username').val()!=''&& $('#password2').val()!=''&& $('#password2').val()==$('#password').val()&&$('#qymc').val()!=''){
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
			url : sys_ctx+"/wx/default.do?method=addUser&flag=1",
	        data:{username:$('#username').val(),password:$('#password').val(),qymc:$("#qymc").val()},  
			success : function(result) {
				// 绑定成功
				if (result.result == true||result.result == 'true') {
					$.fn.cookie('weixinfore_username', $('#username').val(), {expires: 7});
					$.fn.cookie('weixinfore_password', $('#password').val(), {expires: 7});
				
					var url = '/zcpt/wxsm/login.jsp';
				
					common_showToast("注册成功！");
					setTimeout(function(){window.location.href=sys_ctx+url;},800);
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
