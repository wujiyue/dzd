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
    function e() {
        var e = $("body > #wrapper").height() - 61;
        $(".sidebard-panel").css("min-height", e + "px")
    }
    $(function() {

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

    });
$(window).bind("load resize",
    function() {
        $(this).width() < 769 && ($("body").addClass("mini-navbar"), $(".navbar-static-side").fadeIn())
    });