<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    application.setAttribute("basePath", basePath);
%>
<button type="button" class="btn btn-success" style="margin-bottom: 10px" onclick="downall()">全部下载</button>
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
        <c:forEach items="${fileListByHoid }" var="filelist">
            <tr <c:if test="${filelist.uptime==null}">class='danger'</c:if>>
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
        window.open("${basePath }downAllFile", "_blank");
    }

    function view(hid){
        window.open("https://view.officeapps.live.com/op/view.aspx?src=${basePath }downFile?hid=" + hid, "_blank");
    }

    $(function () {

    });
</script>