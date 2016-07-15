<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/easyui.jsp"%>

<!DOCTYPE html>
<html>
<head>
  <title>角色列表</title>
  <style>
    .panel-body {
      padding: 0px;
    }
    table{width:auto;}
  </style>
  <script type="text/javascript" src="${ctx}/resources/js/H-ui.js"></script>
  <script type="text/javascript" src="${ctx}/resources/js/H-ui.admin.js"></script>
  <script type="text/javascript">
    var validform;
    var table_list_td_width = 100;
    //查询功能的标签说明
    var table_list_query_form = {
      cx_name:''
     };
    function add(){
      $("#"+sys_page_config.table_field).val('');
      floatDiv_click("div_show");
      $('#form_show')[0].reset();
      validform.resetForm();
      $(".Validform_checktip").html('');
    }
    function addNew(){
    layer_show("新增角色",sys_ctx+"/org/role/add",'','','',function(){sys_table_list();});
    }
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
        {field:'id',checkbox:true},//加入属性,width:util_fixWidth(0.25)，限定单元格长度
        {field:'orgid',title:'机构Id',align:'center',width:table_list_td_width},
        {field:'name',title:'角色名称',align:'center',width:table_list_td_width},
        {field:'description',title:'角色描述',align:'center',width:table_list_td_width}
       	]];
      table_list_dataGrid.queryParams = table_list_query_form;
      table_list_dataGrid.pagination = true;//明细默认不显示分页
      table_list_dataGrid.url = sys_ctx+'/org/role/json/find';
      table_list_dataGrid.columns = columns;
      table_list_dataGrid.onClickRow=onClickRow;
      $("#table_list").datagrid(table_list_dataGrid);
      //table_list_dataGrid.onClickRow =onClickListRow;
      $("#table_list").datagrid("clearChecked");
    }
    //点击一行事件
    /*var click_table_line = function(guid){
      $.ajax({
        type:"post",
        url:'${ctx}/org/role/json/get/'+guid,
        data:null,
        success:function(json,textStatus){
          layer.closeAll();
          bind(json,true);
        }
      });
    }*/
    var onClickRow=function(rowIndex, rowData){
      $(this).datagrid("clearSelections").datagrid("selectRecord",rowData[sys_page_config.table_field]);
      if(sys_page_config.showid != ''){

        layer_show("编辑角色",sys_ctx+"/org/role/edit?id="+rowData[sys_page_config.table_field],'','');
      }

    }
    var click_table_line = function(guid){

      /*$.ajax({
        type:"post",
        url:'${ctx}/org/role/json/get/'+guid,
        data:null,
        success:function(json,textStatus){
          layer.closeAll();
          bind(json,true);
        }
      });*/
    }

    function save(){
      var b=validform.check(false);
     if(!b){
       return;
     }
      var params=$("#form_show").serialize();
      $.ajax({
        type:"post",
        url:'${ctx}/org/role/json/save?'+params,
        data:null,
        success:function(json,textStatus){
          closefloatDiv(sys_page_config.showid);
          broAjaxReturnMsg(json);
          query("form_query");
        }
      });
    }
    function search_form_reset(tableid){
      $('#'+tableid)[0].reset()
    }
    function list_del(tableid){
      var selecRow = $("#"+tableid).datagrid("getSelections");
      if(selecRow.length > 0){
        layer.confirm("确定这样做吗？", function(){
          var ids=  sys_checkBoxGuid("table_list");
          $.ajax({
            type:"post",
            url:'${ctx}/org/role/json/removes/'+ids,
            data:null,
            success:function(data,textStatus){
              //layer.closeAll();
              broAjaxReturnMsg(data);
              query("form_query");
            }
           // ,error:ajaxError()
          });
        });
      }else{
        layerMsg_warningIcon("请选择要删除的数据")
      }
    }

    $(function(){

    table_list_td_width = parseFloat(($("#table_list").parent().width()-(33+(6)-1))/6).toFixed(2);
      sys_page_config.table_field = 'id';
      sys_page_config.showid = 'div_show';
      table_list_dataGrid.idField = sys_page_config.table_field;
      sys_table_list();

      validform=$("#form_show").Validform({
        tiptype:3,
        postonce:true,//至提交一次
        ajaxPost:false,//ajax方式提交
        showAllError:true //默认 即逐条验证,true验证全部
      });

    })
  </script>

</head>
<body>
<!--面包屑导航条-->
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 基本设置<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="content-wrapper" >
      <!--工具条-->
      <div class="cl">
          <form id="form_query" >
              <div class="menu-bar-default">
                <a onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
                <a onclick="addNew()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>

                <a onclick="list_del('table_list')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
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
