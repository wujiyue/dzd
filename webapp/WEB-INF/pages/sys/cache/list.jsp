<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/easyui.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <title>缓存列表</title>
  <style>
    .panel-body {
      padding: 0px;
    }
    table{width:auto;}
  </style>
  <script type="text/javascript">
    var validform;
    var table_list_td_width = 100;
    //查询功能的标签说明
    var table_list_query_form = {
      cachetype:''
     };

    //根据表单查询
    var query = function(formid){
      var qArr = $("#"+formid)[0];//查询表单区域序列化重写
      for(var i=0;i<qArr.length;i++){
        var id = qArr[i].id;
        if(typeof table_list_query_form[id] != 'undefined')
          table_list_query_form[id] = $("#"+id).val();
      }
      $("#table_list").datagrid("clearChecked");
      sys_table_list();
    }

    var sys_table_list = function(){
      var columns = [[

        {field:'key',title:'键',align:'left',width:225},
        {field:'value',title:'值',align:'left',width:500},
        {field:'hittimes',title:'命中次数',align:'center',width:80},
        {field:'lastaccesstime',title:'上次访问时间',align:'center',width:table_list_td_width},
        {field:'lastupdatetime',title:'上次更新时间',align:'center',width:table_list_td_width},
        {field:'creattime',title:'创建时间',align:'center',width:table_list_td_width},
        {field:'size',title:'大小',align:'center',width:table_list_td_width},
        {field:'proprotion',title:'比率',align:'center',width:table_list_td_width}

       	]];
      table_list_dataGrid.queryParams = table_list_query_form;
      table_list_dataGrid.pagination = true;//明细默认不显示分页
      table_list_dataGrid.url = sys_ctx+'/sys/cache/json/getCacheInfo';
      table_list_dataGrid.columns = columns;
      table_list_dataGrid.onClickRow =onClickListRow;
      $("#table_list").datagrid(table_list_dataGrid);
    }
    //点击一行事件
    var click_table_line = function(guid){
      $.ajax({
        type:"post",
        url:'${ctx}/org/role/json/get/'+guid,
        data:null,
        success:function(json,textStatus){
          layer.closeAll();
          bind(json,true);
        }
      });
    }


    $(function(){

    table_list_td_width = parseFloat(($("#table_list").parent().width()-(33+(6)-1))/6).toFixed(2);
      sys_page_config.table_field = 'key';
      sys_page_config.showid = 'div_show';
      table_list_dataGrid.idField = sys_page_config.table_field;
      sys_table_list();

    })
  </script>

</head>
<body>
<!--面包屑导航条-->
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 系统缓存<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="content-wrapper" >
      <!--工具条-->
      <div class="cl">
          <form id="form_query" >
              <div class="menu-bar-default">
               <select class="select" style="width:175px;padding-left:8px;vertical-align:middle;" id="cachetype" name="cachetype">
                 <option value="">--请选择--</option>
                 <option value="0">系统缓存</option>
                 <option value="1">用户缓存</option>
               </select>
                <a id="open_search"  onclick="javascript:$('#search_hidden').show();$(this).hide();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
              </div>
              <div id="search_hidden"  style="display: none" class="menu-bar-default">
                <input type="text" class="input-text size-S" style="display:inline-block;width:175px;" id="cx_name" name="cx_name"    placeholder="角色名称"/>
                <a  onclick="query('form_query');" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" id="btn_reset" onClick="search_form_reset('form_query')">重置</a>
                <a  onclick="javascript:$('#search_hidden').hide();$('#open_search').show();" class="easyui-linkbutton" data-options="iconCls:'icon-back',plain:true">关闭</a>
              </div>
          </form>
      </div>
      <!--数据列表-->
      <div id="div_list"  style="width:100%;overflow:auto;padding:0;clear:both;border: 0px;">
        <table id="table_list" width="100%"></table>
      </div>

    <div id="div_show" title="详细信息" style="width:450px;height:270px;display: none;">
            <div class="menu-bar-default">
              <a href="javascript:void(0)" id="resetBtn"  class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"  onclick="add()">新增</a>
              <a href="javascript:void(0)" id="saveBtn"  class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="save()" >保存</a>

            </div>
            <div class="c_div_show_content">
              <form id="form_show">
                <input type="hidden" id="id" name="id">

                <table  class="c_table_show" border="0" cellpadding="0">
                  <tr style="display: none;">
                    <td class="c_form_text" width="20%">机构ID</td>
                    <td>
                      <input type="hidden" value="0" id="orgid" name="orgid" maxlength="50"  />
                    </td>
                  </tr>
                  <tr>
                    <td class="c_form_text" width="20%"><label for="name"><font color="red">*</font> 角色名称</label></td>
                    <td>
                      <input type="text" id="name" name="name" datatype="*" class="input-text size-S" nullmsg="请输入角色名称！"/>
                      <div class="Validform_checktip"></div>
                    </td>
                  </tr>
                  <tr>
                    <td class="c_form_text" width="20%"><font color="red">*</font>角色描述</td>
                    <td>
                     <%-- <input type="text" id="description" name="description"  datatype="*" nullmsg="请输入角色描述！" />--%>
                      <textarea id="description" rows="3" style="width: 150px;height:90px;" class="input-text  size-S" name="description"  datatype="*" nullmsg="请输入角色描述！"></textarea>
                      <div class="Validform_checktip"></div>
                    </td>
                  </tr>

                </table>
              </form>
            </div>
      </div>

</div>

</body>
</html>
