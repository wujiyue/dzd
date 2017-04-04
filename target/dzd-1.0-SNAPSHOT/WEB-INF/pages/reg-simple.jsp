<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.markbro.asoiaf.core.utils.SysPara" %>
<%@include file="/WEB-INF/pages/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>注册</title>
        <script type="text/javascript" src="${ctx}/resources/lib/jquery/jquery.min.js"></script>

        <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/H-ui.css">
        <link type="text/css" rel="stylesheet" href="${ctx}/resources/css/animate.min.css">
        <link type="text/css" rel="stylesheet" href="${ctx}/resources/lib/icheck/icheck.css">
        <script type="text/javascript">

        </script>
        <style>
            .gray-bg {
                background-color: #f3f3f4;
            }
            .loginscreen.middle-box {
                width: 300px;
            }
            .middle-box {
                max-width: 400px;
                z-index: 100;
                margin: 0 auto;
                padding-top: 40px;
            }
            .text-center {
                text-align: center;
            }
            h3, h4, h5 {
                margin-top: 5px;
                font-weight: 600;
            }
            h3 {
                font-size: 16px;
            }
            .logo-name {
                color: #e6e6e6;
                font-size: 180px;
                font-weight: 800;
                letter-spacing: -10px;
                margin-bottom: 0;
            }
            .m-t {
                margin-top: 15px;
            }
            p {
                margin-bottom: 10px;
            }
            a {
                color: #337ab7;
                text-decoration: none;
            }
            a:focus, a:hover {
                color: #23527c;
                text-decoration: none;
            }
            .uname {
                background: #fff url(/front/images/user.png) no-repeat 95% center;
                color: #333;
            }
            .pword {
                background: #fff url(/front/images/locked.png) no-repeat 95% center;
                color: #333;
            }
            .text-left {
                text-align: left;
            }
        </style>
    </head>

    <body class="gray-bg">
    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">H+</h1>

            </div>
            <h3>欢迎注册 H+</h3>
            <p>创建一个H+新账户</p>
            <form class="m-t" role="form" action="#">
                <div class="formControls m-t">
                    <input type="text" class="input-text uname" placeholder="请输入用户名" required="" >
                </div>
                <div class="formControls m-t">
                    <input type="password" class="input-text pword" placeholder="请输入密码" required="">
                </div>
                <div class="formControls m-t">
                    <input type="password" class="input-text pword" placeholder="请再次输入密码" required="">
                </div>

                <div class="formControls m-t text-left">
                    <div class="check-box">
                        <input type="checkbox" class="icheckbox-blue" id="checkbox-1">
                        <label for="checkbox-1">我同意<a style="text-decoration: underline" href="#">注册协议</a></label>
                    </div>

                </div>

                <div class="formControls m-t">
                    <button type="submit" class="btn btn-primary btn-block m-b">注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;册</button>
                </div>

                <p class="text-muted text-center  m-t"><small>已经有账户了？</small><a href="/login">点此登录</a>
                </p>

            </form>
        </div>
    </div>

    <script type="text/javascript" src="${ctx}/resources/lib/icheck/jquery.icheck.min.js"></script>
    </body>
</html>

