<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.EhCacheUtils" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>登录日志管理</title>
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
<nav class="breadcrumb"><i class="fa fa-home"></i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span>登录日志管理</nav>
<div class="wrapper animated fadeInRight">
    <!--工具条-->
    <div class="cl">
        <form id="form_query" style="margin-bottom: 15px" class="form-inline">
            <div class="menu-bar-default">
                <a onclick="list_del('tableList')" class="btn btn-sm btn-danger" ><i class="fa fa-minus"></i>&nbsp;删除</a>
                <a id="open_search"  onclick="javascript:$('#search_hidden').show();$(this).hide();" class="btn btn-sm btn-success" ><i class="fa fa-search"></i>&nbsp;搜索</a>
            </div>
            <div id="search_hidden"  style="display: none;margin-top: 8px" class="menu-bar-default">
                <input type="text" id="cx_yhmc" name="cx_yhmc" style="width: 200px;"  placeholder="用户名称" class="form-control">
                <a  onclick="query('form_query');" class="btn btn-sm btn-success" ><i class="fa fa-search"></i>&nbsp;搜索</a>
                <a href="javascript:void(0)"  id="btn_reset" onClick="search_form_reset('form_query')" class="btn btn-sm btn-info"><i class="fa fa-reply"></i>&nbsp;重置</a>
                <a  onclick="javascript:$('#search_hidden').hide();$('#open_search').show();" class="btn btn-default btn-sm"><i class="fa fa-close"></i>&nbsp;关闭</a>
            </div>
        </form>
    </div>
    <!--数据列表-->
    <div class="table-responsive"><table id="tableList" class="table table-striped"></table> </div>

    <!--add Modal -->
    <div id="modal-form" class="modal fade" aria-hidden="true">
        <div class="modal-dialog">
            <form id="form_show" class="form-horizontal" role="form">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h3>对话框标题</h3>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="id" name="id">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户ID：</label>
                            <div class="col-sm-9">
                                <input type="text" id="userid" name="userid"  disabled class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">用户名称：</label>
                            <div class="col-sm-9">
                                <input type="text" id="yhmc" name="yhmc" disabled class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">产品代码：</label>
                            <div class="col-sm-9">
                                <input type="text" id="cpdm" name="cpdm" disabled class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">产品名称：</label>
                            <div class="col-sm-9">
                                <input type="text" id="cpmc" name="cpmc" disabled class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">版本号：</label>
                            <div class="col-sm-9">
                                <input type="text" id="version" name="version"  disabled class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">省份：</label>
                            <div class="col-sm-9">
                                <input type="text" id="sf" name="sf"  disabled class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">登录时间：</label>
                            <div class="col-sm-9">
                                <input type="text" id="logintime" name="logintime"  disabled class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">IP：</label>
                            <div class="col-sm-9">
                                <input type="text" id="ip" name="ip"  disabled class="form-control">
                            </div>
                        </div>
                        <%--<div class="form-group">
                            <label class="col-sm-3 control-label">更新内容：</label>
                            <div class="col-sm-9">
                                &lt;%&ndash;<input type="text" id="gxnr" name="gxnr"  placeholder="更新内容" class="form-control">&ndash;%&gt;
                                <textarea rows="6" style="width:100%;"  id="gxnr" name="gxnr"  placeholder="更新内容" class="form-control"></textarea>
                            </div>
                        </div>--%>


                    </div>
                    <div class="modal-footer" style="text-align: center">
                        <button type="submit"  id="saveBtn"  class="btn btn-primary" >保存</button>
                        <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>



<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/resources/lib/bootstrap-validator/js/bootstrapValidator.js"></script>


<script type="text/javascript" >
    var queryStr="?";
    //查询功能的标签说明
    var table_list_query_form = {
        cx_yhmc:""
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
            field: 'userid',
            align:"center",
            visible:true,
            title: '用户ID'
        }, {
            field: 'cpdm',
            align:"center",
            visible:true,
            title: '产品代码'
        }, {
            field: 'cpmc',
            align:"center",
            visible:true,
            title: '产品名称'
        }, {
            field: 'version',
            align:"center",
            visible:true,
            title: '版本号'
        }, {
            field: 'sf',
            align:"center",
            visible:true,
            title: '省份'
        }, {
            field: 'logintime',
            align:"center",
            visible:true,
            title: '登录时间'
        },{
            field: 'ip',
            title: '登录IP',
            align:"center",
            visible:true
        }];
        table_list_Params.columns=columns;
        table_list_Params.onClickRow=onClickRow;
        table_list_Params.url='${ctx}/sys/cploginLog/json/find'+queryStr;
        $('#tableList').bootstrapTable(table_list_Params);
    }

    var onClickRow=function(row,tr){
        $('#modal-form').on('show.bs.modal', function() {
            $('#modal-form .modal-header h3').html("查看登录日志");
            $('#form_show').bootstrapValidator('resetForm', true);
            bind(JSON.stringify(row));
        });
        $('#modal-form').modal('show');
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
                    url:'${ctx}/sys/cploginLog/json/deletes/'+ids,
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
        sys_table_list();
    }
    function search_form_reset(tableid){
        $('#'+tableid)[0].reset()
    }

</script>
</body></html>
