<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%
        String areatype=request.getParameter("areatype")!=null?request.getParameter("areatype"):"";
        String parentid=request.getParameter("parentid")!=null?request.getParameter("parentid"):"";
        String areatypename="";
        String areatypeForSave="";
        if("ROOT".equals(areatype)){
            areatypename="省、直辖市、自治区";
            areatypeForSave="PROVINCE";
        }
        if("PROVINCE".equals(areatype)){
            areatypename="地级市";
            areatypeForSave="CITY";
        }
        if("CITY".equals(areatype)){
            areatypename="区、县";
            areatypeForSave="AREA";
        }
        if("AREA".equals(areatype)){
            areatypename="";
        }

    %>
    <script type="text/javascript">
        var tmp_areatype='<%=areatype%>';
        var tmp_parentid='<%=parentid%>';
    </script>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>新增</title>
    <meta name="Keywords" content="">
    <meta name="Description" content="">
    <%@include file="/WEB-INF/pages/include/common.jsp"%>
    <%@include file="/WEB-INF/pages/include/jqueryProvince.jsp"%>
    <script type="text/javascript" src="${ctx}/resources/lib/My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript">
     var validform;

        //保存到数据库操作
        function save(){

            var params=$("#form_show").serialize();

            sys_ajaxPost("/base/area/json/save?"+params,null,function(json){
                broAjaxReturnMsg(json);
              json=typeof json=='string'?JSON.parse(json):json;
                var type=json.type;

               if(type=='success'){
                 /*  reset();
                   parent.layer.closeAll('page');
                   parent.refreshTree();
                   parent.sys_table_list();*/

               window.parent.location.reload();
               }




            });
        }
        function reset(){
         validform.resetForm();
         }
        $(function(){
               validform=$("#form_show").Validform({
                tiptype:2,
                  beforeSubmit:function(curform){
                       //验证成功执行ajax保存方法
                       save();
                       //表单提交的方式返回false
                       return false;
                   },

                   btnSubmit:"#saveBtn",
                btnReset:"#resetBtn",
                   postonce:true,//至提交一次
                   ajaxPost:false,//ajax方式提交
                showAllError:true //默认 即逐条验证,true验证全部
            });
        })
    </script>

    <style>
        .div_show{width:90%;margin:5px auto;border:1px solid #e5e5e5;border-radius:5px;}
        .div_show .btn-group{margin:5px auto;}
        .div_show .btn-group a {margin-left: 5px;}
    </style>
</head>
<body>

<!--面包屑导航条-->
<%--
<nav class="breadcrumb"><i class="icon iconfont">&#xf012b;</i><a class="maincolor" href="#">首页</a><span class="c-666 en">&gt;</span>用户<span class="c-666 en">&gt;</span><span class="c-666">新增</span></nav>
--%>

<!--表单-->

<div class="div_show">

   <%-- <bro:msg msgObj="${msgObj}"></bro:msg>--%>
    <!--表单布局-->
    <div class="form form-horizontal responsive">
       <%-- <legend class="text-c">新增</legend>--%>
        <form id="form_show">

            <input type="hidden" value="<%=parentid%>" name="parentid"/>
            <div class="row cl">
                <label class="form-label col-1">区域类型:</label>
                <div class="formControls col-3">
                    <input class="hidden" id="areatype"  name="areatype" value="<%=areatypeForSave%>" autocomplete="off"  type="text">
                    <input class="input-text disabled" id="areatypename" readonly  name="areatypename" value="<%=areatypename%>" autocomplete="off"  type="text">
                    <%--<bro:iconselect id="icon" name="icon" value="search"></bro:iconselect>--%>
                </div>
                <div class="formControls col-3">
                    <div class="Validform_checktip"></div>
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-1"><span style="color: red;">*</span>区域名称:</label>
                <div class="formControls col-3">
                    <input class="input-text" id="name" datatype="s3-10" name="name" value="" autocomplete="off"  type="text">
                </div>
                <div class="formControls col-3">
                    <div class="Validform_checktip"></div>
                </div>
            </div>

            <div class="row cl">
                <label class="form-label col-1"><span style="color: red;">*</span>区域代码:</label>
                <div class="formControls col-3">
                    <input class="input-text" id="code" name="code" datatype="s2-10" nullmsg="请输入区域代码！" errormsg="区域代码必须为2-10位字符！" value="${area.code}" autocomplete="off"  type="text">
                </div>
                <div class="formControls col-3">
                    <div class="Validform_checktip"></div>
                </div>
            </div>
            <div class="row cl">
                <span class="form-label col-1"></span>
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
</body></html>
