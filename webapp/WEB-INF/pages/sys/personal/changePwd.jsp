
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>密码修改</title>

	<link  href="myfront.css" rel="stylesheet" type="text/css"/>

    
    <script>

	$(function(){

	});
function enOrNum(min,max){
	var str = "";
	var pwd = $("#mm2").val();
	if(pwd.length<min) {
		tag="0";
		return;
	}
	if(pwd.length>max){
		tag="0";
		return;
	}
	var reg = new RegExp("^[A-Za-z]+[0-9]+[A-Za-z0-9]*|[0-9]+[A-Za-z]+[A-Za-z0-9]*$");
	if(reg.test(pwd)){
		tag = "1";
	}else{
		tag="0";
		return;
	}
}

    </script>
</head>
<script>

</script>
<body>

<!-- 网页主体 -->	
<div class="content_wrapper clearfix">
   

    <!-- 用户主体内容 --> 
    
    <!-- 修改密码 start -->
    <div class="personal_right">
    	<div class="personal_title">修改密码</div>
    	
    	 <div class="form form-horizontal responsive">
	          <form id="form_show2">

	          		<div class="row cl">
                		<label class="form-label col-1">新密码:</label>
                		<div class="formControls col-3">
                			<input  class="input-text" id="new_pwd" nullmsg="请输入新密码" datatype="*" name="new_pwd" value="" autocomplete="off"  type="password">
                		</div>
                		 <div class="formControls col-3">
                    		<div class="Validform_checktip"></div>
                		</div>
	          		</div>
	          		
	          		<div class="row cl">
                		<label class="form-label col-1">确认密码:</label>
                		<div class="formControls col-3">
                			<input class="input-text" id="new_pwd2" nullmsg="请再次输入密码" datatype="*" name="new_pwd2" value="" autocomplete="off"  type="password" >
                		</div>
                		 <div class="formControls col-3">
                    		<div class="Validform_checktip"></div>
                		</div>
	          		</div>
	          		
	          		<div class="row cl">
		                <span class="form-label col-1"></span>
		                <div class="formControls col-3">
		                   		 <a href="javascript:void(0);" class="submit red_btn" id="saveBtn" style="width:117px;height:38px;line-height:38px;" >保存修改</a>
			                        
		                        <!--  <a onclick="" id="saveBtn" class="btn btn-success radius">保存</a>
		                        <a onclick="reset()"  id="resetBtn" class="btn btn-primary radius">重置</a> -->
		                </div>
            		</div>
            
	          </form>
	          
	          <script type="text/javascript">
	          		$(function(){
	          			var  validform=$("#form_show2").Validform({
	                	 tiptype:2,
	                   	 beforeSubmit:function(curform){
		                       //验证成功执行ajax保存方法
		                       save();
		                       //表单提交的方式返回false
		                       return false;
		                   },
		
		                   btnSubmit:"#saveBtn",
		                   btnReset:"#resetBtn",
		                   postonce:true,//至提交一次
		                   ajaxPost:false,//ajax方式提交
		                   showAllError:true //默认 即逐条验证,true验证全部
		            });
		            
		            //检验密码
	          		
	          	});
	          	
	function save(){

	if($("#new_pwd").val()!=$("#new_pwd2").val()){
		alert("密码两次输入不一致！");
		return;
	}
	var queryString = $("#form_show2").serialize();
	//alert(queryString);
	sys_ajaxPostDirect("/org/default.do?method=save_pwd",queryString,function(json){
		//ajaxAlert(json);
		//Dumper.alert(json);
		alert(json.msg);
		sys_ajaxGet("/portal/login/default.do?method=exit",function(json1){
			//Dumper.alert(json);
			window.location.href=sys_ctx+json1.url;
		});
	});
}
    </script>
         </div>
    </div>
     <!-- 修改密码 end -->
</div>  
<!-- 网页主体 end-->     


</body>
</html>