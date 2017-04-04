<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.EhCacheUtils" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">


  <title>H+ 后台主题UI框架 - Bootstrap Table</title>
  <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
  <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

  <link rel="shortcut icon" href="favicon.ico">

  <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
  <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
  <link href="/resources/lib/bootstrap/table/bootstrap-table.min.css" rel="stylesheet">
  <link href="/resources/css/animate.min.css" rel="stylesheet">
  <link href="/resources/css/index/index.css" rel="stylesheet">
  <link href="/resources/css/bro.css" rel="stylesheet">

</head>

<body class="gray-bg">

<!--面包屑导航条-->
<nav class="breadcrumb"><i class="fa fa-home"></i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 基本设置</nav>
<div class="wrapper wrapper-content animated fadeInRight">
      <!--工具条-->
      <div class="cl">
          <form id="form_query" style="margin-bottom: 15px">
              <div class="menu-bar-default">

                <a onclick="addNew()" class="btn btn-sm btn-primary"><i class="fa fa-plus"></i>&nbsp;新增</a>

                <a onclick="list_del('tableList')" class="btn btn-sm btn-danger" ><i class="fa fa-minus"></i>&nbsp;删除</a>
                  <a id="open_search"  onclick="javascript:$('#search_hidden').show();$(this).hide();" class="btn btn-sm btn-success" ><i class="fa fa-search"></i>&nbsp;搜索</a>
                 </div>
              <div id="search_hidden"  style="display: none;margin-top: 8px" class="menu-bar-default">
                <input type="text" class="form-control" style="display:inline-block;width:175px;" id="cx_name" name="cx_name"    placeholder="角色名称"/>
                <a  onclick="query('form_query');" class="btn btn-sm btn-success" ><i class="fa fa-search"></i>&nbsp;搜索</a>
                <a href="javascript:void(0)"  id="btn_reset" onClick="search_form_reset('form_query')" class="btn btn-sm btn-info"><i class="fa fa-reply"></i>&nbsp;重置</a>
                <a  onclick="javascript:$('#search_hidden').hide();$('#open_search').show();" class="btn btn-default btn-sm"><i class="fa fa-close"></i>&nbsp;关闭</a>
              </div>
          </form>
      </div>
      <!--数据列表-->
  <div class="table-responsive"><table id="tableList" class="table table-striped"></table> </div>
</div>
<script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>

<script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>

<script type="text/javascript" >
  var validform;
var queryStr="?";
  //查询功能的标签说明
  var table_list_query_form = {
    cx_name:''
  };

  function refreshData() {
    $('#tableList').bootstrapTable('refresh');
  }
  $(function(){
    sys_table_list();
  });
function sys_table_list(){
  $('#tableList').bootstrapTable('destroy');
    var columns=[{checkbox:true},{
      field: 'id',
      align:"center",
      sortable:true, //是否允许排序
      order:"asc", //默认排序
      visible:false,
      title: '序号'
    }, {
      field: 'orgid',
      align:"center",
      visible:false,
      title: '机构Id'
    },{
      field: 'name',
      align:"center",
      sortable:true, //是否允许排序
      width:'25%',
      visible:true,
      title: '角色名称'
    }, {
      field: 'description',
      title: '描述',
      formatter:function(value, row, index){
        value = '<font color="gray">'+value+'</font>';
        return value;
      }
    }];
  table_list_Params.columns=columns;
  table_list_Params.onClickRow=onClickRow;
  table_list_Params.url='${ctx}/org/role/json/find'+queryStr;
  $('#tableList').bootstrapTable(table_list_Params);
}
  var onClickRow=function(row,tr){
      layer_show("编辑角色",sys_ctx+"/org/role/edit?id="+row.id,'','','',function(){sys_table_list();});
  }
  function addNew(){
    layer_show("新增角色",sys_ctx+"/org/role/add",'','','',function(){refreshData();});
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
  function list_del(tableid){
    var selecRow = $("#"+tableid).bootstrapTable('getSelections');
    if(selecRow.length > 0){
      layer.confirm("确定这样做吗？", function(){
        var ids = new Array();
        for(var i=0;i<selecRow.length;i++){
          ids[ids.length] = selecRow[i]["id"]
        }

        $.ajax({
          type:"post",
          url:'${ctx}/org/role/json/removes/'+ids,
          data:null,
          success:function(data,textStatus){
            //layer.closeAll();
            broAjaxReturnMsg(data);
           // query("form_query");
            refreshData();
          }
          // ,error:ajaxError()
        });
      });
    }else{
      layerMsg_warningIcon("请选择要删除的数据")
    }
  }

  //根据表单查询
  var query = function(formid){
    queryStr="?";
    var qArr = $("#"+formid)[0];//查询表单区域序列化重写
    var queryStrTem="";
    for(var i=0;i<qArr.length;i++){
      var id = qArr[i].id;
      if(typeof table_list_query_form[id] != 'undefined')
      {
        table_list_query_form[id] = $("#"+id).val();
        queryStrTem+="&"+id+"="+$("#"+id).val();
      }

    }
    queryStrTem=queryStrTem.substring(1);
    queryStr+=queryStrTem;
   // alert(queryStr);
   sys_table_list();
    //refreshData();
  }
  function search_form_reset(tableid){
    $('#'+tableid)[0].reset()
  }

</script>
</body>
</html>
