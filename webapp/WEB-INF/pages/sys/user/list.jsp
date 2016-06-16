<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>角色列表</title>
  <script type="text/javascript">

    var validform;


    function getSel(){

    }
    function sys_table_list(){

    }

    function save(){

      var params=$("#form_show").serialize();

      sys_ajaxPost("/base/area/json/save?"+params,null,function(json){


        broAjaxReturnMsg(json);
        // alert(parent.winIndex);
        sys_table_list();


      });
    }
    function reset(){
      validform.resetForm();
    }

    function del(){
      var arr=datagrid.getSelectedRows();

      if(isEmpty(arr)||arr.length>1){
        layer.msg("请您选择1条数据");
        return;
      }
      var ids=arr.join(",");
      layer.confirm("确定这样做吗？", {
        btn: ['确定','取消'],
        shade: [0.1,'#fff']
      }, function(){
        $.ajax({
          type:"post",
          url:'${ctx}/sys/user/json/remove/'+ids,
          data:null,
          beforeSend:function(){
            layer.load(1);
          },
          success:function(data,textStatus){
            layer.closeAll();
            broAjaxReturnMsg(data,parent);
            sys_table_list();
          },
          error:ajaxError
        });
      });
    }
function formatterAge(age){
  if(Number(age)>25){
    return "<font color='red'>"+age+"</font>"
  }else{
    return "<font color='green'>"+age+"</font>"
  }
}
    $(function(){
      sys_table_list();

      var apply_list = '{{each list as his}}'+
              '<li data-apply-id="{{his.id}}">'+
              '<ul class="shop-attr-lst group">'+
              '<li>店铺名:<a href="javascript:;">{{his.shop_name}}</a></li>'+
              '<li>账户余额：{{his.shop_balance}}元</li>'+
              '<li>age：{{his.age | formatterAge}}</li>'+
              '</ul>'+
              '</li>'+
              '{{/each}}';

      var data = {
        list:[
          {"id":1,"shop_name":"123","shop_balance":5000,age:35},
          {"id":2,"shop_name":"12344","shop_balance":500,age:15}
        ]
      }
      template.config("escape", false);
      template.helper('formatterAge',formatterAge);
      var render = template.compile(apply_list);
      var html = render(data);
      $("#content").html("").html(html);
    })
  </script>

</head>
<body>
<%--
<!--面包屑导航条-->
<nav class="breadcrumb"><i class="icon iconfont">&#xf012b;</i><a class="maincolor" href="#">首页</a><span class="c-666 en">&gt;</span>用户<span class="c-666 en">&gt;</span><span class="c-666">用户列表</span></nav>
--%>
<div class="content-wrapper" >
<!--数据表-->
<div id="div_list" class="mt-10">
<!--工具条-->
<div class="btn-group mt-10 cl">
  <div class="l">

    <a onclick="add()" id="addBtn" class="btn btn-success radius">新增</a>

    <a onclick="del()" id="removeBtn"  class="btn btn-danger radius">删除</a>
  </div>

  <!--搜索条-->
  <div class="r">
    <input type="text" id="bro_searchText"  style="margin-right:150px; " class="input-text radius" placeholder="请输入搜索内容"/>
    <input type="button" id="bro_searchBtn" onclick="bro_search()" style="margin-top:-32px;position: relative; " class="btn btn-success radius f-r" value="搜索"/>
  </div>
</div>


  <div class="mt-10">
    <table id="example"></table>
  </div>
</div>
</div>
<div id="content"></div>
</body></html>
