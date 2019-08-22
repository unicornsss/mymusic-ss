<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/14
  Time: 23:46
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
//                    $(".btn-play").css('display','block');
                    $(this).children(".every-header").children(".btn-play").css('display','block');
//                    $(this).children(".mylist-myname").children(".btn-play").css('display','block');
//                    $(this).children(".mylist-allplay").children(".btn-list-del").css('display','block');
//                    $(this).children(".every-spe").children(".do-list-del").css('display','block');

                    btnFlag = 1;
                }
            });
            $(".every-song").mouseleave(function(){
//                $(".btn-play").css('display','none');
                $(this).children(".every-header").children(".btn-play").css('display','none');
//                $(this).children(".mylist-myname").children(".btn-play").css('display','none');
//                $(this).children(".mylist-allplay").children(".btn-list-del").css('display','none');
//                $(this).children(".every-spe").children(".do-list-del").css('display','none');
                btnFlag = 0;
            });
            $(".topa a").click(function(){
                $(this).css("background",'#35ffaa');
            });
            var noticeFlag = 0;
            $("#singer-notice-btt").click(function(){
//                if(noticeFlag == 0){
//                没有关注
                if($(this).html() =="收藏" ){
                    $(this).html("已收藏");
                    noticeFlag ++;
                    $.ajax({
                        url:"/music/noticeSpeAdd",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{speId: $(this).attr('property') },
                        success:function(data)
                        {
                            if(data.result =="addSuccess"){
                                console.log("收藏成功"+data.result);
                            }else{
                                console.log("收藏失败");
                            }
                            //Result为后端post函数传递来的数据，这里写结果操作代码
                        },error : function(xhr, status, errMsg)
                        {
                            console.log("收藏失败");
                        }
                    });
//                    alert($(this).attr('id'));
                }else{
                    $(this).html("收藏");
                    noticeFlag --;
                    $.ajax({
                        url:"/music/noticeSpeSub",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{speId:$(this).attr('property')},
                        success:function(data){
                            if(data.result =="SubSuccess"){
                                console.log("取消收藏成功"+data.result);
                            }else{console.log("取消失败")}}
                    });
                }
            });
            $(".btn-list").change(function(){
                $.ajax({
                    url:"/music/listAdd",
                    type: 'post',
                    dataType: 'json',
//                        data: {name:"你好"},
                    data:{songId: $(this).parent().parent().attr('property'),
                        listId:$(this).children("option:selected").val()
                    },
                    success:function(data)
                    {
                        if(data.result =="updateSuccess"){
                            console.log("收藏成功"+data.result);
                        }else{
                            console.log("收藏失败");
                        }
                        //Result为后端post函数传递来的数据，这里写结果操作代码
                    },error : function(xhr, status, errMsg)
                    {
                        console.log("收藏失败");
                    }
                });
            });
            $(".singer-play-btt").click(function(){
                var songBtnId = $(this).attr('property');
//                alert(songBtnId);
                var a ='<%=session.getAttribute("result")%>';
                if(a=="null"||a==""){
//                    alert("blank");
                    window.open("/music/play?songId="+songBtnId,"_blank");
                }else{
//                    alert("not_blank");
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
        <li><span><a href="/music/homePage">首页</a></span></li>
        <li><span><a href="/music/showSingers">歌手</a></span></li>
        <li><span id="select-btn2"><a href="">专辑</a></span></li>
        <li><span><a href="">歌单</a></span></li>
        <li><span><a href="">排行榜</a></span></li></ul>
</div>
<div id="page-main">
    <div class="show-sin">
        <div id="every-singer-show"><img src="/files/${special.specialPicture}" alt=""/>
            <div id="singer-details">
                <span id="singer-de-name">${special.specialName}</span><br/>
                <span>发行地区：${special.specialArea}</span>
                <span>语种：${special.specialLanguage}</span>
                <span>类型：${special.specialStyle}</span>
                <p id="singer-de-mes">专辑简介：${special.specialSummary}</p><br/>
                <%--<ul id="singer-des">--%>
                    <%--<li>单曲222</li>--%>
                    <%--<li>|专辑222</li>--%>
                    <%--<li>|粉丝</li></ul>--%>
                <c:if test="${noticeSpeFlag == true}">
                    <button id="singer-notice-btt" property="${special.id}">已收藏</button>
                </c:if><c:if test="${noticeSpeFlag == false}">
                <button id="singer-notice-btt" property="${special.id}">收藏</button>
            </c:if>
            </div>
        </div>
    </div>

    <div id="show-song">
        <ul id="song-menu">
            <li id="header-song">歌曲</li>
            <li class="singer-header-spe">歌手</li>
            <li id="header-time">时长</li>
        </ul>
        <ul class="list-song">
            <c:if test="${not empty songsList}">
                <c:forEach items="${songsList}" var="songs">
                    <li>
                        <div class="every-song">
                            <div class="every-header" property="${songs.id}">
                                <a href="/music/showEverySong?songId=${songs.id}" class="song-name"><span>${songs.songName}</span></a>
                                <div class="btn-play">
                                    <button class="singer-play-btt" property="${songs.id}">播放</button>
                                    <select class="btn-list" ><option value="1" >添加到</option>
                                        <c:forEach items="${lists}" var="lists">
                                        <option value="${lists.id}">${lists.listName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <span class="singer-header-spe"><a href="">${special.singer.singerName}</a></span>
                        </div></li>
                </c:forEach>
            </c:if>
        </ul>
    </div>

</div>
</body>
</html>
