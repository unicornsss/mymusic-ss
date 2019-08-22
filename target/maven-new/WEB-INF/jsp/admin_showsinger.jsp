<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/10/30
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>管理员</title>
    <link rel="stylesheet" href="/css/admin_shows.css"/>
</head>
<body>
<div id="all">
    <div id="manager-top"><div id="manager-name">管理者后台</div><div id="manager-out"><a href="/admin/AdminOut">退出</a></div></div>
    <div id="show-list">
        <ul id="list-menu">
            <li><div><a href="/admin/showSinger">歌手管理</a></div></li><br/>
            <li><div><a href="/admin/showAllSongs">歌曲管理</a></div></li><br/>
            <li><div><a href="/admin/showSpe">专辑管理</a></div></li><br/>
            <li><div><a href="">歌曲管理</a></div></li><br/>
            <li></li>
        </ul>
    </div>
    <div id="show-message">
        <div id="add-singer"><a href="/admin/addSinger">新增歌手</a></div>
        <%--<div class="show-every-singer">--%>
            <%--<a href="">--%>
                <%--<img src="/files/e4072b39jw8e95if2jjvrj20e80e83z0.jpg" alt="" class="show-pic"/>--%>
                <%--<p>Tablo</p>--%>
            <%--</a>--%>
        <%--</div>--%>
        <c:forEach items="${singerList}" var="singerList">
            <div class="show-every-singer">
                <a href="/admin/updateSinger/?singerId=${singerList.id}" >
                    <%--<p2>/files/${singerList.singerPicture}</p2>--%>
                    <img src="/files/${singerList.singerPicture}" alt="" class="show-pic"/>
                    <p>${singerList.singerName}</p>
                    <%--<p><a href="/admin/updateSinger/?singerId=${singerList.id}">修改</a></p>--%>
                </a>
                <a href="/admin/deleteSinger?singerId=${singerList.id}" ><span>删除</span></a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
