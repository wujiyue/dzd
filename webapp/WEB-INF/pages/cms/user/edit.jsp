<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>编辑用户</title>
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
        url:'${ctx}/cms/user/json/save?'+params,
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
    <input type="hidden" value="${user.id}" id="id" name="id"/>
      <div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>用户账户:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="account" datatype="*" value="${user.account}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>用户密码:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="password" datatype="*" value="${user.password}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>用户类型:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="usertype" datatype="*" value="${user.usertype}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>昵称:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="nickname" datatype="*" value="${user.nickname}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>备注:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="remark" datatype="*" value="${user.remark}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>性别:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="gender" datatype="*" value="${user.gender}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>生日:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="birthday" datatype="*" value="${user.birthday}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>年龄:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="age" datatype="*" value="${user.age}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>真实姓名:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="realname" datatype="*" value="${user.realname}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>身份证ID:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="idcard" datatype="*" value="${user.idcard}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>故乡:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="hometown" datatype="*" value="${user.hometown}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>地址:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="address" datatype="*" value="${user.address}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>QQ:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="qq" datatype="*" value="${user.qq}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>email:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="email" datatype="*" value="${user.email}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>email验证标志:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="emailflag" datatype="*" value="${user.emailflag}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>手机号码:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="phone" datatype="*" value="${user.phone}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>phone验证标志:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="phoneflag" datatype="*" value="${user.phoneflag}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>该用户创建类型:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="createtype" datatype="*" value="${user.createtype}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>最后登陆IP:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="lastLoginIp" datatype="*" value="${user.lastLoginIp}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>上次登陆时间:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="lastLoginTime" datatype="*" value="${user.lastLoginTime}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="openid" datatype="*" value="${user.openid}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>关注数:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="focus" datatype="*" value="${user.focus}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>粉丝数:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="fans" datatype="*" value="${user.fans}" />
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
