<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <title>组织机构信息维护</title>
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
        url:'${ctx}/org/organization/json/save?'+params,
        data:null,
        success:function(json,textStatus){
          broAjaxReturnMsg(json);
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
<article class="page-container">
  <form action="" id="form_show" method="post" class="form form-horizontal" id="form-admin-role-add">
    <h3 class="text-c mt-15">组织机构信息维护</h3>
    <input type="hidden" value="${organization.id}" id="id" name="id"/>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>组织名称：</label>
      <div class="formControls col-xs-8 col-sm-5">
        <input type="text" class="input-text" value="${organization.name}" placeholder="" id="name" name="name" datatype="*" nullmsg="请输入组织名称">
      </div>
      <div><div class="Validform_checktip"></div></div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">描述：</label>
      <div class="formControls col-xs-8 col-sm-5">
        <textarea rows="3" style="height: 70px;" class="input-text"  placeholder="" id="description" name="description">${organization.description}</textarea>
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">电话：</label>
      <div class="formControls col-xs-8 col-sm-5">
        <input type="text" class="input-text" value="${organization.phone}" placeholder="" id="phone" name="phone">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">传真：</label>
      <div class="formControls col-xs-8 col-sm-5">
        <input type="text" class="input-text" value="${organization.fax}" placeholder="" id="fax" name="fax">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">添加时间：</label>
      <div class="formControls col-xs-8 col-sm-5">
        <input type="text" class="input-text disabled" readonly value="${organization.createTime}" placeholder="" id="createTime" name="createTime">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">更新时间：</label>
      <div class="formControls col-xs-8 col-sm-5">
        <input type="text" class="input-text disabled" readonly value="${organization.updateTime}" placeholder="" id="updateTime" name="updateTime">
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">状态：</label>
      <div class="formControls col-xs-8 col-sm-5">
        <input type="text" class="input-text disabled" readonly value="${organization.available}" placeholder="" id="available" name="available">
      </div>
    </div>
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
        <button type="button" onclick="save()" class="btn btn-primary radius" id="saveBtn" ><i class="icon-ok"></i> 确定</button>
      </div>
    </div>

  </form>
</article>

<script type="text/javascript" src="${ctx}/resources/lib/icheck/jquery.icheck.min.js"></script>

</body>
</html>
