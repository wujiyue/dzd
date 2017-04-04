<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<%--<i id="${id}Icon" class="icon-${not empty value?value:' hide'}"></i>&nbsp;<label id="${id}IconLabel">${not empty value?value:'无'}</label>&nbsp;--%>
<div id="${id}Icon" style="display: inline-block;"></div>&nbsp;&nbsp;
<input id="${id}" name="${name}" type="hidden" value="${value}"/>
<a id="${id}Button" href="javascript:" class="btn">选择</a>&nbsp;&nbsp;
<script type="text/javascript">
	$("#${id}Button").click(function(){
		layer.open({
			type: 2,
			title: '选择图标',
			shadeClose: true,
			shade: [0.1,'#000'], //遮罩
			btn: ['确定','取消'], //按钮
			area: ['100%', '100%'],
			maxmin: false,//设置显示最大最小化按钮
			scrollbar: false,//屏蔽浏览器滚动条
			yes: function(index, layero){
				var body = layer.getChildFrame('body', index);
				var val=body.find("#icon_value").val();
				var valLabel="<i class=\"iconfont\">"+val+"</i>";
				/*$("#${id}Icon").attr("class", "icon-"+val);
				$("#${id}IconLabel").text(val);*/
				$("#${id}Icon").html(valLabel);
				//$("#${id}IconLabel").text(val);
				$("#${id}").val(val);
				layer.close(index);
			},cancel: function(index){

			},
			content: "${ctx}/base/tag/iconselect?value="+$("#${id}").val() //iframe的url
		});

	});
</script>