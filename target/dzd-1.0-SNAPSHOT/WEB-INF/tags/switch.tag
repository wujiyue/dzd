<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%--<%@include file="/WEB-INF/pages/include/switch.jsp"%>--%>
<%@ attribute name="id" type="java.lang.String" required="true" description="ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="值"%>
<input id="${id}" value="${value}" name="${name}" datatype="*" type="hidden" />
<input id="${id}_switch" class="switch"   type="checkbox"/>

<script type="text/javascript">
	/*初始化switch开关样式*/
	$("#${id}_switch").bootstrapSwitch("onText",'1').bootstrapSwitch("offText",'0').bootstrapSwitch("onColor",'success').bootstrapSwitch("offColor",'default');

	$("#${id}_switch").bootstrapSwitch('onSwitchChange',function(e,s){
		var state=$("#${id}_switch").bootstrapSwitch("state");
		if(state==true){
			$("#${id}").val(1).trigger("blur");
		}else{
			$("#${id}").val(0).trigger("blur");
		}
	});
</script>