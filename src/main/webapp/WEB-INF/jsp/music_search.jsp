<%--
  Created by IntelliJ IDEA.
  User: 羊
  Date: 2018/11/20
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="/css/ttt5.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
        $(function(){
            var btnFlag = 0;
            $(".every-song").mouseover(function(){
//                alert("xxxx");
                if(btnFlag ==0){
//                    $(".btn-play").css('display','block');
                    $(this).children(".every-header").children(".btn-play").css('display','block');
                    btnFlag = 1;
                }
            });
            $(".every-song").mouseleave(function(){
//                $(".btn-play").css('display','none');
                $(this).children(".every-header").children(".btn-play").css('display','none');
                btnFlag = 0;
            });
             var flag2 = 0
            $(".ser-song").mouseenter(function(){
//                alert("xxxx");
                if(flag2 ==0){
//                    $(".btn-play").css('display','block');
                    $(this).children(".every-song").children(".every-header").children(".btn-play").css('display','block');
                    flag2 = 1;
                }
            });
            $(".ser-song").mouseleave(function(){
                alert("yyy");
                if(flag2 ==1){
//                    $(".btn-play").css('display','block');
                    $(this).children(".every-song").children(".every-header").children(".btn-play").css('display','none');
                    flag2 = 0;
                }
            });
            $(".every-header").mouseover(function(){alert("aaa")});
            var noticeFlag = 0;
            $("#singer-notice-btt").click(function(){
                if($(this).html() =="我喜欢" ){
//                    alert($(this).html());
                    $(this).html("已喜欢");
                    noticeFlag ++;
                    $.ajax({
                        url:"/music/addLikeSong",
                        type: 'post',
                        dataType: 'json',
//                        data: {name:"你好"},
                        data:{songId: ${songShow.id}},
                        success:function(data)
                        {
//                            alert(data);
                            if(data.result =="addSuccess"){
                                console.log("关注成功"+data.result);
                            }else{
                                console.log("关注失败");
                            }
                            //Result为后端post函数传递来的数据，这里写结果操作代码
                        },error : function(xhr, status, errMsg)
                        {
                            console.log("关注失败");
                            alert("error");
                        }
                    });
//                    alert($(this).attr('id'));
                }else{
                    $(this).html("我喜欢");
                    noticeFlag --;
                    $.ajax({
                        url:"/music/deleteLikeSong",
                        type: 'post',
                        dataType: 'json',
                        data:{songId: ${songShow.id}
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
                }
            });

            $(".singer-play-btt").click(function(){
                var songBtnId = $(this).attr('property');
                alert(songBtnId);
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
        });
    </script>
</head>
<body>
<div id="show-top">
    <ul id="top-ul">
        <li id="top-ul1"><img src="../other/logo.png" alt=""/></li>
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
        <li><span><a href="">歌手</a></span></li>
        <li><span><a href="">专辑</a></span></li>
        <li><span><a href="">歌单</a></span></li>
        <li><span><a href="">排行榜</a></span></li></ul>
</div>
<div id="page-main">
    <div class="show-sin">
        </div>
        <br/><br/><br/>
        <div id="show-song">
        <div id="comment">
        <span class="comment-title1">歌手</span>
        <%--<span>共10条评论</span>--%>
        <br/>
        <c:if test="${not empty singers}">
        <c:forEach items="${singers}" var="singer" varStatus="status">
            <div class="every-comment" style="float: left;">
                <div><img src="/files/${singer.singerPicture}" alt="" /></div>
                <%--<div><img src="/files/${comment.user.userPic}" alt="" /></div>--%>
                <%--<a href="/">//--%>
                <a href="/music/showEverySinger?singerId=${singer.id}">
                    <span class="comment-name">${singer.singerName}</span></a>
                    <%--<span>${numResult[status.index]}</span>--%>
                <p>
                    ${singer.singerSummary}
                </p>
            </div>
        </c:forEach>
        </c:if>

            <ul class="list-song">
                <c:if test="${not empty songsList}">
                    <c:forEach items="${songsList}" var="songs">
                        <li class="ser-song" >
                            <div class="every-song" style="background: #d1d1d1">
                                <div class="every-header" property="${songs.id}" style="background: #d1d1d1">
                                    <a href="/music/showEverySong?songId=${songs.id}" class="song-name"><span>${songs.songName}</span></a>
                                    <div class="btn-play" >
                                        <button class="singer-play-btt" property="${songs.id}">播放</button>
                                        <select class="btn-list" ><option value="1" >添加到</option>
                                            <c:forEach items="${lists}" var="lists">
                                                <option value="${lists.id}">${lists.listName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <%--<span class="singer-header-spe"><a href="">${songs.singer.singerName}</a></span>--%>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
        </div>
        </div>
</body>
</html>
