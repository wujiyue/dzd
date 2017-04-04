<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <title>角色新增</title>
  <script type="text/javascript" src="${ctx}/resources/js/H-ui.admin.js"></script>
  <script type="text/javascript">
    var validform;
    var table_list_td_width = 100;

    function add(){

      $('#form_show')[0].reset();
      validform.resetForm();
      $(".Validform_checktip").html('');
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

      var b=validform.check(false);
     if(!b){
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

      validform=$("#form_show").Validform({
        tiptype:2,
        postonce:true,//至提交一次
        ajaxPost:false,//ajax方式提交
        showAllError:true //默认 即逐条验证,true验证全部
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

</head>
<body>
<article class="page-container">

  <form action="" id="form_show" method="post" class="form form-horizontal" id="form-admin-role-add">
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
      <div class="formControls col-xs-8 col-sm-5">
        <input type="text" class="input-text" value="" placeholder="" id="name" name="name" datatype="*4-16" nullmsg="请输入角色名称">
      </div>
      <div><div class="Validform_checktip"></div></div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">描述：</label>
      <div class="formControls col-xs-8 col-sm-5">
        <textarea rows="3" style="height: 70px;" class="input-text" value="" placeholder="" id="description" name="description"></textarea>
      </div>
    </div>
    <div class="row cl">
      <label class="form-label col-xs-4 col-sm-3">角色授权：</label>
      <div class="formControls col-xs-8 col-sm-9">

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
    <div class="row cl">
      <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
        <button type="button" onclick="save()" class="btn btn-success radius" id="admin-role-save" name="admin-role-save"><i class="icon-ok"></i> 确定</button>
      </div>
    </div>
  </form>
</article>

<script type="text/javascript" src="${ctx}/resources/lib/icheck/jquery.icheck.min.js"></script>

</body>
</html>
