<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/common.jsp"%>
<%@include file="/WEB-INF/pages/include/ueditor.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>新增文章</title>
  <script type="text/javascript" src="${ctx}/resources/js/H-ui.admin.js"></script>
  <script type="text/javascript">
    var validform;
    function save(){
      var b=validform.check(false);
      if(!b){
        return;
      }
		var c=getContentTxt();
		if(c==''|| c.length==0){
			alert("请输入内容！");
			return;
		}
		var v=getContent();
		//$("#content").val(v);

      var params=$("#form_show").serialize();
      $.ajax({
        type:"post",
        url:'${ctx}/cms/article/json/save?'+params,
        data:{content:v},
        success:function(json,textStatus){
          broAjaxReturnMsg(json);
          setTimeout(function(){
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
          },1000);
        }
      });
    }
    $(function(){
      validform=$("#form_show").Validform({
        tiptype:2,
        postonce:true,//至提交一次
        ajaxPost:false,//ajax方式提交
        showAllError:true //默认 即逐条验证,true验证全部
      });
    })
  </script>
</head>
<body>
<article class="page-container" >
  <div class="form form-horizontal responsive">
    <form action="" id="form_show" method="post">
		<%--<input type="hidden" id="content" name="content" />--%>
      <div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>作者:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="userid" datatype="*" value="${article.userid}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>数据范围:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="datascope" datatype="*" value="${article.datascope}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>文章标题:</label>
		 <div class="formControls col-8">
			<input type="text" class="input-text" name="title" datatype="*" value="${article.title}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>关键词:</label>
		 <div class="formControls col-8">
			<input type="text" class="input-text" name="keywords" datatype="*" value="${article.keywords}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>摘要:</label>
		 <div class="formControls col-8">
			<input type="text" class="input-text" name="description" datatype="*" value="${article.description}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>文章内容:</label>
			 <div class="formControls col-10">

				 <div id="btns">
					 <div>
						 <button onclick="getAllHtml()">获得整个html的内容</button>
						 <button onclick="getContent()">获得内容</button>
						 <button onclick="setContent()">写入内容</button>
						 <button onclick="setContent(true)">追加内容</button>
						 <button onclick="getContentTxt()">获得纯文本</button>
						 <button onclick="getPlainTxt()">获得带格式的纯文本</button>
						 <button onclick="hasContent()">判断是否有内容</button>
						 <button onclick="setFocus()">使编辑器获得焦点</button>
						 <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
						 <button onmousedown="setblur(event)" >编辑器失去焦点</button>

					 </div>
					 <div>
						 <button onclick="getText()">获得当前选中的文本</button>
						 <button onclick="insertHtml()">插入给定的内容</button>
						 <button id="enable" onclick="setEnabled()">可以编辑</button>
						 <button onclick="setDisabled()">不可编辑</button>
						 <button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
						 <button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
						 <button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
					 </div>

					 <div>
						 <button onclick="getLocalData()" >获取草稿箱内容</button>
						 <button onclick="clearLocalData()" >清空草稿箱</button>
					 </div>

				 </div>
				<%--<input type="text" class="input-text" name="content" datatype="*" value="${article.content}" />--%>
				<script id="editor" type="text/plain" style="width:100%;height:500px;"></script>

			</div>

		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>文章类别:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="articletypes" datatype="*" value="${article.articletypes}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>频道栏目ID:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="channelids" datatype="*" value="${article.channelids}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2">来源:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="source"  value="${article.source}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		<div class="row cl">
			<label class="form-label col-2"><span style='color:red;'>*</span>标签:</label>
		 <div class="formControls col-3">
			<input type="text" class="input-text" name="tags" datatype="*" value="${article.tags}" />
		</div>
		<div>
			<div class="Validform_checktip"></div>
		</div>
		</div>

		
      <div class="row cl">
        <div class="formControls col-3 col-offset-2">
          <button type="button" onclick="save()" class="btn btn-primary " ><i class="icon-ok"></i>保存</button>
        </div>
      </div>
    </form></div>
</article>
<script type="text/javascript">

	//实例化编辑器
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('editor');
	function isFocus(e){
		alert(UE.getEditor('editor').isFocus());
		UE.dom.domUtils.preventDefault(e)
	}
	function setblur(e){
		UE.getEditor('editor').blur();
		UE.dom.domUtils.preventDefault(e)
	}
	function insertHtml(value) {
		UE.getEditor('editor').execCommand('insertHtml', value)
	}
	function createEditor() {
		enableBtn();
		UE.getEditor('editor');
	}
	function getAllHtml() {
		alert(UE.getEditor('editor').getAllHtml())
	}
	function getContent() {
		return UE.getEditor('editor').getContent();
	}
	function getPlainTxt() {
		var arr = [];
		arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
		arr.push("内容为：");
		arr.push(UE.getEditor('editor').getPlainTxt());
		alert(arr.join('\n'))
	}
	function setContent(isAppendTo) {
		var arr = [];
		arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
		UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
		alert(arr.join("\n"));
	}
	function setDisabled() {
		UE.getEditor('editor').setDisabled('fullscreen');
		disableBtn("enable");
	}

	function setEnabled() {
		UE.getEditor('editor').setEnabled();
		enableBtn();
	}

	function getText() {
		//当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
		var range = UE.getEditor('editor').selection.getRange();
		range.select();
		var txt = UE.getEditor('editor').selection.getText();
		alert(txt)
	}

	function getContentTxt() {
		return UE.getEditor('editor').getContentTxt();
	}
	function hasContent() {
		return UE.getEditor('editor').hasContents();
	}
	function setFocus() {
		UE.getEditor('editor').focus();
	}
	function deleteEditor() {
		disableBtn();
		UE.getEditor('editor').destroy();
	}
	function disableBtn(str) {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for (var i = 0, btn; btn = btns[i++];) {
			if (btn.id == str) {
				UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
			} else {
				btn.setAttribute("disabled", "true");
			}
		}
	}
	function enableBtn() {
		var div = document.getElementById('btns');
		var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
		for (var i = 0, btn; btn = btns[i++];) {
			UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
		}
	}

	function getLocalData () {
		alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
	}

	function clearLocalData () {
		UE.getEditor('editor').execCommand( "clearlocaldata" );
		alert("已清空草稿箱")
	}
</script>
</body>
</html>
