

$(function(){

})
//加载用户菜单
function loadUserMenu()
{
    $.ajaxSettings.async = false;
    sys_ajaxGet("/sys/permission/json/getUserMenus","",function(json){
     /*   json=JSON.parse(json);
        alert(JSON.stringify(json));*/
       //alert(JSON.stringify(json.qxlist));
        parseMenu(json.qxlist);
    });
    $.ajaxSettings.async = true;
}
function parseMenu(json){
    var html="";
    var qx;var qx2;var qx3;
    var children2;
    var children3;
    var target;
    for(var i=0;i<json.length;i++){
        qx=json[i];

        children2=qx.children;

        html+="<li>";
        target=qx.target;

        if(children2.length>0){
            html+="<a href=\"#\"><i class=\""+qx.icon+"\"></i> <span class=\"nav-label\">"+qx.name+"</span><span class=\"fa arrow\"></span></a>";
            html+="<ul class=\"nav nav-second-level\">";
            for(var j=0;j<children2.length;j++){
                qx2=children2[j];
                children3=qx2.children;
                target=qx2.target;
                if(children3.length>0){
                    html+="<li>";
                    html+="<a href=\"#\"><i class=\""+qx2.icon+"\"></i> <span class=\"nav-label\">"+qx2.name+"</span><span class=\"fa arrow\"></span></a>";
                    html+="<ul class=\"nav nav-second-level\">";
                    for(var k=0;k<children3.length;k++){
                        qx3=children3[k];
                       // alert(JSON.stringify(qx3));
                        target=qx3.target;
                        if(target==""||target==undefined||target=="undefined"){
                            html+="<li><a class=\"J_menuItem\" href=\""+qx3.url+"\">"+qx3.name+"</a></li>";
                        }else{
                            html+="<li><a  target=\""+target+"\"  href=\""+qx3.url+"\">"+qx3.name+"</a></li>";
                        }
                    }
                    html+="</li>";
                    html+="</ul>";
                }else{
                    if(target==""||target==undefined||target=="undefined"){
                        html+="<li><a class=\"J_menuItem\" href=\""+qx2.url+"\">"+qx2.name+"</a>";
                    }else{
                        html+="<li><a target=\""+target+"\"  href=\""+qx2.url+"\">"+qx2.name+"</a>";
                    }

                    html+="</li>";
                }
            }
            html+="</ul>";

        }else{
            if(target==""||target==undefined||target=="undefined"){
                html+="<a class=\"J_menuItem\" href=\""+qx.url+"\" data-index=\""+i+"\"><i class=\""+qx.icon+"\"></i> <span class=\"nav-label\">"+qx.name+"</span></a>";
            }else{
                html+="<a   href=\""+qx.url+"\" target=\""+target+"\" data-index=\""+i+"\"><i class=\""+qx.icon+"\"></i> <span class=\"nav-label\">"+qx.name+"</span></a>";
            }
        }

        html+="</li>";
    }
   // alert(html);
    console.log(html);
    $("#side-menu").append(html);
}

/*
<li>
<a class="J_menuItem" href="/sys/front" data-index="0"><i class="fa fa-home"></i> <span class="nav-label">首页</span></a>
</li>
<li>
<a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">个人中心</span><span class="fa arrow"></span></a>
<ul class="nav nav-second-level">
<li><a class="J_menuItem" href="/account/personalInfo">个人资料</a>
</li>
<li><a class="J_menuItem" href="article-list.html">修改密码</a>
</li>
</ul>
</li>*/
