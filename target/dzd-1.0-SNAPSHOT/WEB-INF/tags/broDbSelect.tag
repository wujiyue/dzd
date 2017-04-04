<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="path" type="java.lang.String" required="true" description="属性名"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="请求数据url"%>

<span class="select-box">
	<select id="${path}" name="${path}" class="select" >
	</select>
	<select id="${path}" name="${path}" class="select" >
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
			bind(json,true);
		}
	});
</script>
