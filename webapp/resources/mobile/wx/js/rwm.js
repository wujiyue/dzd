//============================================================================
// 常量
//============================================================================
var LOGIN_MSG_LIST = {success:'登录成功！',BBB:'用户已被绑定！',AAA:'用户名或密码错误！',FFF:'登录失败！'};
//============================================================================
//初始化
//============================================================================

$(function(){

/*$('#password2').bind('propertychange input',function(e){
    if($('#username').val()!=''&&this.value!=''&&this.value==$('#password').val()){
        $('#submit').removeClass('weui_btn_disabled').removeAttr('disabled');
    }else{
    	$('#submit').addClass('weui_btn_disabled').addAttr('disabled');
    }
});
	$('.saveBtn').click(function(){
		common_showWarning("test123");
	});

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
				
					var url = '/zcpt/wx/login.jsp';
				
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
    */
	//common_showConfirm();
	//common_showToast('123456');
	loadinfo();
	/*$("body").on("click", "a.weui_cell", function () {             
		alert("body on click");      
	});*/
	
});
function openQyxx(obj){
	
	$(obj).next().toggle();
	$(obj).parent().siblings().find("a.weui_cell").next().hide();
}
//跳转新增页面
function toAdd(){
	//window.location.href=sys_ctx+'/zcpt/wx/addrwm.jsp';
	window.location.href=sys_ctx+'/mobile/wx/addrwm';
}
function loadinfo(){
		//sys_ajaxPost("/mobile/wx/default.do?method=queryrwmlist&username="+username+"&rnd="+new Date().getTime(), null, function (json) {
	sys_ajaxPost("/mobile/wx/queryrwmlist?username="+username+"&rnd="+new Date().getTime(), null, function (json) {

		//alert(JSON.stringify(json));
			if(json.result==true){
				var total=json.formData.total;
				var rows=json.formData.rows;
				
				if(total>0){
					
					var qyxxlist="";
					 
						
					for(var i=0;i<total;i++){
						qyxxlist+="<!-- 单条企业信息start -->" ;
						qyxxlist+='<div class="qyxx">';
						qyxxlist+='<a class="weui_cell" onclick="openQyxx(this)" href="javascript:void(0);">' ;
							  
						qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
						qyxxlist+='<span style="font-weight:bold;">'+rows[i].nsrmc+'</span></div>';
						qyxxlist+='<span class="weui_cell_ft"></span></a>';
						qyxxlist+='<!-- 企业信息详情 -->';
						qyxxlist+='<div class="weui_panel weui_panel_access" style="display:none;">';
						qyxxlist+='<div class="weui_panel_bd">';
						qyxxlist+='<div class="weui_media_box weui_media_text">';
						qyxxlist+='<h5 class="weui_media_title">';
						qyxxlist+='<font color="green">您的企业简码是：</font>';
						qyxxlist+='<font color="red">'+rows[i].id+'</font></h5>';
						qyxxlist+='<p class="weui_media_desc">';
						qyxxlist+='<div id="code_'+rows[i].id+'" class="rwmimg"></div></p></div>';
							        
						qyxxlist+='<div class="weui_media_box weui_media_text">';
						qyxxlist+='<h4 class="weui_media_title">企业信息</h4>';
						qyxxlist+='<form action="" id="myform_'+i+'">';
						qyxxlist+='<input type="hidden" id="id_'+i+'" name="id_'+i+'" value="'+rows[i].id+'" />';
						qyxxlist+='<div class="weui_cells weui_cells_form">';
						qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
						qyxxlist+='<div class="weui_cell_hd">';
						qyxxlist+='<label class="weui_label">纳税人识别号:</label></div>';
						qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
						qyxxlist+='<input class="weui_input" type="text" id="nsrsbh_'+i+'" name="nsrsbh_'+i+'" value="'+rows[i].nsrsbh+'" placeholder="请输入纳税人识别号"></div></div>';
							            
						qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
						qyxxlist+='<div class="weui_cell_hd">';
						qyxxlist+='<label class="weui_label"><font color="red">*&nbsp;&nbsp;</font>纳税人名称:</label></div>';
						qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
						qyxxlist+='<input class="weui_input" type="text" id="nsrmc_'+i+'" name="nsrmc_'+i+'" value="'+rows[i].nsrmc+'" placeholder="请输入纳税人名称"></div></div>';
							            
						qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
						qyxxlist+='<div class="weui_cell_hd">';
						qyxxlist+='<label class="weui_label">银行帐号:</label></div>';
						qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
						qyxxlist+='<input class="weui_input" type="text" id="yhzh_'+i+'" name="yhzh_'+i+'" value="'+rows[i].yhzh+'" placeholder="请输入银行帐号"></div></div>';
							            
						qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
						qyxxlist+='<div class="weui_cell_hd">';
						qyxxlist+='<label class="weui_label">地址:</label></div>';
						qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
						qyxxlist+='<input class="weui_input" type="text" id="dz_'+i+'" name="dz_'+i+'"  value="'+rows[i].dz+'"  placeholder="请输入地址"></div></div> </div>'
						qyxxlist+='<div class="weui_btn_area e-mt20">';
						qyxxlist+='<a class="weui_btn weui_btn_primary saveBtn" onclick="saveRwm(this)">保存</a>';
						qyxxlist+='<a class="weui_btn weui_btn_warn deleteBtn" onclick="delRwm(this)">删除</a></div>';
						qyxxlist+='</form>';
						qyxxlist+='</div>';
						qyxxlist+='</div>';
						qyxxlist+='</div>';
						qyxxlist+='</div>';
						qyxxlist+="<!-- 单条企业信息end -->" ;
						
						
					}
					$("#qylist").html(qyxxlist);
					for(var i=0;i<total;i++){
						var data=rows[i];
						var id=data.id;
						var nsrsbh=data.nsrsbh;
						var nsrmc=data.nsrmc;
						var yhzh=data.yhzh;
						var dz=data.dz;
						
						nsrmc=toUtf8(nsrmc);
						yhzh=toUtf8(yhzh);
						dz=toUtf8(dz);
						
						var str=nsrsbh+','+nsrmc+','+yhzh+','+dz;
			     		$('#code_'+id).html("");
			     		$('#code_'+id).qrcode({width:240,height:240,text:str}); 
					}
				}else{
					$("#qylist").html("<p style='text-align:center;margin:8px 0;'><font font-size='12' color='red'>您还没有添加企业名片！</font></p>");
				}
			}
		
	
	});
	}

function changeRwm(str){
	var arr=str.split(",");
	var id=arr[0];
	var nsrsbh=arr[1];
	var nsrmc=arr[2];
	var yhzh=arr[3];
	var dz=arr[4];
	nsrmc=toUtf8(nsrmc);
	yhzh=toUtf8(yhzh);
	dz=toUtf8(dz);
	var str2=nsrsbh+','+nsrmc+','+yhzh+','+dz;
	$('#code_'+id).html("");
	$('#code_'+id).qrcode({width:240,height:240,text:str2});
}

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
	/*var nsrmc=$("#nsrmc").val();
	if(nsrmc==''){
		common_showWarning("纳税人名称不能为空！");
		return;
	}*/
		
	var $this=$(obj);
	var form=$this.parent().parent();
	
	var form_id=form.attr("id");
	var index=form_id.split("_")[1];
	var nsrmc=$("#nsrmc_"+index).val();
	if(nsrmc==''){
		common_showWarning("纳税人名称不能为空！");
		return;
	}else{
		if(nsrmc.length>50){
			common_showWarning("纳税人名称过长！");
			return;
		}
	}
	
	var nsrsbh=$("#nsrsbh_"+index).val();
	if(nsrsbh!=''){
		if(nsrsbh.length>30){
			common_showWarning("纳税人识别号过长！");
			return;
		}
	}		
	var yhzh=$("#yhzh_"+index).val();
	if(yhzh!=''){
		if(yhzh.length>50){
			common_showWarning("银行账号长度不能超过50个字符！");
			return;
		}
	}
	var dz=$("#dz_"+index).val();
	if(dz!=''){
		if(dz.length>50){
			common_showWarning("地址长度不能超过50个字符！");
			return;
		}
	}
	
	//common_showWarning(JSON.stringify(form));
	var params=form.serialize();
	params=params.replaceAll('_[0-9][=]','=');
	params=params.replaceAll("$","").replaceAll(",","").replaceAll(";","");
	//alert(params);
	//sys_ajaxPost("/wx/default.do?method=updaterwm", params, function (json) {
	sys_ajaxPost("/mobile/wx/updaterwm", params, function (json) {

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

function delRwm(obj){
	var $this=$(obj);
	var form=$this.parent().parent();
	var id=form.find("input[type='hidden']").val();
	//alert("id==="+id);
	common_showConfirm('确定要删除该企业名片吗？',function(){
		//alert("id==="+id)
		//sys_ajaxPost("/wx/default.do?method=deleterwm&id="+id+"&rnd="+new Date().getTime(), null, function (json) {
			sys_ajaxPost("/mobile/wx/deleterwm?id="+id+"&rnd="+new Date().getTime(), null, function (json) {
				alert(JSON.stringify(json));
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