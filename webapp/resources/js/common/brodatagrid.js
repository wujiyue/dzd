/* 
 *基于Jquery 和 artTemplate 封装的列表控件
 *可实现分页和无分页列表，功能单一欢迎大家补充
 * Date: 2015-04-28
 datagrid = $("#eTable").datagrid({
 //pageIndex: 1,
 //pageSize: 10,
 //queryParams: $("#search").serialize(),
 url: "/Layui/GetJson",
 pagination: "pagination",
 scriptHtml: "eTableHtml",
 table: "eTableRow",
 isPagination: true,
 //onLoadSuccess: function (data) {
 //    //alert(data);
 //}
 });
 */
(function ($) {
    function init(options) {
        var formatters=[];
        function getParam() {
            var param = "page=" + opts.pageNo + "&limit=" + opts.pageSize;
            param = param + "&" + opts.queryParams;
            return param;
        }
        /*单机、选择一行*/
        function selectRow(){
            $("table tbody tr").on("click",function(){
                var $this=$(this);/*缓存tr对象备用*/
                if(opts.checkbox){
                    var $checkBox= $(this).find("input[type='checkbox']");
                    var flag=$checkBox.is(":checked");/*获得改行是否选中*/

                    if(flag){/*已经选中了，需要取消*/
                        //$checkBox.iCheck('uncheck');
                        $checkBox.prop("checked",false);
                        $this.removeClass("danger");
                    }else{/*还未选中，要选中*/
                        //$checkBox.iCheck('check');
                        if(!opts.checkbox&&!opts.singleSelect){
                            opts.singleSelect=false;
                        }
                        if(opts.singleSelect){
                            tableUnSelectAll();
                            $checkBox.prop("checked",true);
                            $this.addClass("danger").siblings().removeClass("danger");
                        }else{
                            $checkBox.prop("checked",true);
                            $this.addClass("danger");
                        }

                    }
                }else{
                    $this.toggleClass("danger");
                }

                if(opts.onClickRow!=null&&opts.onClickRow!=undefined&&opts.onClickRow!=''){
                    var id=$this.data("id");
                    opts.onClickRow(id);
                }
            })
        }
        /*双击行*/
        function dbClickRow(){
            $("table tbody tr").dblclick(function(){
                var $this=$(this);/*缓存tr对象备用*/
                if(opts.onDbClickRow!=null&&opts.onDbClickRow!=undefined&&opts.onDbClickRow!=''){
                    var id=$this.data("id");
                    opts.onDbClickRow(id);
                }
            });
        }
        /*checkbox全选*/
        function tableSelectAll(){
            $("#"+opts.table+" thead .selectall").on("click",function(e) {
                if(opts.singleSelect){
                    return;
                }
                var flag = $(this).is(":checked");/*全选按钮是否被选中*/
                if (flag) {
                    $("#"+opts.table+" tbody tr").find("input[type='checkbox']").each(function () {
                        $(this).prop("checked",true);//使用prop而不是用attr,避免第二次以后失效
                        $(this).addClass("danger");
                    });
                }else{
                    $("#"+opts.table+" tbody tr").find("input[type='checkbox']").each(function () {
                        $(this).prop("checked",false);
                        $(this).removeClass("danger");
                    });
                }
            });
            /*控制数据checkbox，如果所有数据都选中。那么全选checkbox状态为选中；有一个没选中那么全选checkbox状态为未选中*/
        }
        /*checkbox全不选*/
        function tableUnSelectAll(){
            $("#"+opts.table+" tbody tr").find("input[type='checkbox']").each(function () {
                $(this).prop("checked",false);
                $(this).removeClass("danger");
            });
        }
        //创建表头
        function createThead(){
            var thead="<thead class='text-c'><tr>";
            if(opts.columns!=null&&opts.columns!=undefined&&opts.columns.length>0){
                if(opts.checkbox){
                    thead+="<td style=\"width: 3%\"><input class=\"selectall\" type=\"checkbox\"/></td>";
                }
                $(opts.columns).each(function(i,v){
                    if(v.field&& v.field.length>0){
                        var style="style='";
                        if(v.width&& typeof(v.width)=='string'){
                            if(v.width.indexOf("%")>0){
                                style+="width:"+v.width+";";
                            }else{
                                style+="width:"+parseInt(v.width)+"px;";
                            }
                        }

                        if(v.hidden&& v.hidden==true){
                            style+="display:none;";
                        }
                        if(v.width&& Number(v.width)>0){
                            style+="width:"+parseInt(v.width)+"px;";
                        }
                        /*if(v.align&&v.align.length>0){
                            if(v.align=='left'){
                                style+="text-align:left;";
                            }else if(v.align=='right'){
                                style+="text-align:right;";
                            }else{
                                style+="text-align:center;";
                            }
                        }*/
                        style+="text-align:center;";
                        style+="'";

                        if(style.length>2){
                            thead+="<td "+style+" c-name=\""+v.field+"\">";
                        }else{
                            thead+="<td c-name=\""+v.field+"\">";
                        }
                        if(v.title&& v.title.length>0){
                            thead+=v.title+"</td>";
                        }else{
                            thead+=v.field+"</td>";
                        }
                    }

                });
            }
            thead+="</tr></thead>";
            return thead;
        }

        function createDataMoBan(){
            var moban="{{each list as value i}}<tr data-id=\"{{value.id}}\">";
            if(opts.columns!=null&&opts.columns!=undefined&&opts.columns.length>0){
                if(opts.checkbox){
                    moban+="<td width=\"25px;\" style=\"text-align:center\"><input type=\"checkbox\"/></td>";
                }
                $(opts.columns).each(function(i,v){
                    var style="style=\"";
                    if(v.width&& typeof(v.width)=='string'){
                        if(v.width.indexOf("%")>0){
                            style+="width:"+v.width+";";
                        }else{
                            style+="width:"+parseInt(v.width)+"px;";
                        }
                    }
                    if(v.width&& Number(v.width)>0){
                        style+="width:"+parseInt(v.width)+"px;";
                    }
                    if(v.hidden&& v.hidden==true){
                        style+="display:none;";
                    }
                    if(v.align&&v.align.length>0){
                        if(v.align=='left'){
                            style+="text-align:left;";
                        }else if(v.align=='right'){
                            style+="text-align:right;";
                        }else{
                            style+="text-align:center;";
                        }
                    }
                    style+="\"";
                    if(style.length>2){

                        moban+="<td "+style+">";
                    }else{
                        moban+="<td>";
                    }

                    if(v.formatter&& (typeof v.formatter=="function"  )){
                        moban+="{{value."+v.field+" | formatter_"+v.field+"}}";
                    }else{
                        moban+="{{value."+v.field+"}}";
                    }


                    moban+="</td>";


                });
            }
            moban+="</tr>{{/each}}";
            return moban;
        }
        function queryForm() {
            var cells =0;
           if(opts.columns&&opts.columns.length>0){
                 cells = opts.columns.length;
            }else{
                cells = document.getElementById(opts.table).rows.item(0).cells.length;
            }


            if (opts.isPagination) {
               // document.getElementById(opts.pagination).innerHTML = "";
                $("#"+opts.pagination).html("");
            }
           // if(opts.columns&&opts.columns.length>0){
                var theadhtml=createThead();
            var s = navigator.userAgent.toLowerCase();
            var BrowserInfo = {
                IsIE: /*@cc_on!@*/false,
                IsIE9Under: /*@cc_on!@*/false && (parseInt(s.match(/msie (\d+)/)[1], 10) <= 9)
            };
            if( BrowserInfo.IsIE9Under  ){
                document.getElementById(opts.table).appendChild = theadhtml+"<tbody id=\"tbody_"+opts.table+"\" class=\"text-c\"></tbody>";
            }else{
                document.getElementById(opts.table).innerHTML = theadhtml+"<tbody id=\"tbody_"+opts.table+"\" class=\"text-c\"></tbody>";

            }
                 /*}else{
             var theadhtml=  $("#"+opts.table).find("thead").html();
                alert(theadhtml);
                document.getElementById(opts.table).innerHTML = theadhtml+"<tbody id=\"tbody_"+opts.table+"\" class=\"text-c\"></tbody>";
            }*/

            var loadingStr = "<tr><td colspan=" + cells + " style='text-align:center'>{0}</td></tr>";

           // document.getElementById("tbody_"+opts.table).innerHTML =loadingStr.replace("{0}", "<img src='/resources/images/loading.gif'/>数据正在加载中...");
           $("#tbody_"+opts.table).html(loadingStr.replace("{0}", "<img src='/resources/images/loading.gif'/>数据正在加载中..."));

            var url = opts.url + "?ts=" + Math.random();
            $.post(url, getParam(), function (json) {
               json=JSON.parse(json);
               // alert(JSON.stringify(json));
                if (json.items.length == 0 || typeof (json.items.length) == "undefined") {

                   // document.getElementById("tbody_"+opts.table).innerHTML =loadingStr.replace("{0}", "<img width='18' src='/Scripts/datagrid/images/smiley_027.png'/>没有查询到您想要的数据");
                   $("#tbody_"+opts.table).html(loadingStr.replace("{0}", "<img width='18' src='/Scripts/datagrid/images/smiley_027.png'/>没有查询到您想要的数据"));


                    return;
                }
                data.list = json.items;
                var datahtml ="";
              /*  if(opts.scriptHtml){
                     datahtml = template(opts.scriptHtml, data);
                }else{*/
                    var moban=createDataMoBan();
                    template.config("escape", false);
                    //template.helper('formatterAge',formatterAge);
                $(opts.columns).each(function(i,v){
                    if(v.formatter&& (typeof v.formatter=="function"  )){
                        //moban+="{{value."+v.field+" | formatter_"+v.field+"}}";
                        template.helper("formatter_"+v.field,v.formatter);
                    }
                });
                    var render = template.compile(moban);
                    datahtml = render(data);
               // }

               // document.getElementById("tbody_"+opts.table).innerHTML =datahtml
                $("#tbody_"+opts.table).html(datahtml);



                if (json.totalCount > 0 && opts.isPagination) {
                    totalCount = json.totalCount;
                    pageInitialize(opts.pagination, opts.pageNo, opts.pageSize, totalCount);
                }
                callbackFun();
            });
        }

        function pageInitialize(pagerID, pageNo, pageSize, totalCount) {
            laypage({
                cont: $("#"+pagerID), //容器。值支持id名、原生dom对象，jquery对象。【如该容器为】：<div id="page1"></div>
                pages: Math.ceil(totalCount / pageSize), //通过后台拿到的总页数
                skin:"molv",
                curr: pageNo, //初始化当前页
                jump: function (e, first) { //触发分页后的回调
                    opts.pageNo = e.curr;
                    if (!first) { //一定要加此判断，否则初始时会无限刷新
                        queryForm();
                    }
                }
            });
        }

        function callbackFun() {
            selectRow();
            dbClickRow();
            tableSelectAll();
            if (opts.onLoadSuccess != null) {
                opts.onLoadSuccess();
            }
        }

        var defaults = {
            pageSize: 10,
            pageNo: 1,
            queryParams: "",//查询参数
            pagination: "",//分页条id
            scriptHtml: "",
            table: "",
            url: "",
            isPagination: false,//是否开启分页
            onLoadSuccess: null,
            onClickRow: null,
            onDbClickRow: null,
            checkbox:true,
            columns:null,
            singleSelect:false
        }

        var opts = $.extend(defaults, options);
        var data = new Array();
        var totalCount;
        queryForm();

        var method = {};
        return method.getpageNo = function () {
            return this.pageNo;
        },//当前页刷新
            method.onReload = function () {
                queryForm();
            },//重新加载
            method.onLoad = function () {
                opts.pageNo = 0;
                queryForm();
            },
            method.getData = function () {
                return data;
            },
            method.getTotalCount = function () {
                return totalCount;
            },
            method.getSelectedRows=function(){
                var arr=[];
                $("#"+opts.table+" tbody").find("input[type='checkbox']:checked").each(function(){
                    var id= $(this).parents("tr").attr("data-id");/*获得选中行的数据id在该节点上缓存的值*/
                    arr.push(id);
                });
                return arr;
            },
            method

    }

    $.fn.brodatagrid = function (options) {
        return init(options);
    }
})(jQuery)
