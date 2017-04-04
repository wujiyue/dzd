<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.EhCacheUtils" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>>数据字典管理</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link  href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="/resources/lib/fontawesome/css/font-awesome.min.css">
    <link href="/resources/lib/bootstrap/table/bootstrap-table.min.css" rel="stylesheet">
    <link href="/resources/lib/bootstrap-validator/css/bootstrapValidator.min.css" rel="stylesheet" />
    <link href="/resources/css/animate.min.css" rel="stylesheet">
    <link href="/resources/css/index/index.css" rel="stylesheet">
    <link href="/resources/css/bro.css" rel="stylesheet">

</head>

<body class="gray-bg">

<!--面包屑导航条-->
<nav class="breadcrumb"><i class="fa fa-home"></i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span>>数据字典管理</nav>
<div class="wrapper animated fadeInRight">
    <!--工具条-->
    <div class="cl">
        <form id="form_query" style="margin-bottom: 15px">
            <div class="menu-bar-default">
                <%--<a onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>--%>
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
                            <label class="col-sm-3 control-label">字典类型：</label>
                            <div class="col-sm-9">
                                <input type="text" id="type" name="type"  placeholder="字典类型" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label">字典值：</label>
                            <div class="col-sm-9">
                                <input type="text" id="value" name="value"  placeholder="字典值" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">显示内容：</label>
                            <div class="col-sm-9">
                                <input type="text" id="label" name="label"  placeholder="显示内容" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label   class="col-sm-3 control-label">类型的描述：</label>
                            <div class="col-sm-9">
                                <textarea id="description" name="description" rows="3" style="width: 100%" class="form-control"  placeholder="类型的描述"></textarea>
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

<script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/resources/js/content.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
<script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
<script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/resources/lib/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>

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
                type: {
                    message: '字典类型验证失败',
                    validators: {
                        notEmpty: {
                            message: '字典类型不能为空'
                        }
                    }
                },value: {
                    message: '字典值验证失败',
                    validators: {
                        notEmpty: {
                            message: '字典值不能为空'
                        }
                    }
                },label: {
                    message: '显示内容验证失败',
                    validators: {
                        notEmpty: {
                            message: '显示内容不能为空'
                        }
                    }
                },
                description: {
                    message: '类型描述验证失败',
                    validators: {
                        notEmpty: {
                            message: '类型描述不能为空'
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
        $('#tableList').bootstrapTable({
            columns: [{checkbox:true},{
                field: 'id',
                align:"center",
                sortable:true, //是否允许排序
                order:"asc", //默认排序
                visible:false,
                title: '序号'
            }, {
                field: 'type',
                align:"center",
                visible:true,
                title: '类型'
            }, {
                field: 'value',
                align:"center",
                visible:true,
                title: '值'
            }, {
                field: 'label',
                align:"center",
                visible:true,
                title: '页面显示内容'
            }, {
                field: 'sort',
                align:"center",
                visible:true,
                title: '排序'
            },{
                field: 'description',
                title: '类型的描述',
                formatter:function(value, row, index){
                    value = '<font color="red">'+value+'</font>';
                    return value;
                }
            }],
            pagination: true,  //开启分页
            sidePagination: 'server',//服务器端分页
            pageNumber: 1,//默认加载页
            pageSize: 10,//每页数据
            pageList: [10, 15, 20, 50],//可选的每页数据
            queryParams:function (params) {
                return {
                    rows: params.limit,
                    page: (params.offset / params.limit + 1),
                    offset: params.offset
                }
            }
            ,//请求服务器数据时的参数
            onClickRow:onClickRow,
            url:'${ctx}/base/dictionary/json/find'+queryStr //服务器数据的加载地址
        });

    }

    var onClickRow=function(row,tr){
        //layer_show("编辑模块代码",sys_ctx+"/base/mkdm/edit?id="+row.id,'','','',function(){sys_table_list();});
        $('#modal-form').on('show.bs.modal', function() {
            $('#modal-form .modal-header h3').html("编辑字典");
            $('#form_show').bootstrapValidator('resetForm', true);
            bind(JSON.stringify(row));
        });
        $('#modal-form').modal('show');
    }
    function addNew(){
        //layer_show("新增模块代码",sys_ctx+"/base/mkdm/add",'','','',function(){refreshData();});
        $('#modal-form').on('show.bs.modal', function() {
            $('#modal-form .modal-header h3').html("新增字典");
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
            url:'${ctx}/base/dictionary/json/save?'+params,
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
                    url:'${ctx}/base/dictionary/json/removes/'+ids,
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
