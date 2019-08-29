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
    <h1>编辑作业：</h1>
    <div class="alert alert-danger alert-dismissible fade in hidden" role="alert" id="adderrormessage">
        <button type="button" class="close" aria-label="Close" id="closemessage_id"><span
                aria-hidden="true">&times;</span>
        </button>
        <strong>编辑失败!</strong> 请检查网络连接！
    </div>
    <div class="form-group">
        <label for="osubjectEdit" class="col-sm-2 control-label">课程：</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="osubjectEdit" value="${param.osubject}">
        </div>
    </div>
    <div class="form-group">
        <label for="onameEdit" class="col-sm-2 control-label">名称：</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="onameEdit" value="${param.oname}">
        </div>
    </div>

    <input type="hidden" id="timezone" name="timezone" value="+08:00">

    <div class="form-group">
        <label for="odeadlineEdit" class="col-sm-2 control-label">截止：</label>

        <div class='col-sm-10 input-group date' id='odeadlineEdit'>
            <input type='text' class="form-control" />
            <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
            </span>
        </div>
    </div>

    <div class="form-group">
        <label for="ostateEdit" class="col-sm-2 control-label">状态：</label>
        <div class="col-sm-10">
            <label>
                <select class="form-control" id="ostateEdit">
                    <option value=true>启用</option>
                    <option value=false>禁用</option>
                </select>
            </label>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" class="btn btn-default" onclick="edit(${param.oid })">确定</button>
        </div>
    </div>
</form>
<script>
    $("#closemessage_id").click(function () {
        $("#adderrormessage").addClass("hidden");
    });

    $(function () {
        console.log("${param.odeadline}");
        console.log(new Date(parseInt("${param.odeadline}")));
        $('#odeadlineEdit').datetimepicker({
            locale: 'zh-cn',
            defaultDate: new Date(parseInt("${param.odeadline}"))
        });
    });

    function edit(oid) {

        var osubject = $("#osubjectEdit").val();
        var oname = $("#onameEdit").val();
        var ostate = $("#ostateEdit").val(); // should be true or false string

        // here need to convert js data-localtime to
        // var odeadline = $("#odeadline").data("DateTimePicker").date().toDate().getTime(); // this gets wrong timestamp
        var odeadline = $("#odeadlineEdit").data("DateTimePicker").date().unix();
        console.log('odeadline string from JS: ', odeadline);

        var url = "${basePath }updateOrderByOID?oid=" + oid + "&osubject=" + osubject + "&oname=" + oname + "&ostate=" + ostate + "&odeadlinestr=" + odeadline ;
        $.get(encodeURI(url), function (data) {
            if (data) {
                $('#addmodel').modal('hide');
                $("#loadsubject").load("${basePath}subjectui");
            } else {
                $("#adderrormessage").removeClass("hidden");
            }
        });
    }


</script>