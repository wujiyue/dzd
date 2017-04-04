<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%@ attribute name="callback" type="java.lang.String" required="true"%>
<input id="${id}_sort" name="sort" type="hidden" value="${pageParam.sort}"/>
<input id="${id}_dir" name="dir" type="hidden" value="${pageParam.dir}"/>
<%-- 使用方法： 1.将本tag写在查询的from里；2.在需要排序th列class上添加：sort-column + 排序字段名；3.后台往前台传 pageParam参数；实例文件：userList.jsp--%>
<script type="text/javascript">
	$(document).ready(function() {

		var dirStr1 = $("#${id}_dir").val();
		var sortStr1 = $("#${id}_sort").val();
		$(".sort-column").each(function(){
			if(isEmpty(sortStr1)){
				dirStr1 = dirStr1 && dirStr1.toUpperCase() == "DESC" ? "down" : "up";
				$(this).html($(this).html() + " <i class=\"icon icon-sort-" + dirStr1 + "\"></i>");
			}
			if ($(this).hasClass(sortStr1)) {
				dirStr1 = dirStr1 && dirStr1.toUpperCase() == "DESC" ? "down" : "up";
				$(this).html($(this).html() + " <i class=\"icon icon-sort-" + dirStr1 + "\"></i>");
			}
		});
		$(".sort-column").on("click",function(){
			var order = $(this).attr("class").split(" ");

			var dirStr = $("#${id}_dir").val();
			for(var i=0; i<order.length; i++){
				if (order[i] == "sort-column"){order = order[i+1]; break;}
			}
			$("#${id}_sort").val(order);
			if ( dirStr&&dirStr.toUpperCase()=="DESC"){

				$("#${id}_dir").val("ASC");
			}else{
				$("#${id}_dir").val("DESC");
			}
			${callback}
		});
	});
</script>