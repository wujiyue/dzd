<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<%@include file="/WEB-INF/pages/include/zTree.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <title>资源分类选择</title>
  <meta name="Keywords" content="">
  <meta name="Description" content="">

  <script type="text/javascript">
    var winindex = parent.layer.getFrameIndex(window.name);
    var tmp_parentid=0;
   var totalNum='${limit}';
    var idInput='${idInput}';
    var mcInput='${mcInput}';
    var idInputValue='';
    var mcInputValue='';
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
    }
   function zTreeBeforeAsync(treeId, treeNode){
     if (treeNode) {
       tmp_parentid=	treeNode.id ;
       zTreeObj.setting.async.url="${ctx}/cms/resourcetype/json/ztree?parentid="+tmp_parentid;
     }
   }
   function zTreeOnClick(event, treeId, treeNode,clickFlag) {
            //alert(JSON.stringify(treeNode.parentid));
            if(treeNode.parentid=='0'||treeNode.parentid==0||treeNode.parentid==null){
                layerMsg_cryIcon("您不能选择根节点！")
               return;
            }
       idInputValue=treeNode.id;
       mcInputValue=treeNode.name;


   }

    function refreshTree(){
      tmp_parentid=0;
      zTreeObj.setting.async.url="${ctx}/cms/resourcetype/json/ztree?parentid="+tmp_parentid;
      zTreeObj.reAsyncChildNodes(null, "refresh");

    }



   $(function(){
     initZtree();
   })
  function save(){

    if(idInputValue!=''&&mcInputValue!=''){

        parent.$('#'+idInput).val(idInputValue);
        parent.$('#'+mcInput).val(mcInputValue);
        parent.$('#'+idInput).blur();
        parent.layer.close(winindex);
    }

  }
    function clos(){
      parent.layer.close(winindex);
    }

  </script>

</head>
<body>

<div style="position:relative;width:100%;height:405px;border-bottom:1px solid #ddd;overflow: hidden">

  <div class="clearfix" style="width:100%;border-right:1px solid #ddd;height:100%;float:left;overflow-y: auto;" id="west">
        <div style="height:5px;margin:5px 0 0 5px;height:30px;">
          <a href="#" class="easyui-linkbutton" onClick="refreshTree()" plain="true">刷新</a>
         <%-- <a href="#" class="easyui-linkbutton" onClick="expandAll();" plain="true">展开</a>
          <a href="#" class="easyui-linkbutton" onClick="collapseAll();" plain="true">折叠</a>--%>
        </div>
        <div  style="vertical-align: top;margin:0 0 0 5px;">
          <div>
            <ul class="ztree" id="trees">

            </ul>
          </div>
        </div>
  </div>

</div>

  <div class="row cl  mt-15">
    <div class="text-c">
      <button type="button" onclick="clos()" class="btn btn-default size-S  fr" id="" name=""><i class="icon-ok"></i> 关闭</button>
      &nbsp;&nbsp;
      <button type="button" onclick="save()" class="btn btn-primary size-S  fr" id="" name=""><i class="icon-ok"></i> 确定</button>

    </div>
  </div>

</body></html>
