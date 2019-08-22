<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/13
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="/css/ttt5.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
        $(function(){
            var btnFlag = 0;
            $(".notice-singer-btn").click(function () {
                if(btnFlag == 0){
                    $(this).html("关注");
                    btnFlag ++;
                    $.ajax({
                        url:"/music/noticeSingerSub",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{singerId:$(this).attr('id')},
                        success:function(data)
                        {
                            if(data.result =="success"){
                                console.log("取消关注成功"+data.result);
                            }else{
                                console.log("取消关注失败");
                            }
                            //Result为后端post函数传递来的数据，这里写结果操作代码
                        },error : function(xhr, status, errMsg)
                        {
                            console.log("取消关注失败");
                        }
                    });
//                    alert($(this).attr('id'));
                }else{
                    $(this).html("已关注");
                    btnFlag --;
                    $.ajax({
                        url:"/music/noticeSingerAdd",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{singerId:$(this).attr('id')},
                        success:function(data)
                        {
                            if(data.result =="success"){
                                console.log("关注成功"+data.result);
                            }else{
                                console.log("关注失败");
                            }
                            //Result为后端post函数传递来的数据，这里写结果操作代码
                        },error : function(xhr, status, errMsg)
                        {
                            console.log("关注失败");
                        }
                    });
                }
            });
        });
    </script>
</head>
<body>
<div id="show-top">
    <ul id="top-ul">
        <li id="top-ul1"><img src="/other/logo.png" alt=""/></li>
        <li class="topa" ><a href="/music/homePage"><span>音乐馆</span></a></li>
        <li class="topa" id="top-color"><a href="/music/userPage"><span>我的音乐</span></a></li>
        <li id="top-input">
            <%--<div><input type="text"/></div>--%>
        </li>
        <!--可以退出-->
        <li id="top-5"><a href="">${userShow.username}</a></li>
        <li id="top-6"><a href="/music/outLogin">退出</a></li>
    </ul>
</div>
<div class="show-img">
    <div >
        <img src="/files/${userShow.userPic}" alt="" id="show-img-self"/>
    </div>
    <h1>${userShow.username}</h1>
    <ul class="show-fan">
        <li>关注${userShow.noticeSinger}|</li>
        <li>|粉丝${userShow.fans}</li>
    </ul>
</div>
<div class="main">
    <div id="show-menu">
        <ul class="menu">
            <li><a href="">我喜欢</a></li>
            <li><a href="">我创建的歌单</a></li>
            <li><a href="" id="my-menu-label">关注</a></li>
            <li><a href="">粉丝</a></li>
        </ul>
        <ul class="like">
            <li><a href="">歌手</a></li>
            <li><a href="">用户</a></li>
        </ul>
    </div><br/>
    <div id="show-song">
        <!--1540976864022&&tablo.jpg-->
        <c:if test="${not empty showSinger}">
            <c:forEach items="${showSinger}" var="singer">
                <div class="follow-singer">
                    <img src="/files/${singer.singerPicture}" alt=""/>
                    <span>${singer.singerName}</span><br/>
                    <span>${singer.singerNotice}</span><br/>
                    <span class="notice-singer-btn" id="${singer.id}">已关注</span>
                    <%--<a href="">已关注</a>--%>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>
</html>
