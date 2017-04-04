/**
 * jQuery :  城市联动插件
 * @example  $("#test").ProvinceCity();
 */
$.fn.ProvinceCityLayui = function(){
	var _self = this;
	//定义3个默认值
	_self.data("province",["-请选择-", "-请选择-"]);
	_self.data("city1",["-请选择-", "-请选择-"]);
	_self.data("city2",["-请选择-", "-请选择-"]);

	_self.append("<div class='layui-input-inline'><select lay-filter='hui-province' class='hui-province' ></select></div> ");
	_self.append("<div class='layui-input-inline'> <select lay-filter='hui-city1'  class='hui-city1' ></select></div>");
	_self.append("<div class='layui-input-inline'> <select lay-filter='hui-city2'  class='hui-city2' ></select></div>");
	//分别获取3个下拉框
	var $sel1 = _self.find("select.hui-province").eq(0);
	var $sel2 = _self.find("select.hui-city1").eq(0);
	var $sel3 = _self.find("select.hui-city2").eq(0);

//	alert(JSON.stringify($sel2));
	//alert(JSON.stringify($sel3));
	//默认省级下拉
	if(_self.data("province")){
		$sel1.append("<option value='"+_self.data("province")[1]+"'>"+_self.data("province")[0]+"</option>");
	}
	$.each(GP, function(index,data){
		$sel1.append("<option value='"+data+"|"+index+"'>"+data+"</option>");
	});
	//默认的1级城市下拉
	if(_self.data("city1")){
		$sel2.append("<option value='"+_self.data("city1")[1]+"'>"+_self.data("city1")[0]+"</option>");
	}
	//默认的2级城市下拉
	if(_self.data("city2")){
		$sel3.append("<option value='"+_self.data("city2")[1]+"'>"+_self.data("city2")[0]+"</option>");
	}
	//省级联动 控制
	var index1 = "" ;
	var provincevalue="";
	layui.use(['form'], function() {
		var form = layui.form();
	form.on('select(hui-province)', function(data){
		provincevalue=data.value;
		var arr=provincevalue.split('|');
		if(arr.length==2){
			index1=Number(arr[1])+1;
		}else{
			index1=0;
		}
		//alert(JSON.stringify(index1));
		$sel2[0].options.length=0;
		$sel3[0].options.length=0;
		if(index1==0){	//当选择的为 “请选择” 时
			if(_self.data("city1")){
				$sel2.append("<option value='"+_self.data("city1")[1]+"'>"+_self.data("city1")[0]+"</option>");
			}
			if(_self.data("city2")){
				$sel3.append("<option value='"+_self.data("city2")[1]+"'>"+_self.data("city2")[0]+"</option>");
			}
		}else{
			$.each( GT[index1-1] , function(index,data){

				$sel2.append("<option value='"+data+"|"+index+"'>"+data+"</option>");
			});

			$.each( GC[index1-1][0] , function(index,data){

				$sel3.append("<option value='"+data+"'>"+data+"</option>");
			})
		}
		form.render('select');
	});
		//1级城市联动 控制
		var index2 = "" ;
		form.on('select(hui-city1)', function(data){

			var arr=data.value.split('|');
			if(arr.length==2){
				index2=Number(arr[1]);
			}else{
				index2=0;
			}
			$sel3[0].options.length=0;

			$.each( GC[index1-1][index2] , function(index,data){
				$sel3.append("<option value='"+data+"'>"+data+"</option>");
			});
			form.render('select');
		});
	});
	return _self;
};
$.fn.ProvinceCity = function(){
	var _self = this;
	//定义3个默认值
	_self.data("province",["-请选择-", "-请选择-"]);
	_self.data("city1",["-请选择-", "-请选择-"]);
	_self.data("city2",["-请选择-", "-请选择-"]);
	//插入3个空的下拉框
	//<div class='formControls col-2'>
	//<div class='select-box'>
	//<select class='select'></select>
	//</div>
	//</div>
	/*添加hui-province hui-city1 hui-city2样式主要是方便获取select对象*/
	_self.append("<div class='formControls col-4'><div class='select-box'><select id='hui-province' class='select '></select></div></div>");
	_self.append("<div class='formControls col-4'><div class='select-box'><select id='hui-city1' class='select'></select></div></div>");
	_self.append("<div class='formControls col-4'><div class='select-box'><select id='hui-city2' class='select '></select></div></div>");

	//分别获取3个下拉框
	var $sel1 = _self.find("select").eq(0);
	var $sel2 = _self.find("select").eq(1);
	var $sel3 = _self.find("select").eq(2);
	//默认省级下拉
	if(_self.data("province")){
		$sel1.append("<option value='"+_self.data("province")[1]+"'>"+_self.data("province")[0]+"</option>");
	}
	$.each( GP , function(index,data){
		$sel1.append("<option value='"+data+"'>"+data+"</option>");
	});
	//默认的1级城市下拉
	if(_self.data("city1")){
		$sel2.append("<option value='"+_self.data("city1")[1]+"'>"+_self.data("city1")[0]+"</option>");
	}
	//默认的2级城市下拉
	if(_self.data("city2")){
		$sel3.append("<option value='"+_self.data("city2")[1]+"'>"+_self.data("city2")[0]+"</option>");
	}
	//省级联动 控制
	var index1 = "" ;
	$sel1.change(function(){
		//清空其它2个下拉框
		$sel2[0].options.length=0;
		$sel3[0].options.length=0;
		index1 = this.selectedIndex;
		if(index1==0){	//当选择的为 “请选择” 时
			if(_self.data("city1")){
				$sel2.append("<option value='"+_self.data("city1")[1]+"'>"+_self.data("city1")[0]+"</option>");
			}
			if(_self.data("city2")){
				$sel3.append("<option value='"+_self.data("city2")[1]+"'>"+_self.data("city2")[0]+"</option>");
			}
		}else{
			$.each( GT[index1-1] , function(index,data){
				$sel2.append("<option value='"+data+"'>"+data+"</option>");
			});
			$.each( GC[index1-1][0] , function(index,data){
				$sel3.append("<option value='"+data+"'>"+data+"</option>");
			})
		}
	}).change();
	//1级城市联动 控制
	var index2 = "" ;
	$sel2.change(function(){
		$sel3[0].options.length=0;
		index2 = this.selectedIndex;
		$.each( GC[index1-1][index2] , function(index,data){
			$sel3.append("<option value='"+data+"'>"+data+"</option>");
		})
	});
	return _self;
};