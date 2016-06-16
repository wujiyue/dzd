<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="msgObj" type="com.markbro.asoiaf.core.model.Msg" required="false" description="消息对象"%>
<%@ attribute name="content" type="java.lang.String" required="false" description="消息内容"%>
<%@ attribute name="type" type="java.lang.String" required="false" description="消息类型：info、success、warning、error、loading"%>
<%@ attribute name="style" type="java.lang.String" required="false" description="行内样式"%>
<c:choose>
	<c:when test="${not empty msgObj}">
		<c:if test="${not empty msgObj.type}"><c:set var="ctype" value="${msgObj.type}"/></c:if><c:if test="${empty msgObj.type}"><c:set var="ctype" value="${(fn:indexOf(msgObj.content,'失败') eq -1 and fn:indexOf(msgObj.content,'错误') eq -1 and fn:indexOf(msgObj.content,'禁止') eq -1)?'success':'danger'}"/></c:if>
		<div id="msgBox" class="Huialert Huialert-${ctype} radius" style="${style}"><i class="icon-remove"></i>${msgObj.content}</div>
		<script type="text/javascript">$('#msgBox').show();</script>
	</c:when>
	<c:otherwise>
		<c:set var="content" value="${not empty content ? content:(not empty msg ? msg :(not empty message ? message :''))}"/>
		<c:if test="${not empty content}">
			<c:if test="${not empty type}"><c:set var="ctype" value="${type}"/></c:if><c:if test="${empty type}"><c:set var="ctype" value="${(fn:indexOf(content,'失败') eq -1 and fn:indexOf(content,'错误') eq -1 and fn:indexOf(content,'禁止') eq -1)?'success':'danger'}"/></c:if>
			<div id="msgBox" class="Huialert Huialert-${ctype} radius" style="${style}"><i class="icon-remove"></i>${content}</div>
			<script type="text/javascript">$('#msgBox').show();</script>
		</c:if>
	</c:otherwise>
</c:choose>
