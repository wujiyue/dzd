<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/easyui.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <title>菜单权限管理</title>
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <style>
    .panel-body {
      padding: 0px;
    }
    table{width:auto;}
    .datagrid-row {
      height: 32px;
    }
    <!--
    .ui-state-default, .ui-widget-content .ui-state-default {
      border: 1px solid #d3d3d3;
      /*background: #e6e6e6 url(ui-bg_glass_75_e6e6e6_1x400.png) 50% 50% repeat-x;*/
      font-weight: normal; color: #555555;
    }
    .ui-state-default a, .ui-state-default a:link, .ui-state-default a:visited {
      color: #555555; text-decoration: none;
    }
    .ui-state-default .ui-icon { background-image: url('/resources/lib/jqueryui/ui-icons_888888_256x240.png')/*{iconsDefault}*/; }
    .ui-icon { width: 16px; height: 16px; background-image: url('/resources/lib/jqueryui/ui-icons_222222_256x240.png')/*{iconsContent}*/; }
    .ui-icon-arrowthick-2-n-s { background-position: -128px -48px; }
    #sortable { list-style-type: none; margin: 0; padding: 0; width: 100%; }
    #sortable li {cursor: pointer; margin: 0 3px 3px 3px; padding: 0.4em; padding-left: 1.5em; font-size: 12px; height: 18px; }
    #sortable li span { position: absolute; margin-left: -1.3em; }
    -->
  </style>
  <script type="text/javascript" src="${ctx}/resources/lib/jqueryui/jquery-ui.min.js?t=20140108"></script>

  <script type="text/javascript">
   var tmp_parentid=0;
   var tmp_parentname;
    function loadTree(){
      $('#trees').tree( {
        url :'${ctx}/sys/permission/json/tree?parentid=0',
        checkbox :false,
        lines:true,
        onBeforeExpand:function(node,param){
          //当节点展开之后触发.
          var id=node.id;

          if(id!='0'&&id!=0){
            $('#trees').tree('options').url = "${ctx}/sys/permission/json/tree?parentid="+id;
          }else{
            return;
          }
        },
        onLoadSuccess:function(){
          tmp_parentname=$('#trees').tree('getRoot').text;
        },
        onClick:function(node){
         var id = node.id;

          tmp_parentname=node.text;
          tmp_parentid=id;
          sys_table_list();
        }
      });
      //rightfrm.location.href="${ctx}/sys/permission/list?parentid=0";
    }
    function refreshTree(){
      $('#trees').tree('options').url = "${ctx}/sys/permission/json/tree?parentid=0";
      $('#trees').tree('reload');
    }
    function collapseAll() {
      var node = $('#trees').tree('getSelected');
      if (node) {
        $('#trees').tree('collapseAll', node.target);
      } else {
        $('#trees').tree('collapseAll');
      }
    }
    //全部收缩
    function expandAll() {
      var node = $('#trees').tree('getSelected');
      if (node) {
        $('#trees').tree('expandAll', node.target);
      } else {
        $('#trees').tree('expandAll');
      }
    }
    function resetFrameHeight(){}


    var validform;
    var table_list_td_width = 100;
    //查询功能的标签说明
    var table_list_query_form = {
      parentid:tmp_parentid
    };
    function add(){
      $("#"+sys_page_config.table_field).val('');
      floatDiv_click("div_show");
      $('#form_show')[0].reset();
      validform.resetForm();
      $(".Validform_checktip").html('');
      $("#parentid").val(tmp_parentid);
      $("#parentname").val(tmp_parentname);
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

        {field:'parentid',title:'权限父ID',align:'center',width:80},
        {field:'parentids',title:'parentids',align:'center',width:80},
        {field:'name',title:'权限名称',align:'center',width:80},
        {field:'code',title:'权限代码',align:'center',width:80},
        {field:'url',title:'权限url',align:'center',width:table_list_td_width},
        {field:'icon',title:'图标',align:'center',width:80},
        {field:'sort',title:'排序',align:'center',width:80},
        {field:'available',title:'是否有效',align:'center',width:80},
        {field:'description',title:'描述',align:'center',width:table_list_td_width}
      ]];
      table_list_query_form.parentid=tmp_parentid;
      table_list_dataGrid.queryParams = table_list_query_form;
      table_list_dataGrid.pagination = true;
      table_list_dataGrid.url = sys_ctx+'/sys/permission/json/find';
      table_list_dataGrid.columns = columns;
      table_list_dataGrid.onClickRow =onClickListRow;
      $("#table_list").datagrid(table_list_dataGrid);
      $("#table_list").datagrid('clearSelections');
    }
    //点击一行事件
    var click_table_line = function(guid){
      $.ajax({
        type:"post",
        url:'${ctx}/sys/permission/json/get/'+guid,
        data:null,
        success:function(json,textStatus){
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
        url:'${ctx}/sys/permission/json/save?'+params,
        data:null,
        success:function(json,textStatus){
          closefloatDiv(sys_page_config.showid);
          broAjaxReturnMsg(json);
          query("form_query");
          refreshTree();
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
            url:'${ctx}/sys/permission/json/removes/'+ids,
            data:null,
            success:function(data,textStatus){
              broAjaxReturnMsg(data);
                query("form_query");
                refreshTree();
            }
            // ,error:ajaxError()
          });
        });
      }else{
        layerMsg_warningIcon("请选择要删除的数据")
      }
    }
    $(function(){
      loadTree();
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


   var sortMenu = function(){
     $.ajax({
       type:"post",
       url:'${ctx}/sys/permission/json/findByParentid/'+tmp_parentid,
       data:null,
       success:function(data,textStatus){
           var rows = JSON.parse(data);
           var str = "";
           for(var i=0;i<rows.length;i++){
             str += '<li class="ui-state-default" id="xh_'+rows[i]['id']+'"><span class="ui-icon ui-icon-arrowthick-2-n-s"></span>'+rows[i]['name']+'</li>';
           };
           $("#sortable").html('').append(str);
           $("#sortable").sortable();
           $("#sortable").disableSelection();
         }

     });
     $("#div_sort").show();
     $('#div_sort').window({
       modal: true,
       shadow: false,
       closed: false,
       top:($(window).height() - $(this).height()) * 0.5+50,
       collapsible: false,
       closable:true,
       minimizable: false,
       maximizable: false,
       resizable: true
     });
   }
 var saveSort = function(){
     var arr = $("#sortable").sortable('toArray');
     //Dumper.alert(arr);
     for(var i=0;i<arr.length;i++){
       arr[i] = arr[i] + "_"+(i+1)
     }
     var sort = arr.join(',');
       $.ajax({
         type:"post",
         url:'${ctx}/sys/permission/json/saveSort',
         data:{sort:sort},
         success:function(data,textStatus){
           broAjaxReturnMsg(data);
           query("form_query");
           refreshTree();
         }

       });
   }

  </script>

</head>
<body>
<!--面包屑导航条-->
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统设置 <span class="c-gray en">&gt;</span> 菜单权限管理<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>

<div style="position:relative;width:100%;height:100%;overflow: hidden">
  <div class="clearfix" style="width:225px;border-right:1px solid #ddd;height:100%;float:left;overflow-y: auto;" id="west">
    <div style="height:5px;margin:5px 0 0 5px;width:220px;height:30px;">
      <a href="#" class="easyui-linkbutton" onClick="refreshTree()" plain="true">刷新</a>
      <a href="#" class="easyui-linkbutton" onClick="expandAll();" plain="true">展开</a>
      <a href="#" class="easyui-linkbutton" onClick="collapseAll();" plain="true">折叠</a>
    </div>
    <div  style="vertical-align: top;margin:0 0 0 5px;width:220px;">
      <div>
        <ul id="trees">

        </ul>
      </div>
    </div>
  </div>
  <div id="mainPanle"width="100%;float:left;"  style="overflow-y: hidden;">
   <%-- <iframe id="rightfrm" width="100%" height="800" scrolling="no" src=""
            marginheight="0" marginwidth="0" frameborder="0" name="rightfrm" class="bgcolor_detail"
            onload="resetFrameHeight();"></iframe>--%>
     <!--工具条-->
     <div class="cl">
       <form id="form_query" >
         <div class="menu-bar-default">
           <a onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
           <a onclick="list_del('table_list')" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
           <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onClick="sortMenu();">排序</a>
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
     <div id="div_show" title="详细信息" style="width:450px;height:270px;display: none;">
       <div class="menu-bar-default">
         <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onClick="add()">新增</a>
         <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onClick="save()">保存</a>
       </div>
       <div class="c_div_show_content">
         <form id="form_show">
           <input type="hidden" id="id" name="id">
           <table  class="c_table_show" border="0" cellpadding="0">
             <tr>
               <td class="c_form_text" width="20%"><span style='color:red;'>*</span>父权限</td>
               <td>
                 <input type="hidden" class="input-text" id="parentid" name="parentid" value="" datatype="*" />
                 <input type="text" class="input-text disabled" readonly id="parentname" name="parentname" value=""/>
                 <div class="Validform_checktip"></div>
               </td>
             <tr>
               <td class="c_form_text" width="20%"><span style='color:red;'>*</span>权限名称</td>
               <td>
                 <input type="text" class="input-text" id="name" name="name" datatype="*" />
                 <div class="Validform_checktip"></div>
               </td>
             <tr>
               <td class="c_form_text" width="20%"><span style='color:red;'>*</span>权限代码</td>
               <td>
                 <input type="text" class="input-text" id="code" name="code" datatype="*" />
                 <div class="Validform_checktip"></div>
               </td>
             <tr>
               <td class="c_form_text" width="20%"><span style='color:red;'>*</span>权限url</td>
               <td>
                 <input type="text" class="input-text" id="url" name="url" datatype="*" />
                 <div class="Validform_checktip"></div>
               </td>
             <tr>
               <td class="c_form_text" width="20%">图标</td>
               <td>
                 <input type="text" class="input-text" id="icon" name="icon" />
                 <div class="Validform_checktip"></div>
               </td>
             <tr>
               <td class="c_form_text" width="20%">排序</td>
               <td>
                 <input type="text" class="input-text" id="sort" name="sort"/>
                 <div class="Validform_checktip"></div>
               </td>
             <tr>
               <td class="c_form_text" width="20%"><span style='color:red;'>*</span>权限描述</td>
               <td>
                 <input type="text" class="input-text" id="description" name="description"/>
                 <div class="Validform_checktip"></div>
               </td>

           </table>
         </form>
       </div>
     </div>


     <div id="div_sort" title="菜单排序" style="width:350px;height:400px;display: none;">
       <div class="c_div_tool_bar">
         <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onClick="saveSort()">保存</a>
       </div>
       <div class="c_div_show_content">
         <form id="form_sort">
           <div id="dd" style="width:100%;">
             <ul id="sortable">

             </ul>
           </div>
         </form>
       </div>
     </div>


  </div>
</div>
</body></html>
