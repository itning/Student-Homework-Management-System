<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    application.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>学生作业管理系统-更改密码</title>
    <link rel="shortcut icon" href="${basePath }img/favicon.ico"/>
    <link rel="bookmark" href="${basePath }img/favicon.ico"/>
    <link href="${basePath }weblib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath }css/base.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .form-control {
            margin-bottom: 20px;
        }
    </style>
</head>
<body class="background">
<section>
    <div class="container blur">
        <form class="form-signin" method="post" action="${basePath }changePassword">
            <h2 class="form-signin-heading">请更改您的密码：<p style="color: red" id="errorinfo_id">${errorinfo }</p></h2>
            <input type="number" id="inputID" class="form-control" placeholder="${user.username}" readonly>
            <div class="form-group" id="Password_div">
                <input type="password" name="password" id="inputPassword" class="form-control"
                       placeholder="请输入新密码（密码长度至少8位）" required
                       autofocus>
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
            </div>
            <div class="form-group" id="PasswordAgain_div">
                <input type="password" id="inputPasswordAgain" class="form-control" placeholder="再输入一次新密码" required>
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="button" id="submit_id">确认</button>
        </form>
        <p style="height: 8px"></p>
    </div> <!-- /container -->
</section>
<footer>
    <div class="container">
        <hr>
        <p> 黑ICP备17003448号 | Copyright © 2017 <a href="http://itning.top">itning.top</a>. All rights reserved. </p>
    </div>
</footer>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${basePath }weblib/jquery/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${basePath }weblib/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath }js/base.js"></script>
<script>
    $(function () {
        var height = $(window).height(); //浏览器当前窗口可视区域高度
        var section_height = $("section").outerHeight(true);
        $("section").css("margin-top", (height / 2) - (section_height / 2));
        $(window).resize(function () {
            var height = $(window).height(); //浏览器当前窗口可视区域高度
            var section_height = $("section").outerHeight(true);
            $("section").css("margin-top", (height / 2) - (section_height / 2));
        });
        var inputpasswd = "";
        var passwdlength = 0;
        var inputpasswdAgain = "";
        var passwdAgainlength = 0;
        $("#inputPassword").blur(function () {
            inputpasswd = $(this).val();
            passwdlength = inputpasswd.length;
            if (passwdlength === 0 || passwdlength < 8) {
                $("#errorinfo_id").text("请填写新密码且密码长度至少8位！");
                $("#Password_div").addClass("has-error has-feedback");
            } else {
                $("#errorinfo_id").text("");
                $("#Password_div").removeClass("has-error has-feedback");
            }
        });
        $("#inputPasswordAgain").blur(function () {
            inputpasswdAgain = $(this).val();
            passwdAgainlength = inputpasswdAgain.length;
            if (inputpasswdAgain !== inputpasswd || passwdlength !== passwdAgainlength && passwdlength !== 0) {
                $("#errorinfo_id").text("两次输入密码不一致！");
                $("#PasswordAgain_div").addClass("has-error has-feedback");
            }
            else {
                $("#errorinfo_id").text("");
                $("#PasswordAgain_div").removeClass("has-error has-feedback");
            }
        });
        $("#submit_id").click(function () {
            if (passwdlength !== 0 && passwdAgainlength !== 0) {
                document.forms[0].submit();
            } else if (passwdAgainlength == 0) {
                $("#errorinfo_id").text("请再次输入密码！");
                $("#Password_div").addClass("has-error has-feedback");
            } else {
                $("#errorinfo_id").text("请填写新密码！");
                $("#Password_div").addClass("has-error has-feedback");
            }
        });
    });
</script>
</body>
</html>