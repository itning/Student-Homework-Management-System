/**
 * Created by wangn on 2017/5/23.
 */
$(function () {
    function getProPath() {
        //获取当前网址，如： http://localhost:8083/proj/meun.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： proj/meun.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPath = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/proj
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1);
        return localhostPath+projectName;
    }
    var width = $(window).width();
    if (width > 992) {
        $("body").append("<script src="+getProPath()+"/js/canvas-nest.min.js></script>");
    }
});