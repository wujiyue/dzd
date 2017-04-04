<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="验证码输入框名称"%>
<%@ attribute name="inputClass" type="java.lang.String" required="false" description="验证框类样式"%>
<%@ attribute name="inputCssStyle" type="java.lang.String" required="false" description="验证框样式"%>
<%@ attribute name="blurFun" type="java.lang.String" required="false" description="失去焦点时触发的函数"%>
<%@ attribute name="imageCssStyle" type="java.lang.String" required="false" description="验证码图片样式"%>
<%@ attribute name="buttonCssStyle" type="java.lang.String" required="false" description="看不清按钮样式"%>
<input type="text" id="${name}" name="${name}" onblur="${blurFun}" datatype="*" nullmsg="请输入验证码！" errormsg="验证码位数不正确！" placeholder="请输入验证码" maxlength="5" class="${inputClass} required" style="width:150px;${inputCssStyle}"/>
<img id="${name}Img" src="${pageContext.request.contextPath}/servlet/veryCode" onclick="$('.${name}Refresh').click();" class="radius" style="${imageCssStyle}"/>
<a href="javascript:" onclick="$('#${name}Img').attr('src','${pageContext.request.contextPath}/servlet/veryCode?rnd='+new Date().getTime());" class="mid ${name}Refresh" style="${buttonCssStyle}">看不清</a>