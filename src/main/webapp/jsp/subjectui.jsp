<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    application.setAttribute("basePath", basePath);
%>
<div class="table-responsive">
    <table class="table table-hover">
        <tr>
            <td>科目名称</td>
            <td>批次名称</td>
            <td>当前状态</td>
            <td>管理教师</td>
            <td>上次操作时间</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${allOrderInfo }" var="allorderinfo">
            <tr
                    <c:if test="${!allorderinfo.ostate}">class='danger'</c:if> id="${allorderinfo.oid }">
                <td><p>${allorderinfo.osubject }</p></td>
                <td><p>${allorderinfo.oname }</p></td>
                <td><p>${allorderinfo.ostate?"已启用":"已禁用" }</p></td>
                <td><p></p></td>
                <td><p><fmt:formatDate value="${allorderinfo.otime }" pattern="yyyy年MM月dd日 HH:mm:ss"/></p></td>
                <td>
                    <button type="button" class="btn btn-info btn-primary"
                            onclick="changeState(${allorderinfo.oid },${allorderinfo.ostate})">${allorderinfo.ostate?"禁用":"启用" }</button>
                    <button type="button" class="btn btn-info btn-warning" onclick="edit(${allorderinfo.oid })">编辑
                    </button>
                    <button type="button" class="btn btn-info btn-danger" onclick="del(${allorderinfo.oid })">删除
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script>
    function edit(oid) {
        console.log("edit" + oid);
    }
</script>