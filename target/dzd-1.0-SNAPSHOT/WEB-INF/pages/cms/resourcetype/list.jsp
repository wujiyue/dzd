<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.EhCacheUtils" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>资源分类管理</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
    <link href="/resources/lib/bootstrap/table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${ctx}/resources/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>

    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/index/index.css" rel="stylesheet">
    <link href="/resources/css/bro.css" rel="stylesheet">
</head>
<body class="gray-bg">
<!--面包屑导航条-->
<nav class="breadcrumb"><i class="fa fa-home"></i> 首页 <span class="c-gray en">&gt;</span> CMS <span class="c-gray en">&gt;</span> 资源分类管理</nav>
<div class="wrapper  animated fadeInRight">
    <div style="position:relative;width:100%;height:auto;overflow: hidden">

        <div class="clearfix" style="width:225px;border-right:1px solid #ddd;height:auto;float:left;overflow-y: auto;" id="west">
            <div style="height:5px;margin:5px 0 0 0px;width:220px;height:30px;">
                <a href="#" class="easyui-linkbutton" onClick="refreshTree()" plain="true">刷新</a>
                <a href="#" class="easyui-linkbutton" onClick="expandAll();" plain="true">展开</a>
                <a href="#" class="easyui-linkbutton" onClick="collapseAll();" plain="true">折叠</a>
            </div>
            <div  style="vertical-align: top;margin:0 0 0 0px;width:220px;">
                <div>
                    <ul  class="ztree"  id="trees">

                    </ul>
                </div>
            </div>
        </div>

        <div id="mainPanle"width="100%;float:left;" style="overflow-y: hidden;">
            <!--工具条-->
            <div class="cl">
                <form id="form_query" style="margin-bottom: 15px">
                    <div class="menu-bar-default">
                        <a onclick="addNew()" class="btn btn-sm btn-primary"><i class="fa fa-plus"></i>&nbsp;新增</a>
                        <a onclick="list_del('tableList')" class="btn btn-sm btn-danger" ><i class="fa fa-minus"></i>&nbsp;删除</a>
                        <a href="javascript:void(0)"  onClick="sortMenu();" class="btn btn-sm btn-info" ><i class="fa fa-sort"></i>&nbsp;排序</a>
                        <a id="open_search"  onclick="javascript:$('#search_hidden').show();$(this).hide();" class="btn btn-sm btn-success" ><i class="fa fa-search"></i>&nbsp;搜索</a>
                    </div>
                    <div id="search_hidden"  style="display: none;margin-top: 8px" class="menu-bar-default">
                        <a  onclick="query('form_query');" class="btn btn-sm btn-success" ><i class="fa fa-search"></i>&nbsp;搜索</a>
                        <a href="javascript:void(0)"  id="btn_reset" onClick="search_form_reset('form_query')" class="btn btn-sm btn-info"><i class="fa fa-reply"></i>&nbsp;重置</a>
                        <a  onclick="javascript:$('#search_hidden').hide();$('#open_search').show();" class="btn btn-default btn-sm"><i class="fa fa-close"></i>&nbsp;关闭</a>
                    </div>
                </form>
            </div>
            <!--数据列表-->
            <div class="table-responsive"><table id="tableList" class="table table-striped"></table> </div>

        </div>
    </div>


</div>
<div id="div_sort" title="菜单排序" style="width:350px;height:400px;display: none;">
    <div class="c_div_tool_bar">
        <a href="javascript:void(0)" class="btn btn-primary size-S" style="margin:4px;" onClick="saveSort()">保存</a>
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
<script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>

<link rel="stylesheet" type="text/css" href="${ctx}/resources/lib/easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/lib/easyui/themes/icon.css">
<script type="text/javascript" src="${ctx}/resources/lib/easyui/jquery.easyui.min.js"></script>


<script src="${ctx}/resources/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>


<script type="text/javascript" src="${ctx}/resources/lib/jqueryui/jquery-ui.min.js?t=20140108"></script>

<script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>

<script type="text/javascript" >

    var tmp_parentid=0;
    var tmp_parentname='';
    var zTreeObj;
    var treeSetting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: 'parentid'
            }
        },
        async:{
            enable:true,
            dataType: "text",
            url: "${ctx}/cms/resourcetype/json/ztree?parentid="+tmp_parentid
            //autoParam: ["id=parentid"]
        },
        view: {
            showLine: true,
            showIcon: true
        },
        callback: {
            beforeAsync: zTreeBeforeAsync,

            onAsyncSuccess: zTreeAjaxSuccess,
            //onAsyncError: zTreeAjaxError,
            onClick: zTreeOnClick
        }
    };
    function initZtree(){
        $.fn.zTree.init($("#trees"), treeSetting, null);
        zTreeObj = $.fn.zTree.getZTreeObj("trees");
    }
    //加载完成的回调
    function zTreeAjaxSuccess(event, treeId, treeNode, msg){
        var nodes=zTreeObj.getNodes();
        zTreeObj.expandNode(nodes[0], true, false, false);
        tmp_parentid=nodes[0].id;
        tmp_parentname=nodes[0].name;
        sys_table_list();
    }
    function zTreeBeforeAsync(treeId, treeNode){
        if (treeNode) {
            tmp_parentid=	treeNode.id ;
            zTreeObj.setting.async.url="${ctx}/cms/resourcetype/json/ztree?parentid="+tmp_parentid;
        }
    }
    function zTreeOnClick(event, treeId, treeNode,clickFlag) {
        tmp_parentid=treeNode.id ;
        tmp_parentname=treeNode.name ;
        switch(treeNode.type){

            case "bm":
                alert("bm");
                //pageUrl="${ctx}/org/department/departmentinsert.jsp?bmid="+treeNode.lxid+"&isqds="+isqds;
                break;
            case "gw":alert("gw");
                //pageUrl="${ctx}/org/department/gw_insert.jsp?org_gwid="+treeNode.lxid+"&org_bmid="+treeNode.sjbmid+"&guid="+treeNode.id+"&isqds="+isqds;
                break;
            case "ry":alert("ry");
                //pageUrl="${ctx}/org/default.do?method=getYhInfo&org_yhid="+treeNode.lxid+"&sjid="+treeNode.sjid+"&isqds="+isqds;
                break;
        }
        sys_table_list();
        //parent.rightfrm.location.href=pageUrl;
    }

    function refreshTree(){
        tmp_parentid=0;
        zTreeObj.setting.async.url="${ctx}/cms/resourcetype/json/ztree?parentid="+tmp_parentid;
        zTreeObj.reAsyncChildNodes(null, "refresh");

    }

    var queryStr="?";
    //查询功能的标签说明
    var table_list_query_form = {
        parentid:tmp_parentid
    };
    function refreshData() {
        $('#tableList').bootstrapTable('refresh');
    }
    $(function(){
        initZtree();
        sys_table_list();
    });
    function sys_table_list(){
        $('#tableList').bootstrapTable('destroy');
        $('#tableList').bootstrapTable({
            columns: [{checkbox:true},{
                field: 'id',
                align:"center",
                sortable:true, //是否允许排序
                order:"asc", //默认排序
                visible:false,
                title: '序号'
            },{
                field: 'name',
                align:"center",
                visible:true,
                title: '资源分类名称'
            }, {
                field: 'parentid',
                align:"center",
                visible:true,
                title: '父级编号'
            }, {
                field: 'parentids',
                align:"center",
                visible:false,
                title: '所有父级编号'
            }, {
                field: 'code',
                align:"center",
                visible:true,
                title: '分类代码'
            },{
                field: 'sort',
                align:"center",
                visible:true,
                title: '排序'
            }, {
                field: 'description',
                title: '资源分类描述',
                align:"center",
                visible:true
            }],
            pagination: true,  //开启分页
            sidePagination: 'server',//服务器端分页
            pageNumber: 1,//默认加载页
            pageSize: 10,//每页数据
            pageList: [10, 15, 20, 50],//可选的每页数据
            queryParams:function (params) {
                return {
                    parentid:tmp_parentid,
                    rows: params.limit,
                    page: (params.offset / params.limit + 1),
                    offset: params.offset
                }
            }
            ,//请求服务器数据时的参数
            onClickRow:onClickRow,
            url:'${ctx}/cms/resourcetype/json/find'+queryStr //服务器数据的加载地址
        });

    }

    var onClickRow=function(row,tr){
        layer_show("编辑资源分类",sys_ctx+"/cms/resourcetype/edit?id="+row.id,'','','',function(){sys_table_list();});
    }
    function addNew(){
        var params="?parentid="+tmp_parentid+"&parentname="+tmp_parentname;
        layer_show("新增资源分类",sys_ctx+"/cms/resourcetype/add"+params,'','','',function(){sys_table_list();refreshTree();});
    }

    function save(){
        var params=$("#form_show").serialize();
        $.ajax({
            type:"post",
            url:'${ctx}/cms/resourcetype/json/save?'+params,
            data:null,
            success:function(json,textStatus){

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
                    url:'${ctx}/cms/resourcetype/json/removes/'+ids,
                    data:null,
                    success:function(data,textStatus){
                        //layer.closeAll();
                        broAjaxReturnMsg(data);
                        // query("form_query");
                        refreshData();
                        refreshTree();
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
        sys_table_list();
    }
    function search_form_reset(tableid){
        $('#'+tableid)[0].reset()
    }

    var sortMenu = function(){
        $.ajax({
            type:"post",
            url:'${ctx}/cms/resourcetype/json/findByParentid/'+tmp_parentid,
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
        layerPage("排序",$("#div_sort"),null,{width:'350px',height:'400px'})
        $("#div_sort").show();

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
            url:'${ctx}/cms/resourcetype/json/saveSort',
            data:{sort:sort},
            success:function(data,textStatus){
                broAjaxReturnMsg(data);
                query("form_query");
                refreshTree();
                setTimeout(function(){
                    layer.closeAll();
                },1000);
            }
        });
    }

</script>
</body></html>
