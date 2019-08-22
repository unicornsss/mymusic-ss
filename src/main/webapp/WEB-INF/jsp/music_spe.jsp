<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/15
  Time: 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="/css/ttt5.css"/>
    <script src="/js/jquery.min.js"></script>
</head>
<body>
<div id="show-top">
    <ul id="top-ul">
        <li id="top-ul1"><img src="/other/logo.png" alt=""/></li>
        <li class="topa" id="top-color"><a href="/music/homePage"><span>音乐馆</span></a></li>
        <li class="topa"><a href="/music/userPage"><span>我的音乐</span></a></li>
        <li id="top-input"><div><input type="text"/></div></li>
        <!--可以退出-->
        <li id="top-5"><a href="">${userShow.username}</a></li>
        <li id="top-6"><a href="/music/outLogin">退出</a></li>
    </ul>
</div>
<div id="page-menu">
    <ul>
        <li><span><a href="">首页</a></span></li>
        <li><span><a href="/music/showSingers">歌手</a></span></li>
        <li><span id="select-btn2"><a href="/music/showSpe">专辑</a></span></li>
        <li><span><a href="">歌单</a></span></li>
        <li><span><a href="">排行榜</a></span></li></ul>
</div>
<div id="page-main">
    <div class="new-song" style="padding-top: 10px;">
        <ul class="page-every-spe">
            <c:forEach items="${pageSpe.lists}" var="spe">
                <li><div><a href="/music/showEverySpe?speId=${spe.id}"><img src="/files/${spe.specialPicture}" alt=""/></a>
                    <span><a href="/music/showEverySpe?speId=${spe.id}">${spe.specialName}</a></span><span>${spe.singer.singerName}</span></div>
                    <%--<span>${spe.createTime}</span>--%>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div id="page-menu-btn-spe" style="margin-top: 600px;">
        <table  border="0" cellspacing="0" cellpadding="0" width="800px" style="margin-left: 450px; margin-bottom: 10px;">
            <tr>
                <td class="td2">
                    <span>第${requestScope.pageSpe.currPage }/ ${requestScope.pageSpe.totalPage}页</span>
                    <span>总记录数：${requestScope.pageSpe.totalCount }  每页显示:${requestScope.pageSpe.pageSize}</span>
               <span>
                   <c:if test="${requestScope.pageSpe.currPage != 1}">
                       <a href="${pageContext.request.contextPath }/music/showSpe?currentPage=1">[首页]</a>
                       <a href="${pageContext.request.contextPath }/music/showSpe?currentPage=${requestScope.pageSpe.currPage-1}">[上一页]</a>
                   </c:if>
                   <c:if test="${requestScope.pageSpe.currPage != requestScope.pageSpe.totalPage}">
                       <a href="${pageContext.request.contextPath }/music/showSpe?currentPage=${requestScope.pageSpe.currPage+1}">[下一页]</a>
                       <a href="${pageContext.request.contextPath }/music/showSpe?currentPage=${requestScope.pageSpe.totalPage}">[尾页]</a>
                   </c:if>
               </span>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
