<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	
	/* String username=(String)request.getSession().getAttribute("wx_login_username");
	if(!"".equals(username)&&username!=null){
	}else{
		username="";
	} */
%>
<script type="text/javascript" src="/resources/mobile/wx/js/zepto.min.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/zepto.fx.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/zepto-mvalidate.js"></script>
<script type="text/javascript" src="/resources/mobile/wx/js/zepto.cookie.js"></script>
<script type="text/javascript">
	
	var username = $.fn.cookie('weixinfore_username');
   	if(username==''||username==undefined||username=='undefined'||!username||username==null){
   		//window.location.href=sys_ctx+'/zcpt/wx/login.jsp';
		window.location.href=sys_ctx+'/mobile/wx/login';
   	}
</script>