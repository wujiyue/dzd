<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>编辑系统用户</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/resources/lib/layui-v1.0.7/layui/css/layui.css" rel="stylesheet">
    <link href="/resources/css/bro.css" rel="stylesheet">

    <script type="text/javascript" src="/resources/lib/jquery/1.9.1/jquery.min.js"></script>

    <script type="text/javascript" src="${ctx}/resources/lib/layui-v1.0.7/layui/layui.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/common/broutil.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/Validform/Validform_v5.3.2.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/jquery/province/jquery.provincesCity.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/jquery/province/provincesdata.js"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>编辑系统用户</legend>
</fieldset>
<form action="" id="form_show" method="post"  class="layui-form">
    <input type="hidden" id="id" name="id" value="${user.id}">
    <%--<div class="form-group">
         <label class="col-sm-3 control-label">机构ID：</label>
          <div class="col-sm-9">
             <input type="text" id="orgid" name="orgid" placeholder="机构ID" class="form-control" />
          </div>
    </div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">用户账户：</label>
        <div class="layui-input-block">
            <div class="layui-inline formControls">
                <input type="text" id="account" value="${user.account}"  name="account" datatype="s2-12" nullmsg="请输入帐户名" autocomplete="off" placeholder="请输入用户账户" class="layui-input">
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>
        </div>

    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户密码：</label>
        <div class="layui-input-block">
            <div class="layui-inline  formControls">
                <input type="text" id="password" name="password" value="${user.password}" datatype="*6-12" nullmsg="请输入密码" autocomplete="off" placeholder="请输入用户密码" class="layui-input">
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户类型：</label>
        <div class="layui-input-block">
            <div class="layui-inline formControls">
                <input type="text" id="usertype" name="usertype"  value="${user.usertype}"   autocomplete="off" datatype="*"  placeholder="请输入用户类型" class="layui-input">
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">昵称：</label>

        <div class="layui-input-block">
            <div class="layui-inline formControls">
                <input type="text" id="nickname" name="nickname" value="${user.nickname}"  autocomplete="off" placeholder="请输入昵称" datatype="*2-12"  nullmsg="请输入昵称" class="layui-input">
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别：</label>
        <div class="layui-input-block" >
            <div class="layui-inline formControls">
                    <input type="radio" name="gender" value="1" title="男" <c:if test="${user.gender eq 1 }">checked="" </c:if> >
                    <input type="radio" name="gender" value="0" title="女" <c:if test="${user.gender eq 0}">checked="" </c:if> >
                    <input type="radio" name="gender" value="-1" title="保密" <c:if test="${user.gender  eq -1}">checked="" </c:if> >
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">生日：</label>
        <div class="layui-input-block" >
            <div class="layui-inline formControls">
                <input type="text" name="birthday" id="birthday"  value="${user.birthday}"  ignore="ignore"  autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">年龄：</label>
        <div class="layui-input-block" >
            <div class="layui-inline formControls">
                <input type="number" name="age"  value="18"  value="${user.age}"  ignore="ignore"  autocomplete="off" class="layui-input">
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">真实姓名：</label>
        <div class="layui-input-block">
            <div class="layui-inline formControls">
                <input type="text" id="realname" name="realname"   value="${user.realname}"  autocomplete="off" datatype="zh2-4"  ignore="ignore" placeholder="请输入真实姓名" class="layui-input">
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">身份证ID：</label>
        <div class="layui-input-block">
            <div class="layui-inline formControls">
                <input type="text" id="idcard" name="idcard" value="${user.idcard}"   ignore="ignore" datatype="idCard"   autocomplete="off" placeholder="请输入身份证" class="layui-input">
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">故乡：</label>
        <div class="layui-input-block">
            <div class="layui-inline formControls">
                <%-- <bro:province id="hometown" name="hometown" layui="true" ></bro:province>--%>
                <div id="hometown_div">  <input id="hometown" name="hometown" value="${user.hometown}"  type="hidden" datatype="*"   value="${value}"/>
                </div>
            </div>
            <div class="layui-inline"><div class="Validform_checktip"></div></div>

            <script type="text/javascript">
                $("#hometown_div").ProvinceCityLayui();
                layui.use(['form'], function() {
                    var form = layui.form();
                    form.on('select', function(data) {
                        setTimeout(function(){
                            var province= $(".hui-province").val().split("|")[0];
                            var city1= $(".hui-city1").val().split("|")[0];
                            var city2= $(".hui-city2").val();
                            var home=province+" "+city1+" "+city2;
                            if(home.indexOf("请选择")==-1)
                            {
                                $("#hometown").val(home).blur();
                            }else{
                                $("#hometown").val("").blur();
                            }


                        },300);

                    });
                    var v= $("#hometown").val();
                    var v_p= v.split("|")[0];
                    var v_c1= v.split("|")[1];
                    var v_c2= v.split("|")[2];
                    $(".hui-province")
                });
            </script>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">详细地址：</label>
            <div class="layui-input-block">
                <div class="layui-inline formControls">
                    <%--<input type="text" id="address" name="address" style="width: 80%;" ignore="ignore"   autocomplete="off" placeholder="请输入详细地址" class="layui-input">--%>
                    <textarea placeholder="请输入详细地址" id="address"   name="address" datatype="s5-50" ignore="ignore"  class="layui-textarea">${user.address}</textarea>

                </div>
                <div class="layui-inline"><div class="Validform_checktip"></div></div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">QQ：</label>
            <div class="layui-input-block">
                <div class="layui-inline formControls">
                    <input type="text" id="qq" name="qq" datatype="qq" value="${user.qq}"  ignore="ignore" errormsg="qq格式不正确" autocomplete="off" placeholder="请输入QQ" class="layui-input">
                </div>
                <div class="layui-inline"><div class="Validform_checktip"></div></div>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">邮箱：</label>
            <div class="layui-input-block">
                <div class="layui-inline formControls">
                    <input type="text" id="email" name="email"  value="${user.email}"  datatype="e" errormsg="邮箱格式不正确"  ignore="ignore"  placeholder="请输入邮箱" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-inline"><div class="Validform_checktip"></div></div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">邮箱验证：</label>
                <div class="layui-input-block ">
                    <input type="checkbox" <c:if test="${user.emailflag  eq  1}">checked="" </c:if> id="emailflag"     name="emailflag" lay-skin="switch"  title="邮箱验证">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机：</label>
            <div class="layui-input-block ">
                <div class="layui-inline formControls">
                    <input type="text" id="phone" name="phone" value="${user.phone}" datatype="m" errormsg="手机格式不正确"  ignore="ignore"   placeholder="请输入手机" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-inline"><div class="Validform_checktip"></div></div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">手机验证：</label>
                <div class="layui-input-block">
                    <input type="checkbox"  <c:if test="${user.phoneflag  eq  1}">checked="" </c:if>  id="phoneflag" value="${user.phoneflag}"  name="phoneflag" lay-skin="switch"   title="phone验证">
                </div>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注：</label>
            <div class="layui-input-block">
                <div class="layui-inline formControls">
                    <textarea placeholder="请输入内容" id="remark" name="remark"  class="layui-textarea">${user.phoneflag}</textarea>
                </div>
                <div class="layui-inline"><div class="Validform_checktip"></div></div>
            </div>
        </div>
        <%-- <div class="form-group">
             <label class="col-sm-3 control-label">该用户创建类型：</label>
              <div class="col-sm-9">
                 <input type="text" id="createtype" name="createtype" placeholder="该用户创建类型" class="form-control" />
              </div>
        </div>
       <div class="form-group">
             <label class="col-sm-3 control-label">最后登陆IP：</label>
              <div class="col-sm-9">
                 <input type="text" id="lastLoginIp" name="lastLoginIp" placeholder="最后登陆IP" class="form-control" />
              </div>
        </div>
        <div class="form-group">
             <label class="col-sm-3 control-label">上次登陆时间：</label>
              <div class="col-sm-9">
                 <input type="text" id="lastLoginTime" name="lastLoginTime" placeholder="上次登陆时间" class="form-control" />
              </div>
        </div>--%>
        <%--<div class="form-group">
             <label class="col-sm-3 control-label">：</label>
              <div class="col-sm-9">
                 <input type="text" id="openid" name="openid" placeholder="" class="form-control" />
              </div>
        </div>--%>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <%-- <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>--%>
                <button type="submit" lay-submit=""   class="layui-btn" lay-filter="savebtn" >保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
</form>



<script type="text/javascript">
    var validform;
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form()
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;
        //创建一个编辑器
        /*   var editIndex = layedit.build('LAY_demo_editor');
         //自定义验证规则
         form.verify({
         account: function (value) {
         if (value.length < 5) {
         return '账户最少需要5个字符！';
         }
         }
         , password: [/(.+){6,12}$/, '密码必须6到12位']
         ,content: function(value){
         layedit.sync(editIndex);
         }
         });*/

        validform=$("#form_show").Validform({
            tiptype:2,
            postonce:true,//至提交一次
            ajaxPost:false,//ajax方式提交
            /* callback:function(data){
             if(data.status==1){
             layer.msg(data.info, {icon: data.status,time: 1000}, function(){
             location.reload();
             //刷新页面
             });
             }
             else{
             layer.msg(data.info,
             {icon: data.status,time: 3000});
             }
             } ,*/

            showAllError:true //默认 即逐条验证,true验证全部
        });

        // layer.msg("122333", {icon: 1,time: 3000});
        //监听提交
        form.on('submit(savebtn)', function (data) {
            /* top.layer.alert(JSON.stringify(data.field), {
             title: '最终的提交信息'
             });
             return;*/

            save();
            return false;
        });
    });
    $(function(){


    })
    function save(){
        var b=validform.check(false);
        if(!b){
            //top.layer.msg("表单不符合条件！");
            return;
        }
        var params=$("#form_show").serialize();
        $.ajax({
            type:"post",
            url:'${ctx}/org/user/json/save?'+params,
            data:null,
            success:function(json,textStatus){
                broAjaxReturnMsg(json);
                setTimeout(function(){
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                },1000);
            }
        });
    }
</script>

</body>
</html>
