<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>用户列表</title>
  <style>
    .panel-body {
      padding: 0px;
    }
    table{width:auto;}
  </style>
  <script type="text/javascript">

    var table_list_td_width = 100;
    //查询功能的标签说明
    var table_list_query_form = {
     };

    function getSel(){
      var arr=datagrid.getSelectedRows();
      alert(arr.join(","));
    }
    var sys_table_list = function(){
      var columns = [[
        {field:'id',checkbox:true},//加入属性,width:util_fixWidth(0.25)，限定单元格长度
        {field:'orgId',title:'机构Id',align:'center',width:table_list_td_width},
        {field:'name',title:'角色名称',align:'center',width:table_list_td_width},
        {field:'description',title:'角色描述',align:'center',width:table_list_td_width}
       	]];
      table_list_dataGrid.queryParams = table_list_query_form;
      table_list_dataGrid.pagination = true;//明细默认不显示分页
      table_list_dataGrid.url = sys_ctx+'/sys/role/json/find';
      table_list_dataGrid.columns = columns;
      table_list_dataGrid.onClickRow =onClickListRow;
      $("#table_list").datagrid(table_list_dataGrid);
    }
    //点击一行事件
    var click_table_line = function(guid){
      alert(guid);
      sys_ajaxPost("/sys/role/json/get/"+guid,function(json){
        alert(JSON.stringify(json));
        bind(json,true);
      });
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

    $(function(){
      table_list_td_width = parseFloat(($("#table_list").parent().width()-(33+(6)-1))/6).toFixed(2);
      sys_page_config.table_field = 'id';
      sys_page_config.showid = 'div_show';
      table_list_dataGrid.idField = sys_page_config.table_field;
      sys_table_list();

    })
  </script>

</head>
<body>
<!--面包屑导航条-->
<nav class="breadcrumb"><i class="icon iconfont">&#xf012b;</i><a class="maincolor" href="#">首页</a><span class="c-666 en">&gt;</span>用户<span class="c-666 en">&gt;</span><span class="c-666">用户列表</span></nav>
<div class="content-wrapper" >
<!--数据表-->
<div  class="mt-10">
<!--工具条-->
<div class="btn-group mt-10 cl">
  <div class="l">

    <a onclick="add()" id="addBtn" class="btn btn-success radius">新增</a>

    <%-- <a onclick="getSel()" id="testBtn"  class="btn btn-danger radius">获得选中行</a>--%>
    <a onclick="del()" id="removeBtn"  class="btn btn-danger radius">删除</a>
  </div>
  <div id="gaoji_hidden" style="display: none" class="l ml-15">
    <label for="bro_showDeleted" class="mr-15 va-m f-14 c-warning" ><input onchange="bro_showDeleted()" id="bro_showDeleted" type="checkbox"/>显示已删除的</label>
    <a onclick="bro_findBack()" id="bro_findBackBtn"  class="btn disabled radius">找回</a>
    <a onclick="bro_deleteEver()" id="bro_deleteEverBtn"  class="btn btn-danger">永久删除</a>
  </div>
  <!--搜索条-->
  <div class="r">
    <input type="text" id="bro_searchText"  style="margin-right:150px; " class="input-text radius" placeholder="请输入搜索内容"/>
    <input type="button" id="bro_searchBtn" onclick="bro_search()" style="margin-top:-32px;position: relative; " class="btn btn-success radius f-r" value="搜索"/>
  </div>
</div>


    <div class="mt-10">
      <div id="div_list" class="c_div_list_content" style="width:100%;overflow:auto;">
        <table id="table_list" width="100%"></table>
      </div>

          <div id="div_show" title="详细信息" style="width:450px;height:270px;display: none;">
            <div class="c_div_tool_bar">
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onClick="add()">新增</a>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onClick="save()">保存</a>
            </div>
            <div class="c_div_show_content">
              <form id="form_show">
                <input type="hidden" id="id" name="id">

                <table width="100%" class="c_table_show" border="0" cellpadding="0">
                  <tr>
                    <td class="c_form_text" width="20%">机构ID</td>
                    <td>
                      <input type="text" id="orgId" name="orgId" maxlength="50"  />
                    </td>
                  </tr>
                  <tr>
                    <td class="c_form_text" width="20%">角色名称</td>
                    <td>
                      <input type="text" id="name" name="name" maxlength="50"  />
                    </td>
                  </tr>
                  <tr>
                    <td class="c_form_text" width="20%">角色描述</td>
                    <td>
                      <input type="text" id="description" name="description" maxlength="50"  />
                    </td>
                  </tr>

                </table>
              </form>
            </div>
          </div>
    </div>
</div>
</div>

</body></html>
