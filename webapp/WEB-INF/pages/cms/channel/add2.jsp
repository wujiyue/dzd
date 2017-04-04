<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>新增栏目</title>
  <script type="text/javascript" src="${ctx}/resources/js/H-ui.admin.js"></script>
  <script type="text/javascript">
    var validform;
    function save(){
      var b=validform.check(false);
      if(!b){
        return;
      }
      var params=$("#form_show").serialize();
      $.ajax({
        type:"post",
        url:'${ctx}/cms/channel/json/save?'+params,
        data:null,
        success:function(json,textStatus){
          broAjaxReturnMsg(json);
          setTimeout(function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
          },1000);
        }
      });
    }
    $(function(){
      validform=$("#form_show").Validform({
        tiptype:2,
        postonce:true,//至提交一次
        ajaxPost:false,//ajax方式提交
        showAllError:true //默认 即逐条验证,true验证全部
      });
    })
  </script>
</head>
<body>
<article class="page-container" >
  <div class="form form-horizontal responsive">
    <form action="" id="form_show" method="post">
      <div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>栏目大类:</label>
			 <div class="formControls col-3">
				<input type="hidden" class="input-text" id="parentid" name="parentid" datatype="*" nullmsg="请选择栏目大类" value="${channel.parentid}" />
				 <script type="text/javascript">
					 function channelBigSelectChange(obj){
						$("#parentid").val($(obj).val()) ;
						$("#parentid").blur();
					 }
					 $("#channelBigSelect").change();
				 </script>
				 <bro:broSelect path="channelBigSelect" onchange="channelBigSelectChange(this)" url="/cms/channelBig/json/select"></bro:broSelect>
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>栏目名称:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="name" datatype="*" value="${channel.name}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>栏目链接:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="jump_url" datatype="*" value="${channel.jump_url}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">封面图片:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="img_url"  value="${channel.img_url}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">封面缩略图:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="small_img_url"  value="${channel.small_img_url}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">new标志:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="new_flag"  value="${channel.new_flag}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">热度标志:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="hot_flag"  value="${channel.hot_flag}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		
      <div class="row cl">
        <div class="formControls col-3 col-offset-2">
          <button type="button" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>
      </div>
    </form></div>
</article>
</body>
</html>
