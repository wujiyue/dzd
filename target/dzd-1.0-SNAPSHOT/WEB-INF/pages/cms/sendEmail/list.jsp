<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>邮件发送管理</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
    <link href="/resources/lib/bootstrap/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/resources/lib/bootstrap-validator/css/bootstrapValidator.min.css" rel="stylesheet" />
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/index/index.css" rel="stylesheet">
    <link href="/resources/css/bro.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>

    <script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
</head>

<body class="gray-bg">

<!--面包屑导航条-->
<nav class="breadcrumb"><i class="fa fa-home"></i> 首页 <span class="c-gray en">&gt;</span> cms <span class="c-gray en">&gt;</span>邮件发送管理</nav>
<div class="wrapper animated fadeInRight">
    <!--工具条-->
    <div class="cl">
        <form id="form_query" style="margin-bottom: 15px" class="form-inline">
            <div class="menu-bar-default">
                <a onclick="addNew()" class="btn btn-sm btn-primary"><i class="fa fa-plus"></i>&nbsp;新增</a>
                <a onclick="list_del('tableList')" class="btn btn-sm btn-danger" ><i class="fa fa-minus"></i>&nbsp;删除</a>
                <a id="open_search"  onclick="javascript:$('#search_hidden').show();$(this).hide();" class="btn btn-sm btn-success" ><i class="fa fa-search"></i>&nbsp;搜索</a>
            </div>
            <div id="search_hidden"  style="display: none;margin-top: 8px" class="menu-bar-default">
                <bro:dicSelect id="emailtype" hiddenPrefix="cx_"  datatype="*" nullmsg="请选择邮件类型" cssStyle="width:200px;"  value="${sendEmail.emailtype}"></bro:dicSelect>
                <a  onclick="query('form_query');" class="btn btn-sm btn-success" ><i class="fa fa-search"></i>&nbsp;搜索</a>
                <a href="javascript:void(0)"  id="btn_reset" onClick="search_form_reset('form_query')" class="btn btn-sm btn-info"><i class="fa fa-reply"></i>&nbsp;重置</a>
                <a  onclick="javascript:$('#search_hidden').hide();$('#open_search').show();" class="btn btn-default btn-sm"><i class="fa fa-close"></i>&nbsp;关闭</a>
            </div>
        </form>
    </div>
    <!--数据列表-->
    <div class="table-responsive"><table id="tableList" class="table table-striped"></table> </div>

</div>



<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/Validform/Validform_v5.3.2.js"></script>


<script type="text/javascript" >
    var queryStr="?";
    //查询功能的标签说明
    var table_list_query_form = {
        cx_emailtype:''
    };
    function refreshData() {
        $('#tableList').bootstrapTable('refresh');
    }
    $(function(){
        sys_table_list();
    });
    function sys_table_list(){
        $('#tableList').bootstrapTable('destroy');
        var columns=[{checkbox:true},
            {
            field: 'emailtype',
            align:"center",
            sortable:true,
            order:"asc",
            width:"100px",
            visible:true,
            title: '邮件类型',
            formatter:function(value, row, index){
                switch (value)
                {
                    case "0":value="普通邮件";
                        break;
                    case "1":value="广告邮件";
                        break;
                    default :"-";
                }
                //value = '<font color="red">'+value+'</font>';
                return value;
            }
            },
            {
            field: 'userid',
            align:"center",
            sortable:true,
            order:"asc",
            width:"100px",
            visible:false,
            title: '用户ID'
            },
            {
            field: 'toemail',
            align:"center",
            sortable:true,
            order:"asc",
            visible:true,
            title: '接收者邮箱'
            },
            {
            field: 'fromemail',
            align:"center",
            sortable:true,
            order:"asc",
            visible:true,
            title: '发送者邮箱'
            },
            {
            field: 'title',
            align:"center",
            sortable:true,
            order:"asc",
            visible:true,
            title: '标题'
            },
            {
            field: 'content',
            align:"center",
            sortable:true,
            order:"asc",
            visible:true,
            title: '内容'
            },
            {
            field: 'sendflag',
            align:"center",
            sortable:true,
                width:"100px",
            order:"asc",
            visible:true,
            title: '是否发送',
                formatter:function(value, row, index){
                    switch (value)
                    {
                        case "1":value="已发送";
                            value = '<font color="green">'+value+'</font>';
                            break;
                        default :value="未发送";
                            value = '<font color="red">'+value+'</font>';
                    }
                    return value;
                }
            },
            {
            field: 'feedback',
            align:"center",
            sortable:true,
            order:"asc",
                width:"100px",
            visible:true,
            title: '是否反馈',
                formatter:function(value, row, index){
                    switch (value)
                    {
                        case "1":value="已反馈";
                            value = '<font color="green">'+value+'</font>';
                            break;
                        default :value="未反馈";
                            value = '<font color="red">'+value+'</font>';
                    }
                    return value;
                }
            },
            {
            field: 'sendTime',
            align:"center",
            sortable:true,
            order:"asc",
            visible:false,
            title: '发送时间'
            }
        ];
        table_list_Params.columns=columns;
        table_list_Params.onClickRow=onClickRow;
        table_list_Params.url='${ctx}/cms/sendEmail/json/find'+queryStr;
        $('#tableList').bootstrapTable(table_list_Params);
    }

    var onClickRow=function(row,tr){
        layer_show("编辑邮件发送",sys_ctx+"/cms/sendEmail/edit?id="+row.id,'','','',function(){sys_table_list();});
    }
    function addNew(){
       layer_show("新增邮件发送",sys_ctx+"/cms/sendEmail/add",'','','',function(){sys_table_list();});
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
                    url:'${ctx}/cms/sendEmail/json/deletes/'+ids,
                    data:null,
                    success:function(data,textStatus){
                        broAjaxReturnMsg(data);
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
        sys_table_list();
    }
    function search_form_reset(tableid){
        $('#'+tableid)[0].reset()
    }

</script>
</body></html>
