<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="path" type="java.lang.String" required="true" description="属性名"%>
<%@ attribute name="beginNum" type="java.lang.String" required="false" description="起始值"%>
<%@ attribute name="endNum" type="java.lang.String" required="false" description="结束值"%>
<%@ attribute name="nullmsg" type="java.lang.String" required="false" description="未选择时的错误提示"%>
<span class="select-box">
	<form:select path="${path}" cssClass="select"  datatype="*" nullmsg="${nullmsg}">
		<option   value="">--</option>
		<c:forEach var="i" begin="${beginNum}" end="${endNum}">
			<form:option  value="${i}" label="${i}"/>
		</c:forEach>
	</form:select>
</span>
