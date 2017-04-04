<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="属性名"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="值"%>
<%@ attribute name="datatype" type="java.lang.String" required="false" description=""%>
<%@ attribute name="nullmsg" type="java.lang.String" required="false" description=""%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="类样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="行内样式"%>
<%@ attribute name="hiddenPrefix" type="java.lang.String" required="false" description="隐藏域id的前缀"%>

<span class="select-box" style="${cssStyle}">
<c:choose>
	<c:when test="${not empty hiddenPrefix}" >
		<input type="hidden" id="${hiddenPrefix}${id}" name="${hiddenPrefix}${id}" datatype="${datatype}" nullmsg="${nullmsg}" value="${value}"  >
	</c:when>
	<c:otherwise>
		<input type="hidden" id="${id}" name="${id}" datatype="${datatype}" nullmsg="${nullmsg}" value="${value}"  >
	</c:otherwise>
</c:choose>
	<select id="dicSelect_${id}"  name="dicSelect_${id}" onchange="${id}_SelectChange(this)" style="${cssStyle}" class="select ${cssClass}" >
	</select>
</span>

<script type="text/javascript">
	//请求url获取数据，把dm作为value，mc作为text显示到select中
	$.ajax({
		type:"post",
		url:sys_ctx+"/base/dictionary/json/select?type=${id}",
		data:null,
		success:function(json,textStatus){
			json=(typeof json=='string')?JSON.parse(json):json;
			//alert(JSON.stringify(json));
			bind(json,true);
		}
	});

	function ${id}_SelectChange(obj){
		if(notEmpty("${hiddenPrefix}"))
		{
			$("#${hiddenPrefix}${id}").val($(obj).val()) ;
			$("#${hiddenPrefix}${id}").blur();
		}else{
			$("#${id}").val($(obj).val()) ;
			$("#${id}").blur();
		}

	}
	function showSelect(){
		var v=$("#${id}").val() ;
		$("#dicSelect_${id}").val(v);
	}
	setTimeout("showSelect()",500);
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
