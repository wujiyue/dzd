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

<style type="text/css">

  /*  .table-striped-warner > tbody > tr:nth-of-type(odd) {
        background-color: #cbc8c7;

    }*/
</style>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>Bootstrap 模态窗口</h5>

            </div>
            <div class="ibox-content">
                <div class="text-center">
                    <a data-toggle="modal" class="btn btn-primary" href="#modal-form">打开登录窗口</a>
                </div>
                <!-- Modal -->
                <div id="modal-form" class="modal fade" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h3>对话框标题</h3>
                            </div>


                            <div class="modal-body">
                                <div class="row">
                                    <div class="col-sm-6 b-r">
                                        <h3 class="m-t-none m-b">登录</h3>

                                        <p>欢迎登录本站(⊙o⊙)</p>

                                        <form role="form">
                                            <div class="form-group">
                                                <label>用户名：</label>
                                                <input type="email" placeholder="请输入用户名" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label>密码：</label>
                                                <input type="password" placeholder="请输入密码" class="form-control">
                                            </div>
                                            <div>
                                                <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"><strong>登录</strong>
                                                </button>
                                                <label>
                                                    <input type="checkbox" class="i-checks">自动登录</label>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-sm-6">
                                        <h4>还不是会员？</h4>
                                        <p>您可以注册一个账户</p>
                                        <p class="text-center">
                                            <a href="form_basic.html"><i class="fa fa-sign-in big-icon"></i></a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">关闭</a>
                                <a href="#" class="btn btn-primary">保存更改</a>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>


        <!-- End Panel Basic -->

        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>Bootstrap table</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#">选项1</a>
                        </li>
                        <li><a href="#">选项2</a>
                        </li>
                    </ul>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <p>Bootstrap table是国人开发的一款基于 Bootstrap 的 jQuery 表格插件，通过简单的设置，就可以拥有强大的单选、多选、排序、分页，以及编辑、导出、过滤（扩展）等等的功能。目前在github上已经有2600多个Star，可见其受欢迎程度。</p>
                <ul>
                    <li>支持 Bootstrap 3 和 Bootstrap 2</li>
                    <li>自适应界面</li>
                    <li>固定表头</li>
                    <li>非常丰富的配置参数</li>
                    <li>直接通过标签使用</li>
                    <li>显示/隐藏列</li>
                    <li>显示/隐藏表头</li>
                    <li>通过 AJAX 获取 JSON 格式的数据</li>
                    <li>支持排序</li>
                    <li>格式化表格</li>
                    <li>支持单选或者多选</li>
                    <li>强大的分页功能</li>
                    <li>支持卡片视图</li>
                    <li>支持多语言</li>
                    <li>支持插件</li>
                </ul>


                <div ><table id="tableList" class="table"></table> </div>
            </div>
        </div>



    </div>
    <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/lib/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/resources/js/content.min.js"></script>
    <script src="/resources/lib/bootstrap/table/bootstrap-table.min.js"></script>
    <script src="/resources/lib/bootstrap/table/bootstrap-table-mobile.min.js"></script>
    <script src="/resources/lib/bootstrap/table/locale/bootstrap-table-zh-CN.min.js"></script>


    <script type="text/javascript" >
        function showData() {
            $('#tableList').bootstrapTable('refresh');
        }
        $(function(){



            $('#tableList').bootstrapTable({
                columns: [{checkbox:true},{
                    field: 'id',
                    align:"center",
                    sortable:true, //是否允许排序
                    order:"asc", //默认排序
                    visible:true,
                    title: '序号'
                }, {
                    field: 'mkdm',
                    align:"center",
                    visible:true,
                    title: '代码'
                }, {
                    field: 'description',
                    title: '描述',
                    formatter:function(value, row, index){

                        value = '<font color="red">'+value+'</font>';

                        return value;
                    }
                }],//页面需要展示的列，后端交互对象的属性
                pagination: true,  //开启分页
                sidePagination: 'server',//服务器端分页
                pageNumber: 1,//默认加载页
                idField: "id", //标识哪个字段为id主键
                silent: true, //刷新事件必须设置
                showRefresh: true, //显示刷新按钮
                search: true,//是否显示右上角的搜索框
                striped: true, //使表格带有条纹
                pageSize: 5,//每页数据
                striped: false,
                pageList: [5, 10, 100, 500],//可选的每页数据
                queryParams: function (params) {
                    return {
                       // mkdm:"200",
                        rows: params.limit,
                        page: (params.offset/params.limit+1),
                        offset: params.offset
                    }
                },//请求服务器数据时的参数
                onClickRow:function(row,tr){
                    alert(row.id);
                   //ids =row.id + "," + ids;
                },
                url: '${ctx}/base/mkdm/json/find' //服务器数据的加载地址
            });
        });


    </script>
</body>
</html>
