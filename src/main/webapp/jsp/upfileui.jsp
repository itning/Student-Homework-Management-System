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
    <title>文件上传UI</title>
    <link rel="shortcut icon" href="${basePath }img/favicon.ico"/>
    <link rel="bookmark" href="${basePath }img/favicon.ico"/>
    <link href="${basePath }weblib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath }weblib/fileinput/css/fileinput.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath }css/base.css">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="alert alert-warning alert-dismissible fade in" role="alert" id="alert_id">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
    </button>
    <strong>注意：</strong> 拖入或选择文件后可能暂时无响应，请稍等！
</div>
<input type="file" class="file" id="file_ID" name="file" multiple>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${basePath }weblib/jquery/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${basePath }weblib/bootstrap/js/bootstrap.min.js"></script>
<script src="${basePath }weblib/fileinput/js/fileinput.min.js"></script>
<!--<script src="weblib/fileinput/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
<script src="weblib/fileinput/js/plugins/purify.min.js" type="text/javascript"></script>
<script src="weblib/fileinput/js/plugins/sortable.min.js" type="text/javascript"></script>-->
<!--<script src="weblib/fileinput/themes/fa/theme.min.js"></script>-->
<script src="${basePath }weblib/fileinput/js/locales/zh.js"></script>
<script>
    //http://plugins.krajee.com/file-input/plugin-options
    $("#file_ID").fileinput({
        language: 'zh',
        uploadUrl: "fileup", //上传后台操作的方法
        uploadAsync: false, //设置上传同步异步 当前同步
        maxFileSize: 51200, // 50MB now, this in KB,
        minFileSize: 2, // 阻止过小文件上传
        maxFileCount: 3,
        dropZoneTitle: "拖拽文件到这里 &hellip;<br>文件名格式示例: 学号_姓名_文件内容.zip<br>支持最多3个文件同时上传<br>上传文件会被自动打包<br>仅允许上传zip/rar/7z文件<br>总大小不超过50MB",
        allowedFileExtensions: ['zip', 'rar', '7z'], //限制上传文件后缀
        uploadExtraData: {"file_subject": "file_subjectfile_subjectfile_subject"}
    });
</script>
</body>
</html>