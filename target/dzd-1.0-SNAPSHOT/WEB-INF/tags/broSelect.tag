<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="path" type="java.lang.String" required="true" description="属性名"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="请求数据url"%>
<%@ attribute name="idAttr" type="java.lang.String" required="false" description="存储id值的属性名"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="类样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="行内样式"%>
	<%@ attribute name="onchange" type="java.lang.String" required="false" description="行内样式"%>

<span class="select-box" style="${cssStyle}">
	<select id="${path}" data-target="${idAttr}" name="${path}" onchange="${path}_SelectChange(this)"  class="select ${cssClass}" >
	</select>
</span>
<script type="text/javascript">
	//请求url获取数据，把dm作为value，mc作为text显示到select中
	$.ajax({
		type:"post",
		url:sys_ctx+"${url}",
		data:null,
		success:function(json,textStatus){
			json=(typeof json=='string')?JSON.parse(json):json;
			//alert(JSON.stringify(json));
			bind(json,true);
		}
	});

	function ${path}_SelectChange(obj){
		if(notEmpty('${idAttr}')){
			var v=$(obj).val();
			$("#${idAttr}").val(v) ;
			$("#${idAttr}").blur();
		}

		/*if(notEmpty('${onchange}')){

		if(typeof ${onchange}=='function'){
			eval('${onchange}');
		}
		}*/
	}
	function showSelect(){
		var v=$("#${idAttr}").val() ;
		$("#${path}").val(v);
	}
	setTimeout("showSelect()",100);
	function isEmpty(val) {
		val = $.trim(val);
		if (val == null)
			return true;
		if (val == undefined || val == 'undefined')
			return true;
		if (val == "")
			return true;
		if (val.length == 0)
			return true;
		if (!/[^(^\s*)|(\s*$)]/.test(val))
			return true;
		return false;
	}

	function notEmpty(val) {
		return !isEmpty(val);
	}
</script>
