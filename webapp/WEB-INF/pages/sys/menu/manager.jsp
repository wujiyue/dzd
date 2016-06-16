<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <title>菜单管理</title>
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <style>


  </style>
  <script type="text/javascript">
    $(function() {

      loadTree();

    });
    function loadTree(){
      $('#trees').tree( {
        url :'${ctx}/sys/menu/json/staticTree',
        checkbox :false,
        lines:true,
        onClick:function(node){
          var id = node.id;
          pageUrl="${ctx}/sys/menu/list?parentid="+id;
          rightfrm.location.href=pageUrl;
        }
      });
      rightfrm.location.href="${ctx}/sys/menu/list?parentid=0";
    }
    function refreshTree(){
      $('#trees').tree('reload');
    }
    function collapseAll() {
      var node = $('#trees').tree('getSelected');
      if (node) {
        $('#trees').tree('collapseAll', node.target);
      } else {
        $('#trees').tree('collapseAll');
      }
    }
    //全部收缩
    function expandAll() {
      var node = $('#trees').tree('getSelected');
      if (node) {
        $('#trees').tree('expandAll', node.target);
      } else {
        $('#trees').tree('expandAll');
      }
    }
    function resetFrameHeight(){}


  </script>

</head>
<body>

<div style="width:100%;height:100%;overflow: hidden">
  <div class="clearfix" style="width:225px;height:100%;float:left;overflow-y: auto;" id="west">
    <div style="height:5px;margin:5px 0 0 5px;width:220px;height:30px;">
      <a href="#" class="easyui-linkbutton" onClick="refreshTree()" plain="true">刷新</a>
      <a href="#" class="easyui-linkbutton" onClick="expandAll();" plain="true">展开</a>
      <a href="#" class="easyui-linkbutton" onClick="collapseAll();" plain="true">折叠</a>
    </div>
    <div  style="vertical-align: top;margin:0 0 0 5px;width:220px;">
      <div>
        <ul id="trees">

        </ul>
      </div>
    </div>
  </div>
  <div id="mainPanle"width="100%;float:left;"  style="overflow-y: hidden;">
    <iframe id="rightfrm" width="100%" height="800" scrolling="no" src=""
            marginheight="0" marginwidth="0" frameborder="0" name="rightfrm" class="bgcolor_detail"
            onload="resetFrameHeight();"></iframe>
  </div>
</div>
</body></html>
