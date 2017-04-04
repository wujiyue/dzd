<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>



<!DOCTYPE html>
<html>
<head>
  <title>新增发送邮件</title>
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

</head>
<body>
<div class="wrapper  animated fadeInRight">
  <div class="container-fluid">
    <form action="" id="form_show" method="post" class="form-horizontal" role="form">
		<h2 class="text-center">新增发送邮件</h2>
		 <div class="form-group">
              <label class="col-sm-3 control-label">组织机构id：</label>
               <div class="col-sm-9">
                  <input type="text" id="orgid" name="orgid" placeholder="组织机构id" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">单据分类 1：采购 2：销售 3：库存 4：账目：</label>
               <div class="col-sm-9">
                  <input type="text" id="djfl" name="djfl" placeholder="单据分类 1：采购 2：销售 3：库存 4：账目" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">单据名称：</label>
               <div class="col-sm-9">
                  <input type="text" id="djmc" name="djmc" placeholder="单据名称" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">单据类别：</label>
               <div class="col-sm-9">
                  <input type="text" id="djlb" name="djlb" placeholder="单据类别" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">自定义前缀：</label>
               <div class="col-sm-9">
                  <input type="text" id="zdy" name="zdy" placeholder="自定义前缀" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">前缀1：</label>
               <div class="col-sm-9">
                  <input type="text" id="qz1" name="qz1" placeholder="前缀1" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">流水长度：</label>
               <div class="col-sm-9">
                  <input type="text" id="lscd" name="lscd" placeholder="流水长度" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">归零设置：</label>
               <div class="col-sm-9">
                  <input type="text" id="glsz" name="glsz" placeholder="归零设置" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">流水起始值：</label>
               <div class="col-sm-9">
                  <input type="text" id="qsz" name="qsz" placeholder="流水起始值" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">是否适用所有单据：</label>
               <div class="col-sm-9">
                  <input type="text" id="djsy" name="djsy" placeholder="是否适用所有单据" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">分隔符：</label>
               <div class="col-sm-9">
                  <input type="text" id="fgf" name="fgf" placeholder="分隔符" class="form-control" />
               </div>
         </div>
         <div class="form-group">
              <label class="col-sm-3 control-label">现在流水号：</label>
               <div class="col-sm-9">
                  <input type="text" id="dqls" name="dqls" placeholder="现在流水号" class="form-control" />
               </div>
         </div>
         		<div class="form-group">
        <div class=" col-sm-10 col-sm-offset-2">
          <button type="submit" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>
      </div>
    </form></div>
</div>

<script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/resources/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
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
			url:'${ctx}/base/receipt/json/save?'+params,
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
                        orgid: {
                            message: '组织机构id验证失败',
                            validators: {
                                notEmpty: {
                                    message: '组织机构id不能为空'
                                }
                            }
                        },
                        djfl: {
                            message: '单据分类 1：采购 2：销售 3：库存 4：账目验证失败',
                            validators: {
                                notEmpty: {
                                    message: '单据分类 1：采购 2：销售 3：库存 4：账目不能为空'
                                }
                            }
                        },
                        djmc: {
                            message: '单据名称验证失败',
                            validators: {
                                notEmpty: {
                                    message: '单据名称不能为空'
                                }
                            }
                        },
                        djlb: {
                            message: '单据类别验证失败',
                            validators: {
                                notEmpty: {
                                    message: '单据类别不能为空'
                                }
                            }
                        },
                        zdy: {
                            message: '自定义前缀验证失败',
                            validators: {
                                notEmpty: {
                                    message: '自定义前缀不能为空'
                                }
                            }
                        },
                        qz1: {
                            message: '前缀1验证失败',
                            validators: {
                                notEmpty: {
                                    message: '前缀1不能为空'
                                }
                            }
                        },
                        lscd: {
                            message: '流水长度验证失败',
                            validators: {
                                notEmpty: {
                                    message: '流水长度不能为空'
                                }
                            }
                        },
                        glsz: {
                            message: '归零设置验证失败',
                            validators: {
                                notEmpty: {
                                    message: '归零设置不能为空'
                                }
                            }
                        },
                        qsz: {
                            message: '流水起始值验证失败',
                            validators: {
                                notEmpty: {
                                    message: '流水起始值不能为空'
                                }
                            }
                        },
                        djsy: {
                            message: '是否适用所有单据验证失败',
                            validators: {
                                notEmpty: {
                                    message: '是否适用所有单据不能为空'
                                }
                            }
                        },
                        fgf: {
                            message: '分隔符验证失败',
                            validators: {
                                notEmpty: {
                                    message: '分隔符不能为空'
                                }
                            }
                        },
                        dqls: {
                            message: '现在流水号验证失败',
                            validators: {
                                notEmpty: {
                                    message: '现在流水号不能为空'
                                }
                            }
                        }
                                            },
                    submitHandler: function (validator, form, submitButton) {
                        alert("submit");
                    }
                }).on('success.form.bv', function (e) {
                    e.preventDefault();
                });

	})
</script>

</body>
</html>
