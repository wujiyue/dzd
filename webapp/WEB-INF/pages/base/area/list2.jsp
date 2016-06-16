<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>area管理</title>
    <meta name="Keywords" content="">
    <meta name="Description" content="">

    <script type="text/javascript">
        /*获得选中行的值，一般为id，返回数组*/
        function getSelectedRows(){
            var arr=[];
            $("table tbody").find("input[type='checkbox']:checked").each(function(){
                var id= $(this).parent().parent().attr("data-id");/*获得选中行的数据id在该节点上缓存的值*/
                arr.push(id);
            });
            return arr;
        }
        function bro_remove(){
            var sel=getSelectedRows();
            if(isEmpty(sel)||sel.length>1){
                layer.msg("请您选择1条数据");
                return;
            }
            var id=sel[0];
            layer.confirm("确定这样做吗？", {
                btn: ['确定','取消'],
                shade: [0.1,'#fff']
            }, function(){
                /*执行ajax批量永久删除数据*/
                $.ajax({
                    type:"post",
                    url:'${ctx}/sys/menu/json/remove/'+id,
                    data:null,
                    beforeSend:function(){
                        layer.load(1);
                    },
                    success:function(data,textStatus){
                        layer.closeAll('loading');
                        if(textStatus=='success'){
                            if(data.type=='success'){
                                layer.msg(data.content, {icon:1,time:2500});
                                /*刷新显示列表*/
                                searchFormSubmit();
                            }else{
                                layer.msg(data.content, {icon: 5,time:2500});
                            }
                        }else{
                            layer.msg("删除失败！", {icon: 5,time:2500});
                        }
                    },
                    error:function(){
                        layer.closeAll('loading');/*移除加载动画*/
                        layer.msg("ajax请求出错！", {icon: 5,time:2500});
                    }
                });
            });
        }
        function query(){
            layerLoading(1);
            $("#form_query").submit();
        }
        function pager(){
            laypage({
                cont:$(".pagebar"), //容器。值支持id名、原生dom对象，jquery对象,
                pages: ${menusPaginator.totalPages}, //总页数
                curr:${menusPaginator.page},
                jump:function(e,first){
                    if(!first){ //一定要加此判断，否则初始时会无限刷新
                        $("#pageNum").val(e.curr);
                        query();
                    }
                },
                skin: 'molv', //皮肤
                first: '首页', //若不显示，设置false即可
                last: '尾页', //若不显示，设置false即可
                prev: '上一页', //若不显示，设置false即可
                next: '下一页' //若不显示，设置false即可
            });
        }
        $(function(){
            selectRow();
            pager();
        })
    </script>
</head>
<body>
<!--面包屑导航条-->
<nav class="breadcrumb"><i class="icon iconfont">&#xf012b;</i><a class="maincolor" href="#">首页</a><span class="c-666 en">&gt;</span>sys<span class="c-666 en">&gt;</span><span class="c-666">menu管理</span></nav>
<bro:msg msgObj="${msgObj}"></bro:msg>
<!--工具条-->
<div class="btn-group mt-10 cl">
    <div class="l">
        <a href="${ctx}/sys/menu/add"  id="bro_addBtn" class="btn btn-success radius">新增</a>
        <a onclick="bro_remove()" id="bro_removeBtn"  class="btn btn-danger radius">删除</a>
        <shiro:hasRole name="admin">
            <a onclick="bro_removeBatch()" id="bro_removeBatchBtn"  class="btn btn-danger radius">批量删除</a>
             <script type="text/javascript">
                function bro_removeBatch(){
                            var sel=getSelectedRows();
                            if(isEmpty(sel)){
                                layer.msg("请您选择至少1条数据");
                                return;
                            }
                            var ids=sel.join(",");
                            layer.confirm("确定这样做吗？", {
                                btn: ['确定','取消'],
                                shade: [0.1,'#fff']
                            }, function(){
                                /*执行ajax批量永久删除数据*/
                                $.ajax({
                                    type:"post",
                                    url:'${ctx}/sys/menu/json/removes/'+ids,
                                    data:null,
                                    beforeSend:function(){
                                        layer.load(1);
                                    },
                                    success:function(data,textStatus){
                                        layer.closeAll('loading');
                                        if(textStatus=='success'){
                                            if(data.type=='success'){
                                                layer.msg(data.content, {icon:1,time:2500});
                                                /*刷新显示列表*/
                                                searchFormSubmit();
                                            }else{
                                                layer.msg(data.content, {icon: 5,time:2500});
                                            }
                                        }else{
                                            layer.msg("批量删除失败！", {icon: 5,time:2500});
                                        }
                                    },
                                    error:ajaxError
                                });
                            });
                        }
             </script>
        </shiro:hasRole>
    </div>
    <!--搜索条-->
    <div class="search r">
        <form:form id="form_query" action="${ctx}/sys/menu/list" method="post" modelAttribute="pageParam">
            <input id="pageNum" name="page" type="hidden" value="${menusPaginator.page}"/>
            <input id="pageSize" name="limit" type="hidden" value="${menusPaginator.limit}"/>
            <bro:tableSort id="sort" name="sort" value="" callback="searchFormSubmit()"></bro:tableSort>
            <input type="text" id="bro_searchText" name="searchWords" value="${pageParam.searchWords}" style="margin-right:150px; " class="input-text radius"  placeholder="请输入关键词"/>
            <a  id="bro_searchBtn" onclick="searchFormSubmit()" style="margin-top:-32px;position: relative; " class="btn btn-success radius f-r" ><i class="icon-search"></i> 查询</a>
        </form:form>
    </div>
</div>
<div class="mt-10">
    <!--数据表-->
    <table class="table table-border table-bordered table-hover">
        <thead class="text-c">
            <tr>
               <th style="width: 3%"><input id="all"  type="checkbox"/></th>
					<th class="sort-column id" c-name="id">ID</th>
					<th c-name="parentId">父菜单</th>
					<th c-name="parentIds">父ids</th>
					<th c-name="name">名称</th>
					<th c-name="href">链接</th>
					<th c-name="target">目标</th>
					<th c-name="icon">图标</th>
					<th c-name="sort">排序</th>
					<th c-name="permission">权限</th>
					<th c-name="createBy">创建人</th>
					<th c-name="updateBy">更新人</th>
            </tr>
        </thead>
        <tbody class="text-c">
        <c:forEach items="${menus}" var="menu">
            <tr data-id="${menu.id}">
                <td><input type="checkbox"/></td>
					<td>${menu.id}</td>
					<td>${menu.parentId}</td>
					<td>${menu.parentIds}</td>
					<td>${menu.name}</td>
					<td>${menu.href}</td>
					<td>${menu.target}</td>
					<td>${menu.icon}</td>
					<td>${menu.sort}</td>
					<td>${menu.permission}</td>
					<td>${menu.createBy}</td>
					<td>${menu.updateBy}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    上一页: ${menusPaginator.prePage}
    当前页: ${menusPaginator.page}
    下一页: ${menusPaginator.nextPage}
    总页数: ${menusPaginator.totalPages}
    总条数: ${menusPaginator.totalCount}
    <!--分页条-->
    <div class="pagebar" style="float:right;margin: 15px auto;"></div>
</div>
</body></html>
