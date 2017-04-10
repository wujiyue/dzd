

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
function NavToggle() {
    alert(111);
    $(".navbar-minimalize").trigger("click")
}
var onflag=false;
function SmoothlyMenu() {
    $("body").hasClass("mini-navbar") ? $("body").hasClass("fixed-sidebar") ? ($("#side-menu").hide(), setTimeout(function() {
            $("#side-menu").fadeIn(500)
        },
        300)) : $("#side-menu").removeAttr("style") : ($("#side-menu").hide(), setTimeout(function() {
            $("#side-menu").fadeIn(500)
        },
        100))
}
function localStorageSupport() {
    return "localStorage" in window && null !== window.localStorage
}
layer.config({
    // extend: ["extend/layer.ext.js", "skin/moon/style.css"],

    // skin: "layer-ext-moon"
}),
    $(document).ready(function() {


    }),

    $(function() {

       // loadUserMenu();
      //  setTimeout(function(){
            $(window).resize(function(){
                if($(window).width()>768){
                    $("body").removeClass("mini-navbar");
                    SmoothlyMenu();
                }
            });
            $(".J_menuItem").click(function(){
                //alert(1);
                if($(window).width()<=768) {
                    $(".J_tabCloseOther").trigger("click")
                }

            });
            function e() {
                var e = $("body > #wrapper").height() - 61;
                $(".sidebard-panel").css("min-height", e + "px")
            }

            $("#side-menu").metisMenu(),

                $(".dropdown ul").mouseleave(function() {
                    $(this).parent().toggleClass("open");
                }),

                $(".right-sidebar-toggle").click(function() {
                    var   r = localStorage.getItem("right_menu");

                    if(r=="on")
                    {
                        $("#right-sidebar").toggleClass("sidebar-open-rightmenu");
                        $("#right-sidebar").toggleClass("sidebar-open");
                    }else{
                        $("#right-sidebar").removeClass("sidebar-open-rightmenu");
                        $("#right-sidebar").toggleClass("sidebar-open");
                    }

                }),
                $("#right-sidebar").mouseleave(function() {
                    //setTimeout(function(){
                    $("#right-sidebar").toggleClass("sidebar-open");

                    $("#right-sidebar").removeClass("sidebar-open-rightmenu");


                    // },1000)
                }),

                $(".sidebar-container").slimScroll({
                    height: "100%",
                    railOpacity: .4,
                    wheelStep: 10
                }),
                $(".open-small-chat").click(function() {
                    $(this).children().toggleClass("fa-comments").toggleClass("fa-remove"),
                        $(".small-chat-box").toggleClass("active")
                }),
                $(".small-chat-box .content").slimScroll({
                    height: "234px",
                    railOpacity: .4
                }),
                $(".check-link").click(function() {
                    var e = $(this).find("i"),
                        a = $(this).next("span");
                    return e.toggleClass("fa-check-square").toggleClass("fa-square-o"),
                        a.toggleClass("todo-completed"),
                        !1
                }),
                $(function() {
                    $(".sidebar-collapse").slimScroll({
                        height: "100%",
                        railOpacity: .9,
                        alwaysVisible: !1
                    })
                }),
                $(".navbar-minimalize").click(function() {
                    $("body").toggleClass("mini-navbar"),
                        SmoothlyMenu()
                }),
                e(),
                $(window).bind("load resize click scroll",
                    function() {
                        $("body").hasClass("body-small") || e()
                    }),
                $(window).scroll(function() {
                    $(window).scrollTop() > 0 && !$("body").hasClass("fixed-nav") ? $("#right-sidebar").addClass("sidebar-top") : $("#right-sidebar").removeClass("sidebar-top")
                }),
                $(".full-height-scroll").slimScroll({
                    height: "100%"
                }),
                $("#side-menu>li").click(function() {
                    $("body").hasClass("mini-navbar") && NavToggle()
                }),
                $("#side-menu>li li a").click(function() {
                    $(window).width() < 769 && NavToggle()
                }),
                $(".nav-close").click(NavToggle),
            /(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent) && $("#content-main").css("overflow-y", "auto")

            if ($("#fixednavbar").click(function() {
                    $("#fixednavbar").is(":checked") ? ($(".navbar-static-top").removeClass("navbar-static-top").addClass("navbar-fixed-top"), $("body").removeClass("boxed-layout"), $("body").addClass("fixed-nav"), $("#boxedlayout").prop("checked", !1), localStorageSupport && localStorage.setItem("boxedlayout", "off"), localStorageSupport && localStorage.setItem("fixednavbar", "on")) : ($(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top"), $("body").removeClass("fixed-nav"), localStorageSupport && localStorage.setItem("fixednavbar", "off"))
                }),
                    $("#collapsemenu").click(function() {
                        $("#collapsemenu").is(":checked") ? ($("body").addClass("mini-navbar"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("collapse_menu", "on")) : ($("body").removeClass("mini-navbar"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("collapse_menu", "off"))
                    }),
                    $("#rightmenu").click(function() {
                        var o = localStorage.getItem("boxedlayout");
                        $("#rightmenu").is(":checked") ?  ($("#sliderbar").addClass("navbar-static-side-right"),
                            ("on"==o)?$("#boxedlayout").trigger("click"):null
                            ,$("#page-wrapper").addClass("page-wrapper-rightmenu"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("right_menu", "on")) : ($("#sliderbar").removeClass("navbar-static-side-right"),$("#page-wrapper").removeClass("page-wrapper-rightmenu"),$("#right-sidebar").toggleClass("sidebar-open-rightmenu"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("right_menu", "off"));



                    }),

                    $("#boxedlayout").click(function() {
                        var r = localStorage.getItem("right_menu");
                        $("#boxedlayout").is(":checked") ? ($("body").addClass("boxed-layout"),("on"==r)?$("#rightmenu").trigger("click"):null, $("#fixednavbar").prop("checked", !1), $(".navbar-fixed-top").removeClass("navbar-fixed-top").addClass("navbar-static-top"), $("body").removeClass("fixed-nav"), localStorageSupport && localStorage.setItem("fixednavbar", "off"), localStorageSupport && localStorage.setItem("boxedlayout", "on")) : ($("body").removeClass("boxed-layout"), localStorageSupport && localStorage.setItem("boxedlayout", "off"))
                    }), $(".s-skin-0").click(function() {
                    return $("body").removeClass("skin-1"),
                        $("body").removeClass("skin-2"),
                        $("body").removeClass("skin-3"),
                        !1
                }), $(".s-skin-1").click(function() {
                    return $("body").removeClass("skin-2"),
                        $("body").removeClass("skin-3"),
                        $("body").addClass("skin-1"),
                        !1
                }), $(".s-skin-3").click(function() {
                    return $("body").removeClass("skin-1"),
                        $("body").removeClass("skin-2"),
                        $("body").addClass("skin-3"),
                        !1
                }), localStorageSupport) {
                var e = localStorage.getItem("collapse_menu"),
                    r = localStorage.getItem("right_menu"),
                    a = localStorage.getItem("fixednavbar"),
                    o = localStorage.getItem("boxedlayout");
                "on" == e && $("#collapsemenu").prop("checked", "checked"),
                "on" == r && $("#rightmenu").prop("checked", "checked"),
                "on" == a && $("#fixednavbar").prop("checked", "checked"),
                "on" == o && $("#boxedlayout").prop("checked", "checked")
            }
            if (localStorageSupport) {
                var e = localStorage.getItem("collapse_menu"),
                    r = localStorage.getItem("right_menu"),
                    a = localStorage.getItem("fixednavbar"),
                    o = localStorage.getItem("boxedlayout"),
                    l = $("body");
                "on" == e && (l.hasClass("body-small") || l.addClass("mini-navbar")),
                    "on" == r? ($("#sliderbar").addClass("navbar-static-side-right"),$("#page-wrapper").addClass("page-wrapper-rightmenu"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("right_menu", "on")) : ($("#sliderbar").removeClass("navbar-static-side-right"),$("#page-wrapper").removeClass("page-wrapper-rightmenu"), SmoothlyMenu(), localStorageSupport && localStorage.setItem("right_menu", "off"))
                "on" == a && ($(".navbar-static-top").removeClass("navbar-static-top").addClass("navbar-fixed-top"), l.addClass("fixed-nav")),
                "on" == o && l.addClass("boxed-layout")
            }


     //   },1500);


    });
$(window).bind("load resize",
    function() {
        $(this).width() < 769 && ($("body").addClass("mini-navbar"), $(".navbar-static-side").fadeIn())
    });