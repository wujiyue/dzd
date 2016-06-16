<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="path" type="java.lang.String" required="true" description="属性名"%>
<span class="select-box">
	<form:select path="${path}" cssClass="select"  datatype="*" nullmsg="请选择性别！">
		<option   value="">--</option>
			<form:option  value="1" label="男"/>
			<form:option  value="0" label="女"/>
			<form:option  value="-1" label="保密"/>
	</form:select>
</span>
