<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<%
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";

%>
<!DOCTYPE html>

<html>
<head>
  <title>新增图片</title>
  <script type="text/javascript" src="${ctx}/resources/js/H-ui.admin.js"></script>
  <script type="text/javascript" src="${ctx}/resources/lib/plupload/plupload.full.min.js"></script>
<style type="text/css">
	.red_btn,.green_btn {


		color: #fff;
		text-align: center;
		border-radius: 2px;
		display: inline-block;
		text-decoration: none;
		-webkit-transition: all .3s ease;
		-moz-transition: all .3s ease;
		-o-transition: all .3s ease;
		transition: all .3s ease;
	}
	.red_btn{ background: #f45555; border: 1px solid #f45555;}
	.green_btn{ background: #4CAF50; border: 1px solid #4CAF50;}
	.red_btn:hover{
		background: #e63832;
		border: 1px solid #c42f2a;
		color: #fff;
		text-decoration: none;
	}
	.green_btn:hover {
		background: #2E8231;
		border: 1px solid #2E8231;
		color: #fff;
		text-decoration: none;
	}

</style>
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
        url:'${ctx}/cms/image/json/save?'+params,
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
  <div class="form form-horizontal responsive" style="float:left;width: 50%;">
    <form action="" id="form_show" method="post">


		<div class="row cl">
			<label class="form-label col-3">选择图片:</label>
			<div class="formControls col-6">
				<a href="javascript:void(0);" class="submit green_btn" style="width:117px;height:30px;line-height:30px;" id="browse" onclick="">选择文件</a>
			</div>
		</div>
      <div class="row cl">
			<label class="form-label col-3">用户id:</label>
		 <div class="formControls col-6">
			<input type="text" class="input-text" name="yhid"  value="${image.yhid}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-3">访问类型:</label>
		 <div class="formControls col-6">
			<input type="text" class="input-text" id="" name="datascope"  value="${image.datascope}" />
			 <bro:dicSelect id="sys_area_type" value="4"  datatype="*"  nullmsg="请选择区域类型"></bro:dicSelect>
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-3">图片名称:</label>
		 <div class="formControls col-6">
			<input type="text" class="input-text" id="name" name="name"  value="${image.name}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-3">图片后缀:</label>
			<div class="formControls col-6">
				<input type="text" class="input-text disabled" readonly id="suffixes" name="suffixes"  value="${image.suffixes}" />
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-3">图片大小:</label>
			<div class="formControls col-6">
				<input type="text" class="input-text disabled" readonly id="size" name="size"  value="${image.size}" />
			</div>
		</div>
		<div class="row cl">
				<label class="form-label col-3">存储地址:</label>
				 <div class="formControls col-6">
					<input type="text" class="input-text disabled" readonly id="url" name="url"  value="${image.url}" />
				</div>
		</div>

		<div class="row cl" style="display:none;">
			<label class="form-label col-3">小图地址:</label>
			 <div class="formControls col-6">
				<input type="text" class="input-text" name="url_small"  value="${image.url_small}" />
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-3"><span style='color:red;'>*</span>关键词:</label>
			 <div class="formControls col-6">
				<input type="text" class="input-text" name="keywords" datatype="*" value="${image.keywords}" />
			</div>
			<div>
				<div class="Validform_checktip"></div>
			</div>
		</div>

		<div class="row cl">
				<label class="form-label col-3"><span style='color:red;'>*</span>资源类型:</label>
				 <div class="formControls col-6">
					<input type="hidden" id="resourcetypeid" name="resourcetypeid" datatype="*" nullmsg="请选择类型" value="${image.resourcetypeid}" />

					 <input type="text" class="input-text"  id="resourcetypename" readonly   onclick="base_openZyflxzPage('resourcetypeid','resourcetypename',parent)">

				</div>
				<div>
					<div class="Validform_checktip"></div>
				</div>
		</div>

		
      <div class="row cl">
        <div class="formControls col-6 col-offset-3">
          <button type="button" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>
      </div>
    </form></div>

	<div style="float:left;width: 45%;height: 90%; border: 1px solid #E6E6E6;margin:15px auto;">

		<img width="100%" height="100%" src="/resources/images/cms/nofile.png" id="icon">
	</div>
</article>
<script type="text/javascript">

	var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		runtimes: 'html5,silverlight,html4,flash',
		browse_button : 'browse',
		url : sys_ctx+'/cms/image/uploadSingle',
		flash_swf_url : '${ctx}/resourcesydxs/plupload/Moxie.swf',
		silverlight_xap_url : '${ctx}/resourcesydxs/plupload/Moxie.xap',
		filters: {
			max_file_size: '10mb', //最大上传文件大小（格式100b, 10kb, 10mb, 1gb）
			mime_types : [{ title : "图片文件", extensions:"jpg,jpeg,gif,png,bmp" }]}//只允许上传图片文件
	});
	//绑定文件添加进队列事件
	uploader.bind('FilesAdded',function(uploader,files){
		uploader.start(); //开始上传
	});
	uploader.bind('Error',function(up, err){
		alert(err.message);//上传出错的时候触发
	});
	uploader.bind("FileUploaded", function(up, file, res){
		var json = JSON.parse(res.response);
		$("#name").val(json[0].name);
		$("#size").val(json[0].size);
		$("#url").val(json[0].path);
		$("#icon").attr("src",'<%=basePath%>'+json[0].path);
		$("#suffixes").val(json[0].suffixes);


	})

	window.onload = function() {
		uploader.init(); //初始化
	};

</script>
</body>
</html>
