<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    application.setAttribute("basePath", basePath);
%>

<link rel="stylesheet" href="${basePath }css/subject.css">

<form class="form-horizontal">
    <h1>添加学科和类别：</h1>
    <div class="alert alert-danger alert-dismissible fade in hidden" role="alert" id="adderrormessage">
        <button type="button" class="close" aria-label="Close" id="closemessage_id"><span
                aria-hidden="true">&times;</span>
        </button>
        <strong>添加失败!</strong> 请检查网络连接！
    </div>
    <div class="form-group">
        <label for="osubject" class="col-sm-2 control-label">课程：</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="osubject" placeholder="请输入课程名称...">
        </div>
    </div>
    <div class="form-group">
        <label for="oname" class="col-sm-2 control-label">批次：</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="oname" placeholder="请输入作业名称...">
        </div>
    </div>

    <input type="hidden" id="timezone" name="timezone" value="+08:00">

    <div class="form-group">
        <label for="oname" class="col-sm-2 control-label">截止：</label>

        <div class='col-sm-10 input-group date' id='odeadline'>
            <input type='text' class="form-control" />
            <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
    </div>

    <div class="form-group">
        <label for="oname" class="col-sm-2 control-label">状态：</label>
        <div class="col-sm-10">
            <label>
                <select class="form-control" id="ostate">
                    <option value=true>启用</option>
                    <option value=false>禁用</option>
                </select>
            </label>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-default" onclick="add()">添加</button>
        </div>
    </div>
</form>
<script>
    $("#closemessage_id").click(function () {
        $("#adderrormessage").addClass("hidden");
    });

    $(function () {
        $('#odeadline').datetimepicker({
            locale: 'zh-cn'
        });
    });



</script>