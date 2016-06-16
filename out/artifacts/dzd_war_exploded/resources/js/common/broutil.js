/**
 * table list 2015年11月5日 20:42:59
 */
/*  */
/*根据ajax返回结果给出对应的提示*/
function broAjaxReturnMsg(data,oparent){

   var json=typeof data=='string'?JSON.parse(data):data;
    var type=json.type;
    switch (type){
        case 'success':

           /* if(oparent!=undefined&&oparent!=null&&oparent!=''){
                oparent.layer.close('dialog');
            }else{
               layer.close('dialog');
            }*/
            layerMsg_rightIcon(json.content,oparent);
            break;
        case 'error':
            layerMsg_wrongIcon(json.content,oparent);
            break;
        case 'block':
            layerMsg_lockedIcon(json.content,oparent);
            break;
        case 'info':
        case 'warning':
        case 'danger':
            layerMsg_warningIcon(json.content,oparent);
            break;
        default :

           /* if(oparent!=undefined&&oparent!=null&&oparent!=''){
                oparent.layer.close('dialog');
            }else{
                layer.close('dialog');
            }*/
            layerMsg("操作成功！",oparent);
    }

}
/**
 * Created by Administrator on 2015/7/15.
 */

/*封装了layer弹出层组件*/
/*加载动画*/
function layerLoading(style){
    layer.load(style, {
        shade: [0.1,'#fff'], /*0.1透明度的白色背景*/
        time:1000
    });
}
/*移除加载动画*/
function closeLayerLoading(){
    layer.closeAll('loading');
}
/*function layerLoadCallback(style,callback){
 layer.load(style, {
 shade: [0.1,'#fff']//0.1透明度的白色背景
 //time:3000
 // callback:callback
 });
 }*/
/*最常用的消息提示弹出层*/
function layerMsg(title,oparent){
    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent.layer.msg(title, {time:3000});
    }else{
        layer.msg(title, {time:3000});
    }

}
function layerMsg_top(title,oparent){
    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent.layer.msg(title, {time:3000,offset:1});
    }else{
        layer.msg(title, {time:3000,offset:1});
    }
}
function layerMsg_rightIcon(title,oparent){

    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent.layer.msg(title, {icon: 1,time:2000});
    }else{
        layer.msg(title, {icon: 1,time:2000});
    }
}
function layerMsg_wrongIcon(title,oparent){

    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent.layer.msg(title, {icon: 2,time:2000});

    }else{
        layer.msg(title, {icon: 2,time:2000});

    }
}
function layerMsg_askIcon(title,oparent){

    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent. layer.msg(title, {icon: 3,time:2000});

    }else{
        layer.msg(title, {icon: 3,time:2000});
    }
}
function layerMsg_lockedIcon(title,oparent){
    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent. layer.msg(title, {icon: 4,time:2000});

    }else{
        layer.msg(title, {icon: 4,time:2000});
    }

}
function layerMsg_cryIcon(title,oparent){

    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent. layer.msg(title, {icon: 5,time:2000});

    }else{
        layer.msg(title, {icon: 5,time:2000});
    }
}
function layerMsg_smileIcon(title,oparent){

    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent. layer.msg(title, {icon: 6,time:2000});

    }else{
        layer.msg(title, {icon: 6,time:2000});
    }
}
function layerMsg_warningIcon(title,oparent){

    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent. layer.msg(title, {icon: 7,time:2000});

    }else{
        layer.msg(title, {icon: 7,time:2000});
    }
}
/*询问框*/
function layerConfirm(title,callbackOK,callbackCancel){
    layer.confirm(title, {
        btn: ['确定','取消'],
        /*shade: [0.3,'#000'] //遮罩*/
        shade:false
    }, callbackOK, callbackCancel);
}
function layerAlert(title,callback){
    layer.alert(title,
        { skin:'layui-layer-molv',
            shade: [0.1,'#000'],
            shadeClose: true,
            shift:2/*动画类型2为从下向上出现*/
        },
        callback
    )
}
/*tips,title为提示内容，attachment为附着的jquery对象或id，position为1-4，color为容器颜色*/
function layer_Tips(title,attachment,position,color){
    layer.tips(title, attachment, {
        tips: [position, color],
        time:5000
    });
}
function layerTips(title,attachment){
    layer.tips(title, attachment, {
        tips: [2, '#3595CC'],
        time: 4000
    });
}
/*弹出页面层*/
function layerPage(title,htmlContent,oparent,width,height){
    if(width==undefined||width==null||width==''){
        width='450px';
    }
    if(height==undefined||height==null||height==''){
        height='550px';
    }
    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        oparent.layer.open({
            title:title,
            type: 1,/*type为1的时候，content接收html文本或dom*/
            skin: 'layui-layer-molv', /*加上半透明边框*/
            area: [width, height], /*宽高*/
            /*btn: ['确定','取消'], //按钮?有监控按钮的回调吗*/
            /*shadeClose: true, //开启遮罩关闭*/
            scrollbar: false,/*屏蔽浏览器滚动条*/
            content:htmlContent

        });
    }else{
        layer.open({
            title:title,
            type: 1,/*type为1的时候，content接收html文本或dom*/
            skin: 'layui-layer-molv', /*加上半透明边框*/
            area: [width, height], /*宽高*/
            /*btn: ['确定','取消'], //按钮?有监控按钮的回调吗*/
            /*shadeClose: true, //开启遮罩关闭*/
            scrollbar: false,/*屏蔽浏览器滚动条*/
            content:htmlContent

        });
    }

}
function layerDivshow(title,divid,oparent,width,height){
    var content=$("#"+divid).html();
    layerPage(title,content,oparent,width,height);
}

function layerDialog(title,divid,width,height,options) {
    if(width==undefined||width==null||width==''){
        width='450px';
    }
    if(height==undefined||height==null||height==''){
        height='550px';
    }
    var opts= $.extend({
        type : 1,
        title : [title],
        fix : false,
        //offset:["50px" , '50%'],
        area : [width, height],
        shadeClose : false,
        content : $(divid).html()//在sysNodeList是弹出对象id
    },options);
    return layer.open(opts);
}

/*右下角信息提示框*/
function layerInfoPop(title,htmlContent){
    layer.open({
        title:title,
        skin:"layui-layer-molv",
        type: 1,/*type为1的时候，content接收html文本*/
        area: ["315px", "215px"], /*宽高*/
        content:htmlContent,
        offset: 'rb', /*右下角弹出*/
        time: 6000, /*6秒后自动关闭*/
        shift: 2,/*动画类型2为从下往上出现*/
        shade: false/*既然是右下角信息提示框，就不需要遮罩页面了*/
    });
}
/*关闭iframe层*/
function layerCloseIframe(oparent){
    if(oparent!=undefined&&oparent!=null&&oparent!=''){
        var index = oparent.parent.layer.getFrameIndex(window.name);
        oparent.parent.layer.close(index);
    }else{
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    }

}
/*弹出iframe层,width,height支持像素和百分比*/
function layerIframe(title,url,oparent,width,height){

    if(width==undefined||width==null||width==''){
        width='60%';
    }
    if(height==undefined||height==null||height==''){
        height='80%';
    }
    if(oparent!=undefined&&oparent!=null&&oparent!=''){
      oparent.layer.open({
            type: 2,/*type为2的时候，content接收url地址*/
            skin:"layui-layer-molv",
            title: title,
            shadeClose: false,
            maxmin:false,
            shade: 0.5,
            area: [width, height],
            scrollbar: false,/*屏蔽浏览器滚动条*/
            content: sys_ctx+url, /*iframe的url*/
            zIndex: layer.zIndex, /*重点1*/
            success: function(layero){
                oparent.layer.setTop(layero); /*重点2*/
            }

        });
    }else{
       layer.open({
            type: 2,/*type为2的时候，content接收url地址*/
            skin:"layui-layer-molv",
            title: title,
            shadeClose: false,
            shade: 0.5,
            maxmin:false,
            area: [width, height],
            scrollbar: false,/*屏蔽浏览器滚动条*/
            content: sys_ctx+url, /*iframe的url*/
            zIndex: layer.zIndex, /*重点1*/
            success: function(layero){
                layer.setTop(layero); /*重点2*/
            }

        });
    }

}

/*弹出即全屏*/
function layerFullIFrame(title,url){
    var index = layer.open({
        title:title,
        skin:"layui-layer-molv",
        type: 2,
        content: sys_ctx+url,
        maxmin: true
    });
    layer.full(index);
}
/*右下角弹出iframe框*/
function layerIFramePop(url,callback){
    layer.open({
        type: 2, //iframe窗
        title: false,
        closeBtn: false,
        shade: false,
        area: ['315px', '215px'],//信息提示框的宽和高
        offset: 'rb', //右下角弹出
        time: 6000, //6秒后自动关闭
        shift: 2,//动画类型2为从下往上出现
        content: [url,'no'], //iframe的url，no代表不显示滚动条
        end: callback//结束的回调函数
    });

}
/**
 * 判断非空
 *
 * @param val
 * @returns {Boolean}
 */
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

function isNotEmpty(val) {
    return !isEmpty(val);
}

var ajaxTimeout = null;
$.broAjax = {
    request : function(options,params){
        var opts = $.extend({},{limit:true,before:function(){
            layerLoad(1);
        },error:function(){
            layerMsg_cryIcon('呜呜~,ajax请求出错啦!');
        },callback:function(data){

        }},options);
        var _url = opts.url;
        if(isEmpty(_url)){
            _url = basePath+"/"+opts.model+"/"+opts.method;
        }
        if(isNotEmpty(opts.params)){
            _url+="&"+opts.params;
        }
        if(opts.limit){
            clearTimeout(ajaxTimeout);
            ajaxTimeout = setTimeout(function(){
                $.broAjax.ajaxMain(opts,_url,params);
            },300);
        }else{
            $.broAjax.ajaxMain(opts,_url,params);
        }
    },
    ajaxMain:function(_url,opts,params){
        $.ajax({
            type:"post",
            data : params,
            url : _url,
            beforeSend:function(){opts.before();},
            error:function(){layerMsg_cryIcon("抱歉！因为操作不能够及时响应，请稍后在试...",1);opts.error();clearTimeout(ajaxTimeout);},
            success:function(data){
                if(data=="sessionTimeOut"){
                    layerMsg_cryIcon("session超时,请登录...");
                    window.location.href = basePath+"/login";
                }else if(data=="noPermission"){
                    layerMsg_cryIcon("非常抱歉,您没有权限...");
                    //window.location.href = basePath+"/error";
                }else{
                    if(opts.callback)opts.callback(data);
                }
                clearTimeout(ajaxTimeout);
            }
        });
    }
};

/*日期工具类*/
$.broDate = {
    /*转换日期*/
    _transferDate : function(date){
        if(typeof date =="string"){
            return new Date(date.replace(/-/ig,"/"));
        }else{
            return date;
        }
    },
    /*格式化日期*/
    _toString : function(date,pattern){
        var d = this._transferDate(date);
        return d.format(pattern);
    },

    /*获取两个时间相减的时间*/
    _Date : function(date1,date2){
        var dateTime = this._numMillSecond(date1,date2);
        return new Date(dateTime).format("yyyy-MM-dd");
    },
    //获取两个时间相减的年份
    _Datsyear : function(date1,date2){
        var dateTime = this._numYear(date1,date2);
        return  dateTime;
    },
//获取两个时间相减的月份
    _Datsmonth : function(date1,date2){
        var dateTime = this._numMonth(date2,date1);
        return  dateTime;
    },

    //间隔年份
    _numYear : function(date1,date2){
        var times = this._numDay(date1,date2);
        return  Math.floor(times/365);
    },

    //间隔月份
    _numMonth : function(date1,date2){
        var times = this._numDay(date1,date2);
        return  Math.floor(times/30);
    },

    //间隔天数
    _numDay : function(date1,date2){
        var times = this._numSecond(date1,date2);
        var hour = this._var().hour;
        var mills = this._var().mills;
        return Math.ceil(times/(mills * hour));
    },

//间隔时
    _numHour : function(date1,date2){
        return Math.floor(this._numMillSecond(date1,date2)/(1000*60*60));
    },

    //间隔分
    _numMinute : function(date1,date2){
        return Math.floor(this._numMillSecond(date1,date2)/(1000*60));
    },

    //间隔秒数
    _numSecond : function(date1,date2){
        return Math.floor(this._numMillSecond(date1,date2) / 1000);
    },

    //间隔毫秒
    _numMillSecond : function(date1,date2){
        var stimes = this._getTime(this._transferDate(date1));
        var etimes = this._getTime(this._transferDate(date2));
        return etimes - stimes;
    },

    _var : function(){
        return {hour:24,second:60,mills:3600,format:"yyyy-MM-dd",dateFormat:"yyyy-MM-dd HH:mm:ss"};
    },

    /*某个日期加上多少毫秒*/
    _plusMillisSeconds : function(date,millisSeconds){
        var dateTime = this._getTime(date);
        var mintimes = millisSeconds;
        var rdate = dateTime*1 + mintimes*1;
        return this._format(new Date(rdate));
    },
    /*某个日期加上多少秒*/
    _plusSeconds : function(date,seconds){
        var dateTime = this._getTime(date);
        var mintimes = seconds*1000;
        var rdate = dateTime*1 + mintimes*1;
        return this._format(new Date(rdate));
    },
    /*某个日期加上多少分钟*/
    _plusMinutes : function(date,minutes){
        var dateTime = this._getTime(date);
        var mintimes = minutes*60*1000;
        var rdate = dateTime*1 + mintimes*1;
        return this._format(new Date(rdate));
    },
    /*某个日期加上小时数*/
    _plusHours : function(date,hours){
        var dateTime = this._getTime(date);
        var mintimes = hours*60*60*1000;
        var rdate = dateTime + mintimes;
        return this._format(new Date(rdate));
    },
    /*某个日期加上天数*/
    _plusDays : function(date,days){
        var dateTime = this._getTime(date);
        var mintimes = days*60*60*1000*24;
        var rdate = dateTime*1 + mintimes*1;
        return this._format(new Date(rdate));
    },

    /*某个日期加上多少个月,这里是按照一个月30天来计算天数的*/
    _plusMonths : function(date,months){
        var dateTime = this._getTime(date);
        var mintimes = months*30*60*60*1000*24;
        var rdate = dateTime + mintimes*1;
        return this._format(new Date(rdate));
    },

    /*某个日期加上多少个年,这里是按照一个月365天来计算天数的，如果loop为true则按闰年计算*/
    _plusYears : function(date,years,isLoop){
        var dateTime = this._getTime(date);
        var day = 365;
        if(isLoop)day =366;
        var mintimes = years*day*60*60*1000*24;
        var rdate = dateTime + mintimes;
        return this._format(new Date(rdate));
    },

    /*某个日期加上某个日期，这样的操作视乎没什么意义*/
    _plusDate : function(date1,date2){
        var dateTime = this._getTime(date1);
        var dateTime2 = this._getTime(date2);;
        var rdate = dateTime + dateTime2;
        return this._format(new Date(rdate));
    },

    /*某个日期减去多少毫秒秒*/
    _minusMillisSeconds : function(date,millisSeconds){
        var dateTime = this._getTime(date);
        var mintimes = millisSeconds*1;
        var rdate = dateTime - mintimes;
        return this._format(new Date(rdate));
    },
    /*某个日期减去多少秒*/
    _minusSeconds : function(date,seconds){
        var dateTime = this._getTime(date);
        var mintimes = seconds*1000;
        var rdate = dateTime - mintimes;
        return this._format(new Date(rdate));
    },
    /*某个日期减去多少分钟*/
    _minusMinutes : function(date,minutes){
        var dateTime = this._getTime(date);
        var mintimes = minutes*60*1000;
        var rdate = dateTime - mintimes;
        return this._format(new Date(rdate));
    },
    /*某个日期减去小时数*/
    _minusHours : function(date,hours){
        var dateTime = this._getTime(date);
        var mintimes = hours*60*60*1000;
        var rdate = dateTime - mintimes;
        return this._format(new Date(rdate));
    },
    /*某个日期减去天数*/
    _minusDays : function(date,days){
        var dateTime = this._getTime(date);
        var mintimes = days*60*60*1000*24;
        var rdate = dateTime - mintimes;
        return this._format(new Date(rdate));
    },

    /*某个日期减去多少个月,这里是按照一个月30天来计算天数的*/
    _minusMonths : function(date,months){
        var dateTime = this._getTime(date);
        var mintimes = months*30*60*60*1000*24;
        var rdate = dateTime - mintimes;
        return this._format(new Date(rdate));
    },

    /*某个日期减去多少个年,这里是按照一个月365天来计算天数的*/
    _minusYears : function(date,years,isLoop){
        var dateTime = this._getTime(date);
        var day = 365;
        if(isLoop)day =366;
        var mintimes = years*day*60*60*1000*24;
        var rdate = dateTime - mintimes;
        return this._format(new Date(rdate));
    },

    /*某个日期减去某个日期，这样的操作视乎没什么意义*/
    _minusDate : function(date1,date2){
        var dateTime = this._getTime(date1);
        var dateTime2 = this._getTime(date2);;
        var rdate = dateTime - dateTime2;
        return this._format(new Date(rdate));
    },

    /*获取一个月有多少天*/
    _getMonthOfDay :function(date1){
        var currentMonth = this._getFirstDayOfMonth(date1);
        var nextMonth = this._getNextDayOfMonth(date1);
        return this._numDay(currentMonth,nextMonth);
    },

    /*获取一年又多少天*/
    _getYearOfDay : function(date){
        var firstDayYear = this._getFirstDayOfYear(date);
        var lastDayYear = this._getLastDayOfYear(date);
        return Math.ceil(this._numDay(firstDayYear,lastDayYear));
    },

    /*某个日期是当年中的第几天*/
    _getDayOfYear : function(date1){
        return Math.ceil(this._numDay(this._getFirstDayOfYear(date1),date1));
    },

    /*某个日期是在当月中的第几天*/
    _getDayOfMonth : function(date1){
        return Math.ceil(this._numDay(this._getFirstDayOfMonth(date1),date1));
    },

    /*获取某个日期在这一年的第几周*/
    _getDayOfYearWeek : function(date){
        var numdays = this._getDayOfYear(date);
        return Math.ceil(numdays / 7);
    },

    /*某个日期是在当月中的星期几*/
    _getDayOfWeek : function(date1){
        return this._getWeek(date1);
    },

    /*获取在当前日期中的时间*/
    _getHourOfDay : function(date){
        return this._getHour(date);
    },
    _eq : function(date1,date2){
        var stime = this._getTime(this._transferDate(date1));
        var etime = this._getTime(this._transferDate(date2));
        return stime == etime ? true :false;
    },
    /*某个日期是否晚于某个日期*/
    _after : function(date1,date2){
        var stime = this._getTime(this._transferDate(date1));
        var etime = this._getTime(this._transferDate(date2));
        return  stime < etime ? true :false;
    },

    /*某个日期是否早于某个日期*/
    _before : function(date1,date2){
        var stime = this._getTime(this._transferDate(date1));
        var etime = this._getTime(this._transferDate(date2));
        return  stime > etime ? true :false;
    },

    /*获取某年的第一天*/
    _getFirstDayOfYear : function(date){
        var year = this._getYear(date);
        var dateString = year+"-01-01 00:00:00";
        return dateString;
    },

    /*获取某年的最后一天*/
    _getLastDayOfYear : function(date){
        var year = this._getYear(date);
        var dateString = year+"-12-01 00:00:00";
        var endDay = this._getMonthOfDay(dateString);
        return year+"-12-"+endDay+" 23:59:59";
    },

    /*获取某月的第一天*/
    _getFirstDayOfMonth: function(date){
        var year = this._getYear(date);
        var month = this._getMonth(date);
        var dateString = year +"-"+month+"-01 00:00:00";
        return dateString;
    },

    /*获取某月最后一天*/
    _getLastDayOfMonth : function(date){
        var endDay = this._getMonthOfDay(date);
        var year = this._getYear(date);
        var month = this._getMonth(date);
        return year +"-"+month+"-"+endDay+" 23:59:59";
    },
    /*一天的开始时间*/
    _getFirstOfDay : function(date){
        var year = this._getYear(date);
        var month = this._getMonth(date);
        var date = this._getDay(date);
        return year+"-"+month+"-"+date+" 00:00:00";
    },

    /*一天的结束时间*/
    _getLastOfDay : function(date){
        var year = this._getYear(date);
        var month = this._getMonth(date);
        var date = this._getDay(date);
        return year+"-"+month+"-"+date+" 23:59:59";
    },

    /*获取下个月的第一天*/
    _getNextDayOfMonth: function(date){
        var year = this._getYear(date);
        var month = this._getMonth(date);
        month = month * 1 +1;
        if(month>12){
            year = year+1;
            month = month - 12;
        }
        month = month>9 ? month : "0"+month;
        var dateString = year +"-"+month+"-01 00:00:00";
        return dateString;
    },

    _getFirstOfWeek : function(date1){
        var week = this._getWeek(date1);
        var date = this._minusDays(date1,week);
        var year = this._getYear(date);
        var month = this._getMonth(date);
        var date = this._getDay(date);
        return year+"-"+month+"-"+date+" 00:00:00";
    },

    _getLastOfWeek : function(date1){
        var week = 6-this._getWeek(date1);
        var date = this._minusDays(date1,week);
        var year = this._getYear(date);
        var month = this._getMonth(date);
        var date = this._getDay(date);
        return year+"-"+month+"-"+date+" 23:59:59";
    },

    _getNow : function(){
        return new Date();
    },
    _format : function(date){
        return this._getYear(date)+"-"+this._getMonth(date)+"-"+this._getDay(date)+" "+this._getHour(date)+":"+this._getMinute(date)+":"+this._getSecond(date);
    },
    _getDate :function(){
        return this._getNow();
    },
    /*年*/
    _getYear:function(date){
        return this._transferDate(date).getFullYear();
    },

    /*月*/
    _getMonth:function(date){
        var month = this._transferDate(date).getMonth()+1;
        return month>9 ? month : "0"+month;
    },

    /*日*/
    _getDay:function(date){
        var day = this._transferDate(date).getDate();
        return day >9 ? day : "0"+day;
    },

    /*获取今天星期几,如果为0代表星期日*/
    _getWeek : function(date){
        return this._transferDate(date).getDay();
    },

    /*时*/
    _getHour : function(date){
        var hour = this._transferDate(date).getHours();
        return hour >9 ? hour : "0"+hour;
    },

    /*12小时制时*/
    _getHour12 : function(date){
        var hour = this._transferDate(date).getHours();
        return hour%12 == 0 ? 12 : hour % 12
    },

    /*分*/
    _getMinute : function(date){
        var minutes = this._transferDate(date).getMinutes();
        return minutes >9 ? minutes : "0"+minutes;
    },

    /*秒*/
    _getSecond : function(date){
        var seconds = this._transferDate(date).getSeconds();
        return seconds >9 ? seconds : "0"+seconds;
    },

    /*毫秒*/
    _getMillisecond : function(date){
        return this._transferDate(date).getMilliseconds();
    },

    /*获取今天在当年是第几季度*/
    _getPeriod : function(date){
        var month = this._getMonth(date)*1;
        return  Math.floor((month+3)/3)
    },

    /*星期*/
    _nowWeekChinies : function(date){
        var nowWeek = this._getWeek(date);
        var day = "";
        switch (nowWeek){
            case 0:day="日";break;
                break;
            case 1:day="一";break;
                break;
            case 2:day="二";break;
                break;
            case 3:day="三";break;
                break;
            case 4:day="四";break;
                break;
            case 5:day="五";break;
                break;
            case 6:day="六";break;
        }
        return day;
    },
    _getMessage : function(date){
        var now = date||new Date();
        var hour = now.getHours() ;
        if(hour < 6){return "凌晨好！";}
        else if (hour < 9){return "早上好！";}
        else if (hour < 12){return "上午好！";}
        else if (hour < 14){return "中午好！";}
        else if (hour < 17){return "下午好！";}
        else if (hour < 19){return "傍晚好！";}
        else if (hour < 22){return "晚上好！";}
        else {return "夜里好！";}
    },
    /*返回 1970 年 1 月 1 日至今的毫秒数。*/
    _getTime : function(date){
        return this._transferDate(date).getTime();
    }
};
/*date end*/

/*
**
* 两参数时会自动调用bind，三参数需要自己调用
*/
function sys_ajaxGet(url,data,callback){
    if (callback===undefined){
        if (data===undefined){
            $.getJSON(sys_ctx+url, function(json){bind(json);});
        }else{
            $.getJSON(sys_ctx+url, data,function(json){bind(json);});
        }
    }else{
        $.getJSON(sys_ctx+url, data, callback);
    }
}
//Post数据
function sys_ajaxPost(_url,_data,callback){
    if (callback===undefined){
        $.ajax({type:"POST", url:sys_ctx+_url, data:_data, dataType:"json",
            beforeSend:function(){
                layer.load(1);
            },
            success:function (msg) {
                layer.closeAll('loading');
                layerMsg_rightIcon(msg);
            },
            error:function(xhr, ajaxOptions, thrownError){
                layer.closeAll('loading');
                layerMsg_cryIcon(thrownError);
            }
        });
    }else{
        $.ajax({type:"POST", url:sys_ctx+_url, data:_data, dataType:"json", success:callback,
            error:function(xhr, ajaxOptions, thrownError){
                layerMsg_cryIcon(thrownError);
            }
        });
    }
}
/*****************************************Bind数据*********************************************/
var sys_bind_formData;//抽取出json中的formData供select组件使用
//绑定函数
function bind(bindValue,flag){//json map结构
    if(flag!=null&&flag!=undefined&&flag==true){
        sys_bind_formData=bindValue;
    }
    for(var key in bindValue.formData){
        sys_bind_formData = bindValue.formData;
        break;
    }
    if (bindValue.result===false){
        window.alert(bindValue.msg);
        return;
    }
    alert(JSON.stringify(sys_bind_formData));
    $.each(bindValue,function(k,v){
        k=k.toLowerCase();
        if (/selectData.*/ig.test(k))//先显示下拉列表数据
        {bind(v);return;}
        if ((/formData.*/ig.test(k))||(/gridData.*/ig.test(k)))
        {bind(v);return;}
        var elem = document.getElementById(k);
        if(elem==undefined||elem==null){
            elem = $('[name='+k+']');
            if(elem!=undefined&&elem!=null){
                elem = elem[0];
            }
        }
        if (elem!==null&&elem!=undefined){
            switch(elem.tagName) {
                case "INPUT":
                    bindInput(k,v);
                    break;
                case "TEXTAREA":
                    bindText(k,v);
                    break;
                case "SELECT":
                    bindSelect(k,v);
                    break;
                case "DIV":
                case "SPAN":
                    elem.innerHTML = v;
            }
        }
    });
}
//input绑定:只根据对象ID进行
function bindInput(e,v) {
    v=filterTimestamp(v);
    var obj = $("#"+e).attr("type");
    switch (obj) {
        case "text":
        case "hidden":
        case "password":$("#"+e).val(v); break;
        case "radio" :
        case "checkbox" :
            $("[name="+e+"]").each(function(){
                if (String(v).indexOf(this.value)>-1) {this.checked=true;}
                else{this.checked=false;}
            });
            break;
    }
}
//下拉列表绑定
function bindSelect(e, v) {
    if (v instanceof Array){//初始化select列表[{dm='',mc=''},{}]
        $("select[name='"+e+"']").empty();
        var aObjsel=document.getElementsByName(e);
        var objsel;
        if(aObjsel.length>0){
            objsel = aObjsel[0];
        }else{
            return;
        }
        var mselected = 0;
        var optioninnerHtml = "";
        for(var i=0;i<v.length;i=i+1){
            if((i==0||i==1)&&(objsel.type!="select-multiple")){
                if ((i==0)&&(v[i].dm!="-")&&(v[i].dm!="+")){//如果有删除标志则不显示请选择
                    optioninnerHtml = optioninnerHtml + "<option value=\"\">请选择</option>";
                }
                if (v[i].dm=="-"){
                    continue;
                }
            }
            var oOption=document.createElement("OPTION");
            if (v[i].dm=="+") {v[i].dm="";}
            oOption.value=v[i].dm;
            oOption.text=v[i].mc;
            var isel = v[i]['selected'];
            if(!!isel){
                $(oOption).attr("selected",true);
                mselected = 1
                optioninnerHtml = optioninnerHtml + "<option value=\""+v[i].dm+"\" selected=\"true\">"+v[i].mc+"</option>";
            }else{
                optioninnerHtml = optioninnerHtml + "<option value=\""+v[i].dm+"\">"+v[i].mc+"</option>";
            }
        }
        for(var z=0;z<aObjsel.length;z++){
            $(aObjsel[z]).append(optioninnerHtml);
            if (aObjsel[z].type!="select-multiple" && mselected == 0){aObjsel[z].selectedIndex=0;}
            if(mselected == 1){
                $(aObjsel[z]).trigger("change");
            }
        }
    }else{
        var element =document.getElementById(e);
        for (var j = 0; j < element.options.length; j=j+1) {
            var temp = element.options[j];
            //可以绑定多值的情况
            temp.selected= (eval("/\\b"+temp.value+"\\b/.test(\""+v+"\")"));
        }
    }
}
//文本绑定
function bindText(e, v) {
    v=filterTimestamp(v);
    $("#"+e).val(v);
}
//过滤掉时间串的毫秒
function filterTimestamp(str){
    var v=new String(str);
    if (v.match(/(\d\d:\d\d:\d\d)\.\d{1,3}/)) {
        var v2=v.replace(/(\d\d:\d\d:\d\d)\.\d{1,3}/g, "$1");
        return v2;
    }
    return v;
}
//增加ajax请求异常处理，可以获取500页面中的异常信息
/*
(function($){
    var ajax=$.ajax;
    $.ajax=function(s){
        var old=s.error;
        var errHeader=s.errorHeader||"Error-Json";
        s.error=function(xhr,status,err){
            var resText = xhr.responseText;
            if(resText!='' && typeof(resText)!='undefined' && resText.indexOf('<script>')>=0){
                eval(document.write(resText))
            } else{
                alert("ajax请求错误，请检查");
            }
        }
        ajax(s);
    }
})(jQuery);*/

var sys_page_config = {
    showid:'div_show',    // 模式默认在哪个区域显示导航条
    tableId:"table_list",   //主列表TABLE ID
    table_field:'guid'
};
//列表显示的基础配置
var table_list_dataGrid = {
    fitColumns:false,//自动申缩大小
    pagination:true,//显示分页
    rownumbers:false,//行号
    singleSelect:false,//是否可以只选一行
    width:'auto',
    height:'auto',
    idField:'',
    url:'',
    queryParams:{},
    loadMsg:"正在加载中....",
    columns:[[]],
    onClickRow:onClickListRow
}
function onClickListRow(rowIndex, rowData){
    $(this).datagrid("clearSelections").datagrid("selectRecord",rowData[sys_page_config.table_field]);
    if(sys_page_config.showid != ''){
        click_table_line(rowData[sys_page_config.table_field]);
        floatDiv_click(sys_page_config.showid);
    }
}
function util_fixWidth(percent){
    return $(window).width() * percent; //这里你可以自己做调整
}
function util_fixWidth_list(tableid, percent){
    return parseFloat($("#"+tableid).parent().width()*percent).toFixed(3);//$(window).width() * percent; //这里你可以自己做调整
}
//反选
function sys_ReverseSelection(tableid){
    var pageRow = $("#"+tableid).datagrid("getRows")
    var selecRow = $("#"+tableid).datagrid("getSelections")
    var pageRowSize = pageRow.length;
    var selecRowSize = selecRow.length;
    var mycars = new Array();
    for(var i=0;i<pageRowSize;i++){
        var tempArray = new Array();
        // 便利页面数据，和选中的数据做比较。
        for(var j=0;j<selecRowSize;j++){
            // 如果找到被选中数据直接跳出。否则，将参与比较的选中数据放入数组。
            if(pageRow[i]==selecRow[j]){
                break;
            }else{
                tempArray[tempArray.length]= selecRow[j];
            }
        }
        // 如果参与比较并且比较结果不同，说明该数据未被选中。如数组中备用！
        if(tempArray.length==selecRowSize){
            mycars[mycars.length] = pageRow[i];
        }
    }
    $("#"+tableid).datagrid("clearSelections");
    var mylen = mycars.length;
    for(var ind=0;ind<mylen;ind++){
        $("#"+tableid).datagrid("selectRecord",mycars[ind][sys_page_config.table_field]);
    }
}
//当前选定的checkbox
var sys_checkBoxGuid = function(tableid){
    var selecRow = $("#"+tableid).datagrid("getSelections")
    var guid = new Array();
    for(var i=0;i<selecRow.length;i++){
        guid[guid.length] = selecRow[i][sys_page_config.table_field]
    }
    return guid;
}
/*****************************************表单处理*******************************************/
//显示或隐藏查询块
function sys_showQueryForm(divid, btnid){
    $("#"+btnid).hide();
    $("#"+divid).show();
}
function sys_hideQueryForm(divid, btnid){
    $("#"+btnid).show();
    $("#"+divid).hide();
}
//表单重
var sys_resetForm = function(formid){

}

/*****************************************弹出层*********************************************/
/*悬浮DIV 点击浮出 有遮罩层*/
function floatDiv_click(divid){
    $("#"+divid).show();
    $('#'+divid).window({
        modal: true,
        shadow: false,
        closed: false,
        top:'50',
        collapsible: false,
        closable:true,
        minimizable: false,
        maximizable: false,
        resizable: true
    });
}
/*悬浮DIV没有遮罩层*/
function floatDiv_auto(top,left,divid){
    $("#"+divid).show();
    $('#'+divid).window({
        modal: false,
        shadow: false,
        closed: false,
        collapsible: false,
        top:top,
        left:left,
        minimizable: false,
        maximizable: false,
        closable:false,
        resizable: true
    });
}
/*浮出层有遮罩层*/
function sys_window(divid,top,left){
    $("#"+divid).show();
    $('#'+divid).window({
        modal: true,
        shadow: false,
        closed: false,
        top:top,
        left:left,
        collapsible: false,
        closable:true,
        minimizable: false,
        maximizable: false,
        resizable: true
    });
}

//关闭浮动层
function closefloatDiv(divid){
    $("#"+divid).window('close');
}