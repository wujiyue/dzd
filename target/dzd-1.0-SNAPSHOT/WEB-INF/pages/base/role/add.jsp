<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>角色新增</title>
  <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
  <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
  <link href="/resources/lib/bootstrap/table/bootstrap-table.min.css" rel="stylesheet">
  <link href="/resources/css/animate.min.css" rel="stylesheet">
  <link href="/resources/css/index/index.css" rel="stylesheet">
  <link href="/resources/css/bro.css" rel="stylesheet">
<style>
  /*权限*/
  dl.permission-list, dl.permission-list2{margin-bottom: 0;}
  .permission-list{ border:solid 1px #eee;}
  .permission-list > dt{ background-color:#efefef;padding:5px 10px}
  .permission-list > dd{ padding:10px; padding-left:30px}
  .permission-list > dd > dl{ border-bottom:solid 1px #eee; padding:5px 0;}
  .permission-list > dd > dl > dt{ display:inline-block;float:left;white-space:nowrap;width:100px}
  .permission-list > dd > dl > dd{ margin-left:100px;}
  .permission-list > dd > dl > dd > label{ padding-right:10px}

</style>

</head>
<body>
<div class="wrapper  animated fadeInRight">
  <div class="container-fluid">
  <form action="" id="form_show" method="post" class="form-horizontal" role="form">
    <h2 class="text-center">新增角色</h2>

    <div class="form-group">
      <label class="control-label col-sm-2"><span class="text-danger">*</span>角色名称：</label>
      <div class="col-sm-10">
        <input type="text" class="form-control"  id="name"  name="name"      />
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-sm-2"><span class="text-danger">*</span>描述：</label>
      <div class="col-sm-10">
        <textarea rows="3" style="height: 70px;" class="form-control"  placeholder="" id="description" name="description"> </textarea>
      </div>
    </div>

    <div class="form-group">
      <label class="control-label col-sm-2">角色授权：</label>
      <div class="col-sm-10">

       <%-- <dl class="permission-list">
          <dt>
            <label>
              <input type="checkbox" value="" name="user-Character-0" id="user-Character-0">
              资讯管理</label>
          </dt>
          <dd>
            <dl class="cl permission-list2">
              <dt>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-0" id="user-Character-0-0">
                  栏目管理</label>
              </dt>
              <dd>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-0">
                  添加</label>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-1">
                  修改</label>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-2">
                  删除</label>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-3">
                  查看</label>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-4">
                  审核</label>
                <label class="c-orange"><input type="checkbox" value="" name="user-Character-0-0-0" id="user-Character-0-0-5"> 只能操作自己发布的</label>
              </dd>
            </dl>
            <dl class="cl permission-list2">
              <dt>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-1" id="user-Character-0-1">
                  文章管理</label>
              </dt>
              <dd>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-0">
                  添加</label>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-1">
                  修改</label>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-2">
                  删除</label>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-3">
                  查看</label>
                <label class="">
                  <input type="checkbox" value="" name="user-Character-0-1-0" id="user-Character-0-1-4">
                  审核</label>
                <label class="c-orange"><input type="checkbox" value="" name="user-Character-0-2-0" id="user-Character-0-2-5"> 只能操作自己发布的</label>
              </dd>
            </dl>
          </dd>
        </dl>--%>

         <c:forEach var="per" varStatus="i" items="${permissions}">
              <dl class="permission-list">
                <dt>
                  <label class="mk">
                    <input type="checkbox" value="${per.id}" name="user-Character-${i.count}" id="user-Character-${i.count}">
                      ${per.name}</label>
                </dt>
                <dd>
                  <c:forEach var="per2" varStatus="j" items="${per.children}">
                        <dl class="cl permission-list2">
                          <dt>
                            <label class="ym">
                              <input type="checkbox" value="${per2.id}" name="user-Character-${i.count}-${j.count}" id="user-Character-${i.count}-${j.count}">
                              ${per2.name}</label>
                          </dt>

                              <dd>
                                <c:forEach var="per3" varStatus="k" items="${per2.children}">
                                  <label class="ff">
                                    <input type="checkbox" value="${per3.id}" name="user-Character-${i.count}-${j.count}-${k.count}" id="user-Character-${i.count}-${j.count}-${k.count}">
                                    ${per3.name}</label>
                                </c:forEach>
                              </dd>

                        </dl>
                  </c:forEach>
                </dd>
              </dl>
       </c:forEach>
      </div>
    </div>
    <div class="form-group">
      <div class=" col-sm-10 col-sm-offset-2">
       <%-- <button type="button" onclick="save()" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>--%>
         <button type="submit" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
      </div>
    </div>
  </form>
  </div>
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

<script type="text/javascript" src="${ctx}/resources/lib/icheck/jquery.icheck.min.js"></script>

<script type="text/javascript">


  function add(){
    $('#form_show')[0].reset();
  }


  function save(){
    var ids="";
    $(".permission-list input:checkbox:checked").each(function(i){
      if(0==i){
        ids = $(this).val();
      }else{
        ids += (","+$(this).val());
      }
    });
    //alert(ids);
    var b=$("#form_show").data('bootstrapValidator').isValid();
    if(!b)
    {
      return;
    }
    var params=$("#form_show").serialize();
    $.ajax({
      type:"post",
      url:'${ctx}/org/role/json/saveRoleAndRolePermissions?'+params+"&ids="+ids,
      data:null,
      success:function(json,textStatus){

        broAjaxReturnMsg(json);
        setTimeout(function(){
          var index = parent.layer.getFrameIndex(window.name);
          parent.layer.close(index);
        },1200);
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
        name: {
          message: '角色名称验证失败',
          validators: {
            notEmpty: {
              message: '角色名称不能为空'
            }
          }
        },
        description: {
          message: '描述验证失败',
          validators: {
            notEmpty: {
              message: '描述不能为空'
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

    $(".permission-list dt input:checkbox").click(function(){
      $(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
    });
    $(".permission-list2 dt input:checkbox").click(function(){
      //$(this).closest("dl").find("dd input:checkbox").prop("checked",$(this).prop("checked"));
      var l=$(this).parent().parent().parent().parent().find("dt input:checked").length;
      if($(this).prop("checked")){
        $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
      }else{
        if(l==0){
          $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
        }
      }
    });
    $(".permission-list2 dd input:checkbox").click(function(){
      var l =$(this).parent().parent().find("input:checked").length;
      var l2=$(this).parents(".permission-list").find(".permission-list2 dd").find("input:checked").length;
      if($(this).prop("checked")){
        $(this).closest("dl").find("dt input:checkbox").prop("checked",true);
        $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",true);
      }
      else{
        if(l==0){
          $(this).closest("dl").find("dt input:checkbox").prop("checked",false);
        }
        if(l2==0){
          $(this).parents(".permission-list").find("dt").first().find("input:checkbox").prop("checked",false);
        }
      }
    });

  })
</script>


</body>
</html>
