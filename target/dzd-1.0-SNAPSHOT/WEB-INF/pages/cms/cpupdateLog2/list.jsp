<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.EhCacheUtils" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>更新日志管理</title>
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
    <style>


    </style>
</head>

<body class="gray-bg">

<!--面包屑导航条-->
<nav class="breadcrumb"><i class="fa fa-home"></i> 首页 <span class="c-gray en">&gt;</span> CMS <span class="c-gray en">&gt;</span>更新日志管理</nav>
<div class="wrapper animated fadeInRight">
    <!--工具条-->
    <div class="cl">
        <form id="form_query" style="margin-bottom: 15px">
            <div class="menu-bar-default">
                <a onclick="addNew()" class="btn btn-sm btn-primary"><i class="fa fa-plus"></i>&nbsp;新增</a>
                <a onclick="list_del('tableList')" class="btn btn-sm btn-danger" ><i class="fa fa-minus"></i>&nbsp;删除</a>
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
                            <label class="col-sm-3 control-label">产品：</label>
                            <script type="text/javascript">
                                function aa(obj){
                                    //alert($(obj).val());
                                }
                            </script>
                            <div class="col-sm-9">
                                <input type="hidden" id="cpdm" onchange="aa(this)" name="cpdm"  placeholder="选择产品" class="form-control">
                                <bro:broSelect idAttr="cpdm" onchange=""  path="productSelect" url="/cms/product/json/select"></bro:broSelect>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">版本号：</label>
                            <div class="col-sm-9">
                                <input type="text" id="version" name="version"  placeholder="版本号" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">更新内容：</label>
                            <div class="col-sm-9">
                                <%--<input type="text" id="gxnr" name="gxnr"  placeholder="更新内容" class="form-control">--%>
                                    <textarea rows="6" style="width:100%;"  id="gxnr" name="gxnr"  placeholder="更新内容" class="form-control"></textarea>
                            </div>
                        </div>


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
    };
    function refreshData() {
        $('#tableList').bootstrapTable('refresh');
    }
    $(function(){
        sys_table_list();

        $('form').bootstrapValidator({
            message: 'This value is not valid',
            excluded: [':disabled'],
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                cpdm: {
                    message: '产品代码验证失败',
                    validators: {
                        notEmpty: {
                            message: '产品代码不能为空'
                        }
                    }
                },version: {
                    message: '版本号验证失败',
                    validators: {
                        notEmpty: {
                            message: '版本号不能为空'
                        }
                    }
                },gxnr: {
                    message: '升级内容验证失败',
                    validators: {
                        notEmpty: {
                            message: '升级内容不能为空'
                        }
                    }
                },fbsj: {
                    message: '发布时间验证失败',
                    validators: {
                        notEmpty: {
                            message: '发布时间不能为空'
                        }
                    }
                }
            },
            submitHandler: function (validator, form, submitButton) {
                alert("submit");
            }
        }).on('success.form.bv', function (e) {
            e.preventDefault();
            save();
        });
    });
    function sys_table_list(){
        $('#tableList').bootstrapTable('destroy');
       var columns=[{checkbox:true},{
                field: 'id',
                align:"center",
                sortable:true, //是否允许排序
                order:"asc", //默认排序
                visible:false,
                width:'10%',
                title: '序号'
            }, {
                field: 'cpdm',
                align:"center",
                visible:true,
                width:'10%',
                title: '产品代码'
            }, {
                field: 'cpmc',
                align:"center",
                visible:true,
           width:'10%',
                title: '产品名称'
            }, {
                field: 'version',
                align:"center",
                visible:true,
           width:'10%',
                title: '版本号'
            }, {
                field: 'gxnr',
                align:"left",
                visible:true,
           width:'40%',
                title: '升级内容'
            }, {
                field: 'fbsj',
                align:"center",
                visible:true,
           width:'10%',
                title: '发布时间'
            },{
                field: 'lrr',
                title: '发布人员',
                align:"center",
           width:'10%',
                visible:true
            }];
        table_list_Params.columns=columns;
        table_list_Params.onClickRow=onClickRow;
        table_list_Params.url='${ctx}/cms/cpupdateLog/json/find'+queryStr;
        $('#tableList').bootstrapTable(table_list_Params);
    }

    var onClickRow=function(row,tr){
        //layer_show("编辑模块代码",sys_ctx+"/base/mkdm/edit?id="+row.id,'','','',function(){sys_table_list();});
        $('#modal-form').on('show.bs.modal', function() {
            $('#modal-form .modal-header h3').html("编辑更新日志");
            $('#form_show').bootstrapValidator('resetForm', true);
           // alert(JSON.stringify(row));return;
            bind(JSON.stringify(row));
        });
        $('#modal-form').modal('show');
    }
    function addNew(){
        //layer_show("新增模块代码",sys_ctx+"/base/mkdm/add",'','','',function(){refreshData();});
        $('#modal-form').on('show.bs.modal', function() {
            $('#modal-form .modal-header h3').html("新增更新日志");
            $('#form_show').bootstrapValidator('resetForm', true);
            $('#form_show')[0].reset();
            $('#id').val("");
        });
        $('#modal-form').modal('show');
    }

    function save(){
        var b=$("#form_show").data('bootstrapValidator').isValid();
        if(!b)
        {
            return;
        }
        var params=$("#form_show").serialize();
        $.ajax({
            type:"post",
            url:'${ctx}/cms/cpupdateLog/json/save?'+params,
            data:null,
            success:function(json,textStatus){
                $('#modal-form').modal('hide')
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
                    url:'${ctx}/cms/cpupdateLog/json/deletes/'+ids,
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
