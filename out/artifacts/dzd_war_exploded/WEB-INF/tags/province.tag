<%@ tag language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@include file="/WEB-INF/pages/include/jqueryProvince.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="ID"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="名称"%>
<%@ attribute name="value" type="java.lang.String" required="false" description="值"%>
<div id="${id}">
</div>
<input id="${id}_val" name="${name}" type="hidden" datatype="*"  value="${value}"/>

<script type="text/javascript">


	$("#${id}").ProvinceCity();
	var $province=$("#${id}").find("select").eq(0);
	var $city1=$("#${id}").find("select").eq(1);
	var $city2=$("#${id}").find("select").eq(2);
	var value=$("#${id}_val").val();
	if(notEmpty(value)){
		var arr=null;
		arr=value.split(" ");
		$province.val(arr[0]).trigger("change");
		$city1.val(arr[1]).trigger("change");
		$city2.val(arr[2]).trigger("change");
	}

	/*上面做的是数据回显，下面做得是改变选择的时候把对应的值放到隐藏域*/
	$("#${id}").find("select").on("change",function(){
		var province= $province.val();
		var city1= $city1.val();
		var city2= $city2.val();
		var homeTown=province+" "+city1+" "+city2;
		if(homeTown=='-- -- --')
			homeTown='';
		$("#${id}_val").val(homeTown);
		$("#${id}_val").trigger('blur');
	});


	function isEmpty(val) {
		val = $.trim(val);
		if (val == null)
			return true;
		if (val == undefined || val == 'undefined')
			return true;
		if (val == "")
			return true;
		if (val.length == 0)
			return true;
		if (!/[^(^\s*)|(\s*$)]/.test(val))
			return true;
		return false;
	}

	function notEmpty(val) {
		return !isEmpty(val);
	}
</script>