
$(function(){

	loadinfo();

	
});
function openFpxx(obj){
	
	$(obj).next().toggle();
	$(obj).parent().siblings().find("a.weui_cell").next().hide();
}

function toIndex(){
	window.location.href=sys_ctx+'/zcpt/wxsm/index.jsp?fromlist=1';
}
function loadinfo(){
		sys_ajaxPost("/wx/default.do?method=queryFpsmList&username="+username+"&fpdm="+fpdm+"&kprq_q="+kprq_q+"&kprq_z="+kprq_z+"&rnd="+new Date().getTime(), null, function (json) {
			//alert(JSON.stringify(json));
			//return;
			if(json.result==true){
				var total=json.formData.total;
				var rows=json.formData.rows;
				
				if(total>0){
					
					var qyxxlist='<span class="weui_grid grid_title">发票代码</span>';
					qyxxlist+='<span class="weui_grid grid_title">发票号码</span>';
					qyxxlist+='<span class="weui_grid grid_title">金额</span>';
					qyxxlist+='<span class="weui_grid grid_title">开票时间</span>';
					
						
					for(var i=0;i<total;i++){
						qyxxlist+='<span class="weui_grid">'+rows[i].fpdm+'</span>';
						qyxxlist+='<span class="weui_grid">'+rows[i].fphm+'</span>';
						qyxxlist+='<span class="weui_grid">'+rows[i].je+'</span>';
						qyxxlist+='<span class="weui_grid">'+rows[i].kpsj+'</span>';
						
				
					}
					$("#fplist").html(qyxxlist);
				
				}else{
					$("#fplist").html("<p style='text-align:center;margin:8px 0;'><font font-size='12' color='red'>没有查到任何记录！</font></p>");
				}
			}
		
	
	});
}
/*function loadinfo(){
	sys_ajaxPost("/wx/default.do?method=queryFpsmList&username="+username+"&fpdm="+fpdm+"&kprq_q="+kprq_q+"&kprq_z="+kprq_z+"&rnd="+new Date().getTime(), null, function (json) {
		//alert(JSON.stringify(json));
		//return;
		if(json.result==true){
			var total=json.formData.total;
			var rows=json.formData.rows;
			
			if(total>0){
				
				var qyxxlist="";
				 
					
				for(var i=0;i<total;i++){
					qyxxlist+="<!-- 单条发票信息start -->" ;
					qyxxlist+='<div class="fpxx">';
					qyxxlist+='<a class="weui_cell" onclick="openFpxx(this)" href="javascript:void(0);">' ;
						  
					qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
					qyxxlist+='<span>'+rows[i].fpdm+' | '+rows[i].fphm+'</span></div>';
					qyxxlist+='<span class="weui_cell_ft"></span></a>';
					qyxxlist+='<!-- 发票信息详情 -->';
					qyxxlist+='<div class="weui_panel weui_panel_access" style="display:none;">';
					qyxxlist+='<div class="weui_panel_bd">';
					//qyxxlist+='<div class="weui_media_box weui_media_text">';
					//qyxxlist+='<h5 class="weui_media_title">';
					//qyxxlist+='<font color="green">您的企业简码是：</font>';
					//qyxxlist+='<font color="red">'+rows[i].id+'</font></h5>';
					//qyxxlist+='<p class="weui_media_desc">';
					//qyxxlist+='<div id="code_'+rows[i].id+'" class="rwmimg"></div></p></div>';
						        
					qyxxlist+='<div class="weui_media_box weui_media_text">';
					qyxxlist+='<h4 class="weui_media_title">发票信息</h4>';
					qyxxlist+='<form action="" id="myform_'+i+'">';
					qyxxlist+='<input type="hidden" id="id_'+i+'" name="id_'+i+'" value="'+rows[i].guid+'" />';
					qyxxlist+='<div class="weui_cells weui_cells_form">';
					
						            
					qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
					qyxxlist+='<div class="weui_cell_hd">';
					qyxxlist+='<label class="weui_label">企业税号:</label></div>';
					qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
					qyxxlist+='<input class="weui_input" type="text" id="qysh_'+i+'" name="qysh_'+i+'" value="'+rows[i].qysh+'" readonly></div></div>';
					
					qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
					qyxxlist+='<div class="weui_cell_hd">';
					qyxxlist+='<label class="weui_label">企业名称:</label></div>';
					qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
					qyxxlist+='<input class="weui_input" type="text" id="qymc_'+i+'" name="qymc_'+i+'" value="'+rows[i].qymc+'" readonly></div></div>';
					
					qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
					qyxxlist+='<div class="weui_cell_hd">';
					qyxxlist+='<label class="weui_label">发票代码:</label></div>';
					qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
					qyxxlist+='<input class="weui_input" type="text" id="fpdm_'+i+'" name="fpdm_'+i+'" value="'+rows[i].fpdm+'" readonly></div></div>';
					
					
					qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
					qyxxlist+='<div class="weui_cell_hd">';
					qyxxlist+='<label class="weui_label">发票号码:</label></div>';
					qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
					qyxxlist+='<input class="weui_input" type="text" id="fphm_'+i+'" name="fphm_'+i+'" value="'+rows[i].fphm+'" readonly></div></div>';
					
					qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
					qyxxlist+='<div class="weui_cell_hd">';
					qyxxlist+='<label class="weui_label">金&nbsp;&nbsp;&nbsp;&nbsp;额:</label></div>';
					qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
					qyxxlist+='<input class="weui_input" type="text" id="je_'+i+'" name="je_'+i+'" value="'+rows[i].je+'" readonly></div></div>';
						            
					qyxxlist+='<div class="weui_cell" style="padding: 10px 0px;font-size:13px;">';
					qyxxlist+='<div class="weui_cell_hd">';
					qyxxlist+='<label class="weui_label">开票日期:</label></div>';
					qyxxlist+='<div class="weui_cell_bd weui_cell_primary">';
					qyxxlist+='<input class="weui_input" type="text" id="kpsj_'+i+'" name="kpsj_'+i+'"  value="'+rows[i].kpsj+'"  readonly></div></div> </div>'
					qyxxlist+='<div class="weui_btn_area e-mt20">';
					//qyxxlist+='<a class="weui_btn weui_btn_primary saveBtn" onclick="saveRwm(this)">保存</a>';
					qyxxlist+='<a class="weui_btn weui_btn_warn deleteBtn" onclick="delRwm(this)">删除</a></div>';
					qyxxlist+='</form>';
					qyxxlist+='</div>';
					qyxxlist+='</div>';
					qyxxlist+='</div>';
					qyxxlist+='</div>';
					qyxxlist+="<!-- 单条发票信息end -->" ;
					
					
				}
				$("#fplist").html(qyxxlist);
			
			}else{
				$("#fplist").html("<p style='text-align:center;margin:8px 0;'><font font-size='12' color='red'>没有查到任何记录！</font></p>");
			}
		}
	

});
}*/


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


function delRwm(obj){
	var $this=$(obj);
	var form=$this.parent().parent();
	var id=form.find("input[type='hidden']").val();
	//alert("id==="+id);
	common_showConfirm('确定要删除该条发票扫描信息吗？',function(){
		//alert("id==="+id)
		sys_ajaxPost("/wx/default.do?method=deleteFpsm&id="+id+"&rnd="+new Date().getTime(), null, function (json) {
			if(json.result==true){
				common_showToast("删除成功！");
				$this.parents(".fpxx").remove();
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