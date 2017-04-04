<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	
	/* String username=(String)request.getSession().getAttribute("wx_login_username");
	if(!"".equals(username)&&username!=null){
	}else{
		username="";
	} */
%>
<script type="text/javascript" src="js/zepto.min.js"></script>
<script type="text/javascript" src="js/zepto.fx.js"></script>
<script type="text/javascript" src="js/zepto-mvalidate.js"></script>
<script type="text/javascript" src="js/zepto.cookie.js"></script>
<script type="text/javascript">
	
	var username = $.fn.cookie('weixinfore_username');
	var qymc = $.fn.cookie('weixinfore_qymc');
	var fjh = $.fn.cookie('weixinfore_fjh');
   	if(username==''||username==undefined||username=='undefined'||!username||username==null){
   		window.location.href=sys_ctx+'/zcpt/wxsm/login.jsp?flag=1';
   	}
   	if(qymc==''||qymc==undefined||qymc=='undefined'||!qymc||qymc==null){
   		window.location.href=sys_ctx+'/zcpt/wxsm/login.jsp?flag=1';
   	}
</script>