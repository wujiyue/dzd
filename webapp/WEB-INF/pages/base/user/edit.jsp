<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>



<!DOCTYPE html>
<html>
<head>
  <title>编辑系统用户</title>
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
		<h2 class="text-center">编辑系统用户</h2>
		<input type="hidden" id="id" name="id" value="${user.id}">
		          <%--<div class="form-group">
                    <label class="col-sm-3 control-label">机构ID：</label>
                    <div class="col-sm-9">
                                <input type="text" id="orgid" name="orgid" placeholder="机构ID"  class="form-control" />
                    </div>
                    </div>--%>
                  <div class="form-group">
              <label class="col-sm-3 control-label">用户账户：</label>
               <div class="col-sm-9">
                                <input type="text" id="account" name="account" placeholder="用户账户" value="${user.account}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">用户密码：</label>
               <div class="col-sm-9">
                                <input type="text" id="password" name="password" placeholder="用户密码" value="${user.password}"  class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">用户类型：</label>
               <div class="col-sm-9">
                                <input type="text" id="usertype" name="usertype" placeholder="用户类型"  value="${user.usertype}"  class="form-control" />
                              </div>
         </div>
              <div class="form-group">
                  <label class="col-sm-3 control-label">昵称：</label>
                   <div class="col-sm-9">
                       <input type="text" id="nickname" name="nickname" placeholder="昵称"  value="${user.nickname}" class="form-control" />
                   </div>
              </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">备注：</label>
               <div class="col-sm-9">
                                <input type="text" id="remark" name="remark" placeholder="备注"  value="${user.remark}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">性别：</label>
               <div class="col-sm-9">
                                <input type="text" id="gender" name="gender" placeholder="性别"  value="${user.gender}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">生日：</label>
               <div class="col-sm-9">
                                <input type="text" id="birthday" name="birthday" placeholder="生日"  value="${user.birthday}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">年龄：</label>
               <div class="col-sm-9">
                                <input type="text" id="age" name="age" placeholder="年龄"  value="${user.age}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">真实姓名：</label>
               <div class="col-sm-9">
                                <input type="text" id="realname" name="realname" placeholder="真实姓名"  value="${user.realname}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">身份证ID：</label>
               <div class="col-sm-9">
                                <input type="text" id="idcard" name="idcard" placeholder="身份证ID"   value="${user.idcard}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">故乡：</label>
               <div class="col-sm-9">
                                <input type="text" id="hometown" name="hometown" placeholder="故乡"  value="${user.hometown}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">地址：</label>
               <div class="col-sm-9">
                                <input type="text" id="address" name="address" placeholder="地址"  value="${user.address}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">QQ：</label>
               <div class="col-sm-9">
                                <input type="text" id="qq" name="qq" placeholder="QQ"  value="${user.qq}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">email：</label>
               <div class="col-sm-9">
                                <input type="text" id="email" name="email" placeholder="email"  value="${user.email}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">email验证标志：</label>
               <div class="col-sm-9">
                                <input type="text" id="emailflag" name="emailflag" placeholder="email验证标志"  value="${user.emailflag}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">手机号码：</label>
               <div class="col-sm-9">
                                <input type="text" id="phone" name="phone" placeholder="手机号码"  value="${user.phone}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">phone验证标志：</label>
               <div class="col-sm-9">
                                <input type="text" id="phoneflag" name="phoneflag" placeholder="phone验证标志"   value="${user.phoneflag}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">该用户创建类型：</label>
               <div class="col-sm-9">
                                <input type="text" id="createtype" name="createtype" placeholder="该用户创建类型"  value="${user.createtype}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">最后登陆IP：</label>
               <div class="col-sm-9">
                                <input type="text" id="lastLoginIp" name="lastLoginIp" placeholder="最后登陆IP"  value="${user.lastLoginIp}" class="form-control" />
                              </div>
         </div>
                  <div class="form-group">
              <label class="col-sm-3 control-label">上次登陆时间：</label>
               <div class="col-sm-9">
                                <input type="text" id="lastLoginTime" name="lastLoginTime" placeholder="上次登陆时间"  value="${user.lastLoginTime}" class="form-control" />
                              </div>
         </div>
         <%--         <div class="form-group">
              <label class="col-sm-3 control-label">openid：</label>
               <div class="col-sm-9">
                                <input type="text" id="openid" name="openid" placeholder=""  value="${user.usertype}" class="form-control" />
                              </div>
         </div>--%>
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
			url:'${ctx}/org/user/json/save?'+params,
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
                            message: '机构ID验证失败',
                            validators: {
                                notEmpty: {
                                    message: '机构ID不能为空'
                                }
                            }
                        },
                                    account: {
                            message: '用户账户验证失败',
                            validators: {
                                notEmpty: {
                                    message: '用户账户不能为空'
                                }
                            }
                        },
                                    password: {
                            message: '用户密码验证失败',
                            validators: {
                                notEmpty: {
                                    message: '用户密码不能为空'
                                }
                            }
                        },
                                    usertype: {
                            message: '用户类型验证失败',
                            validators: {
                                notEmpty: {
                                    message: '用户类型不能为空'
                                }
                            }
                        },
                                    nickname: {
                            message: '昵称验证失败',
                            validators: {
                                notEmpty: {
                                    message: '昵称不能为空'
                                }
                            }
                        },
                                    remark: {
                            message: '备注验证失败',
                            validators: {
                                notEmpty: {
                                    message: '备注不能为空'
                                }
                            }
                        },
                                    gender: {
                            message: '性别验证失败',
                            validators: {
                                notEmpty: {
                                    message: '性别不能为空'
                                }
                            }
                        },
                                    birthday: {
                            message: '生日验证失败',
                            validators: {
                                notEmpty: {
                                    message: '生日不能为空'
                                }
                            }
                        },
                                    age: {
                            message: '年龄验证失败',
                            validators: {
                                notEmpty: {
                                    message: '年龄不能为空'
                                }
                            }
                        },
                                    realname: {
                            message: '真实姓名验证失败',
                            validators: {
                                notEmpty: {
                                    message: '真实姓名不能为空'
                                }
                            }
                        },
                                    idcard: {
                            message: '身份证ID验证失败',
                            validators: {
                                notEmpty: {
                                    message: '身份证ID不能为空'
                                }
                            }
                        },
                                    hometown: {
                            message: '故乡验证失败',
                            validators: {
                                notEmpty: {
                                    message: '故乡不能为空'
                                }
                            }
                        },
                                    address: {
                            message: '地址验证失败',
                            validators: {
                                notEmpty: {
                                    message: '地址不能为空'
                                }
                            }
                        },
                                    qq: {
                            message: 'QQ验证失败',
                            validators: {
                                notEmpty: {
                                    message: 'QQ不能为空'
                                }
                            }
                        },
                                    email: {
                            message: 'email验证失败',
                            validators: {
                                notEmpty: {
                                    message: 'email不能为空'
                                }
                            }
                        },
                                    emailflag: {
                            message: 'email验证标志验证失败',
                            validators: {
                                notEmpty: {
                                    message: 'email验证标志不能为空'
                                }
                            }
                        },
                                    phone: {
                            message: '手机号码验证失败',
                            validators: {
                                notEmpty: {
                                    message: '手机号码不能为空'
                                }
                            }
                        },
                                    phoneflag: {
                            message: 'phone验证标志验证失败',
                            validators: {
                                notEmpty: {
                                    message: 'phone验证标志不能为空'
                                }
                            }
                        },
                                    createtype: {
                            message: '该用户创建类型验证失败',
                            validators: {
                                notEmpty: {
                                    message: '该用户创建类型不能为空'
                                }
                            }
                        },
                                    lastLoginIp: {
                            message: '最后登陆IP验证失败',
                            validators: {
                                notEmpty: {
                                    message: '最后登陆IP不能为空'
                                }
                            }
                        },
                                    lastLoginTime: {
                            message: '上次登陆时间验证失败',
                            validators: {
                                notEmpty: {
                                    message: '上次登陆时间不能为空'
                                }
                            }
                        },
                                    openid: {
                            message: '验证失败',
                            validators: {
                                notEmpty: {
                                    message: '不能为空'
                                }
                            }
                        },
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
