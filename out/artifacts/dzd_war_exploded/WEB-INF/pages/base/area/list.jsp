<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <%
    String areatype=request.getParameter("areatype")!=null?request.getParameter("areatype"):"";
    String parentid=request.getParameter("parentid")!=null?request.getParameter("parentid"):"";
    String areatypeForSave="";
      String areatypename="";
    if("PROVINCE".equals(areatype)){
        areatypename="地级市";
        areatypeForSave="CITY";
    }
    if("CITY".equals(areatype)){
        areatypename="区、县";
        areatypeForSave="AREA";
    }
    if("0".equals(parentid)){
        areatypeForSave="PROVINCE";
        areatypename="省、直辖市、自治区";
    }
  %>
  <script type="text/javascript">
    var tmp_areatype='<%=areatype%>';
    var tmp_parentid='<%=parentid%>';
  </script>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <title>区域管理</title>
  <meta name="Keywords" content="">
  <meta name="Description" content="">

  <script type="text/javascript">

 var validform;

 var datagrid;
  function getSel(){
    var arr=datagrid.getSelectedRows();
    alert(arr.join(","));
  }
 function sys_table_list(){
     datagrid = $("#table_list2").brodatagrid({
     pageNo: 1,
     pageSize: 10,
     columns:[
         {field:'id',title:'ID',align:'left',width:"30px"},
         {field:'name',title:'区域名称',align:'center',width:"30px"},
         {field:'areatype',title:'区域类型',align:'center',width:"30px",formatter:function(value){
             if(value=='PROVINCE')
             return "省、直辖市";
             if(value=='CITY')
                 return "市";
             if(value=='AREA')
                 return "区、县";
         }},
         {field:'code',title:'区域代码',hidden:true,align:'right',width:60}
     ],
     //queryParams: $("#query_form").serialize(),
     url: "${ctx}/base/area/json/findByParentid/"+tmp_parentid,
     pagination: "pagination",
     scriptHtml: "eTableHtml",
     table: "table_list2",
     isPagination: true,
     singleSelect:true
     /* ,
      onLoadSuccess: function (data) {
      alert(data);
      }*/
   });
 }
    $(function(){
      sys_table_list();


    })

    function add(){
    layerIframe("新增区域","/base/area/add?parentid="+tmp_parentid+"&areatype="+tmp_areatype,parent);
       // layerDivshow("新增区域","div_show",window.top);
     //   layerDialog("新增区域","#div_show");
    }
 function addNew(){
     $('#div_show').modal('show');
 }
 function save(){

     var params=$("#form_show").serialize();

     sys_ajaxPost("/base/area/json/save?"+params,null,function(json){


         broAjaxReturnMsg(json);
         // alert(parent.winIndex);
         sys_table_list();


     });
 }
 function reset(){
     validform.resetForm();
 }
 function del(){
   var arr=datagrid.getSelectedRows();

   if(isEmpty(arr)||arr.length>1){
     window.top. layer.msg("请您选择1条数据");
     return;
   }
   var ids=arr.join(",");
   parent.layer.confirm("确定这样做吗？", {
     btn: ['确定','取消'],
     shade: [0.1,'#fff']
   }, function(){
     $.ajax({
       type:"post",
       url:'${ctx}/base/area/json/remove/'+ids,
       data:null,
       beforeSend:function(){
           parent.layer.load(1);
       },
       success:function(data,textStatus){
         parent.layer.closeAll();
         parent.refreshTree();
         broAjaxReturnMsg(data,parent);
         sys_table_list();
       },
       error:ajaxError
     });
   });
 }
  </script>
   <%-- <style>
        .div_show{width:90%;margin:5px auto;border:1px solid #e5e5e5;border-radius:5px;}
        .div_show .btn-group{margin:5px auto;}
        .div_show .btn-group a {margin-left: 5px;}
    </style>--%>
</head>
<body>
<!--面包屑导航条-->
<%--<nav class="breadcrumb"><i class="icon iconfont">&#xf012b;</i><a class="maincolor" href="#">首页</a><span class="c-666 en">&gt;</span>管理员管理<span class="c-666 en">&gt;</span><span class="c-666">权限管理</span></nav>
<bro:msg msgObj="${msgObj}"></bro:msg>--%>

<div class="content_wrapper">
<!--数据表-->
  <div id="div_list" class="mt-10">

    <!--工具条-->
    <div class="btn-group mt-10 cl">

      <div class="l">

          <a onclick="add()" id="addBtn" class="btn btn-success radius">新增</a>
          <a onclick="addNew()" id="addBtnNew" class="btn btn-success radius">新增2</a>
         <%-- <a onclick="getSel()" id="testBtn"  class="btn btn-danger radius">获得选中行</a>--%>
         <a onclick="del()" id="removeBtn"  class="btn btn-danger radius">删除</a>

      </div>

    </div>

    <table id="table_list2"  class="table table-border table-bordered table-hover">
      <thead class="text-c">
      <tr>
        <td style="width: 3%"><input class="selectall"  type="checkbox"/></td>
        <td c-name="id" style="display: none;">ID</td>
        <td c-name="code" style="display: none;">区域代码</td>
        <td c-name="name">区域名称</td>
        <td c-name="areatype">区域类型</td>
        <td c-name="sort">排序</td>

      </tr>
      </thead>

      <tbody id="eTable" class="text-c"></tbody>


  </table>
    <!--分页条-->
    <div class="pagebar" id="pagination" style="float:right;margin: 15px auto;"></div>
    <script id="eTableHtml" type="text/html">

      {{each list as value i}}
      <tr data-id="{{value.id}}">
        <td width="3%" style="text-align:center"><input type="checkbox"/></td>
        <td width="5%" style="text-align:center;display: none;">{{value.id}}</td>
        <td width="5%" style="text-align:center;display: none;">{{value.code}}</td>
        <td width="5%" style="text-align:center">{{value.name}}</td>
        <td width="5%" style="text-align:center">{{value.areatype}}</td>
        <td width="5%" style="text-align:center">{{value.sort}}</td>
      </tr>
      {{/each}}

    </script>

  </div>
</div>
<%--<a data-toggle="modal" href="#div_show" class="btn btn-primary size-L">弹出对话框</a>--%>
<div id="div_show" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

    <div class="modal-header">
        <h3 id="myModalLabel">详细信息</h3>

        <a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void(0);">×</a>
    </div>
    <div class="modal-body">
        <div id="div_show_content" title="详细信息">
            <div class="form form-horizontal responsive">
                <%-- <legend class="text-c">新增</legend>--%>
                <form id="form_show">

                    <input type="hidden" value="<%=parentid%>" name="parentid"/>
                    <div class="row cl">
                        <label class="form-label col-2">区域类型:</label>
                        <div class="formControls col-3">
                            <input class="hidden" id="areatype"  name="areatype" value="<%=areatypeForSave%>" autocomplete="off"  type="text">
                            <input class="input-text bg-readonly" id="areatypename" readonly  name="areatypename" value="<%=areatypename%>" autocomplete="off"  type="text">
                            <%--<bro:iconselect id="icon" name="icon" value="search"></bro:iconselect>--%>
                        </div>
                        <div class="formControls col-3">
                            <div class="Validform_checktip"></div>
                        </div>
                    </div>
                    <div class="row cl">
                        <label class="form-label col-2"><span style="color: red;">*</span>区域名称:</label>
                        <div class="formControls col-3">
                            <input class="input-text" id="name" datatype="s3-10" name="name" value="" autocomplete="off"  type="text">
                        </div>
                        <div class="formControls col-3">
                            <div class="Validform_checktip"></div>
                        </div>
                    </div>

                    <div class="row cl">
                        <label class="form-label col-2"><span style="color: red;">*</span>区域代码:</label>
                        <div class="formControls col-3">
                            <input class="input-text" id="code" name="code" datatype="s2-10" nullmsg="请输入区域代码！" errormsg="区域代码必须为2-10位字符！" value="${area.code}" autocomplete="off"  type="text">
                        </div>
                        <div class="formControls col-3">
                            <div class="Validform_checktip"></div>
                        </div>
                    </div>
                    <div class="row cl">
                        <span class="form-label col-2"></span>
                        <div class="formControls col-3">
                            <!--按钮条-->
                            <div class="btn-group text-c">
                                <a onclick="" id="saveBtn" class="btn btn-success radius">保存</a>
                                <a onclick="reset()"  id="resetBtn" class="btn btn-primary radius">重置</a>
                            </div>
                        </div>
                    </div>



                </form>
            </div>
        </div>

    </div>
    <div class="modal-footer">
        <button class="btn btn-primary">确定</button>
        <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>

    </div>

</div>
</body></html>
