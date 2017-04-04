<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>



<!DOCTYPE html>
<html>
<head>
  <title>编辑生成配置</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
	<meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

	<link rel="shortcut icon" href="favicon.ico">

	<link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
	<link href="/resources/lib/bootstrap/table/bootstrap-table.min.css" rel="stylesheet">
	<link href="/resources/css/animate.min.css" rel="stylesheet">
	<link href="/resources/css/index/index.css" rel="stylesheet">
	<link href="/resources/css/bro.css" rel="stylesheet">

    <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
</head>
<body>
<div class="wrapper  animated fadeInRight">
  <div class="container-fluid">
    <form action="" id="form_show" method="post" class="form-horizontal" role="form">
        <input type="hidden" value="${config.id}" id="id" name="id"/>
		<h2 class="text-center">编辑生成配置</h2>
		 <div class="form-group">
              <label class="col-sm-3 control-label">所属类型：</label>
               <div class="col-sm-9">
                <%--  <input type="text" id="section" name="section" placeholder="所属类型" value="${config.section}" class="form-control" />--%>
                    <bro:dicSelect id="section"  value="${config.section}"  ></bro:dicSelect>
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">配置名称：</label>
               <div class="col-sm-9">
                  <input type="text" id="name" name="name" placeholder="配置名称" value="${config.name}" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">配置值：</label>
               <div class="col-sm-9">
                  <input type="text" id="value" name="value" placeholder="配置值" value="${config.value}" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">说明：</label>
               <div class="col-sm-9">
                 <%-- <input type="text" id="description" name="description" placeholder="说明" value="${config.description}" class="form-control" />--%>
                     <textarea rows="3" style="height: 70px;" class="form-control"  placeholder="" id="description" name="description"> ${config.description}</textarea>

               </div>
         </div>
         		<div class="form-group">
        <div class=" col-sm-10 col-sm-offset-2">
          <button type="submit" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>
      </div>
    </form></div>
</div>


<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/resources/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>

<script type="text/javascript">

	function save(){

		var b=$("#form_show").data('bootstrapValidator').isValid();
		if(!b)
		{
			return;
		}
		var params=$("#form_show").serialize();
		$.ajax({
			type:"post",
			url:'${ctx}/gen/config/json/save?'+params,
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

		$('form').bootstrapValidator({
                    message: 'This value is not valid',
                    excluded: [':disabled'],
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },

                    fields: {
        section: {
                            message: '所属类型验证失败',
                            validators: {
                                notEmpty: {
                                    message: '所属类型不能为空'
                                }
                            }
                        },
                        name: {
                            message: '配置名称验证失败',
                            validators: {
                                notEmpty: {
                                    message: '配置名称不能为空'
                                }
                            }
                        },
                    /*    value: {
                            message: '配置值验证失败',
                            validators: {
                                notEmpty: {
                                    message: '配置值不能为空'
                                }
                            }
                        },*/
                        description: {
                            message: '说明验证失败',
                            validators: {
                                notEmpty: {
                                    message: '说明不能为空'
                                }
                            }
                        }
                                            },
                    submitHandler: function (validator, form, submitButton) {
                        alert("submit");
                    }
                }).on('success.form.bv', function (e) {
                    e.preventDefault();
                    save();
                });

	})
</script>

</body>
</html>
