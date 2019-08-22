<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/12
  Time: 23:49
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
            $(".every-song").mouseenter(function(){
                if(btnFlag ==0){
                    $(this).children(".every-header").children(".btn-play").css('display','block');
                    $(this).children(".every-spe").children(".do-list-del").css('display','block');
                    btnFlag = 1;
                }
            });
            $(".every-song").mouseleave(function(){
                $(this).children(".every-header").children(".btn-play").css('display','none');
                $(this).children(".every-spe").children(".do-list-del").css('display','none');
                btnFlag = 0;
            });
            $(".topa a").click(function(){
                $(this).css("background",'#35ffaa');
            });

            $(".do-list-del").click(function () {
                $(this).parent().parent().remove();
                $.ajax({
                    url:"/music/deleteLikeSong",
                    type: 'post',
                    dataType: 'json',
                    data:{songId: $(this).attr('property')
                    },
                    success:function(data) {
                        if(data.result =="updateSuccess"){
                            console.log("删除成功"+data.result);
                        }else{
                            console.log("删除失败");
                        }
                        //Result为后端post函数传递来的数据，这里写结果操作代码
                    },error : function(xhr, status, errMsg)
                    {
                        console.log("删除失败");
                    }
                })
            });

            $(".singer-play-btt").click(function(){
                var songBtnId = $(this).attr('property');
                var a ='<%=session.getAttribute("result")%>';
                if(a=="null"||a==""){
                    alert("blank");
                    window.open("/music/play?songId="+songBtnId,"_blank");
                }else{
                    alert("not_blank");
                    $.ajax({
                        url:"/music/play",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{songId: songBtnId},
                        success:function(data)
                        {
                            if(data.result =="addSuccess"){
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
        <li class="topa"  id="top-color"><a href=""><span>我的音乐</span></a></li>
        <li id="top-input"><div>
            <%--<input type="text"/>--%>
        </div></li>
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
            <li><a href="/music/showList">我创建的歌单</a></li>
            <li><a href="/music/showNotice">关注</a></li>
            <li><a href="">粉丝</a></li>
        </ul>
        <ul class="like">
            <li><a href="">歌曲</a></li>
            <li><a href="">专辑</a></li>
            <%--<li><a href="">专辑</a></li>--%>
        </ul>
    </div><br/>
    <div id="show-song">
        <ul id="song-menu">
            <li id="header-song">歌曲</li>
            <li id="header-singer">歌手</li>
            <li id="header-spe">专辑</li>
            <%--<li id="header-time">时长</li>--%>
        </ul>
        <ul class="list-song">
        <c:if test="${not empty songList}">
        <c:forEach items="${songList}" var="song">
        <li>
            <div class="every-song">
                <div class="every-header">
                    <a href="" class="song-name"><span>${song.songName}</span></a>
                    <div class="btn-play">
                        <button class="singer-play-btt" property="${song.id}">播放</button>
                        <select class="btn-list" ><option value="1" >添加到</option>
                            <c:forEach items="${listIter}" var="list">
                                <option value="${list.id}">${list.listName}</option>
                            </c:forEach>
                        </select></div>
                </div>
                <span class="every-singer"><a href="">${song.singer.singerName}</a></span>
                <span class="every-spe"><a href="">${song.special.specialName}</a>
                <button class="do-list-del" property="${song.id}" style="float: right;display: none;width: 50px;height: 40px;">
                    删除</button>
                </span>
            </div>
        </li>
        </c:forEach>
        </c:if>
        </ul>
    </div>
</div>
</body>
</html>
