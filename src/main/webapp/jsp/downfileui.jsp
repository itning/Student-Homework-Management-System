<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    application.setAttribute("basePath", basePath);
%>
<button type="button" class="btn btn-success" style="margin-bottom: 10px;" onclick="downall()">下载全部本科目作业</button>
<span id="total"></span>
<div class="table-responsive">
    <table class="table table-hover">
        <tr>
            <td>学生学号</td>
            <td>学生姓名</td>
            <td>是否上传</td>
            <td>上传时间</td>
            <td>文件大小</td>
            <td>操作</td>
        </tr>
        <c:set var = "noFileFlag" value="true"/>
        <c:forEach items="${fileListByHoid }" var="filelist">
            <tr
                    <c:if test="${filelist.uptime==null}">
                        class='danger'
                    </c:if>
<%--                    if there is file uploaded, make flag to be false;--%>
                    <c:if test="${filelist.uptime!=null}">
                        <c:set var = "noFileFlag" value="false"/>
                    </c:if>>
                <td><p>${filelist.osubject }</p></td>
                <td><p>${filelist.oname }</p></td>
                <td><p>${filelist.uptime==null?"未上传":"已上传" }</p></td>
                <td><p><fmt:formatDate value="${filelist.uptime }" pattern="yyyy年MM月dd日 HH:mm:ss"/></p></td>
                <td>
                    <p>
                        <c:if test="${filelist.uptime!=null}">
                            <fmt:formatNumber value="${(filelist.filesize)/1024 }" maxFractionDigits="2"/>Kb
                        </c:if>
                    </p>
                </td>
                <td>
                    <button type="button" class="btn btn-info" onclick="down('${filelist.hid }')"
                            <c:if test="${filelist.uptime==null}">disabled='disabled'</c:if>>下载
                    </button>
                    <button type="button" class="btn btn-primary" onclick="view('${filelist.hid }')"
                            <c:if test="${filelist.uptime==null}">disabled='disabled'</c:if>>预览
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script>
    function down(hid) {
        window.open("${basePath }downFile?hid=" + hid, "_blank");
    }

    function downall() {

        // make a check if file list empty, just pop up an alert
        var flag = false;
        flag = ${noFileFlag} ;

        if ( flag !== true  ){
            window.open("${basePath }downAllFile", "_blank");
        } else {
            alert("本科目没有作业提交。");
        }

    }

    function view(hid) {
        window.open("https://view.officeapps.live.com/op/view.aspx?src=${basePath }downFile?hid=" + hid, "_blank");
    }

    $(function () {
        var data = [];
        <c:forEach items="${fileListByHoid }" var="file">
        data.push(${file.filesize});
        </c:forEach>
        var reduce = data.reduce(function (previousValue, currentValue) {
            return previousValue + currentValue;
        });
        console.log(parseInt(reduce) / 1024 / 1024);
        document.getElementById('total').innerText = (parseInt(reduce) / 1024 / 1024).toFixed(2) + 'MB';
    });
</script>