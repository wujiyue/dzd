<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="path" type="java.lang.String" required="true" description="属性名"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="类样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="行内样式"%>
<%@ attribute name="onchange" type="java.lang.String" required="false" description="行内样式"%>

<span class="select-box">
	<select id="${path}" name="${path}"  onchange="${onchange}"  class="select ${cssClass}" style="${cssStyle}">
			<option  value="">-请选择-</option>
			<option  value="1">男</option>
			<option  value="0">女</option>
			<option  value="-1">保密</option>
	</select>
</span>
