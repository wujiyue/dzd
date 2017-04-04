<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<%@include file="/WEB-INF/pages/include/zTree.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="content-type" content="text/html; charset=utf-8">
  <title>用户选择</title>
  <meta name="Keywords" content="">
  <meta name="Description" content="">

  <script type="text/javascript">
    var winindex = parent.layer.getFrameIndex(window.name);

   var totalNum='${limit}';
    var idInput='${idInput}';
    var mcInput='${mcInput}';
    var nowNum=0;
    var tmp_parentid=0;
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
       url: "${ctx}/org/tree/json/ztree?parentid="+tmp_parentid
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

   }
   function zTreeBeforeAsync(treeId, treeNode){
     if (treeNode) {
       tmp_parentid=	treeNode.id ;
       zTreeObj.setting.async.url="${ctx}/org/tree/json/ztree?parentid="+tmp_parentid;
     }
   }
   function zTreeOnClick(event, treeId, treeNode,clickFlag) {

     switch(treeNode.type){

       case "bm":
             //  alert("bm");
         //pageUrl="${ctx}/org/department/departmentinsert.jsp?bmid="+treeNode.lxid+"&isqds="+isqds;
         break;
       case "gw":
         //alert("gw");
         //pageUrl="${ctx}/org/department/gw_insert.jsp?org_gwid="+treeNode.lxid+"&org_bmid="+treeNode.sjbmid+"&guid="+treeNode.id+"&isqds="+isqds;
         break;
       case "ry":
                var id=treeNode.id;
                var name=treeNode.name;
          addUser(id,name);
              // alert(JSON.stringify(treeNode));
         //pageUrl="${ctx}/org/default.do?method=getYhInfo&org_yhid="+treeNode.lxid+"&sjid="+treeNode.sjid+"&isqds="+isqds;
         break;
     }
     //parent.rightfrm.location.href=pageUrl;
   }
    function addUser(id,name){
      var $users=$("#sel-users").find("li");
      var existFlag=false;
      $users.each(function(i,data){
       var tid= $(this).attr("id");
        if(tid==id){
          existFlag=true;
        }
      });
      if(!existFlag){
        if(Number(nowNum)<Number(totalNum)){
          $("#sel-users").append("<li id='"+id+"' onclick='removeUser(this)'>"+name+"</li>");
          nowNum++;
          $("#nowNum").html(nowNum);
        }else{
          layerMsg_wrongIcon("已经达到用户选择上限！")
        }

      }
    }
    function removeUser(obj){
      $(obj).remove();
      nowNum--;
      $("#nowNum").html(nowNum);
    }
    function refreshTree(){
      tmp_parentid=0;
      zTreeObj.setting.async.url="${ctx}/org/tree/json/ztree?parentid="+tmp_parentid;
      zTreeObj.reAsyncChildNodes(null, "refresh");

    }



   $(function(){
     initZtree();
      $("#totalNum").html(totalNum);
   })
  function save(){
    var ids='';
    var mcs='';
    var $users=$("#sel-users").find("li");
    $users.each(function(i,data){
      var tid= $(this).attr("id");
      var tname= $(this).html();
      ids+=tid+"#";
      mcs+=tname+"#";
    });
    ids=ids.substring(0,ids.lastIndexOf("#"));
    mcs=mcs.substring(0,mcs.lastIndexOf("#"));
    parent.$('#'+idInput).val(ids);
    parent.$('#'+mcInput).val(mcs);
    parent.layer.close(winindex);
  }
    function clos(){
      parent.layer.close(winindex);
    }

  </script>

</head>
<body>

<div style="position:relative;width:100%;height:405px;border-bottom:1px solid #ddd;overflow: hidden">

  <div class="clearfix" style="width:50%;border-right:1px solid #ddd;height:100%;float:left;overflow-y: auto;" id="west">
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
<style>
  ul.sel-users{width:80%;height:350px;border:1px solid #ddd;margin:10px auto;overflow-y: auto;}
  ul.sel-users li{position:relative;width:100%;float:left;display: inline-block;list-style-type: none;padding-left: 0px;overflow:hidden;cursor: pointer;}
  ul.sel-users li:hover{background: #ddd;}
</style>
  <div id="mainPanle"width="50%;float:left;" style="overflow-y: hidden;">
    <div style="width:80%;margin:10px auto;"><p class="text-r"><span id="nowNum"  style="font-weight: bold"  class="c-green">0</span>/<span id="totalNum" style="font-weight: bold" class="c-red">5</span></p></div>
    <ul class="sel-users" id="sel-users">

    </ul>
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
