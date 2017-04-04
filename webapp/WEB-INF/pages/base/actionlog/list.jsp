<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/easyui.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>操作日志管理</title>
    <meta name="Keywords" content="">
    <meta name="Description" content="">
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
    };
    function add(){
          $("#"+sys_page_config.table_field).val('');
          floatDiv_click("div_show");
          $('#form_show')[0].reset();
          validform.resetForm();
          $(".Validform_checktip").html('');
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
            
			{field:'yhid',title:'请求人Id',align:'center',width:table_list_td_width},
			{field:'yhmc',title:'请求人',align:'center',width:table_list_td_width},
			{field:'uri',title:'请求的uri',align:'center',width:table_list_td_width},
			{field:'params',title:'请求参数',align:'center',width:table_list_td_width},
			{field:'method',title:'方法',align:'center',width:table_list_td_width},
			{field:'description',title:'描述',align:'center',width:table_list_td_width},
			{field:'result',title:'执行结果',align:'center',width:table_list_td_width},
			{field:'actionTime',title:'消耗的时间',align:'center',width:table_list_td_width},
			{field:'type',title:'类型',align:'center',width:table_list_td_width}
           	]];
          table_list_dataGrid.queryParams = table_list_query_form;
          table_list_dataGrid.pagination = true;
          table_list_dataGrid.url = sys_ctx+'/base/actionlog/json/find';
          table_list_dataGrid.columns = columns;
          table_list_dataGrid.onClickRow =onClickListRow;
          $("#table_list").datagrid(table_list_dataGrid);
     }
     //点击一行事件
     var click_table_line = function(guid){
           $.ajax({
             type:"post",
             url:'${ctx}/base/actionlog/json/get/'+guid,
             data:null,
             success:function(json,textStatus){
               layer.closeAll();
               bind(json,true);
             }
           });
     }
     function save(){
          var b=validform.check(false);
          if(!b){
            return;
          }
           var params=$("#form_show").serialize();
           $.ajax({
             type:"post",
             url:'${ctx}/base/actionlog/json/save?'+params,
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
             layer.confirm("确定这样做吗？", {
               btn: ['确定','取消'],
               shade: [0.1,'#fff']
             }, function(){
               var ids=  sys_checkBoxGuid("table_list");
               $.ajax({
                 type:"post",
                 url:'${ctx}/base/actionlog/json/removes/'+ids,
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 操作日志管理<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="content-wrapper" >
      <!--工具条-->
      <div class="cl">
          <form id="form_query" >
              <div class="menu-bar-default">
                <%--<a onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>--%>
                <a onclick="list_del('table_list')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
                <a id="open_search"  onclick="javascript:$('#search_hidden').show();$(this).hide();" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
              </div>
              <div id="search_hidden"  style="display: none" class="menu-bar-default">
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
      <div id="div_show" title="详细信息" style="width:450px;height:370px;display: none;">
            <div class="menu-bar-default">
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onClick="add()">新增</a>
              <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onClick="save()">保存</a>
            </div>
            <div class="c_div_show_content">
              <form id="form_show">
                <input type="hidden" id="id" name="id">
                <table  class="c_table_show" border="0" cellpadding="0">
                <tr>
					<td class="c_form_text" width="20%"><span style='color:red;'>*</span>请求人Id</td>
					<td>
						<input type="text" class="input-text" id="yhid" name="yhid" datatype="*" />
						<div class="Validform_checktip"></div>
					</td>
				<tr>
					<td class="c_form_text" width="20%"><span style='color:red;'>*</span>请求人</td>
					<td>
						<input type="text" class="input-text" id="yhmc" name="yhmc" datatype="*" />
						<div class="Validform_checktip"></div>
					</td>
				<tr>
					<td class="c_form_text" width="20%"><span style='color:red;'>*</span>请求的uri</td>
					<td>
						<input type="text" class="input-text" id="uri" name="uri" datatype="*" />
						<div class="Validform_checktip"></div>
					</td>
				<tr>
					<td class="c_form_text" width="20%"><span style='color:red;'>*</span>请求参数</td>
					<td>
						<input type="text" class="input-text" id="params" name="params" datatype="*" />
						<div class="Validform_checktip"></div>
					</td>
				<tr>
					<td class="c_form_text" width="20%"><span style='color:red;'>*</span>方法</td>
					<td>
						<input type="text" class="input-text" id="method" name="method" datatype="*" />
						<div class="Validform_checktip"></div>
					</td>
				<tr>
					<td class="c_form_text" width="20%"><span style='color:red;'>*</span>描述</td>
					<td>
						<input type="text" class="input-text" id="description" name="description" datatype="*" />
						<div class="Validform_checktip"></div>
					</td>
				<tr>
					<td class="c_form_text" width="20%"><span style='color:red;'>*</span>执行结果</td>
					<td>
						<input type="text" class="input-text" id="result" name="result" datatype="*" />
						<div class="Validform_checktip"></div>
					</td>
				<tr>
					<td class="c_form_text" width="20%"><span style='color:red;'>*</span>消耗的时间</td>
					<td>
						<input type="text" class="input-text" id="actionTime" name="actionTime" datatype="*" />
						<div class="Validform_checktip"></div>
					</td>
				<tr>
					<td class="c_form_text" width="20%"><span style='color:red;'>*</span>类型</td>
					<td>
						<input type="text" class="input-text" id="type" name="type" datatype="*" />
						<div class="Validform_checktip"></div>
					</td>
				
                </table>
              </form>
            </div>
      </div>
</div>
</body></html>
