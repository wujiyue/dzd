
var queryTimer,searchTimer,
    pageNo= 1,limit=10,
    width="800px",height="100%",
    model="",method="",
    skin="layui-layer-molv";
//初始化分页条
function initPageBar(totalPages,pageNo,jumpFun){
    laypage({
        cont:$(".pagebar"), //容器。值支持id名、原生dom对象，jquery对象,
        pages: totalPages, //总页数
        curr:pageNo,
        jump:jumpFun,
        skin: 'molv', //皮肤
        first: '首页', //若不显示，设置false即可
        last: '尾页', //若不显示，设置false即可
        prev: '上一页', //若不显示，设置false即可
        next: '下一页' //若不显示，设置false即可
    });
}

/*初始化分页列表用到的iCheckbox*/
function initICheck(){
    /*初始化iCheck*/
    $("input[class='icheck']").iCheck({checkboxClass: 'icheckbox_square-green', radioClass: 'iradio_square-blue'});

}
/*checkbox全选*/
function tableCheckAll(){
    /*checkbox全选*/
    $("table thead #all").on("ifChanged",function(e) {
        var flag = $(this).is(":checked");/*全选按钮是否被选中*/
        if (flag) {
            $("table tbody tr").find("input[type='checkbox']").each(function () {
                $(this).iCheck("check");
            });
        }else{
            $("table tbody tr").find("input[type='checkbox']").each(function () {
                $(this).iCheck("uncheck");
            });
        }
    });
    /*控制数据checkbox，如果所有数据都选中。那么全选checkbox状态为选中；有一个没选中那么全选checkbox状态为未选中*/
}

/*翻页的时候重置选中状态*/
function reset(){

}
/*选择一行数据，在数据加载完之后调用，注册里面的事件*/
function selectRow(){
    $("table tbody tr").on("click",function(){
        var $this=$(this);/*缓存tr对象备用*/
        var $checkBox= $(this).find("input[type='checkbox']");
        var flag=$checkBox.is(":checked");/*获得改行是否选中*/
        if(flag){/*已经选中了，需要取消*/
            //$checkBox.iCheck('uncheck');
            $checkBox.attr("checked","true");
            $this.removeClass("danger");
        }else{/*还未选中，要选中*/
            //$checkBox.iCheck('check');
            $checkBox.attr("checked","false");
            $this.addClass("danger");
        }
    })
}
/*ajax请求出错*/
var ajaxError=function(){
    layer.closeAll('loading');/*移除加载动画*/
    layer.msg("ajax请求出错！", {icon: 5,time:2500});
}


/*获得选中行的值，一般为id，返回数组*/
function getSelectedRows(tableid){
    var arr=[];
    $("#"+tableid+" tbody").find("input[type='checkbox']:checked").each(function(){
        var id= $(this).parent().parent().attr("data-id");/*获得选中行的数据id在该节点上缓存的值*/
        arr.push(id);
    });
    return arr;
}


/*设置增删查改按钮的可用状态*/
function setBtnsState(flag){
    var add= $("#bro_addBtn");
    var edit= $("#bro_editBtn");
    var del= $("#bro_deleteBtn");
    var sea= $("#bro_searchBtn");
    if(flag){
        if(add)
            add.attr("class","btn btn-success radius").attr("onclick","bro_add(addUIUrl)");
        if(edit)
            edit.attr("class","btn btn-primary radius").attr("onclick","bro_edit(editUIUrl)");
        if(del)
            del.attr("class","btn btn-danger radius").attr("onclick","bro_delete()");
        if(sea)
            sea.attr("class","btn btn-success radius f-r").attr("onclick","bro_search()");
    }else{
        if(add)
            add.attr("class","btn disabled radius").removeAttr("onclick");
        if(edit)
            edit.attr("class","btn disabled radius").removeAttr("onclick");
        if(del)
            del.attr("class","btn disabled radius").removeAttr("onclick");
        if(sea)
            sea.attr("class","btn disabled radius f-r").removeAttr("onclick");
    }
}
/*关闭高级按钮*/
function closeGaojiUI(){
    $("#bro_gaojiBtn").removeClass("open");
    $("#bro_showDeleted").attr("checked",false);
    $("#bro_findBackBtn").attr("class","btn disabled radius").removeAttr("onclick");
    $("#bro_deleteEverBtn").attr("class","btn disabled radius").removeAttr("onclick");
    $("#gaoji_hidden").hide();
    setBtnsState(true);
    /*修改高级按钮的图案为关闭文件夹*/
    $("#bro_gaojiBtn").find("i").removeClass().addClass("icon-folder-close");
}
/*打开高级按钮*/
function openGaojiUI(){
    $("#bro_gaojiBtn").addClass("open");
    setBtnsState(false);
    $("#gaoji_hidden").show();
    $("#bro_gaojiBtn").find("i").removeClass().addClass("icon-folder-open");
}

/*找回删除的数据*/
function bro_findBack(findBackUrl,callback){
    var sel=getSelectedRows();
    alert(sel);
    if(isEmpty(sel)){
        layer.msg("请您选中至少1条数据");
        return;
    }
    var ids=sel.join(",");//获得数据id用都好分割后的字符串

    $.ajax({
        type:"post",
        url:findBackUrl+ids,
        data:null,
        beforeSend:function(){
            layer.load(1);
        },
        success:function(data,textStatus){
            layer.closeAll('loading');//移除加载动画
            if(textStatus=='success'){
                layer.msg("找回成功！", {icon: 1,time:2500});
                callback;
            }else{
                layer.msg("找回失败！", {icon: 5,time:2500});
            }
        },
        error:ajaxError
    });
}

/*永久删除*/
function bro_deleteEver(deletesUrl,findDeletedUrl){
    var sel=getSelectedRows();
    if(isEmpty(sel)){
        layer.msg("请您选中至少1条数据");
        return;
    }
    var ids=sel.join(",");
    layer.confirm("永久删除后数据不能恢复，确定这样做吗？", {
        btn: ['确定','取消'],
        shade: [0.1,'#fff']
    }, function(){
        /*执行ajax批量永久删除数据*/
        $.ajax({
            type:"post",
            url:deletesUrl+ids,
            data:null,
            beforeSend:function(){
                layer.load(1);
            },
            success:function(data,textStatus){
                layer.closeAll('loading');
                if(textStatus=='success'){
                    layer.msg("删除成功！", {icon: 1,time:2500});
                    /*刷新显示列表*/
                    queryList(findDeletedUrl,1,{"limit":limit});
                }else{
                    layer.msg("删除失败！", {icon: 5,time:2500});
                }
            },
            error:ajaxError
        });
    });
}

/*点击显示已删除checkbox*/
function bro_showDeleted(findDeletedUrl,findUrl){
    var flag=  $("#bro_showDeleted").is(":checked");
    if(flag){
        //允许使用找回按钮
        $("#bro_findBackBtn").attr("class","btn btn-secondary radius").attr("onclick","bro_findBack(findBackUrl,findDeletedUrl)");
        //允许永久删除按钮
        $("#bro_deleteEverBtn").attr("class","btn btn-danger").attr("onclick","bro_deleteEver(deletesUrl,findDeletedUrl)");
        queryList(findDeletedUrl,1,{"limit":limit});
    }else{
        $("#bro_findBackBtn").attr("class","btn disabled radius").removeAttr("onclick");
        $("#bro_deleteEverBtn").attr("class","btn disabled radius").removeAttr("onclick");
        queryList(findUrl,1,{"limit":limit});
    }
}

/*新增操作，打开新增页面*/
function bro_addUI(addUIUrl){
    layer.open({
        title : "新增",
        skin:skin,
        type : 2,
        area : [ width, height ],
        content : basePath +addUIUrl
    });
}

/*编辑操作，打开编辑页面*/
function bro_editUI(editUIUrl){
    var arr = getSelectedRows();
    if (isEmpty(arr)||arr.length>1) {
        layer.msg("请选择一条数据");
        return;
    }
    layer.open({
        title : "编辑",
        skin:skin,
        type : 2,
        area : [ "800px", "100%" ],
        content : basePath +editUIUrl+ '?id=' + arr[0]
    });

}
