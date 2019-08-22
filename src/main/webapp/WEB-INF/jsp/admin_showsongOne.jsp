<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/10/30
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--<html>--%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link rel="stylesheet" href="/css/admin_addsinger.css"/>
    <script>
        function changepic3() {
            var reads= new FileReader();
            f=document.getElementById('uploadfile1').files[0];
            reads.readAsDataURL(f);
            reads.onload=function (e) {
                document.getElementById('showpic').src=this.result;
            };
        }
    </script>
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
            <div >
                <form  action="<%=request.getContextPath()%>/admin/uplrc" method="post" enctype="multipart/form-data" charset=utf-8" >
                    <table>
                        <tr><td></td><td><input type="hidden" name="songId" value="${songShow.id}"/></td></tr>
                        <tr><td>歌名</td><td>${songShow.songName}</td></tr>
                        <tr><td>歌手</td><td>${songShow.singer.singerName}</td></tr>
                        <tr><td>歌曲上传时间</td><td>${songShow.createTime}</td></tr>
                        <tr><td>歌曲风格</td><td>${songShow.songStyle}</td></tr>
                        <tr><td></td><td><input type="file" name="filename" value="上传歌词"/></td></tr>
                        <tr><td>歌词</td><td></td></tr>
                        <tr><td>
                            <td>
                            <c:forEach items="${str}" var="str">
                                ${str}<br/>
                            </c:forEach>
                        </td></tr>
                        <input type="submit" value="确认修改" />
                    </table>
                </form>
            </div>
    </div>
</div>
</body>
</html>
